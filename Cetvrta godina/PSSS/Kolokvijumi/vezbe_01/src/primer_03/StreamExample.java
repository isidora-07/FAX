package primer_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamExample {
	public static void main(String[] args) {
		InputStream is = System.in; // sa tastature
		InputStreamReader ist = new InputStreamReader(is); // karakter po karakter
		BufferedReader br = new BufferedReader(ist); // citanje iz buffera, linija po linija
				
		try {
			String s;
			
			while((s = br.readLine()) != null) {
				System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) { 
				e.printStackTrace();
			}
		}
		
		
	}
}
