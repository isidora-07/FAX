package somepackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "poslednja")
@RequestScoped
public class PoslednjaProdaja {
	private int idProdavca;
	private String nazivProizvoda;
	private String tip;
	private String opis;
	private int kolicina;
	private String datum;
	private ArrayList<PoslednjaProdaja> poslednjiProizvodi;

	public int getIdProdavca() {
		return idProdavca;
	}

	public void setIdProdavca(int idProdavca) {
		this.idProdavca = idProdavca;
	}

	public String getNazivProizvoda() {
		return nazivProizvoda;
	}

	public void setNazivProizvoda(String nazivProizvoda) {
		this.nazivProizvoda = nazivProizvoda;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
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

	public ArrayList<PoslednjaProdaja> poslednjaProdaja() {
		try {
			poslednjiProizvodi = new ArrayList<PoslednjaProdaja>();
			Statement s = ConnectionProvider.getConn().createStatement();
			ResultSet rs = s.executeQuery("SELECT p.naziv, p.tip, p.opis, COUNT(*) AS kolicina\r\n"
					+ "FROM poslednja_prodaja pp LEFT JOIN proizvod p ON pp.naziv_proizvoda = p.naziv\r\n"
					+ "GROUP BY pp.naziv_proizvoda;");

			while (rs.next()) {
				PoslednjaProdaja p = new PoslednjaProdaja();
				p.setNazivProizvoda(rs.getString("naziv"));
				p.setTip(rs.getString("tip"));
				p.setOpis(rs.getString("opis"));
				p.setKolicina(rs.getInt("kolicina"));

				poslednjiProizvodi.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return poslednjiProizvodi;
	}
}
