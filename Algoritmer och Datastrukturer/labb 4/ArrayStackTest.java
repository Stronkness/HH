import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ArrayStackTest
{

	ArrayStack<String> testStack;
	ArrayStack<String> copyStack;

	/**
	 * Fixture initialization (common initialization for all tests).
	 **/

	@Before
	public void setUp()
	{
		// new list for every test case
		testStack= new ArrayStack<String>();
		copyStack = new ArrayStack<String>();
	}

	@Test
	public void copyTest()
	{
		testStack.push("Andre");
		testStack.push("Fredrik");
		testStack.push("Rolle");
		testStack.push("Krille");
		
		copyStack = testStack.copy();
			
		assertEquals("copyStack removes Krille", "Krille", copyStack.pop());	
		assertEquals("copyStack removes Rolle", "Rolle", copyStack.pop());	
		assertEquals("copyStack removes Fredrik", "Fredrik", copyStack.pop());	
		assertEquals("copyStack removes André", "Andre", copyStack.pop());	
	}
}