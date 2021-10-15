  import java.text.DateFormat;
   import java.text.SimpleDateFormat;
   import java.util.Date;
   import java.util.*;


   public class Item implements Comparable <Item>{
      public Date delivered;   // utlånd datum, null när Item inte är utlånad 
      public final String RFIDNR; 
      public String name;
   
      public Item(String thename, String number) {
         name = thename;
         RFIDNR = number;
         delivered=null;
      }
     	
      public void setDeliverDate(Date d) {
         delivered = d;
      }
   
      public Date getDeliverDate() {
         return delivered;
      }
   
      public String getItemNumber() {
         return RFIDNR;
      }
    
	  public String getItemName() {
         return name;
      }
    

      public int compareTo( Item other)
      {     
         return name.compareTo(other.name);
  	}
     
       public boolean equals( Object other)
      {
      // Detta gör du färdig. Vi säger att två objekt är lika om de har samma RFIDNR  
         return false;
      }
 	
		
      public String toString() {
         DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
      if( delivered==null)
		  return RFIDNR + " "+ name+ " "+ "på lager";
		else
         return RFIDNR + " "+ name+ " "+ df.format(delivered);
      }
       	 
   // See a simple program example to understand how Date class works; 
      public static void main (String [] arg)
      {
       
         Calendar calendar = Calendar.getInstance();
      
         Date newDate = calendar.getTime();
      
      // Skapa item- object
         Item itm= new Item ( "Termometer", "1111111111");
         Item itm2= new Item ( "Tigersåg", "1111123411");
         Item itm3= new Item ( "Vinkelslip", "7681111111");
      // Gör item-obj utlånad
         itm.setDeliverDate(newDate);
         itm2.setDeliverDate(newDate);
         itm3.setDeliverDate(newDate);
         
         System.out.println( itm); 
         System.out.println( itm2); 
         System.out.println( itm3); 
      	
      
      }
   
   }


