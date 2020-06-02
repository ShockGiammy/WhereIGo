package logic.servlets;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.beans.InterestsBean;
import logic.controllers.ControllerFacade;
import logic.exceptions.MissingAnswareException;

@WebServlet("/PersonalityTestServlet")
public class PersonalityTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String error = "error";
	private static String defPage = "PersonalityTest.jsp";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		if(request.getParameter("evalPers") != null) {
			List<Integer> answares = new ArrayList<>();
			extractAnswares(answares, request);
			JspChangePage changeP = new JspChangePage();
			if(request.getAttribute(error) != null) {
				changeP.forwardPage(defPage, request, response);
			}
			else {
				InterestsBean intBean = new InterestsBean(answares);
				ControllerFacade fac = new ControllerFacade();
				try {
					String pers = fac.evaluateInterests(intBean);
					request.setAttribute("bookmessage", "This is your personality : " + pers);
					HomePageServlet hpServ = new HomePageServlet();
					hpServ.loadBookTravelSugg(request);
					changeP.forwardPage("BookTravelStart.jsp", request, response);
			
				}catch(MissingAnswareException e) {
					request.setAttribute(error, e.getMessage());
					changeP.forwardPage(defPage, request, response);
				}
			}
		}
	}
	
	private void extractAnswares(List<Integer> answares, HttpServletRequest request) {
		try {
			for(int i = 1; i < 11; i++) {
				String answ = request.getParameter("answ"+ i);
				answares.add(Integer.valueOf(answ));
			}
		}catch(NumberFormatException e) {
			request.setAttribute(error, "Please answare to all the questions");
		}
	}
}
