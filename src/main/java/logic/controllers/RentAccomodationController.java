package logic.controllers;

import java.util.ArrayList;
import java.util.List;

import logic.beans.RentAccomodationBean;
import logic.dao.AccomodationCreator;
import logic.graphiccontrollers.GraphicControlRentAccomodation;
import logic.model.AccomodationModel;
import logic.view.ErrorPopup;

public class RentAccomodationController {

	private GraphicControlRentAccomodation view;
	private AccomodationCreator dao;
	private List<AccomodationModel> listOfAccomodation;
	
	public RentAccomodationController() {
		dao = new AccomodationCreator();
		listOfAccomodation = new ArrayList<>();
	}
	
	public List<RentAccomodationBean> displayAnnouncement() {
		List<RentAccomodationBean> listOfBean = dao.queryDB();
		if (listOfBean.isEmpty()) {
			ErrorPopup error = new ErrorPopup();
			error.displayLoginError("no accomodation to been shown");
		}
		else {
			for (RentAccomodationBean bean : listOfBean) {
				AccomodationModel accomodation = new AccomodationModel(bean);
				listOfAccomodation.add(accomodation);
			}
		}
		return listOfBean;
	}
}

