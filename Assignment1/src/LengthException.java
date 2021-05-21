/**
 * This class extends the RuntimeException class and represents a LengthException.
 * @author hudson
 */

public class LengthException extends RuntimeException {
	
	/**
	 * No-arg constructor
	 */
	
	public LengthException() {}
	
	/**
	 * Constructor that accepts and passes an invalid length message to the superclass.
	 * @param message The invalid length message to be passed to the RuntimeException class constructor.
	 */
	
	public LengthException(String message) {
		super(message);
	}

}
