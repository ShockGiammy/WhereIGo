package logic.model;

public class LocationModel {
	private String country;
	private String city;
	private String description;
	private byte[] locPhoto;
	
	public void setAll(String country, String description, byte[] photo) {
		this.country = country;
		this.description = description;
		this.locPhoto = photo;
	}
	
	public void setCity(String city) {
		this.city = city;
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
