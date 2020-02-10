package logic.dao;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import logic.beans.RentAccomodationBean;
import logic.model.AccomodationModel;

public class AccomodationCreator extends GeneralConnection{
	
	int ID;

	public AccomodationModel createAccomodation(RentAccomodationBean Info) {
		getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO Post VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, Announcement)");    
			statement.setInt(1, Info.getID());
			//statement.setBlob(2,(Blob) Info.getHouseImage());		//image
			//statement.setString(3,Info.getPasw()); 				//user
			statement.setString(4,Info.getDescription()); 			//desc
			statement.setString(5,Info.getBeds());					//beds
			statement.setString(6,Info.getCity());					//city
			statement.setString(7,Info.getAddress());				//address
			statement.setBytes(8,Info.getServices());				//services
			statement.setString(9,Info.getSquareMetres());			//squareMetres
			statement.setString(10,Info.getType());					//tipologia
			statement.executeQuery();
		}
		catch (SQLException e) {
				logger.log(Level.SEVERE, "SQLException occurred during the fetch of credentials", e);
		}
		AccomodationModel acc = new AccomodationModel(Info);
		return acc;
	}
}
