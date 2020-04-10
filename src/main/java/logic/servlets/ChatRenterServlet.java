package logic.servlets;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.LoggedUser;
import logic.controllers.ChatType;
import logic.controllers.ControllerFacade;
import logic.model.Message;
import logic.model.User;

@WebServlet("/ChatRenter")
public class ChatRenterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		
		ChangePageServlet changeP = new ChangePageServlet();
		
		LoggedUser logUser = new LoggedUser();		
		ControllerFacade facade = new ControllerFacade();
		
		User myInfo = facade.getUser(logUser.getUserName());
		myInfo.setName(logUser.getUserName());
		req.setAttribute("I", myInfo);
		
		List<User> users = facade.getUsers();
		req.setAttribute("users", users);
		
		if (req.getParameter("message") != null) {
			facade.sendMessage(req.getParameter("message"), req.getParameter("receiver"));
		}

		List<Message> chat = null;
		User userChat = null;
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
