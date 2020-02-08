package logic.beans;

public class LogInBean {
	private String usrName;
	private String psw;
	
	public void setUserName(String usrName) {
		this.usrName = usrName;
	}
	
	public void setPassword(String password) {
		this.psw = password;
	}
	
	public String getUserName() {
		return this.usrName;
	}
	
	public String getPasw() {
		return this.psw;
	}	
}
