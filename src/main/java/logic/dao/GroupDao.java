package logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import logic.beans.GroupBean;
import logic.beans.UserDataBean;

public class GroupDao extends GeneralConnection{
	private int i;
	
	public void retriveGroups(GroupBean[] beans, UserDataBean userBean) {
		String[] cities = new String[5];
		getConnection();
		i = 0;
		try (PreparedStatement statement = dbConn.getConnection().prepareStatement("select city from Locations where tipeOfPersonality = ?")){
			statement.setString(1, userBean.getPersonality());
			getGroupsDatas(statement, cities);
			i = 0;
			retriveCities(cities);
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "SQLException occurred\n",e);
		}
	}
	
	public void retriveCities(String[] cities) {
		try(PreparedStatement ps1 = dbConn.getConnection().prepareStatement("select * from travelgroups where (travCity=? or travCity=? or travCity=?)")){
			ps1.setString(1, cities[0]);
			ps1.setString(2, cities[1]);
			ps1.setString(3, cities[2]);
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "SQLException occurred : fetch of cities failed",e);
		}
	}
	
	public void getGroupsDatas(PreparedStatement statement, String[] cities) {
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				while(rs.next()) {
					cities[i] = rs.getString(1);
					i += 1;
				}
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Group fetch error", e);
		}
	}
	
	public void getTravelGroupsInfo(PreparedStatement statement, GroupBean[] beans) {
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				beans[i].setGroupOwner(rs.getString(3));
				beans[i].setGroupDestination(rs.getString(2));
				beans[i].setGroupTitle(rs.getString(4));
				i+=1;
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "TravelGroup fetch error", e);
		}
	}
}
