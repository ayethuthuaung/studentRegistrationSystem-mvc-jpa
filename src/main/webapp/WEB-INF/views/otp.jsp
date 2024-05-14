<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>OTP </h2> <br>
	<form:form action="/studentRegistrationSystem-mvc-jpa/otpProcess"
		method="post" modelAttribute="bean" class="login-form" name="confirm">
		
		
		<form:input type="text" path="otp" placeholder="OTP"
			required="true" />
		<form:errors path="otp" cssClass="error" />
		
		<button type="submit">Submit</button>
	
	</form:form>
</body>
</html>