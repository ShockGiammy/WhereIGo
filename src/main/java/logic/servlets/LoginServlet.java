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
import logic.UserType;
import logic.beans.UserDataBean;
import logic.controllers.ControllerFacade;
import logic.exceptions.DuplicateUsernameException;
import logic.exceptions.LengthFieldException;
import logic.exceptions.NullValueException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String errMsg = "errorMsg";
	private static String defPage = "Registration.jsp";
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		JspChangePage changeP = new JspChangePage();
		if(request.getParameter("log") != null) {
			servLogIn(request, response, changeP);
		}
		else if(request.getParameter("reg") != null){
			changeP.forwardPage(defPage, request, response);
		}
		else if(request.getParameter("regnow") != null) {
			servRegister(request,  response,  changeP);
		}
	}
	
	public void servLogIn(HttpServletRequest request, HttpServletResponse response, JspChangePage changeP) {
		UserDataBean dataBean = new UserDataBean(request.getParameter("username"), request.getParameter("password"));
		ControllerFacade facCtrl = new ControllerFacade();
		int ret = facCtrl.checkLogIn(dataBean);
		if(ret == 1) {
			homePageCall(request, response, changeP);
		}
		else {
			request.setAttribute(errMsg, "Invalid username or password");
			changeP.forwardPage("Login.jsp", request, response);
		}
	}
	
	public void servRegister(HttpServletRequest request, HttpServletResponse response, JspChangePage changeP) {
		UserDataBean dataBean = new UserDataBean();
		setUserDatas(request, dataBean);
		if(request.getAttribute(errMsg) != null) {
			changeP.forwardPage(defPage, request, response);
		}
		else {
			retreiveImage(request, dataBean);
			if(request.getAttribute(errMsg) != null) {
				changeP.forwardPage(defPage, request, response);
			}
			else {
				ControllerFacade facCtrl = new ControllerFacade();
				try {
					facCtrl.insertNewUser(dataBean);
					homePageCall(request, response, changeP);
				} 
				catch (DuplicateUsernameException e) {
					request.setAttribute(errMsg, "Username not available");
					changeP.forwardPage(defPage, request, response);
				}
			}
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
			}catch (IOException e) {
				Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
			}
			try (
					FileOutputStream fos = new FileOutputStream(tempFile);
					) {
				fos.write(decodedImg);
			}catch (IOException e) {
				Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
			}
			try {
				dataBean.setUsrImage(tempFile);
			}catch (NullValueException e) {
				request.setAttribute(errMsg, e.getNullExcMsg());
			}
		}
	}
	
	private void setUserDatas(HttpServletRequest request, UserDataBean dataBean) {
		try {
			dataBean.setName(request.getParameter("name"));
			dataBean.setSurname(request.getParameter("surname"));
			dataBean.setUserName(request.getParameter("username"));
			dataBean.setPsw(request.getParameter("password"));
			dataBean.setDateOfBirth(request.getParameter("dateofb"));
    	}catch(LengthFieldException e) {
    		request.setAttribute(errMsg, e.getMsg());
    	}catch(NullValueException e) {
    		request.setAttribute(errMsg, e.getNullExcMsg());
    	}
		dataBean.setGender(request.getParameter("gender"));
		dataBean.setType(request.getParameter("type"));
	}
	
	private void homePageCall(HttpServletRequest request, HttpServletResponse response, JspChangePage changeP) {
		HomePageServlet hpServ = new HomePageServlet();
		hpServ.loadHomePageUserInfo(request);
		LoggedUser logUsr = new LoggedUser();
		HttpSession session = request.getSession();
		session.setAttribute("image", logUsr.getImage());
		session.setAttribute("usrname", logUsr.getUserName());
		if (logUsr.getUserType() == UserType.RENTER) {
			changeP.forwardPage("RenterHomePage.jsp", request, response);
		}
		else {
			changeP.forwardPage("HomePage.jsp", request, response);
		}
	}
}