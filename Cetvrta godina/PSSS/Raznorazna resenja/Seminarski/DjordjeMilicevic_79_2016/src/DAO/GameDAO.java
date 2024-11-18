package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Beans.Game;

public class GameDAO {
	
	private static String INSERT_GAME = "insert into games values(null, ?, ?, ?, ?, ?)";
	private static String SELECT_ALL_GAMES = "select * from games";
	private static String SELECT_GAME_BY_ID = "select * from games where id = ?";
	private static String UPDATE_GAME = "update games set homeId = ?, awayId = ?, winner = ?, date = ?, finished = ? where id = ?";
	private static String DELETE_GAME = "delete from games where id = ?";
	
	private static GameDAO instance = new GameDAO();
	public static GameDAO getInstance() {
		return instance;
	}
	
	public List<Game> getAll(){
		List<Game> games = new ArrayList<Game>();
		
		try {
			Connection conn = ConnectionProvider.getConn();
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(SELECT_ALL_GAMES);
			while(rs.next()) {
				games.add(
					new Game(
						rs.getInt("id"), 
						rs.getInt("homeId"), 
						rs.getInt("awayID"),
						rs.getInt("winner"),
						rs.getDate("date"), 
						rs.getBoolean("finished")
					)
				);
			}
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return games.isEmpty() || games.size() < 1 ? null : games;
	}
	
	public Game get(int id) {
		Game game = null;
		
		try {
			Connection conn = ConnectionProvider.getConn();
			PreparedStatement ps = conn.prepareStatement(SELECT_GAME_BY_ID);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				game = new Game(
					rs.getInt("id"), 
					rs.getInt("homeId"), 
					rs.getInt("awayID"),
					rs.getInt("winner"),
					rs.getDate("date"), 
					rs.getBoolean("finished")
				);
			}
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return game;
	}
	
	public void add(Game game) {
		try {
			Connection conn = ConnectionProvider.getConn();
			PreparedStatement ps = conn.prepareStatement(INSERT_GAME);
			ps.setInt(1, game.getHomeId());
			ps.setInt(2, game.getAwayId());
			ps.setInt(3, game.getWinner());
			ps.setDate(4, (Date)game.getDate());
			ps.setBoolean(5, game.isFinished());
			
			ps.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean update(Game game) {
		int status = 0;
		
		try {
			Connection conn = ConnectionProvider.getConn();
			PreparedStatement ps = conn.prepareStatement(UPDATE_GAME);
			ps.setInt(1, game.getHomeId());
			ps.setInt(2, game.getAwayId());
			ps.setInt(3, game.getWinner());
			ps.setDate(4, (Date)game.getDate());
			ps.setBoolean(5, game.isFinished());
			ps.setInt(6, game.getId());
			
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
			PreparedStatement ps = conn.prepareStatement(DELETE_GAME);
			ps.setInt(1, id);
			
			status = ps.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return status > 0;
	}
}
