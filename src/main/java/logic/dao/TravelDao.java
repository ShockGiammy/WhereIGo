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
import java.sql.Date;

public class TravelDao extends GeneralConnection{
	
	public List<TicketModel> retriveAvailableTickets(UserTravelBean travBean) throws SQLException {
		if(dbConn == null) {
			getConnection();
		}
		List<TicketModel> tickets = new ArrayList<>();
		try(PreparedStatement prep = dbConn.getConnection().prepareStatement("SELECT * FROM Tickets WHERE (depCity=? and arrCity=? and dateOfDep=? and dateOfArr=?)")) {
			prep.setString(1, travBean.getCityOfDep());
			prep.setString(2, travBean.getCityOfArr());
			prep.setString(3, Date.valueOf(travBean.getFirstDay()).toString());
			prep.setString(4, Date.valueOf(travBean.getLastDay()).toString());
			ticketQuery(tickets, prep);
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Error while retriving tickets\n",e);
		}
		return tickets;
	}
	
	/* the executeQuery should be in a function, maybe in the generalConnection Dao*/
	
	public void ticketQuery(List<TicketModel> list, PreparedStatement prep) {
		try (ResultSet rs = prep.executeQuery()){
			while(rs.next()) {
				TicketModel tick= new TicketModel();
				tick.setAll(rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), rs.getFloat(6));
				list.add(tick);
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "RseultSet null \n",e);
		}
	}
}
