package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingletonDbConnection {
	private static SingletonDbConnection istance = null;
	private Logger logger;
	protected Connection connection = null;
	private String url = "jdbc:mysql://localhost/whereigo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String username = "root";
	private String password = "pippo1998";
	
	protected SingletonDbConnection() {
		this.logger = Logger.getLogger("WIG");
	}
	
	public Connection getConnection() {
		try{
			this.connection = DriverManager.getConnection(url, username, password);
			return this.connection;
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Connection to DB failed !", e);
			return null;
		}
	}
	
	public static synchronized SingletonDbConnection getInstance(){
		if(SingletonDbConnection.istance == null) {
			SingletonDbConnection.istance = new SingletonDbConnection();
		}
		return SingletonDbConnection.istance;
	}
	
	public void closeConn() {
		try {
			this.connection.close();
		}catch(SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
}
