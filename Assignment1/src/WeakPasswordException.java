/**
 * This class extends the RuntimeException class and represents a WeakPasswordException.
 * @author hudson
 */

public class WeakPasswordException extends RuntimeException {
	
	/**
	 * No-arg constructor
	 */
	
	public WeakPasswordException() {}
	
	/**
	 * Constructor that accepts and passes a message indicating that a password is valid, but weak.
	 * @param message The invalid entry message to be passed to the RuntimeException class constructor.
	 */
	
	public WeakPasswordException(String message) {
		super(message);
	}

}
