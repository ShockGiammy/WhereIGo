package logic.model;

import logic.beans.RentAccomodationBean;
public class AccomodationModel {
	
	private String renter;
	private int ID;
	private String beds;
	private String city;
	private String address;
	private String type;
	private String squareMetres;
	private String description;
	private byte[] houseImage;
	private byte[] services;
	
	
	public AccomodationModel(RentAccomodationBean bean) {
		ID = bean.getID();
		beds = bean.getBeds();
		renter = bean.getRenter();
		city = bean.getCity();
		address = bean.getAddress();
		type = bean.getType();
		squareMetres = bean.getSquareMetres();
		description = bean.getDescription();
		houseImage = bean.getHouseImage();
		services = new byte[4];
		services = bean.getServices();
	}

	public RentAccomodationBean getInfo() {
		RentAccomodationBean accomodationInfo = new RentAccomodationBean();
		accomodationInfo.setID(ID);
		accomodationInfo.setBeds(beds);
		accomodationInfo.setRenter(renter);
		accomodationInfo.setCity(city);
		accomodationInfo.setAddress(address);
		accomodationInfo.setType(type);
		accomodationInfo.setSquareMetres(squareMetres);
		accomodationInfo.setDescription(description);
		accomodationInfo.setInputStream(houseImage);
		accomodationInfo.setServices(services);
		return accomodationInfo;
	}
}
