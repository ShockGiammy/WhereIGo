<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
</head>

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

<body>
	<form action="LoginServlet" method="post">
	<div class="container">
	<h1>Registration form</h1>	
	<div class="row">
	<div class="col-4">
	<div class="form-horizontal">	
		<div class="form-group">
			<label for="name" class="col-md-6 control-label">Name :</label>
			<div class="col-sm-10">
				<input type="text" name="name" id="name" placeholder="name">
			</div>
		</div><br>
		<div class="form-group">
			<label for="surname" class="col-md-6 control-label">Surname :</label>
			<div class="col-sm-10">
				<input type="text" name="surname" id="surname" placeholder="surname">
			</div>
		</div><br>
		<div class="form-group">
			<label for="dateofb" class="col-md-6 control-label">Date of birth :</label>
			<div class="col-sm-10">
				<input type="text" name="dateofb" id="dateofb" placeholder="date of birth">
			</div>
		</div><br>
		<div class="form-group">
			<label for="gender" class="col-md-6 control-label">Gender :</label>
			<div class="col-sm-10">
				<input type="text" name="gender" id="gender">
			</div>
		</div>
	</div>
	</div>
		
	<div class="col-4">	
	<div class="form-horizontal">
		<div class="form-group">
			<label for="type" class="col-md-6 control-label">User type :</label>
			<div class="col-sm-10">
				<input type="text" name="type" id="type">
			</div>
		</div><br>
		<div class="form-group">
			<label for="username" class="col-md-6 control-label">Username :</label>
			<div class="col-sm-10">
				<input type="text" name="username" id="username" placeholder="username">
			</div>
		</div><br>
		<div class="form-group">
			<label for="password" class="col-md-6 control-label">Password :</label>
			<div class="col-sm-10">
				<input type="password" name="password" id="password" placeholder="password">
			</div>
		</div><br>
		<div class="form-group">
			<div class="col-sm-10">
				Photo:
			</div>
		</div><br>
		<input type="submit" value="register now" name="regnow">
	</div>
	</div>
	</div>
	</div>
	</form>
	
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>