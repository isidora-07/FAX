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
public class User {
	private int id;
	private String username;
	private String password;
	private String name;
	private int year;
	private int roleId;
	private ArrayList<User> konobari;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String login() {
		try {
			PreparedStatement preStat = ConnectionProvider.getConn()
					.prepareStatement("SELECT * FROM user WHERE username=? AND password=?");
			preStat.setString(1, username);
			preStat.setString(2, password);
			ResultSet rs = preStat.executeQuery();
			rs.next();

			User u = new User();
			u.setId(rs.getInt("Id"));
			u.setUsername(rs.getString("username"));
			u.setPassword(rs.getString("password"));
			u.setName(rs.getString("name"));
			u.setYear(rs.getInt("year"));
			u.setRoleId(rs.getInt("role_id"));

			sessionMap.put("logged_user", u);

			if (u.roleId == 1) {
				return "sef.xhtml?faces-redirect=true";
			}
			return "konobar.xhtml?faces-redirect=true";

		} catch (SQLException e) {
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage("form:loginErr", new FacesMessage("Bad login/pass"));
		return "index";
	}

	public ArrayList<User> usersList() {
		konobari = null;
		try {
			konobari = new ArrayList<User>();
			Statement s = ConnectionProvider.getConn().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM user WHERE role_id=2");
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("Id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setName(rs.getString("name"));
				u.setYear(rs.getInt("year"));
				u.setRoleId(rs.getInt("role_id"));
				konobari.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return konobari;
	}

	public void delete(int userId) {
		try {
			PreparedStatement preStat = ConnectionProvider.getConn().prepareStatement("DELETE FROM user WHERE id=?");
			preStat.setInt(1, userId);
			preStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean daLiPostojiUsername(String username) {
		for (User user : konobari) {
			if (user.username.equals(username)) {
				return true;
			}
		}
		return false;
	}

	private String isPasswordValid(String password) {
		if (password.matches(".*[A-Z].*") && password.matches(".*[a-z].*") && password.matches(".*[0-9].*")
				&& password.length() >= 6 && password.length() <= 10)
			return "ok";
		else {
			if (password.length() < 6 || password.length() > 10)
				return "Sifra mora da ima najmanje 6, najvise 10 karaktera";
			return "Sifra mora da bude u formatu aA1";
		}
	}

	private String isValid(int year, String password) {
		if (isPasswordValid(password).equals("ok") && (year >= 18 && year <= 65)) {
			return "ok";
		} else {
			if (year < 18 || year > 65)
				return "Godine u intervalu [18,65]";
			if (password.length() < 6 || password.length() > 10)
				return "Sifra mora da ima najmanje 6, najvise 10 karaktera";
			return "Sifra mora da bude u formatu aA1";
		}
	}

	public void dodaj() {
		String mess = isValid(year, password);
		if (!mess.equals("ok")) {
			FacesContext.getCurrentInstance().addMessage("form:addErr", new FacesMessage(mess));
			return;
		}

		try {
			if (daLiPostojiUsername(username) == true) {
				FacesContext.getCurrentInstance().addMessage("form:addErr", new FacesMessage("Username vec postoji"));
				return;
			}
			
			PreparedStatement preStat = ConnectionProvider.getConn().prepareStatement(
					"INSERT INTO user(Id, username, password, name, year, role_id) VALUES (null, ?, ?, ?, ?, ?)");
			preStat.setString(1, username);
			preStat.setString(2, password);
			preStat.setString(3, name);
			preStat.setInt(4, year);
			preStat.setInt(5, 2);
			preStat.executeUpdate();
			FacesContext.getCurrentInstance().addMessage("form:addSucc",
					new FacesMessage("Uspesno ste dodali konobara!"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String promeniPass(PasswordValidation passVal) {
		User user = (User) sessionMap.get("logged_user");

		if (!user.password.equals(passVal.getOldPass())) {
			FacesContext.getCurrentInstance().addMessage("form:izmenaErr",
					new FacesMessage("Ne poklapaju se stara i nova lozinka"));
			return "konobar";
		}

		if (!passVal.getNewPass1().equals(passVal.getNewPass2())) {
			FacesContext.getCurrentInstance().addMessage("form:izmenaErr",
					new FacesMessage("Sifre moraju da budu iste!"));
			return "konobar";
		}

		String mess = isPasswordValid(passVal.getNewPass1());
		if (!mess.equals("ok")) {
			FacesContext.getCurrentInstance().addMessage("form:izmenaErr", new FacesMessage(mess));
			return "konobar";
		}

		try {
			PreparedStatement preStat = ConnectionProvider.getConn()
					.prepareStatement("UPDATE user SET password=?  WHERE Id=?");
			preStat.setString(1, passVal.getNewPass1());
			preStat.setInt(2, user.id);
			preStat.executeUpdate();
			FacesContext.getCurrentInstance().addMessage("form:izmenaErr",
					new FacesMessage("Uspesno ste izmenili sifre!"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "konobar";
	}
}
