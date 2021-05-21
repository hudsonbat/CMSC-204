import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Town_STUDENT_Test {
	
	Town t1; Town t2; Town t3; Town t4; Town t5; Town t6; Town t7; Town t8; Town t9; Town t10;
	Town t11;
	

	@BeforeEach
	void setUp() throws Exception {
		
		t1 = new Town("Frederick");
		t2 = new Town("Clarksburg");
		t3 = new Town("Poolesville");
		t4 = new Town("Boyds");
		t5 = new Town("Germantown");
		t6 = new Town("Olney");
		t7 = new Town("Darnestown");
		t8 = new Town("Gaithersburg");
		t9 = new Town("Rockville");
		t10 = new Town("Potomac");
		t11 = new Town("Bethesda");

	}

	@AfterEach
	void tearDown() throws Exception {
		
		t1 = t2 = t3 = t4 = t5 = t6 = t7 = t8 = t9 = t10 = t11 = null;
	}

	@Test
	void testGetName() {
		assertEquals("Olney", t6.getName());
		assertEquals("Bethesda", t11.getName());
		assertEquals("Rockville", t9.getName());
	}
	
	@Test
	void testToString() {
		assertEquals("The name of the town is Olney", t6.toString());
		assertEquals("The name of the town is Bethesda", t11.toString());
		assertEquals("The name of the town is Rockville", t9.toString());
	}
	
	@Test
	void testEquals() {
		Town t = new Town("Germantown");
		assertTrue(t5.equals(t));
		assertFalse(t7.equals(t));
	}
	
	@Test
	void testCompareTo() {
		Town t = new Town("Germantown");
		Town n = new Town(t);
		assertTrue(t5.compareTo(n) == 0);
		assertTrue(t3.compareTo(t) > 0);
		assertTrue(t7.compareTo(t) < 0);
	}
	
	

}
