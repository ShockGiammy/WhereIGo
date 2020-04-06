package logic.controllers;

import java.util.ArrayList;
import java.util.List;

import logic.beans.RentAccomodationBean;
import logic.dao.AccomodationCreator;
import logic.model.AccomodationModel;

public class RentAccomodationController {

	private AccomodationCreator dao;
	private List<AccomodationModel> listOfAccomodation;
	
	public RentAccomodationController() {
		dao = new AccomodationCreator();
		listOfAccomodation = new ArrayList<>();
	}
	
	public List<RentAccomodationBean> displayAnnouncement() {
		List<RentAccomodationBean> listOfBean = dao.queryDB();		
		for (RentAccomodationBean bean : listOfBean) {
			AccomodationModel accomodation = new AccomodationModel(bean);
			listOfAccomodation.add(accomodation);
		}
		return listOfBean;
	}
	
	public void createChat(String renter) {
		ChatController chatController = new ChatController();
		chatController.createChat(renter);
	}
	
}

