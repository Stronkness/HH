import java.util.*;
/**
 * <hi>CollectionOps.java</h1>
 * <p>
 * The program function is to add different types of generics to arraylists and then print them out
 * and reverse the lists content.
 * </p>
 * @author André Frisk
 * @version 1.0
 * @since  2019-04-09
 */
public class CollectionOps {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> pluggGrupp = new ArrayList<String>();
		ArrayList<Double> mathV = new ArrayList<Double>();
		ArrayList<Integer> first5 = new ArrayList<Integer>();
		
		list.add("a");
		list.add("b");
		list.add("c");
		
		pluggGrupp.add("Rolle");
		pluggGrupp.add("Krille");
		pluggGrupp.add("Fredrik");
		pluggGrupp.add("Andre");
		
		mathV.add(3.14);
		mathV.add(2.72);
		
		first5.add(1);
		first5.add(2);
		first5.add(3);
		first5.add(4);
		first5.add(5);
		
		print(list);
		print(pluggGrupp);
		print(mathV);
		print(first5);
		
		System.out.println();
		
		print(reverse(list));
		print(reverse(pluggGrupp));
		print(reverse(mathV));
		print(reverse(first5));
	}
	/**
	 * @param <T>; the generic type
	 * @param l; the arraylist
	 */
	public static <T> void print(Collection<T> l) {
		Iterator<T> itrprint = l.iterator();
		
		System.out.print("[");
		
		while(itrprint.hasNext()) {
			System.out.print(itrprint.next());
			
				if(itrprint.hasNext()) {
						System.out.print(",");
			}
		}
		System.out.print("]");
	}
	/**
	 * @param <T>; generic type
	 * @param l; arraylist
	 * @return; return the reversed arraylist
	 */
	public static <T> List<T> reverse(List<T> l){
		List<T> lReverse = new ArrayList<T>(l.size());
		 for(int i = l.size() - 1; i >= 0; i--) {
			lReverse.add(l.get(i));
		 }
		return lReverse;
		 
	}
}
