<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Admin Page</title>
    <!-- Link to Material Design Icons CSS -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <!-- Link to Font Awesome CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="assets/vendors/css/vendor.bundle.base.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="shortcut icon" href="assets/images/favicon.ico" />
</head>
<body>


<!-- partial:partials/_navbar.html -->
<nav class="navbar default-layout-navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
<div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
    <a class="navbar-brand brand-logo" href="index.html"><h3 class="logo-title">ACE Inspiration</h3></a>
    <a class="navbar-brand brand-logo-mini" href="index.html"></a>
</div>
<div class="navbar-menu-wrapper d-flex align-items-stretch">
    <button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
        <!-- Material Design Icon for menu -->
        <span class="material-icons">menu</span>
    </button>

    <ul class="navbar-nav navbar-nav-right">
        <li class="nav-item nav-profile dropdown">
            <a class="nav-link dropdown-toggle" id="profileDropdown" href="#" data-bs-toggle="dropdown" aria-expanded="false">
                <div class="nav-profile-img">
                    <img src="assets/images/faces/face1.jpg" alt="image">
                    <span class="availability-status online"></span>
                </div>
                <div class="nav-profile-text">
                    <p class="mb-1 text-black">Admin</p>
                </div>
            </a>
            <div class="dropdown-menu navbar-dropdown" aria-labelledby="profileDropdown">
                <a class="dropdown-item" href="#">
                    <!-- Material Design Icon for activity log -->
                    <i class="material-icons text-success">cached</i> Activity Log </a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">
                    <!-- Material Design Icon for logout -->
                    <i class="material-icons text-primary">logout</i> Signout </a>
            </div>
        </li>
        <li class="nav-item d-none d-lg-block full-screen-link">
            <a class="nav-link">
                <!-- Font Awesome icon for expand -->
                <i class="fas fa-expand"></i>
            </a>
        </li>
        <li class="nav-item nav-logout d-none d-lg-block">
            <a class="nav-link" href="#">
                <!-- Material Design Icon for power -->
                <i class="material-icons">power</i>
            </a>
        </li>
        <li class="nav-item nav-settings d-none d-lg-block">
            <a class="nav-link" href="#Home">
                <!-- Material Design Icon for home -->
                <i class="material-icons menu-icon">home</i>
            </a>
        </li>
    </ul>
    <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
        <!-- Material Design Icon for menu -->
        <span class="material-icons">menu</span>
    </button>
</div>
</nav>
</body>
</html>
