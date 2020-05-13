package logic.servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.beans.InterestsBean;
import logic.controllers.ControllerFacade;

@WebServlet("/PersonalityTestServlet")
public class PersonalityTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("evalPers") != null) {
			try {
				int[] answares = new int[4];
				answares[0] = Integer.valueOf(request.getParameter("answ1"));
				answares[1] = Integer.valueOf(request.getParameter("answ2"));
				answares[2] = Integer.valueOf(request.getParameter("answ3"));
				answares[3] = Integer.valueOf(request.getParameter("answ4"));
				InterestsBean intBean = new InterestsBean();
				intBean.setAnswares(answares);
				ControllerFacade fac = new ControllerFacade();
				fac.evaluateInterests(intBean);
				ChangePageServlet changeP = new ChangePageServlet();
				HomePageServlet hpServ = new HomePageServlet();
				hpServ.loadHomePageUserInfo(request);
				changeP.forwardPage("HomePage.jsp", request, response);
			}catch(NumberFormatException e) {
				Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
			}
		}
	}

}
