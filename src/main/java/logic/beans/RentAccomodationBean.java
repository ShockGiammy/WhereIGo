package logic.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.exceptions.LengthFieldException;

public class RentAccomodationBean {
	
	private long id;
	private String beds;
	private String renter;
	private String city;
	private String address;
	private String type;
	private String squareMetres;
	private String description;
	private File houseImage;
	private byte[] services;
	private byte[] inputF;
	protected Logger logger = Logger.getLogger("WIG");
	
	public void setBeds(String numBeds) {
		this.beds= numBeds;	
	}
	
	public void setRenter(String renter) throws LengthFieldException {
		if (renter.length() >= 20) {
			throw new LengthFieldException("Renter name too long");
		}
		this.renter = renter;
	}
	
	public void setRenterFromDB(String renter) {
		this.renter = renter;
	}
	
	public String getRenter() {
		return this.renter;
	}
	
	public void setID(long l) {
		this.id = l;
	}

	public String getBeds() {
		return this.beds;	
	}

	public String getCity() {
		return city;
	}

	public void setCity(String citta) throws LengthFieldException {
		if (citta.length() >= 30) {
			throw new LengthFieldException("City name too long");
		}
		this.city = citta;
	}
	
	public void setCityFromDB(String citta) {
		this.city = citta;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String indirizzo) throws LengthFieldException {
		if (indirizzo.length() >= 40) {
			throw new LengthFieldException("Address too long");
		}
		this.address = indirizzo;
	}
	
	public void setAddressFromDB(String indirizzo) {
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

	public void setDescription(String description) throws LengthFieldException {
		if (description.length() >= 4096) {
			throw new LengthFieldException("Description too long");
		}
		this.description = description;
	}
	
	public void setDescriptionFromDB(String description) {
		this.description = description;
	}

	public InputStream getInputFile() {
		InputStream imageInputFile = null;
		if (houseImage != null) {
			try {
				imageInputFile = new FileInputStream(houseImage);
			} catch (FileNotFoundException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
		return imageInputFile;
	}
	
	public File getHouseFile() {
		return this.houseImage;
	}

	public long getFileLength() {
		long len = 0;
		if (houseImage != null) {
			len = houseImage.length();
		}
		return len;
	}
	
	public void setHouseImage(File houseImage) {
		this.houseImage = houseImage;
		if (houseImage != null) {
			try {
				this.inputF = Files.readAllBytes(houseImage.toPath());
			} catch (IOException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
	}
	
	public byte[] getServices() {
		return services;
	}

	public void setServices(byte[] listOfServices) {
		this.services = new byte[4];
		this.services = listOfServices;
	}

	public long getID() {
		return id;
	}
	
	public void setInputStream(byte[] inputS) {
		this.inputF = inputS;
		if (inputS != null) {
			String listingFolder = System.getProperty("user.dir");
			try {
				houseImage = File.createTempFile("output", ".tmp", new File(listingFolder + "/cache"));
				houseImage.deleteOnExit();
			} catch (IOException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
			try (FileOutputStream fileOuputStream = new FileOutputStream(houseImage)){
				fileOuputStream.write(inputS);
			} catch (IOException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
	}


	public byte[] getHouseImage() {
		return inputF;
	}
}