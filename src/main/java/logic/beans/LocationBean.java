package logic.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class LocationBean {
	private String cityName;
	private String contryName;
	private String descrption;
	private byte[] inputStream;
	private File locImage;
	
	public void setCityName(String city) {
		this.cityName = city;
	}
	
	public void setCountryName(String country) {
		this.contryName = country;
	}
	
	public void setDescription(String descr) {
		this.descrption = descr;
	}
	
	public void setStream(byte[] inputS) {
		this.inputStream = inputS;
	}
	
	public void setImage(File image) {
		this.locImage = image;
	}
	
	public String getCityName() {
		return this.cityName;
	}
	
	public String getCountryName() {
		return this.contryName;
	}
	
	public String getDescription() {
		return this.descrption;
	}
	
	public byte[] getStream() {
		return this.inputStream;
	}
	
	public InputStream getFile() {
		InputStream imageFile = null;
		if (this.locImage != null) {
			try {
				imageFile = new FileInputStream(locImage);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return imageFile;
	}
	
	public long getFileLength() {
		long len = 0;
		if (locImage != null) {
			len = locImage.length();
		}
		return len;
	}
}
