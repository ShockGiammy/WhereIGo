package logic.dao;
import java.sql.*;
import logic.model.LocationModel;
import java.util.logging.Level;

public class UserDao extends GeneralConnection{
	public String getCity() {
		LocationModel lm = new LocationModel();
		getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("select * from Locations where country=?");    
			statement.setString(1, "USA");    
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				 lm.setCountry(rs.getString(1));
				 lm.setCity(rs.getString(2));
			}
		}catch (SQLException e) {
			 
			logger.log(Level.SEVERE, "SQLException occurred during fetch of cities", e);
		}
		return lm.getCity();
	}
}
