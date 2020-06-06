package logic.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.LoggedUser;
import logic.beans.AccommodationBean;
import logic.controllers.ControllerFacade;
import logic.exceptions.LengthFieldException;

@WebServlet("/RentRenter")
public class RentRenterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SERVICE = "service";
	private static final String ACTION = "action";
	private static final String ERROR = "ErrorPage.jsp";
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		
		JspChangePage changeP = new JspChangePage();	
		ControllerFacade facade = new ControllerFacade();
	
		AccommodationBean beanToUpdate = null;
		if (req.getParameter(SERVICE)!= null) {
			if (req.getParameter(SERVICE).equals("Create")) {
				AccommodationBean bean = setAccommodationInfo(req, resp, changeP);
				facade.createAccommodation(bean);
			}
			else if (req.getParameter(SERVICE).equals("Update")) {
				AccommodationBean bean = setAccommodationInfo(req, resp, changeP);
				bean.setID(Integer.parseInt(req.getParameter("id")));
				facade.createAccommodation(bean);
			}
		}
		List<AccommodationBean> listOfBean = facade.retrieveMyAnnouncement();
		if (req.getParameter(ACTION)!= null) {
			if(req.getParameter(ACTION).equalsIgnoreCase("Delete")) {
				facade.deleteMyAccommodation(Integer.parseInt(req.getParameter("id")));
			}
			else if (req.getParameter(ACTION).equalsIgnoreCase("requestToUpdate")) {
				for (AccommodationBean bean : listOfBean) {
					if (bean.getID() == Integer.parseInt(req.getParameter("id"))) {
						beanToUpdate = bean;
					}
				}
			}
		}
		req.setAttribute("bean", beanToUpdate);
		
		req.setAttribute("list", listOfBean);
		
		String page = "ManageAccommodations.jsp";
		changeP.forwardPage(page, req, resp);	
	}
	
	public AccommodationBean setAccommodationInfo(HttpServletRequest req, HttpServletResponse resp, JspChangePage changeP) {
		AccommodationBean bean = new AccommodationBean();
		Logger logger = Logger.getLogger("WIG");
		bean.setBeds(req.getParameter("beds"));
		try {
			bean.setCity(req.getParameter("city"));
			bean.setAddress(req.getParameter("address"));
			bean.setDescription(req.getParameter("description"));
			bean.setRenter(LoggedUser.getUserName());
		} catch (LengthFieldException e) {
			logger.log(Level.SEVERE, e.getMessage());
			String page = ERROR;
			changeP.forwardPage(page, req, resp);
		}
		bean.setSquareMetres(req.getParameter("squareMetres"));
		bean.setType(req.getParameter("type"));	
		if (req.getParameter("houseImage") != null) {
			byte[] decodedImg = Base64.getDecoder().decode(req.getParameter("houseImage"));
			String listingFolder = System.getProperty("user.dir");
			File tempFile = null;
			try {
				tempFile = File.createTempFile("output", ".tmp", new File(listingFolder + "/cache"));
				tempFile.deleteOnExit();
			} catch (IOException e) {
				logger.log(Level.SEVERE, e.getMessage());
				String page = ERROR;
				changeP.forwardPage(page, req, resp);
			}
			try (
					FileOutputStream fos = new FileOutputStream(tempFile);
					) {
				fos.write(decodedImg);
			} catch (IOException e) {
				logger.log(Level.SEVERE, e.getMessage());
				String page = ERROR;
				changeP.forwardPage(page, req, resp);
			}
			bean.setHouseImage(tempFile);
		}
		byte[] listOfServices = new byte[4];
		listOfServices[0] = 0;
		listOfServices[1] = 0;
		listOfServices[2] = 0;
		listOfServices[3] = 0;
		if (req.getParameter("garden").equals("true")) {
			listOfServices[0] = 1;
		}
		if (req.getParameter("wifi").equals("true")) {
			listOfServices[1] = 1;
		}
		if (req.getParameter("bathroom").equals("true")) {
			listOfServices[2] = 1;
		}
		if (req.getParameter("kitchen").equals("true")) {
			listOfServices[3] = 1;
		}
		bean.setServices(listOfServices);
		return bean;
	}
}
