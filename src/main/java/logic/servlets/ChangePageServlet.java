package logic.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.beans.GroupBean;
import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;
import logic.controllers.ControllerFacade;

public class ChangePageServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void forwardPage(String jspPage, HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher rd = request.getRequestDispatcher(jspPage);
		try {
			rd.forward(request, response);
		}catch(ServletException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "IOException");
		}
	}
	
	public void loadHomePageUserInfo(HttpServletRequest request) {
		ControllerFacade facCtrl = new ControllerFacade();
		List<UserTravelBean> travBeanList = new ArrayList<>();
		List<GroupBean> gBeanList = new ArrayList<>();
		List<UserDataBean> dBeanList = new ArrayList<>();
		facCtrl.getTravHomePageDatas(travBeanList, gBeanList, dBeanList);
		request.setAttribute("travels", travBeanList);
		request.setAttribute("groups", gBeanList);
		request.setAttribute("users", dBeanList);
	}
}
