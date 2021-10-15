package labb1;
import junit.framework.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class stackTASK4Test {
	int[] go={1,2,3,4,5,6,7,8,9,10};
	stackTASK4 andre = new stackTASK4();
	@Test
	public void pushTest() {
		andre.push(go[0]);
		assertEquals(andre.peek()==go[0],true,"recived"+ " "+andre.peek());
		andre.push(go[1]);
		andre.push(go[2]);
		andre.push(go[3]);
		andre.push(go[4]);
		andre.push(go[5]);
		assertFalse(andre.peek()==go[5],"recived "+andre.peek()+"and wanted "+go[4]);
	}
	@Test
	public void popTest() {
		int a = andre.pop();
		assertEquals(a == -1, true, "Empty stack, expected -1");
		andre.push(go[0]);
		int b = andre.pop();
		assertEquals(b == go[0], true, "Expected " + go[0]);
		andre.push(go[1]);
		andre.push(go[2]);
		andre.push(go[3]);
		int c = andre.pop(); 
		int d = andre.pop(); 
		int e = andre.pop();
		assertEquals(c == go[3], true, "Expected " + go[3]);
		assertEquals(d == go[2], true, "Expected " + go[2]);
		assertEquals(e == go[1], true, "Expected " + go[1]);
	}
	@Test
	public void pushMoreTest() {
		andre.pushMore(1,go);
		assertTrue(andre.peek()==go[0],"Expected "+ go[0]);
		andre.pushMore(3, go);
		assertTrue(andre.peek()==go[2],"Expected "+ go[2]);
		andre.pushMore(3,go);
		assertFalse(andre.peek()==go[3],"Expected "+ go[3]);
		assertFalse(andre.peek()==go[1],"Expected "+ go[1]);
	}
	@Test
	public void popMoreTest() {
		assertNull(andre.popMore(3), "Expected nothing, stack is empty");
		andre.pushMore(3, go);
		List<Integer> list = new ArrayList<Integer>();
		list = andre.popMore(2);
		assertTrue(list.size() == 2, "Expected 2");
		assertTrue(list.get(0) == 2, "Expected 2");
		assertTrue(list.get(1) == 3, "Expected 3");
	}
}
