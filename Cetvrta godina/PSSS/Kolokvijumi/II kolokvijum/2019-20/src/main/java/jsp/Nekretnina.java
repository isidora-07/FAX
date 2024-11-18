package jsp;

import java.sql.*;
import java.util.*;

public class Nekretnina {
	private int id;
	private String tip;
	private int povrsina;
	private String adresa;
	private int cena;
	private List<Nekretnina> nekretnine;
	private Connection conn;
	
	public Nekretnina() 
	{
		
	}
	
	public Nekretnina(int id, String tip, int povrsina, String adresa, int cena) {
		this.id = id;
		this.tip = tip;
		this.povrsina = povrsina;
		this.adresa = adresa;
		this.cena = cena;
	}
	
	public int getId()
	{
		return id;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public int getPovrsina() {
		return povrsina;
	}
	public void setPovrsina(int povrsina) {
		this.povrsina = povrsina;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	
	public List<Nekretnina> listaNekretnina()
	{
		try {
			nekretnine = new LinkedList<Nekretnina>();
			
			conn = ConnectionProvider.getCon();
			Statement stmt = conn.createStatement();
			
			ResultSet res = stmt.executeQuery("SELECT * FROM nekretnina");
			
			while(res.next())
			{
				int id = res.getInt("id");
				String tip = res.getString("tip").trim();
				int povrsina = res.getInt("povrsina");
				String adresa = res.getString("adresa").trim();
				int cena = res.getInt("cena");
				
				Nekretnina n = new Nekretnina(id, tip, povrsina, adresa, cena);
				nekretnine.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nekretnine;
	}
	
	/*public String lista()
	{
		String str = "";
		for(int i=0; i<nekretnine.size(); i++)
			str += nekretnine.get(i).getAdresa() + "\n";
		
		return str;
	}
	*/
	
	public void obrisi(int id)
	{
		
		try {
			conn = ConnectionProvider.getCon();
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM nekretnina WHERE id= "+id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
