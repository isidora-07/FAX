package somepackage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "kup")
@RequestScoped
public class Kupovina {
	private int id_korisnika;
	private int broj_kupljenih;
	private int racun;
	private int id;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
