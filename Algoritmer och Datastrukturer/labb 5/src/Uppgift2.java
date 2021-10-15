import java.util.*;
import java.io.*;
/**
 * <hi>Uppgift2.java</h1>
 * <p>
 * Tests iterator with TreeSet
 * </p>
 * @author André Frisk
 * @version 1.0
 * @since  2019-05-07
 */
public class Uppgift2 {
	public static void main(String[] args) {
		
	try {
		Scanner scan = new Scanner(new File("Text.txt"));
		TreeSet<String> Asp = new TreeSet<String>();
		
		while(scan.hasNext()) {
			Asp.add(scan.next());
		}
		Iterator<String> Ek = Asp.iterator();
		while(Ek.hasNext()) {
			System.out.print(Ek.next() + " ");
		}
	}
	catch (FileNotFoundException e) {
		e.printStackTrace();
		}
}
}
