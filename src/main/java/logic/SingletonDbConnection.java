package logic;

import java.sql.*;

public class SingletonDbConnection {
	private static Connection singDbConn = null;
	private String url = "jdbc:mysql://localhost/whereigo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String username = "root";
	private String password = "pippo18021998";
	
	protected SingletonDbConnection() throws SQLException {
		SingletonDbConnection.singDbConn = DriverManager.getConnection(this.url,this.username,this.password);
		
	}
	
	public synchronized static Connection getDbConnection() throws SQLException {
		if (SingletonDbConnection.singDbConn == null) {
			SingletonDbConnection.singDbConn = (Connection)new SingletonDbConnection();
		}
		return singDbConn;
	}
}
