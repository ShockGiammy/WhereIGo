package logic.model;

public class LocationModel {
	private String country;
	private String city;
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void getCountry() {
		System.out.println(this.country);
	}
	
	public String getCity() {
		return this.city;
	}
}
