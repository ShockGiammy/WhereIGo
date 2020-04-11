package logic.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.logging.Level;

import logic.LoggedUser;
import logic.beans.RentAccomodationBean;
import logic.dao.AccomodationCreator;
import logic.model.AccomodationModel;

public class ManageAnnouncementController {
	
	private RentAccomodationBean info;
	private Random rand;
	protected Logger logger = Logger.getLogger("WIG");
	private AccomodationCreator creator;
	private String username;
	private List<AccomodationModel> listOfAccomodation;
	
	public ManageAnnouncementController() {
		LoggedUser logUser = new LoggedUser();
		this.username = logUser.getUserName();
		listOfAccomodation = new ArrayList<>();
		creator = new AccomodationCreator();
	}
	
	public ManageAnnouncementController(RentAccomodationBean bean) {
		this.info = bean;		
	}
	
	public void createAccomodation(RentAccomodationBean bean) {
		this.info = bean;
		if (bean.getID() != 0) {
			updateMyAccomodation(bean);
		}
		else {
			try {
				rand = SecureRandom.getInstanceStrong();
			} catch (NoSuchAlgorithmException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
			bean.setID(this.rand.nextInt(100000));
			creator.createAccomodation(info);
		}
	}
	
	public List<RentAccomodationBean> displayMyAnnouncement() {
		List<RentAccomodationBean> listOfBeans = creator.queryMyAccomodations(username);
		for (RentAccomodationBean bean : listOfBeans) {
			AccomodationModel accomodation = new AccomodationModel(bean);
			listOfAccomodation.add(accomodation);
		}
		return listOfBeans;
	}
	
	public void deleteMyAccomodation(long l) {
		creator.delete(l);
	}
	
	public void updateMyAccomodation(RentAccomodationBean beanToUpdate) {
		for (AccomodationModel accomodation : listOfAccomodation) {
			if (accomodation.getInfo().getID() == beanToUpdate.getID()) {
				listOfAccomodation.remove(accomodation);
				AccomodationModel accomodationUpdated = new AccomodationModel(beanToUpdate);
				listOfAccomodation.add(accomodationUpdated);
			}
		}
		creator.update(beanToUpdate);
	}
}