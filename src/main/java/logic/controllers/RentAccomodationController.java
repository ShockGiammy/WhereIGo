package logic.controllers;

import logic.beans.RentAccomodationBean;
import logic.dao.AccomodationCreator;
import logic.graphiccontrollers.GraphicControlRentAccomodation;
import logic.model.AccomodationModel;

public class RentAccomodationController {

	private GraphicControlRentAccomodation view;
	private AccomodationCreator dao;
	private RentAccomodationBean bean;
	private AccomodationModel accomodation;
	
	public RentAccomodationController() {
		dao = new AccomodationCreator();
		bean = new RentAccomodationBean();
	}
	
	public RentAccomodationBean displayAnnouncement() {
		bean = dao.queryDB(bean);
		return bean;
	}
}

