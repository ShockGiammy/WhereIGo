<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file = "NavigationBar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book your travel</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<%@ page import="java.util.ArrayList" language="java" %>
<%@ page import="java.util.List" language="java" %>
<%@ page import="logic.beans.GroupBean" language="java" %>
<%@ page import="logic.LoggedUser" language="java"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
    var dtToday = new Date();
    var month = dtToday.getMonth() + 1;
    var day = dtToday.getDate();
    var year = dtToday.getFullYear();
    if(month < 10)
        month = '0' + month.toString();
    if(day < 10)
        day = '0' + day.toString();
    
    var maxDate = year + '-' + month + '-' + day;
    $('#depDate').attr('min', maxDate);
});
</script>

<script type="text/javascript">
$(function(){
	var dtToday = new Date();
    var month = dtToday.getMonth() + 1;
    var day = dtToday.getDate();
    var year = dtToday.getFullYear();
    if(month < 10)
        month = '0' + month.toString();
    if(day < 10)
        day = '0' + day.toString();
    
    var maxDate = year + '-' + month + '-' + day;
    $('#retDate').attr('min', maxDate); 
});
</script>

<style type="text/css">
	select.depcity {
    width:auto;
}

/*IE FIX */
select#depcity {
    width:200%;
}

</style>

<style type="text/css">
	select.arrcity {
    width:auto;
}

/*IE FIX */
select#arrcity {
    width:200%;
}
</style>

</head>


<%
	if(request.getAttribute("bookmessage") !=  null){
%>
<div class="alert alert-info alert-dismissible fade show" role="alert">
  		<p><%=request.getAttribute("bookmessage") %></p>
  	 	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
    	<span aria-hidden="true">&times;</span>
  		</button>
	</div>

<%
	}
%>

<%
		List<String> cities = new ArrayList<>();
		cities = (ArrayList<String>)request.getAttribute("cities");
		List<GroupBean> groupBeanL = new ArrayList<>();
		groupBeanL = (ArrayList<GroupBean>)request.getAttribute("grouplist");
		List<String> depCities = new ArrayList<>();
		depCities = (ArrayList<String>)request.getAttribute("depcit");
		List<String> arrCities = new ArrayList<>();
		arrCities = (ArrayList<String>)request.getAttribute("arrcit");
%>


<body>
	<form action="BookTravelServlet" method="post">
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-6" style="margin-right:60px;">
				<p class="h5">Location suggested for you</p>
				<div class="container">
					<% if(LoggedUser.getPersonality() == null) { %>
					<div class="row">
						<div class="col-sm">
							<a href="BookTravelServlet?action=takeTest" class="btn btn-info btn-l">Take personality test</a>
						</div>
					</div>
					<%
						} else {
					%>
					<%
						for(int i = 0; i < cities.size(); i++) {
					%>
					<div class="row">
						<div class="col-sm">
							<%=cities.get(i)%>
						</div>
						<div class="col-sm">
							<a href="BookTravelServlet?action=moreInfo&city=<%=cities.get(i)%>" class="btn btn-info btn-sm">More info</a>
						</div>
						<div class="col-sm">
							<a href="BookTravelServlet?action=bookshort&city=<%=cities.get(i)%>" class="btn btn-success btn-sm" style="white-space: nowrap;">Book now</a>
						</div>
					</div>
						<%
							}
						%>
						<%
							}
						%>
				</div>
			</div>
			<div class="col-xs-6" style="margin-right:60px;">
				<p class="h5">Travel groups suggested for you</p>
				<table class="table table-sm">
					<thead>
						<tr>
							<th scope="col">Group name</th>
							<th scope="col">Group admin</th>
							<th scope="col">Group destination</th>
							<th scope="col">Join group</th>
						</tr>
					</thead>
						<%
							if(LoggedUser.getPersonality() != null) {
						%>
						<%
							for(int i = 0; i < groupBeanL.size(); i++){
								GroupBean gbean = groupBeanL.get(i);
						%>
					<tr>
						<td><%=gbean.getGroupTitle()%></td>
						<td><%=gbean.getGroupOwner()%></td>
						<td><%=gbean.getGroupDestination()%></td>
						<td><a href="BookTravelServlet?action=joinGroup&descr=<%=gbean.getGroupTitle()%>" class= "btn btn-success btn-l">Join group</a></td>
						<%
							}
						%>
						<%
							}
						%>
					</tr>
				</table>
			</div>
			<div class="col-xs-6">
					<div class="container">
					<p class="h5">Book your travel</p>
						<div class="row">
							<div class="col-4">
								<div class="form-horiontal">
									<div class="form-group">
										<label for="dep" style="white-space: nowrap;">Departure city</label>
										<select class="depcity" name="depCity" id="depcity">
											<%
												for(int i = 0; i < depCities.size(); i++){
											%>
											<option value="<%=depCities.get(i)%>"><%=depCities.get(i)%></option>
											<%
												}
											%>
										</select>
									</div>
									<div class="form-group">
										<label for="arr" style="white-space: nowrap;">Arrive city</label>
										<select class="arrcity" name="arrCity" id="arrcity">
											<%
												for(int i = 0; i < arrCities.size(); i++){
											%>
											<option value="<%=arrCities.get(i)%>"><%=arrCities.get(i)%></option>
											<%
												}
											%>
										</select>
									</div>
								</div>
								<div class="form-horiontal">
									<div class="form-group">
										<label for="depDate" style="white-space: nowrap;">Departure date</label>
										<input type="date" name="depDate" id="depDate">
									</div>
								</div>
								<div class="form-horiontal">
									<div class="form-group">
										<label for="retDate" style="white-space: nowrap;">Return date</label>
										<input type="date" name="retDate" id="retDate">
									</div>
								<div class="form-horiontal">
									<div class="form-group">
										<input type="submit" name="checkSol" value="Find travels" class= "btn btn-info btn-l">
									</div>
								</div>	
							</div>
						</div>
					</div>
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