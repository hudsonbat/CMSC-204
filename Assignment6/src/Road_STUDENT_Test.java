import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Road_STUDENT_Test {
	
	Town t1; Town t2; Town t3; Town t4; Town t5; Town t6; Town t7; Town t8; Town t9; Town t10;
	Town t11;
	
	Road r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14,  r15, r16, r17, r18, r19, r20, r21; 

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
			
		r1 = new Road(t1, t2, 14, "I270-N");
		r2 = new Road(t2, t3, 13, "MD109");
		r3 = new Road(t2, t4, 8, "MD121");
		r4 = new Road(t2, t5,5,"I270-NC");
		r5 = new Road(t2, t6, 14, "MD108");
		r6 = new Road(t3,t4,9, "MD117W");
		r7 = new Road(t3,t7,8,"MD28");
		r8 = new Road(t5, t4,4,"MD117E");
		r9 = new Road(t5, t7, 6, "MD118W");
		r10 = new Road(t5, t8, 6,"I270-C");
		r11 = new Road(t5, t6, 15, "MD118E");
		r12 = new Road(t8, t7, 7, "MD124");
		r13 = new Road(t8, t9, 7, "I270-SC");
		r14 = new Road(t8, t6, 11, "BowieMill");
		r15 = new Road(t7, t10, 8, "MD190W");
		r16 = new Road(t9, t6, 8, "MD97");
		r17 = new Road(t9, t10, 6, "MD189");
		r18 = new Road(t9, t11, 7, "I270-S");
		r19 = new Road(t11, t10, 7, "MD190E");
	}

	@AfterEach
	void tearDown() throws Exception {
		
		t1 = t2 = t3 = t4 = t5 = t6 = t7 = t8 = t9 = t10 = t11 = null;
		
		r1 = r2 = r3 = r4 = r5 = r6 = r7 = r8 = r9 = r10 = r11 = r12 = r13 = r14 = r15 = r16 = r17 = r18 = r19 = null;
	}

	@Test
	void testGetName() {
		assertEquals("MD117W", r6.getName());
		assertEquals("MD118E", r11.getName());
		assertEquals("MD118W", r9.getName());
	}
	
	@Test
	void testToString() {
		assertEquals("Poolesville via MD117W to Boyds 9 mi", r6.toString());
		assertEquals("Germantown via MD118E to Olney 15 mi", r11.toString());
		assertEquals("Germantown via MD118W to Darnestown 6 mi", r9.toString());
	}
	
	@Test
	void testEquals() {
		
		Road road = new Road(new Town("Frederick"), new Town("Clarksburg"), 14, "I270-N");
		assertTrue(r1.equals(road));
		assertFalse(r7.equals(road));
	}
	
	@Test
	void testCompareTo() {
		
		Road road = new Road(new Town("Frederick"), new Town("Clarksburg"), 14, "I270-N");
		assertTrue(r1.compareTo(road) == 0);
		assertTrue(r3.compareTo(r1) > 0);
		assertTrue(r3.compareTo(r7) < 0);
	}
	
	@Test
	void testConnects() {
		
		assertTrue(r1.connects(t1, t2));
		assertFalse(r1.connects(t11, t3));
	}
	
	@Test
	void testContains() {
		
		assertTrue(r11.contains(t6));
		assertFalse(r10.contains(t11));
	}
	
	@Test
	void testGetSource() {
		
		assertEquals(r3.getSource(),t2);
		assertEquals(r8.getSource(), t5);
	}
	
	
	@Test
	void testGetDestination() {
		
		assertEquals(r19.getDestination(), t10);
		assertEquals(r17.getDestination(), t10);
	}
	
	@Test
	void testGetWeight() {
		
		assertEquals(r16.getWeight(),8);
		assertEquals(r8.getWeight(), 4);
	}
	
	
	@Test
	void testSetSource() {
		r19.setSource(new Town("Bethesda"));
		r14.setSource(new Town("Potomac"));
		assertEquals(r19.getSource(), t11);
		assertEquals(r14.getSource(), t10);
	}
	
	@Test
	void testSetDestination() {
		
		r13.setDestination(new Town("Rockville"));
		r16.setDestination(new Town("Boyds"));
		
		assertEquals(r13.getDestination(), t9);
		assertEquals(r16.getDestination(), t4);
	}
	

}
