/**
 * <hi>Uppgift1.java</h1>
 * <p>
 * Tries out the recursive method for the first time with simple methods
 * </p>
 * @author André Frisk
 * @version 1.0
 * @since  2019-05-07
 */
public class Uppgift1 {
	public static void main (String[] args) {
		
		int[] test = {1,2,3,4,5,6,7,8,9,10,11,12,13,15}; // Choose own numbers in array
		int num = 10;									 // Choose numbers to print out (num+1)
		print(test, num);
		
		System.out.println(palindrome("olasalo", 0)); // Write a word to try
		
		System.out.println(baseEx(3, 5));
	}
	/**
	 * Prints the ammount of numbers in array according to the param n
	 * @param a; array
	 * @param n; ammount if numbers that needs to be printed
	 */
	public static void print(int[]a,int n) {
		if(n>=0) {
			System.out.println(a[n]);
			print(a,n-1);
		}
	}
	/**
	 * Checks if the word is a palindrome
	 * @param word; the word to check
	 * @param n; the start position of the letter in the word
	 * @return; boolean and recursion where n adds 1 every time both letters are identical
	 */
	public static boolean palindrome(String word, int n) {
		if(word.length() == n) {
			return true;
		}
		if(word.charAt(n) == word.charAt(word.length()-n-1)) {
			return palindrome(word, n+1);
		}
		return false;
	}
	/**
	 * Basiclly a program that does MathPow but with a recursive method
	 * @param base; the base to mulitply
	 * @param ex; ammount of times to multiply
	 * @return; calls itself to do a recursive, base multiplies with ex and after that the method takes ex-1 every time
	 */
	public static int baseEx(int base, int ex) {
		if(ex == 0) {
			return 1;
		}else {
			return base * baseEx(base, ex-1);
		}
	}
}
