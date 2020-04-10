package logic.servlets;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.beans.RentAccomodationBean;
import logic.controllers.ControllerFacade;

@WebServlet("/RentRenter")
public class RentRenterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		
		ChangePageServlet changeP = new ChangePageServlet();	
		ControllerFacade facade = new ControllerFacade();
	
		String page = null;	
		String act = req.getParameter("action");
		if (act.equalsIgnoreCase("Rent")) {
			List<RentAccomodationBean> listOfBean = facade.displayMyAnnouncement();
			req.setAttribute("list", listOfBean);
			page = "ManageAccomodations.jsp";
		}
		else if (act.equalsIgnoreCase("Update")) {
			
			page = "ManageAccomodations.jsp";
		}
		else if (act.equalsIgnoreCase("Delete")) {
			facade.deleteMyAccomodation(Integer.parseInt(req.getParameter("id")));
			page = "ManageAccomodations.jsp";
		}
		changeP.forwardPage(page, req, resp);	
	}
}
