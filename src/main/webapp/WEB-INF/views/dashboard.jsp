<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
</head>
<body>
	<div class="container-scroller">
		<jsp:include page="navbar.jsp"></jsp:include>
		<div class="container-fluid page-body-wrapper">
			<jsp:include page="sidebar.jsp"></jsp:include>

			<div class="main-panel">
				<div class="content-wrapper">
            <div class="page-header">
              <h3 class="page-title">
                <span class="page-title-icon bg-primary text-white me-2">
                   <i class="material-icons menu-icon">home</i>
                </span> Dashboard
              </h3>
             
            </div>
 
            <div class="row">
              <div class="col-md-4 stretch-card grid-margin">
                <div class="card bg-gradient-danger card-img-holder text-white">
                  <div class="card-body">
                    
                    <h4 class="font-weight-normal mb-3">Students <i class="mdi mdi-chart-line mdi-24px float-right"></i>
                    </h4>
                    <h2 class="mb-5">$ 15,0000</h2>
                    <h6 class="card-text">Increased by 60%</h6>
                  </div>
                </div>
              </div>
              <div class="col-md-4 stretch-card grid-margin">
                <div class="card bg-gradient-info card-img-holder text-white">
                  <div class="card-body">
                    
                    <h4 class="font-weight-normal mb-3">Courses <i class="mdi mdi-bookmark-outline mdi-24px float-right"></i>
                    </h4>
                    <h2 class="mb-5">45,6334</h2>
                    <h6 class="card-text">Decreased by 10%</h6>
                  </div>
                </div>
              </div>
              <div class="col-md-4 stretch-card grid-margin">
                <div class="card bg-gradient-success card-img-holder text-white">
                  <div class="card-body">
                   
                    <h4 class="font-weight-normal mb-3">Users<i class="mdi mdi-diamond mdi-24px float-right"></i>
                    </h4>
                    <h2 class="mb-5">95,5741</h2>
                    <h6 class="card-text">Increased by 5%</h6>
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
	<!-- Custom js for this page -->
	<script src="assets/js/file-upload.js"></script>
	<!-- End custom js for this page -->
	
</body>
</html>
