package labb1;

public class filterTest{
	public static void main(String[] args) {
		Filter fil = new Filter2();
		String[] test = {"hej", "b", "hejsan", "jeh", "be", "rohullah"};
		
		String[] exe = fil.filter(test, fil);
		System.out.println(exe.length);
		for(int i = 0; i < exe.length; i++) {
			System.out.println(exe[i]);
		}
	}
}