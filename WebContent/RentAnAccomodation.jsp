<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="NavigationBar.jsp" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Rent Accomodation</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>

<%@ page import="logic.beans.RentAccomodationBean" %>
<%@ page import="java.util.List" language="java" %>
<%@page import="java.io.*"%>
<%@page import="java.util.Base64"%>
<br>
	<div>
	<table class="table" id="AccomodationsTable">
		<thead>
			<tr>
				<th scope="col">City</th>
				<th scope="col">Address</th>
				<th scope="col">houseImage</th>
				<th scope="col">Description</th>
				<th scope="col">Beds</th>
				<th scope="col">Type of Appartment</th>
				<th scope="col">Square Metres</th>
				<th scope="col">Services</th>
				<th scope="col">included</th>
				<th scope="col">Renter</th>
				<th scope="col">Contacts</th>
			</tr>
		</thead>
		<%
		List<RentAccomodationBean> listOfBean = (List<RentAccomodationBean>)request.getAttribute("list");		
		// print the information about every category of the list
		for(RentAccomodationBean bean : listOfBean) {
			byte[] list = bean.getServices();
		%>
		<tr>
			<td><%=bean.getCity()%></td>
			<td><%=bean.getAddress()%></td>
			<td><img alt="house" src="data:image/jpg;base64, <%out.println(new String(Base64.getEncoder().encodeToString(bean.getHouseImage())));%>" width="150" height="120"/></td>		
			<td><%=bean.getDescription()%></td>
			<td><%=bean.getBeds()%></td>
			<td><%=bean.getType()%></td>
			<td><%=bean.getSquareMetres()%></td>
			<td>
				Garden<br>
				Wifi<br>
				Bathroom<br>
				Kitchen
			</td>
			<td>
				<%if(list[0] == 1) out.println("SI"); else out.println("NO");%><br>
				<%if(list[1] == 1) out.println("SI"); else out.println("NO");%><br>
				<%if(list[2] == 1) out.println("SI"); else out.println("NO");%><br>
				<%if(list[3] == 1) out.println("SI"); else out.println("NO");%>			 
			</td>
			<td><%=bean.getRenter()%></td>
			<td>
       			<a class="btn btn-info btn-l" href="RentTraveller?action=ContactRenter&renter=<%=bean.getRenter()%>" id="contact">Contact<br>Renter</a>
			</td>
		</tr>
		<%
			}
		%>
		</table>
	</div>
</body>
</html>