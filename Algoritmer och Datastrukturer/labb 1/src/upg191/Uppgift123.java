/**
 * <hi>Uppgift123.java</h1>
 * <p>
 * The programs function is to create passwords, the user decides how many passwords will create
 * and how many signs the passwords will contain. The program also prints out if the conditions are met
 * as true or false and prints out how many passwords of the chosen ammount are correct.
 * </p>
 * @author André Frisk
 * @version 1.0
 * @since  2019-04-02
 */

package upg191;
import upg191.Password;

public class Uppgift123 {
	public static void main(String[] args) {
		int signs = 8; // Amount of signs in password, choose yourself
		int forLoops = 1000; // Amount of passwords created, choose yourself
		char[] completePass = new char[signs];
		String value = ""; 
	    String conditions = "(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z ]).{8,12}";
		int truefalse = 0;
		for(int i = 0; i < forLoops; i++){
				completePass = Password.generatePassword(signs); // Take the array from generatePassword into the main
				value = String.copyValueOf(completePass); // Passwords copy into variable value
				if(value.matches(conditions)) { // Calculates the ammount of 'true'
					truefalse = truefalse + 1;
				}
				for(int j = 0; j < signs; j++) {
					System.out.print(completePass[j]);
			}
			System.out.print(" " + value.matches(conditions)); 
			// Use value to check if the conditions are met, types true or false
			System.out.println();
		}
		System.out.println("Total ammount of True: " + truefalse);
		System.out.println("Total ammount of False: " + (forLoops - truefalse));
	}
}
