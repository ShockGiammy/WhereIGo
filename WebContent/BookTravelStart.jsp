<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file = "NavigationBar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book your travel</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<%@ page import="java.util.ArrayList" language="java" %>
<%@ page import="java.util.List" language="java" %>
<%@ page import="logic.beans.GroupBean" language="java" %>

<%
	if(request.getAttribute("fineMsg") !=  null){
%>
<div class="alert alert-info alert-dismissible fade show" role="alert">
  		<p><%=request.getAttribute("fineMsg") %></p>
  	 	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
    	<span aria-hidden="true">&times;</span>
  		</button>
	</div>
<%
	}
%>
<%
		List<String> cities = new ArrayList<>();
		cities = (ArrayList<String>)request.getAttribute("cities");
		List<GroupBean> groupBeanL = new ArrayList<>();
		groupBeanL = (ArrayList<GroupBean>)request.getAttribute("grouplist");
%>

<body>
	<form action="BookTravelServlet" method="post">
	<div class="container-fluid" style="text-align:center;">
		<div class="row">
			<div class="col-xs-6" style="margin-right:20px;">
				<h1>Location suggested for you</h1>
				<ul class="list-group">
					<%
						for(int i = 0; i < cities.size(); i++) {
					%>
					<li class="list-group-item"><%=cities.get(i)%></li>
					<%
						}
					%>
				</ul>
			</div>
			<div class="col-xs-6" style="margin-right:20px;">
				<h1>Travel groups suggested for you</h1>
				<table class="table">
					<thead>
						<tr>
							<th scope="col">Group name</th>
							<th scope="col">Group admin</th>
							<th scope="col">Group destination</th>
							<th scope="col">Join group</th>
						</tr>
					</thead>
						<%
							for(int i = 0; i < groupBeanL.size(); i++){
								GroupBean gbean = groupBeanL.get(i);
						%>
					<tr>
						<td><%=gbean.getGroupTitle()%></td>
						<td><%=gbean.getGroupOwner()%></td>
						<td><%=gbean.getGroupDestination()%></td>
						<td> <input type="submit" name="join" class= "btn btn-success btn-l"></td>
						<%
							}
						%>
					</tr>
				</table>
			</div>
			<div>
				<h1>Book your travel</h1>
				Departure city <input type="text" name="depCity">
				<br>
				Arrive city <input type="text" name="arrCity">
				<br>
				Departure date <input type="date" name="depDate">
				<br>
				Return date <input type="date" name="retDate">
				<br>
				<input type="submit" name="checkSol" value="Find travels" class= "btn btn-info btn-l">
			</div>
		</div>
	</div>
	</form>
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>		
</body>
</html>