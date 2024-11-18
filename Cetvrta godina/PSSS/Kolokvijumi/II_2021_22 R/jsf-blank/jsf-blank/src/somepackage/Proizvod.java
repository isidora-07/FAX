package somepackage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "pr")
@RequestScoped
public class Proizvod {
	private int id;
	private String naziv;
	private int cena;
	private String mera;
	private int kolicina;
	private int id_korisnika;
	private ArrayList<Proizvod> proizvodi = null;

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

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public String getMera() {
		return mera;
	}

	public void setMera(String mera) {
		this.mera = mera;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public int getId_korisnika() {
		return id_korisnika;
	}

	public void setId_korisnika(int id_korisnika) {
		this.id_korisnika = id_korisnika;
	}

	public ArrayList<Proizvod> listaProizvoda(int idKorisnika) {
		try {
			proizvodi = new ArrayList<Proizvod>();
			PreparedStatement preStat;

			if (idKorisnika != 0) {
				preStat = ConnectionProvider.getConn().prepareStatement("SELECT * FROM proizvod WHERE id_korisnika=?");
				preStat.setInt(1, idKorisnika);
			} else
				preStat = ConnectionProvider.getConn().prepareStatement("SELECT * FROM proizvod");

			ResultSet rs = preStat.executeQuery();

			while (rs.next()) {
				Proizvod p = new Proizvod();
				p.setId(rs.getInt("id"));
				p.setNaziv(rs.getString("naziv"));
				p.setCena(rs.getInt("cena"));
				p.setMera(rs.getString("mera"));
				p.setKolicina(rs.getInt("kolicina"));
				p.setId_korisnika(rs.getInt("id_korisnika"));

				proizvodi.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return proizvodi;
	}

	public void obrisiOglas(int idOglasa) {
		try {
			PreparedStatement preStat = ConnectionProvider.getConn()
					.prepareStatement("DELETE FROM proizvod WHERE id=?");
			preStat.setInt(1, idOglasa);
			preStat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String dodajOglas(int idKorisnika) {
		try {
			PreparedStatement preStat = ConnectionProvider.getConn().prepareStatement(
					"INSERT INTO proizvod(id, naziv, cena, mera, kolicina, id_korisnika) VALUES (null, ?, ?, ?, ?, ?)");
			preStat.setString(1, naziv);
			preStat.setInt(2, cena);
			preStat.setString(3, mera);
			preStat.setInt(4, kolicina);
			preStat.setInt(5, idKorisnika);
			preStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "oglasi";
	}
		
	public void dodajProizvodUKorpu(int idProizvoda, int idKor) {

	}
}
