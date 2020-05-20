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
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		if(request.getParameter("evalPers") != null) {
			try {
				List<Integer> answares = new ArrayList<>();
				answares.add(Integer.valueOf(request.getParameter("answ1")));
				answares.add(Integer.valueOf(request.getParameter("answ2")));
				answares.add(Integer.valueOf(request.getParameter("answ3")));
				answares.add(Integer.valueOf(request.getParameter("answ4")));
				InterestsBean intBean = new InterestsBean();
				intBean.setAnswares(answares);
				ControllerFacade fac = new ControllerFacade();
				fac.evaluateInterests(intBean);
				JspChangePage changeP = new JspChangePage();
				HomePageServlet hpServ = new HomePageServlet();
				hpServ.loadHomePageUserInfo(request);
				changeP.forwardPage("HomePage.jsp", request, response);
			}
			catch(NumberFormatException e) {
				request.setAttribute(error, "An error has occured, please try again");
				JspChangePage changeP = new JspChangePage();
				changeP.forwardPage("PersonalityTest.jsp", request, response);
			}
			catch(MissingAnswareException e) {
				request.setAttribute(error, e.getMessage());
				JspChangePage changeP = new JspChangePage();
				changeP.forwardPage("PersonalityTest.jsp", request, response);
			}
		}
	}

}
