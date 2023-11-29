<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        .p{
            font-size: 0.75em;
            font-size: 16px;
        }
        .logo {
            max-height: 50px;
            margin-right: 10px;
        }
        .content-container {
            display: flex;
            max-width: 100%;
            margin: 0 auto;
            padding: 20px;
        }

        .text-container {
            padding: 20px;
            width: 50%;
            font-size: 120%;
            font-weight: bold;
        }

        .image-container img {
            width: 90%;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            height: auto;

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
                <a class="nav-link" href="login.jsp">Login</a>
            </div>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h1> Dashchat is available on all browsers </h1>
    <h2> Chat and dash with friends where/when ever</h2>
</div>
<div class="content-container">
    <div class = "text-container">
        <p>DashChat is a private messaging web application which allows for you to add friends and chat to them discretely</p>
    </div>
    <div class="image-container">
        <img src="DashChat.PNG" alt="Logo">
    </div>
</div>


</body>
</html>
