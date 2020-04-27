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
		listOfAccomodation = dao.queryDB();
		List<RentAccomodationBean> listOfBean = new ArrayList<>();
		for (AccomodationModel model : listOfAccomodation) {
			RentAccomodationBean bean = model.getInfo();
			listOfBean.add(bean);
		}
		return listOfBean;
	}
	
	public void createChat(String renter) {
		ChatController chatController = new ChatController();
		chatController.createChat(renter);
	}
	
}

