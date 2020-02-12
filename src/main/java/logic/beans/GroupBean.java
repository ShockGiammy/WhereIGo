package logic.beans;

public class GroupBean {
	private String groupTitle;
	private String groupOwner;
	private String groupDestination;
	
	public void setGroupTitle(String title) {
		this.groupTitle = title;
	}
	
	public void setGroupOwner(String owner) {
		this.groupOwner = owner;
	}
	
	public void setGroupDestination(String destination) {
		this.groupDestination = destination;
	}
	
	public String getGroupTitle() {
		return this.groupTitle;
	}
	
	public String getGroupOwner() {
		return this.groupOwner;
	}
	
	public String getGroupDestination() {
		return this.groupDestination;
	}
}
