<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file = "NavigationBar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Available tickets</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
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
			<p class="h4">Tickets available</p>
				<div class="row">
				<div class="col-md">Ticket ID</div>
				<div class="col-md">Departure city</div>
				<div class="col-md">Departure date</div>
				<div class="col-md">Arrive city</div>
				<div class="col-md">Return date</div>
				<div class="col-md">Cost</div>
				<div class="col-md">Book now</div>
				<div class="w-100"></div>
				<%
					for(int i = 0; i < travBeanList.size(); i++){
						UserTravelBean travBean = travBeanList.get(i);
				%>
				<div class="col-md"><%=travBean.getId()%></div>
				<div class="col-md"><%=travBean.getCityOfDep()%></div>
				<div class="col-md"><%=travBean.getFirstDay()%></div>
				<div class="col-md"><%=travBean.getCityOfArr()%></div>
				<div class="col-md"><%=travBean.getLastDay()%></div>
				<div class="col-md"><%=travBean.getCost()%></div>
				<div class="col-md"><a href="BookTravelServlet?action=goconf&id=<%out.println(travBean.getId());%>&depCity=<%out.println(travBean.getCityOfDep());%>&arrCity=<%out.println(travBean.getCityOfArr());%>&depDate=<%out.println(travBean.getFirstDay());%>&retDate=<%out.println(travBean.getLastDay());%>&cost=<%out.println(travBean.getCost());%>" class="btn btn-success btn-sm" >Confirm</a></div>
				<div class="w-100"></div>
				<%
					}
				%>
			</div>
		</div>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>