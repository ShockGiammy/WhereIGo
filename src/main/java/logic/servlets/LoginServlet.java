package logic.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.beans.UserDataBean;
import logic.controllers.ControllerFacade;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		if(request.getParameter("log") != null) {
			UserDataBean dataBean = new UserDataBean();
			dataBean.setUserName(request.getParameter("username"));
			dataBean.setPsw(request.getParameter("password"));
			ControllerFacade facCtrl = new ControllerFacade();
			int ret = facCtrl.checkLogIn(dataBean);
			if(ret == 1) {
				ChangePageServlet change = new ChangePageServlet();
				change.loadHomePageUserInfo(request);
				change.forwardPage("HomePage.jsp", request, response);
			}
		}
		else if(request.getParameter("reg") != null){
			ChangePageServlet change = new ChangePageServlet();
			change.forwardPage("Registration.jsp", request, response);
		}
	}
}