/**
 * This class represents a basic double linked list that implements a list ADT.
 * It contains a private Node class and a private iterator class that reduces the
 * need to traverse the linked list to locate specific nodes. The class contains
 * methods for instantiating a list, adding to the front, adding to the back,
 * retrieving from the front, retrieving from the back, removing from the front,
 * removing from the back, and displaying the entries of the list.
 * @author hudson
 */

import java.util.ArrayList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> implements Iterable<T> {
	
	protected Node head;
	protected Node tail;
	protected int numberOfEntries;
	
	/**
	 * This no-arg constructor instantiates an empty list.
	 */
	
	public BasicDoubleLinkedList() {
		head = null;
		tail = null;
		numberOfEntries = 0;
	}
	
	/**
	 * This method accepts data of generic type T 
	 * and passes it to the Node class constructor, 
	 * creating a node that is added to the end of
	 * the list.
	 * @param data The data that the node will store.
	 * @return The linked list constructed.
	 */
	
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		Node newNode = new Node(tail,data, null);
		
		if(isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			tail.setNextNode(newNode);
			tail = newNode;
		}
		numberOfEntries++;
		return this;
	}
	
	/**
	 * This method accepts data of generic type T
	 * and passes it to the Node class constructor, 
	 * creating a node that is added to the front of
	 * the list.
	 * @param data The data that the node will store.
	 * @return The linked list.
	 */
	
	public BasicDoubleLinkedList<T> addToFront(T data) {
		Node newNode = new Node(null,data,head);
		
		if(isEmpty()) {
			head = newNode;
		} else {
			head.setPreviousNode(newNode);
			head = newNode;
		}
		numberOfEntries++;
		return this;
	}
	
	/**
	 * This method removes an item from the list based on the
	 * data that it stores according to a comparator object
	 * that defines a mode of comparison between two data entries. 
	 * @param targetData The data that the item stores.
	 * @param comparator The mode of comparing two data entries.
	 * @return The linked list.
	 */
	
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator){
		
		Node currentNode = head;
		Node nodeToRemove = null;
		Node nodeBefore;
		Node nodeAfter;
		
		while(currentNode != null) {
		
		if(comparator.compare(currentNode.getData(),targetData) == 0) {
			nodeToRemove = currentNode;
		
		if(nodeToRemove == head) {
			head = head.getNextNode();
		} else if(nodeToRemove == tail) {
			tail = tail.getPreviousNode();
		} else {
			nodeAfter = nodeToRemove.getNextNode();
			nodeBefore= nodeToRemove.getPreviousNode();
			nodeBefore.setNextNode(nodeAfter);	
			}
		}
		
		currentNode = currentNode.getNextNode();
		}
		numberOfEntries--;
		return this;
	}
	
	/**
	 * This method returns the data
	 * stored in the first element
	 * of the list removes the first 
	 * element from the list.
	 * @return The data stored in the first item of the list.
	 */
	
	public T retrieveFirstElement() {
		T first = head.getData();
		
		head = head.getNextNode();
		
		numberOfEntries--;
		
		return first;
		
	}
	
	/**
	 * This method returns the data
	 * stored in the last element
	 * of the list removes the last 
	 * element from the list.
	 * @return The data stored in the last item of the list.
	 */
	
	public T retrieveLastElement() {
		T last = tail.getData();
		
		tail = tail.getPreviousNode();
		
		numberOfEntries--;
		
		return last;
	}
	
	/**
	 * This method returns the entries in
	 * the list in the form of an ArrayList.
	 * @return An ArrayList of the list entries.
	 */
	
	public ArrayList<T> toArrayList(){
		
		ArrayList<T> list = new ArrayList<T>(getSize());
		
		Node currentNode = head;
		int index = 0;
		while(currentNode!= null && index < getSize()) {
			list.add(currentNode.getData());
			index++;
			currentNode = currentNode.getNextNode();
		}
		return list;
	}
	
	/**
	 * This method returns the data
	 * stored in the first element
	 * of the list.
	 * @return The data stored in the first element of the list.
	 */
	
	public T getFirst() {
		return head.getData();
	}
	
	/**
	 * This method returns the data
	 * stored in the last element
	 * of the list.
	 * @return The data stored in the last element of the list.
	 */
	
	public T getLast() {
		
		return tail.getData();
	}
	
	/**
	 * This method returns the size of the list.
	 * @return The size of the list.
	 */
	
	public int getSize() {
		return numberOfEntries;
	}
	
	/**
	 * This method determines whether the list
	 * is empty or not.
	 * @return True if the list is empty, false if otherwise.
	 */
	
	public boolean isEmpty() {
		return head == null;
	}
	
	/**
	 * This method returns a new ListIterator object
	 * and throws exceptions if there are no 
	 * elements to be iterated through in the list
	 * (e.g. the list is empty) or the iterator
	 * is called by a method that is not supposed to 
	 * use it.
	 * @throws UnsupportedOperationException If it is called from a method that does't support iterators.
	 * @throws NoSuchElementException If the list is empty.
	 */
	
	@Override
	public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException {

		return new IteratorForLinkedList();
	}
	
	
	
	public class IteratorForLinkedList<T> implements ListIterator<T>{
		
		private Node nextNode;
		private Node previousNode;
		
		/**
		 * This no-arg constructor assigns the
		 * first node in the list to the
		 * nextNode variable, which represents
		 * the iterator's position.
		 */
		
		private IteratorForLinkedList() {
			nextNode = head;
		}
		
		/**
		 * This method throws an UnsupportedOperationException
		 * when called as it is not to be called by the client.
		 * @param arg The argument to be added (which is not allowed).
		 */

		@Override
		public void add(T arg) {
			throw new UnsupportedOperationException("add(T arg) is not supported "
					+ "by this operator");
		}
		
		/**
		 * This method returns true if the list
		 * has a next node and false otherwise
		 * @return True if the list has a next node and false otherwise.
		 */

		@Override
		public boolean hasNext() {
			return nextNode != null;
		}
		
		/**
		 * This method returns true if the list has
		 * a previous node and false otherwise.
		 * @return True if the list has a previous node and false otherwise.
		 */

		@Override
		public boolean hasPrevious() {
			return previousNode != null;
		}
		
		/**
		 * This method returns the data stored
		 * within the next element of the list.
		 * @return The data stored in the next element of the list.
		 */

		@Override
		public T next() {
			T result;
			
			if(hasNext()) {
				previousNode = nextNode;
				result = (T)nextNode.getData();
				nextNode = nextNode.getNextNode();
				
			} else {
				throw new NoSuchElementException("Illegal call to next():" 
						+ "iterator is after end of list");
			}
			return result;
		}
		
		/**
		 * This method throws an UnsupportedOperationException
		 * when called as it is not to be called by the client.
		 * @return The index of the next entry (which is not allowed).
		 */

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException("nextIndex() is not supported "
					+ "by this operator");
		}
		
		/**
		 * This method returns the data of the
		 * previous node in the list.
		 * @return The data stored in the next element of the list.
		 */

		@Override
		public T previous() {
			
			T result;
			
			if(hasPrevious()) {
				result = (T)previousNode.getData();
				previousNode = previousNode.getPreviousNode();
				
			} else {
				throw new NoSuchElementException("Illegal call to previous():"
						+ "iterator is at the beggining of lise");
			}
			
			return result;
		}
		
		/**
		 * This method throws an UnsupportedOperationException
		 * when called as it is not to be called by the client.
		 * @return The index of the previous entry (which is not allowed).
		 */

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException("previousIndex() is not supported "
					+ "by this operator");
		}
		
		/**
		 * This method throws an UnsupportedOperationException
		 * when called as it is not to be called by the client.
		 */

		@Override
		public void remove() {
			throw new UnsupportedOperationException("remove() is not supported "
					+ "by this operator");
		}
		
		/**
		 * This method throws an UnsupportedOperationException
		 * when called as it is not to be called by the client.
		 * @param arg The data to be set (which is not allowed).
		 */

		@Override
		public void set(T arg) {
			throw new UnsupportedOperationException("set(T arg) is not supported "
					+ "by this operator");
		}
	}
	
	
	
	public class Node{
		
		protected T data;
		protected Node next;
		protected Node previous;
		
		/**
		 * This constructor accepts data of generic
		 * type T and calls and passes this data 
		 * to the other Node constructor that
		 * accepts data, previous, and next
		 * node arguments. It initializes the
		 * next and previous parameters to null.
		 * @param data The data to be stored in the node.
		 */
		
		public Node(T data) {
			this(null,data,null);
		}
		
		/**
		 * This constructor accepts a previous node,
		 * next node, and data argument. It assigns the
		 * previous node argument to the previous field, 
		 * the next node argument to the next field,
		 * and the data argument to the data field.
		 * an argument. 
		 * @param previous The previous node.
		 * @param data The data to be stored in this node.
		 * @param next The next node.
		 */
		
		public Node(Node previous,T data, Node next) {
			this.previous = previous;
			this.data = data;
			this.next = next;
		}
		
		/**
		 * This method sets the next field to the node
		 * passed as an argument.
		 * @param nextNode The node to be set as the next node.
		 */
		
		public void setNextNode(Node nextNode) {
			next = nextNode;
		}
		
		/**
		 * This method returns the node in the
		 * next field.
		 * @return The node referenced by the next field.
		 */
		
		public Node getNextNode() {
			return next;
		}
		
		/**
		 * This method sets the previous field to the
		 * node passed as an argument.
		 * @param previousNode The node to be set as the previous Node.
		 */
		
		public void setPreviousNode(Node previousNode) {
			previous = previousNode;
		}
		
		/**
		 * This method returns the node in
		 * the previous field.
		 * @return The node referenced by the previous field.
		 */
		
		public Node getPreviousNode() {
			return previous;
		}
		
		/**
		 * This method sets the data field to the
		 * data passed as an argument.
		 * @param anEntry The data to be stored in the data field.
		 */
		
		public void setData(T anEntry) {
			data = anEntry;
		}
		
		/**
		 * This method returns the data stored
		 * in the current node being referenced.
		 * @return The data stored in the node.
		 */
		
		public T getData() {
			return data;
		}
		
		
	
	}


}
