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
</head>
<body>
<%Account acc = (Account) session.getAttribute("User");%>

<jsp:include page="navbar.jsp" />

<div class="container mt-5 text-center">
    <h1> Change Password </h1>
</div>
<div class="container" style="margin-top: 50px;">
    <div class="panel-body">
        <caption><h2>Change password</h2></caption>
        <br>
        <form action="ChangePassword" method="post">
            <div class="form-group">
                <label for="Chnpass">Current Password:</label>
                <input type="text" id="Chnpass" name="Chnpass" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="pass">New Password:</label>
                <input type="password" id="pass" name="pass" class="form-control" required>
                <small class="text-muted">Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.</small>
            </div>
            <div class="form-group">
                <label for="pass1">Confirm New Password:</label>
                <input type="password" id="pass1" name="pass1" class="form-control" required>
            </div>
            <div id="password-match-message" class="text-danger"></div>
            <div class="container mt-4 d-flex justify-content-center">
                <input type="submit" class="btn btn-seconday" value="Save Changes">
            </div>
        </form>
        <c:if test="${not empty errorMessage}">
            <p style="color: red;">${errorMessage}</p>
        </c:if>
    </div>
</div>

<script>
    var RequiredPass = document.getElementById("Chnpass");
    var password = document.getElementById("pass");
    var confirmPassword = document.getElementById("pass1");
    var oldpassword = '<%=acc.getPassword()%>';

    function validatePassword() {
        var passwordValue = password.value;
        var confirmPasswordValue = confirmPassword.value;
        var RequiredPassValue = RequiredPass.value;
        // Password requirements
        var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@#$%^&*!])[A-Za-z\d@#$%^&*!]{8,}$/;

        if (!regex.test(passwordValue)) {
            alert("Password must be at least 8 characters long and contain at least one lowercase letter, one uppercase letter, one digit, and one special character.");
        } else if (passwordValue !== confirmPasswordValue) {
            alert("Passwords do not match");
        } else if (oldpassword !== RequiredPassValue) {
            alert("Current Password doesn't match");
        }
    }
    // Add an event listener to the form's onsubmit event
    document.querySelector('form').addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent the form from submitting
        validatePassword();
    });
    window.addEventListener('beforeunload', function (event) {
        <% session.removeAttribute("errorMessage"); %>
    });
</script>
<!-- Include Bootstrap JS and jQuery if needed -->
</body>
</html>
