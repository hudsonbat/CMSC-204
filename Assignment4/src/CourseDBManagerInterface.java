/**
 * This class represents a database manager for courses. The class 
 * contains methods to add courses to a course database either by 
 * reading a file or manually adding them, as well as retrieve courses
 * based on their crn numbers and display courses.
 * @author hudson
 */

import java.io.*;
import java.util.*;

public interface CourseDBManagerInterface {
	
	/**
	 * This method adds a course to a database of courses.
	 * @param id The id of the course.
	 * @param crn The crn of the course.
	 * @param credits The number of credits of the course.
	 * @param roomNum The room number of the course.
	 * @param instructor The course instructor.
	 */

	public void add(String id, int crn, int credits, String roomNum, String instructor);
	
	/**
	 * This method returns a course based on the course crn number
	 * passed as an argument.
	 * @param crn The crn of the course. 
	 * @return The corresponding course.
	 */
	
	public CourseDBElement get(int crn);
	
	/**
	 * This method reads a file containing course data and 
	 * adds the courses to a course database.
	 * @param input The file containing data on various courses. 
	 * @throws FileNotFoundException If the file is not found. 
	 */
	
	public void readFile(File input) throws FileNotFoundException;
	
	/**
	 * This method shows all of the courses and their data
	 * according to the ordering of their course crn's.
	 * @return The list of courses and course data.
	 */

	public ArrayList<String> showAll();

}