package somepackage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "kup")
@RequestScoped
public class Kupovina {
	private int id_korisnika;
	private int broj_kupljenih;
	private int racun;
	private int id;
	private ArrayList<Kupovina> istorija = null;

	public int getId_korisnika() {
		return id_korisnika;
	}

	public void setId_korisnika(int id_korisnika) {
		this.id_korisnika = id_korisnika;
	}

	public int getBroj_kupljenih() {
		return broj_kupljenih;
	}

	public void setBroj_kupljenih(int broj_kupljenih) {
		this.broj_kupljenih = broj_kupljenih;
	}

	public int getRacun() {
		return racun;
	}

	public void setRacun(int racun) {
		this.racun = racun;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public ArrayList<Kupovina> istorijaKupovina(int idKorisnika) {
		try {
			istorija = new ArrayList<Kupovina>();
			PreparedStatement preStat = ConnectionProvider.getConn()
					.prepareStatement("SELECT * FROM kupovina WHERE id_korisnika=?");
			preStat.setInt(1, idKorisnika);
			ResultSet rs = preStat.executeQuery();

			while (rs.next()) {
				Kupovina k = new Kupovina();
				k.setId_korisnika(rs.getInt("id_korisnika"));
				k.setBroj_kupljenih(rs.getInt("broj_kupljenih"));
				k.setRacun(rs.getInt("racun"));
				k.setId(rs.getInt("id"));

				istorija.add(k);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return istorija;
	}
}
