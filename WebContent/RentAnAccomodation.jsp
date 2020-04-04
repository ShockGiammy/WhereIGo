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
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.*"%>
<%@page import="java.util.Base64"%>
<br>
	<div>
		<table style="display: inline-block;" id="AccomodationsTable">
		<tr><td>Accomodation lists</td></tr>
		<tr>
			<td>City</td>
			<td>houseImage</td>
			<td>Beds</td>
			<td>Contact</td>
		</tr>
		<%
		ControllerFacade facade = new ControllerFacade();
		List<RentAccomodationBean> listOfBean = facade.displayAnnouncement();
		
		// print the information about every category of the list
		for(RentAccomodationBean bean : listOfBean) {
		%>
		<tr>
			<td><%=bean.getCity()%></td>
			<td><img alt="house" src="data:image/jpg;base64, <%out.println(new String(Base64.getEncoder().encodeToString(bean.getHouseImage())));%>" width="240" height="300"/></td>		
			<td><%=bean.getBeds()%></td>
		</tr>
		<%
			}
		%>
		</table>
	</div>
</body>
</html>