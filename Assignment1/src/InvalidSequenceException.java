/**
 * This class extends the RuntimeException class and represents an InvalidSequenceException.
 * @author hudson
 */

public class InvalidSequenceException extends RuntimeException {
	
	/**
	 * No-Arg Constructor.
	 */
	
	public InvalidSequenceException() {}
	
	/**
	 * Constructor that accepts and passes an invalid sequence message to the superclass.
	 * @param message The invalid sequence message to be passed to the RuntimeException class constructor.
	 */
	
	public InvalidSequenceException(String message) {
		super(message);
	}

}
