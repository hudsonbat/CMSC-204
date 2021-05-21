/**
 * This class extends the RuntimeException class and represents a NoSpecialCharacterException.
 * @author hudson
 */

public class NoSpecialCharacterException extends RuntimeException {
	
	/**
	 * No-arg constructor
	 */
	
	public NoSpecialCharacterException() {}
	
	/**
	 * Constructor that accepts and passes a message indicating the absence of a special character to the superclass.
	 * @param message The invalid entry message to be passed to the RuntimeException class constructor.
	 */
	
	public NoSpecialCharacterException(String message) {
		super(message);
	}

}
