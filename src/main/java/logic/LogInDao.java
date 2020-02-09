package logic;

import java.sql.*;

import logic.beans.LogInBean;
import java.util.logging.Logger;
import java.util.logging.Level;

public class LogInDao {
	private static Connection connection = null;
	private SingletonDbConnection dbConn;
	private Logger logger = Logger.getLogger(LogInDao.class.getName());
	
	public int checkLogInInfo(LogInBean bean) {
		int fetched = 0;
		try{
			String driverName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverName);
			connection = dbConn.getDbConnection();
		}catch(ClassNotFoundException e) {
			logger.log(Level.SEVERE, "ClassNotFoundException occurred", e);
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "SQLException occurred", e);
		}
		
		try {
			PreparedStatement statement = connection.prepareStatement("select * from usr where (username=? and passw=?)");    
			statement.setString(1,bean.getUserName());
			statement.setString(2,bean.getPasw());
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				fetched +=1;
			}
			}catch (SQLException e) {
			 
				logger.log(Level.SEVERE, "SQLException occurred", e);
		}
		return fetched;
	}
}
