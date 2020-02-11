package logic.dao;
import java.sql.*;

import logic.beans.LogInBean;
import logic.beans.UserDataBean;
import logic.model.LocationModel;
import java.util.logging.Level;

public class UserDao extends GeneralConnection{
	public String getCity() {
		LocationModel lm = new LocationModel();
		getConnection();
		try {
			PreparedStatement statement = dbConn.getConnection().prepareStatement("select * from Locations where country=?");    
			statement.setString(1, "USA");    
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				 lm.setCountry(rs.getString(1));
				 lm.setCity(rs.getString(2));
			}
		}catch (SQLException e) {
			 
			logger.log(Level.SEVERE, "SQLException occurred during fetch of cities", e);
		}
		return lm.getCity();
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
