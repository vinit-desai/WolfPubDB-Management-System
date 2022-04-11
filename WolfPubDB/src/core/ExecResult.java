/**
 * This class is used for returning the status of queries and updates run 
 * against the WolfPubDB database. It contains the following two fields:
 * 	- success (boolean): indicates if SQL operation executed successfully or not
 *	- errorMessage (String): message indicating details of why operation failed (if it was unsuccessful)
 */
public class ExecResult {

	/* Instance Fields */
	boolean success;
	String errorMessage;

	public ExecResult(boolean success, String errorMessage) {
		this.success = success;
		this.errorMessage = errorMessage;
	}

}