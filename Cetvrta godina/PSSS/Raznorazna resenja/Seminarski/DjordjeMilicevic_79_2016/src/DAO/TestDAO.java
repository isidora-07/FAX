package DAO;

import java.util.List;

import Beans.Club;
import Beans.Game;
import Beans.Role;
import Beans.User;

public class TestDAO {

	public static void main(String[] args) {
		// Role DAO
		// RoleDAO rdb = RoleDAO.getInstance();
		
		// Read Roles
		/*
		List<Role> roles = rdb.getAll();
		
		for (Role role : roles) {
			System.out.println(role.toString());
		}
		
		System.out.println(rdb.get(1).getRole());
		*/
		
		// User DAO
		UserDAO udb = UserDAO.getInstance();
		
		// Add User
		/*
		User u = new User("djordje", "djordje@gmail.com", "djordje", false, 2);
		udb.add(u);
		*/
		
		// Read Users
		/*
		List<User> users = udb.getAll();
		
		for (User user : users) {
			System.out.println(user.toString());
		}
		*/
		
		// Read User
		/*
		User u = udb.get(1);
		System.out.println(u.toString());
		*/
		
		// Club DAO
		//ClubDAO cdb = ClubDAO.getInstance();
		
		// Read Clubs
		/*
		List<Club> clubs = cdb.getAll();
		
		for (Club club : clubs) {
			System.out.println(club.toString());
		}*/
		
		// Read Club
		/*
		Club club = cdb.get(1);
		System.out.println(club.toString());
		*/
		
		// Game DAO
		//GameDAO gdb = GameDAO.getInstance();
		/*
		List<Game> games = gdb.getAll();
		
		for (Game game : games) {
			System.out.println(game.toString());
		}*/
	}

}
