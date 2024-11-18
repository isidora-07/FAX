package Utilities;

import java.math.BigInteger;
import java.security.MessageDigest;

public class PasswordHash {
	public static String sha1(String password) {
		String hash = "";
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			digest.update(password.getBytes("utf8"));
			hash = String.format("%040x", new BigInteger(1, digest.digest()));
			hash = hash.toUpperCase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hash;
	}
}
