/**
 * <hi>Uppgift5.java</h1>
 * <p>
 * This program will scan the content of a file you've created and inserted in the code. The program
 * will then separate every word by letters and calculate the total ammount of that characther, both numbers
 * and capital/lowercase letters. A chart is created and writes out which letter/number occured and
 * writes out the total number of that characther and also how many % of the text that contains this characther.
 * </p>
 * @author André Frisk
 * @version 1.0
 * @since  2019-04-02
 */

package upg191;
import java.util.*;
import java.io.*;

public class Uppgift5 {
	public static void main(String[] args){
		
		File txt = new File("Uppgift5.txt"); // Choose your file
		int[] letters = new int[127]; 
		int textcounter = 0;
		
		try {
			Scanner eBook = new Scanner(txt);
			
			while(eBook.hasNext()) {
				String wordtxt = eBook.next();
					char[] txtarray = wordtxt.toCharArray(); 
					// Splits the word into single letters (charatchers) and puts them into an array
					
					for(int  i = 0; i<txtarray.length; i++) {
						int array = (int)txtarray[i]; 
						// Creates a variable with UNICODE
						letters[array-1] = letters[array-1] + 1; 
						// Adds a 1 to that arrayindex that the UNICODE characther is on
						textcounter ++; 
						// Used to calculate the percentage (total charatchers in the text)
					}
				for(int i = 0; i<letters.length; i++) {
					if(letters[i] != 0) {
						System.out.println(((char)(i+1)) + ": Total " + letters[i] + " : " + ((float)letters[i]/textcounter)*100 + "%");
					}
				}
			}
			eBook.close();
		}
		catch(FileNotFoundException error) { 
			// If the file cannot be found
			error.printStackTrace();
		}
	}
}
