/**
 * This class extends the RuntimeException class and is meant
 * to indicate the presence of a queue underflow exception.
 * @author hudson
 */

public class QueueUnderflowException extends RuntimeException {
	
	/**
	 * No-arg constructor.
	 */
	
	public QueueUnderflowException() {}
	
	/**
	 * This constructor accepts a string as a 
	 * parameter and passes this string to
	 * the superclass constructor.
	 * @param message The queue underflow exception message.
	 */
	
	public QueueUnderflowException(String message) {
		super(message);
		
	}

}
