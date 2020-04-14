package logic.servlets;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import logic.LoggedUser;
import logic.beans.UserDataBean;
import logic.controllers.ControllerFacade;
import logic.exceptions.DuplicateUsernameException;

@WebServlet("/LoginServlet")
@MultipartConfig
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
		//dataBean.setUsrImage(retreiveImage(request));
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
	
	private InputStream retreiveImage(HttpServletRequest request) {
		try {
			Part filePart = request.getPart("photo");
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			InputStream is = filePart.getInputStream();
			//Files.copy(is, fileName, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e1) {
			Logger.getLogger("WIG").log(Level.SEVERE, e1.getMessage());
		} catch (ServletException e1) {
			Logger.getLogger("WIG").log(Level.SEVERE, "Servlet exception while parsing file\n");
		}
		return null;
	}
}