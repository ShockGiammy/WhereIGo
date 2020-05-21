package logic.beans;

import logic.exceptions.LengthFieldException;

public class GroupBean {
	private String groupTitle;
	private String groupOwner;
	private String groupDestination;
	
	
	public void setGroupTitle(String title) throws LengthFieldException {
		if(title.length() > 50) {
			throw new LengthFieldException("Group title too long");
		}
		this.groupTitle = title;
	}
	
	public void setGroupOwner(String owner) {
		this.groupOwner = owner;
	}
	
	public void setGroupDestination(String destination) throws LengthFieldException {
		if(destination.length() > 45) {
			throw new LengthFieldException("Group destination too long");
		}
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
