  import java.text.DateFormat;
   import java.text.SimpleDateFormat;
   import java.util.Date;
   import java.util.*;


   public class Item implements Comparable <Item>{
      public Date delivered;   // utl�nd datum, null n�r Item inte �r utl�nad 
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
      // Detta g�r du f�rdig. Vi s�ger att tv� objekt �r lika om de har samma RFIDNR  
         return false;
      }
 	
		
      public String toString() {
         DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
      if( delivered==null)
		  return RFIDNR + " "+ name+ " "+ "p� lager";
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
         Item itm2= new Item ( "Tigers�g", "1111123411");
         Item itm3= new Item ( "Vinkelslip", "7681111111");
      // G�r item-obj utl�nad
         itm.setDeliverDate(newDate);
         itm2.setDeliverDate(newDate);
         itm3.setDeliverDate(newDate);
         
         System.out.println( itm); 
         System.out.println( itm2); 
         System.out.println( itm3); 
      	
      
      }
   
   }


