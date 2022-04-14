import java.util.Scanner;
import java.sql.*;

public class BillDistributor {

    public static void execute(int transactionId, int distributorId) {
        String sql = String.format("INSERT INTO Bills VALUES (%d , %d , False , NULL);",transactionId,distributorId);
        System.out.println("sql::" + sql);
		WolfPubDB.executeUpdate(sql);
	}

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        System.out.println("sql::" + sql);
		WolfPubDB.executeQuery(sql);
    }

    public static void showDistributorDetails(){
        String sql = String.format("SELECT * FROM Distributor;");
        System.out.println("sql::" + sql);
		WolfPubDB.executeQuery(sql);
    }

    public static void updateDistributor(float totalBalance, int distributorId){
        String columnName = "Balance";
        String sql = String.format("UPDATE Distributor SET %s= %.2f WHERE DistributorID = %d;",columnName,totalBalance,distributorId);
        System.out.println("sql::" + sql);
		WolfPubDB.executeUpdate(sql);
    }

    public static void addTransaction(int transactionId, float totalBalance, String orderDate){
        String sql = String.format("INSERT INTO Transaction VALUES (%d , %.2f , '%s');",transactionId,totalBalance,orderDate);
        System.out.println("sql::" + sql);
		WolfPubDB.executeUpdate(sql);
    }


    public static void run(float totalBalance, int distributorId, String orderDate){
        System.out.println("\n");
		System.out.println("Bill distributor");

        Scanner in = new Scanner(System.in);

        System.out.println("Enter transaction id");
        int transactionId = in.nextInt();
        System.out.println("You entered id " + transactionId);

        addTransaction(transactionId, totalBalance, orderDate);

        showDetails("Transaction");

        execute(transactionId,distributorId);

        showDetails("Bills");

        updateDistributor(totalBalance, distributorId);

        showDistributorDetails();

    }

}