package logic.model;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javafx.scene.image.Image;
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
		services = bean.getServices();
		
		System.out.println("model "+ID);
		System.out.println(beds);
		System.out.println(renter);
		System.out.println(city);
		System.out.println(address);
	}

	public void getInfo() {
		RentAccomodationBean accomodationInfo = new RentAccomodationBean();
		accomodationInfo.setID(ID);
		accomodationInfo.setBeds(beds);
		accomodationInfo.setRenter(renter);
	}
}
