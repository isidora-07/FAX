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
			System.out.println("Greska pri konekciji...");
			System.out.println(e.getMessage());
		}

	}

	public static Connection getConn() {
		return conn;
	}
}
