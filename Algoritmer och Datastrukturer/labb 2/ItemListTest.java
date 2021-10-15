import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ItemListTest
{

	ItemList listname1;
	ItemList listname2;
	ItemList listname3;

	/**
	 * Fixture initialization (common initialization for all tests).
	 **/

	@Before
	public void setUp()
	{
		// new list for every test case
		listname1 = new ItemList();
		listname2 = new ItemList();
		listname3 = new ItemList();
	}

	@Test
	public void addTest()
	{
		Item t1 = new Item("andre1","123456781");
		Item t2 = new Item("andre2","123456782");
		Item t3 = new Item("andre3","123456783");
		Item t4 = new Item("andre4","123456784");
		Item t5 = new Item("andre5","123456785");
			listname1.add(t1);
			listname1.add(t2);
			listname1.add(t3);
			listname1.add(t4);
			listname1.add(t5);
			
		
		assertEquals("Add 1000 obj test with size()", 5, listname1.size());
		
		
	}

	
	@Test
	public void isEmptyTest()
	{
		assertEquals("Test if new list is empty", true, listname1.isEmpty());
		Item t1 = new Item("andre", "1234567890");
		Item t2 = new Item("fredrik","9987654321");
		Item t3 = new Item("rolle","1230984756");
		Item t4 = new Item("krille","3983246517");
		Item t5 = new Item("philip","1695230987");
			listname1.add(t1);
			listname1.add(t2);
			listname1.add(t3);
			listname1.add(t4);
			listname1.add(t5);
		assertEquals("Test if list with objects isn't empty", false, listname1.isEmpty());
		
		
			listname1.remove("1234567890");
			listname1.remove("9987654321");
			listname1.remove("1230984756");
			listname1.remove("3983246517");
			listname1.remove("1695230987");
		
		assertEquals("Remove all objects check that list is empty", true, listname1.isEmpty());
	}

	@Test
	public void removeTest()
	{
		
		Item t1 = new Item("andre", "1234567890");
		Item t2 = new Item("fredrik","9987654321");
		Item t3 = new Item("rolle","1230984756");
		Item t4 = new Item("krille","3983246517");
		Item t5 = new Item("philip","1695230987");
			listname1.add(t1);
			listname1.add(t2);
			listname1.add(t3);
			listname1.add(t4);
			listname1.add(t5);
		listname1.remove("1234567890");
		assertEquals("Check that first occurence of object is removed", false, listname1.find("1234567890"));
		assertEquals("Check that size has decreces", 4, listname1.size());
		
	}

	@Test
	public void countObjectsTest()
	{	Item test = new Item("hani","1232795714");
		listname1.add(test);
		assertEquals("Test contains() with existing object", 1, listname1.countObjects("hani"));
		assertEquals("Test contains() with non existing object", 0, listname1.countObjects("sam"));
		assertEquals("Test contains() with null object", 0, listname1.countObjects(null)); 
	}

	@Test
	public void sizeTest()
	{
		assertEquals("Check that new list has size 0", 0, listname1.size());
	}
	@Test
	public void FindTest()
	{
		assertEquals("Check that new list has niklas", false, listname1.find("niklas"));
	}

	
}