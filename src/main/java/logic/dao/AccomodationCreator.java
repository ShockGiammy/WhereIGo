package logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.beans.RentAccomodationBean;
import logic.model.AccomodationModel;

public class AccomodationCreator extends GeneralConnection{
	
	protected Logger logger = Logger.getLogger(UserDao.class.getName());

	public AccomodationModel createAccomodation(RentAccomodationBean info) {
		getConnection();
		try {
			PreparedStatement statement = dbConn.getConnection().prepareStatement("INSERT INTO Post(ID,photo,utente,descr,beds,city,address,services,squareMetres,tipologia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");    
			statement.setInt(1, info.getID());
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
			logger.log(Level.SEVERE, "Got an exception!");
			logger.log(Level.SEVERE, e.getMessage());
		}
		return new AccomodationModel(info);
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
			logger.log(Level.SEVERE, "Got an exception!");
			logger.log(Level.SEVERE, e.getMessage());
		}
		return acc;
	}
}
