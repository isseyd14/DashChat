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

@WebServlet("/RejectFRServlet")
public class RejectFRServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String PendingUsername = request.getParameter("userId");
        Account acco = (Account) request.getSession().getAttribute("User");
        try{
            accountDAO account = new accountDAO();
            Account acc = account.ReturnUsername(PendingUsername);
            FriendRequestDAO FriendRequest = new FriendRequestDAO();
            FriendRequest.RejectFriendRequest(acco.getAccountId(), acc.getAccountId());
            response.sendRedirect("FriendRequest.jsp");
        }catch(Exception e){
            System.out.println(" Login Error - " + e.getMessage());
        }
    }
}