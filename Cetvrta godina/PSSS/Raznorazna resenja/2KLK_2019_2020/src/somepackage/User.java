package somepackage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "user")
@RequestScoped
public class User {
	private int id;
	private String ime;
	private String sifra;
	private String uloga;
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
			PreparedStatement preStat = ConnectionProvider.getConn().prepareStatement("SELECT * FROM korisnik WHERE ime=? AND sifra=?");
			preStat.setString(1, ime);
			preStat.setString(2, sifra);
			ResultSet rs = preStat.executeQuery();
			rs.next();
			User u = new User();
			u.setId(rs.getInt("id"));
			u.setIme(rs.getString("ime"));
			u.setSifra(rs.getString("sifra"));
			u.setUloga(rs.getString("uloga"));
			
			sessionMap.put("logged_user", u);
			
			if(u.getUloga().equals("administrator")) {
				return "admin.xhtml?faces-redirect=true";
			} else {
				return "kupac.xhtml?faces-redirect=true";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage("form:errorLogin", new FacesMessage("Neuspesan login"));
		return "";
	}
}
