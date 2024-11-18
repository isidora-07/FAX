package februar2022;

import java.io.IOException;

class AirPlane {
	public AirPlane() throws IOException, RuntimeException {
		System.out.println("AirPlane");
	}
}

class AirJet extends AirPlane {

	public AirJet() throws IOException, RuntimeException {
		super();
		// TODO Auto-generated constructor stub
	}

}

public class Tester {
	public static void main(String[] args) throws IOException {
		new AirPlane();
	}

}
