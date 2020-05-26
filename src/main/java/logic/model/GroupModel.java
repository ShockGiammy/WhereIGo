package logic.model;

import logic.beans.GroupBean;

public class GroupModel {
	private String groupOwner;
	private String groupDescr;
	private String groupDest;
	
	public void setAllByBean(GroupBean gBean) {
		this.groupOwner = gBean.getGroupOwner();
		this.groupDest = gBean.getGroupDestination();
		this.groupDescr = gBean.getGroupTitle();
	}
	
	public void setTitleByBean(GroupBean gBean) {
		this.groupDescr = gBean.getGroupTitle();
	}
	
	public void setAll(String owner, String description, String destination) {
		this.groupOwner = owner;
		this.groupDescr = description;
		this.groupDest = destination;
	}
	
	public String getOwner() {
		return this.groupOwner;
	}
	
	public String getDescription() {
		return this.groupDescr;
	}
	
	public String getDestination() {
		return this.groupDest;
	}
}
