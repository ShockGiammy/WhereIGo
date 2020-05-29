package logic.servlets;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.LoggedUser;
import logic.beans.MessageBean;
import logic.beans.UserChatBean;
import logic.controllers.ChatType;
import logic.controllers.ControllerFacade;
import logic.exceptions.LengthFieldException;

@WebServlet("/ChatRenter")
public class ChatRenterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		
		JspChangePage changeP = new JspChangePage();		
		ControllerFacade facade = new ControllerFacade(this);
		
		UserChatBean myInfo = facade.getUser(LoggedUser.getUserName());
		myInfo.setName(LoggedUser.getUserName());
		req.setAttribute("I", myInfo);
		facade.setOfflineStatus();
		
		List<UserChatBean> users = facade.getUsers();
		req.setAttribute("users", users);
		
		if (req.getParameter("message") != null) {
			MessageBean message = null;
        	try {
				message = new MessageBean(req.getParameter("message"), req.getParameter("receiver"));
			} catch (LengthFieldException e) {
				String page = "ErrorPage.jsp";
				changeP.forwardPage(page, req, resp);
				Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());				
			}
			facade.sendMessage(message);
		}
		
		UserChatBean userChat = null;
		List<MessageBean> chat = null;
		if (req.getParameter("chat") != null && req.getParameter("chat").equals("private")) {
			chat = facade.openChat(req.getParameter("user"), ChatType.PRIVATE);
			userChat = facade.getUser(req.getParameter("user"));
			userChat.setName(req.getParameter("user"));
		}
		req.setAttribute("userChat", userChat);
		req.setAttribute("chat", chat);
			
		String page = "ChatRenter.jsp";
		changeP.forwardPage(page, req, resp);
	}
}
