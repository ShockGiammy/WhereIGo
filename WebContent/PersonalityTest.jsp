<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file = "NavigationBar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Personality test</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
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
					<span style="white-space:nowrap;">
						<input type="radio" name="answ1" id="answ1.1" value="2">
						<label class="radio-inline" for="answ1.1" style="white-space: nowrap;">Playing video-games with friends</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ1" id="answ1.2" value="1">
						<label class="radio-inline" for="answ1.2">Reading book</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ1" id="answ1.3" value="3">
						<label class="radio-inline" for="answ1.3">Doing sports</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ1" id="answ1.4" value="4">
						<label class="radio-inline" for="answ1.4">Going for nature trips</label>
					</span>
				</div>
			</div>
		</div>
		
		<div class="col-4">
			<p class="h6" style="font-weight: bold;">What do you prefer for your saturday night?</p>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ2" id="answ2.1" value="4">
						<label class="radio-inline" for="answ2.1" style="white-space: nowrap;">Stay at home and watch films/tv series</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ2" id="answ2.2" value="1">
						<label class="radio-inline" for="answ2.2">Go out with friends for a beer</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ2" id="answ2.3" value="2">
						<label class="radio-inline" for="answ2.3">Go to the disco with friends</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ2" id="answ2.4" value="3">
						<label class="radio-inline" for="answ2.4">Have dinner and then go to sleep</label>
					</span>
				</div>
			</div>
		</div>
		</div>
		
		<div class="row">
		<div class="col-4" style="margin-right:40px;">
			<p class="h6" style="font-weight: bold;">If you have a problem with one of your friends :</p>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ3" id="answ3.1" value="2">
						<label class="radio-inline" for="answ3.1" style="white-space: nowrap;">You talk to her/him and try to solve</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ3" id="answ3.2" value="1">
						<label class="radio-inline" for="answ3.2">You ask an advice to another of your friends</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ3" id="answ3.3" value="3">
						<label class="radio-inline" for="answ3.3">You wait the she/he comes to talk with you</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ3" id="answ3.4" value="4">
						<label class="radio-inline" for="answ3.4">It's her/his fault for sure, so you don't argue</label>
					</span>
				</div>
			</div>
		</div>
		
		<div class="col-4">
			<p class="h6" style="font-weight: bold;">Which type of tv series you like :</p>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ4" id="answ4.1" value="4">
						<label class="radio-inline" for="answ4.1">Everyone : I just lie on the couch and watch the tv</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ4" id="answ4.2" value="1">
						<label class="radio-inline" for="answ4.2">I usually watch the ones my friends suggest to me</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ4" id="answ4.3" value="2">
						<label class="radio-inline" for="answ4.3">I enjoy documentaries very much</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ4" id="answ4.4" value="3">
						<label class="radio-inline" for="answ4.4">I don't like tv series that much.</label>
					</span>
				</div>
			</div>
		</div>
		</div>
		
		<div class="row">
		<div class="col-4" style="margin-right:40px;">
			<p class="h6" style="font-weight: bold;">After a delusion : </p>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ5" id="answ5.1" value="1">
						<label class="radio-inline" for="answ5.1" style="white-space: nowrap;">You try to spend a lot of time with your friends</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ5" id="answ5.2" value="3">
						<label class="radio-inline" for="answ5.2">You focus on your job and you don't think about it.</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ5" id="answ5.3" value="2">
						<label class="radio-inline" for="answ5.3">You take a break from your job and go for a trip.</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ5" id="answ5.4" value="4">
						<label class="radio-inline" for="answ5.4">You spend some weeks sleeping and watching tv</label>
					</span>
				</div>
			</div>
		</div>	
		
		<div class="col-4">
			<p class="h6" style="font-weight: bold;">Which sentence represent you better at work?</p>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ6" id="answ6.1" value="1">
						<label class="radio-inline" for="answ6.1" style="white-space: nowrap;">I like cooperation and team working</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ6" id="answ6.2" value="3">
						<label class="radio-inline" for="answ6.2">I prefer to work individually</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ6" id="answ6.3" value="2">
						<label class="radio-inline" for="answ6.3">I am competitive and I want always to be right</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ6" id="answ6.4" value="4">
						<label class="radio-inline" for="answ6.4">In a team, I am the one who sleeps</label>
					</span>
				</div>
			</div>
		</div>
		</div>
		
		<div class="row">
		<div class="col-4" style="margin-right:40px;">
			<p class="h6" style="font-weight: bold;">Do you think you are a person :</p>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ7" id="answ7.1" value="2">
						<label class="radio-inline" for="answ7.1">Perfectly organized</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ7" id="answ7.2" value="3">
						<label class="radio-inline" for="answ7.2">Quit organized</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ7" id="answ7.3" value="1">
						<label class="radio-inline" for="answ7.3">Poorly organized</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ7" id="answ7.4" value="4">
						<label class="radio-inline" for="answ7.4">Not organized at all</label>
					</span>
				</div>
			</div>
		</div>
		<div class="col-4">
			<p class="h6" style="font-weight: bold;white-space:nowrap;">Your co-worker keeps stealing your drink from the fridge. What do you do?</p>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ9" id="answ9.1" value="2">
						<label class="radio-inline" for="answ9.1" style="white-space: nowrap;">You don't mind, it isn't a problem for you</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ9" id="answ9.2" value="3" style="white-space: normal;">
						<label class="radio-inline" for="answ9.2" style="white-space: nowrap;">Spit in your drink, and watch them drink your saliva</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ9" id="answ9.3" value="1">
						<label class="radio-inline" for="answ9.3" style="white-space: nowrap;">You buy more drinks so that anyone can have her/his</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ9" id="answ9.4" value="4">
						<label class="radio-inline" for="answ9.4" style="white-space: nowrap;">You always keep you drink near to your desk</label>
					</span>
				</div>
			</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-4" style="margin-right:40px;">
			<p class="h6" style="font-weight: bold;">During a trip abroad : </p>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ8" id="answ8.1" value="3">
						<label class="radio-inline" for="answ8.1">You visit all the typical places </label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ8" id="answ8.2" value="4">
						<label class="radio-inline" for="answ8.2">You spend all your time in at the sea or spa</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ8" id="answ8.3" value="1">
						<label class="radio-inline" for="answ8.3">You try to meet new people</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ8" id="answ8.4" value="2">
						<label class="radio-inline" for="answ8.4">You totally get involved in the local culture</label>
					</span>
				</div>
			</div>
			<input type="submit" class="btn btn-success btn-l" name="evalPers" value="Submit answares">
		</div>
		<div class="col-4">
			<p class="h6" style="font-weight: bold;white-space:nowrap;">You get chatting to someone at a party, and you find out they hate the same person as you. What do you do?</p>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ10" id="answ10.1" value="3">
						<label class="radio-inline" for="answ10.1" style="white-space: nowrap;">Start a beautiful long friendship with them</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ10" id="answ10.2" value="4">
						<label class="radio-inline" for="answ10.2">Briefly talk about the person, and move the conversation on</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ10" id="answ10.3" value="1">
						<label class="radio-inline" for="answ10.3" style="white-space: nowrap;">Talk about the person you both hate for hours</label>
					</span>
				</div>
			</div>
			<div class="form-horizontal">
				<div class="form-group">
					<span style="white-space:nowrap;">
						<input type="radio" name="answ10" id="answ10.4" value="2">
						<label class="radio-inline" for="answ10.4">Plot the downfall of the person you hate</label>
					</span>
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