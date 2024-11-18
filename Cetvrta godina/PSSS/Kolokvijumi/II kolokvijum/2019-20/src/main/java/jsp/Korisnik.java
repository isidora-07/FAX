package jsp;

import java.sql.*;
import java.util.*;

public class Korisnik {
	private String ime;
	private String sifra;
	private String uloga;
	private List<Korisnik> korisnici;
	 
	public Korisnik()
	{
		
	}
	
	public Korisnik(String ime, String sifra, String uloga) {
		this.ime = ime;
		this.sifra = sifra;
		this.uloga = uloga;
	}

	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	public String getUloga() {
		return uloga;
	}
	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	
	public List<Korisnik> listaKorisnika()
	{
		
		try {
			korisnici = new LinkedList<Korisnik>();
			
			Connection conn = ConnectionProvider.getCon();
			Statement stmt = conn.createStatement();
			
			ResultSet res = stmt.executeQuery("SELECT * FROM korisnik");
			while(res.next())
			{
				String ime = res.getString("ime").trim();
				String sifra = res.getString("sifra").trim();
				String uloga = res.getString("uloga").trim();
				Korisnik k = new Korisnik(ime, sifra, uloga);
				System.out.println(k.getIme());
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			korisnici = null;
		}
		
		return korisnici;
	}
	
	public Korisnik readKorisnik(String ime, String sifra)
	{
		try {
			Connection conn = ConnectionProvider.getCon();
			
			String query = "SELECT * FROM korisnik "
					+ "WHERE ime=? AND sifra=?";
			
			PreparedStatement prepStmt = conn.prepareStatement(query);
			prepStmt.setString(1, ime);
			prepStmt.setString(2, sifra);
			
			ResultSet res = prepStmt.executeQuery();
			
			if(res.next())
			{
				String ime2 = res.getString("ime").trim();
				String sifra2 = res.getString("sifra").trim();
				String uloga = res.getString("uloga").trim();
				Korisnik k = new Korisnik(ime2, sifra2, uloga);
				return k;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
