/**
 * This class represents a sorted double linked list that implements a sorted list ADT.
 * It extends the BasicDoubleLinkedList class instead of duplicating the methods 
 * of the BasicDoubleLinkedList class. The class overrides the add methods of 
 * the super class to disallow them in favor of an add method that defines
 * additions of entries to a sorted list. Outside of this method, it inherits the
 * methods of the super class. 
 * @author hudson
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
	
	Comparator<T> comparator;
	
	/**
	 * This constructor creates a new list object
	 * and sets the comparator field to the
	 * comparator passed as an argument. 
	 * @param comparator2 The comparator to be used to make comparisons between list entries.
	 */
	
	public SortedDoubleLinkedList(Comparator<T> comparator2) {
		this.comparator = comparator2;
	}
	
	/**
	 * This method adds an item to the sorted list by
	 * comparing the item to other items in the list
	 * according to the comparator used to initialize
	 * the sorted list. 
	 * @param data The data to be added to the sorted list.
	 * @return The sorted list.
	 */
	
	public SortedDoubleLinkedList<T> add(T data){
		
		Node currentNode = null;
		Node nodeAfter = head;
		Node endNode = null;
		boolean found = false;
		
		if(numberOfEntries <= 0) {
			head = new Node(data);
			tail = head;
			numberOfEntries++;
			return this;
		} else if(numberOfEntries == 1) {
			if(comparator.compare(data, head.getData()) <= 0) {
				currentNode = new Node(null, data, head);
				head.setPreviousNode(currentNode);
				head = currentNode;
				numberOfEntries++;
		} else {
			currentNode = new Node(head,data,null);
			head.setNextNode(currentNode);
			tail = currentNode;
			numberOfEntries++;
		}
			return this;
			
		} else {
		
		while(nodeAfter != null) {
			if(comparator.compare(data, nodeAfter.getData()) <= 0) {
				if(nodeAfter.getPreviousNode() != null && !found) {
					currentNode = new Node(nodeAfter.getPreviousNode(),data, nodeAfter);
					nodeAfter.getPreviousNode().setNextNode(currentNode);
					nodeAfter.setPreviousNode(currentNode);
					numberOfEntries++;
				} else {
					currentNode = new Node(null, data, nodeAfter);
					head = currentNode;
					nodeAfter.setPreviousNode(currentNode);
					numberOfEntries++;
				}
				found = true;
			}
			
			if(nodeAfter.getNextNode() == null && !found) {
				endNode = new Node(tail, data, null);
				tail.setNextNode(endNode);
				tail = endNode;
				numberOfEntries++;
				found=true;
				}
			
			if(found) {
				nodeAfter=null;
			} else {
				nodeAfter = nodeAfter.getNextNode();
				}
			}
		}

		return this;
	}
	
	/**
	 * This method overrides the addToEnd method of the
	 * super class and throws an UnsupportedOperationException
	 * if the client attempts to invoke it.
	 * @param data The data to be added.
	 * @return The basic linked list to which data is added (but this is not allowed)
	 * @throws UnsupportedOperationException If the method is called.
	 */
	
	@Override
	public BasicDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	/**
	 * This method overrides the addToFront method of the
	 * super class and throws an UnsupportedOperationException
	 * if the client attempts to invoke it.
	 * @param data The data to be added.
	 * @return The basic linked list to which data is added (but this is not allowed)
	 * @throws UnsupportedOperationException If the method is called.
	 */
	
	@Override
	public BasicDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	/**
	 * This method calls the iterator method of the
	 * superclass, which returns a new ListIterator
	 * object.
	 * @return A ListIterator object.
	 */
	
	@Override
	public ListIterator<T> iterator(){
		return super.iterator();
	}
	
	/**
	 * This method calls the remove method of the
	 * superclass and passes a data argument and comparator
	 * argument to this method. 
	 * @param data The data of the node to be removed.
	 * @param comparator The means of comparing entries.
	 * @return The sorted linked list obtained after removing the given entry.
	 */
	
	@Override
	public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator){
		super.remove(data, comparator);
		return this;
	}
	

}
