package labb1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.lang.*;

public class stackTASK6 {

private Message[] stack;
private int ToS;

public stackTASK6() {
	stack = new Message[5];
	ToS = -1;
}
/**
 * stack.length = 0
 * stack.length > ToS >= -1
 */



/**
 * @precondition sizeMessage() >= 0
 */
public Message peek() {
	assert sizeMessage() >= 0: "Failed. Nothing in the stack";
	return stack[ToS] ;
}
/**
 *@precondition input != null
 */
public void push(Message input) {
	assert (ToS+1) < stack.length-1: "Failed. Stack is full";
	
		ToS++;
		stack[ToS] = input;
}

/**
 * @precondition sizeMessage() != 0
 * @postcondition stack[ToS] != null
 */
public Message pop() {
	assert sizeMessage() >= 0: "Failed. Stack is empty";
	
	Message erase = null;
	erase = stack[ToS];
	ToS--;
	
	assert stack[ToS] != null: "Failed. Non existent";
	return erase;
}

/**
 * @precondition insert.length > 0
 *
 */
public void pushMore(int n, Message[] insert) {
	assert n >0:"n ";
	assert insert.length > 0: "Failed. Nothing to add";
	assert ((ToS+n) < stack.length-1): "Failed. Not enough space";

		for(int i = 0; i < n; i++) {
			if(ToS < stack.length-1) {
				ToS++;
				stack[ToS] = insert[i];
			}
		}
}

/**
 * @precondition (sizeMessage() - n) >= 0 
 * @postcondition list.size() > 0
 */
public List<Message> popMore(int n) {
	assert n>0: "Failed. Can't pop negative amount";
	assert (ToS-n) > 0: "Failed. Can't pop more than there already is";
	
	List<Message> list = new ArrayList<>();
	
		for(int i = 0; i < n; i++) {
			list.add(stack[ToS]);
			ToS--;	
		}
		Collections.reverse(list);
		assert list.size()>0: "";
		return list;
}

/** 
 * @postcondition sizeMessage() > 0
 */
public int sizeMessage() {
	assert (ToS+1) >= 0: "Failed. There's a hole in the Stack";
	return ToS + 1;
}
}
