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
                    <h4 class="card-title">User Table</h4>
                    <table class="table table-striped" id="userTable">
                      <thead>
                        <tr>
                          <th>Name</th>
                    <th>Email</th>
                    <th>Gender</th>
                    <th>Phone</th>
                    <th>Date of Birth</th>
                    <th>Address</th>
                    <th>Actions</th>
                        </tr>
                      </thead>
                      <tbody>
                      <c:set var="displayedIndex" value="1" />
            <c:forEach items="${userList}" var="user">
         
                        <tr>
                          <td>${user.name}</td>
                          <td>${user.email}</td>
                          <td>${user.gender}</td>
                       <td>${user.phone}</td>
                          <td>${user.dob}</td>
                          <td>${user.address}</td>
                <td>
                <div>
<c:if test="${!user.role eq 'master'}">
    <i class="fa-solid fa-pen-to-square text-primary" onclick="location.href = 'updateUser/${user.id }';"></i>
</c:if>
   
	               		<c:if test="${loggedInUser.role eq 'master'}">
    <i class="fa-solid fa-trash text-danger" style="margin-left: 20px;" onclick="confirmDelete(${user.id})"></i>
</c:if>

           </div>
                </td>
                          <c:set var="displayedIndex" value="${displayedIndex + 1}" />
                        </tr>
                        
                        </c:forEach>
                      </tbody>
                    </table>
                    <form id="deleteForm" action="deleteuser/{id}" method="get">
        <input type="hidden" id="userId" name="id" value="">
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
   <!-- Data Table -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.datatables.net/2.0.1/js/dataTables.js"></script>
    <script src="https://cdn.datatables.net/2.0.1/js/dataTables.bootstrap5.js"></script>

<script>
function confirmDelete(userId) {
    var confirmDelete = confirm("Are you sure you want to delete this user?");
    if (confirmDelete) {
        document.getElementById("deleteForm").action = "deleteUser/" + userId;
        document.getElementById("deleteForm").submit();
    }
}

    </script>
    <script>
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