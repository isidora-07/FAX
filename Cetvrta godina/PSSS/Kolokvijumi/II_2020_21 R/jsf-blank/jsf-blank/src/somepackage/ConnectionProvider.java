package somepackage;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
private static Connection conn = null;
	
	static {
		try {
			Class.forName(IProvider.DRIVER);
			conn = DriverManager.getConnection(IProvider.CONNECTION_URL);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static Connection getConn() {
		return conn;
	}
}