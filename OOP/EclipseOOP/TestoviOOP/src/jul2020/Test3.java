package jul2020;

public class Test3 {
	public static void main(String[] args) {
		int[] x = { 0, 1, 2, 3, 4 };

		try {
			System.out.println("x[5]= " + x[5]);
			System.out.println("x[3]= " + x[3]);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("index of bound");
		}  finally {
			System.out.println("must");
		}

		System.out.println("x[0] = " + x[0]);
	}

}
