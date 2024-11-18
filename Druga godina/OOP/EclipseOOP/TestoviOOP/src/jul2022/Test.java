package jul2022;

public class Test {
	private int size;
	
	public Test(int size) {
		this.size = size;
	}
	
	public static void sendHome(Test p, int newSize) {
		p = new Test(newSize);
		p.size = 4;
	}
	
	public static final void main(String[] args) {
		final Test phone = new Test(3);
		sendHome(phone, 7);
		System.out.println(phone.size);
	}
}
