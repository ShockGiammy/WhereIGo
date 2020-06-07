<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="NavigationBar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>Chat</title>
<style>
.msg_card_body{
	overflow-y: auto;
	}
.msg_cotainer_send{
	margin-top: auto;
	margin-bottom: auto;
	margin-right: 10px;
	border-radius: 25px;
	background-color: #78e08f;
	padding: 10px;
	position: relative;
}
.msg_cotainer{
	margin-top: auto;
	margin-bottom: auto;
	margin-left: 10px;
	border-radius: 25px;
	background-color: #82ccdd;
	padding: 10px;
	position: relative;
}

.container{max-width:1170px; margin:auto;}
img{ max-width:100%;}

.headind_srch{ padding:10px 29px 10px 20px; overflow:hidden; border-bottom:1px solid #c4c4c4;}

.inbox_people {
  background: #f8f8f8 none repeat scroll 0 0;
  float: left;
  overflow: hidden;
  width: 40%; border-right:1px solid #c4c4c4;
}
.inbox_msg {
  border: 1px solid #c4c4c4;
  clear: both;
  overflow: hidden;
}

.top_spac{ margin: 20px 0 0;}


.recent_heading {float: left; width:45%;}

.recent_heading h4 {
  color: #05728f;
  font-size: 20px;
  margin: auto;
}

.chat_ib h5{ font-size:16px; color:#464646; margin:0 0 8px 0;}
.chat_img {
  float: left;
  width: 11%;
}

.chat_ib {
  float: left;
  padding: 0 0 0 15px;
  width: 88%;
}

.chat_people{ overflow:hidden; clear:both;}

.chat_list {
  border-bottom: 1px solid #c4c4c4;
  margin: 0;
  padding: 18px 16px 10px;
}
.inbox_chat { height: 550px; overflow-y: scroll;}

.active_chat{ background:#ebebeb;}

.img_cont{
	position: relative;
	height: 70px;
	width: 70px;
}
.img_cont_msg{
	height: 40px;
	width: 40px;
}

.mesgs {
  float: left;
  padding: 30px 15px 0 25px;
  width: 60%;
}

.input_msg_write input {
  background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
  border: medium none;
  color: #4c4c4c;
  font-size: 15px;
  min-height: 48px;
  width: 100%;
}

.type_msg {border-top: 1px solid #c4c4c4;position: relative;}
.msg_send_btn {
  background: #05728f none repeat scroll 0 0;
  border: medium none;
  border-radius: 50%;
  color: #fff;
  cursor: pointer;
  font-size: 17px;
  height: 35px;
  position: absolute;
  right: 10px;
  top: 11px;
  width: 37px;
}
.messaging { padding: 0 0 18px 0;}
.msg_history {
  height: 516px;
  overflow-y: auto;
}
</style>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" type="text/css" rel="stylesheet">
</head>
<body>
<%@ page import="java.util.List" language="java" %>
<%@ page import="logic.beans.UserChatBean" %>
<%@ page import="java.util.Base64"%>
<%@ page import="logic.beans.MessageBean" %>
<%@ page import="logic.beans.GroupChatBean" %>
<%
List<UserChatBean> users = (List<UserChatBean>)request.getAttribute("users");
List<GroupChatBean> groups = (List<GroupChatBean>)request.getAttribute("groups");
%>
<div class="container">
<%
UserChatBean userChat = (UserChatBean)request.getAttribute("userChat");
String groupName = (String)request.getAttribute("group");
if (userChat!= null) {
%>
<h3 class=" text-center"><%=userChat.getName()%></h3>
<input type="hidden" value="<%=userChat.getName()%>" id="receiver"/>
<input type="hidden" value="private" id="chatType"/>
<%
	}
else if (groupName!= null){
%>
<h3 class=" text-center"><%=groupName%></h3>
<input type="hidden" value="<%=groupName%>" id="receiver"/>
<input type="hidden" value="group" id="chatType"/>
<%
	}
else {
%>
<h3 class=" text-center">Chat</h3>
<%
	}
%>
<div class="messaging">
      <div class="inbox_msg">
        <div class="inbox_people">
          <div class="headind_srch">
            <div class="recent_heading">
              <h4>Users and Groups</h4>
            </div>
          </div>
          <div class="inbox_chat">
          <%
          	for(UserChatBean user : users) {
            	if (user.getPicture()!=null) {
          %>
            <div class="chat_list">
              <div class="chat_people">
                <div class="chat_img"> 
                <img class="rounded-circle user_img_msg" src="data:image/jpg;base64, <%out.println(new String(Base64.getEncoder().encodeToString(user.getPicture())));%>" alt="UserImage" width="45" height="45">
                </div>
                <div class="chat_ib">
                  <a href="ChatTraveller?chat=private&user=<%out.println(user.getName());%>" class="btn btn-primary stretched-link"><%=user.getName()%></a>
                </div>
              </div>
			</div>
			<%
            	}
			}
			for(GroupChatBean group : groups) {
			%>
			<div class="chat_list">
              <div class="chat_people">
                <div class="chat_ib">
                  <a href="ChatTraveller?chat=group&user=<%out.println(group.getName());%>" class="btn btn-primary stretched-link"><%out.println(group.getName());%></a>
                </div>
              </div>
			</div>
            <%
            	}
            %>           
          </div>
        </div>
        <div class="mesgs">
          <div class="msg_history" id="chatMessages">
          <%
          UserChatBean myInfo = (UserChatBean)request.getAttribute("I");
                              String userName = myInfo.getName();
                              String myImage = null;
                              if (myInfo.getPicture()!= null) {
                            	  myImage = new String(Base64.getEncoder().encodeToString(myInfo.getPicture()));
                          	}
                              List<MessageBean> chat = (List<MessageBean>)request.getAttribute("chat");
                              String pictureImage = null;
                            	 
                              if (chat!= null) {
                            	if (userChat!= null) {
                        	  		pictureImage = new String(Base64.getEncoder().encodeToString(userChat.getPicture()));
                            	}
                              	for (MessageBean message : chat) {
                          			if (message.getMsg() != null) {
                          				if (!message.getName().equals(userName)) {
          %>          
              	  	<div class="d-flex justify-content-start mb-4">
              	  		<div class="img_cont_msg">
              	  			<img src="data:image/jpg;base64, <%out.println(pictureImage);%>" alt="userImage" class="rounded-circle user_img_msg">
              	  		</div>
						<div class="msg_cotainer">
                  	  	<%=message.getName()+": "+message.getMsg() %>
              	  		</div>
            	  </div>
            	<% 
            	}
      			else { %>
            	<div class="d-flex justify-content-end mb-4">
					<div class="msg_cotainer_send">
                    <%=userName+": "+message.getMsg() %>
                    </div>
                    <div class="img_cont_msg">
                    <img src="data:image/jpg;base64, <%out.println(myImage);%>" alt="userImage" class="rounded-circle user_img_msg">
              	  	</div>
            	</div>
            		<% 
      					}
            		}
      			}
          	}
            %>
          </div>
          <div class="type_msg">
            <div class="input_msg_write">
              <input type="text" class="write_msg" placeholder="Type a message" id="textMsg"/>
              <button class="msg_send_btn" type="button" onclick="sendMessage()">
              	<i class="fa fa-paper-plane-o" aria-hidden="true"></i>
              </button>
            </div>
        </div>
      </div>
	</div>
  </div>
</div>
<script>
window.scrollTo(0, document.body.scrollHeight);

var objDiv = document.getElementById("chatMessages");
objDiv.scrollTop = objDiv.scrollHeight;

var input = document.getElementById("textMsg");
input.addEventListener("keyup", function(event) {
	if (event.keyCode === 13) {
 		event.preventDefault();
 		sendMessage();
	}
});

function sendMessage() {
	var message = document.getElementById("textMsg").value;
	var receiver = document.getElementById("receiver").value;
	var type = document.getElementById("chatType").value;
	document.getElementById("textMsg").clean;
	$.ajax( {
		type: "POST",
		data:{
			message : message,
			receiver : receiver
		},
		url: "ChatTraveller"
	});
	setTimeout(function() {
			window.location.href = "ChatTraveller?chat="+type+"&user="+receiver;
	}, 500);
}
</script>
</body>
</html>
