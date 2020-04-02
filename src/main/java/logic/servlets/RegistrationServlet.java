package logic.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.beans.UserDataBean;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public UserDataBean dataBean;
	
	public RegistrationServlet() {
		this.dataBean = new UserDataBean();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp ) {
		
	}
}
