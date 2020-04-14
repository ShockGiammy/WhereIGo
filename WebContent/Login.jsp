<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

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
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>	
</body>
</html>