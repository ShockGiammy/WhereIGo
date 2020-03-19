/* This dao is in charge of retrive datas about all the flight tickets currently available*/

package logic.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import logic.model.TicketModel;
import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;
import java.sql.Date;

public class TravelDao extends GeneralConnection{
	
	public List<TicketModel> retriveAvailableTickets(UserTravelBean travBean, UserDataBean bean) throws SQLException {
		getConnection();
		List<TicketModel> tickets = new ArrayList<>();
		if(checkIfBooked(travBean, bean)) {
			return tickets;
		}
		try(PreparedStatement prep = dbConn.getConnection().prepareStatement("SELECT * FROM Tickets WHERE (depCity=? and arrCity=? and dateOfDep=? and dateOfArr=? and numOfTick > 0)")) {
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
	
	public void ticketQuery(List<TicketModel> list, PreparedStatement prep) {
		try (ResultSet rs = prep.executeQuery()){
			while(rs.next()) {
				TicketModel tick= new TicketModel();
				tick.setAll(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), rs.getFloat(6));
				list.add(tick);
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "ResultSet null \n",e);
		}
	}
	
	public boolean checkIfBooked(UserTravelBean trav, UserDataBean dataBean) {
		List<TicketModel> tickList = new ArrayList<>();
		getUserTickets(dataBean, tickList);
		for(int i = 0; i < tickList.size(); i++) {
			if((tickList.get(i).getDepCity().toLowerCase()).equals(trav.getCityOfDep()) && (tickList.get(i).getArrCity().toLowerCase()).equals(trav.getCityOfArr()) && tickList.get(i).getDepDay().compareTo(trav.getFirstDay()) == 0 && tickList.get(i).getArrDay().compareTo(trav.getLastDay()) == 0) {
				return true;
			}
		}
		return false;
	}
	
	public void saveBoughtTickets(TicketModel tick, UserDataBean dataBean) {
		getConnection();
		try(PreparedStatement statement = dbConn.getConnection().prepareStatement("INSERT INTO Buys(ticket, passenger) VALUES(?,?)")){
			statement.setString(2, dataBean.getUsername());
			statement.setInt(1, tick.getId());
			statement.execute();
			scaleNumberOfTickets(tick);
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Failed to update tickets \n",e);
		}
	}
	
	public void scaleNumberOfTickets(TicketModel tick) {
		getConnection();
		try(PreparedStatement statement = dbConn.getConnection().prepareStatement("UPDATE tickets SET numOfTick = numOfTick-1 WHERE ID=?")){
			statement.setInt(1, tick.getId());
			statement.execute();
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Failed to update number of tickets \n",e);
		}
	}
	
	public void getUserTickets(UserDataBean dataBean, List<TicketModel> tickList) {
		getConnection();
		try(PreparedStatement statement = dbConn.getConnection().prepareStatement("SELECT * FROM tickets JOIN buys on tickets.ID = buys.ticket WHERE (passenger=?)")){
			statement.setString(1, dataBean.getUsername());
			fetchTickets(statement, tickList);
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Error while fetching tickets\n",e);
		}
	}
	
	public void fetchTickets(PreparedStatement statement, List<TicketModel> tickList) {
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				TicketModel tick = new TicketModel();
				tick.setAll(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), rs.getFloat(6));
				tickList.add(tick);
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Cannot manipulate tickets resultset \n",e);
		}
	}
	
	public void deleteTick(UserTravelBean travBean, UserDataBean dataBean) {
		getConnection();
		try(PreparedStatement statement = dbConn.getConnection().prepareStatement("delete from Buys where ticket=? and passenger=?")){
			statement.setInt(1, travBean.getId());
			statement.setString(2, dataBean.getUsername());
			statement.execute();
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Cannot delete ticket \n",e);
		}
	}
	
	public void getSuggestedTickets(UserTravelBean travBean, List<TicketModel> tickList) {
		getConnection();
		try(PreparedStatement statement = dbConn.getConnection().prepareStatement("select ID, depCity, dateOfDep, dateOfArr, cost from tickets where(arrCity=? and numoftick > 0)")){
			statement.setString(1, travBean.getCityOfArr());
			findSuggTickets(statement, tickList, travBean);
		}catch(SQLException e) {
			logger.log(Level.SEVERE,e.getMessage());
		}
	}
	
	public void findSuggTickets(PreparedStatement statement, List<TicketModel> tickList, UserTravelBean travBean) {
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				TicketModel tick = new TicketModel();
				tick.setAll(rs.getInt(1), rs.getString(2), travBean.getCityOfArr(), rs.getDate(3).toLocalDate(), rs.getDate(4).toLocalDate(), rs.getFloat(5));
				tickList.add(tick);
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE,e.getMessage());
		}
	}
}
