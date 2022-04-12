import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * Class used for executing the EmployeePaymentsReport API operation.
 */
public class EmployeePaymentsReport {

	public static ExecResult run(Scanner reader) {
		System.out.println("");
		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.print("Start Date (YYYY-MM-DD): ");
		String startDate = reader.nextLine();
		
		System.out.print("End Date (YYYY-MM-DD): ");
		String endDate = reader.nextLine();

		System.out.println("");

		return execute(startDate, endDate);
	}

	public static ExecResult execute(String startDate, String endDate) {
		String sql = 
			"SELECT  Type AS WorkType," + "\n" +
			"		SUM(Amount) AS ContributorPayments" + "\n" +
			"FROM Wages" + "\n" +
			"NATURAL JOIN Transaction" + "\n" +
			"WHERE IssueDate >= '%s' AND IssueDate <='%s'" + "\n" +
			"GROUP BY 1" + "\n" +
			"ORDER BY 1;" + "\n"
		;
		sql = String.format(sql, startDate, endDate);
		// System.out.println(sql);
		return WolfPubDB.executeQuery(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for EmployeePaymentsReport");
		System.out.println("====================================");
		ExecResult execResults = execute("2022-01-01", "2022-12-31");
		if (execResults.success) {
			System.out.println("EmployeePaymentsReport: Success");
		} else {
			System.out.println("EmployeePaymentsReport: Failure");
			System.out.println("\tError: " + execResults.errorMessage);
		}
		System.out.println("\n");
	}

}