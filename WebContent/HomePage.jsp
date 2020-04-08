<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file = "NavigationBar.html"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>

<%@ page import="java.util.ArrayList" language="java" %>
<%@ page import="java.util.List" language="java" %>
<%@ page import="logic.beans.UserTravelBean" language="java" %>
<%@ page import="logic.beans.GroupBean"  language="java"%>

<%
	List<UserTravelBean> tBeanList = new ArrayList<>();
	tBeanList = (ArrayList<UserTravelBean>)request.getAttribute("travels");
	List<GroupBean> gBeanList = new ArrayList<>();
	gBeanList = (ArrayList<GroupBean>)request.getAttribute("groups");
%>

<body>
<form action="RentAnAccomodation" method="post">
	<div class="container-fluid" style="text-align:center;">
		<div class = "row">
			<div class="col-xs-6" style="margin-right:100px;">
				<h1>Your travels</h1>
				<table class="table">
					<thead>
						<tr>
							<th scope="col">Ticket ID</th>
							<th scope="col">Departure city</th>
							<th scope="col">Departure date</th>
							<th scope="col">Arrive city</th>
							<th scope="col">Arrive date</th>
							<th scope="col">Cost</th>
						</tr>
					</thead>
						<%
							for(int i = 0; i < tBeanList.size(); i++){
								UserTravelBean bean = tBeanList.get(i);
						%>
					<tr>
						<td><%=bean.getId()%></td>
						<td><%=bean.getCityOfDep()%></td>
						<td><%=bean.getFirstDay()%></td>
						<td><%=bean.getCityOfArr()%></td>
						<td><%=bean.getLastDay()%></td>
						<td><%=bean.getCost()%></td>
						<%
							}
						%>
					</tr>
					</table>
					</div>
	
			<div class="col-xs-6">
				<h1>Your travel groups</h1>
				<table class="table">
					<thead>	
						<tr>
							<th scope="col">Group name</th>
							<th scope="col">Group admin</th>
							<th scope="col">Group destination</th>
						</tr>
					</thead>
					<%
						for(int i = 0; i < gBeanList.size(); i++){
							GroupBean gbean = gBeanList.get(i);
					%>
				<tr>
					<td><%=gbean.getGroupTitle()%></td>
					<td><%=gbean.getGroupOwner()%></td>
					<td><%=gbean.getGroupDestination()%></td>
				</tr>
				<%
					}
				%>
				</table>
				</div>
		</div>
	</div>
	</form>
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>	
</body>
</html>