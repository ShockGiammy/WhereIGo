package logic.dao;
import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import logic.SingletonDbConnection;

public class GeneralConnection {
	protected Connection connection = null;
	protected SingletonDbConnection dbConn;
	protected Logger logger = Logger.getLogger(LogInDao.class.getName());
	
	@SuppressWarnings("static-access")
	public void getConnection() {
		try{
			String driverName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverName);
			connection = dbConn.getDbConnection();
		}catch(ClassNotFoundException e) {
			logger.log(Level.SEVERE, "ClassNotFoundException occurred", e);
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "SQLException occurred", e);
		}
	}
}
