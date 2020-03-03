package logic;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingletonDbConnection {
	private static SingletonDbConnection istance = null;
	private Connection connection = null;
	private Logger logger;
	
	protected SingletonDbConnection(String url, String username, String password) {
		this.logger = Logger.getLogger("WIG");
		try{
			this.connection = DriverManager.getConnection(url, username, password);
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Connection to DB failed !", e);
		}
	}
	
	public Connection getConnection() {
		if(connection != null) {
			return connection;
		}
		return null;
	}
	
	public static synchronized SingletonDbConnection getInstance(String url, String username, String password){
		if(SingletonDbConnection.istance == null) {
			SingletonDbConnection.istance = new SingletonDbConnection(url, username, password);
		}
		return SingletonDbConnection.istance;
	}
}
