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
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static Connection getCon()
	{
		return conn;
	}
}
