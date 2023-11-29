<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Dao.FriendDao" %>
<%@ page import="Model.Account" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap Example</title>

    <!-- Link to Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-rbHb5e9BR29VU7WqgDD1tuQaBVuztsC2p4ctL1upFQCTBBfQ/rW1yD02eEUP1ck" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <!-- Your custom CSS if needed -->
    <style>
        .logo {
            max-height: 50px; /* Adjust the height as needed */
            margin-right: 10px; /* Optional: Adjust the margin */
        }
    </style>
    <script>
            // Ensure the form is hidden initially
            document.addEventListener("DOMContentLoaded", function() {
                document.getElementById('editDetailsForm').style.display = 'none';
            });


        function toggleForm() {
            var list = document.getElementById('userDetailsList');
            var form = document.getElementById('editDetailsForm');

            list.style.display = (list.style.display === 'none' || list.style.display === '') ? 'none' : 'block';
            form.style.display = (form.style.display === 'none' || form.style.display === '') ? 'block' : 'none';
            document.body.classList.add('center');

        }
            function cancelButton() {
                // Redirect to account.jsp
                window.location.href = 'Account.jsp';
            }
    </script>

</head>
<body>

<jsp:include page="navbar.jsp" />

<%  Account acc = (Account) session.getAttribute("User");  %>
    <div class="container mt-5 text-center">
        <h1> Account details</h1>
    </div>
<ol id="userDetailsList" class="list-group" style="align-items: center">
    <li class="list-group-item d-flex justify-content-between align-items-start col-6">
        <div class="ms-2 me-auto">
            <div class="fw-bold">Username</div>
            <%=acc.getUsername()%>
        </div>
    </li>
    <li class="list-group-item d-flex justify-content-between align-items-start col-6">
        <div class="ms-2 me-auto">
            <div class="fw-bold">Email</div>
            <%=acc.getEmail()%>
        </div>
    </li>
    <li class="list-group-item d-flex justify-content-between align-items-start col-6">
        <div class="ms-2 me-auto">
            <div class="fw-bold">First Name</div>
            <%=acc.getFirstName()%>
        </div>
    </li>
    <li class="list-group-item d-flex justify-content-between align-items-start col-6">
        <div class="ms-2 me-auto">
            <div class="fw-bold">Last Name</div>
            <%=acc.getLastName()%>
        </div>
    </li>
    <div class="container mt-4 d-flex justify-content-center">
        <button  type="button" class="btn btn-secondary" style="margin: 5px;" onclick="toggleForm()">Edit Details</button>
        <button  class="btn btn-secondary" style="margin: 5px;">Delete account</button>
    </div>
    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>
</ol>


<div style="display: flex; justify-content: center; align-items: center;">
    <form id="editDetailsForm" action="updateDetailsServlet" method="post" style="text-align: center;">
        <!-- Add form fields for editing details here -->
        <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" class="form-control" id="username" name="username" value="<%=acc.getUsername()%>" required>
        </div>

        <div class="mb-3">
            <label for="firstName" class="form-label">First Name</label>
            <input type="text" class="form-control" id="firstName" name="firstName" value="<%=acc.getFirstName()%>" required>
        </div>

        <div class="mb-3">
            <label for="lastName" class="form-label">Last Name</label>
            <input type="text" class="form-control" id="lastName" name="lastName" value="<%=acc.getLastName()%>" required>
        </div>


        <button type="submit" class="btn btn-secondary" style="margin: 5px;">Save Changes</button>
        <button type="button" class="btn btn-secondary" style="margin: 5px;" onclick="cancelButton()">Cancel</button>
    </form>
</div>


<!-- Include Bootstrap JS and jQuery if needed -->
</body>
</html>
