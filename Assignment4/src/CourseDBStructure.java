/**
 * This class represents a course database by implementing the
 * CourseDBStructureInterface interface. It contains constructors
 * and methods for building course databases by adding course 
 * objects to a linked list hashtable. 
 * @author hudson
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class CourseDBStructure implements CourseDBStructureInterface {
	
	String name;
	int length;
	LinkedList [] hashTable;
	
	/**
	 * This constructor accepts an integer argument, which is used
	 * to set the length of the linked list hashtable. 
	 * @param length The length of the hashtable.
	 */
	
	public CourseDBStructure(int length) {
		this.length = length;
		hashTable = new LinkedList[length];
	}
	
	/**
	 * This constructor accepts a String argument that is used
	 * to name the database and an integer argument that is used
	 * to initialize the hashtable field to be of a certain length.
	 * @param name The name of the database.
	 * @param length The length of the hashtable.
	 */
	
	public CourseDBStructure(String name, int length) {
		this.name = name;
		this.length = length;
		hashTable = new LinkedList[length];
	}
	
	/**
	 * This method accepts a course object and adds it to a given index of
	 * the hashtable according to the crn of the course object. If the index
	 * is already occupied by another course object, the course is added to 
	 * the end of the linked chain for that given index of the hashtable.
	 * @param element The course object to be added to the course database.
	 */

	@Override
	public void add(CourseDBElement element) {
		
		int key = Math.abs(element.hashCode());
		int index = key % length;
		
		if(hashTable[index] == null) {
			LinkedList<CourseDBElement> list = new LinkedList<>();
			list.add(element);
			hashTable[index] = list;
		} else if(hashTable[index] != null) {
			hashTable[index].add(element);
		}
	}
	
	/**
	 * This method returns the course corresponding to a given
	 * crn. If more than one course corresponds to this index,
	 * the crn of each course in the linked chain is compared
	 * with the crn supplied as an argument until it is found.
	 * @param crn The crn of the course.
	 * @return The course element corresponding to the given crn.
	 * @throws IOException If the course element is not in the database.
	 */

	@Override
	public CourseDBElement get(int crn) throws IOException {
		
		String crnStr = String.valueOf(crn);
		int hashIndex = Math.abs(crnStr.hashCode()) % length;
		int index = 0;
		CourseDBElement crsCopy = null;
		boolean found = false;
		
		if(hashTable[hashIndex]!=null) {
			while(index < hashTable[hashIndex].size() && !found) {
				crsCopy = (CourseDBElement) hashTable[hashIndex].get(index);
				if(crn == crsCopy.getCRN()) {
					found = true;	
				}
				index++;
			}
		}else {
			throw new IOException();
		}
		
		return crsCopy;
	}
	
	/**
	 * This method returns the size of the hashtable.
	 * @return The size of the hashtable.
	 */

	@Override
	public int getTableSize() {
		return length;
	}
	

}
