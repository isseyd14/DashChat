import Model.Dao.*;
import Model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/WebConnecting")
public class WebConnectingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String username = request.getParameter("username");
        Account user = (Account) request.getSession().getAttribute("User");
        accountDAO account = new accountDAO();
        MessageDAO message = new MessageDAO();
        try {
            Account receiver = account.ReturnUsername(username);
            String ConvoId = message.ReturnConversation(user.getAccountId(), receiver.getAccountId());
            List<Message> ReceiversMessages = message.Returnmessages(ConvoId, receiver.getAccountId());
            request.getSession().setAttribute("receiverMessages", ReceiversMessages);
            request.getSession().setAttribute("ReceiverAccount", receiver);
            request.getSession().setAttribute("ConversationID", ConvoId);
            System.out.println(ConvoId);

            response.sendRedirect("Home.jsp");
        }

        catch(Exception e){
            System.out.println(" Login Error - " + e.getMessage());
        }
    }

    // Redirect back to the account details page or another appropriate page
}