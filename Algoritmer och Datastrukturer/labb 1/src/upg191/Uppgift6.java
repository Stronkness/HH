/**
 * <hi>Uppgift6.java</h1>
 * <p>
 * This program will read with the help of a scanner a textfile you've created yourself with a content of
 * only letters and numbers. The program will then scan every letter and separate them. A array is created
 * with a number of keys which will with every key move the letter/number that amount of steps higher in
 * ASCIII code. The user decides these keys. Two files are created in the process, a encrypt file and a 
 * decrypt file. The encrypt file contain the crypt of the textfile created. The decrypt decrypts the encrypt
 * file and turns the content back to its normal state (same as the textfile content given in the beginning).
 * </p>
 * @author André Frisk
 * @version 1.0
 * @since  2019-04-02
 */

package upg191;
import java.util.*;
import java.io.*;

public class Uppgift6 {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException{
		
	    PrintWriter filout_1=null;
	    PrintWriter filout_2=null;
		int x = 0;
		String text = "";
		File file = new File("Uppgift6.txt");
		Scanner scan = new Scanner(file);
		int[] key = {3,7,2,10,4,1}; // Choose the ammount of keys and which keys to use
		char[] crypttext = null;
		filout_1 =new PrintWriter(new BufferedWriter(new FileWriter("Encrypt.txt", true))); // Creates the encrypt file
		filout_2 =new PrintWriter(new BufferedWriter(new FileWriter("Decrypt.txt", true))); // Created the decrypt file
		
		while(scan.hasNext()) {
			text = scan.next();
			crypttext = text.toCharArray();
			for(int i = 0; i < crypttext.length; i++) {
			if(x == key.length) {
				x = 0;
			}
			if(crypttext[i] == '.') { // Inserts a . if it exists in the file
				filout_1.write("."); 
		        filout_2.write("."); 
			}
			else {
				char charreturn_1 = Caesar.encrypt_character(crypttext[i], key[x]); // Insert the char from encrypt
				char charreturn_2 = Caesar.decrypt_character(charreturn_1, key[x]); // Insert the char from decrypt
				
				filout_1.write(charreturn_1); 
		        filout_2.write(charreturn_2);  

			}
			x++;
		}
		filout_1.write(" "); // Creates blank which the program skips otherwise
        filout_2.write(" "); 
		
	}
		filout_1.close();
	filout_2.close();
		}
}
