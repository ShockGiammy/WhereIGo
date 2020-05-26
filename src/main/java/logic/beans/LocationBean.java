package logic.beans;

public class LocationBean {
	private String cityName;
	private String contryName;
	private String descrption;
	private byte[] inputStream;
	
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
}
