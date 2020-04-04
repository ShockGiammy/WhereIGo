<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="NavigationBar.html" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Rent Accomodation</title>
</head>
<body>

<%@ page import="logic.beans.RentAccomodationBean" %>
<%@ page import="java.util.List" language="java" %>
<br>
	<div>
		<table style="display: inline-block;" id="AccomodationsTable">
		<tr><td>Accomodation lists</td></tr>
		<tr>
			<td>City</td>
			<td>houseImage</td>
			<td>Beds</td>
		</tr>
		<%
		List<RentAccomodationBean> list = (List<RentAccomodationBean>)request.getAttribute("listOfBean");

		// print the information about every category of the list
		for(RentAccomodationBean bean : list) {
		%>
		<tr>
			<td><%=bean.getCity()%></td>
			<td><%=bean.getHouseImage()%></td>
			<td><%=bean.getBeds()%></td>
		</tr>
		<%
			}
		%>
	</div>

</body>
</html>