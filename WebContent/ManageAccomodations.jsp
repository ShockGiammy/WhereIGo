<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="RenterNavigationBar.jsp" %>
<!DOCTYPE html>
<html>
<style>
/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 65px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
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
  padding: 0;
  border: 1px solid #888;
  width: 100%;
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
  font-size: 22px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}

.modal-header {
  background-color: #5cb85c;
  color: white;
}

.modal-body {padding: 2px 6px;}

.modal-footer {
  background-color: #5cb85c;
  color: white;
}
</style>
<head>
<meta charset="UTF-8">
<title>Manage your accomodations</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>

<%@ page import="logic.beans.RentAccomodationBean" %>
<%@ page import="java.util.List" language="java" %>
<%@ page import="java.io.*"%>
<%@ page import="java.util.Base64"%>
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
				<th scope="col">Type of Accomodation</th>
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
			<td><img alt="house" src="data:image/jpg;base64, <%if (bean.getHouseImage()!= null) {
													out.println(new String(Base64.getEncoder().encodeToString(bean.getHouseImage())));
													}%>" width="150" height="120"/>
													<input type="hidden" id="base64HousePrev"></td>		
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
       			<a class="btn btn-info btn-l" id="update" href="RentRenter?action=requestToUpdate&id=<%=bean.getID()%>">Update<br>Info</a>
			</td>
			<td>
				<a class="btn btn-danger btn-l" href="RentRenter?action=Delete&id=<%=bean.getID()%>" id="delete">Delete</a>
			</td>
		</tr>
		<%
			} 
		%>
		</table>
	</div>
	<% 
	RentAccomodationBean beanToUpdate = null;
	if (request.getAttribute("bean") != null) {
		beanToUpdate = (RentAccomodationBean)request.getAttribute("bean");
	}
	if (beanToUpdate!= null) { 
	%>	
		<input type="hidden" id="toBeUpdate" value="<%=beanToUpdate.getID()%>">
	<%
	} else {
	%>
		<input type="hidden" id="toBeUpdate" value="false">
	<%
	}
	%>
	<!-- The Modal -->
<div id="createOrUpdate" class="modal">

  <!-- Modal content -->
<div class="modal-content">
  <div class="modal-header">
    <h5>Set Accomodation Informations</h5>
    <span class="close">&times;</span>
  </div>
  <div class="modal-body">
	<form>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputCity">City</label>
      <input type="text" class="form-control" id="city" <% if (beanToUpdate != null) {%> value="<%=beanToUpdate.getCity()%>" <% } else {%> placeholder="Enter City" <% } %>>
    </div>
    <div class="form-group col-md-6">
      <label for="inputAddress">Address</label>
      <input type="text" class="form-control" id="address" <% if (beanToUpdate != null) {%> value="<%=beanToUpdate.getAddress()%>" <% } else {%> placeholder="Enter Address" <% } %>>
    </div>
  </div>
  <div class="form-row">
    <div class="form-group col-md-6">
      <div class="form-group">
    	<label for="FormControlSelect">Type of Apartment</label>
    	<select class="form-control" id="type">
       	<option value="apartment" <% if (beanToUpdate != null && beanToUpdate.getType().equals("apartment")) {%> selected <% } %>>apartment</option>
      	<option value="cottage" <% if (beanToUpdate != null && beanToUpdate.getType().equals("cottage")) {%> selected <% } %>>cottage</option>
      	<option value="studio flat" <% if (beanToUpdate != null && beanToUpdate.getType().equals("studio flat")) {%> selected <% } %>>studio flat</option>
    	</select>
  	  </div>
    </div>
    <div class="form-group col-md-4">
      <div class="form-group">
    	<label for="FormControlSelect">Square Metres</label>
    	<select class="form-control" id="squareMetres">
    	<option value="&lt; 20" <% if (beanToUpdate != null && beanToUpdate.getSquareMetres().equals("< 20")) {%> selected <% } %> >&lt; 20</option>
      	<option value="20 - 39" <% if (beanToUpdate != null && beanToUpdate.getSquareMetres().equals("20 - 39")) {%> selected <% } %>>20 - 39</option>
      	<option value="40 - 59" <% if (beanToUpdate != null && beanToUpdate.getSquareMetres().equals("40 - 59")) {%> selected <% } %>>40 - 59</option>
      	<option value="> 60" <% if (beanToUpdate != null && beanToUpdate.getSquareMetres().equals("> 60")) {%> selected <% } %>>> 60</option>
    	</select>
  	  </div>
    </div>
    <div class="form-group col-md-2">
      <div class="form-group">
    	<label for="FormControlSelect">Beds</label>
    	<select class="form-control" id="beds">
      	<option value="1" <% if (beanToUpdate != null && beanToUpdate.getBeds().equals("1")) {%> selected <% } %> >1</option>
        <option value="2" <% if (beanToUpdate != null && beanToUpdate.getBeds().equals("2")) {%> selected <% } %>>2</option>
        <option value="3" <% if (beanToUpdate != null && beanToUpdate.getBeds().equals("3")) {%> selected <% } %>>3</option>
        <option value="4" <% if (beanToUpdate != null && beanToUpdate.getBeds().equals("4")) {%> selected <% } %>>4</option>
        <option value="5" <% if (beanToUpdate != null && beanToUpdate.getBeds().equals("5")) {%> selected <% } %>>5</option>
        <option value="6" <% if (beanToUpdate != null && beanToUpdate.getBeds().equals("6")) {%> selected <% } %>>6</option>
        <option value="7" <% if (beanToUpdate != null && beanToUpdate.getBeds().equals("7")) {%> selected <% } %>>7</option>
        <option value="8" <% if (beanToUpdate != null && beanToUpdate.getBeds().equals(">8")) {%> selected <% } %>>>8</option>
    	</select>
  	  </div>
    </div>
  </div>
  <div class="form-row">
  <div class="form-group col-md-6">
    <label for="FormControlTextarea">Description</label>
    <% if (beanToUpdate != null) {%>
    <textarea class="form-control" id="description" rows="3" ><%=beanToUpdate.getDescription()%></textarea>	 
    <% } else { %>
    <textarea class="form-control" id="description" rows="3" placeholder="Enter a short description of the house..."></textarea>
     <% } %>
  </div>
  <div class="form-group col-md-6">
    <label for="FormControlFile">Select a photo</label>
    <input type="file" class="form-control-file" id="houseImage" accept="image/jpg, image/png">
    <input type="hidden" id="base64House" name="base64House" <% if (beanToUpdate != null) { %> value="<%=new String(Base64.getEncoder().encodeToString(beanToUpdate.getHouseImage()))%>" <% } %>>
  </div>
  </div>
  <div class="form-group col-md-4">
  <label>
    Services
  </label>
  <div class="form-row">
  	<div class="form-check">
  		<input class="form-check-input" type="checkbox" value="" id="garden" 
  			<% if (beanToUpdate != null && beanToUpdate.getServices()[0]==1) { %> checked <% } %>>
  		<label class="form-check-label" for="garden">
    	Garden &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
  		</label>
  	</div>
  	<div class="form-check">
    	<input class="form-check-input" type="checkbox" value="" id="wifi"
    		<% if (beanToUpdate != null && beanToUpdate.getServices()[1]==1) { %> checked <% } %>>
    	<label class="form-check-label" for="wifi">
    	Wifi
    	</label>
    </div>
  </div>
  <div class="form-row">
  	<div class="form-check">
  		<input class="form-check-input" type="checkbox" value="" id="bathroom"
  		 	<% if (beanToUpdate != null && beanToUpdate.getServices()[2]==1) { %> checked <% } %>>
  		<label class="form-check-label" for="bathroom">
    	Bathroom &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
  		</label>
  	</div>
  	<div class="form-check">
    	<input class="form-check-input" type="checkbox" value="" id="kitchen"
    		<% if (beanToUpdate != null && beanToUpdate.getServices()[3]==1) { %> checked <% } %>>
    	<label class="form-check-label" for="kitchen">
    	Kitchen
    	</label>
    </div>
  </div>
  </div>
  </form>		
  </div>
  <div class="modal-footer">
    <a class="btn btn-warning btn-l" id="cancel">Cancel</a>
    <a class="btn btn-success btn-l" onClick="confirm()" id="confirm">Confirm</a>
    <input type="hidden" id="service"/>
  </div>
