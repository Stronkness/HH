/**
 * <hi>Uppgift7.java</h1>
 * <p>method</p>
 * @author André Frisk
 * @version 1.0
 * @since  2019-04-02
 */

package upg191;
import java.util.*;
import java.io.*;

public class Uppgift7 {
	private static Scanner classlist;
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("klasslista.txt");
		classlist = new Scanner(file); 
		
		int index = 4; // Choose index to search for name on that index
		String name = "Bong Nilsson"; // Choose name from klasslista.txt or try with a non existing one
		String checkName = "André Frisk";
		ArrayList<String> names = new ArrayList<String>();
		
		 while(classlist.hasNextLine()){
			 names.add(classlist.nextLine()); // Adds all names from klasslista.txt to Arraylist
		 }
		 System.out.println(findName(names, index));
		 System.out.println(deleteName(names, name));
		 System.out.println(addToList(names, checkName));
		 
		 System.out.println(names);
	}
	/**
	 * @param list; uses the Arraylist in main
	 * @param index; Chosen index to search for that name on this specific index
	 * @return; returns the name on that given index
	 */
	 public static String findName(ArrayList<String> list, int index) {
			 String indexname = list.get(index);
			 return indexname; // returns the name which is located on given index
		 }
	 /**
	  * @param list; uses the Arraylist in main
	  * @param name; name used in main to search for the name in the list 
	  * @return; returns boolean if name exists or not (deleted in this case)
	  */
	 public static boolean deleteName(ArrayList<String> list, String name) {
		 	String compare = "";
		 		for(int i = 0; i < list.size(); i++) {
		 			compare = list.get(i);
		 			if(compare.equals(name)){ 
		 				// if the name exists in the list, erase it and return true
		 				list.remove(i);
		 				return true;
		 		}
		 	}
		 		return false; // if the name does'nt exist, return false
		 	}
	 /**
	  * @param list; uses the Arraylist in main
	  * @param name; name used in main to search for the name in the list
	  * @return; returns false or true if the name exists or adds to the list
	  */
	 public static boolean addToList(ArrayList<String> list, String name) {
		 for(int i = 0; i < list.size(); i++) {
	 			if(list.get(i).compareTo(name) == 0){ // checks if the name is in the list
	 				return false;
	 }
		 }
	 			list.add(name);
	 			Collections.sort(list); // if the program adds the name, sort the list in alphabetical order
	 			return true;
	}
}