
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

@WebServlet("/AddFriendServlet")
public class AddFriendServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //second id (sent request)
        String username = request.getParameter("username");
        Account acc = (Account) request.getSession().getAttribute("User");

        try{
            accountDAO account = new accountDAO();
            FriendDao friend = new FriendDao();
            FriendRequestDAO FR = new FriendRequestDAO();
            Account Friendacc = account.ReturnUsername(username);
            if (!account.emailValid(Friendacc.getEmail())) {
                request.getSession().setAttribute("openModal", true);
                request.setAttribute("errorMessage2", "username doesnt exist");
                RequestDispatcher rd = request.getRequestDispatcher("Friends.jsp");
                rd.forward(request, response);
            } else if (FR.checkFR(acc.getAccountId(),Friendacc.getAccountId())) {
                request.setAttribute("errorMessage2","Friend request already has been sent or is waiting to be accepted");
                request.getSession().setAttribute("openModal", true);
                RequestDispatcher rd = request.getRequestDispatcher("Friends.jsp");
                rd.forward(request, response);
            } else if (!friend.checkFriend(acc.getAccountId(),Friendacc.getAccountId())) {
                friend.sendFriendRequest(acc.getAccountId(),Friendacc.getAccountId());
                request.setAttribute("errorMessage2","Friend request sent succesfully");
                request.getSession().setAttribute("openModal", true);
                RequestDispatcher rd = request.getRequestDispatcher("Friends.jsp");
                rd.forward(request, response);
                System.out.println("friend request sent");
            }
            else{
                request.setAttribute("errorMessage2","This user is already on your friends list");
                request.getSession().setAttribute("openModal", true);
                RequestDispatcher rd = request.getRequestDispatcher("Friends.jsp");
                rd.forward(request, response);
            }

        }catch(Exception e){
            System.out.println(" Login Error - " + e.getMessage());
        }


    }
}
