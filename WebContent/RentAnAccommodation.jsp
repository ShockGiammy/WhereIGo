<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="NavigationBar.jsp" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Rent Accommodation</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>

<%@ page import="logic.beans.AccommodationBean" %>
<%@ page import="java.util.List" language="java" %>
<%@page import="java.io.*"%>
<%@page import="java.util.Base64"%>
<br>
	<div>
	<table class="table" id="AccommodationsTable">
		<thead>
			<tr>
				<th scope="col">City</th>
				<th scope="col">Address</th>
				<th scope="col">houseImage</th>
				<th scope="col">Description</th>
				<th scope="col">Beds</th>
				<th scope="col">Type of Accommodation</th>
				<th scope="col">Square Metres</th>
				<th scope="col">Services</th>
				<th scope="col">included</th>
				<th scope="col">Renter</th>
				<th scope="col">Contacts</th>
			</tr>
		</thead>
		<%
			List<AccommodationBean> listOfBean = (List<AccommodationBean>)request.getAttribute("list");		
				// print the information about every category of the list
				for(AccommodationBean bean : listOfBean) {
			byte[] list = bean.getServices();
		%>
		<tr>
			<td><%=bean.getCity()%></td>
			<td><%=bean.getAddress()%></td>
			<td><img alt="house" src="data:image/jpg;base64, <%if (bean.getHouseImage()!= null) { 
													out.println(new String(Base64.getEncoder().encodeToString(bean.getHouseImage())));
													}%>" width="150" height="120"/></td>		
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
			<%if (bean.getServices() != null) {%>
			<td>
				<%if(list[0] == 1) out.println("SI"); else out.println("NO");%><br>
				<%if(list[1] == 1) out.println("SI"); else out.println("NO");%><br>
				<%if(list[2] == 1) out.println("SI"); else out.println("NO");%><br>
				<%if(list[3] == 1) out.println("SI"); else out.println("NO");%>			 
			</td>
			<%} else { %>
			<td>
				<%out.println("NO");%><br>
				<%out.println("NO");%><br>
				<%out.println("NO");%><br>
				<%out.println("NO");%>			 
			</td>
			<% } %>
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