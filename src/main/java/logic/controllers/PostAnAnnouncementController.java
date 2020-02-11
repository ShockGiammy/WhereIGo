package logic.controllers;

import java.util.Random;

import logic.beans.RentAccomodationBean;
import logic.dao.AccomodationCreator;
import logic.model.AccomodationModel;

public class PostAnAnnouncementController {
	
	private RentAccomodationBean Info;
	private AccomodationModel Acc;
	
	public PostAnAnnouncementController() {		
	}
	
	public PostAnAnnouncementController(RentAccomodationBean bean) {
		this.Info = bean;
		
	}
	
	//public void modificateAccomodationInfo(int ID) {	
	//	Accomodation.setInfo(Info);
	//}
	
	public void createAccomodation(RentAccomodationBean bean) {
		this.Info = bean;
		Random random = new Random();
		bean.setID(random.nextInt(100));
		System.out.println(bean.getID());
		AccomodationCreator creator = new AccomodationCreator();
		Acc = creator.createAccomodation(Info);
	}

	public void show(RentAccomodationBean bean) {
		Acc.getInfo();
		
	}
}