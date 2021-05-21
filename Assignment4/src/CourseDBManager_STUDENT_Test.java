import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseDBManager_STUDENT_Test {

	CourseDBManager cMgr;

	@BeforeEach
	void setUp() throws Exception {
		cMgr = new CourseDBManager();
	}

	@AfterEach
	void tearDown() throws Exception {
		cMgr = null;
		
	}

	@Test
	void testAdd() {
		
		try {
		cMgr.add("ANTH240", 23456, 2, "SW499", "Dr. Jackson");
		cMgr.add("CMSC101", 65432, 3, "SC307", "Dr. Matthews");
		cMgr.add("ECON140", 23455, 1, "Distance-Learning", "Dr. Johnson");
		cMgr.add("MATH180", 23457, 4, "HT301", "Dr. Peterson");
		} catch (Exception e) {
			fail("this shouldn't have caused an exception");
		}
	}
	
	
	@Test
	void testReadFile() {
		
		File f = new File("Courses.txt");
		
		try {
		PrintWriter pw = new PrintWriter(f);
		
		pw.println("CMSC100 21556 2 Distance-Learning Janet E. Joy");
		pw.println("CMSC100 22344 2 SW217 Gloria E. Barron");
		pw.println("CMSC100 22974 2 Distance-Learning Janet E. Joy");
		pw.println("CMSC110 21561 3 SC451 Rabiha J. Kayed");
		pw.println("CMSC110 20484 3 HT300 Madhvi A. Shah");
		pw.println("CMSC110 23363 3 SC451 Sascha Simkanich");
		pw.println("CMSC110 21565 3 Distance Learning Janet E. Joy");
		pw.println("CMSC110 21564 3 SC451 Behzad Maghami");
		pw.println("CMSC110 21560 3 SC450 Behzad Maghami");
		pw.close();
		cMgr.readFile(f);
		} catch(FileNotFoundException e) {
			fail("This shouldn't have occurred");
		}
		
		ArrayList<String> courses = new ArrayList<>();
		courses = cMgr.showAll();
		
		assertEquals(courses.get(0),"\nCourse:CMSC110 CRN:20484 Credits:3 Instructor:Madhvi A. Shah Room:HT300");
		assertEquals(courses.get(1),"\nCourse:CMSC100 CRN:21556 Credits:2 Instructor:Janet E. Joy Room:Distance-Learning");
		assertEquals(courses.get(2),"\nCourse:CMSC110 CRN:21560 Credits:3 Instructor:Behzad Maghami Room:SC450");
		assertEquals(courses.get(3),"\nCourse:CMSC110 CRN:21561 Credits:3 Instructor:Rabiha J. Kayed Room:SC451");
		assertEquals(courses.get(4),"\nCourse:CMSC110 CRN:21564 Credits:3 Instructor:Behzad Maghami Room:SC451");
		assertEquals(courses.get(5),"\nCourse:CMSC110 CRN:21565 Credits:3 Instructor:Learning Janet Room:Distance");
		assertEquals(courses.get(6),"\nCourse:CMSC100 CRN:22344 Credits:2 Instructor:Gloria E. Barron Room:SW217");
		assertEquals(courses.get(7),"\nCourse:CMSC100 CRN:22974 Credits:2 Instructor:Janet E. Joy Room:Distance-Learning");
		assertEquals(courses.get(8),"\nCourse:CMSC110 CRN:23363 Credits:3 Instructor:Sascha Simkanich Room:SC451");
	}
	
	@Test
	void showAll() {
		cMgr.add("ANTH240", 23456, 2, "SW499", "Dr. Jackson");
		cMgr.add("CMSC101", 65432, 3, "SC307", "Dr. Matthews");
		cMgr.add("ECON140", 23455, 1, "Distance-Learning", "Dr. Johnson");
		cMgr.add("MATH180", 23457, 4, "HT301", "Dr. Peterson");
		ArrayList<String> courses = new ArrayList<>();
		courses = cMgr.showAll();
		
		assertEquals(courses.get(0),"\nCourse:ECON140 CRN:23455 Credits:1 Instructor:Dr. Johnson Room:Distance-Learning");
		assertEquals(courses.get(1),"\nCourse:ANTH240 CRN:23456 Credits:2 Instructor:Dr. Jackson Room:SW499");
		assertEquals(courses.get(2),"\nCourse:MATH180 CRN:23457 Credits:4 Instructor:Dr. Peterson Room:HT301");
		assertEquals(courses.get(3),"\nCourse:CMSC101 CRN:65432 Credits:3 Instructor:Dr. Matthews Room:SC307");
		
	}
	
	@Test
	void get() {
		CourseDBElement course1 = new CourseDBElement("ANTH240", 23456, 2, "SW499", "Dr. Jackson");
		CourseDBElement course2 = new CourseDBElement("CMSC101", 65432, 3, "SC307", "Dr. Matthews");
		CourseDBElement course3 = new CourseDBElement("ECON140", 23455, 1, "Distance-Learning", "Dr. Johnson");
		CourseDBElement course4 = new CourseDBElement("MATH180", 23457, 4, "HT301", "Dr. Peterson");
		
		cMgr.add("ANTH240", 23456, 2, "SW499", "Dr. Jackson");
		cMgr.add("CMSC101", 65432, 3, "SC307", "Dr. Matthews");
		cMgr.add("ECON140", 23455, 1, "Distance-Learning", "Dr. Johnson");
		cMgr.add("MATH180", 23457, 4, "HT301", "Dr. Peterson");
		
		assertEquals(0,cMgr.get(23456).compareTo(course1));
		assertEquals(0,cMgr.get(65432).compareTo(course2));
		assertEquals(0,cMgr.get(23455).compareTo(course3));
		assertEquals(0,cMgr.get(23457).compareTo(course4));

	}
	

}
