package logic.servlets;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.LoggedUser;
import logic.beans.UserDataBean;
import logic.controllers.ControllerFacade;
import logic.exceptions.DuplicateUsernameException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		ChangePageServlet changeP = new ChangePageServlet();
		if(request.getParameter("log") != null) {
			servLogIn(request, response, changeP);
		}
		else if(request.getParameter("reg") != null){
			changeP.forwardPage("Registration.jsp", request, response);
		}
		else if(request.getParameter("regnow") != null) {
			servRegister(request,  response,  changeP);
		}
	}
	
	public void servLogIn(HttpServletRequest request, HttpServletResponse response, ChangePageServlet changeP) {
		UserDataBean dataBean = new UserDataBean();
		dataBean.setUserName(request.getParameter("username"));
		dataBean.setPsw(request.getParameter("password"));
		ControllerFacade facCtrl = new ControllerFacade();
		int ret = facCtrl.checkLogIn(dataBean);
		if(ret == 1) {
			changeP.loadHomePageUserInfo(request);
			LoggedUser logUsr = new LoggedUser();
			HttpSession session = request.getSession();
			session.setAttribute("image", logUsr.getImage());
			changeP.forwardPage("HomePage.jsp", request, response);
		}
		else {
			request.setAttribute("errorMsg", "Invalid username or password");
			changeP.forwardPage("Login.jsp", request, response);
		}
	}
	
	public void servRegister(HttpServletRequest request, HttpServletResponse response, ChangePageServlet changeP) {
		UserDataBean dataBean = new UserDataBean();
		dataBean.setName(request.getParameter("name"));
		dataBean.setSurname(request.getParameter("surname"));
		dataBean.setDateOfBirth(request.getParameter("dateofb"));
		dataBean.setGender(request.getParameter("gender"));
		dataBean.setType(request.getParameter("type"));
		dataBean.setUserName(request.getParameter("username"));
		dataBean.setPsw(request.getParameter("password"));
		retreiveImage(request, dataBean);
		ControllerFacade facCtrl = new ControllerFacade();
		try {
			facCtrl.insertNewUser(dataBean);
			changeP.loadHomePageUserInfo(request);
			changeP.forwardPage("HomePage.jsp", request, response);
		} catch (DuplicateUsernameException e) {
			request.setAttribute("errorMsg", "Username not available");
			changeP.forwardPage("Registration.jsp", request, response);
		}
	}
	
	private void retreiveImage(HttpServletRequest request, UserDataBean dataBean) {
		if(request.getParameter("prof64Image") != null) {
			byte[] decodedImg = Base64.getDecoder().decode(request.getParameter("prof64Image"));
			String listingFolder = System.getProperty("user.dir");
			File tempFile = null;
			try {
				tempFile = File.createTempFile("output", ".tmp", new File(listingFolder));
				tempFile.deleteOnExit();
			} catch (IOException e) {
				Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
			}
			try (
					FileOutputStream fos = new FileOutputStream(tempFile);
					) {
				fos.write(decodedImg);
			} catch (IOException e) {
				Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
			}
			dataBean.setUsrImage(tempFile);
		}
	}
}