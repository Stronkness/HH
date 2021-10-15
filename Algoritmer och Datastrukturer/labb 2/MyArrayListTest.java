import static org.junit.Assert.*; 
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

public class MyArrayListTest {

	MyArrayList<Integer> list_1;
	MyArrayList<Integer> list_2;
	MyArrayList<Integer> list_3;	
			
   /** Fixture initialization (common initialization
    *  for all tests). **/
	 
   @Before 
   public void setUp() {
		//  new list for every test case
		list_1 = new MyArrayList<Integer>();
		list_2 = new MyArrayList<Integer>();
		list_3 = new MyArrayList<Integer>();
   }
	
	@Test
	public void addTest()
	{
		for(int i = 0; i< 1000; i++)
		{
			list_1.add(i);
		}
		assertEquals("Add 1000 obj test with size()", 1000, list_1.size());
		
		Integer test_Integer = new Integer(-1);
		list_1.add(test_Integer);
		assertSame("Use add() and get() to check if object is placed at right position",
					  test_Integer, list_1.get(1000));
		
		assertEquals("Check that null object is not added, returns false", false, list_1.add(null));
		assertEquals("Check that size hasn't change when adding null", 1001, list_1.size());
		
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void getTestExceptionIndexToHigh()
	{
		list_1.get(0);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void getTestExceptionNegativeIndex()
	{
		list_1.get(-1);
	}

	@Test
	public void isEmptyTest()
	{
		assertEquals("Test if new list is empty", true, list_1.isEmpty());
		for(int i = 0; i < 1000; i++)
		{
			list_1.add(i);
		}
		assertEquals("Test if list with objects isn't empty", false, list_1.isEmpty());
		for (int i = 0; i < 1000; i++)
		{
			list_1.remove(i);
		}
		assertEquals("Remove all objects check that list is empty", true, list_1.isEmpty());
	}
	
	@Test
	public void removeTest()
	{
		assertEquals("Try to remove non existing object check if remove() returns false", false, list_1.remove(1));
		list_1.add(1);
		list_1.add(2);
		list_1.add(1);
		list_1.remove(1);
		assertEquals("Check that first occurence of object is removed", 1, list_1.indexOf(1));
		assertEquals("Check that size has decreces", 2, list_1.size());
		list_1.remove(2);
		assertEquals("Check that object is removed against indexOf()", -1, list_1.indexOf(2));
	}
	
	@Test
	public void containsTest()
	{
		list_1.add(2);
		assertEquals("Test contains() with existing object", true, list_1.contains(2));
		assertEquals("Test contains() with non existing object", false, list_1.contains(1));
		assertEquals("Test contains() with null object", false, list_1.contains(null));		
	}

	@Test
	public void indexOfTest()
	{
		list_1.add(1);
		list_1.add(1);
		assertEquals("Test that indexOf() object returns first index", 0, list_1.indexOf(1));
	}

	@Test
	public void sizeTest()
	{
		assertEquals("Check that new list has size 0", 0, list_1.size());
	}
	
	@Test
	public void toArrayAndIteratoTest()
	{
		Object [] arr1 = null;
		assertArrayEquals("Check that empty list returns null array", arr1, list_1.toArray());
		Object[] arr2 = new Object[10];
		for(int i = 0; i < 10; i++)
		{
			arr2[i] = i;
			list_1.add(i);
		}
		assertArrayEquals("Check that array returned is right", arr2, list_1.toArray());
		
		Iterator<Integer> itr = list_1.iterator();
		for(int i = 0; i < 9; i++)
		{
			itr.next();
		}
		// testing iterator
		assertEquals("Testing iterator", new Integer(9), itr.next());
		assertEquals("Testing that the iterator has reached the end", false, itr.hasNext());
	}
}