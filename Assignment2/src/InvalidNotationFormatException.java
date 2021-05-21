/**
 * This class extends the RuntimeException class and is meant to 
 * indicate the occurrence of an invalid notation format exception.
 * @author hudson
 */
public class InvalidNotationFormatException extends RuntimeException {
	
	/**
	 * No-arg constructor.
	 */
	
	public InvalidNotationFormatException() {}
	
	/**
	 * This constructor accepts a string as a parameter 
	 * and passes the string to the superclass constructor.
	 * @param message The invalid notation format exception message.
	 */
	
	public InvalidNotationFormatException(String message) {
		super(message);
	}

}
