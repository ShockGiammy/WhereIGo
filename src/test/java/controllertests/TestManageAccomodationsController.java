package controllertests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import logic.LoggedUser;
import logic.beans.RentAccomodationBean;
import logic.controllers.ControllerFacade;
import logic.exceptions.LengthFieldException;

public class TestManageAccomodationsController {
	
	ControllerFacade facade;
	LoggedUser logUser;

	public TestManageAccomodationsController() {
		facade = new ControllerFacade();
		logUser = new LoggedUser();
	}
	
	@Test
	public void createAccomodationAndCheck() {
		
		LoggedUser.setUserName("Renter");
		
		RentAccomodationBean bean = new RentAccomodationBean();
		bean.setBeds("6");
		try {
			bean.setCity("Rome");
			bean.setAddress("Via Cavour 56");
			bean.setDescription("Newly renovated flat in a beautiful Victorian building just a minute away from the tube and the high street.\r\n" + 
					"\r\n" + 
					"The flat is situated on the top floor of a charming building and has an open plan kitchen living room, 2 bedrooms with double beds and a brand new marble bathroom.");
			bean.setRenter(this.logUser.getUserName());
		} catch (LengthFieldException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, ()-> e.getMessage());
		}
		bean.setSquareMetres("> 60");
		bean.setType("apartment");
		facade.createAccomodation(bean);
		
		List<RentAccomodationBean> listOfBean = facade.displayMyAnnouncement();
		if (listOfBean.isEmpty()) {
			Logger.getLogger("WIG").log(Level.SEVERE, "No accomodation has to been shown");
		}
		else {
			for (RentAccomodationBean beanReturned : listOfBean) {
				assertEquals(bean.getDescription(), beanReturned.getDescription());
				/*assertEquals(bean.getCity(), beanReturned.getCity());
				assertEquals(bean.getAddress(), beanReturned.getAddress());
				assertEquals(bean.getBeds(), beanReturned.getBeds());
				assertEquals(bean.getType(), beanReturned.getType());
				assertEquals(bean.getSquareMetres(), beanReturned.getSquareMetres());*/
			}
		}
	}
	
	@Test
	public void updateAndDeleteAccomodation() {
		List<RentAccomodationBean> listOfBeanToUpdate = facade.displayMyAnnouncement();
		if (listOfBeanToUpdate.isEmpty()) {
			Logger.getLogger("WIG").log(Level.SEVERE, "No accomodation has to been shown");
		}
		else {
			File imageHouse = new File(System.getProperty("user.dir")+"/src/main/resources/images/houseExample.png");
			RentAccomodationBean beanReturned = listOfBeanToUpdate.get(0);
			beanReturned.setHouseImage(imageHouse);
			facade.createAccomodation(beanReturned);
		}
		List<RentAccomodationBean> listOfBeanUpdated = facade.displayMyAnnouncement();
		if (listOfBeanToUpdate.isEmpty()) {
			Logger.getLogger("WIG").log(Level.SEVERE, "No accomodation has to been shown");
		}
		else {
			for (RentAccomodationBean beanReturned : listOfBeanUpdated) {
				for (RentAccomodationBean beanUpdated : listOfBeanToUpdate) {			
					if (beanUpdated.getID() == beanReturned.getID()) {
						assertEquals(new String(Base64.getEncoder().encodeToString(beanUpdated.getHouseImage())), new String(Base64.getEncoder().encodeToString(beanReturned.getHouseImage())));
					}
				}
				facade.deleteMyAccomodation(beanReturned.getID());
			}
		}
	}
}
