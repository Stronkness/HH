/**
 * <hi>Caesar.java</h1>
 * <p>Method for the program Uppgift6</p>
 * @author André Frisk
 * @version 1.0
 * @since  2019-04-02
 */

package upg191;

public class Caesar {
	/**
	 * @param tkn; letter/number from the textfile sent from main to get encrypted
	 * @param key; the key used from main to move the letter/number a given amount higher in the ASCIII table
	 * @return; returns a single charatcher created encrypted to main
	 */
	 public static char encrypt_character(char tkn, int key){
		 int left_1 = 0;
		 int result = (int)(tkn) + key;
		 
		 if(result > 127) {
			 left_1 = result - 127;
			 result = 47 + left_1;
		 }
		 return (char)result;
	 }
	 /**
	  * @param tkn; letter/number from the encrypted textfile sent from main to get decrypted
	  * @param key; the key used from main to move the letter/number a given amount higher in the ASCIII table
	  * @return; returns a single characther decrypted to main
	  */
	 public static char decrypt_character(char tkn, int key){
		 int left_2 = 0;
		 int outcome = (int)(tkn) - key;
		 
		 if(outcome < 47) {
			 left_2 = outcome + left_2;
			 outcome = 127 + left_2;
		 }
		 return (char)outcome;
	 }
}
