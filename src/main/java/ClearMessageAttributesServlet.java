import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/clearMessageAttributes")
public class ClearMessageAttributesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Remove session attributes
        request.getSession().removeAttribute("SenderMessages");
        request.getSession().removeAttribute("receiverMessages");
        request.getSession().removeAttribute("ReceiverAccount");
        request.getSession().removeAttribute("ConversationID");
        // You might also want to invalidate the session if needed
        // session.invalidate();
        // Send a response to the client (optional)
        response.setContentType("text/plain");
        response.getWriter().write("Session attributes cleared successfully");
    }
}