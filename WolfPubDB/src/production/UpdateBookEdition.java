import java.util.Scanner;
import java.sql.*;

public class UpdateBookEdition {

	public static void execute(String columnName, int updateID, String updatedValue) {
        String sql = String.format("UPDATE Book SET %s='%s' WHERE PublicationID = %d;",columnName,updatedValue,updateID);
        System.out.println("sql::" + sql);
		WolfPubDB.executeUpdate(sql);
	}

   public static void showDistributorDetails(){
        String sql = String.format("SELECT * FROM Book;");
        System.out.println("sql::" + sql);
		WolfPubDB.executeQuery(sql);
    }

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Update book data");
        Scanner in = new Scanner(System.in);

        showDistributorDetails();

        System.out.println("Enter the number of columns you want to update");
        int numberOfColums = in.nextInt();

        for(int i = 0;i <numberOfColums;i++){
            Scanner in1 = new Scanner(System.in);

            System.out.println("Enter the name of the column you want to update");
            String columnName = in1.nextLine();

            System.out.println("Enter the values you want to update the existing data as");
            String updatedValue = in1.nextLine();

            System.out.println("Enter the publication ID of the book you want to update");
            int updateID = in1.nextInt();

            execute(columnName,updateID,updatedValue);
        }
        
	}

}