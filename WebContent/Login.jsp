<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

</head>

<%
	if(request.getAttribute("errorMsg") != null){
%>
	<div class="alert alert-warning alert-dismissible fade show" role="alert">
  		<p><%=request.getAttribute("errorMsg") %></p>
  	 	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
    	<span aria-hidden="true">&times;</span>
  		</button>
	</div>
<%
	}
%>

<style>
	body {
    background-image: url("back.jpg") no-repeat center center;
    background-size: cover;
}
</style>

<body style="background-image : url('webimm/back.jpg');">
	<form action="getLoginInfo" method="post">
		<div class="container">
			<div class="parent-div d-flex align-items-center justify-content-center">
			<div class="child-div">
			<h1>Login</h1>
				<div class="input-icons">
					<input id="username" name="username" type="text" placeholder="username" class="input-field"/>
				</div><br>
				<div class="input-icons">
					<input id="password" name="password" type="password" placeholder="password" class="input-field"/>
				</div><br><br>
				<input type="submit" name="reg" value="register" class= "btn btn-info btn-l">
				<input type= "submit" name="log" value="login" class="btn btn-success btn-l">
			</div>
			</div>
		</div>
	</form>
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>	
</body>
</html>