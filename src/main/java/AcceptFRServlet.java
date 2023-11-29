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

@WebServlet("/AcceptFRServlet")
public class AcceptFRServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String PendingUsername = request.getParameter("userId");
        Account main = (Account) request.getSession().getAttribute("User");
        try{
            FriendRequestDAO FriendRequest = new FriendRequestDAO();
            MessageDAO message = new MessageDAO();
            accountDAO account = new accountDAO();
            Account acc = account.ReturnUsername(PendingUsername);
            System.out.print("a" + acc.getEmail());
            System.out.print("g" + main.getEmail());
            FriendRequest.AcceptFriendRequest(main.getAccountId(), acc.getAccountId());
            message.Initiateconversation(main.getAccountId(), acc.getAccountId());
            response.sendRedirect("FriendRequest.jsp");
        }catch(Exception e){
            System.out.println(" Login Error - " + e.getMessage());
        }
    }
}
