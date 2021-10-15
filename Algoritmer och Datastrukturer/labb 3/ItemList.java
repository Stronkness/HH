import java.util.*;

/**
 * Klassen ItemList är en länkad lista som innehåller noder av Item-objekt.
 * Första noden i listan ( header) innehåller inget objekt.
 */

   public class ItemList{
      private Node header;
      private int counter;
	private Boolean boolean1;
    
	// Constructor
      public ItemList( ) {
         header = new Node( null );
         counter = 0;
      }
    
    /**
    Skapar ett ny Node-objekt med ett nytt Item objekt och lägger den i listan. 
    Ökar värdet av variabel counter;
     */
      /**
       * 
       * @param newItem; adds the item to the list
       */
      public void add(Item newItem) {
    	  Node nynode = new Node(newItem);
          Node temp = header;
          
          
          while(temp.next != null){
             temp=temp.next;
          }
         
          temp.next=nynode;
          counter++;
          
      }
    
    /**
    Skapar ett ny Node objekt med ett nytt Item-objekt och lägger den i listan på plats index,
    Ökar värdet av variabel size;
   */
      /**
       * 
       * @param newItem; adds the item to the list
       * @param index; adds the item to this index
       */
      public void add( Item newItem, int index)
      {
    	   if(index <= counter && index >= 0) {
    			 Node temp = header;
    			 Node obje = new Node(newItem);
    			 for(int i = 0; i < index; i++) {
    				 temp = temp.next;
    			 }
    			 obje.next = temp.next;
    			 temp.next = obje;
    			 counter++;
    			 }
    			   else{
    				   System.out.println("Index not found");
    			   }
      
      }
    
    
    /**
    Returnerar true om det finns ett Item-objekt vars RFIDNR är lika med metodens parametern id 
     */
      /**
       * 
       * @param id; the specific object to find in the list
       * @return; returns boolean
       */
      public boolean find(String id) {
    	  Node realNode = header;
          for(int i = 0; i < counter; i++) {
        	  if(realNode.next.itm.RFIDNR.equals(id)) {
        		  return true;
        	  }
        	  realNode = realNode.next;
          }
         return false;	 
      }
    
   /**    
       Ta bort noden som innehåller Item- objektet med viss id*/
      /**
       * 
       * @param id; the specific object to remove in the list
       * @return; returns the item
       */
	public Item remove(String id) {
    	  Node realNode = header;
          Node temp = null;
          while(realNode.next != null){
             if(realNode.next.itm.RFIDNR.equals(id)){
            	
            	temp = realNode.next;
                realNode.next = realNode.next.next;
                counter--;
                break;
             }
             realNode = realNode.next;
          }
		return temp.itm;
      }
   
    /**
     Räknar och returnerar antalet Item- objekt med ett vis namn oavsett RFIDNR 
     */
   /**
    * 
    * @param name; the name which you want to count in the list
    * @return; return the counter how many objects calculated
    */
      public int countObjects(String name) {
      int cObj = 0;
      Node realNode = header;
      for(int i = 0; i < counter; i++) {
    	  if(name == realNode.next.itm.name) {
    		  cObj++;
    		  realNode = realNode.next;
    	  }
      }
         return cObj;
      }
   
    
    /**
    Skriver ut innehållet i listan
    */
      public void printList() {
    	  Node realNode = header.next;
          while(realNode != null){
             System.out.println(realNode.itm + " ");
             realNode = realNode.next;
          }
      }
    
    /**
     Returnera true om listan är tom annars false
    */
      public boolean isEmpty(){
    	  if(counter == 0) {
    		  return true;
    	  }
         return false;
      }
		
		/**
     Returnera antlet element i listan
    */
      public int size()
      {
         return counter;
      }
      public Item comparator(int x) {
    	 Node temporary = header.next; 
    	  
    	  for(int i = 1; x > i; i++) {
    		  temporary = temporary.next;
    	  }
    	  return temporary.itm;
      }
      /**
       * 
       * @param name; the name you want to find
       * @return; returns the item or a String if the name is not found
       */
    public String findName(String name) {
    	 Node realNode = header;
         for(int i = 0; i < counter; i++) {
       	  if(realNode.next.itm.name.equals(name)) {
       		  return realNode.next.itm.RFIDNR + " " + realNode.next.itm.name;
       	  }
       	  realNode = realNode.next;
         }
        return "Product not in stock";	
    }
    /**
     * 
     * @param i; the RFIDNR used to find
     * @return; returns the item or a String if the name is not found
     */
    public String findRFID(String i) {
    	Node realNode = header;
        for(int j = 0; j < counter; j++) {
      	  if(realNode.next.itm.RFIDNR.equals(i)) {
      		  return realNode.next.itm.RFIDNR + " " + realNode.next.itm.name;
      	  }
      	  realNode = realNode.next;
        }
       return "Product not in stock";	
    }
        public String getItem() {
        	Node realNode = header;
        	String item = "";
        	
        	for(int i = 0; i < counter; i++) {
        		item = item + realNode.next.itm.name + " " + realNode.next.itm.RFIDNR + " ";
        		realNode = realNode.next;
        	}return item;
        }
   }