package logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.beans.RentAccomodationBean;
import logic.model.AccomodationModel;
import logic.model.Chat;

public class ChatDao extends GeneralConnection{
		
	protected Logger logger = Logger.getLogger("WIG");

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

		public Chat queryDB(String sender, String receiver) {
			int fetched = 0;
			int i = 0;
			getConnection();
			ArrayList<String> messages = new ArrayList<>();
			try {
				PreparedStatement statement = dbConn.getConnection().prepareStatement("Select message From DBChat Where (sender = ?) and (receiver = ?)");    
				statement.setString(1, sender);
				statement.setString(2,  receiver);
				ResultSet rs = statement.executeQuery();
				while(rs.next()) {
					System.out.println("Risultato: " + rs.getString(1));
					messages.add(rs.getString(1));
				}
			}
			catch (SQLException e) {
				logger.log(Level.SEVERE, "Got an exception!");
				logger.log(Level.SEVERE, e.getMessage());
			}
			Chat chat = new Chat();
			chat.setMessages(messages);
			return chat;
		}
	}
