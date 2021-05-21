/**
 * This class implements the Comparable interface and represents a Road. It provides fields for the 
 * name of the road, the two towns that the road begins and ends at, as well as the distance between 
 * the two towns. In addition to the getter and setter methods for retrieving and setting the data in these fields, 
 * the class also provides an overriden equals and compareTo method to determine whether two towns are equal and a 
 * toString method to give the name of the road, the towns it connects, and the distance between them.
 * @author hudson
 */

public class Road implements Comparable<Road> {
	
	private Town beg;
	private Town end;
	private int weight;
	private String roadName;
	
	/**
	 * This constructor builds a road object by setting the beg, end, 
	 * weight, and roadName fields to the corresponding arguments 
	 * passed as parameters. 
	 * @param source The town that the road begins from.
	 * @param destination The town that the road ends at.
	 * @param degrees The distance between the source and destination.
	 * @param name The name of the road.
	 */
	
	public Road(Town source, Town destination, int degrees, String name) {
		beg = source;
		end = destination;
		weight = degrees;
		roadName = name;
	}
	
	/**
	 * This constructor builds a road object by setting the beg, end, 
	 * and roadName fields to the corresponding arguments passed as 
	 * parameters. 
	 * passed as parameters. 
	 * @param source The town that the road begins from.
	 * @param destination The town that the road ends at.
	 * @param name The name of the road.
	 */
	
	public Road(Town source, Town destination, String name) {
		beg= source;
		end= destination;
		roadName = name;
		weight = 1;
	}
	
	/**
	 * This method determines whether the road contains the town 
	 * passed as a parameter.
	 * @param town The town in question.
	 * @return True if the road does contain the town and false otherwise.
	 */
	
	public boolean contains(Town town) {
		return (this.beg == town) || (this.end == town);
	}
	
	/**
	 * This method returns the town that the road begins
	 * from.
	 * @return The town that the road begins from.
	 */

	public Town getSource() {
		return beg;
	}
	
	/**
	 * This method sets the town that the road begins
	 * from to the town passed as a parameter.
	 * @param beg The town that the road is to begin from.
	 */

	public void setSource(Town beg) {
		this.beg = beg;
	}
	
	/**
	 * This method returns the town that the road ends
	 * at.
	 * @return The town that the road ends at.
	 */

	public Town getDestination() {
		return end;
	}
	
	/**
	 * This method sets the town that the road ends
	 * at to the town passed as a parameter.
	 * @param end The town that the road is to end at.
	 */

	public void setDestination(Town end) {
		this.end = end;
	}
	
	/**
	 * This method returns the distance between the source
	 * and destination of the road.
	 * @return The distance of the road.
	 */

	public int getWeight() {
		return weight;
	}
	
	/**
	 * This method sets the weight/distance of the road to the 
	 * weight passed as a parameter.
	 * @param weight The distance of the road.
	 */

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	/**
	 * This method returns the name of the road. 
	 * @return The name of the road.
	 */

	public String getName() {
		return roadName;
	}
	
	/**
	 * This method sets the name of the road to 
	 * the name passed as a parameter.
	 * @param roadName The name of the road.
	 */

	public void setName(String roadName) {
		this.roadName = roadName;
	}
	
	/**
	 * This method determines whether two towns passed as 
	 * parameters are connected by a road.
	 * @param t1 The first town.
	 * @param t2 The second town.
	 * @return True if they are connected by a road and false otherwise.
	 */
	
	public boolean connects(Town t1, Town t2) {
		return (t1.equals(beg) || t1.equals(end)) && (t2.equals(beg) || t2.equals(end));
	}
	
	/**
	 * This method returns a string with the source and destination connected by the road, 
	 * the distance of the road, and the name of the road.
	 * @return The source and destination connected by the road, the distance of the
	 * road, and the name of the road.
	 */
	
	@Override
	public String toString() {
		return beg.getName() + " via " + roadName + " to " + end.getName() + 
				" " + weight + " mi";
	}
	
	/**
	 * This method determines whether two road objects are equal
	 * by accepting a road object as a parameter.
	 * @param r The road object to be compared.
	 * @return True if the roads are the same and false otherwise.
	 */
	
	@Override
	public boolean equals(Object r) {
		if(!(r instanceof Road)) {
			return false;
		}
		
		Road temp = (Road)r;
		
		if(this.beg.getName().equals(temp.beg.getName()) && 
				this.end.getName().equals(temp.end.getName())) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * This method compares a road object to another road object
	 * passed as a parameter.
	 * @param o The road to be compared.
	 * @return 0 if the roads are equal, -1 if this road is less than the road passed to the method, and
	 * 1 if this road is greater than the road passed to the method.
	 */

	@Override
	public int compareTo(Road o) {
		
		if(o.getName().equals(this.roadName)) {
			return 0;
		} else if (this.roadName.compareTo(o.getName()) < 0) {
			return -1;
		} else {
			return 1;
		}
		
	}

}
