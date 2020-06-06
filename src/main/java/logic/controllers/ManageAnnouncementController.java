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
import logic.beans.AccommodationBean;
import logic.dao.AccommodationDao;
import logic.model.AccommodationModel;

public class ManageAnnouncementController {
	
	private Random rand;
	protected Logger logger = Logger.getLogger("WIG");
	private AccommodationDao creator;
	private String username;
	private List<AccommodationModel> listOfAccomodation;
	
	public ManageAnnouncementController() {
		this.username = LoggedUser.getUserName();
		listOfAccomodation = Collections.synchronizedList(new ArrayList<>());
		creator = new AccommodationDao();
	}
	
	public void createAccommodation(AccommodationBean bean) {
		AccommodationModel model = new AccommodationModel(bean);
		if (model.getID() != 0) {
			updateMyAccommodation(model);
		}
		else {
			try {
				rand = SecureRandom.getInstanceStrong();
			} catch (NoSuchAlgorithmException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
			model.setID(this.rand.nextInt(100000));
			model.saveAccommodation();
		}
	}
	
	public List<AccommodationBean> retrieveMyAnnouncement() {
		listOfAccomodation = creator.retrieveMyAccommodations(username);
		List<AccommodationBean> listOfBeans = new ArrayList<>();
		for (AccommodationModel model : listOfAccomodation) {
			AccommodationBean bean = model.getInfo();
			listOfBeans.add(bean);
		}
		return listOfBeans;
	}
	
	public void deleteMyAccommodation(long l) {
		creator.delete(l);
	}
	
	public synchronized void updateMyAccommodation(AccommodationModel modelToUpdate) {
		Iterator<AccommodationModel> iterator = listOfAccomodation.iterator();
	    while(iterator.hasNext()) {
	    	AccommodationModel accomodation = iterator.next();
			if (accomodation.getInfo().getID() == modelToUpdate.getID()) {
				listOfAccomodation.remove(accomodation);
				listOfAccomodation.add(modelToUpdate);
			}
		}
	    modelToUpdate.updateAccommodation();
	}
}