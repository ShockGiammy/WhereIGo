package logic.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import logic.beans.RentAccomodationBean;
import logic.dao.AccomodationCreator;
import logic.model.AccomodationModel;

public class PostAnAnnouncementController {
	private RentAccomodationBean info;
	private AccomodationModel acc;
	private Random rand;
	
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
			e.printStackTrace();
		}
		bean.setID(this.rand.nextInt());
		AccomodationCreator creator = new AccomodationCreator();
		acc = creator.createAccomodation(info);
	}

	public void show(RentAccomodationBean bean) {	
		acc.getInfo();
		
	}
}