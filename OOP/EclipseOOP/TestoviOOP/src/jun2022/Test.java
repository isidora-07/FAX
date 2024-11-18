package jun2022;

public class Test {
	public static void main(String[] args) {
		I x = new D();
		
		if(x instanceof I) System.out.println("I");
		if(x instanceof J) System.out.println("J");
		if(x instanceof C) System.out.println("C");
		if(x instanceof D) System.out.println("D");
	}
}

interface I {}

interface J {}

class C implements I {}

class D extends C implements J {}
