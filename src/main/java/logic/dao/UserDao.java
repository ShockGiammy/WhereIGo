package logic.dao;
import java.sql.*;

import logic.beans.LogInBean;
import logic.beans.UserDataBean;
import java.util.logging.Level;

public class UserDao extends GeneralConnection{
	
	public String[] getCity(UserDataBean usrBean) {
		String locat[] = new String[3];
		int i = 0;
		getConnection();
		try {
			PreparedStatement statement = dbConn.getConnection().prepareStatement("select * from Locations where tipeOfPersonality=?");    
			statement.setString(1, usrBean.getPersonality());    
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				locat[i] = rs.getString(2);
				i+=1;
			}
		}catch (SQLException e) {
			 
			logger.log(Level.SEVERE, "SQLException occurred during fetch of cities", e);
		}
		return locat;
	}
	
	public int checkLogInInfo(LogInBean bean, UserDataBean usrBean) {
		int fetched = 0;
		getConnection();
		try {
			PreparedStatement statement = dbConn.getConnection().prepareStatement("select * from usr where (username=? and passw=?)");    
			statement.setString(1,bean.getUserName());
			statement.setString(2,bean.getPasw());
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				fetched +=1;
				usrBean.setUserName(rs.getString(1));
				usrBean.setName(rs.getString(3));
				usrBean.setSurname(rs.getString(4));
				usrBean.setDateOfBirth(rs.getString(5));
				usrBean.setGender(rs.getString(6));
				if(rs.getString(8) != null) {
					usrBean.setPersonality(rs.getString(8));
				}
			}
			
		}catch (SQLException e) {
				logger.log(Level.SEVERE, "SQLException occurred during the fetch of credentials", e);
		}
		return fetched;
	}
	
	public void insertPersonality(String personality,String username) {
		getConnection();
		try {
			PreparedStatement stm = dbConn.getConnection().prepareStatement("update usr set tipeOfPersonality = ? where username = ?");
			stm.setString(1, personality);
			stm.setString(2, username);
			stm.execute();
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "SQLException occured during insert", e);
		}
	}
}
