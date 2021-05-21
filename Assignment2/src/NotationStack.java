/**
 * This class implements a stack of objects by using an array-based 
 * implementation where the top of the stack is the last entry in the array.
 * @author hudson
 */

import java.util.ArrayList;

public class NotationStack<T> implements StackInterface<T> {
	
	private T[] stack;
	private int topIndex;
	private boolean integrityOK;
	private static final int DEFAULT_CAPACITY = 15;
	
	/**
	 * This no-arg constructor calls the parameterized constructor and 
	 * initializes the size of the stack to the default capacity.
	 */
	
	public NotationStack() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * This constructor accepts an integer argument as a parameter
	 * to initialize the size of the stack. The topIndex field is set
	 * to -1 so that the first entry pushed to the stack has index 0.
	 * @param initialCapacity The initial capacity of the stack array.
	 */
	
	public NotationStack(int initialCapacity) {
		integrityOK = false;
		
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[initialCapacity];
		stack = temp;
		topIndex = -1;
		integrityOK = true;
	}
	
	/**
	 * This method returns true is the stack is empty and false otherwise.
	 * @return True if the stack is empty and false otherwise.
	 */

	@Override
	public boolean isEmpty() {
		
		return topIndex < 0;
	}
	
	/**
	 * This method returns true if the stack is full and false otherwise.
	 * @return True if the stack is full and false otherwise.
	 */

	@Override
	public boolean isFull() {
		
		return topIndex == stack.length - 1;
	}
	
	/**
	 * This method pops, or removes, the top entry of the stack or the
	 * last entry added to the array.
	 * @return The entry to be removed.
	 * @throws StackUnderflowException If the stack is empty.
	 */

	@Override
	public T pop() throws StackUnderflowException {
		
		if(isEmpty()) {
			throw new StackUnderflowException("Cannot remove from empty stack");
		} else {
			T top = stack[topIndex];
			stack[topIndex] = null;
			topIndex--;
			return top;
		}
		
	}
	
	/**
	 * This method returns the top entry of the stack array. 
	 * @return The top entry of the stack.
	 * @throws StackUnderflowException If the stack is empty.
	 */

	@Override
	public T top() throws StackUnderflowException {
		
		if(isEmpty()) {
			throw new StackUnderflowException("Cannot remove from empty stack");
			
		} else {
			T top = stack[topIndex];
			return top;
			}
	}
	
	/**
	 * This method returns the size of the stack.
	 * @return The size of the stack.
	 */

	@Override
	public int size() {
		
		return topIndex+1;
	}
	
	/**
	 * This method pushes, or adds, an object to the top of the stack
	 * and returns true or false depending on whether the addition was
	 * successful or not.
	 * @param e The entry to be pushed onto the stack.
	 * @return True if the push was successful and false otherwise.
	 * @throws StackOverflowException If the stack is full prior to the push.
	 */

	@Override
	public boolean push(T e) throws StackOverflowException {
		
		boolean status = false;
		
		if(isFull()) {
			throw new StackOverflowException("Cannot push to full stack");
		} else {
			stack[topIndex + 1] = e;
			topIndex++;
			status = true;
		}
		
		return status;
	}
	
	/**
	 * This method returns a string representation of the stack
	 * beginning with the earliest entry in the stack and
	 * finishing with the top entry of the stack.
	 * @return The string containing the concatenated entries of the stack. 
	 */
	
	@Override
	public String toString() {
		String s = "";
		
		for (int i = 0; i <= topIndex; i++) {
			s += stack[i];
		}
		
		return s;
	}
	
	/**
	 * This method returns a string representation of the stack
	 * by concatenating each entry to a delimiter that is passed
	 * as an argument beginning with the earliest entry in the
	 * stack and ending with the top entry of the stack.
	 * @param delimiter The delimiter to be placed between entries of the stack.
	 * @return The string representation of the stack with the delimiter.
	 */

	@Override
	public String toString(String delimiter) {
	String s = "";
		
		for (int i = 0; i <= topIndex; i++) {
			if(i == topIndex) {
				s += stack[i];
			} else {
				s += stack[i] + delimiter;
			}
		}
		
		return s;
	}
	
	/**
	 * This method accepts an ArrayList as a parameter and 
	 * copies each entry in the ArrayList to the stack array.
	 * @param list The ArrayList whose entries are to be copied to the stack.
	 */

	@Override
	public void fill(ArrayList<T> list) {
		int len = list.size();
		
		ArrayList<T> newList = new ArrayList<T>(len);
		for(T e : list) {
			newList.add(e);
		}
		
		for(T e : newList) {
			stack[topIndex + 1] = e;
			topIndex++;
		}
		
	}
	

}
