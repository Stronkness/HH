package labb3.task1;

public class Main {
	public static void main(String[] args) {
		Tree<String> t =
				new Tree<String>("top",
				new Tree[] {
				new Tree("sub1"),
				new Tree("sub2")
				});
				Saver s = new Saver();
				String r = s.save(t);
				System.out.println(r);
	}
}
