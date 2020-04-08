<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirm and buy ticket</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>

<%@ page import="logic.beans.UserTravelBean" language="java" %>
<%
	UserTravelBean travBean = new UserTravelBean();
	travBean = (UserTravelBean)request.getAttribute("tick");
%>

<body>
	<form action="BookTravelServlet" method="post">
	<h1>Ticket confirm</h1>
		<div class="form-group row">
			<label for="id" class="col-sm-2 col-form-label">Ticket id :</label>
			<div class="col-sm-10">
				<input type="text" value="<%=travBean.getId()%>" name="id" id= "id" readonly class="col-sm-2 col-form-label"><br>
			</div>
		</div>
		<div class="form-group row">
			<label for="depCity" class="col-sm-2 col-form-label">Departure city :</label>
			<div class="col-sm-10">
				<input type="text" value="<%=travBean.getCityOfDep()%>" name="depCity" id="depCity" readonly class="col-sm-2 col-form-label"><br>
			</div>
		</div>
		<div class="form-group row">
			<label for="depDate" class="col-sm-2 col-form-label">Departure date :</label>
			<div class="col-sm-10">
				<input type="text" value="<%=travBean.getFirstDay()%>" name="depDate" id="depDate" readonly class="col-sm-2 col-form-label"><br>
			</div>
		</div>
		<div class="form-group row">
			<label for="arrCity" class="col-sm-2 col-form-label">Arrive city :</label>
			<div class="col-sm-10">
				<input type="text" value="<%=travBean.getCityOfArr()%>" name="arrCity" id="arrCity" readonly class="col-sm-2 col-form-label"><br>
			</div>
		</div>
		<div class="form-group row">
			<label for="retDate" class="col-sm-2 col-form-label">Return date :</label>
			<div class="col-sm-10">
				<input type="text" value="<%=travBean.getLastDay()%>" name="retDate" id="retDate" readonly class="col-sm-2 col-form-label"><br>
			</div>
		</div>
		<div class="form-group row">
			<label for="cost" class="col-sm-2 col-form-label">Cost :</label>
			<div class="col-sm-10">
				<input type="text" value="<%=travBean.getCost()%>" name="cost" id="cost" readonly class="col-sm-2 col-form-label"><br>
			</div>
		</div>
		<div>
			<input type="submit" name="savetick" value="Confirm" class= "btn btn-success btn-md">
		</div>
	</form>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>