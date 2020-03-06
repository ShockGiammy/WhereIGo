/* This dao is in charge of retrive datas about all the flight tickets currently available*/

package logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import logic.model.TicketModel;
import logic.beans.UserTravelBean;

public class TravelDao extends GeneralConnection{
	
	public TravelDao() {
		getConnection();
	}
	
	public List<TicketModel> retriveAvailableTickets(UserTravelBean travBean) throws SQLException {
		List<TicketModel> tickets = new ArrayList<>();
		TicketModel tick= new TicketModel();
		ResultSet rs = null;
		try(PreparedStatement prep = dbConn.getConnection().prepareStatement("SELECT * FROM Tickets WHERE (arrCity=? and depCity=?)")) {
			prep.setString(1, travBean.getCityOfArr());
			prep.setString(2, travBean.getCityOfDep());
			rs = prep.executeQuery();
			while(rs.next()) {
				tick.setAll(rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), rs.getFloat(6));
				tickets.add(tick);
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Error while retriving tickets\n",e);
		}
		finally {
			rs.close();
		}
		return tickets;
	}
}
