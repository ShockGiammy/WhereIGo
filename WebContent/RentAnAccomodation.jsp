<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="NavigationBar.html" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Rent Accomodation</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>

<%@ page import="logic.beans.RentAccomodationBean" %>
<%@ page import="java.util.List" language="java" %>
<%@ page import="logic.controllers.ControllerFacade" %>
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
		ControllerFacade facade = new ControllerFacade();
		List<RentAccomodationBean> list = facade.displayAnnouncement();
		//(List<RentAccomodationBean>)request.getAttribute("listOfBean");

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