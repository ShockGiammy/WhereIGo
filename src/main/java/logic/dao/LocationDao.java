package logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.SingletonDbConnection;
import logic.model.LocationModel;
import logic.model.UserModel;

public class LocationDao {
	
	public void retriveLocationInfo(LocationModel locModel) {
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("select * from locations where city=?")){
			statement.setString(1,locModel.getCity());
			getLocations(statement, locModel);
		}catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "Error while retriving location", e);
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
	}
	
	public void getLocations(PreparedStatement statement, LocationModel locModel) {
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				locModel.setAll(rs.getString(1), rs.getString(5), rs.getBytes(4));
			}
		}catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "Location ResultSet error", e);
		}
	}
	
	public List<String> getSuggestedLocations(UserModel usrMod) {
		List<String> loc = new ArrayList<>();
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("select city from locations where tipeofpersonality=? and city not in (select arrCity from Tickets join Buys on Tickets.id = Buys.ticket where Buys.passenger =?)")){
			statement.setString(1, usrMod.getUserPersonality());
			statement.setString(2, usrMod.getUserName());
			retriveSuggestedLocations(statement, loc);
		}catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "SQLException on fetchin locations\n", e);
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
		return loc;
	}
	
	public void retriveSuggestedLocations(PreparedStatement statement, List<String> loc) {
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				loc.add(rs.getString(1));
			}
		}catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "ResultSet fetch fail !", e);
		}
	}
}
