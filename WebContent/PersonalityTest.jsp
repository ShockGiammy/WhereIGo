<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file = "NavigationBar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Personality test</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>

<%
	if(request.getAttribute("error") != null){
%>
<div class="alert alert-info alert-dismissible fade show" role="alert">
  		<p><%=request.getAttribute("error") %></p>
  	 	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
    	<span aria-hidden="true">&times;</span>
  		</button>
</div>
<%
	}		
%>

<body>
<form action="PersonalityTestServlet" method="post">
	<div class="container">
		<div class="row">
		<div class="col-4" style="margin-right:40px;">
			<p class="h6" style="font-weight: bold;">What do you like to do in your free time?</p>
			<div class="form-horizontal">
				<div class="form-group">
					<div class="choice">
						<input type="radio" name="answ1" id="answ1.1" value="2">
						<label class="radio-inline" for="answ1.1" style="white-space: nowrap;">Playing video-games with friends</label>
					</div>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<div class="choice">
						<input type="radio" name="answ1" id="answ1.2" value="1">
						<label class="radio-inline" for="answ1.2">Reading book</label>
					</div>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<div class="choice">
						<input type="radio" name="answ1" id="answ1.3" value="3">
						<label class="radio-inline" for="answ1.3">Doing sports</label>
					</div>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<div class="choice">
						<input type="radio" name="answ1" id="answ1.4" value="4">
						<label class="radio-inline" for="answ1.4">Going for nature trips</label>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-4">
			<p class="h6" style="font-weight: bold;">What do you prefer for your saturday night?</p>
			<div class="form-horizontal">
				<div class="form-group">
					<div class="choice">
						<input type="radio" name="answ2" id="answ2.1" value="4">
						<label class="radio-inline" for="answ2.1" style="white-space: nowrap;">Stay at home and watch films/tv series</label>
					</div>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<div class="choice">
						<input type="radio" name="answ2" id="answ2.2" value="1">
						<label class="radio-inline" for="answ2.2">Go out with friends for a beer</label>
					</div>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<div class="choice">
						<input type="radio" name="answ2" id="answ2.3" value="2">
						<label class="radio-inline" for="answ1.3">Go to the disco with friends</label>
					</div>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<div class="choice">
						<input type="radio" name="answ2" id="answ2.4" value="3">
						<label class="radio-inline" for="answ1.4">Have dinner and then go to sleep</label>
					</div>
				</div>
			</div>
		</div>
	</div>
		

	
	<div class="row">
		<div class="col-4" style="margin-right:40px;">
			<p class="h6" style="font-weight: bold;">Which sentence represent you better at work?</p>
			<div class="form-horizontal">
				<div class="form-group">
					<div class="choice">
						<input type="radio" name="answ3" id="answ3.1" value="1">
						<label class="radio-inline" for="answ3.1" style="white-space: nowrap;">I like cooperation and team working</label>
					</div>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<div class="choice">
						<input type="radio" name="answ3" id="answ3.2" value="3">
						<label class="radio-inline" for="answ3.2">I prefer to work individually</label>
					</div>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<div class="choice">
						<input type="radio" name="answ3" id="answ3.3" value="2">
						<label class="radio-inline" for="answ3.3">I am competitive and I want always to be right</label>
					</div>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<div class="choice">
						<input type="radio" name="answ3" id="answ3.4" value="4">
						<label class="radio-inline" for="answ3.4">In a team, I am the one who sleeps</label>
					</div>
				</div>
			</div>
		</div>
		
		
		<div class="col-4">
			<p class="h6" style="font-weight: bold;">Do you think you are a person :</p>
			<div class="form-horizontal">
				<div class="form-group">
					<div class=choice>
						<input type="radio" name="answ4" id="answ4.1" value="2">
						<label class="radio-inline" for="answ4.1">Perfectly organized</label>
					</div>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<div class="choice">
						<input type="radio" name="answ4" id="answ4.2" value="3">
						<label class="radio-inline" for="answ4.2">Quite organized</label>
					</div>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<div class="choice">
						<input type="radio" name="answ4" id="answ4.3" value="1">
						<label class="radio-inline" for="answ4.3">Poorly organized</label>
					</div>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<div class="choice">
						<input type="radio" name="answ4" id="answ4.4" value="4">
						<label class="radio-inline" for="answ4.4">Not organized at all</label>
					</div>
				</div>
			</div>
			<input type="submit" class="btn btn-success btn-l" name="evalPers" value="Submit answares">
		</div>
	</div>
</div>
</form>
	
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>