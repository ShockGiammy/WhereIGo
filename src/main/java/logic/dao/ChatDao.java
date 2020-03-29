package logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.SingletonDbConnection;
import logic.model.Message;
import logic.model.User;

public class ChatDao {
	
	private static final String EXCEPTION = "Got an exception!";

	public void saveMessage(Message msg, String receiver) {
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("INSERT INTO Chat (sender, receiver, message) VALUES (?, ?, ?)")){  
			statement.setString(1, msg.getName());
			statement.setString(2,  receiver);
			statement.setString(3, msg.getMsg());
			statement.execute();
		}
		catch (SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "Exception Occurred\n");
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
	}

	public void setStatus(String user, String status) {
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("UPDATE usr Set userStatus = ? where username = ?")){   
			statement.setString(1,  status);
			statement.setString(2, user);
			statement.execute();
		}
		catch (SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, EXCEPTION);
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
	}
	
	public String getStatus(String user) {
		String status = null;
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("Select userStatus From usr Where (username = ?)")){    
			statement.setString(1, user);
			status = retriveStatus(statement);
		}
		catch (SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, EXCEPTION);
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
		return status;
	}
	
	public List<Message> getSavedMsg(String sender, String receiver) {
		List<Message> messages = new ArrayList<>();
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("Select * From Chat Where (sender = ? and receiver = ?) or (sender = ? and receiver = ?)")){    
			statement.setString(1, sender);
			statement.setString(2, receiver);
			statement.setString(3, receiver);
			statement.setString(4,  sender);
			retriveSavedMessages(statement, messages);
		}
		catch (SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, EXCEPTION);
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
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
			Logger.getLogger("WIG").log(Level.SEVERE, "Messages fetch error", e);
		}
	}
	
		
	public ObservableList<User> getUsersQuery(String userName) {
		ObservableList<User> users = FXCollections.observableArrayList();
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("Select distinct receiver From Chat Where (sender = ?)")){    
			statement.setString(1, userName);
			retriveUsers(statement, users);
		}
		catch (SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, EXCEPTION);
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("Select distinct sender From Chat Where (receiver = ?)")){    
			statement.setString(1, userName);
			retriveUsers(statement, users);
		}
		catch (SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, EXCEPTION);
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
		for (User user : users) {
			try (Connection conn = SingletonDbConnection.getInstance().getConnection();
					PreparedStatement statement = conn.prepareStatement("Select userStatus, profilePicture From usr Where username = ?")){    
				statement.setString(1, user.getName());
				retriveUserInfo(statement, user);
			}
			catch (SQLException e) {
				Logger.getLogger("WIG").log(Level.SEVERE, EXCEPTION);
				Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
			}
			finally {
				SingletonDbConnection.getInstance().closeConn();
			}
		}
		return users;
	}
	
	public void retriveUserInfo(PreparedStatement statement, User user) {
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				user.setStatus(rs.getString(1));
				byte[] image = rs.getBytes(2);
				user.setPicture(image);
			}
		}catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "User fetch error", e);
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
			Logger.getLogger("WIG").log(Level.SEVERE, "Users fetch error", e);
		}
	}
	
	public String retriveStatus(PreparedStatement statement) {
		String status = null;
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				status = rs.getString(1);
			}
		}catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "Status fetch error", e);
		}
		return status;
	}
	
	public void createNewChat(String user, String renter) {
		if (getSavedMsg(user, renter).isEmpty()) {
			try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("INSERT INTO Chat (sender, receiver) VALUES (?, ?)")){  
				statement.setString(1, user);
				statement.setString(2,  renter);
				statement.execute();
			}
			catch (SQLException e) {
				Logger.getLogger("WIG").log(Level.SEVERE, "Exception Occurred\n");
				Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
			}
			finally {
				SingletonDbConnection.getInstance().closeConn();
			}
		}
	}
}
