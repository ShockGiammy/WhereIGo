package logic.controllers;

import logic.beans.RentAccomodationBean;
import logic.dao.AccomodationCreator;
import logic.graphiccontrollers.GraphicControlRentAccomodation;
import logic.model.AccomodationModel;
import logic.view.ErrorPopup;

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
		if (accomodation[0] == null) {
			ErrorPopup error = new ErrorPopup();
			error.displayLoginError("no accomodation to been shown");
		}
		else {
			for (int i = 0; i <6; i++) {
				if (accomodation[i] != null) {
					listOfBean[i] = accomodation[i].getInfo();
				}
			}
		}
		return listOfBean;
	}
	
	public RentAccomodationBean getDetail(int number) {
		return accomodation[number].getInfo();
	}
}

