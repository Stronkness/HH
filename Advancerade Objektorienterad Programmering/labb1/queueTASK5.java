package labb1;

public class queueTASK5 {
	private int[] queue;
	private int size;
	private int back, front;
	
	public queueTASK5() {
		queue = new int[5];
		front = 0;
		back = -1;
		size = 0;
	}
	/**
	 * @precondition input != null
	 * @postcondition doubleQueue != null && doubleQueue.length > queue.length
	 */
	public void enqueue(int input) {
		assert (Integer)input != null: "Failed. input is null";
		int[] doubleQueue = null;
		if(size == queue.length) {	
			doubleQueue = new int[queue.length*2];
			for(int i = 0; i < size; i++) {
				doubleQueue[i] = queue[front];
				front++;
				if((front) == queue.length) {
					front = 0;
				}
			}
			queue = doubleQueue;
			front = 0;
			back = size-1;
			System.out.println("Doubled up: " + "size is " + size);
			
			if((back + 1) == queue.length) {
				back = 0;
			}else {
				back += 1;
			}
			queue[back] = input;
			size++;
			System.out.println("Added " + input + " : size is " + size);
		}else{
			if((back+1) == queue.length) {
				back = 0;
			}else {
			back += 1;
			}
			queue[back] = input;
			size++;
			System.out.println("Added " + input + " : size is " + size);
		}
		assert doubleQueue != null && doubleQueue.length > queue.length: "Fail. DoubleQueue failed";
	}
	/**
	 * @precondition size != 0
	 * @postcondition erase != null
	 */
	public int dequeue() {
		assert size>0 : "Violated precondition size, queue is empty!";
		
			int erase = queue[front];
			if((front + 1) == queue.length) {
				front = 0;
			}else {
			front += 1;
			}
			size--;
		
		assert (Integer)erase != null: "Cannot erade null";
		return erase;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	
	public static void main(String[] args) {
		queueTASK5 q = new queueTASK5();
		System.out.println(q.isEmpty());
	//	System.out.println(q.dequeue() + " : the size is " + q.size);
		q.enqueue(1);
		q.enqueue(4);
		q.enqueue(3);
		q.enqueue(2);
		System.out.println();
		System.out.println(q.dequeue() + " : the size is " + q.size);
		System.out.println(q.dequeue() + " : the size is " + q.size);
		System.out.println();
		q.enqueue(22);
		q.enqueue(33);
		q.enqueue(44);
		System.out.println(q.isEmpty());
		
		q.enqueue(2222);
		q.enqueue(6);
		q.enqueue(7);
		q.enqueue(66);
		q.enqueue(66666);
		
		System.out.println(q.dequeue() + " : the size is " + q.size);
		q.enqueue(1337);
		
		q.enqueue(0);
		
	/*	System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue()); */
		
		System.out.println();
		System.out.println("For loop");
		for(int i = q.size; i > 0; i--) {
			System.out.println(q.dequeue() + " : the size is" + q.size);
		}
		System.out.println();
		//System.out.println(q.dequeue() + " : the size is" + q.size);
	}
}
