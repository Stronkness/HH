package labb2.task3;

public class filtermethod extends Filter{
	public static void main(String[] args) {
		filtermethod fil = new filtermethod();
		String[] test = {"hej", "b", "hejsan", "jeh", "be", "rohullah"};
		
		String[] exe = fil.filter(test, fil);
		System.out.println(exe.length);
		if(exe.length == 6) {
			System.out.println("Yes it works!");
		}else {
			System.out.println("Naaaah it doesnt work");
		}
		for(int i = 0; i < exe.length; i++) {
			System.out.println(exe[i]);
		}
	}
	
	@Override
	public boolean accept(String x) {
		 if(x.length() <= 3) {
				return true;
			}
			return false;
		}
	 
}
