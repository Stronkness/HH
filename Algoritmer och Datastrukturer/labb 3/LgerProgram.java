import java.util.*;
import java.text.*;
import java.io.*;
/**
 * <hi>LgerProgram.java</h1>
 * <p>
 * Builds up a storage (for me its a toolstorage) with a menu where you can pick what to do yourself
 * for example fill the storage, borrow a product and check which tools are in storage.
 * </p>
 * @author André Frisk
 * @version 1.0
 * @since  2019-04-16
 */
public class LgerProgram
{
static Scanner scan = new Scanner(System.in);
static ItemList inStore =new ItemList();
static ItemList outStore=new ItemList();
static long nr=1111111111;
static Calendar calendar = Calendar.getInstance();
static Date newDate = calendar.getTime();
static PrintWriter printIn;
static PrintWriter printOut;

public static void main(String[] args)
      {
	
       try{
       Scanner files=new Scanner(new File("Verktyg.txt"));
		 while( files.hasNext()) {
			 files.next();
		 } 
		 }
		 
		 catch(IOException ex){System.out.println("Filen finns inte");}
        printMenu();
         int choice = scan.nextInt();
         scan.nextLine(); 

         while (choice != 0)
         {
            dispatch(choice);
            printMenu();
            choice = scan.nextInt();
				scan.nextLine();  
         }
	}
		
		public static String getRFID()
		{
		 return ""+ nr++;
		 
		}
   /**
    * 
    * @param choice; the choice made in the menu
    */
       public static void dispatch(int choice)
      {
         int loc;
      
      
         switch(choice)
         {
            case 0: 
               System.out.println("EXIT");
               System.exit(0);
               break;
         		
            case 1:	  
         try{
            	       Scanner scanWares=new Scanner(new File("Verktyg.txt"));
            	       Scanner scanOutWares = new Scanner(new File("VerktygOut.txt"));
            			 while( scanWares.hasNext()) {
            				   inStore.add(new Item(scanWares.next(), scanWares.next()));	    
            			 } 
            			 while( scanOutWares.hasNext()) {
          				   outStore.add(new Item(scanOutWares.next(), scanOutWares.next()));	    
          			 } 
            			 }
            			 
            			 catch(IOException ex){System.out.println("Filen finns inte");}
					   // Här bygger du upp lagret. Fyller inlager med Item-objekt vars namn läser du från filen.
						// RFIDNR som du behöver till varje Item- kan skapas av metoden ge
						// Utlagret är i början tom

                                 	               			
                  break;

            case 2:
               {
            	   System.out.println("Please enter the product you want to borrow");
            	   Scanner rfidScan = new Scanner(System.in);
            	   String temporary = rfidScan.nextLine();
            	   if(inStore.find(temporary)) {
            		  Item adder;
            		  adder = inStore.remove(temporary);
            		  adder.setDeliverDate(newDate);
            		  outStore.add(adder);
            		  System.out.println(adder);
            		  
            		  try {
						printIn = new PrintWriter("Verktyg.txt");
						printOut = new PrintWriter("VerktygOut.txt");
						
						printIn.print(inStore.getItem());
						printOut.print(outStore.getItem());
						
						printIn.close();
						printOut.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
            		  
            	   }else {
            		   System.out.println("Product not in stock");
            	   }
                  						
                  break;
               }	 
            case 3:
               {
            	   System.out.println("Please enter the product you want to return");
            	   Scanner rfidScan2 = new Scanner(System.in);
            	   String temporary = rfidScan2.nextLine();
            	   int count = 1;
            	   if(outStore.find(temporary)) {
            		  Item adder;
            		  adder = outStore.remove(temporary);
            		  adder.setDeliverDate(null);
            		  System.out.println(adder);
            		  
            		  while(inStore.size() > count) {
            			  
            			  if(adder.compareTo(inStore.comparator(count)) < 0) {
            				  inStore.add(adder, count-1);
            				  break;
            			  }
            			  count++;
            	
            			  }
            		  
            			  System.out.println("Product returned");
            			  
            		  
            	   
            	   try {
						printIn = new PrintWriter("Verktyg.txt");
						printOut = new PrintWriter("VerktygOut.txt");
						
						printIn.print(inStore.getItem());
						printOut.print(outStore.getItem());
						
						printIn.close();
						printOut.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
            		  
            	   }else {
            		   System.out.println("Product not borrowed");
            	   }
                  						
                  break;
               }
            	
            case 4:
				    {System.out.println("Please enter the product you want to find");
	            	   Scanner rfidScan3 = new Scanner(System.in);
				       
	            	   if(rfidScan3.hasNextInt()) {
	            		  System.out.println(inStore.findRFID(rfidScan3.next()));
	            		  
	            	   }   
	            	   else {
	            		   System.out.println(inStore.findName(rfidScan3.next()));
	            		   
	            	   }
				    	
				    	break;
					 }
				case 5:
				    {
				    	outStore.printList();
					    break;
					 }
					 
				case 6:
				    {
				    	inStore.printList();
					    break;
					 }

            
            
            
            default:
               System.out.println("Sorry, invalid choice");
         }
      }
   
    //----------------------------
    // Skriver ut användar meny 
    //----------------------------
       public static void printMenu()
      {
         System.out.println("\n  Welcome");
         System.out.println("   ====");
         System.out.println("0: Exit");
         System.out.println("1: Setup Store");
         System.out.println("2: Borrow out a product");
         System.out.println("3: Bring back borrowed product" );
         System.out.println("4: Search for a product in stock");
		 System.out.println("5: Show borrowed products");
         System.out.println("6: Current products in stock");
      
         System.out.print("\nEnter your choice: ");
      }
		
		
		
	   }
 
/* Exempel hur man läser från fil. 
try{
       Scanner filescan=new Scanner(new File("Verktyg.txt"));
		 while( filescan.hasNext())
		 System.out.println( filescan.next());
		 }
		 
		 catch(IOException ex){System.out.println("Filen finns inte");
*/