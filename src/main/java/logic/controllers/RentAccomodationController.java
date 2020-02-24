package logic.controllers;

import logic.beans.RentAccomodationBean;
import logic.dao.AccomodationCreator;
import logic.graphiccontrollers.GraphicControlRentAccomodation;
import logic.model.AccomodationModel;

public class RentAccomodationController {

	private GraphicControlRentAccomodation view;
	private AccomodationCreator dao;
	private RentAccomodationBean bean;
	private AccomodationModel[] accomodation;
	
	public RentAccomodationController() {
		dao = new AccomodationCreator();
		bean = new RentAccomodationBean();
	}
	
	public RentAccomodationBean[] displayAnnouncement() {
		accomodation = dao.queryDB(bean);
		RentAccomodationBean[] listOfBean = new RentAccomodationBean[6];
		for (int i = 0; i <6; i++) {
			if (accomodation[i] != null) {
				listOfBean[i] = accomodation[i].getInfo();
			}
		}
		return listOfBean;
	}
	
	public RentAccomodationBean getDetail(int number) {
		RentAccomodationBean bean = accomodation[number].getInfo();
		return bean;
	}
}

