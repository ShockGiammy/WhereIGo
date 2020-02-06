package logic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;


public class TravelDao {
	
	static Connection connection = null;
	static String url = "jdbc:mysql://93.44.101.57:3306/WhereIGo?autoReconnect=true&useSSL=false";
	static String username = "root";
	static String password = "ksdB120N?";
	
	public static void main(String[] args) throws SQLException {
		connection = DriverManager.getConnection(url, username, password);
		String query = "insert into Travel (LocationName)" + "values (?)";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, "Rome");
		int status = ps.executeUpdate();
		if(status != 0) {
			System.out.println("All ok\n");
		}
		
		Statement<?, ?> state = (Statement)connection.createStatement();
		ResultSet resSet = ((java.sql.Statement) state).executeQuery("SELECT * FROM Travel");
		while(resSet.next()) {
			String index = resSet.getString(1);
			System.out.println("Here's datas :" +resSet.getRow() + ":" + index);
			index = resSet.getString("LocationName");
			System.out.println("Datas by row :" + resSet.getRow() + ":" + index);
			
		}
	}
}