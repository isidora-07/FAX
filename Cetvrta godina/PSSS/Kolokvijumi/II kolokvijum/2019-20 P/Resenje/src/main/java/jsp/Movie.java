package jsp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	public Movie()
	{
		
	}
	
	public Movie(String title, String description, String director, int year, int runtime_Minutes, int rating,
			int votes, int revenue_Millions, int metascore) {
		super();
		Title = title;
		Description = description;
		Director = director;
		Year = year;
		Runtime_Minutes = runtime_Minutes;
		Rating = rating;
		Votes = votes;
		Revenue_Millions = revenue_Millions;
		Metascore = metascore;
	}

	public Movie(int iD, String title, String description, String director, int year, int runtime_Minutes, int rating,
			int votes, int revenue_Millions, int metascore) {
		super();
		ID = iD;
		Title = title;
		Description = description;
		Director = director;
		Year = year;
		Runtime_Minutes = runtime_Minutes;
		Rating = rating;
		Votes = votes;
		Revenue_Millions = revenue_Millions;
		Metascore = metascore;
	}
	
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
	
	public static List<Movie> listMovies()
	{
		try {
			List<Movie> movies = new ArrayList<Movie>();
			
			Connection conn = ConnectionProvider.getConn();
			
			String query = "SELECT * FROM movie";
			
			PreparedStatement prepStmt = conn.prepareStatement(query);
			
			ResultSet res = prepStmt.executeQuery();
			
			while(res.next())
			{
				int ID = res.getInt("ID");
				String Title = res.getString("Title").trim();
				String Description = res.getString("Description").trim();
				String Director = res.getString("Director").trim();
				int Year = res.getInt("Year");
				int Runtime_Minutes = res.getInt("Runtime_Minutes");
				int Rating = res.getInt("Rating");
				int Votes = res.getInt("Votes");
				int Revenue_Millions = res.getInt("Revenue_Millions");
				int Metascore = res.getInt("Metascore");
				
				Movie m = new Movie(ID, Title, Description, Director, Year, Runtime_Minutes, Rating, Votes, Revenue_Millions, Metascore);
				movies.add(m);
			}
			
			return movies;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static Movie getDetailsMovieBtId(int idMovie)
	{
		Movie m = null;
		try {
			Connection conn = ConnectionProvider.getConn();
			
			String query = "SELECT * FROM movie "
					+ "WHERE ID=?";
			
			PreparedStatement prepStmt = conn.prepareStatement(query);
			
			prepStmt.setInt(1, idMovie);
			
			ResultSet res = prepStmt.executeQuery();
			
			if(res.next())
			{
				int ID = res.getInt("ID");
				String Title = res.getString("Title").trim();
				String Description = res.getString("Description").trim();
				String Director = res.getString("Director").trim();
				int Year = res.getInt("Year");
				int Runtime_Minutes = res.getInt("Runtime_Minutes");
				int Rating = res.getInt("Rating");
				int Votes = res.getInt("Votes");
				int Revenue_Millions = res.getInt("Revenue_Millions");
				int Metascore = res.getInt("Metascore");
				
				m = new Movie(ID, Title, Description, Director, Year, Runtime_Minutes, Rating, Votes, Revenue_Millions, Metascore);
				return m;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return m;
	}
	
	public static boolean deleteMovieById(int idMovie)
	{
		try {
			Connection conn = ConnectionProvider.getConn();
			
			String query = "DELETE FROM movie WHERE ID=?";
			
			PreparedStatement prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, idMovie);
			
			prepStmt.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
