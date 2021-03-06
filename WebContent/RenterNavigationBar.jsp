<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
.img_cont_msg{
	height: 40px;
	width: 40px;
}
</style>
<body>
<%@ page import="java.util.Base64"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">WhereIGo</a>
			<img alt="LOGO" src="webimm/Logo.PNG" height="95" width="265">
		 <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    		<span class="navbar-toggler-icon"></span>
 		 </button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
    		<ul class="navbar-nav mr-auto">
      			<li class="nav-item">
        			<a class="nav-link" href="moveTo?action=gohome">Home<span class="sr-only">(current)</span></a>
      			</li>
      			<li class="nav-item">
       				<a class="nav-link" href="moveTo?action=Rent">Manage accommodations</a>
      			</li>
      			<li class="nav-item">
       				<a class="nav-link" href="moveTo?action=Chat">Chat with users&nbsp&nbsp&nbsp</a>
      			</li>
      			<%
      			byte[] image = (byte[])session.getAttribute("image");
      			%>
      			<li class="nav-item">
      				<div>
              	  		<img src="data:image/jpg;base64, <%if (image!=null) { out.println(new String(Base64.getEncoder().encodeToString(image))); }%>" height="45px" width="45px" alt="userImage" class="rounded-circle user_img_msg">       		
              		</div>
              	</li>
      			<li class="nav-item">
       				<a class="nav-link" href="moveTo?action=Exit">&nbsp&nbsp&nbspExit</a>
      			</li>
      		</ul>
      	</div>
      </nav>
</body>
</html>