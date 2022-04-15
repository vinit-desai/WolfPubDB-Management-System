import java.util.Scanner;
import java.sql.*;

public class CreateBookEdition {


    public static ExecResult execute(int publicationID, String publicationType, String publishDate, String publicationTopic, int isbnNumber, String bookTitle, int editionNumber) {

        ExecResult result = null;

		String sql = 
			"INSERT INTO Publication VALUES "  + "\n" + "\t" +
				"(%d , '%s' , '%s' , '%s')"  + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, publicationID, publicationType, publishDate, publicationTopic);

        result = WolfPubDB.executeUpdate(sql);

        if(!result.success){
            return result;
        }

        sql = 
			"INSERT INTO Book VALUES "  + "\n" + "\t" +
				"(%d , %d , '%s' , %d , '%s')"  + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, publicationID, isbnNumber, bookTitle, editionNumber, publishDate);
        
		return WolfPubDB.executeUpdate(sql);
	}



    public static ExecResult run(Scanner reader) {
		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Publication ID: ");
		int publicationID = reader.nextInt();
		reader.nextLine();

		String publicationType = "Book";

		System.out.println("Publish Date (YYYY-MM-DD): ");
		String publishDate = reader.nextLine();

		System.out.println("Publication Topic: ");
		String publicationTopic = reader.nextLine();

        System.out.println("ISBN Number: ");
		int isbnNumber = reader.nextInt();
        reader.nextLine();

        System.out.println("Book Title: ");
		String bookTitle = reader.nextLine();

        System.out.println("Edition Number: ");
		int editionNumber = reader.nextInt();
        reader.nextLine();

		return execute(publicationID, publicationType, publishDate, publicationTopic, isbnNumber, bookTitle, editionNumber);	
	}

}