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
	
	public void setFirstDay(String firstDay) {
		this.firstDay = firstDay;
	}
	
	public void setLastDay(LocalDate lastDay) {
		this.lastDay = lastDay.toString();
	}
	
	public void setLastDay(String lastDay) {
		this.lastDay = lastDay;
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
	
	public Integer getId() {
		return Integer.valueOf(this.id);
	}
	
	public String getParsedId() {
		return this.id;
	}
	
	public String getFirstDay() {
		return this.firstDay;
	}
	
	public LocalDate getFirstDayPars() {
		return LocalDate.parse(this.firstDay);
	}
	
	public String getLastDay() {
		return this.lastDay;
	}
	
	public LocalDate getLastDayPars() {
		return LocalDate.parse(this.lastDay);
	}
	
	public String getCityOfDep(){
		return this.cityOfDep;
	}
	
	public String getCityOfArr() {
		return this.cityOfArr;
	}
	
	public Float getCost() {
		return Float.valueOf(this.cost);
	}
	
	public String getParsedCost() {
		return this.cost;
	}
}
