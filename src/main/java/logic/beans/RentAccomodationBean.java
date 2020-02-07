package logic.beans;

import javafx.scene.image.Image;

public class RentAccomodationBean {
	
	//private renter;
	private int ID;
	private String beds;
	private int renterID;
	private String city;
	private String address;
	private String type;
	private int squareMetres;
	private String description;
	private Image houseImage;
	private int[] services;
	
	public void setBeds(String numBeds) {
		this.beds= numBeds;	
	}
	
	public void setRenter(int renter) {
		this.renterID = renter;
	}
	public void setID(int Id) {
		this.renterID = Id;
	}

	public String getBeds() {
		return this.beds;	
	}
	
	public int getID() {
		return this.ID;
	}
	
	public int getRenter() {
		return this.renterID;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String citta) {
		this.city = citta;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String indirizzo) {
		this.address = indirizzo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSquareMetres() {
		return squareMetres;
	}

	public void setSquareMetres(int squareMetres) {
		this.squareMetres = squareMetres;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Image getHouseImage() {
		return houseImage;
	}

	public void setHouseImage(Image image) {
		this.houseImage = image;
	}

	public int[] getServices() {
		return services;
	}

	public void setServices(int[] listOfServices) {
		this.services = listOfServices;
	}
}