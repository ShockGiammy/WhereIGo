package logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import logic.beans.RentAccomodationBean;
import logic.model.AccomodationModel;

public class AccomodationCreator extends GeneralConnection{

	public AccomodationModel createAccomodation(RentAccomodationBean Info) {
		getConnection();
		try {
			PreparedStatement statement = dbConn.getConnection().prepareStatement("INSERT INTO Post(ID,photo,utente,descr,beds,city,address,services,squareMetres,tipologia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");    
			statement.setInt(1, Info.getID());
			statement.setBinaryStream(2,Info.getInputFile(), Info.getFileLength());		//image
			statement.setString(3,Info.getRenter()); 				//user
			statement.setString(4,Info.getDescription()); 			//description
			statement.setString(5,Info.getBeds());					//beds
			statement.setString(6,Info.getCity());					//city
			statement.setString(7,Info.getAddress());				//address
			statement.setBytes(8,Info.getServices());				//services
			statement.setString(9,Info.getSquareMetres());			//squareMetres
			statement.setString(10,Info.getType());					//type
			statement.execute();
		}
		catch (SQLException e) {
			System.err.println("Got an exception!");
		    System.err.println(e.getMessage());
		}
		AccomodationModel acc = new AccomodationModel(Info);
		return acc;
	}

	public AccomodationModel[] queryDB(RentAccomodationBean bean) {
		int fetched = 0;
		getConnection();
		AccomodationModel[] acc = new AccomodationModel[6];
		try {
			PreparedStatement statement = dbConn.getConnection().prepareStatement("Select * From Post");    
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				if (fetched<6) {
					bean.setID(rs.getInt(1));
					byte[] image = rs.getBytes(2);
					bean.setInputStream(image);
					bean.setRenter(rs.getString(3));
					bean.setDescription(rs.getString(4));
					bean.setBeds(rs.getString(5));
					bean.setCity(rs.getString(6));
					bean.setAddress(rs.getString(7));
					bean.setServices(rs.getBytes(8));
					bean.setSquareMetres(rs.getString(9));
					bean.setType(rs.getString(10));
					acc[fetched] = new AccomodationModel(bean);
					fetched +=1;
				}
			}
		}
		catch (SQLException e) {
			System.err.println("Got an exception!");
		    System.err.println(e.getMessage());
		}
		return acc;
	}
}
