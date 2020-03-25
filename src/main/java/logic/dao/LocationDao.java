package logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import logic.beans.LocationBean;
import logic.beans.UserDataBean;

public class LocationDao extends GeneralConnection{
	
	public void retriveLocationInfo(LocationBean locBean) {
		getConnection();
		try (PreparedStatement statement = dbConn.getConnection().prepareStatement("select * from locations where city=?")){
			statement.setString(1,locBean.getCityName());
			getLocations(statement, locBean);
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Error while retriving location", e);
		}
	}
	
	public void getLocations(PreparedStatement statement, LocationBean locBean) {
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				locBean.setCountryName(rs.getString(1));
				byte[] image = rs.getBytes(4);
				locBean.setStream(image);
				locBean.setDescription(rs.getString(5));
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Location ResultSet error", e);
		}
	}
	
	public List<String> getSuggestedLocations(UserDataBean dataBean) {
		getConnection();
		List<String> loc = new ArrayList<>();
		try (PreparedStatement statement = dbConn.getConnection().prepareStatement("select city from locations where tipeofpersonality=? and city not in (select arrCity from Tickets join Buys on Tickets.id = Buys.ticket where Buys.passenger =?)")){
			statement.setString(1, dataBean.getPersonality());
			statement.setString(2, dataBean.getUsername());
			retriveSuggestedLocations(statement, loc);
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "SQLException on fetchin locations\n", e);
		}
		return loc;
	}
	
	public void retriveSuggestedLocations(PreparedStatement statement, List<String> loc) {
		getConnection();
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				loc.add(rs.getString(1));
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "ResultSet fetch fail !", e);
		}
	}
}
