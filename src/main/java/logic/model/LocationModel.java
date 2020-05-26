package logic.model;

import logic.beans.LocationBean;

public class LocationModel {
	private String country;
	private String city;
	private String description;
	private byte[] locPhoto;
	
	public void setCityByBean(LocationBean locBean) {
		this.city = locBean.getCityName();
	}
	
	public void setAll(String country, String description, byte[] photo) {
		this.country = country;
		this.description = description;
		this.locPhoto = photo;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public byte[] getPhoto() {
		return this.locPhoto;
	}
}
