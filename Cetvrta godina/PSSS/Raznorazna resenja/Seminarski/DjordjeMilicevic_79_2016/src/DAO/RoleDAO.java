package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Beans.Role;

public class RoleDAO {
	private static final String INSERT_ROLE = "insert into roles values(null, ?)";
	private static final String SELECT_ALL_ROLES = "select * from roles";
	private static final String SELECT_ROLE_BY_ID = "select * from roles where id = ?";
	private static final String SELECT_ROLE_BY_NAME = "select * from roles where role = ?";
	private static final String UPDATE_ROLE = "update roles set role = ? where id = ?";
	private static final String DELETE_ROLE = "delete from roles where id = ?";
	private static final String SELECT_FAN_ROLE = "select * from roles where role='navijac'";
	private static final String SELECT_MANAGMENT_ROLE = "select * from roles where role='uprava'";
	
	private static RoleDAO instance = new RoleDAO();
	public static RoleDAO getInstance() {
		return instance;
	}
	
	public List<Role> getAll(){
		List<Role> roles = new ArrayList<Role>();
		
		try {
			Connection conn = ConnectionProvider.getConn();
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(SELECT_ALL_ROLES);
			while(rs.next()) {
				roles.add(
					new Role(
						rs.getInt("id"), 
						rs.getString("role")
					)
				);
			}
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return roles.isEmpty() || roles.size() < 1 ? null : roles;
	}
	
	public Role get(int id) {
		Role role = null;
		
		try {
			Connection conn = ConnectionProvider.getConn();
			PreparedStatement ps = conn.prepareStatement(SELECT_ROLE_BY_ID);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				role = new Role(
					rs.getInt("id"), 
					rs.getString("role")
				);
			}
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return role;
	}
	
	public Role get(String name) {
		Role role = null;
		
		try {
			Connection conn = ConnectionProvider.getConn();
			PreparedStatement ps = conn.prepareStatement(SELECT_ROLE_BY_NAME);
			ps.setString(1, name);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				role = new Role(
					rs.getInt("id"), 
					rs.getString("role")
				);
			}
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return role;
	}
	
	public void add(Role role) {
		try {
			Connection conn = ConnectionProvider.getConn();
			PreparedStatement ps = conn.prepareStatement(INSERT_ROLE);
			ps.setString(1, role.getRole());
			
			ps.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean update(Role role) {
		int status = 0;
		
		try {
			Connection conn = ConnectionProvider.getConn();
			PreparedStatement ps = conn.prepareStatement(UPDATE_ROLE);
			ps.setString(1, role.getRole());
			ps.setInt(2, role.getId());
			
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
			PreparedStatement ps = conn.prepareStatement(DELETE_ROLE);
			ps.setInt(1, id);
			
			status = ps.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return status > 0;
	}
	
	public Role getFan() {
		return getCustomRole(SELECT_FAN_ROLE);
	}
	
	public Role getManagment() {
		return getCustomRole(SELECT_MANAGMENT_ROLE);
	}
	
	private Role getCustomRole(String sql) {
		Role role = null;
		
		try {
			Connection conn = ConnectionProvider.getConn();
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				role = new Role(
					rs.getInt("id"), 
					rs.getString("role")
				);
			}
			
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return role;
	}
}
