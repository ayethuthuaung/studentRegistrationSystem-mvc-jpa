<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="util.common"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Purple Admin</title>
<link rel="stylesheet" href="assets/vendors/css/vendor.bundle.base.css">
<link rel="stylesheet" href="assets/vendors/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/boxicons/2.1.0/css/boxicons.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>
<!-- plugins:css -->

</head>
<body>

	<!-- partial:../../partials/_sidebar.html -->
	<nav class="sidebar sidebar-offcanvas" id="sidebar">
		<ul class="nav">
			<li class="nav-item nav-profile"><a href="#" class="nav-link">
					<div class="nav-profile-image">
						<img src="assets/images/adminProfile.png" alt="profile"> <span
							class="login-status online"></span>
						<!--change to offline or busy as needed-->
					</div>
					<div class="nav-profile-text d-flex flex-column">
						<span class="font-weight-bold mb-2">${loggedInUser.name }</span> <span
							class="text-secondary text-small">${loggedInUser.role }</span>
					</div>
			</a></li>
			<li class="nav-item"><a class="nav-link" href="<%=common.url%>/dashboard">
					<span class="menu-title">Dashboard</span> 
					<i class="fa-brands fa-windows menu-icon"></i>
			</a></li>
			<li class="nav-item"><a class="nav-link"
				data-bs-toggle="collapse" href="#user-management"
				aria-expanded="false" aria-controls="user-management"> <span
					class="menu-title">Admin Management</span>
					<i class="fa-solid fa-caret-down menu-icon"></i>
			</a>
				<div class="collapse" id="user-management">
					<ul class="nav flex-column sub-menu">
						<li class="nav-item">
						 	<a class="nav-link" href="<%=common.url%>addAdmin"><i class="fa-solid fa-user-plus" style="margin-right: 10px;"></i>Admin Register</a></li>
						<li class="nav-item">
							<a class="nav-link" href="<%=common.url%>viewAdmin"><i class="fa-solid fa-users" style="margin-right: 10px;"></i>ViewAdmin</a></li>
					</ul>
				</div></li>
			<li class="nav-item"><a class="nav-link"
				data-bs-toggle="collapse" href="#course-management"
				aria-expanded="false" aria-controls="course-management"> <span
					class="menu-title">Course Management</span>
					<i class="fa-solid fa-caret-down menu-icon"></i>
			</a>
				<div class="collapse" id="course-management">
					<ul class="nav flex-column sub-menu">
						<li class="nav-item">
							<a class="nav-link" href="<%=common.url%>addCourse"><i class="fa-solid fa-folder-plus" style="margin-right: 10px;"></i>Course Register</a></li>
						<li class="nav-item">
							<a class="nav-link" href="<%=common.url%>viewCourse"><i class="fa-solid fa-folder-open" style="margin-right: 10px;"></i>View Course</a></li>
					</ul>
				</div></li>
			<li class="nav-item"><a class="nav-link"
				data-bs-toggle="collapse" href="#student-management"
				aria-expanded="false" aria-controls="student-management"> <span
					class="menu-title">Student Management</span>
					<i class="fa-solid fa-caret-down menu-icon"></i>
			</a>
				<div class="collapse" id="student-management">
					<ul class="nav flex-column sub-menu">					
						<li class="nav-item"><a class="nav-link"
							href="<%=common.url%>addStudent"><i class="fa-solid fa-user-plus" style="margin-right: 10px;"></i> Student Registration</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<%=common.url%>viewStudent"><i class="fa-solid fa-users" style="margin-right: 10px;"></i>View Student</a></li>
					</ul>
				</div></li>
			<li class="nav-item"><a class="nav-link" href="<%=common.url%>logout">
					<span class="menu-title">logout</span> 
					<i class="fa-solid fa-right-from-bracket menu-icon"></i>
			</a></li>

		</ul>
	</nav>
	

</body>
</html>