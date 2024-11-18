package somepackage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "val")
@RequestScoped
public class PasswordValidation {
	private String oldPass;
	private String newPass1;
	private String newPass2;

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass1() {
		return newPass1;
	}

	public void setNewPass1(String newPass1) {
		this.newPass1 = newPass1;
	}

	public String getNewPass2() {
		return newPass2;
	}

	public void setNewPass2(String newPass2) {
		this.newPass2 = newPass2;
	}
}
