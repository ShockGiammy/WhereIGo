package logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.SingletonDbConnection;
import logic.model.Message;
import logic.model.PrivateMessage;

public class ChatDao {
	
	private static final String EXCEPTION = "Got an exception!";

	public void saveMessage(Message createMessage) {
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("INSERT INTO Chat (sender, receiver, message) VALUES (?, ?, ?)")){  
			statement.setString(1, createMessage.getName());
			statement.setString(2, createMessage.getGroupOrReceiver());
			statement.setString(3, createMessage.getMsg());
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

	
	
	public List<Message> retrieveSavedMsg(String sender, String receiver) {
		List<Message> messages = new ArrayList<>();
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("Select * From Chat Where (sender = ? and receiver = ?) or (sender = ? and receiver = ?)")){    
			statement.setString(1, sender);
			statement.setString(2, receiver);
			statement.setString(3, receiver);
			statement.setString(4,  sender);
			retrieveSavedMessages(statement, messages);
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
	
	public List<Message> retrieveGroupMsg(String group) {
		List<Message> messages = new ArrayList<>();
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("Select * From Chat Where receiver = ?")){    
			statement.setString(1, group);
			retrieveSavedMessages(statement, messages);
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
	
	public void retrieveSavedMessages(PreparedStatement statement, List<Message> messages) {
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				PrivateMessage message = new PrivateMessage();
				message.setName(rs.getString(2));
				message.setMsg(rs.getString(4));
				messages.add(message);
			}
		}catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "Messages fetch error", e);
		}
	}
	
	public void createNewChat(Message newMessage) {
		if (retrieveSavedMsg(newMessage.getName(), newMessage.getGroupOrReceiver()).isEmpty()) {
			try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("INSERT INTO Chat (sender, receiver) VALUES (?, ?)")){  
				statement.setString(1, newMessage.getName());
				statement.setString(2, newMessage.getGroupOrReceiver());
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
