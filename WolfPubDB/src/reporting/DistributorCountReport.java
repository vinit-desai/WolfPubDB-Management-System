import java.util.Scanner;

/**
 * Class used for executing the DistributorCountReport API operation.
 */
public class DistributorCountReport {

	public static ExecResult run(Scanner reader) {
		return execute();
	}

	public static ExecResult execute() {
		String sql = 
			"SELECT COUNT(*) AS DistributorCount FROM Distributor;" + "\n"
		;
		return WolfPubDB.executeQuery(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for DistributorCountReport");
		System.out.println("====================================");
		ExecResult execResults = execute();
		if (execResults.success) {
			System.out.println("DistributorCountReport: Success");
		} else {
			System.out.println("DistributorCountReport: Failure");
			System.out.println("\tError: " + execResults.errorMessage);
		}
		System.out.println("\n");
	}

}