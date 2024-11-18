package jsp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static Connection conn = null;
	
	static {
		try {
			Class.forName(IProvider.DRIVER);
			conn = DriverManager.getConnection(IProvider.CONNECTION_URL);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConn() {
		return conn;
	}
}
