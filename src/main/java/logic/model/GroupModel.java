package logic.model;

import java.util.List;
import logic.beans.GroupBean;
import logic.dao.GroupDao;

public class GroupModel {
	private GroupDao grpDao;
	private String groupOwner;
	private String groupDescr;
	private String groupDest;
	
	public GroupModel() {
		this.grpDao = new GroupDao();
	}
	
	public void getGroups(GroupBean beans, List<GroupBean> beanList) {
		grpDao.retriveGroups(beans, beanList);
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
