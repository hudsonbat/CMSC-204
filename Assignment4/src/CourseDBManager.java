/**
 * This class represents a database manager for courses by 
 * implementing the CourseDBManagerInterface interface. The class 
 * contains methods to add courses to a course database either by reading 
 * a file or manually adding them, as well as retrieve courses
 * based on their crn numbers and display courses. The manager
 * also orders the courses that it adds and shows based on the crn 
 * numbers of the respective courses.
 * @author hudson
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class CourseDBManager implements CourseDBManagerInterface {
	
	protected CourseDBStructure courses = new CourseDBStructure(10);
	
	/**
	 * This method adds courses to an instance of a CourseDBStructure by
	 * passing the arguments to the CourseDBElement constructor and calling
	 * the add method of the CourseDBStructure class.
	 * @param id The id of the course.
	 * @param crn The crn of the course.
	 * @param credits The number of credits of the course.
	 * @param roomNum The room number of the course.
	 * @param instructor The course instructor.
	 */

	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement course = new CourseDBElement(id,crn,credits,roomNum,instructor);
		courses.add(course);
	}
	
	/**
	 * This method returns a course based on the course crn number
	 * passed as an argument.
	 * @param crn The crn of the course. 
	 * @return The corresponding course.
	 */

	@Override
	public CourseDBElement get(int crn) {
		
		CourseDBElement course = null;
		
		try {
			course = courses.get(crn);
		} catch (IOException e) {
			e.getMessage();
		}
		
		return course;
	}
	
	/**
	 * This method reads a file containing course data and 
	 * adds the courses to a course database.
	 * @param input The file containing data on various courses. 
	 * @throws FileNotFoundException If the file is not found. 
	 */

	@Override
	public void readFile(File input) throws FileNotFoundException {
		Scanner scan = new Scanner(input);
		String str [];
		CourseDBElement course;
		String inst;
		
		if(scan.hasNext()) {
			while(scan.hasNext()) {
				str = scan.nextLine().split(" ");
				int crn = Integer.parseInt(str[1]);
				int credits = Integer.parseInt(str[2]);
				if(str.length == 7) {
					inst = str[4] + " " + str[5] + " " + str[6];
				} else {
					inst = str[4] + " " + str[5];
				}
				course = new CourseDBElement(str[0],crn,credits,str[3],inst);
				courses.add(course);
			}
			scan.close();
		} else {
			scan.close();
			throw new FileNotFoundException();
		}
		
	}
	
	/**
	 * This method shows all of the courses and their data
	 * according to the ordering of their course crn's.
	 * @return The list of courses and course data.
	 */

	@Override
	public ArrayList<String> showAll() {
		
		ArrayList<CourseDBElement> courses1 = new ArrayList<CourseDBElement>();
		ArrayList<String> courses2 = new ArrayList<>();
		String str;
		CourseDBElement course;
		CourseDBElement crs;
		int i = 0;
		int j = 0;
		
		while(i < courses.length) {
			if(courses.hashTable[i]!=null) {
				while(j < courses.hashTable[i].size()) {
					course = (CourseDBElement) courses.hashTable[i].get(j);
					crs = new CourseDBElement(course.getCrsID(),course.getCRN(),course.getNumCred(),course.getRoomNum(),course.getInstName());
					courses1.add(crs);
					j++;
				}
			}
			i++;
			j=0;
		}
		
		Collections.sort(courses1);
		
		
		for(int h=0;h<courses1.size();h++) {
			course = courses1.get(h);
			str = "\n" + "Course:" + course.getCrsID() + " " + "CRN:" + course.getCRN() + " " +
					"Credits:" + course.getNumCred() + " " + "Instructor:" + course.getInstName() + " " +
					"Room:" + course.getRoomNum();
			courses2.add(str);
		}
	
		
		return courses2;
	}
	

}
