import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TownGraphManager_STUDENT_Test {
	
	TownGraphManager graph = new TownGraphManager();
	
	String t1 = "Chicago";
	String t2 = "Detroit";
	String t3 = "Kansas City";
	String t4 = "Dallas";
	String t5 = "New York";
	String t6 = "Washington";
	String t7 = "Richmond";
	String t8 = "Indianapolis";
	String t9 = "Nashville";
	String t10 = "Atlanta";
	String t11 = "Miami";
	String t12 = "New Orleans";

	@BeforeEach
	void setUp() throws Exception {
		
	graph.addTown(t1); graph.addTown(t2); graph.addTown(t3); graph.addTown(t4);
	graph.addTown(t5); graph.addTown(t6); graph.addTown(t7); graph.addTown(t8);
	graph.addTown(t9); graph.addTown(t10); graph.addTown(t11); graph.addTown(t12);
		
	graph.addRoad(t1, t2, 282, "I-94");
	graph.addRoad(t1, t3, 510, "I-55");
	graph.addRoad(t3, t8, 482, "I-70(W)");
	graph.addRoad(t3, t9, 555, "I-24");
	graph.addRoad(t3, t4, 513, "I-49(N)");
	graph.addRoad(t2, t6, 524, "I-76");
	graph.addRoad(t2, t8, 287,"I-69");
	graph.addRoad(t6, t7, 109,"I-95(S)");
	graph.addRoad(t6, t8, 588, "I-70(E)");
	graph.addRoad(t9, t8, 288,"I-65");
	graph.addRoad(t6, t9, 666, "I-40(E)");
	graph.addRoad(t6, t5, 225, "I-95(N)");
	graph.addRoad(t7, t9, 614, "I-81");
	graph.addRoad(t7, t10, 533, "I-85");
	graph.addRoad(t10, t11, 664, "I-75(E)");
	graph.addRoad(t10, t9, 249, "I-75(W)");
	graph.addRoad(t10, t4, 782, "I-20");
	graph.addRoad(t4, t9, 664, "I-40(W)");
	graph.addRoad(t4, t12, 506, "I-49(S)");
	graph.addRoad(t12, t11, 506, "I-10");
	}

	@AfterEach
	void tearDown() throws Exception {
	
	graph=null;
		
	}

	@Test
	public void testAddRoad() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("I-10", roads.get(0));
		assertEquals("I-20", roads.get(1));
		assertEquals("I-24", roads.get(2));
		assertEquals("I-40(E)", roads.get(3));
		graph.addRoad("Baltimore", "Philadelphia", 100,"I-95(W)");
		roads = graph.allRoads();
		assertEquals("I-10", roads.get(0));
		assertEquals("I-20", roads.get(1));
		assertEquals("I-24", roads.get(2));
		assertEquals("I-40(E)", roads.get(3));
		assertEquals("I-95(W)", roads.get(20));
		
	}

	@Test
	public void testGetRoad() {
		assertEquals("I-94", graph.getRoad(t1, t2));
		assertEquals("I-40(E)", graph.getRoad(t6, t9));
	}

	@Test
	public void testAddTown() {
		assertEquals(false, graph.containsTown("Baltimore"));
		graph.addTown("Baltimore");
		assertEquals(true, graph.containsTown("Baltimore"));
	}
	
	@Test
	public void testDisjointGraph() {
		assertEquals(false, graph.containsTown("Baltimore"));
		graph.addTown("Baltimore");
		ArrayList<String> path = graph.getPath(t1,"Baltimore");
		assertFalse(path.size() > 0);
	}

	@Test
	public void testContainsTown() {
		assertEquals(true, graph.containsTown("Washington"));
		assertEquals(false, graph.containsTown("Memphis"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(t3, t8));
		assertEquals(false, graph.containsRoadConnection(t5, t12));
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("I-10", roads.get(0));
		assertEquals("I-20", roads.get(1));
		assertEquals("I-24", roads.get(2));
		assertEquals("I-70(E)", roads.get(10));
		assertEquals("I-70(W)", roads.get(11));
	}

	@Test
	public void testDeleteRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(t7, t10));
		graph.deleteRoadConnection(t7, t10, "I-85");
		assertEquals(false, graph.containsRoadConnection(t7, t10));
	}

	@Test
	public void testDeleteTown() {
		assertEquals(true, graph.containsTown(t9));
		graph.deleteTown(t9);
		assertEquals(false, graph.containsTown(t9));
	}
	
	@Test
	public void testAllTowns() {
		ArrayList<String> roads = graph.allTowns();
		assertEquals(t10, roads.get(0));
		assertEquals(t1, roads.get(1));
		assertEquals(t4, roads.get(2));
		assertEquals(t2, roads.get(3));
		assertEquals(t6, roads.get(11));
	}

	@Test
	public void testGetPath() {
		ArrayList<String> path = graph.getPath(t7,t4);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Richmond via I-81 to Nashville 614 mi",path.get(0).trim());
		  assertEquals("Nashville via I-40(W) to Dallas 664 mi",path.get(1).trim());

	}
	
	@Test
	public void testGetPathA() {
		ArrayList<String> path = graph.getPath(t5,t3);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("New York via I-95(N) to Washington 225 mi",path.get(0).trim());
		  assertEquals("Washington via I-70(E) to Indianapolis 588 mi",path.get(1).trim());
		  assertEquals("Indianapolis via I-70(W) to Kansas City 482 mi",path.get(2).trim());
	}
	
	@Test
	public void testGetPathB() {
		ArrayList<String> path = graph.getPath(t11,t2);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Miami via I-75(E) to Atlanta 664 mi",path.get(0).trim());
		  assertEquals("Atlanta via I-75(W) to Nashville 249 mi",path.get(1).trim());
		  assertEquals("Nashville via I-65 to Indianapolis 288 mi",path.get(2).trim());
		  assertEquals("Indianapolis via I-69 to Detroit 287 mi",path.get(3).trim());
	}
	
	@Test
	public void testPopulateTownGraph() throws FileNotFoundException, IOException {
		File md = new File("C:\\Users\\hudso\\Desktop\\USTowns.txt");
		graph.populateTownGraph(md);
		
		ArrayList<String> pathB = graph.getPath(t11,t2);
		  assertNotNull(pathB);
		  assertTrue(pathB.size() > 0);
		  assertEquals("Miami via I-75(E) to Atlanta 664 mi",pathB.get(0).trim());
		  assertEquals("Atlanta via I-75(W) to Nashville 249 mi",pathB.get(1).trim());
		  assertEquals("Nashville via I-65 to Indianapolis 288 mi",pathB.get(2).trim());
		  assertEquals("Indianapolis via I-69 to Detroit 287 mi",pathB.get(3).trim());
		  
		  ArrayList<String> pathA = graph.getPath(t5,t3);
		  assertNotNull(pathA);
		  assertTrue(pathA.size() > 0);
		  assertEquals("New York via I-95(N) to Washington 225 mi",pathA.get(0).trim());
		  assertEquals("Washington via I-70(E) to Indianapolis 588 mi",pathA.get(1).trim());
		  assertEquals("Indianapolis via I-70(W) to Kansas City 482 mi",pathA.get(2).trim());
		
	}

}
