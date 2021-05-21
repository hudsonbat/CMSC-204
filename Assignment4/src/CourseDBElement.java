/**
 * This class represents a course. Accordingly, the class contains fields that
 * represent the features of a course, such as the course ID, the number of credits,
 * the instructor name, room name, and so on. The class provides constructors and
 * setter methods that enable the construction/mutation of course objects as well as 
 * getter methods that enable access to course data fields. The class also implements
 * the Comparable interface so that course objects can be compared on the basis of
 * the crn number. The class also contains an overriden hashCode method to generate a 
 * hashcode based on the crn number.
 * @author hudson
 *
 */

public class CourseDBElement implements Comparable<CourseDBElement> {
	
	String crsID;
	int crn;
	int numCred;
	String roomNum;
	String instName;
	
	/**
	 * No-arg constructor
	 */
	
	public CourseDBElement() {}
	
	/**
	 * This constructor initializes the fields of a course object to the values
	 * passed to its parameters. 
	 * @param crsID The course id.
	 * @param crn The crn number.
	 * @param numCred The number of credits.
	 * @param roomNum The room number.
	 * @param instName The instructor name.
	 */
	
	public CourseDBElement(String crsID, int crn, int numCred, String roomNum, String instName) {
		this.crsID = crsID;
		this.crn = crn;
		this.numCred = numCred;
		this.roomNum = roomNum;
		this.instName = instName;
	}
	
	/**
	 * This method returns the course id of the course.
	 * @return The course id.
	 */
	
	public String getCrsID() {
		return crsID;
	}
	
	/**
	 * This method sets the course id to the course id value passed 
	 * to the method. 
	 * @param crsID The course id.
	 */

	public void setCrsID(String crsID) {
		this.crsID = crsID;
	}
	
	/**
	 * This method returns the crn number of the course.
	 * @return The crn number of the course.
	 */

	public int getCRN() {
		return crn;
	}
	
	/**
	 * This method sets the crn number of a course object
	 * to the value passed as an argument.
	 * @param crn The crn number.
	 */

	public void setCRN(int crn) {
		this.crn = crn;
	}
	
	/**
	 * This method returns the room number of a
	 * course object.
	 * @return The room number.
	 */

	public String getRoomNum() {
		return roomNum;
	}
	
	/**
	 * This method sets the room number of a
	 * course object to the value passed as an
	 * argument.
	 * @param roomNum The room number.
	 */

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	
	/**
	 * This method returns the name of the
	 * instructor for a course object.
	 * @return The name of the instructor.
	 */

	public String getInstName() {
		return instName;
	}
	
	/**
	 * This method sets the name of the course
	 * instructor to the value passed as an
	 * argument.
	 * @param instName The instructor name.
	 */

	public void setInstName(String instName) {
		this.instName = instName;
	}
	
	/**
	 * This method sets the number of credits for a
	 * course object to the value passed as an
	 * argument.
	 * @param numCred The number of credits.
	 */
	
	public void setNumCred(int numCred) {
		this.numCred = numCred;
	}
	
	/**
	 * This method returns the number of credits
	 * for a given course.
	 * @return The number of credits.
	 */
	
	public int getNumCred() {
		return numCred;
	}
	
	/**
	 * This method generates a hash code for a given
	 * course by finding the string value of the course crn 
	 * and then calling the hashCode method of the 
	 * String class to generate a hash code for the crn.
	 * @return The hashcode for the crn. 
	 */
	
	@Override
	public int hashCode() {
		String crnStr = String.valueOf(crn);
		int code = crnStr.hashCode();
		return code;
	}
	
	/**
	 * This method compares courses by accepting a course 
	 * object as a parameter and comparing the string
	 * value of that crn number and the crn number of the
	 * course object it is being compared to.
	 * @param course The course being compared.
	 * @return 1, -1, or 0 depending on the outcome of the comparison.
	 */

	@Override
	public int compareTo(CourseDBElement course) {
		
		String crnStr1 = String.valueOf(crn);
		String crnStr2 = String.valueOf(course.getCRN());
		
		return crnStr1.compareTo(crnStr2);

	}
	
	

}
