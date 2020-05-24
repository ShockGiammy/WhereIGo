<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ include file = "NavigationBar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Confirm and buy ticket</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>

<%@ page import="logic.beans.UserTravelBean" language="java" %>

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

<%
	UserTravelBean travBean = new UserTravelBean();
	travBean = (UserTravelBean)request.getAttribute("tick");
%>

<body>
	<form action="BookTravelServlet" method="post">
	<div class="row">
	<div class="col-4" style="margin-right:20px;">
	<p class="h4">Ticket confirm</p>
		<div class="form-group row">
			<label for="id" class="col-sm-4 col-form-label" style="white-space: nowrap;">Ticket id :</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" value="<%=travBean.getId()%>" name="id" id= "id" readonly class="col-sm-2 col-form-label"><br>
			</div>
		</div>
		<div class="form-group row">
			<label for="depCity" class="col-sm-4 col-form-label" style="white-space: nowrap;">Departure city :</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" value="<%=travBean.getCityOfDep()%>" name="depCity" id="depCity" readonly class="col-sm-2 col-form-label"><br>
			</div>
		</div>
		<div class="form-group row">
			<label for="depDate" class="col-sm-4 col-form-label" style="white-space: nowrap;">Departure date :</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" value="<%=travBean.getFirstDay()%>" name="depDate" id="depDate" readonly class="col-sm-2 col-form-label"><br>
			</div>
		</div>
		<div class="form-group row">
			<label for="arrCity" class="col-sm-4 col-form-label" style="white-space: nowrap;">Arrive city :</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" value="<%=travBean.getCityOfArr()%>" name="arrCity" id="arrCity" readonly class="col-sm-2 col-form-label"><br>
			</div>
		</div>
		<div class="form-group row">
			<label for="retDate" class="col-sm-4 col-form-label" style="white-space: nowrap;">Return date :</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" value="<%=travBean.getLastDay()%>" name="retDate" id="retDate" readonly class="col-sm-2 col-form-label"><br>
			</div>
		</div>
		<div class="form-group row">
			<label for="cost" class="col-sm-4 col-form-label" style="white-space: nowrap;">Cost :</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" value="<%=travBean.getCost()%>" name="cost" id="cost" readonly class="col-sm-2 col-form-label"><br>
			</div>
		</div>
		</div>
		
		<div class="col-4">
			<p class="h4">Create a group</p>
		<div class="form-group row">
			<label for="groupname" class="col-sm-4 col-form-label" style="white-space: nowrap;">Group name :</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="groupname" id= "groupname" class="col-sm-2 col-form-label"><br>
			</div>
		</div>
		<div class="form-group row">
			<label for="dest" class="col-sm-4 col-form-label" style="white-space: nowrap;">Destination :</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" value="<%=travBean.getCityOfArr()%>" name="dest" id="dest" readonly class="col-sm-2 col-form-label"><br>
			</div>
		</div>
		<div class="form-group row">
			<label for="owner" class="col-sm-4 col-form-label" style="white-space: nowrap;">Group owner :</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" value="<%=session.getAttribute("usrname")%>" name="owner" id="owner" readonly class="col-sm-2 col-form-label"><br>
			</div>
		</div>
		<div>
			<input type="submit" name="savetick" value="Confirm" class= "btn btn-success btn-md">
			<input type="submit" name="savebookgroup" value="Create a group" class= "btn btn-success btn-md">
		</div>
		</div>
		
	</div>
	</form>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>