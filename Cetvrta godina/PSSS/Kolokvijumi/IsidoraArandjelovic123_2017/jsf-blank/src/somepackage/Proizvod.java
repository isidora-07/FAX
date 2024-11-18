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
	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

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

			if (idKorisnika == 0)
				preStat = ConnectionProvider.getConn().prepareStatement("SELECT * FROM proizvod");
			else {
				preStat = ConnectionProvider.getConn().prepareStatement("SELECT * FROM proizvod WHERE id_korisnika=?");
				preStat.setInt(1, idKorisnika);
			}
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

	private String isValid(String mera) {
		if (mera.length() > 4)
			return "Greska.. mera mora da sadrzi manje od 4 cifre";
		if (mera.matches("^[a-z]*$") || mera.matches("^[A-Z]*$")) {
			return "ok";
		}
		return "Mera sadrzi samo cifre!";
	}

	public void dodajOglas(int idKorisika) {
		try {
			String message = isValid(mera);
			if (!message.equals("ok")) {
				FacesContext.getCurrentInstance().addMessage("form:dodavanjeErr", new FacesMessage(message));
				return;
			}

			PreparedStatement preStat = ConnectionProvider.getConn().prepareStatement(
					"INSERT INTO proizvod(id, naziv, cena, mera, kolicina, id_korisnika) VALUES (null, ?, ?, ?, ?, ?)");

			preStat.setString(1, naziv);
			preStat.setInt(2, cena);
			preStat.setString(3, mera);
			preStat.setInt(4, kolicina);
			preStat.setInt(5, idKorisika);
			preStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Proizvod> proizvodiUKorpi = new ArrayList<Proizvod>();

	public void daodajUKorpu(int idProizvoda) {
		try {
			PreparedStatement preStat = ConnectionProvider.getConn()
					.prepareStatement("SELECT * FROM proizvod WHERE id=?");
			preStat.setInt(1, idProizvoda);
			ResultSet rs = preStat.executeQuery();
			rs.next();

			Proizvod p = new Proizvod();
			p.setId(rs.getInt("id"));
			p.setNaziv(rs.getString("naziv"));
			p.setCena(rs.getInt("cena"));
			p.setMera(rs.getString("mera"));
			p.setKolicina(rs.getInt("kolicina"));
			p.setId_korisnika(rs.getInt("id_korisnika"));

			proizvodiUKorpi = listaProizvodaKorpa();
			proizvodiUKorpi.add(p);
			sessionMap.put("korpa", proizvodiUKorpi);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Proizvod> listaProizvodaKorpa() {
		if (sessionMap.get("korpa") != null) {
			proizvodiUKorpi = (ArrayList<Proizvod>) sessionMap.get("korpa");
		}
		return proizvodiUKorpi;
	}

	public int ukupnaCena() {
		int ukupno = 0;
		for (Proizvod p : listaProizvodaKorpa()) {
			ukupno += p.cena;
		}
		return ukupno;
	}

	public void kupi(int idKorisnika) {
		int size = listaProizvodaKorpa().size();

		for (Proizvod p : proizvodiUKorpi) {
			System.out.println("Ime " + p.naziv);
			System.out.println("ID " + p.id);
			try {
				PreparedStatement preStatUpdate = ConnectionProvider.getConn()
						.prepareStatement("UPDATE proizvod SET kolicina = kolicina - 1 WHERE id=?");
				preStatUpdate.setInt(1, p.id);
				preStatUpdate.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		PreparedStatement preStatKupovina;
		try {
			preStatKupovina = ConnectionProvider.getConn().prepareStatement(
					"INSERT INTO kupovina(id_korisnika, broj_kupljenih, racun, id) VALUES (?, ?, ?, null)");
			preStatKupovina.setInt(1, idKorisnika);
			preStatKupovina.setInt(2, size);
			preStatKupovina.setInt(3, ukupnaCena());
			preStatKupovina.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		sessionMap.remove("korpa");
		proizvodiUKorpi = new ArrayList<Proizvod>();
	}

}
