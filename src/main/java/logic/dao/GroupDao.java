package logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.SingletonDbConnection;
import logic.exceptions.GroupNameTakenException;
import logic.model.GroupModel;
import logic.model.UserModel;

public class GroupDao {
	
	public void retriveSuggestedGroups(UserModel usrMod, List<GroupModel> modelList) {
		if(usrMod.getUserPersonality() != null) {
			try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("select travcity,groupowner,title from travelgroups join locations on travelgroups.travCity=locations.city where (tipeOfPersonality=? and groupowner!=?) and title not in (select grp as title from participatesto where participant = ?)")){
				statement.setString(1, usrMod.getUserPersonality());
				statement.setString(2, usrMod.getUserName());
				statement.setString(3, usrMod.getUserName());
				getSuggestedGroupsDatas(statement, modelList);
			}catch(SQLException e) {
				Logger.getLogger("WIG").log(Level.SEVERE, "SQLException occurred\n",e);
			}
			finally {
				SingletonDbConnection.getInstance().closeConn();
			}
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
	
	public void saveUserGroup(GroupModel grpMod) throws GroupNameTakenException {
		try(PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("INSERT INTO travelgroups(travCity, groupOwner, title) VALUES(?,?,?)")){
			statement.setString(1, grpMod.getDestination());
			statement.setString(2, grpMod.getOwner());
			statement.setString(3, grpMod.getDescription());
			statement.execute();
		}catch(SQLException e) {
			if(e.getErrorCode() == 1062) {
				throw new GroupNameTakenException(e.getMessage());
			}
			else {
				Logger.getLogger("WIG").log(Level.SEVERE, "Cannot insert group", e);
			}
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
	}
	
	public void getUserGroups(List<GroupModel> grpModel, UserModel usrMod) {
		try(PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("select distinct travcity,title,groupowner from travelgroups where (groupowner=?)")){
			statement.setString(1, usrMod.getUserName());
			findUserGroups(grpModel, statement);
		}catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
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
	
	public void getPartGroups(List<GroupModel> grpModel, UserModel bean) {
		try(PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("select groupowner,title,travcity from travelgroups join participatesto on participatesto.grp = travelgroups.title where(participant =?)")){
			statement.setString(1, bean.getUserName());
			fetchPartGroup(statement, grpModel);
		}catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
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
	
	public void deleteGroup(GroupModel grpMod) {
		try(PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("delete from travelgroups where (groupowner=? and title=?)")){
			statement.setString(1, grpMod.getOwner());
			statement.setString(2, grpMod.getDescription());
			statement.execute();
		}catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
	}
	
	public int insertParticipant(GroupModel grpBean, UserModel dataBean) {
		try(PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("insert into participatesto(participant, grp) values(?,?)")){
			statement.setString(2, grpBean.getDestination());
			statement.setString(1, dataBean.getUserName());
			statement.execute();
			return 0;
		}catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
		return -1;
	}
	
	public void leaveJoinedGroup(GroupModel grpBean, UserModel dataBean) {
		try(PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("delete from participatesto where(participant=? and grp=?)")){
			statement.setString(2, grpBean.getDescription());
			statement.setString(1, dataBean.getUserName());
			statement.execute();
		}catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
	}
}
