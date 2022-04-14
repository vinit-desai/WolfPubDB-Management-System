import java.util.Scanner;
import java.sql.*;

public class CreateOrder {


    public static void showOrderDetails(){
        String sql = String.format("SELECT * FROM Orders;");
        System.out.println("sql::" + sql);
		WolfPubDB.executeQuery(sql);
    }

    public static void execute(int orderId, int distributorId, int publicationId, int numberOfUnits, float pricePerUnit, String orderDate, float shippingCost, String orderStatus) {
        String sql = String.format("INSERT INTO Orders VALUES (%d , %d , %d , %d , %.2f , '%s' , %.2f , '%s');",orderId,distributorId,publicationId,numberOfUnits,pricePerUnit,orderDate,shippingCost,orderStatus);
        System.out.println("sql::" + sql);
		WolfPubDB.executeUpdate(sql);
	}

    public static void main(String[] args){
        System.out.println("\n");
		System.out.println("Create a new order");

        System.out.println("Existing orders");
        showOrderDetails();

        Scanner in = new Scanner(System.in);

        System.out.println("Enter order id");
        int orderId = in.nextInt();
        System.out.println("You entered id " + orderId);

        Scanner in1 = new Scanner(System.in);

        System.out.println("Enter distributor's id");
        int distributorId = in1.nextInt();
        System.out.println("You entered id " + distributorId);

        Scanner in2 = new Scanner(System.in);
        System.out.println("Enter publication's id");
        int publicationId = in2.nextInt();
        System.out.println("You entered id " + publicationId);

        Scanner in3 = new Scanner(System.in);
        System.out.println("Enter number of units");
        int numberOfUnits = in3.nextInt();
        System.out.println("You entered number of units " + numberOfUnits);

        Scanner in4 = new Scanner(System.in);
        System.out.println("Enter price per unit");
        float pricePerUnit = in4.nextFloat();
        System.out.println("You entered price per unit " + pricePerUnit);

        Scanner in5 = new Scanner(System.in);
        System.out.println("Enter order date (YYYY-MM-DD):");
		String orderDate = in5.nextLine(); 
        System.out.println("You entered date " + orderDate);

        System.out.println("Enter shipping cost");
		float shippingCost = in5.nextFloat(); 
        System.out.println("You entered shipping cost " + shippingCost);

        Scanner in6 = new Scanner(System.in);
        System.out.println("Enter order status");
		String orderStatus = in6.nextLine(); 
        System.out.println("You entered order status " + orderStatus);

        float totalBalance = (numberOfUnits*pricePerUnit) + shippingCost;

        execute(orderId,distributorId,publicationId,numberOfUnits,pricePerUnit,orderDate,shippingCost,orderStatus);

        showOrderDetails();

        BillDistributor.run(totalBalance,distributorId,orderDate);
    }

}