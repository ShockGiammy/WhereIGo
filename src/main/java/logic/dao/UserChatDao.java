package logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.SingletonDbConnection;
import logic.model.UserChatModel;

public class UserChatDao {
	
	private static final String EXCEPTION = "Got an exception!";

	public void saveStatus(UserChatModel user) {
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("UPDATE usr Set userStatus = ? where username = ?")){   
			statement.setString(1, user.getStatus());
			statement.setString(2, user.getName());
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
	
	public String retrieveUserStatus(String user) {
		String status = null;
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("Select userStatus From usr Where (username = ?)")){    
			statement.setString(1, user);
			status = retrieveStatus(statement);
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
	
	public List<UserChatModel> retrieveUsersQuery(String userName) {
		List<UserChatModel> users = new ArrayList<>();
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("Select distinct receiver From Chat Where (sender = ?)")){    
			statement.setString(1, userName);
			retrieveUsers(statement, users);
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
			retrieveUsers(statement, users);
		}
		catch (SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, EXCEPTION);
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
		for (UserChatModel user : users) {
			try (Connection conn = SingletonDbConnection.getInstance().getConnection();
					PreparedStatement statement = conn.prepareStatement("Select userStatus, profilePicture From usr Where username = ?")){    
				statement.setString(1, user.getName());
				retrieveUserInfo(statement, user);
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
	
	public void retrieveUserInfo(PreparedStatement statement, UserChatModel user) {
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
	
	public void retrieveUsers(PreparedStatement statement, List<UserChatModel> users) {
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				int helpVar = 0;
				String name = rs.getString(1);
				for (UserChatModel user : users) {
					if (user.getName().contentEquals(name)) {
						helpVar = 1;
					}
				}
				if (helpVar == 0) {
					UserChatModel newUser = new UserChatModel();
					newUser.setName(name);
					users.add(newUser);
				}
			}
		}catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "Users fetch error", e);
		}
	}
	
	public String retrieveStatus(PreparedStatement statement) {
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
	
	public UserChatModel retriveUserPicture(String userName) {
		UserChatModel user = new UserChatModel();
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("Select profilePicture From usr Where username = ?")){    
			statement.setString(1, userName);
			retrievePicture(statement, user);
		}
		catch (SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, EXCEPTION);
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
		return user;
	}
	
	public void retrievePicture(PreparedStatement statement, UserChatModel user) {
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				byte[] image = rs.getBytes(1);
				user.setPicture(image);
			}
		}catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "User fetch error", e);
		}
	}
}
