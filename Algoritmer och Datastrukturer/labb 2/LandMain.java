import java.io.*;
import java.util.*;
/**
 * <hi>LandMain.java</h1>
 * <p>
 * The program function is to read from a file the following: country, population, size and capitaltown.
 * Then the program must with help of the Comparators, the method and arraylist/iterators 
 * create a menu which the user must decide which type from the file must be sorted in alphabetical
 * order or size of the numbers starting from the lowest.
 * Choose 1 for countryname, 2 for citizenamount, 3 for size. This will be printed out
 * in a specific file created by the program.
 * </p>
 * @author André Frisk
 * @version 1.0
 * @since  2019-04-09
 */
public class LandMain {
	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(new File("europa.txt"));
			
			System.out.println("Choose which to sort. 1 for Countrynames. 2 for Citizens. 3 for Size");
			
			@SuppressWarnings("resource")
			Scanner menu = new Scanner(System.in);
			
			ArrayList<Land> a_list = new ArrayList<Land>();
			String menu2 = menu.next();
			
			// Creates files for specific sorting
			PrintWriter countryname = new PrintWriter(new FileOutputStream("Countryname.txt"), true);
			PrintWriter citizens = new PrintWriter(new FileOutputStream("Citizens.txt"), true);
			PrintWriter sizes = new PrintWriter(new FileOutputStream("Sizes.txt"), true);
			scan.nextLine();
			
			// Adds every word and number from the file to an arraylist
			while (scan.hasNext()){
			    a_list.add(new Land(scan.next(),Integer.parseInt(scan.next()),Integer.parseInt(scan.next()),scan.next()));
			}
			// Country sorting
			if((Integer.parseInt(menu2) == 1)) {
			Collections.sort(a_list, new LandComparatorCountry());
			Iterator<Land> itr1 = a_list.iterator();
			
			while(itr1.hasNext()) {
				countryname.println(itr1.next());
				countryname.println(" ");
				
			}
		countryname.close();
			}
		// Citizen sorting
		if((Integer.parseInt(menu2) == 2)) {
		Collections.sort(a_list, new LandComparatorCitizen());
		Iterator<Land> itr2 = a_list.iterator();
		
		while(itr2.hasNext()) {
			citizens.println(itr2.next());
			citizens.println(" ");
		}
	citizens.close();
		}
	// Size sorting
	if(Integer.parseInt(menu2) == 3) {
	Collections.sort(a_list, new LandComparatorSize());
	Iterator<Land> itr3 = a_list.iterator();
	
	while(itr3.hasNext()) {
		sizes.println(itr3.next());
		sizes.println(" ");
	}
sizes.close();
	}
		
		
		
		} 
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
}