</div>

</div>
<script>
//Get the modal
var modal = document.getElementById("createOrUpdate");

// Get the button that opens the modal
var create = document.getElementById("create");
var cancel = document.getElementById("cancel");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

if (document.getElementById("toBeUpdate").value != "false") {
	document.getElementById("service").value = "Update";
	modal.style.display = "block";
}

// When the user clicks on the button, open the modal
create.onclick = function() {
  document.getElementById("service").value = "Create";
  modal.style.display = "block";
}
	
//var bean = document.getElementById("update").value;

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  document.getElementById("toBeUpdate").value = "false";
  modal.style.display = "none";
  setTimeout(function() {
	window.location.href = "RentRenter";
  }, 1000);
}
cancel.onclick = function() {
  document.getElementById("toBeUpdate").value = "false";
  modal.style.display = "none";
  setTimeout(function() {
	window.location.href = "RentRenter";
  }, 1000);
}

function confirm() {
	var city = document.getElementById("city").value;
	var address = document.getElementById("address").value;
	var type = document.getElementById("type").value;
	var squareMetres = document.getElementById("squareMetres").value;
	var beds = document.getElementById("beds").value;
	var description = document.getElementById("description").value;
	var garden = document.getElementById("garden").checked;
	var wifi = document.getElementById("wifi").checked;
	var bathroom = document.getElementById("bathroom").checked;
	var kitchen = document.getElementById("kitchen").checked;
	var service = document.getElementById("service").value;
	var houseImage = document.getElementById("base64House").value;
	var id = document.getElementById("toBeUpdate").value;
	
	$.ajax( {
		type: "POST",
		data:{
			city : city,
			address : address,
			type : type,
			squareMetres : squareMetres,
			beds : beds,
			description : description,
			houseImage : houseImage,
			garden : garden,
			wifi : wifi,
			bathroom : bathroom,
			kitchen : kitchen,
			service : service,
			id : id
		},
		url: "RentRenter"
	});
	setTimeout(function() {
		window.location.href = "RentRenter";
	}, 1300);
}

$("#houseImage").change(function() {
	var file = document.getElementById('houseImage').files[0];
	var reader = new FileReader();
	reader.readAsBinaryString(file);
	reader.onload = function() {
		codedImg = btoa(reader.result);
		console.log(codedImg);
		document.getElementById('base64House').value = codedImg;
		console.log(document.getElementById('base64House').value);
	};
	reader.onerror = function(error) {
		alert("Invalid file");
	};
});

</script>
</body>
</html>