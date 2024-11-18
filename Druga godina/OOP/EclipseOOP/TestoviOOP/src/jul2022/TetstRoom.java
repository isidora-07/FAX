package jul2022;

public class TetstRoom {

	public static void main(String[] args) {
		ClassRoom[] crooms = new ClassRoom[10];
		Room[] rooms;
		Lab[] labs;

		for (int i = 0; i < 5; i++) {
			crooms[i] = new ClassRoom();
		}

		rooms = crooms;
		labs = (Lab[]) crooms;
		System.out.println(labs[0].i);
	}

}

interface Facilities {
}

class Room {
	static int i = 0;

	Room() {
		i++;
	}
}

class ClassRoom extends Room implements Facilities {
}

class Lab extends ClassRoom {
}