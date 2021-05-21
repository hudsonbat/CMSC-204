/**
 * This class extends the RuntimeException class and represents a NoUpperAlphaException.
 * @author hudson
 */

public class NoUpperAlphaException extends RuntimeException {
	
	/**
	 * No-arg constructor
	 */
	
	public NoUpperAlphaException() {}
	
	
	/**
	 * Constructor that accepts and passes a message indicating the absence of a uppercase letter to the superclass.
	 * @param message The invalid entry message to be passed to the RuntimeException class constructor.
	 */
	
	public NoUpperAlphaException(String message) {
		super(message);
	}

}
