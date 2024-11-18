package jsp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Role {
	private int id;
	private String name;
	
	public Role()
	{
		
	}
	
	public Role(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Role getRoleById(int id)
	{
		PreparedStatement prepStmt;
		try {
			Connection conn = ConnectionProvider.getCon();
			
			String query = "SELECT * FROM role "
					+ "WHERE id=?";
			
			prepStmt = conn.prepareStatement(query);
			
			prepStmt.setInt(1, id);
			
			ResultSet res = prepStmt.executeQuery();
			
			if(res.next())
			{
				int id2 = res.getInt("id");
				String name = res.getString("name").trim();
				
				Role r = new Role(id2, name);
				return r;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int getRoleIdByName(String name)
	{
		PreparedStatement prepStmt;
		try {
			Connection conn = ConnectionProvider.getCon();
			
			String query = "SELECT * FROM role "
					+ "WHERE name=?";
			
			prepStmt = conn.prepareStatement(query);
			prepStmt.setString(1, name);
			
			ResultSet res = prepStmt.executeQuery();
			
			if(res.next())
			{
				return res.getInt("id");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}
}
