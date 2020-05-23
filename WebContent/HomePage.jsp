<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file = "NavigationBar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>

<%@ page import="java.util.ArrayList" language="java" %>
<%@ page import="java.util.List" language="java" %>
<%@ page import="logic.beans.UserTravelBean" language="java" %>
<%@ page import="logic.beans.UserDataBean" language="java" %>
<%@ page import="logic.beans.GroupBean"  language="java"%>

<%
	List<UserTravelBean> tBeanList = new ArrayList<>();
	tBeanList = (ArrayList<UserTravelBean>)request.getAttribute("travels");
	List<GroupBean> gBeanList = new ArrayList<>();
	gBeanList = (ArrayList<GroupBean>)request.getAttribute("groups");
	List<UserDataBean> dBeanList = new ArrayList<>();
	dBeanList = (ArrayList<UserDataBean>)request.getAttribute("users");
%>

<body>
<form action="RentAnAccomodation" method="post">
	<div class="container-fluid" style="text-align:center;">
		<div class = "row">
			<div class="col-xs-6" style="margin-right:30px;">
				<p class="h5">Your travels</p>
				<table class="table table-sm">
					<thead>
						<tr>
							<th scope="col">Ticket ID</th>
							<th scope="col">Departure city</th>
							<th scope="col">Departure date</th>
							<th scope="col">Arrive city</th>
							<th scope="col">Return date</th>
							<th scope="col">Cost</th>
							<th scope="col">Delete ticket</th>
						</tr>
					</thead>
						<%
							for(int i = 0; i < tBeanList.size(); i++){
								UserTravelBean bean = tBeanList.get(i);
						%>
					<tr>
						<td><%=bean.getParsedId()%></td>
						<td><%=bean.getCityOfDep()%></td>
						<td><%=bean.getFirstDay()%></td>
						<td><%=bean.getCityOfArr()%></td>
						<td><%=bean.getLastDay()%></td>
						<td><%=bean.getParsedCost()%></td>
						<td><a href="BookTravelServlet?action=delTick&id=<%=bean.getId()%>" class="btn btn-danger btn-md">Delete</a>
						<%
							}
						%>
					</tr>
					</table>
					</div>
	
			<div class="col-xs-6" style="margin-right:30px;">
				<p class="h5">Your travel groups</p>
				<table class="table table-sm">
					<thead>	
						<tr>
							<th scope="col">Group name</th>
							<th scope="col">Group admin</th>
							<th scope="col">Group destination</th>
							<th scope="col">Delete or leave group</th>
						</tr>
					</thead>
					<%
						for(int i = 0; i < gBeanList.size(); i++){
							GroupBean gbean = gBeanList.get(i);
					%>
				<tr>
					<td><%=gbean.getGroupTitle()%></td>
					<td><%=gbean.getGroupOwner()%></td>
					<td><%=gbean.getGroupDestination()%></td>
					<td><a href="BookTravelServlet?action=delGroup&groupName=<%=gbean.getGroupTitle()%>&groupOwner=<%=gbean.getGroupOwner()%>" class="btn btn-danger btn-l">Delete/Leave</a></td>
				</tr>
				<%
					}
				%>
				<tr>
					<td><a href="BookTravelServlet?action=creategroup" class=" btn btn-l btn-info">Create group</a></td>
				</tr>
				</table>
				</div>
				
				<div>
					<p class="h5">Travelers suggested for you</p>
					<div class="container">
						<%
							for(int i = 0; i < dBeanList.size(); i++){
							UserDataBean bean = dBeanList.get(i);
						%>
						<div class="row">
							<div><img src="data:image/jpg;base64, <%if (bean.getByteStream()!= null) { out.println(new String(Base64.getEncoder().encodeToString(bean.getByteStream()))); }%>" height="35px" width="35px" alt="userImage" class="rounded-circle user_img_msg"></div>
							<div style="font-size:20px;"><%=bean.getUsername()%></div>
							<div class="col-sm"><a href="ChatTraveller?action=openChat" class="btn btn-info btn-l">Contact</a></div>
						</div><br>
						<%
							}
						%>
					</div>
				</div>
			</div>
		</div>
	</form>
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>	
</body>
</html>