/**
 * This class extends the RuntimeException class and is meant to
 * indicate the presence of a queue overflow exception. 
 * @author hudson
 *
 */
public class QueueOverflowException extends RuntimeException {
	
	/**
	 * No-arg constructor.
	 */
	public QueueOverflowException() {
	}
	
	/**
	 * This constructor accepts a string as a parameter 
	 * and passes the string to the superclass constructor.
	 * @param message The queue overflow exception message.
	 */
	
	public QueueOverflowException(String message) {
		super(message);
	}

}
