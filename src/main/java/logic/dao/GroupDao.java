package logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import logic.beans.GroupBean;
import logic.beans.UserDataBean;
import logic.model.GroupModel;

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
					groupBean.setGroupDestination(rs.getString(1));
					groupBean.setGroupOwner(rs.getString(2));
					groupBean.setGroupTitle(rs.getString(3));
					beanList.add(groupBean);
				}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Group fetch error", e);
		}
	}
	
	public int saveUserGroup(GroupBean grpBean) {
		getConnection();
		try(PreparedStatement statement = dbConn.getConnection().prepareStatement("INSERT INTO travelgroups(travCity, groupOwner, title) VALUES(?,?,?)")){
			statement.setString(1, grpBean.getGroupDestination());
			statement.setString(2, grpBean.getGroupOwner());
			statement.setString(3, grpBean.getGroupTitle());
			statement.execute();
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Cannot insert group", e);
			return -1;
		}
		return 0;
	}
	
	public void retriveUserGroups(List<GroupModel> grpModelList, UserDataBean dataBean) {
		getConnection();
		try(PreparedStatement statement = dbConn.getConnection().prepareStatement("Select * from ParticipatesTojoin travelgroups on participatesto.grp = travelgroups.ID where (participant=?)")){
			statement.setString(1, dataBean.getUsername());
			fetchGroups(statement, grpModelList);
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Cannot fetch user group", e);
		}
	}
	
	public void fetchGroups(PreparedStatement statement, List<GroupModel> grpModelList) {
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				GroupModel model = new GroupModel();
				model.setAll(rs.getInt(3), rs.getString(5), rs.getString(6), rs.getString(4));
				grpModelList.add(model);
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Error while executing query user group", e);
		}
	}
	
	public void getUserGroups(List<GroupModel> grpModel, UserDataBean dataBean) {
		getConnection();
		try(PreparedStatement statement = dbConn.getConnection().prepareStatement("select distinct ID,travcity,title,groupowner from travelgroups join participatesto where (participant=? or groupowner=?)")){
			statement.setString(1, dataBean.getUsername());
			statement.setString(2, dataBean.getUsername());
			
		}catch(SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
	
	public void findUserGroups(List<GroupModel> grpModel, PreparedStatement statement) {
		try(ResultSet rs = statement.executeQuery()) { 
			while(rs.next()) {
				GroupModel group = new GroupModel();
				group.setAll(rs.getInt(1), rs.getString(4), rs.getString(3), rs.getString(2));
				grpModel.add(group);
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
}
