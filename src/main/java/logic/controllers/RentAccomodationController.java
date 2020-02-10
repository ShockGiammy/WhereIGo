package logic.controllers;

import java.util.Random;

import logic.beans.RentAccomodationBean;
import logic.dao.AccomodationCreator;
import logic.model.AccomodationModel;

public class RentAccomodationController {
	
	private RentAccomodationBean Info;
	private AccomodationModel Acc;
	
	public RentAccomodationController() {		
	}
	
	public RentAccomodationController(RentAccomodationBean bean) {
		this.Info = bean;
		
	}
	
	//public void modificateAccomodationInfo(int ID) {	
	//	Accomodation.setInfo(Info);
	//}
	
	public void createAccomodation(RentAccomodationBean bean) {
		this.Info = bean;
		Random random = new Random();
		bean.setID(random.nextInt(100));
		AccomodationCreator creator = new AccomodationCreator();
		Acc = creator.createAccomodation(Info);
	}

	public void show(RentAccomodationBean bean) {
		Acc.getInfo();
		
	}
}