package Sever;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Model.Account;
import Model.Message;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import Model.WSMessage;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import Model.Dao.*;

@ServerEndpoint(value = "/webchat/{username}")
public class WebChat {

    private static Map<String, Session> userSessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        session.getUserProperties().put("username", username);
        userSessions.put(username, session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        Jsonb jsonb = JsonbBuilder.create();
        WSMessage wsMessage = jsonb.fromJson(message, WSMessage.class);
        System.out.println("message sent");

        String type = wsMessage.getType();
        if ("chat".equals(type)) {
            String senderUsername = wsMessage.getRecipient();
            String body = wsMessage.getBody();
            sendMessage(wsMessage, session);
        }
        else if ("wrongSession".equals(type)){
            SendOffline(wsMessage);
        }
    }

    private void sendMessage(WSMessage message, Session session){
        Session recipientSession = userSessions.get(message.getRecipient());
        System.out.println(message.getBody());
        if (recipientSession != null && recipientSession.isOpen()) {
            try {
                // Send the message to the recipient's session
                Jsonb responseJsonb = JsonbBuilder.create();
                String jsonResponse = responseJsonb.toJson(message);
                //this is sent regardless in case the user is online but isnt on the current session chat dialogue
                recipientSession.getBasicRemote().sendText(jsonResponse);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Recipient user not found: " + message.getRecipient());
            SendOffline(message);
        }
    }


    private void SendOffline(WSMessage message){
        try {
            MessageDAO mess = new MessageDAO();
            accountDAO account = new accountDAO();
            String ReceiverUsername = message.getRecipient();
            String SenderUsername = message.getUserName();
            Account Sender =  account.ReturnUsername(SenderUsername);
            Account Receiver =  account.ReturnUsername(ReceiverUsername);
            String convoID = mess.ReturnConvoID(Sender.getAccountId(), Receiver.getAccountId());
            mess.SendMessage(convoID ,message.getBody(),Sender.getAccountId() );
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @OnClose
    public void onClose(Session session, CloseReason reason) {
        System.out.println("WebSocket connection closed: " + reason.getReasonPhrase());
    }
}
