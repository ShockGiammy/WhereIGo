package logic.beans;

import java.time.LocalDate;

public class UserTravelBean {
	private String id;
	private String firstDay;
	private String lastDay;
	private String cityOfDep;
	private String cityOfArr;
	private String cost;
	
	public void setId(int ident) {
		this.id = String.valueOf(ident);
	}
	
	public void setFirstDay(LocalDate firstDay) {
		this.firstDay = firstDay.toString();
	}
	
	public void setLastDay(LocalDate lastDay) {
		this.lastDay = lastDay.toString();
	}
	
	public void setDepCity(String depCity) {
		this.cityOfDep = depCity;
	}
	
	public void setArrCity(String arrCity) {
		this.cityOfArr = arrCity;
	}
	
	public void setCost(float money) {
		this.cost = String.valueOf(money);
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getFirstDay() {
		return this.firstDay;
	}
	
	public String getLastDay() {
		return this.lastDay;
	}
	
	public String getCityOfDep(){
		return this.cityOfDep;
	}
	
	public String getCityOfArr() {
		return this.cityOfArr;
	}
	
	public String getCost() {
		return this.cost;
	}
}
