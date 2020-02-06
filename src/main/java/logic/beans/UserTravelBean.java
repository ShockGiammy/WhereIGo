package logic.beans;

import java.time.LocalDate;

public class UserTravelBean {
	private String firstDay;
	private String lastDay;
	private String moneyRange;
	
	public void setFirstDay(String firstDay) {
		this.firstDay = firstDay;
	}
	
	public void setLastDay(String lastDay) {
		this.lastDay = lastDay;
	}
	
	public void setMoneyRange(String moneyRange) {
		this.moneyRange = moneyRange;
	}
	
	public void getFirstDay() {
		System.out.println(this.firstDay);
	}
	
	public void getLastDay() {
		System.out.println(this.lastDay);
	}
	
	public void getMoneyRange(){
		System.out.println(this.moneyRange);
	}
}
