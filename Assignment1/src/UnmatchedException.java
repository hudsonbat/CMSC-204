/**
 * This class extends the RuntimeException class and represents an UnmatchedException.
 * @author hudson
 */

public class UnmatchedException extends RuntimeException {
	
	/**
	 * No-arg constructor
	 */
	
	public UnmatchedException() {}
	
	/**
	 * Constructor that accepts and passes a message indicating the inequality of two passwords to the superclass.
	 * @param message The invalid entry message to be passed to the RuntimeException class constructor.
	 */
	
	public UnmatchedException(String message) {
		super(message);
	}

}
