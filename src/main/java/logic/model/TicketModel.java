/* We image that we have only two-way tickets*/
package logic.model;

import java.time.LocalDate;

public class TicketModel {
	private int id;
	private String departureCity;
	private String arrivalCity;
	private LocalDate departureDate;
	private LocalDate arriveDate;
	private float cost;
	
	public void setAll(int ident, String depCity, String arrCity, LocalDate depDate, LocalDate arrDate, float money) {
		this.id = ident;
		this.departureCity = depCity;
		this.arrivalCity = arrCity;
		this.departureDate = depDate;
		this.arriveDate = arrDate;
		this.cost = money;
	}
	
	public void setId(int ident) {
		this.id = ident;
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
