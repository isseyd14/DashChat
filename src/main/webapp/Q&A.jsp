<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Include your meta tags, title, and CSS links here -->

    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="Q&A.css">

    <!-- Your custom CSS if needed -->
    <style>
        .logo {
            max-height: 50px; /* Adjust the height as needed */
            margin-right: 10px; /* Optional: Adjust the margin */
        }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp" />
<h1 style="text-align: center; padding: 20px">Questions and Answers</h1>

<div class="qa-container">
    <div class="question" onclick="toggleAnswer('q1')">▪ How do I start a conversation?</div>
    <div class="answer" id="q1">To start a conversation, simply type your message in the chat box at the bottom of the screen and hit 'submit' while choosing a friend to chat with in the home screen</div>

    <div class="question" onclick="toggleAnswer('q2')">▪ Is my chat history saved?</div>
    <div class="answer" id="q2">No the chat history is not saved this is to pursue the privacy side and orientation of the web chat application ? </div>

    <div class="question" onclick="toggleAnswer('q3')">▪ If i receive a message while im offline or not in a direct chat with the user will i still be alerted ? </div>
    <div class="answer" id="q3"> you will still receive the message as it will be saved in the server till opened by the user </div>

    <div class="question" onclick="toggleAnswer('q4')">▪ Is the application available on my mobile device ? </div>
    <div class="answer" id="q4">DashChat is still unavailable on mobile devices </div>

    <div class="question" onclick="toggleAnswer('q5')">▪ How do I add friends to my chat? </div>
    <div class="answer" id="q5"> Within the Friends tab you can easily search a users username and add them </div>

    <!-- Add more questions and answers as needed -->
</div>

<!-- Include Bootstrap JS and jQuery if needed -->
<script>

    function toggleAnswer(questionId) {
        var answer = document.getElementById(questionId);
        if (answer.style.display === 'none' || answer.style.display === '') {
            answer.style.display = 'block';
        } else {
            answer.style.display = 'none';
        }
    }
</script>
</body>
</html>
