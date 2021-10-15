/**
 * <hi>Password.java</h1>
 * <p>Method Password for the Uppgift123 program</p>
 * @author André Frisk
 * @version 1.0
 * @since  2019-04-02
 */

package upg191;

public class Password {
	/**
	 * @param signAmmount; takes the ammount of signs from main to create passwords with given signs
	 * @return; returns every password to main created
	 */
	public static char[] generatePassword(int signAmmount){
		char[] pass = new char[signAmmount];
		int result = 0;
		int unicodeN;
		for(int u = 0; u < signAmmount; u++) {
			unicodeN = (int)(Math.random()*3); // Unicode ASCIII
				if(unicodeN == 0) { // Numbers in Unicode
					result = (int)(Math.random()*10) + 48;
				}
				else if(unicodeN == 1) { // Capital letters in Unicode
					result = (int)(Math.random()*26) + 65;
				}
				else if(unicodeN == 2) { // Lower case letters in Unicode
					result = (int)(Math.random()*26) + 97;
				}
				pass[u] = (char)result;
		}
		return pass;
	      
	}
	
}
