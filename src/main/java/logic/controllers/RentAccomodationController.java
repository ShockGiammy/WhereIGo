package logic.controllers;

import java.util.ArrayList;
import java.util.List;

import logic.beans.AccomodationBean;
import logic.dao.AccomodationDao;
import logic.model.AccomodationModel;

public class RentAccomodationController {

	private AccomodationDao dao;
	private List<AccomodationModel> listOfAccomodation;
	
	public RentAccomodationController() {
		dao = new AccomodationDao();
		listOfAccomodation = new ArrayList<>();
	}
	
	public List<AccomodationBean> retrieveAnnouncement() {
		listOfAccomodation = dao.retrieveAccomodations();
		List<AccomodationBean> listOfBean = new ArrayList<>();
		for (AccomodationModel model : listOfAccomodation) {
			AccomodationBean bean = model.getInfo();
			listOfBean.add(bean);
		}
		return listOfBean;
	}
	
	public void createChat(String renter) {
		ChatController chatController = new ChatController();
		chatController.createChat(renter);
	}
	
}

