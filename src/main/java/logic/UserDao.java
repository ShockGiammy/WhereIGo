package logic;
import java.sql.*;
import logic.model.LocationModel;
import java.util.logging.Logger;
import java.util.logging.Level;

public class UserDao {
	private static Connection connection;
	private static SingletonDbConnection dbConn;
	private Logger logger = Logger.getLogger(UserDao.class.getName());
	
	public String getCity() {
		LocationModel lm = new LocationModel();
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
			PreparedStatement statement = connection.prepareStatement("select * from Locations where country=?");    
			statement.setString(1, "USA");    
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				 lm.setCountry(rs.getString(1));
				 lm.setCity(rs.getString(2));
			}
		}catch (SQLException e) {
			 
			logger.log(Level.SEVERE, "SQLException occurred", e);
		}
		return lm.getCity();
	}
}
