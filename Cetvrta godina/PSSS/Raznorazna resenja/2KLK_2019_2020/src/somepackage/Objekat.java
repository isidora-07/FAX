package somepackage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "objekat")
@RequestScoped
public class Objekat {
	private int id;
	private String tip;
	private int povrsina;
	private String adresa;
	private int cena;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public ArrayList objektiList() {
		ArrayList objekti = null;
		try {
			objekti = new ArrayList();
			PreparedStatement preStat = ConnectionProvider.getConn().prepareStatement("SELECT * FROM nekretnina");
			ResultSet rs = preStat.executeQuery();
			while(rs.next()) {
				Objekat o = new Objekat();
				o.setId(rs.getInt("id"));
				o.setTip(rs.getString("tip"));
				o.setPovrsina(rs.getInt("povrsina"));
				o.setAdresa(rs.getString("adresa"));
				o.setCena(rs.getInt("cena"));
				
				objekti.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return objekti;
	}
	
	public void delete(int id) {
		try {
			PreparedStatement preStat = ConnectionProvider.getConn().prepareStatement("DELETE FROM nekretnina WHERE id=?");
			preStat.setInt(1, id);
			preStat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
