<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<head>
<meta charset="UTF-8">
<title>Registration</title>
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
				<input type="date" name="dateofb" id="dateofb" placeholder="date of birth">
			</div>
		</div><br>
		<div class="form-group">
			<label for="gender" class="col-md-6 control-label">Gender :</label>
			<div class="col-sm-10">
				<select class="form-control" id="gender" name="gender">
					<option value="Female">Female</option>
					<option value="Male">Male</option>
					<option value="Other">Other</option>
				</select>
			</div>
		</div>
	</div>
	</div>
		
	<div class="col-4">	
	<div class="form-horizontal">
		<div class="form-group">
			<label for="type" class="col-md-6 control-label">User type :</label>
			<div class="col-sm-10">
				<select class="form-control" name="type" id="type">
					<option value="Traveler">Traveler</option>
					<option value="Renter">Renter</option>
				</select>
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
				<label for="profImage" class="col-md-6 control-label">Profile photo :</label>
				<div class="file-upload-wrapper">
					<input type="file" class="form-control-file" id="profImage" name="profImage" accept="image/jpg, image/png">
					<input type="hidden" id="prof64Image" name="prof64Image">
				</div>
			</div>
		</div><br>
		<input type="submit" value="register now" name="regnow" class="btn btn-success btn-lg">
	</div>
	</div>
	</div>
	</div>
	</form>
	
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<script>
$("#profImage").change(function() {
	var file = document.getElementById('profImage').files[0];
	var reader = new FileReader();
	reader.readAsBinaryString(file);
	reader.onload = function() {
		codedImg = btoa(reader.result);
		console.log(codedImg);
		document.getElementById('prof64Image').value = codedImg;
		console.log(document.getElementById('prof64Image').value);
	};
	reader.onerror = function(error) {
		alert("Invalid file");
	};
});
</script>

</body>
</html>