public class Queries{

    public static void createPublication(int publicationID, String type, String publicationDate, String topic, String ISBNType, String title, int editionIssue, String creationDate) {
		
        String sql = 
			"INSERT INTO Publication VALUES "  + "\n" + "\t" +
				"(%s, '%s', '%s', '%s')"  + "\n" +
			";" + "\n" + "\n"
		;
        
        sql = String.format(sql, publicationID, type, publicationDate, topic);
        
        WolfPubDB.executeUpdate(sql);
        
        if(type.equals("Book")){
            sql = "INSERT INTO Book VALUES " + "\n" + "\t" +
				"(%s, '%s', '%s', %s, '%s')"  + "\n" +
			";" + "\n" + "\n"
        ;
        }
        else{
            sql = "INSERT INTO Periodical VALUES " + "\n" + "\t" +
				"(%s, '%s', '%s', %s, '%s')"  + "\n" +
			";" + "\n" + "\n"
		;
        }
        
        sql = String.format(sql, publicationID, ISBNType, title, editionIssue, creationDate);
		
        WolfPubDB.executeUpdate(sql);
	}

    public static void assignEditor(int publicationID, int contributorID) {
		
        String sql = 
			"INSERT INTO Edits VALUES "  + "\n" + "\t" +
				"(%s,%s)"  + "\n" +
			";" + "\n" + "\n"
		;
        
        sql = String.format(sql, publicationID, contributorID);
        
        WolfPubDB.executeUpdate(sql);
	}

    public static void viewPublication(int publicationID) {
		
		String sql = 
			"SELECT * FROM Publication" + "\n" +
			"WHERE PublicationID = %s;"
		;
        
		sql = String.format(sql, publicationID);
		
		WolfPubDB.executeQuery(sql);
	}

    public static void addBookChapter(int publicationID, int chapterNumber, String title, String text) {
		
		String sql = 
			"INSERT INTO Chapter VALUES "  + "\n" + "\t" +
				"(%s, %s, '%s', '%s')"  + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, publicationID, chapterNumber, title, text);
        
		WolfPubDB.executeUpdate(sql);
	}

    public static void deleteBookChapter(int publicationID, int chapterNumber) {

		String sql = 
			"DELETE FROM Chapter"  + "\n" +
            "WHERE PublicationID = %s AND ChapterNumber = %s"  + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, publicationID, chapterNumber);
        
		WolfPubDB.executeUpdate(sql);
	}

    public static void addArticle(int publicationID, int sequenceNumber, String title, String creationDate, String text) {

		String sql = 
			"INSERT INTO Article VALUES "  + "\n" + "\t" +
				"(%s, %s, '%s', '%s', '%s')"  + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, publicationID, sequenceNumber, title, creationDate, text);
        
		WolfPubDB.executeUpdate(sql);
	}

    public static void deleteArticle(int publicationID, int sequenceNumber) {

		String sql = 
			"DELETE FROM Article "  + "\n" +
            "WHERE PublicationID = %s AND SequenceNumber = %s"  + "\n" +
			";" + "\n" + "\n"
		;

        sql = String.format(sql, publicationID, sequenceNumber);

        WolfPubDB.executeUpdate(sql);
	}

    public static void monthlyOrdersReport() {
		
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

    public static void monthlyRevenuesReport() {
		
		String sql = 
			"SELECT  YEAR(PaymentDate) AS Year, MONTH(PaymentDate) AS Month," + "\n" +
			"SUM(Amount) AS TotalRevenue" + "\n" +
			"FROM Bills" + "\n" +
			"NATURAL JOIN Transaction" + "\n" +
			"WHERE Paid = True" + "\n" +
			"GROUP BY 1,2" + "\n" +
			"ORDER BY 1,2;" + "\n"
		;
		
		WolfPubDB.executeQuery(sql);
	}

    public static void monthlyExpensesReport() {
		
		String sql = 
			"SELECT  Year, Month," + "\n" +
			"		SUM(TotalWages) AS TotalWages, " + "\n" +
			"		SUM(TotalShippingCosts) AS TotalShippingCosts" + "\n" +
			"FROM (" + "\n" +
			"	SELECT  YEAR(IssueDate) AS Year, MONTH(IssueDate) AS Month, " + "\n" +
			"			SUM(Amount) AS TotalWages , SUM(0.00) AS TotalShippingCosts" + "\n" +
			"	FROM Wages" + "\n" +
			"	NATURAL JOIN Transaction" + "\n" +
			"	GROUP BY 1,2" + "\n" +
			"		UNION ALL" + "\n" +
			"	SELECT  YEAR(OrderDate) AS Year, MONTH(OrderDate) AS Month, " + "\n" +
			"			SUM(0.00) AS TotalWages, SUM(Amount) AS TotalShippingCosts" + "\n" +
			"	FROM Orders" + "\n" +
			"	NATURAL JOIN Transaction" + "\n" +
			"	WHERE Status IN('Shipped', 'Delivered')" + "\n" +
			"	GROUP BY 1,2" + "\n" +
			") AS Expenses" + "\n" +
			"GROUP BY 1,2" + "\n" +
			"ORDER BY 1,2;" + "\n"
		;
		
		WolfPubDB.executeQuery(sql);
	}

    public static void distributorCountReport() {
		
		String sql = 
			"SELECT COUNT(*) AS DistributorCount FROM Distributor;" + "\n"
		;
		
		WolfPubDB.executeQuery(sql);
	}

    public static void distributorRevenueReport() {
		
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

    public static void locationRevenueReport() {
		
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
		
		WolfPubDB.executeQuery(sql);
	}

    public static void employeePaymentsReport(String startDate, String endDate) {
		
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

}