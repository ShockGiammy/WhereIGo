/* We image that we have only two-way tickets*/
package logic.model;

import java.time.LocalDate;

public class TicketModel {
	private String departureCity;
	private String arrivalCity;
	private LocalDate departureDate;
	private LocalDate arriveDate;
	private float cost;
	
	public void setAll(String depCity, String arrCity, LocalDate depDate, LocalDate arrDate, float money) {
		this.departureCity = depCity;
		this.arrivalCity = arrCity;
		this.departureDate = depDate;
		this.arriveDate = arrDate;
		this.cost = money;
	}
	
	public String getDepCity() {
		return this.departureCity;
	}
	
	public String getArrCity() {
		return this.arrivalCity;
	}
	
	public LocalDate getDepDay() {
		return this.arriveDate;
	}
	
	public LocalDate getArrDay() {
		return this.departureDate;
	}
	
	public float getMoney() {
		return this.cost;
	}
}
