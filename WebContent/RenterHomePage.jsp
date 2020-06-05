<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="RenterNavigationBar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<style>
h1{
    margin-top:50px;
    margin-left: 25px;
    margin-right: 10px;
}
</style>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
<%@ page import="logic.LoggedUser" %>
<h1>Welcome back, <%=LoggedUser.getUserName() %></h1>
</body>
</html>