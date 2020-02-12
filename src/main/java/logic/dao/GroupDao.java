package logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import logic.beans.GroupBean;
import logic.beans.UserDataBean;

public class GroupDao extends GeneralConnection{
	
	public void retriveGroups(GroupBean beans[], UserDataBean userBean) {
		String cities[] = new String[5];
		getConnection();
		int i = 0;
		try {
			PreparedStatement ps = dbConn.getConnection().prepareStatement("select city from Locations where tipeOfPersonality = ?");
			ps.setString(1, userBean.getPersonality());
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				cities[i] = res.getString(1);
				i += 1;
				}
			i = 0;
			PreparedStatement ps1 = dbConn.getConnection().prepareStatement("select * from travelgroups where (travCity=? or travCity=? or travCity=?)");
			ps1.setString(1, cities[0]);
			ps1.setString(2, cities[1]);
			ps1.setString(3, cities[2]);
			ResultSet res1 = ps1.executeQuery();
			while(res1.next()) {
				beans[i].setGroupOwner(res1.getString(3));
				beans[i].setGroupDestination(res1.getString(2));
				beans[i].setGroupTitle(res1.getString(4));
				i+=1;
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "SQLException occurred\n",e);
		}
	}
}
