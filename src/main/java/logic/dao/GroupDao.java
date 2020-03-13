package logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import logic.beans.GroupBean;

public class GroupDao extends GeneralConnection{
	
	public void retriveGroups(GroupBean bean , List<GroupBean> beanList) {
		getConnection();
		try (PreparedStatement statement = dbConn.getConnection().prepareStatement("select * from travelgroups where (travCity=?)")){
			statement.setString(1, bean.getGroupDestination());
			getGroupsDatas(statement, beanList);
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "SQLException occurred\n",e);
		}
	}
	
	public void getGroupsDatas(PreparedStatement statement, List<GroupBean> beanList) {
		try(ResultSet rs = statement.executeQuery()){
				while(rs.next()) {
					GroupBean groupBean = new GroupBean();
					groupBean.setGroupDestination(rs.getString(2));
					groupBean.setGroupOwner(rs.getString(3));
					groupBean.setGroupTitle(rs.getString(4));
					beanList.add(groupBean);
				}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Group fetch error", e);
		}
	}
}
