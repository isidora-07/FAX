package somepackage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "proizvod")
@RequestScoped
public class Proizvod {
	private int id;
	private String naziv;
	private String tip;
	private String opis;
	private int kolicina;
	private ArrayList<Proizvod> proizvodi;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getkolicina() {
		return kolicina;
	}

	public void setkolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public ArrayList<Proizvod> getProizvodi() {
		try {
			proizvodi = new ArrayList<Proizvod>();
			Statement s = ConnectionProvider.getConn().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM proizvod WHERE kolicina > 0");

			while (rs.next()) {
				Proizvod p = new Proizvod();
				p.setId(rs.getInt("id"));
				p.setNaziv(rs.getString("naziv"));
				p.setTip(rs.getString("tip"));
				p.setOpis(rs.getString("opis"));
				p.setkolicina(rs.getInt("kolicina"));
				proizvodi.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proizvodi;
	}

	public void dodajProizvod() {
		try {
			PreparedStatement prepStat = ConnectionProvider.getConn()
					.prepareStatement("INSERT INTO proizvod(id, naziv, tip, opis, kolicina) VALUES (NULL, ?, ?, ?, ?)");
			prepStat.setString(1, naziv);
			prepStat.setString(2, tip);
			prepStat.setString(3, opis);
			prepStat.setInt(4, kolicina);
			prepStat.executeUpdate();

			FacesContext.getCurrentInstance().addMessage("form:addErr", new FacesMessage("Saccessfull"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int idProizvoda) {
		try {
			PreparedStatement preStat = ConnectionProvider.getConn()
					.prepareStatement("DELETE FROM proizvod WHERE id=?");
			preStat.setInt(1, idProizvoda);
			preStat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void naruciProizvod(int idProizvoda, int userId) {
		try {
			PreparedStatement preStat = ConnectionProvider.getConn()
					.prepareStatement("UPDATE proizvod SET kolicina=kolicina-1 WHERE id=?");
			preStat.setInt(1, idProizvoda);
			preStat.executeUpdate();

			dodajPoslednjuProdaju(idProizvoda, userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void dodajPoslednjuProdaju(int idProizvoda, int userId) {
		try {
			PreparedStatement statProizvod = ConnectionProvider.getConn()
					.prepareStatement("SELECT * FROM proizvod WHERE id=?");
			statProizvod.setInt(1, idProizvoda);
			ResultSet rs = statProizvod.executeQuery();
			rs.next();

			Proizvod p = new Proizvod();
			p.setId(rs.getInt("id"));
			p.setNaziv(rs.getString("naziv"));
			p.setTip(rs.getString("tip"));
			p.setOpis(rs.getString("opis"));
			p.setkolicina(rs.getInt("kolicina"));

			PreparedStatement statInsert = ConnectionProvider.getConn().prepareStatement(
					"INSERT INTO poslednja_prodaja(id_prodavca, naziv_proizvoda, datum) VALUES (?, ?, ?)");
			statInsert.setInt(1, userId);
			statInsert.setString(2, p.naziv);

			LocalDateTime myDateObj = LocalDateTime.now();
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			String formattedDate = myDateObj.format(myFormatObj);
			statInsert.setString(3, formattedDate);

			statInsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
