package primer_05;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Test {

	public static void main(String[] args) {
		ObjectOutputStream oos = null;
		
		Student student1 = new Student("Isidora", "123/2017");
		try {
			FileOutputStream fos = new FileOutputStream("SerijalizovaniObjekat.bin");
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(student1);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
