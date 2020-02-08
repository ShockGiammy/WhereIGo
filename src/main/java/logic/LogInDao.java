package logic;

import java.sql.*;

import logic.beans.LogInBean;

public class LogInDao {
	private static Connection connection = null;
	private SingletonDbConnection dbConn;
	
	public int checkLogInInfo(LogInBean bean) {
		int fetched = 0;
		try{
			String driverName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverName);
			connection = dbConn.getDbConnection();
		}catch(ClassNotFoundException e) {
			System.out.println("Could not find the database driver " + e.getMessage());
		}catch(SQLException e) {
			System.out.println("Could not connect to the database " + e.getMessage());
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
			 
			  System.out.println("Could not retrieve data from the database " + e.getMessage());
		}
		return fetched;
	}
}
