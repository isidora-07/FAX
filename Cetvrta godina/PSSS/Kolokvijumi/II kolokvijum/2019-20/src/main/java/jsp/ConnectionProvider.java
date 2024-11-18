package jsp;

import java.sql.*;

public class ConnectionProvider {
	private static Connection con = null;
	
	static {
		try {
			Class.forName(IProvider.DRIVER);
			con = DriverManager.getConnection(IProvider.CONNECTION_URL);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getCon() {
		return con;
	}
}
