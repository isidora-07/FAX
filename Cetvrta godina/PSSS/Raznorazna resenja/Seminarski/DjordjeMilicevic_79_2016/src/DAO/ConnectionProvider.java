package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
//	public static Connection conn;
//	static {
//		try {
//			Class.forName(IProvider.DRIVER);
//			conn = DriverManager.getConnection(IProvider.CONNECTION_URL);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public static Connection getConn() {
		// return conn;
		Connection conn = null;
		 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(IProvider.CONNECTION_URL);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return conn;
	}
}
