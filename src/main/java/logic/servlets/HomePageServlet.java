package logic.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.controllers.ControllerFacade;

@WebServlet("/HomePageServlet")
public class HomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("action");
		String page = null;
		if(act.equalsIgnoreCase("gobooktravel")) {
			page = "BookTravelStart.jsp";
			ControllerFacade fac = new ControllerFacade();
			List<String> cities = new ArrayList<>();
			cities.addAll(fac.showLocations());
			request.setAttribute("cities", cities);
		}
		nextPage(page, request, response);
	}
	
	public void nextPage(String jspPage, HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher rd = request.getRequestDispatcher(jspPage);
		ChangePageServlet change = new ChangePageServlet();
		change.forwardPage(rd, request, response);
	}
}
