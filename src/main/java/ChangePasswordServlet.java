
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.Dao.*;
import Model.*;
import java.sql.*;
import java.io.IOException;

@WebServlet("/ChangePassword")
public class  ChangePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newpass = request.getParameter("pass");
        Account user = (Account) request.getSession().getAttribute("User");
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            accountDAO account = new accountDAO();
            account.changePassword(newpass, user.getAccountId());
            user.setPassword(newpass);
            request.getSession().setAttribute("User", user);
            request.setAttribute("errorMessage","Password succesfully Changed");
            RequestDispatcher rd = request.getRequestDispatcher("ChangePassword.jsp");
            rd.forward(request, response);
            }  catch(Exception e){
            System.out.println(" Login Error - " + e.getMessage());
        } finally {
            // Close statement and connection
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}