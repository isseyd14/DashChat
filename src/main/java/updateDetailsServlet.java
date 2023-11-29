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

@WebServlet("/updateDetailsServlet")
public class updateDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        accountDAO account = new accountDAO();
        try {
            if (!account.usernameValid(username)) {
                Account user = (Account) request.getSession().getAttribute("User");
                account.UpdateUserDetails(firstName,lastName,username,user.getAccountId());
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setUsername(username);
                request.getSession().setAttribute("User", user);
                response.sendRedirect("Account.jsp");
            } else{
                request.setAttribute("errorMessage", "The username is already taken please try again");
                RequestDispatcher rd = request.getRequestDispatcher("Account.jsp");
                rd.forward(request, response);
            }
        }
        catch(Exception e){
            System.out.println(" Login Error - " + e.getMessage());
        }
    }
    // Redirect back to the account details page or another appropriate page
}