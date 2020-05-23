/* This dao is in charge of retrive datas about all the flight tickets currently available*/
package logic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.model.TicketModel;
import logic.model.UserModel;
import logic.SingletonDbConnection;
import java.sql.Date;

public class TravelDao {
	
	public void findCurrTravels(List<TicketModel> tickModList) {
		try(PreparedStatement prep = SingletonDbConnection.getInstance().getConnection().prepareStatement("SELECT DISTINCT depcity,arrcity FROM Tickets")){
			fetchCurrTicks(prep, tickModList);
		} catch (SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "No available tickets at the moment",e);
		}
	}
	
	public List<TicketModel> retriveAvailableTickets(TicketModel tickMod, UserModel usrMod) {
		List<TicketModel> tickets = new ArrayList<>();
		if(checkIfBooked(tickMod, usrMod)) {
			return tickets;
		}
		try(PreparedStatement prep = SingletonDbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Tickets WHERE (depCity=? and arrCity=? and dateOfDep=? and dateOfArr=? and numOfTick > 0)")) {
			prep.setString(1, tickMod.getDepCity());
			prep.setString(2, tickMod.getArrCity());
			prep.setString(3, Date.valueOf(tickMod.getDepDay()).toString());
			prep.setString(4, Date.valueOf(tickMod.getArrDay()).toString());
			ticketQuery(tickets, prep);
		}
		catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "Error while retriving tickets\n",e);
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
		return tickets;
	}
	
	public void ticketQuery(List<TicketModel> list, PreparedStatement prep) {
		try (ResultSet rs = prep.executeQuery()){
			setFetchedTick(rs, list);
		}
		catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "ResultSet null \n",e);
		}
	}
	
	public boolean checkIfBooked(TicketModel tickMod, UserModel usrMod) {
		List<TicketModel> tickList = new ArrayList<>();
		getUserTickets(usrMod, tickList);
		for(int i = 0; i < tickList.size(); i++) {
			if((tickList.get(i).getDepCity()).equalsIgnoreCase(tickMod.getDepCity()) && (tickList.get(i).getArrCity()).equalsIgnoreCase(tickMod.getArrCity()) && tickList.get(i).getDepDay().compareTo(tickMod.getDepDay()) == 0 && tickList.get(i).getArrDay().compareTo(tickMod.getArrDay()) == 0) {
				return true;
			}
		}
		return false;
	}
	
	public void saveBoughtTickets(TicketModel tick, UserModel dataBean) {
		try(PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("INSERT INTO Buys(ticket, passenger) VALUES(?,?)")){
			statement.setString(2, dataBean.getUserName());
			statement.setInt(1, tick.getId());
			statement.execute();
			updateNumberOfTickets(tick,0);
		}
		catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "Failed to update tickets \n",e);
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
	}
	
	public void updateNumberOfTickets(TicketModel tick, int operation) {
		if(operation == 0) {
			try(PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("UPDATE tickets SET numOfTick = numOfTick-1 WHERE ID=?")){
				statement.setInt(1, tick.getId());
				statement.execute();
			}
			catch(SQLException e) {
				Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
			}
			finally {
				SingletonDbConnection.getInstance().closeConn();
			}
		}
		else if(operation == 1) {
			try(PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("UPDATE tickets SET numOfTick = numOfTick+1 WHERE ID=?")){
				statement.setInt(1, tick.getId());
				statement.execute();
			}
			catch(SQLException e) {
				Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
			}
			finally {
				SingletonDbConnection.getInstance().closeConn();
			}
		}
	}
	
	public void getUserTickets(UserModel usrMod, List<TicketModel> tickList) {
		try(PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM tickets JOIN buys on tickets.ID = buys.ticket WHERE (passenger=?)")){
			statement.setString(1, usrMod.getUserName());
			fetchTickets(statement, tickList);
		}
		catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "Error while fetching tickets\n",e);
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
	}
	
	public void fetchTickets(PreparedStatement statement, List<TicketModel> tickList) {
		try(ResultSet rs = statement.executeQuery()){
			setFetchedTick(rs, tickList);
		}
		catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "Cannot manipulate tickets resultset \n",e);
		}
	}
	
	public void deleteTick(TicketModel tickModel, UserModel usrMod) {
		try(PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("delete from Buys where ticket=? and passenger=?")){
			statement.setInt(1, tickModel.getId());
			statement.setString(2, usrMod.getUserName());
			statement.execute();
			updateNumberOfTickets(tickModel, 1);
		}
		catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "Cannot delete ticket \n",e);
		}
		finally {
			SingletonDbConnection.getInstance().getConnection();
		}
	}
	
	public void getSuggestedTickets(TicketModel tickMod, UserModel usrMod, List<TicketModel> tickList) {
		if(checkIfSuggBooked(tickMod, usrMod)) {
			return;
		}
		try(PreparedStatement statement = SingletonDbConnection.getInstance().getConnection().prepareStatement("select ID, depCity, dateOfDep, dateOfArr, cost from tickets where(arrCity=? and numoftick > 0)")){
			statement.setString(1, tickMod.getArrCity());
			findSuggTickets(statement, tickList, tickMod);
		}
		catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE,e.getMessage());
		}
		finally {
			SingletonDbConnection.getInstance().closeConn();
		}
	}
	
	public void findSuggTickets(PreparedStatement statement, List<TicketModel> tickList, TicketModel tickModel) {
		try(ResultSet rs = statement.executeQuery()){
			while(rs.next()) {
				TicketModel tick = new TicketModel(rs.getString(2), tickModel.getArrCity(), rs.getDate(3).toLocalDate(), rs.getDate(4).toLocalDate());
				tick.setId(rs.getInt(1));
				tick.setCost(rs.getFloat(5));
				tickList.add(tick);
			}
		}
		catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE,e.getMessage());
		}
	}
	
	public boolean checkIfSuggBooked(TicketModel tickMod, UserModel usrMod) {
		List<TicketModel> tickList = new ArrayList<>();
		getUserTickets(usrMod, tickList);
		for(int i = 0; i < tickList.size(); i++) {
			if(tickList.get(i).getArrCity().equalsIgnoreCase(tickMod.getArrCity())) {
				return true;
			}
		}
		return false;
	}
	
	public void setFetchedTick(ResultSet rs, List<TicketModel> tickList) {
		try {
			while(rs.next()) {
				TicketModel tick= new TicketModel(rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate());
				tick.setId(rs.getInt(1));
				tick.setCost(rs.getFloat(6));
				tickList.add(tick);
			}
		}
		catch(SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "Cannot manipulate tickets resultset \n",e);
		}
	}
	
	private void fetchCurrTicks(PreparedStatement ps, List<TicketModel> listTick) {
		try(ResultSet rs = ps.executeQuery()){
			while(rs.next()) {
				TicketModel tickMod = new TicketModel(rs.getString(1), rs.getString(2));
				listTick.add(tickMod);
			}
		} catch (SQLException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, "Error while fetching tickets : no ticket available \n",e);
		}
	}
}
