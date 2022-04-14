import java.util.Scanner;
import java.sql.*;

public class UpdateDistributor {

	public static void execute(String columnName, int updateID, String updatedValues) {
        String sql = String.format("UPDATE Distributor SET %s='%s' WHERE DistributorID = %d;",columnName,updatedValues,updateID);
        System.out.println("sql::" + sql);
		WolfPubDB.executeUpdate(sql);
	}

   public static void showDistributorDetails(){
        String sql = String.format("SELECT * FROM Distributor;");
        System.out.println("sql::" + sql);
		WolfPubDB.executeQuery(sql);
    }

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Update distributor data");
        Scanner in = new Scanner(System.in);

        showDistributorDetails();

        System.out.println("Enter the number of columns you want to update");
        int numberOfColums = in.nextInt();

        for(int i = 0;i <numberOfColums;i++){
            Scanner in1 = new Scanner(System.in);

            System.out.println("Enter the name of the column you want to update");
            String columnName = in1.nextLine();

            System.out.println("Enter the values you want to update the existing data as");
            String updatedValues = in1.nextLine();

            System.out.println("Enter the distributorID of the row you want to update");
            int updateID = in1.nextInt();

            execute(columnName,updateID,updatedValues);
        }
        
	}

}