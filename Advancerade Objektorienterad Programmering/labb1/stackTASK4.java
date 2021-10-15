package labb1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class stackTASK4 {

private int[] stack;
private int ToS;

public stackTASK4() {
	stack = new int[5];
	ToS = -1;
}
public int peek() {
	return stack[ToS] ;
}


public void push(int input) {
	if(ToS < stack.length-1) {
		ToS++;
		stack[ToS] = input;
	}else {
		System.out.println("Stack is full!");
	}
}

public int pop() {
	int erase = -1;
	if(ToS >= 0) {
		erase = stack[ToS];
		ToS--;
		return erase;
	}else {
		System.out.println("Stack already empty!");
		return erase;
	}
}

public void pushMore(int n, int[] insert) {
	if((ToS + n) < stack.length-1) {
		for(int i = 0; i < n; i++) {
			if(ToS < stack.length-1) {
				ToS++;
				stack[ToS] = insert[i];
			}
		}
	}else {
		System.out.println("Not enough space in the Stack!");
	}
}

public List<Integer> popMore(int n) {
	List<Integer> list = new ArrayList<>();
	if((ToS - n) >= 0) {
		for(int i = 0; i < n; i++) {
			list.add(stack[ToS]);
			ToS--;	
		}
		Collections.reverse(list);
		return list;
	}else {
		System.out.println("Cannot erase that many objects");
		return null;
	}
}
}
