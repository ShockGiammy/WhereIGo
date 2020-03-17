package logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.model.Message;
import logic.model.User;

public class ChatDao extends GeneralConnection{
	
	private static final String EXCEPTION = "Got an exception!";

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
			logger.log(Level.SEVERE, EXCEPTION);
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
			logger.log(Level.SEVERE, EXCEPTION);
			logger.log(Level.SEVERE, e.getMessage());
		}
		return status;
	}
	
	public List<Message> getSavedMsg(String sender, String receiver) {
		getConnection();
		List<Message> messages = new ArrayList<>();
		try (PreparedStatement statement = dbConn.getConnection().prepareStatement("Select * From Chat Where (sender = ? and receiver = ?) or (sender = ? and receiver = ?)")){    
			statement.setString(1, sender);
			statement.setString(2, receiver);
			statement.setString(3, receiver);
			statement.setString(4,  sender);
			retriveSavedMessages(statement, messages);
		}
		catch (SQLException e) {
			logger.log(Level.SEVERE, EXCEPTION);
			logger.log(Level.SEVERE, e.getMessage());
		}
		return messages;
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
	
		
	public ObservableList<User> getUsersQuery(String userName) {
		getConnection();
		ObservableList<User> users = FXCollections.observableArrayList();
		try (PreparedStatement statement = dbConn.getConnection().prepareStatement("Select distinct receiver From Chat Where (sender = ?)")){    
			statement.setString(1, userName);
			retriveUsers(statement, users);
		}
		catch (SQLException e) {
			logger.log(Level.SEVERE, EXCEPTION);
			logger.log(Level.SEVERE, e.getMessage());
		}
		try (PreparedStatement statement = dbConn.getConnection().prepareStatement("Select distinct sender From Chat Where (receiver = ?)")){    
			statement.setString(1, userName);
			retriveUsers(statement, users);
		}
		catch (SQLException e) {
			logger.log(Level.SEVERE, EXCEPTION);
			logger.log(Level.SEVERE, e.getMessage());
		}
		for (User user : users) {
			try (PreparedStatement statement = dbConn.getConnection().prepareStatement("Select userStatus, profilePicture From usr Where username = ?")){    
				statement.setString(1, user.getName());
				retriveUserInfo(statement, user);
			}
			catch (SQLException e) {
				logger.log(Level.SEVERE, EXCEPTION);
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
		return users;
	}
	
	public void retriveUserInfo(PreparedStatement statement, User user) {
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				user.setStatus(rs.getString(1));
				user.setPicture(rs.getBytes(2));
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "User fetch error", e);
		}
	}
	
	public void retriveUsers(PreparedStatement statement, ObservableList<User> users) {
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				int helpVar = 0;
				String name = rs.getString(1);
				for (User user : users) {
					if (user.getName().contentEquals(name)) {
						helpVar = 1;
					}
				}
				if (helpVar == 0) {
					User newUser = new User();
					newUser.setName(name);
					users.add(newUser);
				}
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
			logger.log(Level.SEVERE, "Status fetch error", e);
		}
		return status;
	}
	
	public void createNewChat(String user, String renter) {
		if (getSavedMsg(user, renter) == null) {
			getConnection();
			try (PreparedStatement statement = dbConn.getConnection().prepareStatement("INSERT INTO Chat (sender, receiver) VALUES (?, ?)")){  
				statement.setString(1, user);
				statement.setString(2,  renter);
				statement.execute();
			}
			catch (SQLException e) {
				logger.log(Level.SEVERE, "Exception Occurred\n");
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
	}
}
