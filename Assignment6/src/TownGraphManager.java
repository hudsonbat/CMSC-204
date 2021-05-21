/**
 * This class represents a town graph manager by implementing the TownGraphManager interface.
 * It contain methods for adding, removing, retrieving, and searching the Towns and Roads contained
 * in a given graph. It also contains methods that return the shortest route between any two
 * given towns. 
 * @author hudson
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class TownGraphManager implements TownGraphManagerInterface {
	
	Graph g = new Graph();
	Town t1;
	Town t2;
	Town town;
	Road r;
	String name;
	LinkedList<Road> roads;
	LinkedList<Town> towns;
	ArrayList<String> roadList;
	ArrayList<String> townList;
	int weight = 0;
	
	/**
	 * Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */

	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		t1 = new Town(town1);
		t2 = new Town(town2);
		r = g.addEdge(t1, t2, weight, roadName);
		
		if(r != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */

	@Override
	public String getRoad(String town1, String town2) {
		t1 = new Town(town1);
		t2 = new Town(town2);
		r = g.getEdge(t1, t2);
		
		return r.getName();
	}
	
	/**
	 * Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */

	@Override
	public boolean addTown(String v) {
		t1 = new Town(v);
		return g.addVertex(t1);
	}
	
	/**
	 * Gets a town with a given name
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */

	@Override
	public Town getTown(String name) {
		
		t1 = new Town(name);
		
		town = null;
		
		for(Town t : g.vertexSet()) {
			if(t.equals(t1)) {
				town = t;
			}
		}
		
		return town;	
	}
	
	/**
	 * Determines if a town is already in the graph
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */

	@Override
	public boolean containsTown(String v) {
		
		t1 = new Town(v);
		
		return g.containsVertex(t1);
		
	}
	
	/**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		t1 = new Town(town1);
		t2 = new Town(town2);
		
		return g.containsEdge(t1, t2);
	}
	
	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */

	@Override
	public ArrayList<String> allRoads() {
		
		roads = new LinkedList<Road>();
		
		roadList = new ArrayList<>();
		
		for(Road r : g.edgeSet()) {
			roads.add(r);
		}
		
		for(Road r : roads) {
			roadList.add(r.getName());
		}
		
		Collections.sort(roadList);
		
		return roadList;
	}
	
	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param road the road name
	 * @return true if the road was successfully deleted, false if not
	 */

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		
		t1 = new Town(town1);
		t2 = new Town(town2);
		
		r = g.getEdge(t1, t2);
		weight = r.getWeight();
		
		g.removeEdge(t1, t2, weight, road);
		
		return true;
	}
	
	/**
	 * Deletes a town from the graph
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */

	@Override
	public boolean deleteTown(String v) {
		
		boolean value = false;
		
		t1 = new Town(v);
		
		value = g.removeVertex(t1);
		
		return value;
	}
	
	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */

	@Override
	public ArrayList<String> allTowns() {
		
		towns = new LinkedList<Town>();
		
		townList = new ArrayList<>();
		
		for(Town t : g.vertexSet()) {
			towns.add(t);
		}
		
		for(Town t : towns) {
			townList.add(t.getName());
		}
		
		Collections.sort(townList);
		
		return townList;
	}
	
	/**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		
		t1 = new Town(town1);
		t2 = new Town(town2);
		
		return g.shortestPath(t1, t2);
	}
	
	/**
	 * This method creates a town graph based on a file. 
	 * @param selectedFile The file from which the town graph is created.
	 * @throws FileNotFoundException If the file cannot be found.
	 * @throws IOException If the file has some other checked IO exception.
	 */

	public void populateTownGraph(File selectedFile) throws FileNotFoundException, IOException {
		
		String source = "";
		String destination = "";
		
		try {
		Scanner scan = new Scanner(selectedFile);
		String [] str = new String[4];
		
		while(scan.hasNext()) {
			str = scan.nextLine().split("[,;]+");
			source = str[2];
			destination = str[3];
			weight = Integer.parseInt(str[1]);
			name = str[0];
			addTown(source);
			addTown(destination);
			addRoad(source, destination, weight, name);
			}
		scan.close();
		} catch(FileNotFoundException e) {
			e.getMessage();
		} catch(IOException e) {
			e.getMessage();
		} 
	
	}

}
