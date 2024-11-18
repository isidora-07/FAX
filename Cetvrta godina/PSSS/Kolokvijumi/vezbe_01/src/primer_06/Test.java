package primer_06;

import java.beans.XMLEncoder;
import java.io.*;

public class Test {
	public static void main(String[] args) {
		
		XMLEncoder encoder = null;
		
		Student s1 = new Student("Pera Peric", "123/2017");
		Student s2 = new Student("Mika Mikic", "124/2017");
		
		try {
			FileOutputStream fos = new FileOutputStream("student.xml");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			encoder = new XMLEncoder(bos);
			encoder.writeObject(s1);
			encoder.writeObject(s2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(encoder != null) {
				encoder.close();
			}
		}
	}
}
