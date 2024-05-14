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

	<h2>New Password</h2><br>
	<form:form action="/studentRegistrationSystem-mvc-jpa/newpassprogress"
		method="post" modelAttribute="nbean" class="login-form" name="confirm">
		
		
		<form:input type="password" path="password" placeholder="Password"
			required="true" />
		<form:errors path="password" cssClass="error" />
		<br>
		<button type="submit">Submit</button>
	
	</form:form>
</body>
</html>