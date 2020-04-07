<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="NavigationBar.html" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
<%@ page import="java.util.List" language="java" %>
<%@ page import="logic.model.User" %>
<%@ page import="java.util.Base64"%>
	<%
	List<User> users = (List<User>)request.getAttribute("users");
	List<String> groups = (List<String>)request.getAttribute("groups");
	%>
	<div style="float:left">
	<%
	for(User user : users) {
		if (user.getPicture()!=null) {
	%>
	<ul class="smooth-scroll list-unstyled">
		<li class="list-group-item"> <a href="chatRenter?action=openChat"> <img alt="house" src="data:image/jpg;base64, <%out.println(new String(Base64.getEncoder().encodeToString(user.getPicture())));%>" width="45" height="45"/> <%=user.getName() %></a>
	</ul>
	<%
		}
	}
	for(String group : groups) {
	%>
	<ul>
		<li class="list-group-item"><a href="chatRenter?action=openChat"><%=group %></a>
	</ul>
	<%
	}
	%>
	</div>
	<div class="container">
  		<!--<img src="/w3images/bandmember.jpg" alt="Avatar">-->
  		<p>Sweet! So, what do you wanna do today?</p>
	</div>
	<div style="clear:both"></div>
	<!--<footer tabindex="-1" class="_2tW_W"><div class="_3pkkz V42si copyable-area"><div class="weEq5" style="will-change: width;">
	<div tabindex="-1" class="_1Plpp"><div tabindex="-1" class="_3F6QL _2WovP"><div class="_39LWd" style="visibility: visible;">Scrivi un messaggio</div><div class="_2S1VP copyable-text selectable-text" contenteditable="true" data-tab="1" dir="ltr" spellcheck="true"></div></div></div>
	</div></div></footer>-->
	
<!--<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script>
$( "li" ).click(function() {
  $( this ).slideUp();
});
</script>-->
</body>
</html>