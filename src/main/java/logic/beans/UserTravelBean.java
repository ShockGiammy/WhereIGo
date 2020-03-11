package logic.beans;

import java.time.LocalDate;

public class UserTravelBean {
	private LocalDate firstDay;
	private LocalDate lastDay;
	private String cityOfDep;
	private String cityOfArr;
	private Float cost;
	
	public void setFirstDay(LocalDate firstDay) {
		this.firstDay = firstDay;
	}
	
	public void setLastDay(LocalDate lastDay) {
		this.lastDay = lastDay;
	}
	
	public void setDepCity(String depCity) {
		this.cityOfDep = depCity;
	}
	
	public void setArrCity(String arrCity) {
		this.cityOfArr = arrCity;
	}
	
	public void setCost(float money) {
		this.cost = money;
	}
	
	public LocalDate getFirstDay() {
		return this.firstDay;
	}
	
	public LocalDate getLastDay() {
		return this.lastDay;
	}
	
	public String getCityOfDep(){
		return this.cityOfDep;
	}
	
	public String getCityOfArr() {
		return this.cityOfArr;
	}
	
	public float getCost() {
		return this.cost;
	}
}
