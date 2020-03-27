package logic;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingletonDbConnection {
	private static SingletonDbConnection istance = null;
	private Connection connection = null;
	private Logger logger;
	private String url = "jdbc:mysql://localhost/whereigo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String username = "root";
	private String password = "pippo1998";
	
	protected SingletonDbConnection() {
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
	
	public static synchronized SingletonDbConnection getInstance(){
		if(SingletonDbConnection.istance == null) {
			SingletonDbConnection.istance = new SingletonDbConnection();
		}
		return SingletonDbConnection.istance;
	}
}
