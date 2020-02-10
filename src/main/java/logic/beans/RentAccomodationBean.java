package logic.beans;

import javafx.scene.image.Image;

public class RentAccomodationBean {
	
	//private renter;
	private int ID;
	private String beds;
	private String renter;
	private String city;
	private String address;
	private String type;
	private String squareMetres;
	private String description;
	private Image houseImage;
	private byte[] services;
	
	
	
	public void setBeds(String numBeds) {
		this.beds= numBeds;	
	}
	
	public void setRenter(String renter) {
		this.renter = renter;
	}
	public void setID(int Id) {
		this.ID = Id;
	}

	public String getBeds() {
		return this.beds;	
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

	public String getSquareMetres() {
		return squareMetres;
	}

	public void setSquareMetres(String squareMetres) {
		this.squareMetres = squareMetres;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@SuppressWarnings("exports")
	public Image getHouseImage() {
		return houseImage;
	}

	public void setHouseImage(@SuppressWarnings("exports") Image image) {
		this.houseImage = image;
	}

	public byte[] getServices() {
		return services;
	}

	public void setServices(byte[] listOfServices) {
		this.services = listOfServices;
	}

	public int getID() {
		return ID;
	}
}