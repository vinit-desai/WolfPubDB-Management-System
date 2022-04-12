public class EmployeePaymentsReport {

	public static void execute(String startDate, String endDate) {
		
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
		
		WolfPubDB.executeQuery(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for EmployeePaymentsReport");
		System.out.println("====================================");
		execute("2022-01-01", "2022-12-31");
	}

}