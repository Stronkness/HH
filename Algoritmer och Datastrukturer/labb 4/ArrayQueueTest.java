import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class ArrayQueueTest {

	ArrayQueue<String> testQueue;
	ArrayQueue<String> reverseQueue;

	@Before
	public void setUp() throws Exception {
		testQueue = new ArrayQueue<String>();
		reverseQueue = new ArrayQueue<String>();
	}
	
	@Test
	public void testPrint() {
		testQueue.enqueue("Andre");
		testQueue.enqueue("Rolle");
		testQueue.enqueue("Fredrik");
		testQueue.enqueue("Krille");
		
		assertEquals("testQueue prints out", "Andre Rolle Fredrik Krille ", testQueue.printQueue());
	}
	@Test
	public void testReverse() {
		testQueue.enqueue("Andre");
		testQueue.enqueue("Rolle");
		testQueue.enqueue("Fredrik");
		testQueue.enqueue("Krille");
		
		reverseQueue = testQueue.reverse();
		
		assertEquals("reverseQueue removes Krille", "Krille", reverseQueue.dequeue());
		assertEquals("reverseQueue removes Fredrik", "Fredrik", reverseQueue.dequeue());
		assertEquals("reverseQueue removes Rolle", "Rolle", reverseQueue.dequeue());
		assertEquals("reverseQueue removes Andre", "Andre", reverseQueue.dequeue());
	}
}
