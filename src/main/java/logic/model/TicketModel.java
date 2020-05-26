/* We image that we have only two-way tickets*/
package logic.model;

import java.time.LocalDate;

import logic.beans.UserTravelBean;

public class TicketModel {
	private int id;
	private String departureCity;
	private String arrivalCity;
	private LocalDate departureDate;
	private LocalDate arriveDate;
	private float cost;
	
	public void setDepAndArrByBean(UserTravelBean travBean) {
		this.departureCity = travBean.getCityOfDep();
		this.arrivalCity = travBean.getCityOfArr();
	}
	
	public void setIdByBean(UserTravelBean travBean) {
		this.id = travBean.getParsedId();
	}
	
	public void setArrCityByBean(UserTravelBean travBean) {
		this.arrivalCity = travBean.getCityOfArr();
	}
	
	public void setDepAndArr(String depCity, String arrCity) {
		this.departureCity = depCity;
		this.arrivalCity = arrCity;
	}
	
	public void setTravByBean(UserTravelBean travBean) {
		this.departureCity = travBean.getCityOfDep();
		this.arrivalCity = travBean.getCityOfArr();
		this.departureDate = travBean.getFirstDayPars();
		this.arriveDate = travBean.getLastDayPars();
	}
	
	public void setAll(int ident, String depCity, String arrCity, LocalDate depDate, LocalDate arrDate, float cost) {
		this.id = ident;
		this.departureCity = depCity;
		this.arrivalCity = arrCity;
		this.departureDate = depDate;
		this.arriveDate = arrDate;
		this.cost = cost;
	}
	
	public void setAllByBean(UserTravelBean travBean) {
		this.id = travBean.getParsedId();
		this.departureCity = travBean.getCityOfDep();
		this.arrivalCity = travBean.getCityOfDep();
		this.departureDate = travBean.getFirstDayPars();
		this.arriveDate = travBean.getLastDayPars();
		this.cost = travBean.getParsedCost();
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getDepCity() {
		return this.departureCity;
	}
	
	public String getArrCity() {
		return this.arrivalCity;
	}
	
	public LocalDate getDepDay() {
		return this.departureDate;
	}
	
	public LocalDate getArrDay() {
		return this.arriveDate;
	}
	
	public float getMoney() {
		return this.cost;
	}
}
