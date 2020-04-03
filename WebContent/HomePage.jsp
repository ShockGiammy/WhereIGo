<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>

<%@ page import="java.util.ArrayList" language="java" %>
<%@ page import="java.util.List" language="java" %>
<%@ page import="logic.beans.UserTravelBean" language="java" %>

<%
	List<UserTravelBean> tBeanList = new ArrayList<>();
	tBeanList = (ArrayList<UserTravelBean>)request.getAttribute("travels");
%>

<body>
	<div class="navbar">
		<a href="home">Home</a>
		<a href="booktrav">Book a travel</a>
		<a href="rent">Rent an accomodation</a>
		<a href="chat">Chat with users</a>
		<a href="image">Profile image</a>
		<a href="exit">Exit</a>
	</div>
	<br>
	<div>
		<table style="display: inline-block;" id="tickTable">
		<tr><td>Your tickets</td></tr>
		<tr>
			<td>Id biglietto</td>
			<td>Città di partenza</td>
			<td>Data di partenza</td>
			<td>Città di arrivo</td>
			<td>Data ritorno</td>
			<td>Costo</td>
		</tr>
		<%
			for(int i = 0; i < tBeanList.size(); i++){
				UserTravelBean bean = tBeanList.get(i);
		%>
		<tr>
			<td><%=bean.getId()%></td>
			<td><%=bean.getCityOfDep()%></td>
			<td><%=bean.getFirstDay()%></td>
			<td><%=bean.getCityOfArr()%></td>
			<td><%=bean.getLastDay().toString()%></td>
			<td><%=bean.getCost()%></td>
		</tr>
		<%
			}
		%>
	</div>
</body>
</html>