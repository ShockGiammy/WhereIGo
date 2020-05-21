package logic.beans;

import java.time.LocalDate;

import logic.exceptions.NullValueException;

public class UserTravelBean {
	private String id;
	private String firstDay;
	private String lastDay;
	private String cityOfDep;
	private String cityOfArr;
	private String cost;
	
	public UserTravelBean() {
		
	}
	
	public UserTravelBean(String cityOfArr) {
		this.cityOfArr = cityOfArr;
	}
	
	public UserTravelBean(LocalDate firstDay, LocalDate lastDay, String cityOfDep, String cityOfArr) {
		this.firstDay = firstDay.toString();
		this.lastDay = lastDay.toString();
		this.cityOfDep = cityOfDep;
		this.cityOfArr = cityOfArr;
	}
	
	public UserTravelBean(String firstDay, String lastDay, String cityOfDep, String cityOfArr) {
		this.firstDay = firstDay;
		this.lastDay = lastDay;
		this.cityOfDep = cityOfDep;
		this.cityOfArr = cityOfArr;
	}
	
	public void setId(int ident) {
		this.id = String.valueOf(ident);
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setFirstDay(LocalDate firstDay) throws NullValueException {
		if(firstDay == null) {
			throw new NullValueException("Insert a valid departure date");
		}
		this.firstDay = firstDay.toString();
	}
	
	public void setFirstDay(String firstDay) throws NullValueException {
		if(firstDay.equalsIgnoreCase("")) {
			throw new NullValueException("Insert a valid return date");
		}
		this.firstDay = firstDay;
	}
	
	public void setLastDay(LocalDate lastDay) throws NullValueException {
		if(lastDay == null) {
			throw new NullValueException("Insert a valid return date");
		}
		this.lastDay = lastDay.toString();
	}
	
	public void setLastDay(String lastDay) throws NullValueException {
		if(lastDay.equalsIgnoreCase("")) {
			throw new NullValueException("Insert a valid departure date");
		}
		this.lastDay = lastDay;
	}
	
	public void setDepCity(String depCity) throws NullValueException {
		if(depCity == null || depCity.equalsIgnoreCase("")) {
			throw new NullValueException("Insert a valid departure city");
		}
		this.cityOfDep = depCity;
	}
	
	public void setArrCity(String arrCity) throws NullValueException {
		if(arrCity == null || arrCity.equalsIgnoreCase("")) {
			throw new NullValueException("Insert a valid arrive city");
		}
		this.cityOfArr = arrCity;
	}
	
	public void setCost(float money) {
		this.cost = String.valueOf(money);
	}
	
	public void setCost(String money) {
		this.cost = money;
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
