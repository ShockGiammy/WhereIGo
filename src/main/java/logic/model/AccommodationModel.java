package logic.model;

import logic.beans.AccommodationBean;
import logic.dao.AccommodationDao;
public class AccommodationModel {
	
	private String renter;
	private long id;
	private String beds;
	private String city;
	private String address;
	private String type;
	private String squareMetres;
	private String description;
	private byte[] houseImage;
	private byte[] services;
	
	
	public AccommodationModel(AccommodationBean bean) {
		id = bean.getID();
		beds = bean.getBeds();
		renter = bean.getRenter();
		city = bean.getCity();
		address = bean.getAddress();
		type = bean.getType();
		squareMetres = bean.getSquareMetres();
		description = bean.getDescription();
		if (bean.getHouseImage() != null) {
			houseImage = bean.getHouseImage();
		}
		services = new byte[4];
		services = bean.getServices();
	}
	
	public AccommodationModel() {
	}

	public AccommodationBean getInfo() {
		AccommodationBean accommodationInfo = new AccommodationBean();
		accommodationInfo.setID(id);
		accommodationInfo.setBeds(beds);
		accommodationInfo.setRenterFromDB(renter);
		accommodationInfo.setCityFromDB(city);
		accommodationInfo.setAddressFromDB(address);
		accommodationInfo.setType(type);
		accommodationInfo.setSquareMetres(squareMetres);
		accommodationInfo.setDescriptionFromDB(description);
		if (this.houseImage != null) {
			accommodationInfo.setInputStream(houseImage);
		}
		accommodationInfo.setServices(services);
		return accommodationInfo;
	}
	
	public void saveAccommodation() {
		AccommodationDao dao = new AccommodationDao();
		dao.createAccommodation(this.getInfo());
	}
	
	public void updateAccommodation() {
		AccommodationDao dao = new AccommodationDao();
		dao.update(this.getInfo());
	}
	
	public void setID(long id) {
		this.id = id;
	}
	
	public long getID() {
		return this.id;
	}
	
	public void setBeds(String numBeds) {
		this.beds= numBeds;	
	}
	
	public void setRenter(String renter) {
		this.renter = renter;
	}
	
	public void setCity(String citta) {
		this.city = citta;
	}

	public void setAddress(String indirizzo) {
		this.address = indirizzo;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSquareMetres(String squareMetres) {
		this.squareMetres = squareMetres;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setServices(byte[] listOfServices) {
		this.services = new byte[4];
		this.services = listOfServices;
	}

	public void setHouseImageBytes(byte[] input) {
		this.houseImage = input;
	}
}
