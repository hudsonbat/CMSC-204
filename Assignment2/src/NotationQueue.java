/**
 * This class implements a queue of objects of any type by using an
 * array-based implementation. The class uses a circular array to 
 * account for the possibility of having to add entries to the end
 * of the queue.
 * @author hudson
 */

import java.util.ArrayList;

public class NotationQueue<T> implements QueueInterface<T> {
	
	private T[] queue;
	private int frontIndex;
	private int backIndex;
	private boolean integrityOK;
	private static final int DEFAULT_CAPACITY = 15;
	
	/**
	 * This no-arg constructor calls the parameterized constructor and 
	 * initializes the size of the queue to the default capacity.
	 */
	
	public NotationQueue() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * This constructor accepts an integer that is used to 
	 * initialize the size of the queue array. An extra position
	 * is added to the array so that it is possible to 
	 * distinguish between a full and empty array. 
	 * The frontIndex is set to 0 and the backIndex is set to -1
	 * so that the first entry added has an index of 0.
	 * @param initialCapacity The initial capacity of the queue array.
	 */
	
	public NotationQueue(int initialCapacity) {
		integrityOK = false;
		
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[initialCapacity + 1];
		queue = temp;
		frontIndex = 0;
		backIndex = -1;
		integrityOK = true;
		
	}
	
	/**
	 * This method returns true if the queue array is empty and false otherwise.
	 * @return True if the array is empty and false otherwise.
	 */

	@Override
	public boolean isEmpty() {
		
		return frontIndex == (backIndex + 1) % queue.length;
	}
	
	/**
	 * This methods returns true if the queue array is full and false otherwise.
	 * @return True if the array is full and false otherwise.
	 */

	@Override
	public boolean isFull() {
		
		return frontIndex == (backIndex + 2) % queue.length;
	}
	
	/**
	 * This method dequeues, or removes, an object from the queue array and
	 * returns the object being removed.
	 * @return The object being removed.
	 * @throws QueueUnderflowException If the queue array is empty.
	 */

	@Override
	public T dequeue() throws QueueUnderflowException {
		
		if(isEmpty()) {
			throw new QueueUnderflowException("Cannot remove entry from empty queue");
		} else {
			T front = queue[frontIndex];
			queue[frontIndex] = null;
			frontIndex = (frontIndex + 1) % queue.length;
			return front;
		}
		
	}
	
	/**
	 * This method calculates and returns the size of the queue array.
	 * @return The size of the queue array.
	 */

	@Override
	public int size() {
		
		int size = 0;
		int front = frontIndex;
		int back = backIndex;
		
		while(front <= back) {
			size++;	
			front = (front + 1) % queue.length;
			
		}
		
		return size;
	}
	
	/**
	 * This method enqueues, or adds, an object to the queue array.
	 * @param e The object, or entry, to be added to the queue array.
	 * @return True if the enqueue, or addition, was successful and false otherwise.
	 * @throws QueueOverflowException If the array is full.
	 */

	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		
		if(isFull()) {
			throw new QueueOverflowException("Cannot enqueue to full queue");
		} else {
			backIndex = (backIndex + 1) % queue.length;
			queue[backIndex] = e;
			return true;
		}
	}
	
	/**
	 * This method appends or concatenates each Object in the queue
	 * starting with the first entry.
	 * @return The string representation of the queue.
	 */
	
	@Override
	public String toString() {
		String s = "";
		
		int front = frontIndex;
		int back = backIndex;
		
		while(front <= back) {
			s += queue[front];
			front = (front + 1) % queue.length;
		}
		
		
		return s;
	}
	
	
	/**
	 * This method appends or concatenates each Object in the queue
	 * with a delimiter that is passed as an argument.
	 * @param delimiter The delimiter that each Object is appended to.
	 * @return The string representation of the queue with the delimiter.
	 */

	@Override
	public String toString(String delimiter) {
		String s = "";
		
		int front = frontIndex;
		int back = backIndex;
		
		while(front <= back) {
			if(front == back) {
				s += queue[front];
			} else {
				s += queue[front] + delimiter;
			}
			front = (front + 1) % queue.length;
		}
		
		return s;

	}
	
	/**
	 * This method fills or copies the queue array with the entries of
	 * an ArrayList that is passed as a parameter. 
	 * @param list The ArrayList from which entries are copied to the queue.
	 */

	@Override
	public void fill(ArrayList<T> list) {
		
		backIndex = backIndex + 1;
		
		int len = list.size();
		
		ArrayList<T> newList = new ArrayList<T>(len);
		for(T e : list) {
			newList.add(e);
		}
		
		for(T e : newList) {
			queue[backIndex] = e;
			backIndex++;
		}
		
		backIndex--;
		
	}
	
	

}
