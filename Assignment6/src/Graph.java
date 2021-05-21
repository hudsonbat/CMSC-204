/**
 * This class represents a Graph by implementing the GraphInterface interface.
 * It contains method for adding, removing, retrieving and searching the 
 * edges and vertices of a graph, as well as finding the shortest weighted
 * path between any two given vertices.
 * @author hudson
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Set;


public class Graph implements GraphInterface<Town,Road> {
	
	private LinkedList<Town> towns;
	private LinkedList<Road> roads;
	private Hashtable<Town, LinkedList<Road>> graph;
	private Hashtable<Town, LinkedList<Town>> adjList;
	private int vertexCount;
	private LinkedList<Road> edges;
	
	/**
	 * No-arg constructor
	 */
	
	public Graph() {
		graph = new Hashtable<>(20);
		adjList = new Hashtable<>();
		roads = new LinkedList<>();
		towns = new LinkedList<>();
		vertexCount=0;
	}
	
	/**
	* Returns an edge connecting source vertex to target vertex if such
    * vertices and such edge exist in this graph. Otherwise returns
    * null. If any of the specified vertices is null
    * returns null
    *
    * In undirected graphs, the returned edge may have its source and target
    * vertices in the opposite order.
    *
    * @param sourceVertex source vertex of the edge.
    * @param destinationVertex target vertex of the edge.
    * @return an edge connecting source vertex to target vertex.
    */
	
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		
		LinkedList<Road> r = graph.get(sourceVertex);
		int j = 0;
		
		Road result = null;
		
		if(r != null) {
			while(j < r.size() && result == null) {
				if(r.get(j).getDestination().equals(destinationVertex) && 
						r.get(j).getSource().equals(sourceVertex)) {
					result = r.get(j);
					}
				j++;
				}
			}
		
		return result;
	}
	
	/**
	 * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * 
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
	 */

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road r = new Road(sourceVertex,destinationVertex, weight,description);
		
		if(graph.containsKey(sourceVertex)) {
			graph.get(sourceVertex).add(r);
			if(adjList.containsKey(sourceVertex)) {
				adjList.get(sourceVertex).add(destinationVertex);
			} else {
				adjList.put(sourceVertex, new LinkedList<Town>());
				adjList.get(sourceVertex).add(destinationVertex);
			}
		} else {
			graph.put(sourceVertex, new LinkedList<Road>());
			graph.get(sourceVertex).add(r);
		}
		
		roads.add(r);
		return r;
	}
	
	/**
	 * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
	 */

	@Override
	public boolean addVertex(Town v) throws NullPointerException {
		
		if(this.containsVertex(v)) {
			return false;
		} else {
			towns.add(v);
			graph.put(v, new LinkedList<Road>());
		}
		vertexCount++;
		
		return true;
	}
	
	/**
	 * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
	 */

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		
		LinkedList<Road> r = graph.get(sourceVertex);
		int j = 0;
		
		boolean found = false;;
		
		if(r != null) {
			while(j < r.size()) {
				if(r.get(j).getDestination().equals(destinationVertex) &&
						r.get(j).getSource().equals(sourceVertex)) {
					found = true;
					}
				j++;
				}
			}
		
		
		return found;
	}
	
	/**
	 * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
	 */

	@Override
	public boolean containsVertex(Town v) {
		
		LinkedList<Road> r = graph.get(v);
		
		if(r != null) {
			return true;
		} else {
			return false;
		}

	}
	
	/**
	 * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
	 */

	@Override
	public Set<Road> edgeSet() {
		
		Set<Road> edgeSet = new HashSet<Road>();
		
		for(Road r : roads) {
			edgeSet.add(r);
		}
		
		return edgeSet;
	}
	
	/**
	 * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
	 */
	

	@Override
	public Set<Road> edgesOf(Town vertex) {
		
		Set<Road> edgeSet = new HashSet<Road>();
		
		LinkedList<Road> roadList = graph.get(vertex);
		
		for(Road r : roadList) {
			edgeSet.add(r);
		}
		
		return edgeSet;
	}
	
	/**
	 * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight greater than -1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
	 */

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		
		Road r = new Road(sourceVertex, destinationVertex, weight, description);
		
		LinkedList<Road> rds = new LinkedList<Road>();
		int i = 0;
		boolean found = false;
		
		if(graph.containsKey(sourceVertex)) {
			rds = graph.get(sourceVertex);
			while(!found && i < rds.size()) {
				if(rds.get(i).equals(r)) {
					graph.get(sourceVertex).remove(i);
					found = true;
				}
				i++;
			}
		}
					
		if(!found) {
			return null;
		} else {
			return r;
		}
		
	}
	
	/**
	 * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
	 */

	@Override
	public boolean removeVertex(Town v) {
		
		int index = 0;
		boolean found = false;
		
		if(!this.containsVertex(v)) {
			return false;
		} else {
			graph.remove(v);
			while(index < towns.size() && !found) {
				if(towns.get(index).equals(v)) {
					towns.remove(index);
					found = true;
				}
				index++;
			}
			
		}
		vertexCount--;
		
		return true;
	}
	
	/**
	 * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
	 */

	@Override
	public Set<Town> vertexSet() {
		
		Set<Town> vertexSet = new HashSet<Town>();
		
		for(Town t : towns) {
			vertexSet.add(t);
		}
		
		return vertexSet;
	}
	
	/**
	 * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 */

	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		Town parent = null;
		Road edge = null;
		int i = 0;
		
		dijkstraShortestPath(sourceVertex);
		ArrayList<String> shortestPath = new ArrayList<String>();
		
		Town current = destinationVertex;
		
		while(!current.equals(sourceVertex) && i < edges.size()) {
			if(getEdgesOf(current).isEmpty()) {
				shortestPath.clear();
				return shortestPath;
			} else {
				
				for(Road r : edges) {
					if(r.getDestination().equals(current)) {
						parent = r.getSource();
						edge = r;
					}
				}
				
				shortestPath.add(edge.toString());
				current = parent;
				i++;
			}
		}
		
		Collections.reverse(shortestPath);
		
		return shortestPath;
		
	}
	
	/**
	 * This method returns the edges in any direction from the
	 * given Town supplied as an argument.
	 * @param vertex The vertex for which all adjacent edges are returned.
	 * @return The edges adjacent to this vertex in any direction.
	 */
	
	
	public Set<Road> getEdgesOf(Town vertex) {
		Set<Road> edges = new HashSet<>();
		
		for(Road r : roads) {
			if(r.getDestination().equals(vertex) || r.getSource().equals(vertex)) {
				edges.add(r);
			}
		}
		
		return edges;
	}
	
	/**
	 * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
	 */

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		
		Hashtable<Town, Integer> distance = new Hashtable<>();
		LinkedList<Town> unvisited = new LinkedList<>();
		Set<Town> visited = new HashSet<>();
		edges = new LinkedList<>();
		boolean done = false;
		
		int lowestIndex;
		int adjCost;
		Town lowestCost;
		Town neighbor;
		
		for(Town town : towns) {
			if(town.equals(sourceVertex)) {
				distance.put(sourceVertex, 0);
			} else {
				distance.put(town, 10000);
			}
			unvisited.add(town);
		}
		
		while(!done && !unvisited.isEmpty()) {
			lowestIndex = 0;
			
			for(int i=1; i < unvisited.size(); i++) {
				if(distance.get(unvisited.get(i)) < distance.get(unvisited.get(lowestIndex))){
					lowestIndex=i;
				}
			}
			
			lowestCost = unvisited.remove(lowestIndex);
			visited.add(lowestCost);
			
			if(distance.get(lowestCost) == 10000) {
				done = true;
			} else {
				
				for(Road edge : getEdgesOf(lowestCost)) {
					if(edge.getDestination().equals(lowestCost)) {
						neighbor = edge.getSource();
					} else {
						neighbor = edge.getDestination();
					}
					
					if(!visited.contains(neighbor)) {
						adjCost = distance.get(lowestCost) + edge.getWeight();
						
						if(adjCost < distance.get(neighbor)) {
							if(getEdge(lowestCost,neighbor) == null) {
								edge = getEdge(neighbor, lowestCost);
								edge.setSource(lowestCost);
								edge.setDestination(neighbor);
							} else {
								edge = getEdge(lowestCost,neighbor);
							}
							edges.add(edge);
							distance.put(neighbor, adjCost);
						}
					}
				}
			}
		}	
	}
}
