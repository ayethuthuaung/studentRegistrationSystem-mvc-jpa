<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="util.common"%>
<%@page import="student.com.service.StudentService" %>
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
<link rel="stylesheet" href="<%=common.url%>assets/vendors/css/vendor.bundle.base.css">
<link rel="stylesheet" href="<%=common.url%>assets/css/style.css">
<link rel="shortcut icon" href="<%=common.url%>assets/images/favicon.ico" />

</head>
<body>

	<div class="container-scroller">
		<jsp:include page="navbar.jsp"></jsp:include>
		<div class="container-fluid page-body-wrapper">
			<jsp:include page="sidebar.jsp"></jsp:include>

			<div class="main-panel">
				<div class="content-wrapper">

					<div class="row">

						<div class="col-12">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">Update Student Register Form</h4>
									<form:form  class="form-sample" method="post" action="../../studentRegistrationSystem-mvc-jpa/updateStudentProcess" enctype="multipart/form-data"
										modelAttribute="studentDto">
										<div style="color: red">${error}</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">Code</label>
													<div class="col-sm-9">
														<form:input type="text" class="form-control"
															value="${studentDto.studentId }" path="studentId" disabled="disabled" readonly="true" />
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">Name</label>
													<div class="col-sm-9">
														<form:input type="text" class="form-control" path="name" id="studentNameInput" value="${studentDto.name }"/>
														<span id="studentNameError" style="color: red;"></span>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">Phone</label>
													<div class="col-sm-9">
														<form:input type="text" class="form-control" path="phone" id="studentPhoneInput" value="${studentDto.phone }"/>
														<span id="studentPhoneError" style="color: red;"></span>
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">Email</label>
													<div class="col-sm-9">
														<form:input type="email" class="form-control" path="email" id="studentEmailInput" value="${studentDto.email }"/>
														<span id="studentEmailError" style="color: red;"></span>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">Date of
														Birth</label>
													<div class="col-sm-9">
														<form:input type="date" class="form-control" path="dob" id="studentDOBInput" value="${studentDto.dob }"/>
														<span id="studentDOBError" style="color: red;"></span>
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">Gender</label>
													<div class="col-sm-4">
														<div class="form-check">
															<label class="form-check-label"> <form:radiobutton
																	class="form-check-input" path="gender"
																	id="genderRadioMale" value="Male" checked="checked" />
																Male
															</label>
														</div>
													</div>
													<div class="col-sm-5">
														<div class="form-check">
															<label class="form-check-label"> <form:radiobutton
																	class="form-check-input" path="gender"
																	id="genderRadioFemale" value="Female" /> Female
															</label>
														</div>
													</div>
												</div>
											</div>
										</div>
										
										<div class="row">
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">Education</label>
													<div class="col-sm-9">
														<form:input type="text" class="form-control" path="education" id="studentEducationInput" value="${studentDto.education }"/>
														<span id="studentEducationError" style="color: red;"></span>
													</div>
												</div>
											</div>
											
											<div class="col-md-6">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">Photo</label>
													<div class="col-sm-9">
														<form:input type="file" class="form-control" path="photoImageInput" id="studentPhotoImageInput" value="${studentDto.photoImageInput }" />
														<img style="width: 150px; height: 150px;" class="image" src="data:image/jpeg;base64, ${studentDto.photoPath}" alt="Student Photo">
														<span id="studentPhotoError" style="color: red;"></span>
													</div>
												</div>
											</div>
										</div>
										
										 <div class="col-md-12">
                        <div class="form-group row">
                          <div class="col-sm-2">
                          <label class="col-form-label">Course</label>
                          
                        </div>
                        <div class="col-sm-10 row">
                        
                        <c:forEach items="${studentDto.courses}" var="course">
    <div class="col-sm-3">
        <div class="form-check">
            <form:checkbox path="courseId" value="${course.courseId}" id="course_${course.courseId}" checked="true"/>
            <label  for="course_${course.courseId}">
                ${course.name}
            </label>
        </div>
    </div>
</c:forEach>
<c:forEach items="${courseList}" var="course">
    <div class="col-sm-3">
        <div class="form-check">
            <form:checkbox path="courseId" value="${course.courseId}" id="course_${course.courseId}"/>
            <label  for="course_${course.courseId}">
                ${course.name}
            </label>
        </div>
    </div>
</c:forEach>

                         
                        </div>
                        </div>
                      </div>

										
										<div class="mt-3">
											<button 
												class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn"
												id="studentSubmitButton">Update</button>
											<button type="reset"
												class="btn btn-block btn-secondary btn-lg font-weight-medium auth-form-btn"
												id="signInButton">Reset</button>
										</div>
										<form:input path="id" type="hidden" value="${studentDto.id }"/>
									</form:form>
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
	<script src="<%=common.url%>assets/vendors/js/vendor.bundle.base.js"></script>
	<!-- endinject -->
	<!-- Plugin js for this page -->
	<!-- End plugin js for this page -->
	<!-- inject:js -->
	<script src="<%=common.url%>assets/js/off-canvas.js"></script>
	<script src="<%=common.url%>assets/js/hoverable-collapse.js"></script>
	<script src="<%=common.url%>assets/js/misc.js"></script>
	<!-- endinject -->
	<script src="https://cdn.jsdelivr.net/npm/@emailjs/browser@3/dist/email.min.js"></script>
	<!-- Custom js for this page -->
	<script src="<%=common.url%>assets/js/file-upload.js"></script>
	<!-- End custom js for this page -->
	<script src="<%=common.url%>assets/js/validation.js"></script>  
	
</body>
</html>
