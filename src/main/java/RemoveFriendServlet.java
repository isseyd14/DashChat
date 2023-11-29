import Model.Dao.*;
import Model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/RemoveFriend")
public class RemoveFriendServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String PendingUsername = request.getParameter("userId");
        Account main = (Account) request.getSession().getAttribute("User");
        try{
            MessageDAO message = new MessageDAO();
            accountDAO account = new accountDAO();
            Account acc = account.ReturnUsername(PendingUsername);
            FriendDao Friend = new FriendDao();
            Friend.RemoveFriend(main.getAccountId(), acc.getAccountId());
            message.DeleteConversation(main.getAccountId(), acc.getAccountId());
            response.sendRedirect("Friends.jsp");
        }catch(Exception e){
            System.out.println(" Login Error - " + e.getMessage());
        }
    }
}