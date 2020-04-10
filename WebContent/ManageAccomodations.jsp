<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="RenterNavigationBar.jsp" %>
<!DOCTYPE html>
<html>
<style>
/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 100px; /* Location of the box */
  left: 0;
  top: 0;
  width: 80%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
  position: relative;
  background-color: #fefefe;
  margin: auto;
  padding: 20px;
  border: 1px solid #888;
  width: 80%;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19);
  -webkit-animation-name: animatetop;
  -webkit-animation-duration: 0.4s;
  animation-name: animatetop;
  animation-duration: 0.4s
}

/* Add Animation */
@-webkit-keyframes animatetop {
  from {top:-300px; opacity:0} 
  to {top:0; opacity:1}
}

@keyframes animatetop {
  from {top:-300px; opacity:0}
  to {top:0; opacity:1}
}

/* The Close Button */
.close {
  color: white;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}

.modal-header {
  padding: 2px 16px;
  background-color: #5cb85c;
  color: white;
}

.modal-body {padding: 2px 16px;}

.modal-footer {
  padding: 2px 16px;
  background-color: #5cb85c;
  color: white;
}
</style>
<head>
<meta charset="ISO-8859-1">
<title>Manage your accomodations</title>
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
		<button  class="btn btn-secondary btn-lg btn-block" id="create">Create a new accomodation</button>
	</div>
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
				<th scope="col">Update Info</th>
				<th scope="col">Delete</th>
			</tr>
		</thead>
		<%
		int i = 0;
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
			<td>
       			<button  class="btn btn-info btn-l" id="update" value="<%=bean.getID() %>">Update<br>Info</button>
			</td>
			<td>
				<a class="btn btn-info btn-l" href="RentRenter?action=Delete&id=<%=bean.getID()%>" id="delete">Delete</a>
			</td>
		</tr>
		<%
			}
		%>
		</table>
	</div>
	<!-- The Modal -->
<div id="createOrUpdate" class="modal">

  <!-- Modal content -->
<div class="modal-content">
  <div class="modal-header">
    <h2>Set Accomodation Informations</h2>
    <span class="close">&times;</span>
  </div>
  <div class="modal-body">
	<table class="table" id="AccomodationsTable">
		<thead>
			<tr>
				<th scope="col">City</th>
				<th scope="col">Address</th>
				<th scope="col">houseImage</th>
				<th scope="col">Description</th>
				<th scope="col">Beds</th>
				<th scope="col">Type of Apartment</th>
				<th scope="col">Square Metres</th>
				<th scope="col">Services</th>
				<th scope="col">included</th>
			</tr>
		</thead>
		<%
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
		</tr>
		<%
			}
		%>
		</table>
  </div>
  <div class="modal-footer">
    <h3>Modal Footer</h3>
  </div>
</div>

</div>
<script>
//Get the modal
var modal = document.getElementById("createOrUpdate");

// Get the button that opens the modal
var update = document.getElementById("update");
var create = document.getElementById("create");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
update.onclick = function() {
  modal.style.display = "block";
}

create.onclick = function() {
  modal.style.display = "block";
}
	
var bean = document.getElementById("update").value;

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}
</script>
</body>
</html>