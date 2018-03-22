package Exceptions; /**
 * 
 */

/**
 * An exception object that is to be thrown when the data format
 * does not meet the requirement.  
 * @author Sylvia Wong
 * @version 30/09/05
 */
public class BadDataFormatException extends Exception {

	/**
	 * Constructor
	 */
	public BadDataFormatException() {
		super("Incompatible data format.");
	}

	/**
	 * Constructor
	 * @param arg0	message carried by this exception 
	 */
	public BadDataFormatException(String arg0) {
		super(arg0);
	}


}
