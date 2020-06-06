package logic.dao;

import logic.SingletonDbConnection;
import logic.exceptions.DuplicateUsernameException;
import logic.model.UserModel;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao {
	
	public void insertPersonality(UserModel usrModel) {
		try (PreparedStatement stm = SingletonDbConnection.getInstance().getConnection().prepareStatement("update usr set tipeOfPersonality = ? where username = ?")){
			stm.setString(1, usrModel.getUserPersonality());
			stm.setString(2, usrModel.getUserName());
			stm.execute();
		}
		catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "SQLException occured during insert");
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
	}
	
	public int checkLogInInfo(UserModel usrMod) {
		int ret = 0;
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("SELECT tipeOfUser,tipeOfPersonality,profilePicture FROM usr WHERE (username=? and passw=?)")){    
			statement.setString(1,usrMod.getUserName());
			statement.setString(2,usrMod.getPaswd());
			ret = getLoggedUser(statement, usrMod);
		}
		catch (SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "SQLException occurred during the fetch of credentials");
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
		return ret;
	}
	
	public void insertNewUser(UserModel usrModel) throws DuplicateUsernameException {
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("INSERT INTO usr(username, passw, nome, surname, dateOfBirth, gender, tipeOfUser, profilePicture, userStatus) VALUES(?,?,?,?,?,?,?,?,?)")){
			statement.setString(1, usrModel.getUserName());
			statement.setString(2, usrModel.getPaswd());
			statement.setString(3, usrModel.getName());
			statement.setString(4, usrModel.getSurname());
			statement.setDate(5, Date.valueOf(usrModel.getDateOfBirth()));
			statement.setString(6, usrModel.getGender());
			statement.setString(7, usrModel.getUserType());
			statement.setBinaryStream(8, usrModel.getInputFile(), usrModel.getFileLength());
			statement.setString(9, "offline");
			statement.execute();
		}
		catch(SQLException e) {
			if(e.getErrorCode() == 1062) {
				throw new DuplicateUsernameException(e.getMessage());
			}
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
	}
	
	public int getLoggedUser(PreparedStatement statement, UserModel usrMod) {
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				if(rs.getString(2) != null) {
					usrMod.setUserPersonality(rs.getString(2));
				}
				usrMod.setLogUsrCred(rs.getString(1), rs.getBytes(3));
				return 1;
			}
		}
		catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "Cannot get logged user");
		}
		return 0;
	}
	
	public void findSimilarUsers(List<UserModel> usersList, UserModel logUsr) {
		if(logUsr.getUserPersonality() != null) {
			try(PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("select username, profilePicture from usr where (tipeOfPersonality=? and username !=?)")){
				statement.setString(1, logUsr.getUserPersonality());
				statement.setString(2, logUsr.getUserName());
				getSimUsers( statement,  usersList);
			}
			catch(SQLException e) {
				Logger.getLogger("WIG").log(Level.SEVERE, "Error while retreiving similar users");
			}
			finally {
				SingletonDbConnection.getInstance().closeConn();
			}
		}
	}
	
	public void getSimUsers(PreparedStatement statement, List<UserModel> usrModel) {
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				UserModel usr = new UserModel();
				usr.setUsrNameAndPic(rs.getString(1), rs.getBytes(2));
				usrModel.add(usr);
			}
		}
		catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "Error while fetching users' datas");
		}
	}
}
