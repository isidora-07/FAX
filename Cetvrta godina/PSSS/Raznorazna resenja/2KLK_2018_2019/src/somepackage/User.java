package somepackage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import com.mysql.cj.Session;

@ManagedBean(name="user")
@RequestScoped
public class User {
	private int id;
	private String username;
	private String password;
	private String name;
	private int year;
	private Role role;
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	private ArrayList<User> users;
	private ArrayList<Order> orders;
	
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
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public ArrayList usersList() {
		users = null;
		try {
			users = new ArrayList<User>();
			Statement s = ConnectionProvider.getConn().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM user WHERE Id!=1");
			while(rs.next()) {
				User u = new User();
				u.setId(rs.getInt("Id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setName(rs.getString("name"));
				u.setYear(rs.getInt("year"));
				
				Role role = new Role();
				role.setId(2);
				role.setName("konobar");
				u.setRole(role);
				
				users.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public ArrayList ordersList() {
		orders = null;
		try {
			orders = new ArrayList<Order>();
			Statement s = ConnectionProvider.getConn().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM table_order");
			while(rs.next()) {
				Order o = new Order();
				o.setId(rs.getInt("Id"));
				o.setDescription(rs.getString("description"));
				o.setQuantity(rs.getInt("quantity"));
				orders.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return orders;
	}
	
	public String login() {
		int result = 0;
		
		try {
			PreparedStatement preStat = ConnectionProvider.getConn().prepareStatement("SELECT * FROM user WHERE username=? AND password=?");
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
			
			PreparedStatement preStatRole = ConnectionProvider.getConn().prepareStatement("SELECT * FROM role WHERE id=?");
			preStatRole.setInt(1, rs.getInt("role_id"));
			ResultSet rsRole = preStatRole.executeQuery();
			rsRole.next();
			Role role = new Role();
			role.setId(rsRole.getInt("id"));
			role.setName(rsRole.getString("name"));
			user.setRole(role);
			
			sessionMap.put("logged_user", user);
			if(user.role.getId() == 1) {
				return "admin.xhtml?faces-redirect=true";
			}
			return "konobar.xhtml?faces-redirect=true";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage("form:error", new FacesMessage("Bad login incredentials"));
		return "index";
	}
	
	
	
	private String isPasswordValid(String pass) {
		if(pass.matches(".*[A-Z].*") && pass.matches(".*[a-z].*") && pass.matches(".*[0-9].*")) {
			return "ok";
		}
		else {
			return "Password must be stronger aA1";
		}
	}
	
	private String isValid() {
		users = usersList();
		for(User u : users) {
			if(u.getUsername().equals(username)) {
				return "Username is used";
			}
		}
		
		if(year < 18 || year > 65) {
			return "Years must be in interval of 18 to 65";
		}
		
		return isPasswordValid(password);
	}
	
	public void changePass(PasswordValidation pv) {
		System.out.println(pv.getOldPass() + " " + pv.getNewPass1() + " " + pv.getNewPass2() + " " + isPasswordValid(pv.getNewPass1()) + " " + password);
		User u = (User) sessionMap.get("logged_user");
		if(pv.getOldPass().equals(u.getPassword()) && isPasswordValid(pv.getNewPass1()).equals("ok") && pv.getNewPass1().equals(pv.getNewPass2())) {
			try {
				PreparedStatement preStat = ConnectionProvider.getConn().prepareStatement("UPDATE user SET password=? WHERE id=?");
				preStat.setString(1, pv.getNewPass1());
				preStat.setInt(2, u.getId());
				preStat.executeUpdate();
				setPassword(pv.getNewPass1());
				
				FacesContext.getCurrentInstance().addMessage("formKonobar:success", new FacesMessage("Uspesno promenjena sifra"));
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			FacesContext.getCurrentInstance().addMessage("formKonobar:errorPass", new FacesMessage("Greska prilikom promene lozinke"));
		}
	}
	
	public void addUser() {
		String message = isValid();
		if(message.equals("ok")) {
			try {
				PreparedStatement preStat = ConnectionProvider.getConn().prepareStatement("INSERT INTO user VALUES(null,?,?,?,?,?)");
				preStat.setString(1, username);
				preStat.setString(2, password);
				preStat.setString(3, name);
				preStat.setInt(4, year);
				preStat.setInt(5, 2);
				preStat.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			FacesContext.getCurrentInstance().addMessage("formAddingUser:errorAddingUser", new FacesMessage(message));
		}
	}
	
	public void delete(int id) {
		try {
			PreparedStatement preStat = ConnectionProvider.getConn().prepareStatement("DELETE FROM user WHERE id=?");
			preStat.setInt(1, id);
			preStat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
