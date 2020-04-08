<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="registerNow" method="post">
	<table>
		<tr><td>Name : <input type="text" name="name"></td>
		<td>Surname : <input type="text" name="surname"></td></tr>
		<tr><td>Date of birth : <input type="date" name="dateofbirth"></td>
		<td>Type of user : <input type="text" name="typeOfUsr"></td></tr>
		<tr><td>Username : <input type="text" name="username"></td>
		<td>Password : <input type="password" name="paswd"></td></tr>
		<td>Gender : <input type="text" name="gender"></td></tr>
		</table>
		<input type="submit" value="register now">
	</form>
</body>
</html>