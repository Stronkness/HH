//  Du får (om du vill) ändra strukturen i klassen Huffman genom att lägga till metoder eller 
// variabler. Observera att variablerna och metoderna i klassen är static.

/**
 * <hi>HuffmanKomprimering.java</h1>
 * <p>
 * Takes a file and tries to "komprimera" so that the file is the same but the ammount of bytes is lowered
 * by atleast half. In other word the storage memory decreases
 * </p>
 * @author André Frisk
 * @version 1.0
 * @since  2019-05-07
 */
import java.util.*;
import java.io.*;

class HuffmanKomprimering{
    
    private static ArrayList<HuffmanTree> theForest;
    private static int[]     freqs = new int[256];
    private static String[] codes = new String[256];
    private static int       setSize;
    private static String 	namefile;
/**
 * Adds every letter to array and counts how many times every letter occurs in freqs
 * @param filename; the file that needs to be read
 */
    private  static void readFile(String filename){
	try {
		namefile = filename;
		@SuppressWarnings("resource")
		BufferedReader scan = new BufferedReader(new FileReader(filename));
		int num = scan.read();
		while(num != -1) {
			if(codes[num] != null) {
				if(codes[num].equals(Integer.toString(num))) {
					freqs[num] = freqs[num] + 1;
				}
			}
			else {
				codes[num] = Integer.toString(num);
				freqs[num] = 1;
			}num = scan.read();
		}
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	 catch (IOException e) {
			e.printStackTrace();
		}
   }	
    // Creates a HuffmanTree with ascii, the letters is the node and the requency is the weight.
    // Adds the tree to theForest Arraylist
    private  static void makeTree(){
    	HuffmanTree ek = null;
    	HuffmanTree bok = null;
    	for(int i = 0; i < codes.length; i++) {
    		if(codes[i] != null){
    		ek = new HuffmanTree(Integer.valueOf(codes[i]), freqs[i]);
    		theForest.add(ek);
    		}
    	}
    		while(theForest.size() != 1) {
    			theForest.sort(null);
    			ek = theForest.remove(1);
    			bok = theForest.remove(0);
    			theForest.add(new HuffmanTree(ek, bok));
    		}
    	}
    
    private  static void makeCods(){
    	HuffmanTree gran = theForest.get(0);
    	gran.codes(codes);
	// använder metoden codes() fšr din slutliga HT. Metoden finns definierad i klassen HuffmanTree. Undersšk den. 
	
    }
    
    
    //Prints out the result of how many times a letter is occured and also in binary
    private  static void showResults(){
    	for(int i = 0; i < codes.length; i++) {
    		if(codes[i] != null) {
    		char treebranch = (char)i;
    		System.out.println(treebranch + " " + codes[i] + " " + freqs[i]);
    		}
    	}
    
    }
   //Copies the file and insert the content into a new file
   private static void makeFile(){
	   try {
		BufferedReader scan = new BufferedReader(new FileReader(namefile));
		FileOutputStream alm = new FileOutputStream("NewText.txt");
		DataOutputStream tall = new DataOutputStream(alm);
		
		String result = "";
		int num = scan.read();
		while(num != -1) {
			result += codes[num];
			num = scan.read();
			}
		int counter = 0;
		String text = "";
		for(int i = 0; i < result.length(); i++) {
			counter++;
			text = text + result.charAt(i);
			if(counter == 8) {
				counter = 0;
				tall.write(stringTobyte(text));
				text = "";
			}
		}
		tall.close();
		
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
      // anvŠnd stringTobyte fšr att bygga den komprimerade filen.
 catch (IOException e) {
		e.printStackTrace();
	}
   }
    
   
// metoden tar som argument en strŠng (11000111) och konvertera den till byte
// anvŠnd metoden fšr att konvertera dina koder till byte som skall sedan sparas till filen. 
   
public static byte stringTobyte( String s)
	 {
	   byte b=0;
		if(s.length()!=8)
		{
		  throw new RuntimeException ("The string representation of the byte in not of proper length!");
		} 
		  for(int i=0;i<8;i++){
		   if(s.charAt(i)=='1')
			  b |= (1<<(7-i));
		
		}
		 return b;
		}	 
 
   
  public static void main(String [] a){
	setSize = 256; // så många olika tecken.
	freqs  = new int[setSize];
	theForest = new ArrayList(); 
	Scanner scan=new Scanner( System.in);
	System.out.println("Ange filens namn");
	String fileName=scan.next();
	readFile(fileName);
	makeTree();       
	makeCods();
	showResults();
	makeFile();
    }
}