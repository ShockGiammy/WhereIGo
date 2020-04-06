package logic.servlets;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.controllers.ControllerFacade;
import logic.model.User;

@WebServlet("/chatRenter")
public class ChatRenterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ControllerFacade facade;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		
		facade = new ControllerFacade();
		List<User> users = facade.getUsers();
		req.setAttribute("users", users);
		List<String> groups = facade.getGroups();
		req.setAttribute("groups", groups);
		
		ChangePageServlet changeP = new ChangePageServlet();
		String page = "ChatRenter.jsp";
		changeP.forwardPage(page, req, resp);
		
	}
	

}
