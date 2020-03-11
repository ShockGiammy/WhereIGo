package logic.dao;
import java.sql.*;

import logic.beans.LogInBean;
import logic.beans.UserDataBean;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class UserDao extends GeneralConnection{
	
	public String[] getCity(UserDataBean usrBean) {
		String[] locat = new String[3];
		getConnection();
		try (PreparedStatement statement = dbConn.getConnection().prepareStatement("select * from Locations where tipeOfPersonality=?")){    
			statement.setString(1, usrBean.getPersonality());    
			getCities(statement, locat);
		}catch (SQLException e) {
			 
			logger.log(Level.SEVERE, "SQLException occurred during fetch of cities", e);
		}
		return locat;
	}
	
	public void insertPersonality(String personality,String username) {
		getConnection();
		try (PreparedStatement stm = dbConn.getConnection().prepareStatement("update usr set tipeOfPersonality = ? where username = ?")){
			stm.setString(1, personality);
			stm.setString(2, username);
			stm.execute();
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "SQLException occured during insert", e);
		}
	}
	
	public int checkLogInInfo(LogInBean bean, UserDataBean usrBean) {
		getConnection();
		int ret = 0;
		if(dbConn == null) {
			getConnection();
		}
		try (PreparedStatement statement = dbConn.getConnection().prepareStatement("SELECT * FROM usr WHERE (username=? and passw=?)")){    
			statement.setString(1,bean.getUserName());
			statement.setString(2,bean.getPasw());
			ret = getLoggedUser(statement, usrBean);
		}catch (SQLException e) {
				logger.log(Level.SEVERE, "SQLException occurred during the fetch of credentials", e);
		}
		return ret;
	}
	
	public void insertNewUser(UserDataBean usrBean) {
		getConnection();
		try (PreparedStatement statement = dbConn.getConnection().prepareStatement("INSERT INTO usr(username, passw, nome, surname, dateOfBirth, gender, tipeOfUser) VALUES(?,?,?,?,?,?,?)")){
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
		getConnection();
		try (PreparedStatement statement = dbConn.getConnection().prepareStatement("SELECT * FROM usr WHERE (username = ?)")){
			statement.setString(1, usrBean.getUsername());
			retriveUser(statement, usrBean);
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "SQLException on fetchin user datas\n", e);
		}
	}
	
	public List<String> getLocations(UserDataBean dataBean) {
		getConnection();
		List<String> loc = new ArrayList<>();
		try (PreparedStatement statement = dbConn.getConnection().prepareStatement("SELECT city FROM Locations WHERE (tipeOfPersonality = ?)")){
			statement.setString(1, dataBean.getPersonality());
			retriveLocations(statement, loc);
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "SQLException on fetchin locations\n", e);
		}
		return loc;
	}
	
	public void getCities(PreparedStatement statement, String[] locat)  {
		getConnection();
		int i = 0;
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				locat[i] = rs.getString(2);
				i+=1;
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "ResultSet fetch ", e);
		}
	}
	
	public int getLoggedUser(PreparedStatement statement, UserDataBean usrBean) {
		getConnection();
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				usrBean.setUserName(rs.getString(1));
				usrBean.setName(rs.getString(3));
				usrBean.setSurname(rs.getString(4));
				usrBean.setDateOfBirth(rs.getString(5));
				usrBean.setGender(rs.getString(6));
				usrBean.setType(rs.getString(7));
				usrBean.setPersonality(rs.getString(8));
				return 1;
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "ResultSet fetch fail !", e);
		}
		return 0;
	}
	
	public void retriveUser(PreparedStatement statement, UserDataBean usrBean) {
		getConnection();
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				usrBean.setName(rs.getString(3));
				usrBean.setSurname(rs.getString(4));
				usrBean.setDateOfBirth(rs.getString(5));
				usrBean.setGender(rs.getString(6));
				usrBean.setType(rs.getString(7));
				usrBean.setPersonality(rs.getString(8));
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "ResultSet fetch fail !", e);
		}
	}
	
	public void retriveLocations(PreparedStatement statement, List<String> loc) {
		getConnection();
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				loc.add(rs.getString(1));
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "ResultSet fetch fail !", e);
		}
	}
}
