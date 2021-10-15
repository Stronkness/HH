/**
 * <hi>Uppgift4.java</h1>
 * <p>
 * The user must write an array with numbers and choose a index plus a number. The program will then
 * check with the index if that position in the array is busy, if not, the number chosen will be inserted there.
 * Otherwise the program will make a copy of the array and double the length and move all indexnumbers
 * from the index and higher one step to the right so the indexnumber deicded by the user can be inserted.
 * If there is a zero in the end of the array and the index is busy, the program moves all numbers from index
 * and higher to the right by one.
 * </p>
 * @author André Frisk
 * @version 1.0
 * @since  2019-04-02
 */

package upg191;
import java.util.Arrays;

public class Uppgift4 {
public static void main(String[] args) {
		
		int[] test = {3,4,5,6}; // Choose int[] for the specific cases
		int x = 9; // The number which will be inserted into the array
		int index = 2; // Choose an index
		
		addAtIndex(test, x, index);
	}
/**
 * @param a; the array used in main
 * @param x; the number chosen by the user to be inserted in the array
 * @param index; the positionnumber where param x will be inserted
 */
public static void addAtIndex(int [] a, int x, int index) {
		
	int[] doubleammount = new int[4];
	
	
		if(index > a.length-1) {
			System.out.println("ERROR");
			System.exit(0); // Stops the program if index is bigger than arraylength
		}
		else if(a[a.length-1]==0) {
			for(int i = a.length-1; i > index; i--) { // Starts from the last position in the array and moves the indexnumbers backwards until the arrival of given index
				a[i] = a[i - 1];
			}
			a[index] = x;
			System.out.println(Arrays.toString(a));
		}
		else if(a[index]!=0){
			doubleammount = Arrays.copyOf(a, a.length * 2); // The arrays size become twice as big
				for(int i = doubleammount.length-1; i > index; i--) {
					doubleammount[i] = doubleammount[i - 1];
				}
				doubleammount[index] = x;
				System.out.println(Arrays.toString(doubleammount));
		}
			
		else{
			a[index] = x;
			System.out.println(Arrays.toString(a));
		}
	}
}
