<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form action="addUser" method="post" modelAttribute="userBean">
		<div style="color: red">${error}</div>
	
		<table>

			<tr>
				<td>Name</td>
				<td><form:input type="text" path="name" /></td>
				<td><form:errors path="name" style="color:red;"></form:errors></td>
			</tr>

			<tr>
				<td>Email</td>
				<td><form:input type="email" path="email"/></td>
				<td><form:errors path="email" style="color:red;"></form:errors></td>
			</tr>

			<tr>
				<td>
                  <form:label path="gender" class="form-label">Gender</form:label>
                    <form:radiobutton path="gender" value="Male" class="form-check-input"/> Male
                    <form:radiobutton path="gender" value="Female" class="form-check-input"/> Female
                  <form:errors path="gender" style="color:red;"></form:errors>
                </td>
            </tr>

			<tr>
				<td>Phone</td>
				<td><form:input type="text" path="phone"/></td>
				<td><form:errors path="phone" style="color:red;"></form:errors></td>
			</tr>

			<tr>
				<td>Date of Birth</td>
				<td><form:input type="date" path="dob"/></td>
				<td><form:errors path="dob" style="color:red;"></form:errors></td>
			</tr>
			
			<tr>
				<td>Address</td>
				<td><form:input type="text" path="address"/></td>
				<td><form:errors path="address" style="color:red;"></form:errors></td>
			</tr>
			
			
			<tr>
				<td></td>
				<td><input type="submit" value="Add"></td>
			</tr>

		</table>
	</form:form>
</body>
</html>