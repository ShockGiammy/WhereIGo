package logic.dao;
import java.sql.*;

import logic.beans.LogInBean;
import logic.beans.UserDataBean;
import java.util.logging.Level;

public class UserDao extends GeneralConnection{
	
	public UserDao() {
		getConnection();
	}
	
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
	
	public int checkLogInInfo(LogInBean bean, UserDataBean usrBean) {
		int ret = 0;
		try {
			PreparedStatement statement = dbConn.getConnection().prepareStatement("SELECT * FROM usr WHERE (username=? and passw=?)");    
			statement.setString(1,bean.getUserName());
			statement.setString(2,bean.getPasw());
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ret +=1;
				usrBean.setUserName(bean.getUserName());
				usrBean.setName(rs.getString(3));
				usrBean.setSurname(rs.getString(4));
				usrBean.setDateOfBirth(rs.getString(5));
				usrBean.setGender(rs.getString(6));
				usrBean.setType(rs.getString(7));
				usrBean.setPersonality(rs.getString(8));
			}
			
		}catch (SQLException e) {
				logger.log(Level.SEVERE, "SQLException occurred during the fetch of credentials", e);
		}
		return ret;
	}
	
	public void insertNewUser(UserDataBean usrBean) {
		try {
			PreparedStatement statement = dbConn.getConnection().prepareStatement("INSERT INTO usr(username, passw, nome, surname, dateOfBirth, gender, tipeOfUser) VALUES(?,?,?,?,?,?,?)");
			statement.setString(1, usrBean.getUsername());
			statement.setString(2, usrBean.getPassword());
			statement.setString(3, usrBean.getName());
			statement.setString(4, usrBean.getSurname());
			statement.setString(5, usrBean.getDateOfBirth());
			statement.setString(6, usrBean.getGender());
			statement.setString(7, usrBean.getType());
			statement.execute();
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "SQLException on registration\n", e);
		}
	}
	
	public void getUserDatas(UserDataBean usrBean) {
		try {
			PreparedStatement statement = dbConn.getConnection().prepareStatement("SELECT * FROM usr WHERE (username = ?)");
			statement.setString(1, usrBean.getUsername());
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				usrBean.setName(rs.getString(3));
				usrBean.setSurname(rs.getString(4));
				usrBean.setDateOfBirth(rs.getString(5));
				usrBean.setGender(rs.getString(6));
				usrBean.setType(rs.getString(7));
				usrBean.setPersonality(rs.getString(8));
			}
			
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "SQLException on fetchin user datas\n", e);
		}
	}
	
	public String[] getLocations(UserDataBean dataBean) {
		int i = 0;
		String loc[] = new String[3];
		try {
			PreparedStatement statement = dbConn.getConnection().prepareStatement("SELECT city FROM Locations WHERE (tipeOfPersonality = ?)");
			statement.setString(1, dataBean.getPersonality());
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				loc[i] = rs.getString(1);
				i+=1;
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "SQLException on fetchin locations\n", e);
		}
		return loc;
	}
}
