package logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.messages.Message;
import com.messages.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChatDao extends GeneralConnection{

	public void saveMessage(Message msg) {
		getConnection();
		try (PreparedStatement statement = dbConn.getConnection().prepareStatement("INSERT INTO Chat (sender, receiver, message) VALUES (?, ?, ?)")){  
			statement.setString(1, msg.getName());
			statement.setString(2,  "pippo");
			statement.setString(3, msg.getMsg());
			statement.execute();
		}
		catch (SQLException e) {
			logger.log(Level.SEVERE, "Got an exception!");
			logger.log(Level.SEVERE, e.getMessage());
		}
	}

	public void setOnlineStatus() {
		getConnection();
		try (PreparedStatement statement = dbConn.getConnection().prepareStatement("UPDATE usr Set userStatus = 'online' where username = ?")){   
			statement.setString(1, "ciao");
			statement.execute();
		}
		catch (SQLException e) {
			logger.log(Level.SEVERE, "Got an exception!");
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
	
	public List<Message> getSavedMsg(String sender, String receiver) {
		getConnection();
		List<Message> messages = new ArrayList<>();
		try (PreparedStatement statement = dbConn.getConnection().prepareStatement("Select * From Chat Where (sender = ?) and (receiver = ?)")){    
			statement.setString(1, sender);
			statement.setString(2,  receiver);
			ResultSet rs = getDatas(statement);
			if(rs == null) {
				return messages;
			}
			while(rs.next()) {
				Message message = new Message();
				message.setName(rs.getString(2));
				message.setMsg(rs.getString(4));
				messages.add(message);
			}
		}
		catch (SQLException e) {
			logger.log(Level.SEVERE, "Got an exception!");
			logger.log(Level.SEVERE, e.getMessage());
		}
		return messages;
	}
		
	public ObservableList<User> getUsersQuery(String sender) {
		getConnection();
		ObservableList<User> users = FXCollections.observableArrayList();
		try (PreparedStatement statement = dbConn.getConnection().prepareStatement("Select distinct receiver From Chat Where (sender = ?)")){    
			statement.setString(1, sender);
			ResultSet rs = getDatas(statement);
			if(rs == null){
				return users;
			}
			while(rs.next()) {
				User user = new User();
				user.setName(rs.getString(1));
				users.add(user);
			}
		}
		catch (SQLException e) {
			logger.log(Level.SEVERE, "Got an exception!");
			logger.log(Level.SEVERE, e.getMessage());
		}
		return users;
	}
}
