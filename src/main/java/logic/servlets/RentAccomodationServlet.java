package logic.servlets;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.beans.RentAccomodationBean;
import logic.controllers.ControllerFacade;

@WebServlet("/RentAnAccomodation")
public class RentAccomodationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static ControllerFacade facade = new ControllerFacade();
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		 
		List<RentAccomodationBean> listOfBean = facade.displayAnnouncement();
		req.setAttribute("list", listOfBean);		
	}
}
