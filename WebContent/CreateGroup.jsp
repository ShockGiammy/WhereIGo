<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create your group</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>

<%
	if(request.getAttribute("bookmessage") !=  null){
%>
<div class="alert alert-info alert-dismissible fade show" role="alert">
  		<p><%=request.getAttribute("bookmessage") %></p>
  	 	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
    	<span aria-hidden="true">&times;</span>
  		</button>
	</div>
<%
	}
%>

<body>
	<form method="post" action="BookTravelServlet">
	<div class="row">
	<div class="col-4" style="margin-right:20px;">
	<p class="h4">Ticket confirm</p>
		<div class="form-group row">
			<label for="owner" class="col-sm-4 col-form-label" style="white-space: nowrap;">Group owner :</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" value="<%=session.getAttribute("usrname")%>" name="owner" id= "owner" readonly class="col-sm-2 col-form-label"><br>
			</div>
		</div>
		<div class="form-group row">
			<label for="dest" class="col-sm-4 col-form-label" style="white-space: nowrap;">Group destination :</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="dest" id="dest" class="col-sm-2 col-form-label"><br>
			</div>
		</div>
		<div class="form-group row">
			<label for="groupname" class="col-sm-4 col-form-label" style="white-space: nowrap;">Group name :</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="groupname" id="groupname" class="col-sm-2 col-form-label"><br>
			</div>
		</div>
		<div>
			<input type="submit" name="savegroup" value="Create group" class= "btn btn-success btn-md">
		</div>
	</div>
	</div>
	</form>
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>