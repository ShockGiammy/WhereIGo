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
			PreparedStatement statement = dbConn.getConnection().prepareStatement("INSERT INTO Post(ID,photo,utente,descr,beds,city,address,services,squareMetres,tipologia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");    
			statement.setInt(1, Info.getID());
			statement.setBinaryStream(2,Info.getInputFile(), Info.getFileLength());		//image
			statement.setString(3,"sono io"); 				//user
			statement.setString(4,Info.getDescription()); 			//desc
			statement.setString(5,Info.getBeds());					//beds
			statement.setString(6,Info.getCity());					//city
			statement.setString(7,Info.getAddress());				//address
			statement.setBytes(8,Info.getServices());				//services
			statement.setString(9,Info.getSquareMetres());			//squareMetres
			statement.setString(10,Info.getType());					//tipologia
			statement.execute();
		}
		catch (SQLException e) {
			System.err.println("Got an exception!");
		    System.err.println(e.getMessage());
		}
		AccomodationModel acc = new AccomodationModel(Info);
		return acc;
	}
}
