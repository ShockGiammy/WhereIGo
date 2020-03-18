package logic.model;

import java.util.List;
import logic.beans.GroupBean;
import logic.dao.GroupDao;

public class GroupModel {
	private GroupDao grpDao;
	private Integer groupId;
	private String groupOwner;
	private String groupDescr;
	private String groupDest;
	
	public GroupModel() {
		this.grpDao = new GroupDao();
	}
	
	public void getGroups(GroupBean beans, List<GroupBean> beanList) {
		grpDao.retriveGroups(beans, beanList);
	}
	
	public void setAll(int id, String owner, String description, String destination) {
		this.groupId = id;
		this.groupOwner = owner;
		this.groupDescr = description;
		this.groupDest = destination;
	}
	
	public int getId() {
		return this.groupId;
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
