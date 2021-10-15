import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ArrayQueueTestTest
{

    ArrayQueue<String> soup;
    ArrayQueue<String> dopes;

    /**
     * Fixture initialization (common initialization for all tests).
     **/

    @Before
    public void setUp()
    {
        // new list for every test case
        soup= new ArrayQueue();
        dopes = new ArrayQueue();
    }

    @Test
    public void printTest()
    {
        soup.enqueue("A");
        soup.enqueue("B");
        soup.enqueue("C");
        soup.enqueue("D");


        assertEquals("printqueue removes abcd", "ABCD", soup.printQueue());
    }
     @Test
    public void reverseTest()
    {
        soup.enqueue("A");
        soup.enqueue("B");
        soup.enqueue("C");
        soup.enqueue("D");

        dopes = soup.reverse();

        assertEquals("reversequeue removes D", "D", dopes.dequeue());
        assertEquals("reversequeue removes C", "C", dopes.dequeue());
        assertEquals("reversequeue removes B", "B", dopes.dequeue());
        assertEquals("reversequeue removes A", "A", dopes.dequeue());
    }
}