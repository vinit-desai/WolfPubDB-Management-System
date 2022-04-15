/**
 * This class is used for returning the status of queries and updates run 
 * against the WolfPubDB database. It contains the following two fields:
 * 	- success (boolean): indicates if SQL operation executed successfully or not
 *	- errorMessage (String): message indicating details of why operation failed (if it was unsuccessful)
 */
import java.sql.*;
import java.lang.StringBuilder;

public class Result {

	/* Instance Fields */
	ResultSet resultSet;
	String errorMessage;

	public Result(ResultSet resultSet, String errorMessage) {
		this.resultSet = resultSet;
		this.errorMessage = errorMessage;
	}

}