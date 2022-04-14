import java.util.Scanner;
import java.sql.*;

public class CreateNewBookEdition {

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        System.out.println("sql::" + sql);
		WolfPubDB.executeQuery(sql);
    }

    public static void execute(int publicationId, int isbnNumber, String title, int editionNumber, String creationDate) {
        String sql = String.format("INSERT INTO Book VALUES (%d , %d , '%s' , %d , '%s');",publicationId,isbnNumber,title,editionNumber,creationDate);
        System.out.println("sql::" + sql);
		WolfPubDB.executeUpdate(sql);
	}


    public static void main(String[] args){
        System.out.println("\n");
		System.out.println("Create a new book edition");

        System.out.println("Existing publications");
        showDetails("Publication");

        Scanner in = new Scanner(System.in);

        System.out.println("Enter publication id");
        int publicationId = in.nextInt();
        System.out.println("You entered id " + publicationId);

        Scanner in1 = new Scanner(System.in);

        System.out.println("Enter ISBN number");
        int isbnNumber = in.nextInt();
        System.out.println("You entered ISBN Number " + isbnNumber);

        Scanner in2 = new Scanner(System.in);

        System.out.println("Enter title");
        String title = in2.nextLine();
        System.out.println("You entered title " + title);

        System.out.println("Enter edition number");
        int editionNumber = in2.nextInt();
        System.out.println("You entered edition number " + editionNumber);

        Scanner in3 = new Scanner(System.in);

        System.out.println("Enter creation date (YYYY-MM-DD)");
        String creationDate = in3.nextLine();
        System.out.println("You entered creation date " + creationDate);

        execute(publicationId,isbnNumber,title,editionNumber,creationDate);

        showDetails("Book");

    }

}