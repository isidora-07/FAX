package somepackage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	private ArrayList<Korisnik> korisnici;

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

		} catch (Exception e) {
			System.out.println("Username " + korisnickoIme + " password " + sifra);
			System.out.println("LOGIN error");
			e.printStackTrace();
		}

		FacesContext.getCurrentInstance().addMessage("form:loginErr", new FacesMessage("Bad login"));
		return "index";
	}

	public ArrayList<Korisnik> users() {
		korisnici = null;

		try {
			korisnici = new ArrayList<Korisnik>();
			Statement s = ConnectionProvider.getConn().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM korisnik");

			while (rs.next()) {
				Korisnik k = new Korisnik();
				k.setId(rs.getInt("id"));
				k.setKorisnickoIme(rs.getString("korisnicko_ime"));
				k.setSifra(rs.getString("sifra"));
				k.setUloga(rs.getString("uloga"));
				korisnici.add(k);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return korisnici;
	}

	private String isValid() {
		korisnici = users();
		for (Korisnik k : korisnici) {
			if (k.getKorisnickoIme().equals(korisnickoIme)) {
				return "Korisnicko ime je korisceno.";
			}
		}

		return isPasswordValid(sifra);
	}

	private String isPasswordValid(String pass) {
		if (pass.matches(".*[A-Z].*") && pass.matches(".*[a-z].*") && pass.matches(".*[0-9].*")
				&& (pass.length() >= 6 && pass.length() <= 10)) {
			return "ok";
		} else {
			if (pass.length() < 6 || pass.length() > 10)
				return "Pass mora da ima najmanje 6 karaktera a najvise 10";
			return "Password mora biti aA1";
		}
	}

	public void dodajProdavca() {
		String message = isValid();
		if (message.equals("ok")) {
			try {
				PreparedStatement preStat = ConnectionProvider.getConn().prepareStatement(
						"INSERT INTO korisnik(id, korisnicko_ime, sifra, uloga) VALUES (null, ?, ?, ?)");
				preStat.setString(1, korisnickoIme);
				preStat.setString(2, sifra);
				preStat.setString(3, "prodavac");
				preStat.executeUpdate();

				FacesContext.getCurrentInstance().addMessage("form:addProd", new FacesMessage("Saccessfull"));

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			FacesContext.getCurrentInstance().addMessage("addUserForm:addProdErr", new FacesMessage(message));
		}
	}

}