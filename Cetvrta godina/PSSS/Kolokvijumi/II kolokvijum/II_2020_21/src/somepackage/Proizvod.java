package somepackage;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	private ArrayList<Proizvod> proizvodiN;

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

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public ArrayList<Proizvod> listaProizvoda() {
		proizvodi = null;

		try {
			proizvodi = new ArrayList<Proizvod>();
			Statement s = ConnectionProvider.getConn().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM proizvod");

			while (rs.next()) {
				Proizvod p = new Proizvod();
				p.setId(rs.getInt("id"));
				p.setNaziv(rs.getString("naziv"));
				p.setTip(rs.getString("tip"));
				p.setOpis(rs.getString("opis"));
				p.setKolicina(rs.getInt("kolicina"));
				proizvodi.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return proizvodi;
	}

	public ArrayList<Proizvod> listaProizvodaNaruci() {
		proizvodiN = null;

		try {
			proizvodiN = new ArrayList<Proizvod>();
			Statement s = ConnectionProvider.getConn().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM proizvod");

			while (rs.next()) {
				if (rs.getInt("kolicina") > 0) {
					Proizvod p = new Proizvod();
					p.setId(rs.getInt("id"));
					p.setNaziv(rs.getString("naziv"));
					p.setTip(rs.getString("tip"));
					p.setOpis(rs.getString("opis"));
					p.setKolicina(rs.getInt("kolicina"));
					proizvodiN.add(p);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return proizvodiN;
	}

	public void delete(int id) {

		try {
			PreparedStatement preStat = ConnectionProvider.getConn()
					.prepareStatement("DELETE FROM proizvod WHERE id=?");
			preStat.setInt(1, id);
			preStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void dodajProizvod() {
		try {
			PreparedStatement preStat = ConnectionProvider.getConn()
					.prepareStatement("INSERT INTO proizvod(id, naziv, tip, opis, kolicina) VALUES (null,?,?,?,?)");
			preStat.setString(1, naziv);
			preStat.setString(2, tip);
			preStat.setString(3, opis);
			preStat.setInt(4, kolicina);
			preStat.executeUpdate();

			FacesContext.getCurrentInstance().addMessage("from:addErr",
					new FacesMessage("Uspesno ste dodali proizvod!"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void naruci(int id, int userId) {

		try {
			PreparedStatement preStat = ConnectionProvider.getConn()
					.prepareStatement("UPDATE proizvod SET kolicina = kolicina - 1 WHERE id = ?");
			preStat.setInt(1, id);
			preStat.executeUpdate();
			System.out.println("id proizvoda " + id + ", id prodavca " + userId);
			proizvod(id, userId);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void proizvod(int id, int userId) {
		try {
			PreparedStatement preStat = ConnectionProvider.getConn()
					.prepareStatement("SELECT * FROM proizvod WHERE id=?");
			preStat.setInt(1, id);
			System.out.println("GET PRODUCT ");
			ResultSet rs = preStat.executeQuery();
			rs.next();
			Proizvod p = new Proizvod();
			p.setId(rs.getInt("id"));
			p.setNaziv(rs.getString("naziv"));
			p.setTip(rs.getString("tip"));
			p.setOpis(rs.getString("opis"));
			p.setKolicina(rs.getInt("kolicina"));
			
			System.out.println("GET PRODUCT RADI: " + p.id);
			System.out.println("GET PORUDZBINA");
			PreparedStatement preStatInsert = ConnectionProvider.getConn().prepareStatement(
					"INSERT INTO poslednja_prodaja(id_prodavca, naziv_proizvoda) VALUES (?,?)");
			preStatInsert.setInt(1, userId);
			preStatInsert.setString(2, p.naziv);
			preStatInsert.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
