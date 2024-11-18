package jul2020;

public class Test4 {
	int variable = 1;

	Test4() {
		variable++;
	}

	public double GetVariable2(float x) {
		return variable + x;
	}
	
	public double GetVariable2(double x) {
		return variable + x;
	}
	
	public static void main(String[] args) {
		Test4 t = new Test4();
		System.out.println(t.GetVariable2(12.3));
	}
	
}
