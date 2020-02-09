package logic.beans;


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
	
	public String getFirstDay() {
		return this.firstDay;
	}
	
	public String getLastDay() {
		return this.lastDay;
	}
	
	public String getMoneyRange(){
		return this.moneyRange;
	}
}
