package logic.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.logging.Level;

import logic.LoggedUser;
import logic.beans.RentAccomodationBean;
import logic.dao.AccomodationCreator;
import logic.model.AccomodationModel;

public class ManageAnnouncementController {
	
	private Random rand;
	protected Logger logger = Logger.getLogger("WIG");
	private AccomodationCreator creator;
	private String username;
	private List<AccomodationModel> listOfAccomodation;
	
	public ManageAnnouncementController() {
		LoggedUser logUser = new LoggedUser();
		this.username = logUser.getUserName();
		listOfAccomodation = Collections.synchronizedList(new ArrayList<>());
		creator = new AccomodationCreator();
	}
	
	public void createAccomodation(RentAccomodationBean bean) {
		AccomodationModel model = new AccomodationModel(bean);
		if (model.getID() != 0) {
			updateMyAccomodation(model);
		}
		else {
			try {
				rand = SecureRandom.getInstanceStrong();
			} catch (NoSuchAlgorithmException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
			model.setID(this.rand.nextInt(100000));
			model.createAccomodation();
		}
	}
	
	public List<RentAccomodationBean> displayMyAnnouncement() {
		listOfAccomodation = creator.queryMyAccomodations(username);
		List<RentAccomodationBean> listOfBeans = new ArrayList<>();
		for (AccomodationModel model : listOfAccomodation) {
			RentAccomodationBean bean = model.getInfo();
			listOfBeans.add(bean);
		}
		return listOfBeans;
	}
	
	public void deleteMyAccomodation(long l) {
		creator.delete(l);
	}
	
	public synchronized void updateMyAccomodation(AccomodationModel modelToUpdate) {
		Iterator<AccomodationModel> iterator = listOfAccomodation.iterator();
	    while(iterator.hasNext()) {
	    	AccomodationModel accomodation = iterator.next();
			if (accomodation.getInfo().getID() == modelToUpdate.getID()) {
				listOfAccomodation.remove(accomodation);
				listOfAccomodation.add(modelToUpdate);
			}
		}
	    modelToUpdate.updateAccomodation();
	}
}