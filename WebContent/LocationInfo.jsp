<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Location Info</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<%@ page import="java.util.Base64"%>
<%@ page import="logic.beans.LocationBean" language="java" %>
<%
	LocationBean locBean = new LocationBean();
	locBean = (LocationBean)request.getAttribute("location");
%>

<body>
	<p class="h3"><%=locBean.getCityName()%></p>
	<p class="h4"><%=locBean.getCountryName() %></p>
	<div class="container">
		<div class="row">
			<div class="col-xs" style="margin-right:5px;">
				<img src="data:image/jpg;base64, <%out.println(new String(Base64.getEncoder().encodeToString(locBean.getStream())));%>" height="600px" width="600px" class="img-fluid" alt="Responsive image">
			</div>
			<div class="col-xs">
				<%=locBean.getDescription()%>
			</div>
		<div class="col-xs">
			<a href="HomePageServlet?action=gobooktravel" class="btn btn-secondary btn-lg">Back</a>
		</div>
	</div>
</div>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>