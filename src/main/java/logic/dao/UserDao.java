package logic.dao;

import logic.SingletonDbConnection;
import logic.beans.LogInBean;
import logic.beans.UserDataBean;
import logic.exceptions.TakenUsernameException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class UserDao extends GeneralConnection{
	
	public void insertPersonality(String personality,String username) {
		try (PreparedStatement stm = SingletonDbConnection.getInstance().getConnection().prepareStatement("update usr set tipeOfPersonality = ? where username = ?")){
			stm.setString(1, personality);
			stm.setString(2, username);
			stm.execute();
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "SQLException occured during insert", e);
		}
	}
	
	public int checkLogInInfo(LogInBean bean, UserDataBean usrBean) {
		int ret = 0;
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("SELECT tipeOfUser,tipeOfPersonality,profilePicture FROM usr WHERE (username=? and passw=?)")){    
			statement.setString(1,bean.getUserName());
			statement.setString(2,bean.getPasw());
			ret = getLoggedUser(statement, usrBean);
			usrBean.setUserName(bean.getUserName());
		}catch (SQLException e) {
				logger.log(Level.SEVERE, "SQLException occurred during the fetch of credentials", e);
		}
		return ret;
	}
	
	public void insertNewUser(UserDataBean usrBean) throws TakenUsernameException {
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("INSERT INTO usr(username, passw, nome, surname, dateOfBirth, gender, tipeOfUser, profilePicture, userStatus) VALUES(?,?,?,?,?,?,?,?,?)")){
			statement.setString(1, usrBean.getUsername());
			statement.setString(2, usrBean.getPassword());
			statement.setString(3, usrBean.getName());
			statement.setString(4, usrBean.getSurname());
			statement.setString(5, usrBean.getDateOfBirth());
			statement.setString(6, usrBean.getGender());
			statement.setString(7, usrBean.getType());
			statement.setBinaryStream(8, usrBean.getInputFile(), usrBean.getFileLength());
			statement.setString(9, "offline");
			statement.execute();
		}catch(SQLException e) {
			throw new TakenUsernameException(e.getMessage());
		}
	}
	
	public void getUserDatas(UserDataBean usrBean) {
		try (PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM usr WHERE (username = ?)")){
			statement.setString(1, usrBean.getUsername());
			retriveUser(statement, usrBean);
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "SQLException on fetchin user datas\n", e);
		}
	}
	
	public int getLoggedUser(PreparedStatement statement, UserDataBean usrBean) {
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				usrBean.setType(rs.getString(1));
				if(rs.getString(2) != null) {
					usrBean.setPersonality(rs.getString(2));
				}
				usrBean.setByteSteam(rs.getBytes(3));
				return 1;
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Cannot get logged user", e);
		}
		return 0;
	}
	
	public void retriveUser(PreparedStatement statement, UserDataBean usrBean) {
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				usrBean.setName(rs.getString(3));
				usrBean.setSurname(rs.getString(4));
				usrBean.setDateOfBirth(rs.getString(5));
				usrBean.setGender(rs.getString(6));
				usrBean.setType(rs.getString(7));
				usrBean.setPersonality(rs.getString(8));
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "ResultSet fetch fail !", e);
		}
	}
}
