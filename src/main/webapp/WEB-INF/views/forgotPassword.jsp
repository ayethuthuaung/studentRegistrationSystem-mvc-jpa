<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
  <%@ page import="util.common"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Purple Admin</title>
    <!-- plugins:css -->
    <link rel="stylesheet" href="assets/vendors/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="assets/vendors/css/vendor.bundle.base.css">
    <!-- endinject -->
    <!-- Plugin css for this page -->
    <!-- End plugin css for this page -->
    <!-- inject:css -->
    <!-- endinject -->
    <!-- Layout styles -->
    <link rel="stylesheet" href="assets/css/style.css">
    <script src="https://cdn.jsdelivr.net/npm/@emailjs/browser@3/dist/email.min.js"></script>
    <!-- End layout styles -->
    <link rel="shortcut icon" href="assets/images/favicon.ico" />
  </head>
  <body>
    <div class="container-scroller">
      <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="content-wrapper d-flex align-items-center auth">
          <div class="row flex-grow">
            <div class="col-lg-4 mx-auto">
              <div class="auth-form-light text-left p-5">
                <div class="brand-logo">
                  <h3 class="fixed-header">ACE Inspiration</h3>
                </div>
              
                <h6 class="font-weight-light">Forgot Password?</h6>
                <form:form class="pt-3" method="post" modelAttribute="userDto">
                  <div class="form-group">
                    <form:input type="email" class="form-control form-control-lg" id="forgotPasswordEmailInput" placeholder="Email" path="email"/>
                  	<span id="forgotPasswordEmailError" style="color:red;">${emailError }</span>
                  </div>
                  
                  <div class="my-2 d-flex justify-content-between align-items-center">
                    <div class="form-check">   
                    </div>
                    <a href="<%=common.url%>login" class="auth-link text-primary">Remember password?</a>
                  </div>
                  
                  <div class="mt-3">
                    <button type="submit" class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn" id="forgotPasswordEmailSubmitButton" disabled="disabled">Submit</button>
                  </div>
                  
              
                  
                </form:form>
              </div>
            </div>
          </div>
        </div>
        <!-- content-wrapper ends -->
      </div>
      <!-- page-body-wrapper ends -->
    </div>
    <!-- container-scroller -->
    <!-- plugins:js -->
    <script src="../../assets/vendors/js/vendor.bundle.base.js"></script>
    <!-- endinject -->
    <!-- Plugin js for this page -->
    <!-- End plugin js for this page -->
    <!-- inject:js -->
    <script src="../../assets/js/off-canvas.js"></script>
    <script src="../../assets/js/hoverable-collapse.js"></script>
    <script src="../../assets/js/misc.js"></script>
    <script src="assets/js/validation.js"></script>
    <script>
        function sendMail() {
            console.log("Hi");
            (function () {
                emailjs.init("WsE2hFeJ5wtOCXh6w"); // account public key
            })();

            var params = {
                sendername: "Ace Inspiration",
                to: document.querySelector("#forgotPasswordEmailInput").value,
                subject: "Password Reset OTP",
                replyto: document.querySelector("#forgotPasswordEmailInput").value,
                message: `Your OTP for resetting the password is: ${otp}`,
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
        
    </script>
    <!-- endinject -->
  </body>
</html>