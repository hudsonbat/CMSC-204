/**
 * This class implements the Comparable interface and demonstrates a town. It contains a
 * single field, the name of the town, and provides methods that set and get the name
 * as well as methods that compare towns and strings that represent towns. 
 * @author hudson
 */

public class Town implements Comparable<Town> {
	
	private String name;
	
	/**
	 * This constructor builds a town object by setting the name
	 * field to the value passed as a parameter.
	 * @param name The name of the town.
	 */
	
	public Town(String name) {
		this.name = name;
	}
	
	/**
	 * This copy constructor builds a copy of a
	 * town passed as a parameter.
	 * @param t The town to be copied.
	 */
	
	public Town(Town t) {
		this(t.name);
	}
	
	/**
	 * This method generates a hashcode for the town based 
	 * on the name of the town.
	 * @return The hashcode of the town. 
	 */
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	/**
	 * This method returns the name of the town.
	 * @return The name of the town.
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * This method returns the name of the town
	 * in a sentence.
	 * @return The name of the town in a sentence.
	 */
	
	@Override
	public String toString() {
		return "The name of the town is " + name;
	}
	
	/**
	 * This method takes a town as an argument and determines
	 * whether this town and the town passed as an argument
	 * are equal.
	 * @param o The town to be compared.
	 * @return True if the towns are equal and false otherwise.
	 */
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Town)) {
			return false;
		}
		
		Town temp = (Town)o;
		
		if(this.name.equals(temp.name)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This method compares a town object to another town object
	 * passed as a parameter.
	 * @param t The town to be compared.
	 * @return 0 if the towns are equal, -1 if this town is less than the town passed to the method, and
	 * 1 if this town is greater than the town passed to the method.
	 */

	@Override
	public int compareTo(Town t) {
		if(this.equals(t)){
			return 0;
		} else if(this.name.compareTo(t.name) < 0) {
			return -1;
		} else {
			return 1;
		}
	}
	
}
