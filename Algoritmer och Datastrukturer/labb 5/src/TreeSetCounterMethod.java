
public class TreeSetCounterMethod implements Comparable<TreeSetCounterMethod>{
	private int counter;
	private String name;
	
	/**
	 * Constructor
	 * @param target; the word
	 * @param number; ammount of the words occured
	 */
	public TreeSetCounterMethod(String target, int number) {
		name = target;
		counter = number;
	}
	//Get the counter
	public int getCounter() {
		return counter;
	}
	//Set the counter
	public void setCounter(int counter) {
		this.counter = counter;
	}
	//Get the name
	public String getName() {
		return name;
	}
	//Set the name
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	/**
	 * Compares different words
	 * @param arg0; the word set with name and counter
	 */
	public int compareTo(TreeSetCounterMethod arg0) {
		return name.compareTo(arg0.getName());
	}
}
