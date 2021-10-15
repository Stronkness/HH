import java.util.*;
/**
 * <hi>RandomQueue.java</h1>
 * <p>
 * Creates a new program with specific functions (methods) connected with LinkedList
 * </p>
 * @author André Frisk
 * @version 1.0
 * @since  2019-04-29
 */
public class RandomQueue {
	private static LinkedList<Object> Link;
	private int realSize;
	private Object front;
	private Object back;
	
	// Constructor
	public RandomQueue() {
		Link = new LinkedList<Object>();
		realSize = 0;
	}
	/**
	 * Checks if queue is empty
	 * @return boolean
	 */
	public boolean isEmpty() {
		if(realSize==0) {
			return true;
		}else {
			return false;
		}
	}
	/*
	 * Adds the object to queue
	 */
	public void enqueue(Object o) {
		Link.addFirst(o);
		back = Link.getFirst();
		realSize++;
	}
	/*
	 * Removes a random object in queue
	 * @return the object removed
	 */
	public Object dequeue() {
		realSize--;
		Object obj = Link.remove((int)(Math.random()*realSize));
		if(realSize != 0) {
		front = Link.getLast();
		}	
		return obj;
	}
	public static void main(String[] args) {
		RandomQueue RQ = new RandomQueue();
		
		System.out.println(RQ.isEmpty());
		
		RQ.enqueue("Wisp");
		RQ.enqueue(7);
		RQ.enqueue(3.14);
		RQ.enqueue("Krille");
		RQ.enqueue("Jordgubb");
		
		System.out.println(RQ.isEmpty());
		
		System.out.println(RQ.dequeue());
		System.out.println(RQ.dequeue());
		System.out.println(RQ.dequeue());
		System.out.println(RQ.dequeue());
		System.out.println(RQ.dequeue());
		}
}
