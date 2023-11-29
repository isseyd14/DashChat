package Model.Dao;

import Model.*;

import javax.sound.midi.Receiver;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
public class MessageDAO {
    protected Connection getConnection() {
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/?user=root?autoReconnect=true&useSSL=false";
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, "root", "root");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database. Check the database URL, username, and password.", e);
        }
    }
    // intiate a conversation for a user as soon as they open up
    public void Initiateconversation(String id1, String id2) throws SQLException {
        String sql = "INSERT INTO webchat.user_conversations (Account_id_1, Account_id_2) VALUES (?,?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id1);
            ps.setString(2, id2);
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
    // delete a conversation after a user has deleted the friend ship
    public void DeleteConversation(String id1, String id2) throws SQLException {
        String sql = "DELETE FROM webchat.user_conversations WHERE (Account_id_1 = ? AND Account_id_2 = ?) or (Account_id_1 = ? AND Account_id_2 = ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id1);
            ps.setString(2, id2);
            ps.executeUpdate();
            ps.clearParameters();
            ps.setString(3, id2);
            ps.setString(4, id1);
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // returns a conversation id between two users
    public String ReturnConversation(String id1, String id2) throws SQLException {
        String sql = "SELECT conversation_id FROM webchat.user_conversations WHERE (Account_id_1 = ? AND Account_id_2 = ?) or (Account_id_1 = ? AND Account_id_2 = ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id1);
            ps.setString(2, id2);
            ps.setString(3, id2);
            ps.setString(4, id1);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Conversation ID found, return it
                    return rs.getString("conversation_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return null if no conversation ID is found
        return null;
    }
    //return ConvoID between two users
    public String ReturnConvoID(String SenderID, String RecieverID) {
        String sql = "SELECT conversation_id FROM webchat.user_conversations WHERE (Account_id_2 = ? AND Account_id_1 = ?) or (Account_id_2 = ? AND Account_id_1 = ?)";
        String Convoid = "";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, SenderID);
            ps.setString(2, RecieverID);
            ps.setString(4, SenderID);
            ps.setString(3, RecieverID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Conversation ID found, return it
                    return rs.getString("conversation_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    //add a message to the Database to save the stored information
    public void SendMessage(String conversationID, String Context, String SenderID){
        String sql = "INSERT INTO webchat.messages (conversation_id, content, sender_id) VALUES (?,?,?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, conversationID);
            ps.setString(2, Context);
            ps.setString(3, SenderID);
            System.out.println("message worked");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //return messages between two users
    public List<Message> Returnmessages(String ConvoID, String UserID) throws SQLException {
        String sql = "SELECT content, timestamp FROM webchat.messages WHERE sender_id = ? AND conversation_id = ?";
        List<Message> pairList = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, UserID);
            ps.setString(2, ConvoID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Conversation ID found, return it
                    String content = rs.getString("content");
                    Timestamp time = rs.getTimestamp("timestamp");
                    Message pair1 = new Message(content, time);
                    pairList.add(pair1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pairList;
    }
    //deletes messages after being read
    public void deleteMessages( String senderId, String conversationId) {
        String sql = "DELETE FROM webchat.messages WHERE sender_id = ? AND conversation_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, senderId);
            ps.setString(2, conversationId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //check if there are messages which still haven't been read

    public int CheckUnreadConvo(String SenderID, String ReceiverID){
        String ConvoID = ReturnConvoID(SenderID, ReceiverID);
        List<Message> ReceiverMessages = null;
        try {
            ReceiverMessages = Returnmessages(ConvoID, ReceiverID);
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return ReceiverMessages.size();
    }

}
