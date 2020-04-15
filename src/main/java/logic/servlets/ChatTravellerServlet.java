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

@WebServlet("/ChatTraveller")
public class ChatTravellerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		
		ChangePageServlet changeP = new ChangePageServlet();
		
		LoggedUser logUser = new LoggedUser();
		ControllerFacade facade = new ControllerFacade(this);
		List<User> users = facade.getUsers();
		req.setAttribute("users", users);
		List<String> groups = facade.getGroups();
		req.setAttribute("groups", groups);
		
		User myInfo = facade.getUser(logUser.getUserName());
		myInfo.setName(logUser.getUserName());
		req.setAttribute("I", myInfo);
		
		if (req.getParameter("message") != null) {
			facade.sendMessage(req.getParameter("message"), req.getParameter("receiver"));
		}

		List<Message> chat = null;
		User userChat = null;
		if (req.getParameter("chat") != null) {
			if (req.getParameter("chat").equals("private")) {
				chat = facade.openChat(req.getParameter("user"), ChatType.PRIVATE);
				userChat = facade.getUser(req.getParameter("user"));
				userChat.setName(req.getParameter("user"));
			} else if (req.getParameter("chat").equals("group")) {
				chat = facade.openChat(req.getParameter("user"), ChatType.GROUP);
			}
		}
		req.setAttribute("userChat", userChat);
		req.setAttribute("chat", chat);
			
		String page = "ChatTraveller.jsp";
		changeP.forwardPage(page, req, resp);
	}
}
