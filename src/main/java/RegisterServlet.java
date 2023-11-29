import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Model.*;
import Model.Dao.accountDAO;

import java.io.IOException;
import java.sql.*;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    // Method to set session attributes
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get user input from the login form
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fname = request.getParameter("Firstname");
        String lname = request.getParameter("Lastname");
        String usern = request.getParameter("userName");

        try {
            accountDAO account = new accountDAO();
            if (account.usernameValid(usern)) {
                request.setAttribute("errorMessage","The username is already taken");
                response.sendRedirect("register.jsp");
            }
            else if(account.emailValid(email)){
                // If user is a customer, create a User object and set it in the session
                account.Registeracc(email,password,usern,fname,lname);
                HttpSession session = request.getSession();
                request.setAttribute("errorMessage","Account succesfully created");
                response.sendRedirect("login.jsp");
            }
            else {
                // If email and password do not match any user, set an error message
                request.setAttribute("errorMessage", "This email already has an account linked to it");
                RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
                rd.forward(request, response);
            }
        } catch(Exception e){
            System.out.println(" Login Error - " + e.getMessage());
        }
    }

}