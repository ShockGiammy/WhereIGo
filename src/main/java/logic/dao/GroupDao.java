package logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

import logic.beans.GroupBean;

public class GroupDao extends GeneralConnection{
	private int i = 0;
	
	public void retriveGroups(List<GroupBean> beans) {
		getConnection();
		try (PreparedStatement statement = dbConn.getConnection().prepareStatement("select * from travelgroups where (travCity=? or travCity=? or travCity=?)")){
			statement.setString(1, beans.get(0).getGroupDestination());
			statement.setString(2, beans.get(1).getGroupDestination());
			statement.setString(3, beans.get(1).getGroupDestination());
			getGroupsDatas(statement, beans);
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "SQLException occurred\n",e);
		}
	}
	
	public void getGroupsDatas(PreparedStatement statement, List<GroupBean> beans) {
		try(ResultSet rs = statement.executeQuery()){
				while(rs.next()) {
					beans.get(i).setGroupOwner(rs.getString(3));
					beans.get(i).setGroupTitle(rs.getString(4));
					i+=1;
				}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Group fetch error", e);
		}
	}
}
