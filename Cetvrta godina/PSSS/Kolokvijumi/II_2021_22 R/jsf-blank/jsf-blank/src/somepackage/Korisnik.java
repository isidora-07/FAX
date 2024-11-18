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
	private String username;
	private String password;
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		try {
			PreparedStatement preStat = ConnectionProvider.getConn()
					.prepareStatement("SELECT * FROM korisnik WHERE koriscnicko_ime=? AND sifra=?");
			preStat.setString(1, username);
			preStat.setString(2, password);
			ResultSet rs = preStat.executeQuery();
			rs.next();

			Korisnik k = new Korisnik();
			k.setId(rs.getInt("id"));
			k.setUsername(rs.getString("koriscnicko_ime"));
			k.setPassword(rs.getString("sifra"));

			sessionMap.put("logged_user", k);

			return "kotarica.xhtml?faces-redirect=true";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		FacesContext.getCurrentInstance().addMessage("form:errLogin", new FacesMessage("Bad login"));
		return "index";
	}

}
