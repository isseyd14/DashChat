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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    // Method to set session attributes
    private void setSessionAttrs(HttpSession session,  Account acc ) {
        session.setAttribute("email", acc.getEmail());
        session.setAttribute("User", acc);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get user input from the login form
        String email = request.getParameter("email");
        String password = request.getParameter("Password");

        try {
            accountDAO account = new accountDAO();

            if(account.LoginValid(password,email)){
                // If user is a customer, create a User object and set it in the session
                System.out.println("Hello, GlassFish!");
                Account acco = account.ReturnAcc(email, password);
                System.out.println(acco.getUsername());
                HttpSession session = request.getSession();
                setSessionAttrs(session, acco);
                response.sendRedirect("Home.jsp");
            }
            else {
                // If email and password do not match any user, set an error message
                request.setAttribute("errorMessage", "Username or password incorrect. Try again.");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }
        } catch(Exception e){
            System.out.println(" Login Error - " + e.getMessage());
        }
    }

}