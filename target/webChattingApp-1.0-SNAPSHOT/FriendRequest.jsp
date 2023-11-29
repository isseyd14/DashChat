<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Dao.FriendDao" %>
<%@ page import="Model.Account" %>
<%@ page import="Model.Dao.FriendRequestDAO" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap Example</title>

    <!-- Link to Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <!--<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-rbHb5e9BR29VU7WqgDD1tuQaBVuztsC2p4ctL1upFQCTBBfQ/rW1yD02eEUP1ck" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <!-- Your custom CSS if needed -->
    <style>
        .logo {
            max-height: 50px; /* Adjust the height as needed */
            margin-right: 10px; /* Optional: Adjust the margin */
        }
    </style>
</head>
<body>

<%
    FriendRequestDAO friend = new FriendRequestDAO();
    Account acc = (Account) session.getAttribute("User");
    ArrayList<Account> friendRequests = friend.ReturnFriendRequests(acc);
%>

<jsp:include page="navbar.jsp" />

<div class="container mt-5 text-center">
    <h1> DashChat With your friends </h1>
</div>
<br>
<div class="d-flex aligns-items-center justify-content-center card text-center w-50 mx-auto">
    <ul class="list-group">
        <li class="list-group-item d-flex justify-content-between align-items-center">
            <h4>Friend Requests</h4>
        </li>
        <!--<li class="list-group-item d-flex justify-content-between align-items-center">
            <input type="text" class="form-control" placeholder="Search friends..." id="friendSearch">
        </li>-->
        <%
            if (!friendRequests.isEmpty()) {
                for (Account item : friendRequests) { %>
        <li class="list-group-item d-flex justify-content-between align-items-center">
            <label id="PendingRequestUsername"> <%= item.getUsername() %></label>
            <div class="d-flex justify-content-end">
                <a href="#" class="badge rounded-pill ms-1 text-decoration-none" onclick="submitFormAndRefresh('AcceptFRServlet', '<%= item.getUsername() %>')">✔</a>
                <a href="#" class="badge text-danger rounded-pill ms-1 text-decoration-none" onclick="submitFormAndRefresh('RejectFRServlet', '<%= item.getUsername() %>')">X</a>
            </div>
        </li>
        <form id="acceptForm_<%= item.getUsername() %>" action="AcceptFRServlet" method="post">
            <input type="hidden" name="userId" value="<%= item.getUsername() %>">
        </form>

        <form id="rejectForm_<%= item.getUsername() %>" action="RejectFRServlet" method="post">
            <input type="hidden" name="userId" value="<%= item.getUsername() %>">
        </form>
        <% }
        } else { %>
        <li class="list-group-item d-flex justify-content-between align-items-center">
            No pending friend requests currently
        </li>
        <% } %>

    </ul>
</div>

<!-- Include Bootstrap JS and jQuery if needed -->
<script>
    function submitFormAndRefresh(action, userId) {
        var formId = action === 'AcceptFRServlet' ? 'acceptForm_' : 'rejectForm_';
        var form = document.getElementById(formId + userId);
        form.submit();
        // You can adjust the delay (e.g., 1000ms) based on your needs
        setTimeout(function () {
            location.reload();
        }, 1000);
    }
</script>
</body>
</html>
