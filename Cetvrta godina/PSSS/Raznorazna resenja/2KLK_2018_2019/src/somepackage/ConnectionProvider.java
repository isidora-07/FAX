package somepackage;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	public static Connection conn;
	static {
		
		try {
			Class.forName(IProvider.DRIVER);
			conn = DriverManager.getConnection(IProvider.CONNECTION_URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn() {
		return conn;
	}
}
