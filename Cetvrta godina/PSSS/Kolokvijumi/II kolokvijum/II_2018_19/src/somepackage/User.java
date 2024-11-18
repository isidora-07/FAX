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
	int id;
	private String username;
	private String password;
	private String name;
	private int year;
	private Role role_id;
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	private ArrayList<User> users;
	private ArrayList<TableOrder> orders;

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

	public Role getRole_id() {
		return role_id;
	}

	public void setRole_id(Role role_id) {
		this.role_id = role_id;
	}

	public String login() {
		try {
			PreparedStatement preStat = ConnectionProvider.getConn()
					.prepareStatement("SELECT * FROM user WHERE username=? AND password=?");
			preStat.setString(1, username);
			preStat.setString(2, password);
			ResultSet rs = preStat.executeQuery();
			rs.next();
			User user = new User();
			user.setId(rs.getInt("Id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setName(rs.getString("name"));
			user.setYear(rs.getInt("year"));

			PreparedStatement preStatRole = ConnectionProvider.getConn()
					.prepareStatement("SELECT * FROM role WHERE id=?");
			preStatRole.setInt(1, rs.getInt("role_id"));
			ResultSet rsRole = preStatRole.executeQuery();
			rsRole.next();
			Role role = new Role();
			role.setId(rsRole.getInt("id"));
			role.setName(rsRole.getString("name"));
			user.setRole_id(role);

			sessionMap.put("logged_user", user);
			if (user.role_id.getId() == 1) {
				return "admin.xhtml?faces-redirect=true";
			}
			return "konobar.xhtml?faces-redirect=true";

		} catch (Exception e) {
			System.out.println("Username " + username + " password " + password);
			System.out.println("LOGIN error");
			System.out.println(e.getMessage());
		}
		FacesContext.getCurrentInstance().addMessage("form:error", new FacesMessage("Nije dobar login"));
		return "index";
	}

	private String isValid() {
		users = usersList();
		for (User u : users) {
			if (u.getUsername().equals(username)) {
				return "Username je koriscen.";
			}
		}

		if (year < 18 || year > 65) {
			return "Godine moraju biti u intervalu 18-65";
		}

		return isPasswordValid(password);
	}

	private String isPasswordValid(String pass) {
		if (pass.matches(".*[A-Z].*") && pass.matches(".*[a-z].*") && pass.matches(".*[0-9].*")
				&& (pass.length() >= 6 && pass.length() <= 10)) {
			return "ok";
		} else {
			return "Password mora biti aA1";
		}
	}

	public ArrayList<TableOrder> orderList() {
		orders = null;

		try {
			orders = new ArrayList<TableOrder>();
			Statement s = ConnectionProvider.getConn().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM table_order");

			while (rs.next()) {
				TableOrder o = new TableOrder();
				o.setId(rs.getInt("Id"));
				o.setDescription(rs.getString("description"));
				o.setQuantity(rs.getInt("quantity"));

				orders.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orders;
	}

	public ArrayList<User> usersList() {
		users = null;

		try {
			users = new ArrayList<User>();
			Statement s = ConnectionProvider.getConn().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM user WHERE Id!=1");

			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("Id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setName(rs.getString("name"));
				u.setYear(rs.getInt("year"));

				Role role = new Role();
				role.setId(2);
				role.setName("konobar");
				u.setRole_id(role);

				users.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	public void addUser() {
		String message = isValid();
		if (message.equals("ok")) {
			try {
				PreparedStatement preStat = ConnectionProvider.getConn()
						.prepareStatement("INSERT INTO user VALUES(null,?,?,?,?,?)");
				preStat.setString(1, username);
				preStat.setString(2, password);
				preStat.setString(3, name);
				preStat.setInt(4, year);
				preStat.setInt(5, 2);
				preStat.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			FacesContext.getCurrentInstance().addMessage("addUserForm:addUserError", new FacesMessage(message));
		}

	}

	public void delete(int id) {
		try {
			PreparedStatement preStat = ConnectionProvider.getConn().prepareStatement("DELETE FROM user WHERE Id=?");
			preStat.setInt(1, id);
			preStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void changePass(PasswordValidation passVal) {
		User u = (User) sessionMap.get("logged_user");
		if (passVal.getOldPass().equals(u.getPassword()) && isPasswordValid(passVal.getNewPass1()).equals("ok")
				&& passVal.getNewPass1().equals(passVal.getNewPass2())) {
			try {
				PreparedStatement preStat = ConnectionProvider.getConn()
						.prepareStatement("UPDATE user SET password=? WHERE id=?");
				preStat.setString(1, passVal.getNewPass1());
				preStat.setInt(2, u.getId());
				preStat.executeUpdate();
				setPassword(passVal.getNewPass1());

				FacesContext.getCurrentInstance().addMessage("formKonobar:success",
						new FacesMessage("Uspesno promenjena sifra"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			FacesContext.getCurrentInstance().addMessage("formKonobar:errorPass",
					new FacesMessage("Greska prilikom promene lozinke"));
		}

	}

}
