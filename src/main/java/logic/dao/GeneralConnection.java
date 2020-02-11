package logic.dao;
import java.util.logging.Logger;
import java.util.logging.Level;
import logic.SingletonDbConnection;

public class GeneralConnection {
	private String url = "jdbc:mysql://localhost/whereigo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String username = "root";
	private String password = "pippo1998";
	protected SingletonDbConnection dbConn;
	protected Logger logger = Logger.getLogger(UserDao.class.getName());
	
	public void getConnection() {
		try{
			String driverName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverName);
			dbConn = SingletonDbConnection.getInstance(this.url, this.username, this.password);
		}catch(ClassNotFoundException e) {
			logger.log(Level.SEVERE, "ClassNotFoundException occurred", e);
		}
	}
}
