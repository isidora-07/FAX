package jul2022;

interface Friend {
	public String getName();
}

class Cat implements Friend {
	public String getName() {
		return "Kitty";
	}
}

public class Dog implements Friend {

	public String getName() throws RuntimeException {
		return "Doggy";
	}
	
	public static void main(String[] args) {
		Friend fried = new Dog();
		Cat cat = new Cat();
		System.out.println(cat.getName());
	}
}
