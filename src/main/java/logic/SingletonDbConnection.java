package logic;

import java.sql.*;

public class SingletonDbConnection {
	private static SingletonDbConnection istance = null;
	private Connection connection = null;
	
	protected SingletonDbConnection(String url, String username, String password) {
		try{
			this.connection = DriverManager.getConnection(url, username, password);
		}catch(SQLException e) {
			System.out.println("SQL exception occurred");
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
