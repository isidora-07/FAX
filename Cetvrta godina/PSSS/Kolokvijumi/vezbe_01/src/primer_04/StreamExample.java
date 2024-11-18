package primer_04;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class StreamExample {
	public static void main(String[] args) {
		FileReader fr = null;
		BufferedReader br = null;
		
		FileWriter fw = null;
		PrintWriter pw = null;
		
		try {
			fr = new FileReader("ulaz.txt");
			fw = new FileWriter("izlaz.txt");
			
			br = new BufferedReader(fr);
			pw = new PrintWriter(fw);
			
			String s;
			
			while((s = br.readLine()) != null) {
				pw.write(s + "\r\n");
				// da bismo bili sigurni da je stvarno iz bafera otislo u fajl
				pw.flush();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(pw != null) {
				pw.close();
			}
			
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
