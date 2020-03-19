package logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		try(PreparedStatement statement = dbConn.getConnection().prepareStatement("Select * from ParticipatesTo join travelgroups on participatesto.grp = travelgroups.title where (participant=?)")){
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
				model.setAll(rs.getString(5), rs.getString(6), rs.getString(4));
				grpModelList.add(model);
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Error while executing query user group", e);
		}
	}
	
	public void getUserGroups(List<GroupModel> grpModel, UserDataBean dataBean) {
		getConnection();
		try(PreparedStatement statement = dbConn.getConnection().prepareStatement("select distinct travcity,title,groupowner from travelgroups where (groupowner=?)")){
			statement.setString(1, dataBean.getUsername());
			findUserGroups(grpModel, statement);
			getPartGroups(grpModel, dataBean);
		}catch(SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
	
	public void findUserGroups(List<GroupModel> grpModel, PreparedStatement statement) {
		try(ResultSet rs = statement.executeQuery()) { 
			while(rs.next()) {
				GroupModel group = new GroupModel();
				group.setAll(rs.getString(3), rs.getString(2), rs.getString(1));
				grpModel.add(group);
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
	
	public void getPartGroups(List<GroupModel> grpModel, UserDataBean bean) {
		try(PreparedStatement statement = dbConn.getConnection().prepareStatement("select groupowner,title,travcity from travelgroups join participatesto on participatesto.grp = travelgroups.title where(participant =?)")){
			statement.setString(1, bean.getUsername());
			fetchPartGroup(statement, grpModel);
		}catch(SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
	
	public void fetchPartGroup(PreparedStatement statement, List<GroupModel> grpList) {
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				GroupModel group = new GroupModel();
				group.setAll(rs.getString(1), rs.getString(2), rs.getString(3));
				grpList.add(group);
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
	
	public void deleteGroup(GroupBean bean) {
		getConnection();
		try(PreparedStatement statement = dbConn.getConnection().prepareStatement("delete from travelgroups where (groupowner=? and title=?)")){
			statement.setString(1, bean.getGroupOwner());
			statement.setString(2, bean.getGroupTitle());
			statement.execute();
		}catch(SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
	
	public int insertParticipant(GroupBean grpBean) {
		getConnection();
		try(PreparedStatement statement = dbConn.getConnection().prepareStatement("insert into participatesto(participant, grp) values(?,?)")){
			statement.setString(2, grpBean.getGroupTitle());
			statement.setString(1, grpBean.getGroupOwner()); //this is the participant,so maybe the bean should be revised
			statement.execute();
			return 0;
		}catch(SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
			return -1;
		}
	}
}
