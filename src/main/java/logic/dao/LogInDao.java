package logic.dao;

import java.sql.*;

import logic.beans.LogInBean;
import logic.beans.UserDataBean;

import java.util.logging.Level;

public class LogInDao extends GeneralConnection{
	
	public int checkLogInInfo(LogInBean bean, UserDataBean usrBean) {
		int fetched = 0;
		getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("select * from usr where (username=? and passw=?)");    
			statement.setString(1,bean.getUserName());
			statement.setString(2,bean.getPasw());
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				fetched +=1;
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
}
