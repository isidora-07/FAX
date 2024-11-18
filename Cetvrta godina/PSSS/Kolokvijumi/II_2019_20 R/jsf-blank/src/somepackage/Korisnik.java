package somepackage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	private String ime;
	private String sifra;
	private String uloga;
	private ArrayList<Korisnik> korisnici = null;
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String login() {
		try {
			PreparedStatement preStat = ConnectionProvider.getConn()
					.prepareStatement("SELECT * FROM korisnik WHERE ime=? AND sifra=?");
			preStat.setString(1, ime);
			preStat.setString(2, sifra);
			ResultSet rs = preStat.executeQuery();
			rs.next();

			Korisnik k = new Korisnik();
			k.setId(rs.getInt("id"));
			k.setIme(rs.getString("ime"));
			k.setSifra(rs.getString("sifra"));
			k.setUloga(rs.getString("uloga"));

			sessionMap.put("logged_user", k);

			if (k.uloga.equals("kupac"))
				return "kupac.xhtml?faces-redirect=true";
			else
				return "admin.xhtml?faces-redirect=true";
		} catch (SQLException e) {
			e.printStackTrace();
		}

		FacesContext.getCurrentInstance().addMessage("form: loginErr", new FacesMessage("Nije dobar username/pass"));
		return "index";
	}

	private String isValidPassword(String pass) {
		if (pass.length() < 6 || pass.length() > 10)
			return "Sifra mora da ima najmanje 6, a najvise 10 karaktera";
		if (pass.matches(".*[a-z].*") && pass.matches(".*[A-Z].*") && pass.matches(".*[0-9].*"))
			return "ok";
		return "Sifra mora da bude u formatu Aa1";
	}

	private ArrayList<Korisnik> listaKorisnika() {
		try {
			korisnici = new ArrayList<Korisnik>();
			PreparedStatement preStat = ConnectionProvider.getConn().prepareStatement("SELECT * FROM korisnik");
			ResultSet rs = preStat.executeQuery();
			while (rs.next()) {
				Korisnik k = new Korisnik();
				k.setId(rs.getInt("id"));
				k.setIme(rs.getString("ime"));
				k.setSifra(rs.getString("sifra"));
				k.setUloga(rs.getString("uloga"));
				korisnici.add(k);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return korisnici;
	}

	private boolean daLiPostojiUsername() {
		korisnici = listaKorisnika();
		for (Korisnik k : korisnici) {
			if (k.ime.equals(ime))
				return true;
		}
		return false;
	}

	public void dodajKorisnika(PasswordValidation passVal) {
		if (daLiPostojiUsername() == true) {
			FacesContext.getCurrentInstance().addMessage("form:changeMess", new FacesMessage("Username vec postoji"));
			return;
		}

		if (!passVal.getNewPass1().equals(passVal.getNewPass2())) {
			FacesContext.getCurrentInstance().addMessage("form:changeMess",
					new FacesMessage("Sifre moraju da budu iste"));
			return;
		}

		String mess = isValidPassword(passVal.getNewPass1());
		if (!mess.equals("ok")) {
			FacesContext.getCurrentInstance().addMessage("form:changeMess", new FacesMessage(mess));
			return;
		}

		try {
			PreparedStatement preStat = ConnectionProvider.getConn()
					.prepareStatement("INSERT INTO korisnik(id, ime, sifra, uloga) VALUES (null, ?, ?, ?)");
			preStat.setString(1, ime);
			preStat.setString(2, passVal.getNewPass1());
			preStat.setString(3, "kupac");
			preStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
