import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Graph_STUDENT_Test {
	
	Graph graph = new Graph();
	
	Town t1 = new Town("Frederick");
	Town t2 = new Town("Clarksburg");
	Town t3 = new Town("Poolesville");
	Town t4 = new Town("Boyds");
	Town t5 = new Town("Germantown");
	Town t6 = new Town("Olney");
	Town t7 = new Town("Darnestown");
	Town t8 = new Town("Gaithersburg");
	Town t9 = new Town("Rockville");
	Town t10 = new Town("Potomac");
	Town t11 = new Town("Bethesda");

	@BeforeEach
	void setUp() throws Exception {
		
	graph.addVertex(t1); graph.addVertex(t2); graph.addVertex(t3); graph.addVertex(t4);
	graph.addVertex(t5); graph.addVertex(t6); graph.addVertex(t7); graph.addVertex(t8);
	graph.addVertex(t9); graph.addVertex(t10); graph.addVertex(t11);
		
	graph.addEdge(t1, t2, 14, "I270-N");
	graph.addEdge(t2, t3, 13, "MD109");
	graph.addEdge(t2, t4, 8, "MD121");
	graph.addEdge(t2, t5,5,"I270-NC");
	graph.addEdge(t2, t6, 14, "MD108");
	graph.addEdge(t3,t4,9, "MD117W");
	graph.addEdge(t3,t7,8,"MD28");
	graph.addEdge(t5, t4,4,"MD117E");
	graph.addEdge(t5, t7, 6, "MD118W");
	graph.addEdge(t5, t8, 6,"I270-C");
	graph.addEdge(t5, t6, 15, "MD118E");
	graph.addEdge(t8, t7, 7, "MD124");
	graph.addEdge(t8, t9, 7, "I270-SC");
	graph.addEdge(t8, t6, 11, "BowieMill");
	graph.addEdge(t7, t10, 8, "MD190W");
	graph.addEdge(t9, t6, 8, "MD97");
	graph.addEdge(t9, t10, 6, "MD189");
	graph.addEdge(t9, t11, 7, "I270-S");
	graph.addEdge(t11, t10, 7, "MD190E");
		
	}

	@AfterEach
	void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testGetEdge() {
		assertEquals(new Road(t1, t2, 14, "I270-N"), graph.getEdge(t1, t2));
		assertEquals(new Road(t5, t6, 15, "MD118E"), graph.getEdge(t5, t6));
	}

	@Test
	public void testAddEdge() {
		assertEquals(false, graph.containsEdge(t3, t10));
		graph.addEdge(t3, t10, 1, "DirtRoad");
		assertEquals(true, graph.containsEdge(t3, t10));
	}

	@Test
	public void testAddVertex() {
		Town newTown = new Town("Chevy-Chase");
		assertEquals(false, graph.containsVertex(newTown));
		graph.addVertex(newTown);
		assertEquals(true, graph.containsVertex(newTown));
	}

	@Test
	public void testContainsEdge() {
		assertEquals(true, graph.containsEdge(t2, t6));
		assertEquals(false, graph.containsEdge(t11, t1));
	}

	@Test
	public void testContainsVertex() {
		assertEquals(true, graph.containsVertex(new Town("Frederick")));
		assertEquals(false, graph.containsVertex(new Town("Baltimore")));
	}

	@Test
	public void testEdgeSet() {
		Set<Road> roads = graph.edgeSet();
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("BowieMill", roadArrayList.get(0));
		assertEquals("I270-C", roadArrayList.get(1));
		assertEquals("I270-N", roadArrayList.get(2));
		assertEquals("I270-NC", roadArrayList.get(3));
		assertEquals("I270-S", roadArrayList.get(4));
		assertEquals("MD118E", roadArrayList.get(10));
	}

	@Test
	public void testEdgesOf() {
		Set<Road> roads = graph.getEdgesOf(t2);
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("I270-N", roadArrayList.get(0));
		assertEquals("I270-NC", roadArrayList.get(1));
		assertEquals("MD108", roadArrayList.get(2));
		assertEquals("MD109", roadArrayList.get(3));
		assertEquals("MD121", roadArrayList.get(4));
	}
	
	@Test
	public void testRemoveEdge() {
		assertEquals(true, graph.containsEdge(t2, t6));
		graph.removeEdge(t2, t6, 14, "MD108");
		assertEquals(false, graph.containsEdge(t2, t6));
	}
	
	@Test
	public void testRemoveVertex() {
		assertEquals(true, graph.containsVertex(t11));
		graph.removeVertex(t11);
		assertEquals(false, graph.containsVertex(t11));
	}

	@Test
	public void testVertexSet() {
		Set<Town> roads = graph.vertexSet();
		assertEquals(true, roads.contains(t1));
		assertEquals(true, roads.contains(t9));
		assertEquals(true, roads.contains(t5));
		assertEquals(true, roads.contains(t4));
		assertEquals(true, roads.contains(t10));
	}

	 @Test
	  public void testFrederick_to_Germantown() {
		  String beginTown = "Frederick", endTown = "Germantown";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Frederick via I270-N to Clarksburg 14 mi",path.get(0).trim());
			  assertEquals("Clarksburg via I270-NC to Germantown 5 mi",path.get(1).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
	  
	  
	  @Test
	  public void testClarksburg_to_Rockville() {
		  String beginTown = "Clarksburg", endTown = "Rockville";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Clarksburg via I270-NC to Germantown 5 mi",path.get(0).trim());
			  assertEquals("Germantown via I270-C to Gaithersburg 6 mi",path.get(1).trim());
			  assertEquals("Gaithersburg via I270-SC to Rockville 7 mi",path.get(2).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
	  
	  @Test
	  public void testBethesda_to_Clarksburg() {
		  String beginTown = "Bethesda", endTown = "Clarksburg";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Bethesda via I270-S to Rockville 7 mi",path.get(0).trim());
			  assertEquals("Rockville via I270-SC to Gaithersburg 7 mi",path.get(1).trim());
			  assertEquals("Gaithersburg via I270-C to Germantown 6 mi",path.get(2).trim());
			  assertEquals("Germantown via I270-NC to Clarksburg 5 mi",path.get(3).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }

}
