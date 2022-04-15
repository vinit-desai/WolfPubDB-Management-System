import java.util.Scanner;
import java.sql.*;
import java.lang.StringBuilder;

public class CreateOrder {

    private static final String user = "nkotche";												// username
	private static final String password = "Adder0108&";											// password
	private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/"+user;

    private static Connection connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.mariadb.jdbc.Driver");
		return DriverManager.getConnection(jdbcURL, user, password);
	}

    public static float getResults(int distributorID){

        ResultSet resultSet = null;
        float balance = 0.0f;

        String sql = "SELECT * FROM Distributor WHERE DistributorID = %d"  + "\n" + "\t" + "\n" +
			";" + "\n" + "\n";

        sql = String.format(sql,distributorID);

        try (Connection connection = connect()) {

			try (Statement statement = connection.createStatement()) {
				WolfPubDB.executeUpdate(sql);

                resultSet = statement.executeQuery(sql);

                while(resultSet.next()){
                    balance = resultSet.getFloat("Balance");
                }

			} catch (SQLException error) {
				return 0.0f;
			}

		} catch (ClassNotFoundException | SQLException e) {

			String errorMsg = "Unable to Connect Using jdbcURL: " + jdbcURL;
			return 0.0f;

		}

        return balance;
        
    }

     public static ExecResult execute(int orderID, int distributorID, int publicationID, int numberOfUnits, float pricePerUnit, String orderDate, float shippingCost, String orderStatus, int transactionID, float transactionAmount, float totalBalance) {

        ExecResult result = null;

        String sql = "INSERT INTO Transaction VALUES "  + "\n" + "\t" +
				"(%d , %.2f , '%s')"  + "\n" +
			";" + "\n" + "\n";
        
		sql = String.format(sql, transactionID, transactionAmount, orderDate);

        result = WolfPubDB.executeUpdate(sql);

        if(!result.success) {
			return result;
		}

		sql = "INSERT INTO Orders VALUES "  + "\n" + "\t" +
				"(%d , %d , %d , %d , %.2f , '%s' , %.2f , '%s')"  + "\n" +
			";" + "\n" + "\n";
        
		sql = String.format(sql, orderID, distributorID, publicationID, numberOfUnits, pricePerUnit, orderDate, shippingCost, orderStatus);

        result = WolfPubDB.executeUpdate(sql);

        if(!result.success){
            return result;
        }

        sql = 
			"UPDATE Distributor SET %s=%.2f WHERE DistributorID = "  + "\n" + "\t" +
				"(%d)"  + "\n" +
			";" + "\n" + "\n"
		    ;

        sql = String.format(sql,"Balance",totalBalance,distributorID);

		return WolfPubDB.executeUpdate(sql);
	}


    public static ExecResult run(Scanner reader) {
		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Order ID: ");
		int orderID = reader.nextInt();
		reader.nextLine();

        System.out.println("Distributor ID: ");
		int distributorID = reader.nextInt();
		reader.nextLine();

        System.out.println("Publication ID: ");
		int publicationID = reader.nextInt();
		reader.nextLine();

        System.out.println("Transaction ID: ");
		int transactionID = reader.nextInt();
		reader.nextLine();

        System.out.println("Number of units: ");
		int numberOfUnits = reader.nextInt();
		reader.nextLine();

        System.out.println("Price per unit: ");
		float pricePerUnit = reader.nextFloat();
		reader.nextLine();

        System.out.println("Order Date (YYYY-MM-DD): ");
        String orderDate = reader.nextLine();

        System.out.println("Order Status: ");
		String orderStatus = reader.nextLine(); 

        System.out.println("Shipping Cost: ");
		float shippingCost = reader.nextFloat(); 
        reader.nextLine();

        float currentBalance = getResults(distributorID);

        float transactionAmount = (numberOfUnits*pricePerUnit) + shippingCost;

        float totalBalance = transactionAmount + currentBalance;

		return execute(orderID,distributorID,publicationID,numberOfUnits,pricePerUnit,orderDate,shippingCost,orderStatus,transactionID,transactionAmount,totalBalance);	
	}

}