<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file = "NavigationBar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Available tickets</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>

<%@ page import="java.util.ArrayList" language="java" %>
<%@ page import="java.util.List" language="java" %>
<%@ page import="logic.beans.UserTravelBean" language="java" %>

<% 
	List<UserTravelBean> travBeanList = new ArrayList<>();
	travBeanList = (ArrayList<UserTravelBean>)request.getAttribute("tickets");
%>

<body>			
		<div class="container">
			<div>Tickets available</div>
				<div class="row">
				<div class="col">Ticket ID</div>
				<div class="col">Departure city</div>
				<div class="col">Departure date</div>
				<div class="col">Arrive city</div>
				<div class="col">Return date</div>
				<div class="col">Cost</div>
				<div class="col">Book now</div>
				<div class="w-100"></div>
				<%
					for(int i = 0; i < travBeanList.size(); i++){
						UserTravelBean travBean = travBeanList.get(i);
				%>
				<div class="col"><%=travBean.getId()%></div>
				<div class="col"><%=travBean.getCityOfDep()%></div>
				<div class="col"><%=travBean.getFirstDay()%></div>
				<div class="col"><%=travBean.getCityOfArr()%></div>
				<div class="col"><%=travBean.getLastDay()%></div>
				<div class="col"><%=travBean.getCost()%></div>
				<div class="col"><a href="BookTravelServlet?action=goconf&id=<%out.println(travBean.getId());%>&depCity=<%out.println(travBean.getCityOfDep());%>&arrCity=<%out.println(travBean.getCityOfArr());%>&depDate=<%out.println(travBean.getFirstDay());%>&retDate=<%out.println(travBean.getLastDay());%>&cost=<%out.println(travBean.getCost());%>" class="btn">Confirm</a></div>
				<div class="w-100"></div>
				<%
					}
				%>
			</div>
		</div>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>