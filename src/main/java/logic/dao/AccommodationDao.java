package logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.SingletonDbConnection;
import logic.beans.AccommodationBean;
import logic.model.AccommodationModel;

public class AccommodationDao {
	
	private static final String EXCEPTION = "Got an exception!";

	public AccommodationModel createAccommodation(AccommodationBean info) {
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("INSERT INTO Post(ID,photo,utente,descr,beds,city,address,services,squareMetres,tipologia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")){    
			statement.setLong(1, info.getID());
			statement.setBinaryStream(2,info.getInputFile(), info.getFileLength());		//image
			statement.setString(3,info.getRenter()); 				//user
			statement.setString(4,info.getDescription()); 			//description
			statement.setString(5,info.getBeds());					//beds
			statement.setString(6,info.getCity());					//city
			statement.setString(7,info.getAddress());				//address
			statement.setBytes(8,info.getServices());				//services
			statement.setString(9,info.getSquareMetres());			//squareMetres
			statement.setString(10,info.getType());					//type
			statement.execute();
		}
		catch (SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, EXCEPTION);
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
		return new AccommodationModel(info);
	}

	public List<AccommodationModel> retrieveAccommodations() {
		List<AccommodationModel> models = new ArrayList<>();
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("Select * From Post")){    
			retrieveAccommodationDatas(statement, models);
		}catch (SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, EXCEPTION);
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
		return models;
	}
	
	public List<AccommodationModel> retrieveMyAccommodations(String myUsername) {
		List<AccommodationModel> models = new ArrayList<>();
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("Select * From Post Where utente = ?")){
			statement.setString(1, myUsername);
			retrieveAccommodationDatas(statement, models);
		}catch (SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, EXCEPTION);
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
		return models;
	}
	
	public void retrieveAccommodationDatas(PreparedStatement statement, List<AccommodationModel> models) {
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				AccommodationModel model = new AccommodationModel();
				model.setID(rs.getLong(1));
				byte[] image = rs.getBytes(2);
				model.setHouseImageBytes(image);
				model.setRenter(rs.getString(3));
				model.setDescription(rs.getString(4));
				model.setBeds(rs.getString(5));
				model.setCity(rs.getString(6));
				model.setAddress(rs.getString(7));
				model.setServices(rs.getBytes(8));
				model.setSquareMetres(rs.getString(9));
				model.setType(rs.getString(10));
				models.add(model);
			}
		}catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "Location ResultSet error", e);
		}
	}
	
	public void delete(long l) {
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("Delete From Post Where ID = ?")){    
			statement.setLong(1, l);
			statement.execute();
		}catch (SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, EXCEPTION);
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
	}
	
	public void update(AccommodationBean info) {
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("Update Post Set photo = ? , descr = ? ,beds = ? "
				+ ",city = ? ,address = ? ,services = ? ,squareMetres = ? ,tipologia = ? Where ID = ?")){
			statement.setBinaryStream(1,info.getInputFile(), info.getFileLength());		//image
			statement.setString(2,info.getDescription()); 			//description
			statement.setString(3,info.getBeds());					//beds
			statement.setString(4,info.getCity());					//city
			statement.setString(5,info.getAddress());				//address
			statement.setBytes(6,info.getServices());				//services
			statement.setString(7,info.getSquareMetres());			//squareMetres
			statement.setString(8,info.getType());					//type
			statement.setLong(9, info.getID());
			statement.execute();
		}catch (SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, EXCEPTION);
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
	}
}
