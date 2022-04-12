public class MonthlyOrdersReport {

	public static void execute() {
		
		String sql = 
			"SELECT  YEAR(OrderDate) AS Year, MONTH(OrderDate) AS Month," + "\n" +
			"DistributorID, PublicationID," + "\n" +
			"SUM(Units) AS TotalUnits, SUM(Units*PricePerUnit) AS TotalPrice" + "\n" +
			"FROM Orders" + "\n" +
			"GROUP BY 1,2,3,4" + "\n" +
			"ORDER BY 1,2,3,4;" + "\n"
		;
		
		WolfPubDB.executeQuery(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for MonthlyOrdersReport");
		System.out.println("=================================");
		execute();
	}

}