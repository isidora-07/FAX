package jun2021;

class Aa{
	Aa(String mess){
		System.out.println(mess + " from A");
	}
}

class B extends Aa{
	B(String mess){
		super(mess);
		System.out.println("Hello from B");
	}
}

public class RunSubClass {

	public static void main(String[] args) {
		B b = new B("lal");
	}

}
