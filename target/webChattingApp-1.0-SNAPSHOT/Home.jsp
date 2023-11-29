<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="Model.Dao.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Bootstrap Example</title>

  <!-- Link to Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/socket.io/4.0.1/socket.io.js"></script>
  <link rel="stylesheet" type="text/css" href="Home.css">


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
  MessageDAO messagedao = new MessageDAO();
  Account MainUser = (Account) session.getAttribute("User");
  ArrayList<Account> friends = friend.ReturnFriends(MainUser);
  String ConvoID = (String) session.getAttribute("ConversationID");
  ArrayList<Message> allMessages = new ArrayList<>();
  List<Message> ReceiverMessages = null;
  Account Receiver = null;
  if (ConvoID != null ) {
    ReceiverMessages = (List<Message>) session.getAttribute("receiverMessages");
    Receiver = (Account) session.getAttribute("ReceiverAccount");
    allMessages.addAll(ReceiverMessages);
    allMessages.sort(Comparator.comparing(Message::getTimestamp));
  }

%>

<jsp:include page="navbar.jsp" />
<!--Left side FriendList-->
<ul class="list-group" style="width: 20%; border: 1px solid #adb5bd; height: 92.9vh; float: left; overflow-y: auto;">
  <li class="list-group-item d-flex justify-content-between align-items-center h-5">
    <h4>Chat with your friend</h4>
      <% if (!friends.isEmpty()) { %>
      <% for (Account item : friends) { %>
  <li class="list-group-item d-flex justify-content-between align-items-center h-5" onclick="submitForm('<%= item.getUsername() %>')">
    <form id="userForm_<%= item.getUsername() %>" action="WebConnecting" method="post">
      <label style="cursor: pointer;" for="username_<%= item.getUsername() %>" id="usernameLabel_<%= item.getUsername() %>"><%= item.getUsername() %></label>
      <input type="hidden" id="username_<%= item.getUsername() %>" name="username" value="<%= item.getUsername() %>">
    </form>
  <span id="Notification_<%= item.getUsername() %>" class="badge bg-primary rounded-pill">
    <% int messageSize = messagedao.CheckUnreadConvo(MainUser.getAccountId(), item.getAccountId());
      if (messageSize > 0) { %>
      New Chat
    <% } %>
  </span>
  </li>
  <% } %>
  <% } else { %>
  <li class="list-group-item d-flex justify-content-between align-items-center h-5">
    Add some friends in the friends tab
    <span class="badge bg-primary rounded-pill">?</span>
  </li>
  <% } %>
</ul>

<!--this is for the header-->
  <div class="right-box">
    <%if (ConvoID == null ) {%>
    <h2>Pick someone from your friends list to chat with</h2>
    <%} else {%> <h2>Currently chatting with <%= Receiver.getUsername()%> </h2> <%}%>
  </div>

<!--this is for the chat box -->
<div class="chat-box">
  <% for (Message message : allMessages) { %>
  <div class="container right">
    <%= message.getTimestamp() %>: <%= message.getContent() %>
  </div>
  <% } if(Receiver != null ) { messagedao.deleteMessages(Receiver.getAccountId(), ConvoID); }%>
</div>

<!--this is for the inputbox at the bottom-->
<div class = "input-box">
    <div class="input-group">
      <input id="message" name="message" type="text" class="form-control" aria-label="Text input with segmented dropdown button" placeholder="Dash a message here">
      <button onclick="sendMessage()" id="SubmitMessage" type="button" class="btn btn-outline-secondary">submit</button>
    </div>
</div>

<!-- Include Bootstrap JS and jQuery if needed -->
<script>
  function submitForm(username) {
    document.getElementById('userForm_' + username).submit();
    console.log(username);
  }
  let wsocket;
  let userName;
  let Recipient;

  //sends messages
  function sendMessage() {
    let input = document.getElementById("message");
    <%
      String recUsername = (Receiver != null) ? Receiver.getUsername() : "";
    %>
    Recipient = '<%=recUsername%>';
    console.log(Recipient);
    if (input.value.length > 0) {
      let message = {
        userName: userName,
        type: 'chat',
        body: input.value,
        recipient: Recipient// Use textContent to get the label value
      };
      console.log(input.value);
      console.log(Recipient);
      console.log(userName);
      let messageJson = JSON.stringify(message);
      wsocket.send(messageJson);
      input.value = "";
      let chatBox = document.querySelector('.chat-box');
      let messageDiv = document.createElement('div');
      messageDiv.className = "container left";
      messageDiv.textContent = message.body;
      chatBox.appendChild(messageDiv);
      scrollChatToBottom();
    }
  }

  //receives messages
  function onMessage(evt) {
    let message = JSON.parse(evt.data);
    console.log(message);
    console.log(Recipient);
    console.log(message.userName);
    Recipient = '<%=recUsername%>';
    if (message.type === "chat" && Recipient === message.userName) {
      let chatBox = document.querySelector('.chat-box');
      message.belongsTo = "Receiver";
      console.log(message.body);
      // Create a new div for the received message
      let messageDiv = document.createElement('div');
      messageDiv.className = "container right";
      messageDiv.textContent = message.body;
      // Append the new message div to the chat box
      chatBox.appendChild(messageDiv);
      scrollChatToBottom();
    } else if (message.type === "info") {
      // used in case the user is in the session but isnt with the chat dialogue with the specific user
    } else if (message.type === "chat" && Recipient !== message.userName){
      console.log(message.userName);
      message.body == "wrongSession";
      wsocket.send(message);
      let badge = document.getElementById('Notification_' + message.userName);
      badge.textContent = "New Chat";
    }

  }
  document.getElementById('message').addEventListener('keypress', function (event) {
    if (event.key === 'Enter') {
      sendMessage();
    }
  });


  function connect() {
    <%
    String username = (MainUser != null) ? MainUser.getUsername() : "";
    %>
    wsocket = new WebSocket('ws://localhost:8080/webChattingApp/webchat/<%=username%>');
    console.log('Connected');
    wsocket.onmessage = onMessage;
    console.log("idk3")
    userName = '<%=username%>';
  }
  window.addEventListener("load", connect, false);

  function scrollChatToBottom() {
    var chatBox = document.querySelector('.chat-box');
    chatBox.scrollTop = chatBox.scrollHeight;
  }
</script>
</body>
</html>