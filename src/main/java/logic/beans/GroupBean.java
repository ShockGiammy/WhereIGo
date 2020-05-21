package logic.beans;

import logic.exceptions.LengthFieldException;
import logic.exceptions.NullValueException;

public class GroupBean {
	private String groupTitle;
	private String groupOwner;
	private String groupDestination;
	
	public GroupBean() {
		
	}
	
	public GroupBean(String title) {
		this.groupTitle = title;
	}
	
	public GroupBean(String title, String owner) {
		this.groupTitle = title;
		this.groupOwner = owner;
	}
	
	public GroupBean(String title, String owner, String destination) {
		this.groupDestination = destination;
		this.groupTitle = title;
		this.groupOwner = owner;
	}
	
	public void setGroupTitle(String title) throws LengthFieldException, NullValueException {
		if(title == null ||title.equalsIgnoreCase("")) {
			throw new NullValueException("Please insert a valid group name");
		}
		else if(title.length() > 50) {
			throw new LengthFieldException("Group title too long");
		}
		this.groupTitle = title;
	}
	
	public void setGroupOwner(String owner) {
		this.groupOwner = owner;
	}
	
	public void setGroupDestination(String destination) throws LengthFieldException, NullValueException {
		if(destination == null ||destination.equalsIgnoreCase("")) {
			throw new NullValueException("Please insert a valid group destination");
		}
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
