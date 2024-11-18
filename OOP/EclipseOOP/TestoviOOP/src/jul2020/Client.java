package jul2020;

public class Client {
	static void doCalc(byte a) {
		System.out.println("byte...");
	}

	static void doCalc(long a, long b) {
		System.out.println("long, long");
	}

	static void doCalc(Byte s1, Byte s2) {
		System.out.println("Byte, Byte");
	}

	public static void main(String[] args) {
		Byte b = (byte) 1000001;
		doCalc(5, 5);
	}
}
