
   import java.util.*;

/**
 * Klassen SimpleLinkedList �r en l�nkad lista som inneh�ller noder av objekt.
 * F�rsta noden i listan inneh�ller inget objekt.
 */

   /**
    * <hi>SimpleLinkedList.java</h1>
    * <p>
    * Create methods for LinkedList so that it can be used for other programs. Also this program is used
    * to get familiar with nodes and linkedlists.
    * </p>
    * @author Andr� Frisk
    * @version 1.0
    * @since  2019-04-16
    */
   public class SimpleLinkedList{
      private ListNode header;
      private int counter;
    
      public SimpleLinkedList( ) {
         header = new ListNode( null );
         counter = 0;
      }
    
    /**
    Skapar en ny ListNode  med ett nytt objekt och l�gger den i listan
     */
      
      /**
       * 
       * @param theobj; the "name" you want to insert to the list
       */
      public void insert (String theobj)
      {
         ListNode nynode = new ListNode(theobj);
       
         ListNode temp = header;
         while(temp.next != null){
            temp=temp.next;
         }
        
         temp.next=nynode;
         counter++;
      }
   
   
   /*		 
   Ta bort noden som inneh�ller respektive objekt
     */
      /**
       * 
       * @param theobj; removes the "name" from the list
       */
      public void remove(String theobj) {
         ListNode node = header;
        
         while(node.next != null){
            if(node.next.element.equals(theobj)){
               node.next = node.next.next;
               counter--;
               break;
            }
            node = node.next;
         }
      }
    
    /**
    Skriver ut inneh�llet i listan
     */
      public void print() {
         ListNode node = header.next;
         while(node != null){
            System.out.println(node.element);
            node = node.next;
         }
      }   
   /**
    * 
    * @param theobj; inserts a "name" to the header adress, becomes the new header adress
    */
      public void insert2(String theobj ) {
         ListNode nynode = new ListNode(theobj, header.next);
         header.next = nynode;
         counter++;
      }
    
   // Detta skall du g�ra 
	// l�ger objekt p� p� plats index. Index f�r inte vara negativ eller st�rre �n listans storlek
      /**
       * 
       * @param obj; the "name" to insert in the list
       * @param index; position in the list that tje obj will be inserted to
       */
   public void insert(String obj, int index)
	{
	   if(index < counter && index >= 0) {
	 ListNode temp = header.next;
	 ListNode obje = new ListNode(obj);
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
   
	// Returnerar och tar bort ett objkt fr�n lista
   /**
    * 
    * @param index; removes the object on the index
    * @return; if the index does not exist or if the name is deleted, the return null is there only so there is no error in the code
    */
   public String remove( int index)
  {
	   if(counter<= index+1) {
	  return "Index does not exist";
	  }
	   ListNode temp = header.next;
	   for(int i = 0; i <= index; i++){
		   if(index == i) {
			   temp.next = temp.next.next;
			   counter--;
			   return "Name deleted";
		   }
		   temp = temp.next;
	   }
	   return null;
  }
   
//returnerar storleken p� listan. L�gg till i klasssen  en variabel f�r detta
	 // uppdatera variabeln n�r det �r fallet
	 
  public int size()
  {
       return counter;
  } 
  
  public String toString()
  {
	  String names = "";
	  ListNode nodename = header.next;
      	
	  while(nodename != null){
         names += nodename.element + " ";
         nodename = nodename.next;
      }
      return names;
  } 
    
      public static void main ( String [] arg)
      {
         // testa metoderna fr�n klassen SimpleLinkedList
			SimpleLinkedList klassLista=new SimpleLinkedList();
			klassLista.insert("Olle");
			klassLista.insert2("Nina");
			klassLista.insert("Fredrik");
			klassLista.print();
			System.out.println(" ");
         
			klassLista.insert("Andr�", 0);
			klassLista.print();
			System.out.println(" ");
			
			klassLista.remove("Nina");
			klassLista.print();
			System.out.println(" ");
			
			System.out.println(klassLista.remove(4));
			klassLista.print();
			System.out.println(" ");
			
			System.out.println(klassLista.size());
			System.out.println(klassLista.toString());
      }
   }
	 
	 