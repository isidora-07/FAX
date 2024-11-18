package Beans;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Club {
	private int id;
	private String name;
	private String town;
	private int wins;
	private int losses;
	private String logo;
	
	public Club() {
		
	}
	
	public Club( String name, String town, int wins, int losses, String logo) {
		super();
		this.name = name;
		this.town = town;
		this.wins = wins;
		this.losses = losses;
		this.logo = logo;
	}

	public Club(int id, String name, String town, int wins, int losses, String logo) {
		super();
		this.id = id;
		this.name = name;
		this.town = town;
		this.wins = wins;
		this.losses = losses;
		this.logo = logo;
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

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}
	
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public static List<Club> sortByWins(List<Club> clubs){
		Collections.sort(clubs, new Comparator<Club>() {
			
			private int comp(int a, int b) {
				return a > b ? 1 : a < b ? -1 : 0;
			}

			@Override
			public int compare(Club o1, Club o2) {
				return comp(o2.getWins(), o1.getWins());
			}
			
		});
		
		return clubs;
	}

	@Override
	public String toString() {
		return "Club [id=" + id + ", name=" + name + ", town=" + town + ", wins=" + wins + ", losses=" + losses + "]";
	}
	
	
}
