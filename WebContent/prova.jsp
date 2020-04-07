<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="NavigationBar.html" %>
<!DOCTYPE html>
<html>
<head>
<style>
.container{max-width:1170px; margin:auto;}
img{ max-width:100%;}
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

.headind_srch{ padding:10px 29px 10px 20px; overflow:hidden; border-bottom:1px solid #c4c4c4;}

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

.incoming_msg_img {
  display: inline-block;
  width: 6%;
}
.received_msg {
  display: inline-block;
  padding: 0 0 0 10px;
  vertical-align: top;
  width: 92%;
 }
 .received_withd_msg p {
  background: #ebebeb none repeat scroll 0 0;
  border-radius: 3px;
  color: #646464;
  font-size: 14px;
  margin: 0;
  padding: 5px 10px 5px 12px;
  width: 100%;
}

.received_withd_msg { width: 57%;}
.mesgs {
  float: left;
  padding: 30px 15px 0 25px;
  width: 60%;
}

 .sent_msg p {
  background: #05728f none repeat scroll 0 0;
  border-radius: 3px;
  font-size: 14px;
  margin: 0; color:#fff;
  padding: 5px 10px 5px 12px;
  width:100%;
}
.outgoing_msg{ overflow:hidden; margin:26px 0 26px;}
.sent_msg {
  float: right;
  width: 46%;
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
  height: 33px;
  position: absolute;
  right: 0;
  top: 11px;
  width: 33px;
}
.messaging { padding: 0 0 50px 0;}
.msg_history {
  height: 516px;
  overflow-y: auto;
}
</style>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" type="text/css" rel="stylesheet">
</head>
<body>
<%@ page import="java.util.List" language="java" %>
<%@ page import="logic.model.User" %>
<%@ page import="java.util.Base64"%>
<%@ page import="logic.model.Message" %>
<%
List<User> users = (List<User>)request.getAttribute("users");
List<String> groups = (List<String>)request.getAttribute("groups");
%>
<div class="container">
<h3 class=" text-center">Chat</h3>
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
			for(User user : users) {
				if (user.getPicture()!=null) {
			%>
            <div class="chat_list">
              <div class="chat_people">
                <div class="chat_img"> <img src="data:image/jpg;base64, <%out.println(new String(Base64.getEncoder().encodeToString(user.getPicture())));%>" alt="UserImage" width="45" height="45">
                </div>
                <div class="chat_ib">
                  <a href="chatRenter?chat=private&user=<%out.println(user.getName());%>" class="btn btn-primary stretched-link"><%=user.getName()%></a>
                </div>
                
              </div>
			</div>
			<%
				}
			}
          	for(String group : groups) {
			%>
			<div class="chat_list">
              <div class="chat_people">
                <div class="chat_ib">
                  <a href="chatRenter?chat=group&user=<%out.println(group);%>" class="btn btn-primary stretched-link"><%=group%></a>
                </div>
              </div>
			</div>
            <%
			}
			%>           
          </div>
        </div>
        <div class="mesgs">
          <div class="msg_history">
          <%
          String userName = (String)request.getAttribute("I");
          List<Message> chat = (List<Message>)request.getAttribute("chat");
          if (chat!= null) {
          	for (Message message : chat) {
      			if (message.getMsg() != null) {
      				if (message.getName().equals(userName)) {
          			%>          
            		<div class="incoming_msg">
              	  		<div class="incoming_msg_img">
              	  			<img src="https://ptetutorials.com/images/user-profile.png" alt="sunil">
              	  		</div>
              	  	<div class="received_msg">
                	<div class="received_withd_msg">
                  	  <p><%=message.getMsg() %></p>
              	  	</div>
            	  </div>
            	</div>
            	<% 
            	}
      			else { %>
            	<div class="outgoing_msg">
              	  <div class="sent_msg">
                    <p><%=message.getMsg() %></p>
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
              <input type="text" class="write_msg" placeholder="Type a message" />
              <button class="msg_send_btn" type="button" onclick="sendMessage()"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
            </div>
        </div>
      </div>
	</div>
  </div>
</div>
<script>
function sendMessage() {
	console.log("Put a message here.");
}
</script>
</body>
</html>
