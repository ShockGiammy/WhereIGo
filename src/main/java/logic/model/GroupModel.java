package logic.model;

import java.util.ArrayList;
import java.util.List;

import logic.beans.GroupBean;
import logic.dao.GroupDao;

public class GroupModel {
	private GroupDao grpDao;
	
	public GroupModel() {
		this.grpDao = new GroupDao();
	}
	
	public void getGroups(GroupBean beans, List<GroupBean> beanList) {
		grpDao.retriveGroups(beans, beanList);
	}
}
