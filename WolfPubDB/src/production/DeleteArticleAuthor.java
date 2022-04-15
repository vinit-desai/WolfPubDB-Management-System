import java.util.Scanner;
import java.sql.*;

public class DeleteArticleAuthor {

    public static ExecResult execute(int publicationID, int sequenceNumber) {

		String sql = 
			"DELETE FROM AuthorsArticle WHERE PublicationID = %d AND SequenceNumber = %d"  + "\n"
            + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, publicationID, sequenceNumber);
        
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

		return execute(publicationID, sequenceNumber);	
	}

}