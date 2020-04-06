<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="NavigationBar.html" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>

<body>
<%@ page import="java.util.List" language="java" %>
<%@ page import="logic.model.User" %>
<%@page import="java.util.Base64"%>
	<% List<User> users = (List<User>)request.getAttribute("users");
	for(User user : users) {
		if (user.getPicture()!=null) {
	%>
	<ul>
		<li><img alt="house" src="data:image/jpg;base64, <%out.println(new String(Base64.getEncoder().encodeToString(user.getPicture())));%>" width="45" height="45"/><%=user.getName() %>
	</ul>
	<%
		}
	}
	List<String> groups = (List<String>)request.getAttribute("groups");
	for(String group : groups) {
	%>
	<ul>
		<li><%=group %>
	</ul>
	<%
	}
	%>
</body>
</html>