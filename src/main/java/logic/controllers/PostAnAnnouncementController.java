package logic.controllers;

import java.util.Random;

import logic.beans.RentAccomodationBean;
import logic.dao.AccomodationCreator;
import logic.model.AccomodationModel;

public class PostAnAnnouncementController {
	
	private RentAccomodationBean info;
	private AccomodationModel acc;
	
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
		Random random = new Random();
		bean.setID(random.nextInt(100));
		AccomodationCreator creator = new AccomodationCreator();
		acc = creator.createAccomodation(info);
	}

	public void show(RentAccomodationBean bean) {	
		acc.getInfo();
		
	}
}