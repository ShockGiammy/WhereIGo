package logic.controllers;

import logic.Accomodation;
import logic.AccomodationCreator;
import logic.beans.RentAccomodationBean;

public class RentAccomodationController {
	
	private RentAccomodationBean Info;
	private Accomodation Acc;
	
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
		AccomodationCreator creator = new AccomodationCreator();
		Acc = creator.createAccomodation(Info);
	}

	public void show(RentAccomodationBean bean) {
		Acc.getInfo();
		
	}
}