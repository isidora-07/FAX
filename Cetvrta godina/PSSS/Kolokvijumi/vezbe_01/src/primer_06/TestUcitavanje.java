package primer_06;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestUcitavanje {
	public static void main(String[] args) {
		XMLDecoder decoder = null;
		
		try {
			FileInputStream fis = new FileInputStream("student.xml");
			BufferedInputStream bis = new BufferedInputStream(fis);
			decoder = new XMLDecoder(bis);
			
			Student s1 = (Student)decoder.readObject();
			Student s2 = (Student)decoder.readObject();
			
			System.out.println(s1);
			System.out.println(s2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(decoder != null) {
				decoder.close();
			}
		}
		
	}
}
