package logic.servlets;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.LoggedUser;
import logic.controllers.ChatType;
import logic.controllers.ControllerFacade;
import logic.exceptions.ServerDownException;
import logic.model.Message;
import logic.model.User;

@WebServlet("/chatRenter")
public class ChatRenterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		
		ChangePageServlet changeP = new ChangePageServlet();
		
		LoggedUser logUser = new LoggedUser();
		ControllerFacade facade = new ControllerFacade();
		List<User> users = facade.getUsers();
		req.setAttribute("users", users);
		List<String> groups = facade.getGroups();
		req.setAttribute("groups", groups);
		req.setAttribute("I", logUser.getUserName());

		List<Message> chat = null;
		if (req.getParameter("chat") != null) {
			facade.closeLastChat();
			if (req.getParameter("chat").equals("private")) {
				chat = facade.openChat(req.getParameter("user"), ChatType.PRIVATE);
				try {
					facade.execute(req.getParameter("user"), ChatType.PRIVATE);
				} catch (ServerDownException e) {				
					String page = "ErrorPage.jsp";
					changeP.forwardPage(page, req, resp);
				}
			} else if (req.getParameter("chat").equals("group")) {
				chat = facade.openChat(req.getParameter("user"), ChatType.GROUP);
				try {
					facade.execute(req.getParameter("user"), ChatType.GROUP);
				} catch (ServerDownException e) {
					String page = "ErrorPage.jsp";
					changeP.forwardPage(page, req, resp);
				}
			}
		}
		req.setAttribute("chat", chat);
			
		String page = "prova.jsp";
		changeP.forwardPage(page, req, resp);
	}
	

}
