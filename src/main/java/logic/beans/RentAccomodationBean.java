package logic.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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
	private File houseImage;
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

	public File getHouseImage() {
		return houseImage;
	}
	
	public InputStream getInputFile() {
		InputStream imageInputFile = null;
		try {
			imageInputFile = new FileInputStream(houseImage);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return imageInputFile;
	}
	
	public long getFileLength() {
		return houseImage.length();
	}

	public void setHouseImage(File houseImage) {
		this.houseImage = houseImage;
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