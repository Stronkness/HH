import java.util.*;
import java.io.*;
/**
 * <hi>Search.java</h1>
 * <p>
 * Uses Linear- and Binarysearch with a random value and checks if linear or binary is the best in this situation
 * to use to search for specific values. Also creates an array with a given size.
 * </p>
 * @author André Frisk
 * @version 1.0
 * @since  2019-04-16
 */
public class Search {
	private static PrintWriter linear;
	private static PrintWriter binary;
	public static void main(String[] args) throws FileNotFoundException {
		int lowestNumber = 1;
		int highestNumber = 1000;
		int size = 100000;
		
		linear = new PrintWriter(new FileOutputStream("Linear.txt"), true);
		binary = new PrintWriter(new FileOutputStream("Binary.txt"), true);
		
		int[] array = createArray(size, lowestNumber, highestNumber);
		
		int searchValue = (int)((highestNumber-lowestNumber)*Math.random()) + lowestNumber;

		for(int i = 0; i < 10; i++) {
			long linearTimeStart = System.currentTimeMillis();
			linearSearch(array, searchValue);
			long linearTimeStop = System.currentTimeMillis();
			double resultLinear = linearTimeStop - linearTimeStart;
			linear.println(resultLinear + " ");
		}
			Arrays.sort(array);
			for(int j = 0; j < 10; j++) {
			long binaryTimeStart = System.currentTimeMillis();
			binarySearch(array, searchValue);
			long binaryTimeStop = System.currentTimeMillis();
			double resultBinary = binaryTimeStop - binaryTimeStart;
			binary.println(resultBinary + " ");
			}
	}
	/**
	 * 
	 * @param a; array created
	 * @param x; the value to search for
	 * @return; returns the position if found or -1 if false
	 */
	public static int linearSearch(int[] a, int x) {
		for(int i = 0; i < a.length; i++) {
			if(x == a[i]) {
				return i;
			}
		}
		return -1;
		
	}
	/**
	 * 
	 * @param a; array created
	 * @param x; the value to search for
	 * @return; return the position
	 */
	public static int binarySearch(int[] a, int x) {
		int middle;
		int lowest = 0;
		int highest = a.length-1;
		for(int i = 0; i < a.length; i++) {
			middle = (highest + lowest)/2;
			
			if(x<a[i]) {
				highest = lowest + 1;
			}else if(x>a[i]) {
				lowest = highest + 1;
			}else {
				return middle;
			}
		}return -1;
	}
	/**
	 * 
	 * @param size; the given size for the array
	 * @param min; lowest number in array
	 * @param max; highest number in array
	 * @return; returns the array
	 */
	public static int[] createArray(int size, int min, int max) {
		int[] array = new int[size];
		for(int i = min; i <= max; i++) {
			array[i] = array[i+1];
		}
		return array;
	}
}
