package logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.SingletonDbConnection;
import logic.beans.GroupBean;
import logic.beans.UserDataBean;
import logic.model.GroupModel;

public class GroupDao {
	
	public void retriveSuggestedGroups(UserDataBean dataBean, List<GroupModel> modelList) {
		if(dataBean.getPersonality()== null) {
			return;
		}
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("select travcity,groupowner,title from travelgroups join locations on travelgroups.travCity=locations.city where tipeOfPersonality=?")){
			statement.setString(1, dataBean.getPersonality());
			getSuggestedGroupsDatas(statement, modelList);
		}catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "SQLException occurred\n",e);
		}
	}
	
	public void getSuggestedGroupsDatas(PreparedStatement statement, List<GroupModel> modelList) {
		try(ResultSet rs = statement.executeQuery()){
				while(rs.next()) {
					GroupModel grpModel = new GroupModel();
					grpModel.setAll(rs.getString(2), rs.getString(3), rs.getString(1));
					modelList.add(grpModel);
				}
		}catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "Group fetch error", e);
		}
	}
	
	public int saveUserGroup(GroupBean grpBean) {
		try(PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("INSERT INTO travelgroups(travCity, groupOwner, title) VALUES(?,?,?)")){
			statement.setString(1, grpBean.getGroupDestination());
			statement.setString(2, grpBean.getGroupOwner());
			statement.setString(3, grpBean.getGroupTitle());
			statement.execute();
		}catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "Cannot insert group", e);
			return -1;
		}
		return 0;
	}
	
	public void getUserGroups(List<GroupModel> grpModel, UserDataBean dataBean) {
		try(PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("select distinct travcity,title,groupowner from travelgroups where (groupowner=?)")){
			statement.setString(1, dataBean.getUsername());
			findUserGroups(grpModel, statement);
		}catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
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
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
	}
	
	public void getPartGroups(List<GroupModel> grpModel, UserDataBean bean) {
		try(PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("select groupowner,title,travcity from travelgroups join participatesto on participatesto.grp = travelgroups.title where(participant =?)")){
			statement.setString(1, bean.getUsername());
			fetchPartGroup(statement, grpModel);
		}catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
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
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
	}
	
	public void deleteGroup(GroupBean bean) {
		try(PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("delete from travelgroups where (groupowner=? and title=?)")){
			statement.setString(1, bean.getGroupOwner());
			statement.setString(2, bean.getGroupTitle());
			statement.execute();
		}catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
	}
	
	public int insertParticipant(GroupBean grpBean, UserDataBean dataBean) {
		try(PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("insert into participatesto(participant, grp) values(?,?)")){
			statement.setString(2, grpBean.getGroupTitle());
			statement.setString(1, dataBean.getUsername());
			statement.execute();
			return 0;
		}catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
			return -1;
		}
	}
	
	public void leaveJoinedGroup(GroupBean grpBean, UserDataBean dataBean) {
		try(PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("delete from participatesto where(participant=? and grp=?)")){
			statement.setString(2, grpBean.getGroupTitle());
			statement.setString(1, dataBean.getUsername());
			statement.execute();
		}catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
	}
}
