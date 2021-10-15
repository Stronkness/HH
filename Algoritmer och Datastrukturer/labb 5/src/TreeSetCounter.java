import java.util.*;
import java.io.*;
/**
 * <hi>TreeSetCounter.java</h1>
 * <p>
 * Creates a counter for TreeSet that accuretly counts every word and its frequency
 * </p>
 * @author André Frisk
 * @version 1.0
 * @since  2019-05-07
 */
public class TreeSetCounter {
	public static TreeSet<TreeSetCounterMethod> tree = new TreeSet<TreeSetCounterMethod>();
	public static void main(String[] args) throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(new File("Text.txt"));
		while(scan.hasNext()) {
			addWord(scan.next());
		}
		Iterator<TreeSetCounterMethod> itr = iterator();
		while(itr.hasNext()) {
			TreeSetCounterMethod print = itr.next();
			System.out.println(print.getName() + " " + print.getCounter());
		}
		System.out.println("The following word is the word that occurs the most: " + getMaxFrek());
		makeEmpty();
		if(!itr.hasNext()) {
			System.out.println("The tree have been chopped down!");
		}
	}
	/**
	 * Adds a word to the Tree, if the word already exists, add 1 to the counter of that word
	 * @param x; the word
	 */
	public static void addWord(String x) {
		boolean condition = false;
		Iterator<TreeSetCounterMethod> itr = tree.iterator();
		while(itr.hasNext() && condition == false) {
			TreeSetCounterMethod temp = itr.next();
			if(temp.getName().equals(x)) {
				temp.setCounter(temp.getCounter()+1);
				condition = true;
			}
		}
		if(condition == false) {
			tree.add(new TreeSetCounterMethod(x,1));
		}
	}
	//Empty the Tree (or chop it down you could also say)
	public static void makeEmpty() {
		tree.clear();
	}
	/**
	 * Get the word that occurs the most times
	 * @return; the word occured most times
	 */
	public static String getMaxFrek() {
		Iterator<TreeSetCounterMethod> itr = tree.iterator();
		String maxName = "";
		int maxRepeat = 0;
		while(itr.hasNext()) {
			TreeSetCounterMethod temp = itr.next();
			if(temp.getCounter() > maxRepeat) {
				maxRepeat = temp.getCounter();
				maxName = temp.getName();
			}
		}
		return maxName;
	}
	/**
	 * A iterator
	 * @return; the iterator created
	 */
	public static Iterator<TreeSetCounterMethod> iterator() {
		Iterator<TreeSetCounterMethod> itr = tree.iterator();
		return itr;
	} 
}
