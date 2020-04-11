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
	<form action="BookTravelServlet" method="get">
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-6" style="margin-right:30px;">
				<p class="h5">Location suggested for you</p>
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
			<div class="col-xs-6" style="margin-right:30px;">
				<p class="h5">Travel groups suggested for you</p>
				<table class="table table-sm">
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
						<td><a href="BookTravelServlet?action=joinGroup&descr=<%=gbean.getGroupTitle()%>" class= "btn btn-success btn-l">Join group</a></td>
						<%
							}
						%>
					</tr>
				</table>
			</div>
			<div class="col-xs-6">
					<div class="container">
					<p class="h5">Book your travel</p>
						<div class="row">
							<div class="col-4">
								<div class="form-horiontal">
									<div class="form-group">
										<label for="dep" class="col-xs-2 control-label">Departure city</label>
										<input type="text" name="depCity" id="dep">
									</div>
									<div class="form-group">
										<label for="arr" class="col-xs-2 control-label">Arrive city</label>
										<input type="text" name="arrCity" id="arr">
									</div>
								</div>
							</div>
							<div class="col-4">
								<div class="form-horiontal">
									<div class="form-group">
										<label for="depDate" class="col-xs-2 control-label">Departure date</label>
										<input type="date" name="depDate" id="depDate">
									</div>
								</div>
								<div class="form-horiontal">
									<div class="form-group">
										<label for="retDate">Return date</label>
										<input type="date" name="retDate" id="retDate">
									</div>
								<div class="form-horiontal">
									<div class="form-group">
										<input type="submit" name="checkSol" value="Find travels" class= "btn btn-info btn-l">
									</div>
								</div>	
							</div>
						</div>
					</div>
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