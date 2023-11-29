<%--
  Created by IntelliJ IDEA.
  User: issey
  Date: 7/09/2023
  Time: 4:06 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-rbHb5e9BR29VU7WqgDD1tuQaBVuztsC2p4ctL1upFQCTBBfQ/rW1yD02eEUP1ck" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Frontline Bank - Login</title>
    <style>
        .form-group input[type="email"]{
            /* Your styles for form fields here */
            width: 20%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .login-container {
            text-align: center;
            margin-top: 50px;
        }

        .form-group {
            margin-bottom: 20px;
        }


        .logo {
            max-height: 50px; /* Adjust the height as needed */
            margin-right: 10px; /* Optional: Adjust the margin */
        }

        .form-group label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }

        .form-group input[type="text"],
        .form-group input[type="password"] {
            width: 20%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .centered-button-container {
            text-align: center;
        }

        .centered-button {
            padding: 10px 20px;
            background-color: #007bff; /* Blue button color */
            color: white; /* White text color for the button */
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            text-decoration: none; /* Remove underlining */

        }
        .form-group input[type="submit"] {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <img src="logo.png" alt="Logo" class="logo">
        <a class="navbar-brand" href="#">DashChat</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
                <a class="nav-link" href="Q&A.jsp">Q&A</a>
                <ul class="nav navbar-nav navbar-right">
                    <a class="nav-link" href="login.jsp">Login</a>
                </ul>
            </div>
        </div>
    </div>
</nav>

<div class="container">
    <!-- Create Account Form -->
    <div class="login-container">
        <div class="login-box">
            <h3>Create an Account</h3>
            <form action="RegisterServlet" method="post" onsubmit="return validateForm()">

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}" title="Please enter a valid email address">
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="form-group">
                    <label for="confirmPassword">Confirm Password:</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" required>
                </div>
                <div id="password-match-message" class="text-danger"></div>

                <div class="form-group">
                    <label for="Firstname">First Name:</label>
                    <input type="text" id="Firstname" name="Firstname" required>
                </div>
                <div class="form-group">
                    <label for="Lastname">Last Name:</label>
                    <input type="text" id="Lastname" name="Lastname" required>
                </div>
                <div class="form-group">
                    <label for="userName">Username:</label>
                    <input type="text" id="userName" name="userName" required>
                </div>
                    <input class="btn btn-secondary" type="submit" value="Create Account">
            </form>
            <c:if test="${not empty errorMessage}">
                <p style="color: red;">${errorMessage}</p>
            </c:if>
            <br>

            <div class="centered-button-container">
                <a href="login.jsp" class="btn btn-secondary">Already have an account? Log in</a>
            </div>
        </div>
    </div>
</div>
<script>
    var password = document.getElementById("password");
    var confirmPassword = document.getElementById("confirmPassword");
    var message = document.getElementById("password-match-message");

    function validatePassword() {
        var passwordValue = password.value;
        var confirmPasswordValue = confirmPassword.value;

        // Password requirements
        var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@#$%^&*!])[A-Za-z\d@#$%^&*!]{8,}$/;

        if (!regex.test(passwordValue)) {
            message.innerHTML = "Password must be at least 8 characters long and contain at least one lowercase letter, one uppercase letter, one digit, and one special character.";
        } else if (passwordValue !== confirmPasswordValue) {
            message.innerHTML = "Passwords do not match";
        } else {
            message.innerHTML = "";
        }
    }

    password.addEventListener("input", validatePassword);
    confirmPassword.addEventListener("input", validatePassword);
</script>
<script>
    function validateForm() {
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("confirmPassword").value;

        if (password !== confirmPassword) {
            alert("Password and Confirm Password do not match.");
            return false;
        }

        return true;
    }

</script>

</body>
</html>