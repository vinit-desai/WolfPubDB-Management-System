import java.util.Scanner;
import java.sql.*;

public class AddArticleAuthor {

    public static ExecResult execute(int publicationID, int sequenceNumber, int contributorID) {

        ExecResult result = null;

		String sql = 
			"INSERT INTO AuthorsArticle VALUES "  + "\n" + "\t" +
				"(%d , %d, %d)"  + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, publicationID, sequenceNumber, contributorID);
        
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

        System.out.println("Sequence Number: ");
		int sequenceNumber = reader.nextInt();
        reader.nextLine();

        System.out.println("Contributor ID: ");
		int contributorID = reader.nextInt();
        reader.nextLine();

		return execute(publicationID, sequenceNumber, contributorID);	
	}

}