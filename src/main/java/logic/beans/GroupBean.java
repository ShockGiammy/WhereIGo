package logic.beans;

public class GroupBean {
	private Integer id;
	private String groupTitle;
	private String groupOwner;
	private String groupDestination;
	
	public void setId(int ident) {
		this.id = ident;
	}
	
	public void setGroupTitle(String title) {
		this.groupTitle = title;
	}
	
	public void setGroupOwner(String owner) {
		this.groupOwner = owner;
	}
	
	public void setGroupDestination(String destination) {
		this.groupDestination = destination;
	}
	
	public int getId() {
		return this.id;
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
