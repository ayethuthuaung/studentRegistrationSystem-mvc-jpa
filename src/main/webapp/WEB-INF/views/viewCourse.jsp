<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="util.common"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Admin Page</title>
<!-- Link to Material Design Icons CSS -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<!-- Link to Font Awesome CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
	integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="assets/vendors/css/vendor.bundle.base.css">
<link rel="stylesheet" href="assets/css/style.css">
<link rel="shortcut icon" href="assets/images/favicon.ico" />
 <!-- Data Table -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/2.0.1/css/dataTables.bootstrap5.css">

</head>
<body>
	<div class="container-scroller">
		<jsp:include page="navbar.jsp"></jsp:include>
		<div class="container-fluid page-body-wrapper">
			<jsp:include page="sidebar.jsp"></jsp:include>

			<div class="main-panel">
				<div class="content-wrapper">

					<div class="row">
             
              <div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <h4 class="card-title">Course Table</h4>
                    <table class="table table-striped" id="userTable">
                      <thead>
                        <tr>
                          <th> No </th>
                          <th> Code </th>
                          <th> Name </th>
                          <th> Price </th>
                          <th> Month </th>
                          <th> Period </th>
                          <th> Action </th>
                        </tr>
                      </thead>
                      <tbody>
                      <c:set var="displayedIndex" value="1" />
						<c:forEach var="course" items="${courseList}">
                        <tr>
                          <td>${displayedIndex}</td>
                          <td>${course.courseId}</td>
                          <td>${course.name}</td>
                          <td>${course.price}</td>
                          <td>${course.month}</td>
                          <td>${course.period}</td>
                    
                      <td>
                      <i class="fa-solid fa-pen-to-square text-primary" onclick="location.href = '/studentRegistrationSystem-mvc-jpa/updateCourse/${course.id}';"></i>
	               		
						<i class="fa-solid fa-trash text-danger" style="margin-left: 20px;" onclick="confirmDelete('${course.id}')"></i>
                      </td>
                          <c:set var="displayedIndex" value="${displayedIndex + 1}" />
                        </tr>
                        
                        </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
              
                     
           	 	</div>
          
				</div>
				<jsp:include page="footer.jsp"></jsp:include>
			</div>
			
			<!-- main-panel ends -->
		</div>
		<!-- page-body-wrapper ends -->
	</div>
	<!-- container-scroller -->
	<!-- plugins:js -->
	<script src="assets/vendors/js/vendor.bundle.base.js"></script>
	<!-- endinject -->
	<!-- Plugin js for this page -->
	<!-- End plugin js for this page -->
	<!-- inject:js -->
	<script src="assets/js/off-canvas.js"></script>
	<script src="assets/js/hoverable-collapse.js"></script>
	<script src="assets/js/misc.js"></script>
	<!-- endinject -->
	<script src="https://cdn.jsdelivr.net/npm/@emailjs/browser@3/dist/email.min.js"></script>
	<!-- Custom js for this page -->
	<script src="assets/js/file-upload.js"></script>
	<!-- End custom js for this page -->
	 <!-- Data Table -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.datatables.net/2.0.1/js/dataTables.js"></script>
    <script src="https://cdn.datatables.net/2.0.1/js/dataTables.bootstrap5.js"></script>

    <script>
    function confirmDelete(courseId) {
        if (confirm("Are you sure you want to delete this course?")) {
            location.href = '/studentRegistrationSystem-mvc-jpa/deleteCourse/' + courseId;
        }
    }
      $(document).ready(function() {
            // Initialize DataTable
            $('#userTable').DataTable({
                "lengthMenu": [7, 10, 25, 50],
	        "pageLength": 7
            });
        });

    </script>
</body>
</html>
