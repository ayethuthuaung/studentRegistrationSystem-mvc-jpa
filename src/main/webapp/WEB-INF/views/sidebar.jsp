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
<!-- plugins:css -->

</head>
<body>

	<!-- partial:../../partials/_sidebar.html -->
	<nav class="sidebar sidebar-offcanvas" id="sidebar">
		<ul class="nav">
			<li class="nav-item nav-profile"><a href="#" class="nav-link">
					<div class="nav-profile-image">
						<img src="assets/images/faces/face1.jpg" alt="profile"> <span
							class="login-status online"></span>
						<!--change to offline or busy as needed-->
					</div>
					<div class="nav-profile-text d-flex flex-column">
						<span class="font-weight-bold mb-2">David Grey. H</span> <span
							class="text-secondary text-small">Project Manager</span>
					</div> <i class="mdi mdi-bookmark-check text-success nav-profile-badge"></i>
			</a></li>
			<li class="nav-item"><a class="nav-link" href="<%=common.url%>/">
					<span class="menu-title">Dashboard</span> <i
					class="mdi mdi-home menu-icon"></i>
			</a></li>
			<li class="nav-item"><a class="nav-link"
				data-bs-toggle="collapse" href="#user-management"
				aria-expanded="false" aria-controls="user-management"> <span
					class="menu-title">User Management</span> <i class="menu-arrow"></i>
					<i class="bi bi-chevron-down menu-icon"></i>
			</a>
				<div class="collapse" id="user-management">
					<ul class="nav flex-column sub-menu">
						<li class="nav-item">
						 	<a class="nav-link" href="<%=common.url%>addAdmin">Admin Register</a></li>
						<li class="nav-item">
							<a class="nav-link" href="<%=common.url%>addAdmin">ViewAdmin</a></li>
					</ul>
				</div></li>
			<li class="nav-item"><a class="nav-link"
				data-bs-toggle="collapse" href="#course-management"
				aria-expanded="false" aria-controls="course-management"> <span
					class="menu-title">Course Management</span> <i class="menu-arrow"></i>
					<i class="bi bi-chevron-down menu-icon"></i>
			</a>
				<div class="collapse" id="course-management">
					<ul class="nav flex-column sub-menu">
						<li class="nav-item">
							<a class="nav-link" href="<%=common.url%>addCourse">Course Register</a></li>
						<li class="nav-item">
							<a class="nav-link" href="<%=common.url%>viewCourse">View Course</a></li>
					</ul>
				</div></li>
			<li class="nav-item"><a class="nav-link"
				data-bs-toggle="collapse" href="#student-management"
				aria-expanded="false" aria-controls="student-management"> <span
					class="menu-title">Student Management</span> <i class="menu-arrow"></i>
					<i class="bi bi-chevron-down menu-icon"></i>
			</a>
				<div class="collapse" id="student-management">
					<ul class="nav flex-column sub-menu">
						<li class="nav-item"><a class="nav-link"
							href="<%=common.url%>addAdmin">Student Registration</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<%=common.url%>addAdmin">View Student</a></li>
					</ul>
				</div></li>
			<li class="nav-item"><a class="nav-link" href="<%=common.url%>login">
					<span class="menu-title">logout</span> <i
					class="mdi mdi-home menu-icon"></i>
			</a></li>

		</ul>
	</nav>
	

</body>
</html>