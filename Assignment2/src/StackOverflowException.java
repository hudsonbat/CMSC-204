/**
 * This class extends the RuntimeException class and is meant 
 * to indicate the presence of a stack overflow exception.
 * @author hudson
 */
public class StackOverflowException extends RuntimeException {
	
	/**
	 * No-arg constructor.
	 */
	
	public StackOverflowException() {}
	
	/**
	 * This constructor accepts a string as a parameter
	 * and passes the string to the superclass constructor.
	 * @param message The stack over flow exception message.
	 */
	
	public StackOverflowException(String message) {
		super(message);
	}

}
