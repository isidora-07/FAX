package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Beans.User;
import Utilities.PasswordHash;

public class UserDAO {
	private static final String INSERT_USER = "insert into users values (null, ?, ?, ?, ?, ?)";
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String SELECT_USER_BY_ID = "select * from users where id = ?";
	private static final String SELECT_USER_BY_NAME = "select * from users where name = ?";
	private static final String UPDATE_USER = "update users set name = ?, email = ?, isAdmin = ?, roleId = ? where id = ?";
	private static final String DELETE_USER = "delete from users where id = ?";
	private static final String LOGIN_USER = "select * from users where name = ? and password = ?";
	
	private static UserDAO instance = new UserDAO();
	
	public static UserDAO getInstance() {
		return instance;
	}
	
    private UserDAO() {
		
	}
    
    public List<User> getAll(){
		List<User> users = new ArrayList<User>();
		
		try {
			Connection conn = ConnectionProvider.getConn();
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(SELECT_ALL_USERS);
			while(rs.next()) {
				users.add(
					new User(
						rs.getInt("id"), 
						rs.getString("name"), 
						rs.getString("email"), 
						"", // Password
						rs.getBoolean("isAdmin"), 
						rs.getInt("roleId")
					)
				);
			}
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return (users.isEmpty() || users.size() < 1) ? null : users;
	}
    
    public User get(int id) {
		User user = null;
		
		try {
			Connection conn = ConnectionProvider.getConn();
			PreparedStatement ps = conn.prepareStatement(SELECT_USER_BY_ID);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user = new User(
					rs.getInt("id"), 
					rs.getString("name"), 
					rs.getString("email"), 
					"", // Password
					rs.getBoolean("isAdmin"), 
					rs.getInt("roleId")
				);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
    
    public User get(String name) {
		User user = null;
		
		try {
			Connection conn = ConnectionProvider.getConn();
			PreparedStatement ps = conn.prepareStatement(SELECT_USER_BY_NAME);
			ps.setString(1, name);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user = new User(
					rs.getInt("id"), 
					rs.getString("name"), 
					rs.getString("email"), 
					"", // Password
					rs.getBoolean("isAdmin"), 
					rs.getInt("roleId")
				);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
    
    public void add(User user) {
		try {
			Connection conn = ConnectionProvider.getConn();
			PreparedStatement ps = conn.prepareStatement(INSERT_USER);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, PasswordHash.sha1(user.getPassword()));
			ps.setBoolean(4, user.isAdmin());
			ps.setInt(5, user.getRoleId());
			
			ps.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean update(User user) {
		int status = 0;
		
		try {
			Connection conn = ConnectionProvider.getConn();
			PreparedStatement ps = conn.prepareStatement(UPDATE_USER);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setBoolean(3, user.isAdmin());
			ps.setInt(4, user.getRoleId());
			ps.setInt(5, user.getId());
			
			status = ps.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return status > 0;
	}
	
	public boolean delete(int id) {
		int status = 0;
		
		try {
			Connection conn = ConnectionProvider.getConn();
			PreparedStatement ps = conn.prepareStatement(DELETE_USER);
			ps.setInt(1, id);
			
			status = ps.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return status > 0;
	}
	
	public User login(String name, String password) {
		User user = null;
		
		try {
			Connection conn = ConnectionProvider.getConn();
			PreparedStatement ps = conn.prepareStatement(LOGIN_USER);
			ps.setString(1, name);
			ps.setString(2, PasswordHash.sha1(password));
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user = new User(
					rs.getInt("id"), 
					rs.getString("name"), 
					rs.getString("email"), 
					"", // Password
					rs.getBoolean("isAdmin"), 
					rs.getInt("roleId")
				);
			}
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
}
