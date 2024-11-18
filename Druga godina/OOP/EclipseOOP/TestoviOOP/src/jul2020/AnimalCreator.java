package jul2020;

class Animal {
	protected static void testClassMethod() {
		System.out.println("The class" + " method in Animal.");
	}
	
	public void testInstanceMthod() {
		System.out.println("The instance" + " method in Animal.");
	}
}

class Cat extends Animal {
	protected static void testClassMethod() {
		System.out.println("The class" + " method in Cat.");
	}
	
	public void testInstanceMthod() {
		System.out.println("The instance" + " method in Cat.");
	}
}

public class AnimalCreator {
	public static void main(String[] args) {
		Cat myCat = new Cat();
		Animal animal = myCat;
		Animal.testClassMethod();
		animal.testInstanceMthod();
	}
}