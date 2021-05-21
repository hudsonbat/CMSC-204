/**
 * This class extends the RuntimeException class and is meant
 * to indicate the presence of a stack underflow exception.
 * @author hudson
 */

public class StackUnderflowException extends RuntimeException {
	
	/**
	 * No-arg constructor
	 */
	
	public StackUnderflowException() {}
	
	/**
	 * This constructor accepts a string as a parameter
	 * and passes this string to the superclass constructor.
	 * @param message The stack underflow exception message.
	 */
	
	public StackUnderflowException(String message) {
		super(message);
	}

}
