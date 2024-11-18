package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Beans.Club;

public class ClubDAO {
	private static final String SELECT_ALL_CLUBS = "select * from clubs";
	private static final String SELECT_CLUB_BY_ID = "select * from clubs where id = ?";
	private static final String UPDATE_CLUB = "update clubs set wins = ?, losses = ? where id = ?";
	
	private static ClubDAO instance = new ClubDAO();
	public static ClubDAO getInstance() {
		return instance;
	}
	
	public List<Club> getAll(){
		List<Club> clubs = new ArrayList<Club>();
		
		try {
			Connection conn = ConnectionProvider.getConn();
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(SELECT_ALL_CLUBS);
			while(rs.next()) {
				clubs.add(
					new Club(
						rs.getInt("id"), 
						rs.getString("name"),
						rs.getString("town"),
						rs.getInt("wins"),
						rs.getInt("losses"),
						rs.getString("logo")
					)
				);
			}
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return clubs.isEmpty() || clubs.size() < 1 ? null : clubs;
	}
	
	public Club get(int id) {
		Club club = null;
		
		try {
			Connection conn = ConnectionProvider.getConn();
			PreparedStatement ps = conn.prepareStatement(SELECT_CLUB_BY_ID);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				club = new Club(
					rs.getInt("id"), 
					rs.getString("name"),
					rs.getString("town"),
					rs.getInt("wins"),
					rs.getInt("losses"),
					rs.getString("logo")
				);
			}
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return club;
	}
	
	public boolean update(Club club) {
		int status = 0;
		
		try {
			Connection conn = ConnectionProvider.getConn();
			PreparedStatement ps = conn.prepareStatement(UPDATE_CLUB);
			ps.setInt(1, club.getWins());
			ps.setInt(2, club.getLosses());
			ps.setInt(3, club.getId());
			
			status = ps.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return status > 0;
	}
}
