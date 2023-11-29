<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Dao.FriendDao" %>
<%@ page import="Model.Account" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Include your meta tags, title, and CSS links here -->

    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

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
    FriendDao friend = new FriendDao();
    Account acc = (Account) session.getAttribute("User");
    ArrayList<Account> friends = friend.ReturnFriends(acc);
%>

<jsp:include page="navbar.jsp" />

<div class="container mt-5 text-center">
    <h1> DashChat With your friends </h1>
</div>
<br>
<div class="d-flex aligns-items-center justify-content-center card text-center w-50 mx-auto">
    <ul class="list-group">
        <li class="list-group-item d-flex justify-content-between align-items-center">
            <h4>Friends list</h4>
            <button class="btn btn-outline-success" id="myBtn">Add Friends</button>
        </li>
        <%
            if (!friends.isEmpty()) {
                for (Account item : friends) {
        %>
        <li class="list-group-item d-flex justify-content-between align-items-center" data-username="<%= item.getUsername() %>">
            <label id="PendingRequestUsername"> <%= item.getUsername() %></label>
            <div class="d-flex justify-content-end">
                <a href="#" class="badge text-danger rounded-pill ms-1 text-decoration-none" onclick="submitFormAndRefresh('RemoveFriend', '<%= item.getUsername() %>')">X</a>
            </div>
        </li>

        <form id="rejectForm_<%= item.getUsername() %>" action="RemoveFriend" method="post">
            <input type="hidden" name="userId" value="<%= item.getUsername() %>">
        </form>
        <%
            }
        } else {
        %>
        <li class="list-group-item d-flex justify-content-between align-items-center">
            Add some friends in the friends tab
        </li>
        <%
            }
        %>
    </ul>
</div>

<!-- Your modal code here -->
<%
    Boolean openModal = (Boolean) session.getAttribute("openModal");
    if (openModal != null && openModal) {
        session.removeAttribute("openModal");
    }
%>
<div id="myModal" class="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Add Friend</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="AddFriendServlet" method="post">
                    <input type="text" name="username" class="form-control" placeholder="Search by username...">
                    <p id="errorMessage" style="color: #000000;">${errorMessage2}</p>
                    <br>
                    <button type="submit" class="btn btn-secondary">Add Friend</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    // Function to remove a friend
    function submitFormAndRefresh(action, userId) {
        var formId = action === 'AcceptFRServlet' ? 'acceptForm_' : 'rejectForm_';
        var form = document.getElementById(formId + userId);
        form.submit();
        // You can adjust the delay (e.g., 1000ms) based on your needs
        setTimeout(function () {
            location.reload();
        }, 1000);
    }
    // Get the elements by their ID
    var modal = document.getElementById("myModal");
    // Get the button that opens the modal
    var btn = document.getElementById("myBtn");
    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("btn-close")[0];

    // When the page loads, check if openModal is true and open the modal
    window.onload = function() {
        var openModalFromServer = <%=openModal%>;
        if (openModalFromServer) {
            modal.style.display = "block";
        }
    };

    // When the user clicks on the button, open the modal
    btn.onclick = function() {
        modal.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
        <% session.removeAttribute("errorMessage2");%>
    }
    window.onunload = function() {
        <% session.removeAttribute("openModal");%>
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            <% session.removeAttribute("errorMessage2");%>
            modal.style.display = "none";
        }
    }
    document.getElementById('friendSearch').addEventListener('input', function () {
        var searchValue = this.value.toLowerCase();
        var friendItems = document.querySelectorAll('.list-group-item[data-username]');

        friendItems.forEach(function (item) {
            var username = item.getAttribute('data-username').toLowerCase();
            var label = item.querySelector('.friend-username');

            // Check if the username contains the search value
            if (username.includes(searchValue)) {
                item.style.display = 'flex'; // Show the item
            } else {
                item.style.display = 'none'; // Hide the item
            }
        });
    });

</script>

<!-- Include Bootstrap JS and jQuery if needed -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
