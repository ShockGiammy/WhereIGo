package logic.controllers;

import java.util.ArrayList;
import java.util.List;

import logic.beans.AccommodationBean;
import logic.dao.AccommodationDao;
import logic.model.AccommodationModel;

public class RentAccommodationController {

	private AccommodationDao dao;
	private List<AccommodationModel> listOfAccommodation;
	
	public RentAccommodationController() {
		dao = new AccommodationDao();
		listOfAccommodation = new ArrayList<>();
	}
	
	public List<AccommodationBean> retrieveAnnouncement() {
		listOfAccommodation = dao.retrieveAccommodations();
		List<AccommodationBean> listOfBean = new ArrayList<>();
		for (AccommodationModel model : listOfAccommodation) {
			AccommodationBean bean = model.getInfo();
			listOfBean.add(bean);
		}
		return listOfBean;
	}
	
	public void createChat(String renter) {
		ChatController chatController = new ChatController();
		chatController.createChat(renter);
	}
	
}

