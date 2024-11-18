package Beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class Game {
	private int id;
	private int homeId;
	private int awayId;
	private int winner;
	private Date date;
	private boolean finished;
	
	public Game() {
		
	}

	public Game(int homeId, int awayId, int winner, Date date, boolean finished) {
		super();
		this.homeId = homeId;
		this.awayId = awayId;
		this.winner = winner;
		this.date = date;
		this.finished = finished;
	}

	public Game(int id, int homeId, int awayId, int winner, Date date, boolean finished) {
		super();
		this.id = id;
		this.homeId = homeId;
		this.awayId = awayId;
		this.winner = winner;
		this.date = date;
		this.finished = finished;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHomeId() {
		return homeId;
	}

	public void setHomeId(int homeId) {
		this.homeId = homeId;
	}

	public int getAwayId() {
		return awayId;
	}

	public void setAwayId(int awayId) {
		this.awayId = awayId;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public static List<Game> sortByDate(List<Game> games){
		Collections.sort(games, new Comparator<Game>() {
			
			private int comp(long a, long b) {
				return a > b ? 1 : a < b ? -1 : 0;
			}

			@Override
			public int compare(Game o1, Game o2) {
				return comp(o1.getDate().getTime(), o2.getDate().getTime());
			}
			
		});
		
		return games;
	}
	
	public static List<Game> arrangeGames(List<Game> games){
		List<Game> activeGames = new ArrayList<Game>();
		List<Game> finishedGames = new ArrayList<Game>();
		
		for(Game game: games) {
			if(game.isFinished()) {
				finishedGames.add(game);
			}
			else {
				activeGames.add(game);
			}
		}
		
		activeGames = sortByDate(activeGames);
		finishedGames = sortByDate(finishedGames);
		
		activeGames.addAll(finishedGames);
		return activeGames;
	}
	
	private boolean priority(String priority){
		switch(priority) {
			case "finished":
				return isFinished() ? true : false;
			case "now":
				return date.getTime() <= (new Date()).getTime() && !isFinished() ? true : false;
			case "soon":
				return date.getTime() > (new Date()).getTime() && date.getTime() < ((new Date()).getTime() + 1000 * 60 * 60 * 24 * 2) && !isFinished() ? true : false;
			case "latter":
				return date.getTime() > ((new Date()).getTime() + 1000 * 60 * 60 * 24 * 2) ? true : false;
			default:
				return false;
		}
	}
	
	public static List<Game> filterByPriority(List<Game> games, String priority){
		List<Game> filtered = new ArrayList<Game>();
		
		for(Game game: games)
			if(game.priority(priority))
				filtered.add(game);
		
		return filtered.size() > 0 ? filtered : null;
	}
	
	public String giveBsProperty() {
		if(isFinished())
			return "";
		else if(date.getTime() <= (new Date()).getTime())
			return "bg-danger";
		else if(date.getTime() > (new Date()).getTime() && date.getTime() < ((new Date()).getTime() + 1000 * 60 * 60 * 24 * 2))
			return "bg-warning";
		
		return "bg-info";
		
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", homeId=" + homeId + ", awayId=" + awayId + ", winner=" + winner + ", date=" + date
				+ ", finished=" + finished + "]";
	}
}
