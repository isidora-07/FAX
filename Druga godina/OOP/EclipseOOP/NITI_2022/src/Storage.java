
public class Storage {
	double slot1;
	double slot2;
	int upisi;

	public Storage() {
		slot1 = 0;
		slot2 = 0;
		upisi = 2;
	}

	public void ispisi(String name) throws InterruptedException {
		synchronized (this) {
			while (upisi > 0)
				wait();

			if (upisi == 0) {
				System.out.println(name + ", slot1: " + slot1);
				upisi++;
				slot1 = 0;
				System.out.println(name + ", slot2 " + slot2);
				upisi++;
				slot2 = 0;
			}

			notifyAll();
		}
	}

	public void upisi(double nextDouble, String name) throws InterruptedException {
		synchronized (this) {
			while (upisi == 0)
				wait();

			if (slot1 == 0) {
				slot1 = nextDouble;
				upisi--;
				System.out.println(name + ", upisujem u slot1 " + slot1);
			} else if (slot2 == 0) {
				slot2 = nextDouble;
				upisi--;
				System.out.println(name + ", upisujem u slot2 " + slot2);
			}

			notifyAll();
		}
	}

}
