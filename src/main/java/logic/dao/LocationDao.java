package logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import logic.beans.LocationBean;

public class LocationDao extends GeneralConnection{
	public LocationDao() {
		getConnection();
	}
	
	public void retriveLocationInfo(LocationBean locBean) {
		try {
			PreparedStatement stmt = dbConn.getConnection().prepareStatement("SELECT * FROM Locations WHERE (city =?)");
			stmt.setString(1,locBean.getCityName());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				locBean.setCountryName(rs.getString(1));
				byte[] image = rs.getBytes(4);
				locBean.setStream(image);
				locBean.setDescription(rs.getString(5));
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Error while retriving location");
			e.printStackTrace();
		}
	}
}
