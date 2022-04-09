public class DistributorRevenueReport {

	public static void execute() {
		String sql = 
			"SELECT  Distributor.DistributorID AS DistributorID, " + "\n" +
			"		COALESCE(TotalRevenue, 0) AS TotalRevenue" + "\n" +
			"FROM Distributor" + "\n" +
			"LEFT JOIN (" + "\n" +

			"	SELECT  DistributorID,"  + "\n" +
			"			SUM(Amount) AS TotalRevenue" + "\n" +
			"	FROM Bills" + "\n" +
			"	NATURAL JOIN Transaction" + "\n" +
			"	WHERE Paid = True" + "\n" +
			"	GROUP BY 1" + "\n" +
				
			") AS Revenues" + "\n" +
			"	ON Distributor.DistributorID = Revenues.DistributorID" + "\n" +
			"ORDER BY 1;" + "\n"
		;
		WolfPubDB.executeQuery(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for DistributorRevenueReport");
		System.out.println("======================================");
		execute();
	}

}