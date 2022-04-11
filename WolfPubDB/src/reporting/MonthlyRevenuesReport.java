/**
 * Class used for executing the MonthlyRevenuesReport API operation.
 */
public class MonthlyRevenuesReport {

	public static ExecResult execute() {
		String sql = 
			"SELECT  YEAR(PaymentDate) AS Year, MONTH(PaymentDate) AS Month," + "\n" +
			"SUM(Amount) AS TotalRevenue" + "\n" +
			"FROM Bills" + "\n" +
			"NATURAL JOIN Transaction" + "\n" +
			"WHERE Paid = True" + "\n" +
			"GROUP BY 1,2" + "\n" +
			"ORDER BY 1,2;" + "\n"
		;
		return WolfPubDB.executeQuery(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for MonthlyRevenuesReport");
		System.out.println("===================================");
		ExecResult execResults = execute();
		if (execResults.success) {
			System.out.println("MonthlyRevenuesReport: Success");
		} else {
			System.out.println("MonthlyRevenuesReport: Failure");
			System.out.println("\tError: " + execResults.errorMessage);
		}
		System.out.println("\n");
	}

}