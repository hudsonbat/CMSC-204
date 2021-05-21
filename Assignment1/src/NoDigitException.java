/**
 * This class extends the RuntimeException class and represents a NoDigitException.
 * @author hudson
 *
 */

public class NoDigitException extends RuntimeException {
	
	/**
	 * No-arg constructor
	 */
	
	public NoDigitException() {}
	
	/**
	 * Constructor that accepts and passes a message indicating the absence of a digit to the superclass.
	 * @param message The invalid entry message to be passed to the RuntimeException class constructor. 
	 */
	
	public NoDigitException(String message) {
		super(message);
	}
	

}
