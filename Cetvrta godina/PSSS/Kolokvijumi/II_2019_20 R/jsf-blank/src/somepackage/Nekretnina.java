package somepackage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "nek")
@RequestScoped
public class Nekretnina {
	private int id;
	private String tip;
	private int povrsina;
	private String adresa;
	private int cena;
	private ArrayList<Nekretnina> nekretnine = null;

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

	public ArrayList<Nekretnina> listaNekretnina() {
		try {
			nekretnine = new ArrayList<Nekretnina>();
			Statement stat = ConnectionProvider.getConn().createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM nekretnina");

			while (rs.next()) {
				Nekretnina n = new Nekretnina();
				n.setId(rs.getInt("id"));
				n.setTip(rs.getString("tip"));
				n.setPovrsina(rs.getInt("povrsina"));
				n.setAdresa(rs.getString("adresa"));
				n.setCena(rs.getInt("cena"));
				nekretnine.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nekretnine;
	}

	public void obrisi(int id) {
		try {
			PreparedStatement preStat = ConnectionProvider.getConn()
					.prepareStatement("DELETE FROM nekretnina WHERE id=?");
			preStat.setInt(1, id);
			preStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String dodajProizvod() {
		try {
			PreparedStatement preStat = ConnectionProvider.getConn().prepareStatement(
					"INSERT INTO nekretnina(id, tip, povrsina, adresa, cena) VALUES (null, ?, ?, ?, ?)");
			preStat.setString(1, tip);
			preStat.setInt(2, povrsina);
			preStat.setString(3, adresa);
			preStat.setInt(4, cena);
			preStat.executeUpdate();
			FacesContext.getCurrentInstance().addMessage("form:succ", new FacesMessage("Uspesno"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "admin";
	}
}
