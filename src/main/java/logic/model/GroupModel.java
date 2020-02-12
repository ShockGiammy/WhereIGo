package logic.model;

import logic.beans.GroupBean;
import logic.beans.UserDataBean;
import logic.dao.GroupDao;

public class GroupModel {
	private GroupDao grpDao;
	
	public GroupModel() {
		this.grpDao = new GroupDao();
	}
	
	public void getGroups(GroupBean beans[], UserDataBean dataBean) {
		grpDao.retriveGroups(beans, dataBean);
	}
}
