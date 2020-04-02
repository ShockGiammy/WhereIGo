package logic.servlets;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.beans.UserDataBean;
import logic.controllers.ControllerFacade;
import logic.exceptions.DuplicateUsernameException;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp ) {
		UserDataBean dataBean = new UserDataBean();
		dataBean.setName(req.getParameter("name"));
		dataBean.setSurname(req.getParameter("surname"));
		dataBean.setDateOfBirth(req.getParameter("dateofbirth"));
		dataBean.setGender(req.getParameter("gender"));
		dataBean.setType(req.getParameter("typeOfUsr"));
		dataBean.setUserName(req.getParameter("username"));
		dataBean.setPsw(req.getParameter("paswd"));
		ControllerFacade facCtrl = new ControllerFacade();
		try {
			facCtrl.insertNewUser(dataBean);
		} catch (DuplicateUsernameException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
	}
}
