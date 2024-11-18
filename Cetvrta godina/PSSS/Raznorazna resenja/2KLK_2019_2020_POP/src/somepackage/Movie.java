package somepackage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "movie")
@RequestScoped
public class Movie {
	private int ID;
	private String Title;
	private String Description;
	private String Director;
	private int Year;
	private int Runtime_Minutes;
	private int Rating;
	private int Votes;
	private int Revenue_Millions;
	private int Metascore;
	
	private ArrayList movies = null;
	private ArrayList paggMovies = null;
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	
	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getTitle() {
		return Title;
	}
	
	public void setTitle(String title) {
		Title = title;
	}
	
	public String getDescription() {
		return Description;
	}
	
	public void setDescription(String description) {
		Description = description;
	}
	
	public String getDirector() {
		return Director;
	}
	
	public void setDirector(String director) {
		Director = director;
	}
	
	public int getYear() {
		return Year;
	}
	
	public void setYear(int year) {
		Year = year;
	}
	
	public int getRuntime_Minutes() {
		return Runtime_Minutes;
	}
	
	public void setRuntime_Minutes(int runtime_Minutes) {
		Runtime_Minutes = runtime_Minutes;
	}
	
	public int getRating() {
		return Rating;
	}
	
	public void setRating(int rating) {
		Rating = rating;
	}
	
	public int getVotes() {
		return Votes;
	}
	
	public void setVotes(int votes) {
		Votes = votes;
	}
	
	public int getRevenue_Millions() {
		return Revenue_Millions;
	}
	
	public void setRevenue_Millions(int revenue_Millions) {
		Revenue_Millions = revenue_Millions;
	}
	
	public int getMetascore() {
		return Metascore;
	}
	
	public void setMetascore(int metascore) {
		Metascore = metascore;
	}
	
	public ArrayList getMovieList() {
		movies = (ArrayList) sessionMap.get("movies_list");
		if(movies == null) {
			return updateMovieList("");
		}
		
		int pagNum = (Integer)sessionMap.get("pagginator_num");
		int pagSize = (Integer)sessionMap.get("pagginator_size");
		
		if(pagNum != pagSize)
			paggMovies = new ArrayList(movies.subList((pagNum) * 20, (pagNum) * 20 + 20));
		else {
			int ostatak = movies.size() % 20;
			paggMovies = new ArrayList(movies.subList((pagNum) * 20, (pagNum) * 20 + ostatak));
		}
		return paggMovies;
	}

	public ArrayList updateMovieList(String pretraga) {
		movies = null;
		try {
			movies = new ArrayList();
			PreparedStatement preStat;
			if(!pretraga.equals("")) {
				preStat = ConnectionProvider.getConn().prepareStatement("SELECT * FROM movie WHERE Title LIKE ?");
				preStat.setString(1, "%" + pretraga + "%");
			} else {
				preStat = ConnectionProvider.getConn().prepareStatement("SELECT * FROM movie");
			}
			
			ResultSet rs = preStat.executeQuery();
			while(rs.next()) {
				Movie m = new Movie();
				m.setID(rs.getInt("ID"));
				m.setTitle(rs.getString("Title"));
				m.setDescription(rs.getString("Description"));
				m.setDirector(rs.getString("Director"));
				m.setYear(rs.getInt("Year"));
				m.setRuntime_Minutes(rs.getInt("Runtime_minutes"));
				m.setRating(rs.getInt("Rating"));
				m.setVotes(rs.getInt("Votes"));
				m.setRevenue_Millions(rs.getInt("Revenue_Millions"));
				m.setMetascore(rs.getInt("Metascore"));
				movies.add(m);
			}
			
			sessionMap.put("movies_list", movies);
			sessionMap.put("pagginator_num", 0);
			sessionMap.put("pagginator_size", movies.size() / 20);
			
			int pagNum = (Integer)sessionMap.get("pagginator_num");
			int pagSize = (Integer)sessionMap.get("pagginator_size");
			
			if(pagNum != pagSize)
				paggMovies = new ArrayList(movies.subList((pagNum) * 20, (pagNum) * 20 + 20));
			else {
				int ostatak = movies.size() % 20;
				paggMovies = new ArrayList(movies.subList((pagNum) * 20, (pagNum) * 20 + ostatak));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return paggMovies;
	}
	
	public String redirect(int id) {
		return "movie.xhtml?faces-redirect=true&id=" + id; 
	}
	
	public Movie getMovie(int id) {
		Movie m = null;
		
		try {
			m = new Movie();
			PreparedStatement preStat;
			preStat = ConnectionProvider.getConn().prepareStatement("SELECT * FROM movie WHERE ID=?");
			preStat.setInt(1, id);
			ResultSet rs = preStat.executeQuery();
			rs.next();
			m.setTitle(rs.getString("Title"));
			m.setDescription(rs.getString("Description"));
			m.setYear(rs.getInt("Year"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m;
	}
	
	public void changePage(int increment) {
		int pagNum = (Integer)sessionMap.get("pagginator_num");
		int pagSize = (Integer)sessionMap.get("pagginator_size");
		
		pagNum = pagNum + increment;
		if(pagNum < 0)
			pagNum = 0;
		if(pagNum > pagSize)
			pagNum = pagSize;
		
		sessionMap.remove("pagginator_num");
		sessionMap.put("pagginator_num", pagNum);
		
	}
}
