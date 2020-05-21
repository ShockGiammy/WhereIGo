package logic.model;

import logic.beans.RentAccomodationBean;
import logic.dao.AccomodationCreator;
public class AccomodationModel {
	
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
	
	
	public AccomodationModel(RentAccomodationBean bean) {
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
	
	public AccomodationModel() {
	}

	public RentAccomodationBean getInfo() {
		RentAccomodationBean accomodationInfo = new RentAccomodationBean();
		accomodationInfo.setID(id);
		accomodationInfo.setBeds(beds);
		accomodationInfo.setRenterFromDB(renter);
		accomodationInfo.setCityFromDB(city);
		accomodationInfo.setAddressFromDB(address);
		accomodationInfo.setType(type);
		accomodationInfo.setSquareMetres(squareMetres);
		accomodationInfo.setDescriptionFromDB(description);
		if (this.houseImage != null) {
			accomodationInfo.setInputStream(houseImage);
		}
		accomodationInfo.setServices(services);
		return accomodationInfo;
	}
	
	public void createAccomodation() {
		AccomodationCreator dao = new AccomodationCreator();
		dao.createAccomodation(this.getInfo());
	}
	
	public void updateAccomodation() {
		AccomodationCreator dao = new AccomodationCreator();
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
