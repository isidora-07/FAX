package jsp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class User {
	private int Id;
	private String username;
	private String password;
	private String name;
	private int year;
	private int role_id;
	private List<User> listaKonobara;
	
	public User()
	{
		
	}
	
	public User(String username, String password, String name, int year, int role_id) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.year = year;
		this.role_id = role_id;
	}
	
	public User(int id, String username, String password, String name, int year, int role_id) {
		super();
		this.Id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.year = year;
		this.role_id = role_id;
	}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	
	public User Login(String name, String pass)
	{
		try {
			Connection conn = ConnectionProvider.getCon();
			
			String query = "SELECT * FROM user "
					+ "WHERE username=? AND password=?";
			
			PreparedStatement prepStmt = conn.prepareStatement(query);
			
			prepStmt.setString(1, name);
			prepStmt.setString(2, pass);
			
			ResultSet res = prepStmt.executeQuery();
			
			if(res.next())
			{
				int id = res.getInt("Id");
				String username = res.getString("username").trim();
				String password = res.getString("password").trim();
				String name2 = res.getString("name").trim();
				int year = res.getInt("year");
				int role_id = res.getInt("role_id");
				
				User u = new User(id, username, password, name2, year, role_id);
				return u;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<User> ListaKonobara()
	{
		Role r = new Role();
		int idRole = r.getRoleIdByName("konobar");
		
		if(idRole != -1) // da li uloga postoji
		{
			try {
				listaKonobara = new ArrayList<User>();
				
				Connection conn = ConnectionProvider.getCon();
				
				String query = "SELECT * FROM user "
						+ "WHERE role_id=?";
				
				PreparedStatement prepStmt = conn.prepareStatement(query);
				prepStmt.setInt(1, idRole);
				
				ResultSet res = prepStmt.executeQuery();
				
				while(res.next())
				{
					int id = res.getInt("Id");
					String username = res.getString("username").trim();
					String password = res.getString("password").trim();
					String name = res.getString("name").trim();
					int year = res.getInt("year");
					int role_id = res.getInt("role_id");
					
					User u = new User(id, username, password, name, year, role_id);
					listaKonobara.add(u);
				}
				
				return listaKonobara;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public boolean jedinstvenUsernameProvera(String username)
	{
		PreparedStatement prepStmt;
		try {
			Connection conn = ConnectionProvider.getCon();
			
			String query = "SELECT * FROM user "
					+ "WHERE username LIKE ?";
			
			prepStmt = conn.prepareStatement(query);
			prepStmt.setString(1, username);
			
			ResultSet res = prepStmt.executeQuery();
			
			if(res.next()) // postoji vec taj username
			{
				return false;
			}
			
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static void deleteUser(int id)
	{
		PreparedStatement prepStmt;
		try {
			Connection conn = ConnectionProvider.getCon();
			
			String query = "DELETE FROM user WHERE Id=?";
			
			prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, id);
			
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public User getRoleIdByUserId(int idUser)
	{
		try {
			Connection conn = ConnectionProvider.getCon();
			
			String query = "SELECT * FROM user "
					+ "WHERE Id=?";
			
			PreparedStatement prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, idUser);
			
			ResultSet res = prepStmt.executeQuery();
			
			if(res.next())
			{
				int id = res.getInt("Id");
				String username = res.getString("username").trim();
				String password = res.getString("password").trim();
				String name = res.getString("name").trim();
				int year = res.getInt("year");
				int role_id = res.getInt("role_id"); 
				
				User u = new User(id, username, password, name, year, role_id);
				return u;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
