import java.util.*;

public class SimpleDataStructure {
	private String[] friends;
	private int counter;

	/**
	 * <hi>SimpleDataStructure.java</h1>
	 * <p>
	 * Uses names written to an array by the user and tests the different functions such as add, print, and get.
	 * The main task is to fix problems with the different methods.
	 * </p>
	 * @author André Frisk
	 * @version 1.0
	 * @since  2019-04-09
	 */
	public static void main(String[] arg) {
		String[] addUnlimitedNames = {"Andre", "Fredrik", "Krille", "Philip", "Rolle"};
		SimpleDataStructure myfriends = new SimpleDataStructure();
		
		for(int i = 0; i < addUnlimitedNames.length; i++) {
		myfriends.add(addUnlimitedNames[i]);
		}
		myfriends.printFriends();
		System.out.println(" ");
		System.out.println(myfriends.get(23));
		System.out.println(myfriends.remove("Nicolina"));
		System.out.println(" ");
		myfriends.printFriends();
		System.out.println(" ");
		System.out.println(myfriends.remove("Mansson"));
		System.out.println(" ");
		myfriends.printFriends();
		System.out.println(" ");
		myfriends.addSort("David");
		myfriends.printFriends();
	}
	public SimpleDataStructure() {
		friends = new String[5];
		counter = 0;
	}

	/*
	 * Appends the other friend name to the end of this list.
	 * @param other; name to insert in the array
	 */
	public boolean add(String other) {
		if(counter > friends.length-1){
			String[] newFriends = Arrays.copyOf(friends, friends.length*2);
			friends = Arrays.copyOf(newFriends, newFriends.length);
		}
		friends[counter] = other;
		counter++;
		
		return true;
	}

	/** returns the name at the specified index
	 * @param index; the position in the array to investigate
	 */
	public String get(int index) {
		if(index > friends.length-1) {
			System.out.println("Too high index");
			return "";
		}
		return friends[index];
	}

	/**
	 * removes the first occurrence of the specified element in this list if the
	 * list contains the name
	 * @param name; takes the name from main to remove from the array
	 */
	public boolean remove(String name) {
		for (int i = 0; i < counter; i++) {
			if (friends[i].equals(name)) {
				friends[i] = null;
				if(friends[i] == null) {
					for(int j = i; j < friends.length-1; j++) {
						friends[j] = friends[j + 1];
					}
					friends[friends.length-1] = null;
				}
				counter--;
				return true;
			}
		}
		return false;
	}

	/** prints all names in the array friends */
	public void printFriends() {
		for (int i = 0; i < friends.length; i++) {
			if(friends[i]!= null) {
			System.out.print(friends[i] + " ");
		System.out.println();
	}
		}
		}
	/** adds a name and sorts the list 
	 * @param name; name added to array plus the array gets sorted
	 */
	public void addSort(String name) {
		add(name);
			for(int i = 0; i < friends.length; i++) {
					for(int j = 1; j < friends.length; j++) {
						if(friends[j] != null) {
							if(friends[j-1].compareTo(friends[j])>0) {
								String temporary = friends[j];
								friends[j] = friends[j-1];
								friends[j-1] = temporary;
							}
							if(friends[j].compareTo(friends[j-1])<0) {
								break;
							}
				}
					
				}
			
				
			}
			
	}
}