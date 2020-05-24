<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Location Info</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
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

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>