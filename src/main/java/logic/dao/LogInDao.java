package logic.dao;

import java.sql.*;

import logic.beans.LogInBean;
import logic.beans.UserDataBean;

import java.util.logging.Level;

public class LogInDao extends GeneralConnection{
	int ret = 0;
	
	public LogInDao() {
		getConnection();
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
				usrBean.setName(rs.getString(3));
				usrBean.setSurname(rs.getString(4));
				usrBean.setDateOfBirth(rs.getString(5));
				usrBean.setGender(rs.getString(6));
			}
			
		}catch (SQLException e) {
				logger.log(Level.SEVERE, "SQLException occurred during the fetch of credentials", e);
		}
		return ret;
	}
	
	public int insertNewUser(UserDataBean usrBean) {
		try {
			PreparedStatement statement = dbConn.getConnection().prepareStatement("INSERT INTO usr values(?,?,?,?,?,?,?,?");
			statement.setString(1, usrBean.getUsername());
			statement.setString(2, usrBean.getPassword());
			statement.setString(3, usrBean.getName());
			statement.setString(4, usrBean.getSurname());
			statement.setString(5, usrBean.getDateOfBirth());
			statement.setString(6, usrBean.getGender());
			statement.setString(7, usrBean.getType());
			statement.setString(8, null);
			ret = statement.executeUpdate();
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "SQLException on registration\n", e);
		}
		return ret;
	}
}
