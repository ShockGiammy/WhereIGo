package logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.model.Message;
import logic.model.User;

public class ChatDao extends GeneralConnection{

	public void saveMessage(Message msg, String receiver) {
		getConnection();
		try (PreparedStatement statement = dbConn.getConnection().prepareStatement("INSERT INTO Chat (sender, receiver, message) VALUES (?, ?, ?)")){  
			statement.setString(1, msg.getName());
			statement.setString(2,  receiver);
			statement.setString(3, msg.getMsg());
			statement.execute();
		}
		catch (SQLException e) {
			logger.log(Level.SEVERE, "Exception Occurred\n");
			logger.log(Level.SEVERE, e.getMessage());
		}
	}

	public void setStatus(String user, String status) {
		getConnection();
		try (PreparedStatement statement = dbConn.getConnection().prepareStatement("UPDATE usr Set userStatus = ? where username = ?")){   
			statement.setString(1,  status);
			statement.setString(2, user);
			statement.execute();
		}
		catch (SQLException e) {
			logger.log(Level.SEVERE, "Got an exception!");
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
	
	public String getStatus(String user) {
		getConnection();
		String status = null;
		try (PreparedStatement statement = dbConn.getConnection().prepareStatement("Select userStatus From usr Where (username = ?)")){    
			statement.setString(1, user);
			status = retriveStatus(statement);
		}
		catch (SQLException e) {
			logger.log(Level.SEVERE, "Got an exception!");
			logger.log(Level.SEVERE, e.getMessage());
		}
		return status;
	}
	
	public List<Message> getSavedMsg(String sender, String receiver) {
		getConnection();
		List<Message> messages = new ArrayList<>();
		try (PreparedStatement statement = dbConn.getConnection().prepareStatement("Select * From Chat Where (sender = ?) and (receiver = ?)")){    
			statement.setString(1, sender);
			statement.setString(2,  receiver);
			retriveSavedMessages(statement, messages);
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
			retriveUsers(statement, users);
		}
		catch (SQLException e) {
			logger.log(Level.SEVERE, "Got an exception!");
			logger.log(Level.SEVERE, e.getMessage());
		}
		return users;
	}
	
	public void retriveSavedMessages(PreparedStatement statement, List<Message> messages) {
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				Message message = new Message();
				message.setName(rs.getString(2));
				message.setMsg(rs.getString(4));
				messages.add(message);
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Messages fetch error", e);
		}
	}
	
	public void retriveUsers(PreparedStatement statement, ObservableList<User> users) {
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				User user = new User();
				user.setName(rs.getString(1));
				users.add(user);
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Users fetch error", e);
		}
	}
	
	public String retriveStatus(PreparedStatement statement) {
		String status = null;
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				status = rs.getString(1);
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Users fetch error", e);
		}
		return status;
	}
}
