package logic.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Logger;
import java.util.logging.Level;

import logic.beans.RentAccomodationBean;
import logic.dao.AccomodationCreator;
import logic.model.AccomodationModel;

public class PostAnAnnouncementController {
	private RentAccomodationBean info;
	private AccomodationModel acc;
	private Random rand;
	protected Logger logger = Logger.getLogger("WIG");
	
	public PostAnAnnouncementController() {		
	}
	
	public PostAnAnnouncementController(RentAccomodationBean bean) {
		this.info = bean;
		
	}
	
	//public void modificateAccomodationInfo(int ID) {	
	//	Accomodation.setInfo(Info);
	//}
	
	public void createAccomodation(RentAccomodationBean bean) {
		this.info = bean;
		try {
			rand = SecureRandom.getInstanceStrong();
		} catch (NoSuchAlgorithmException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		bean.setID(this.rand.nextInt());
		AccomodationCreator creator = new AccomodationCreator();
		acc = creator.createAccomodation(info);
	}

	public void show(RentAccomodationBean bean) {	
		acc.getInfo();
		
	}
}