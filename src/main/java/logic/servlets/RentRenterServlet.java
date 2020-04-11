package logic.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.LoggedUser;
import logic.beans.RentAccomodationBean;
import logic.controllers.ControllerFacade;

@WebServlet("/RentRenter")
public class RentRenterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		
		ChangePageServlet changeP = new ChangePageServlet();	
		ControllerFacade facade = new ControllerFacade();
	
		String page = null;
		System.out.println("prova");
		System.out.println(req.getParameter("service"));
		if (req.getParameter("service")!= null) {
			System.out.println("prova2");
			if (req.getParameter("service").equals("Create")) {	
				System.out.println("create1");
				RentAccomodationBean bean = new RentAccomodationBean();
				LoggedUser logUser = new LoggedUser();
				bean.setBeds(req.getParameter("beds"));
				bean.setCity(req.getParameter("city"));
				bean.setAddress(req.getParameter("address"));
				bean.setDescription(req.getParameter("description"));
				bean.setSquareMetres(req.getParameter("squareMetres"));
				bean.setType(req.getParameter("type"));
				bean.setRenter(logUser.getUserName());
				System.out.println("create1.3");
				System.out.println(req.getParameter("houseImage"));
				byte[] decodedImg = Base64.getDecoder().decode(req.getParameter("houseImage"));
				String listingFolder = System.getProperty("user.dir");
				File tempFile = null;
				try {
					tempFile = File.createTempFile("output", ".tmp", new File(listingFolder));
					tempFile.deleteOnExit();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				try (
					FileOutputStream fos = new FileOutputStream(tempFile);
						) {
					fos.write(decodedImg);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				System.out.println("create1.6");
				bean.setHouseImage(tempFile);
				byte[] listOfServices = new byte[4];
				if (req.getParameter("garden").equals("true")) {
					listOfServices[0] = 1;
				}
				else { 
					listOfServices[0] = 0;
				}
				if (req.getParameter("wifi").equals("true")) {
					listOfServices[1] = 1;
				}
				else { 
					listOfServices[1] = 0;
				}
				if (req.getParameter("bathroom").equals("true")) {
					listOfServices[2] = 1;
				}
				else { 
					listOfServices[2] = 0;
				}
				if (req.getParameter("kitchen").equals("true")) {
					listOfServices[3] = 1;
				}
				else { 
					listOfServices[3] = 0;
				}
				System.out.println("create1.8");
				bean.setServices(listOfServices);
				facade.createAccomodation(bean);
				System.out.println("create2");
			}
			else if (req.getParameter("service").equals("Update")) {	
				//href="RentRenter?action=Update&id="<%=bean.getID()%>"
				System.out.println("update");
			}
		}
		else if (req.getParameter("action")!= null && req.getParameter("action").equalsIgnoreCase("Delete")) {
			facade.deleteMyAccomodation(Integer.parseInt(req.getParameter("id")));
		}
		
		List<RentAccomodationBean> listOfBean = facade.displayMyAnnouncement();
		req.setAttribute("list", listOfBean);
		
		page = "ManageAccomodations.jsp";
		changeP.forwardPage(page, req, resp);	
	}
}
