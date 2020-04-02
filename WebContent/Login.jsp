<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="CSS/SubmitProva.css"></link>
</head>
<body>
	<form action="getLoginInfo" method="post">
		<table>
			<tr>
			<td>Username :<br> 
			<input type="text" name="username"></td>
			</tr>
			<tr>
			<td>Password :<br> 
			<input type="password" name="password"></td>
			</tr>
		</table>
		<br>
		<input type= "submit" value="login" id="login">
		<input type="submit" value="register"id= "register">
	</form>	
</body>
</html>