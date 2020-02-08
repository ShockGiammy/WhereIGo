package logic;
import java.sql.*;
import logic.model.LocationModel;

public class UserDao {
	private static Connection connection;
	private static SingletonDbConnection dbConn;
	
	public String getCity() {
		LocationModel lm = new LocationModel();
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
			PreparedStatement statement = connection.prepareStatement("select * from Locations where country=?");    
			statement.setString(1, "USA");    
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				 lm.setCountry(rs.getString(1));
				 lm.setCity(rs.getString(2));
			}
		}catch (SQLException e) {
			 
			  System.out.println("Could not retrieve data from the database " + e.getMessage());
		}
		return lm.getCity();
	}
}
