public class LocationRevenueReport {

	public static ExecResult execute() {
		String sql = 
			"SELECT  Address.StreetAddress AS Location, " + "\n" +
			"		COALESCE(TotalRevenue, 0) AS TotalRevenue" + "\n" +
			"FROM Address" + "\n" +
			"LEFT JOIN (" + "\n" +

			"	SELECT  StreetAddress, " + "\n" +
			"			SUM(Amount) AS TotalRevenue" + "\n" +
			"	FROM Distributor" + "\n" +
			"	NATURAL JOIN Bills" + "\n" +
			"	NATURAL JOIN Transaction" + "\n" +
			"	WHERE Paid = True" + "\n" +
			"	GROUP BY 1" + "\n" +
				
			") AS AddressRevenue" + "\n" +
			"	ON Address.StreetAddress = AddressRevenue.StreetAddress" + "\n" +
			"ORDER BY 1;" + "\n"
		;
		return WolfPubDB.executeQuery(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for LocationRevenueReport");
		System.out.println("===================================");
		ExecResult execResults = execute();
		if (execResults.success) {
			System.out.println("LocationRevenueReport: Success");
		} else {
			System.out.println("LocationRevenueReport: Failure");
			System.out.println("\tError: " + execResults.errorMessage);
		}
		System.out.println("\n");
	}

}