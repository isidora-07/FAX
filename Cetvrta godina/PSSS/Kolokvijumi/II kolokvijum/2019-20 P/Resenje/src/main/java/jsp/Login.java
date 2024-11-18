package jsp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
	private int id;
	private String username;
	private String password;
	
	public Login()
	{
		
	}
	
	public Login(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Login(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static Login LoginUser(Login l)
	{			
		Login login = null;
		PreparedStatement prepStmt;
		try {
			Connection conn = ConnectionProvider.getConn();
			
			String query = "SELECT * FROM login WHERE username=? AND password=?";
			
			prepStmt = conn.prepareStatement(query);
	
			prepStmt.setString(1, l.getUsername());
			prepStmt.setString(2, l.getPassword());
			
			ResultSet res = prepStmt.executeQuery();
			
			if(res.next())
			{
				int id = res.getInt("id");
				String username = res.getString("username").trim();
				String password = res.getString("password").trim();
				
				login = new Login(id, username, password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return login;
	}

}
