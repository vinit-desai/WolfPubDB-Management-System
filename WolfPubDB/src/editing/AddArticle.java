import java.util.Scanner;

/**
 * Class used for executing the AddArticle API operation.
 */
public class AddArticle {

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

		System.out.println("Title: ");
		String title = reader.nextLine();

		System.out.println("Creation Date (YYYY-MM-DD): ");
		String creationDate = reader.nextLine();

		System.out.println("Text: ");
		String text = reader.nextLine();

		return execute(publicationID, sequenceNumber, title, creationDate, text);	
	}

	public static ExecResult execute(int publicationID, int sequenceNumber, String title, String creationDate, String text) {

		String sql = 
			"INSERT INTO Article VALUES "  + "\n" + "\t" +
				"(%s, %s, '%s', '%s', '%s')"  + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, publicationID, sequenceNumber, title, creationDate, text);
        
		return WolfPubDB.executeUpdate(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for AddArticle");
		System.out.println("===============================");
		execute(9, 3, "Dream Come True", "2021-12-19", "Lorem Ipsum...");
	}

}