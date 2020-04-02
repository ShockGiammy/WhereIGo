package logic.servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
	public void doPost(HttpServletRequest request, HttpServletResponse resp) {
		UserDataBean dataBean = new UserDataBean();
		dataBean.setUserName(request.getParameter("username"));
		dataBean.setPsw(request.getParameter("password"));
		ControllerFacade facCtrl = new ControllerFacade();
		int ret = facCtrl.checkLogIn(dataBean);
		if(ret == 1) {
			RequestDispatcher rd = request.getRequestDispatcher("HomePage.jsp");
			try {
				rd.forward(request, resp);
			} catch (ServletException e) {
				Logger.getLogger("WIG").log(Level.SEVERE,"ServletException problem!");
			} catch (IOException e) {
				Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
			}
		}
	}

}