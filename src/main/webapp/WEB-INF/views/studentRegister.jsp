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
					         <div class="row">
              <div class="col-md-6 m-auto grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <h4 class="card-title">Student Registration</h4>
                    
                    <form class="forms-sample">
                      <div class="form-group row">
                        <label for="exampleInputUsername2" class="col-sm-3 col-form-label">Student Name</label>
                        <div class="col-sm-9">
                          <input type="text" class="form-control" id="exampleInputUsername2" placeholder="Username">
                        </div>
                      </div>
                      <div class="form-group row">
                        <label for="exampleInputEmail2" class="col-sm-3 col-form-label">Email</label>
                        <div class="col-sm-9">
                          <input type="email" class="form-control" id="exampleInputEmail2" placeholder="Email">
                        </div>
                      </div>
                      <button type="submit" class="btn btn-primary me-2">Submit</button>
                      <button class="btn btn-light">Cancel</button>
                    </form>
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
	<script>
	document.addEventListener("DOMContentLoaded", function() {
	console.log("hi")
	const passwordInput = document.getElementById("passwordInput")
	passwordInput.value = generateRandomPassword(6);
	let passwordToEmail = passwordInput.value;
     function generateRandomPassword(length) {
     const charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_=+";
     let password = "";
     for (let i = 0; i < length; i++) {
         const randomIndex = Math.floor(Math.random() * charset.length);
         password += charset[randomIndex];
     }
     return password;
     }
     
     function sendMail() {
         console.log("Hi");
         (function () {
             emailjs.init("WsE2hFeJ5wtOCXh6w"); // account public key
         })();
         let password = passwordToEmail;
         var params = {
             sendername: `ACE Inspiration`,
             to: document.querySelector("#to").value,
             subject: `Welcome to ACE Inspiration`,
             replyto: `example@gmail.com`,
             message: `ACE Inspiration has been added to your account.
                         Your Password is : ${password}`,

         };

         console.log(params);

         var serviceID = "service_hp6qahr" // email service id
         var templateID = "template_ey1j7r8" // email template id
         emailjs.send(serviceID, templateID, params)
         .then( res => {
             alert("Email Sent Successfully!")
         })
         .catch();

     }
     document.getElementById("signInButton").addEventListener("click", sendMail);
	});
 </script>
</body>
</html>
