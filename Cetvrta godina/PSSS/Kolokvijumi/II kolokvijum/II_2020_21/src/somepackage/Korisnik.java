package somepackage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "user")
@RequestScoped
public class Korisnik {
	private int id;
	private String korisnickoIme;
	private String sifra;
	private String uloga;
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
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

	public String loginUser() {
		try {
			PreparedStatement preStat = ConnectionProvider.getConn()
					.prepareStatement("SELECT * FROM korisnik WHERE korisnicko_ime=? AND sifra=?");
			preStat.setString(1, korisnickoIme);
			preStat.setString(2, sifra);
			ResultSet rs = preStat.executeQuery();
			rs.next();
			Korisnik k = new Korisnik();
			k.setId(rs.getInt("id"));
			k.setKorisnickoIme(rs.getString("korisnicko_ime"));
			k.setSifra(rs.getString("sifra"));
			k.setUloga(rs.getString("uloga"));
			

			sessionMap.put("logged_user", k);
			if (k.getUloga().equals("sef_magacina")) {
				return "sef.xhtml?faces-redirect=true";
			}
			return "prodavac.xhtml?faces-redirect=true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		FacesContext.getCurrentInstance().addMessage("form:loginErr", new FacesMessage("Login nije dobar!"));
		return "index";
	}
}
