/**
 * This class extends the RuntimeException class and represents a NoLowerAlphaException.
 * @author hudson
 */

public class NoLowerAlphaException extends RuntimeException {
	
	/**
	 * No-arg constructor
	 */
	
	public NoLowerAlphaException() {}
	
	/**
	 * Constructor that accepts and passes a message indicating the absence of a lowercase letter to the superclass.
	 * @param message The invalid entry message to be passed to the RuntimeException class constructor.
	 */
	
	public NoLowerAlphaException(String message) {
		super(message);
	}

}
