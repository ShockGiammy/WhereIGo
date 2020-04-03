package logic.servlets;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;
import logic.controllers.ControllerFacade;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		if(req.getParameter("log") != null) {
			UserDataBean dataBean = new UserDataBean();
			dataBean.setUserName(req.getParameter("username"));
			dataBean.setPsw(req.getParameter("password"));
			ControllerFacade facCtrl = new ControllerFacade();
			int ret = facCtrl.checkLogIn(dataBean);
			if(ret == 1) {
				List<UserTravelBean> travBeanList = new ArrayList<>();
				facCtrl.getBookedTickets(travBeanList);
				req.setAttribute("travels", travBeanList);
				RequestDispatcher rd = req.getRequestDispatcher("HomePage.jsp");
				ChangePageServlet change = new ChangePageServlet();
				change.forwardPage(rd, req, resp);
			}
		}
		else if(req.getParameter("reg") != null){
			RequestDispatcher rd = req.getRequestDispatcher("Registration.jsp");
			ChangePageServlet change = new ChangePageServlet();
			change.forwardPage(rd, req, resp);
		}
	}
}