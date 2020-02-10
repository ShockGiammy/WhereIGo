package logic;

import java.sql.*;

public class SingletonDbConnection {
	private static Connection dbConn;
	private String url = "jdbc:mysql://localhost/whereigo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String username = "root";
	private String password = "pippo1998";
	
	protected SingletonDbConnection() throws SQLException {
		dbConn = DriverManager.getConnection(this.url,this.username,this.password);
	}
	
	public synchronized static Connection getDbConnection() throws SQLException {
		if (dbConn == null) {
			new SingletonDbConnection();
		}
		return dbConn;
	}
}
