<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <img src="logo.png" alt="Logo" class="logo">
    <a class="navbar-brand" href="#">DashChat</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
      <ul class="navbar-nav">
        <a class="nav-link" href="Home.jsp">Home</a>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Friends
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="Friends.jsp">Friends list</a></li>
            <li><a class="dropdown-item" href="FriendRequest.jsp">Friend Requests</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Account
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="Account.jsp">Account Details</a></li>
            <li><a class="dropdown-item" href="ChangePassword.jsp">Change password</a></li>
          </ul>
        </li>
        <a class="nav-link" href="logout">Logout</a>
      </ul>
    </div>
  </div>
</nav>