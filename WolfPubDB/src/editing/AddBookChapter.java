import java.util.Scanner;

/**
 * Class used for executing the AddBookChapter API operation.
 */
public class AddBookChapter {

	public static ExecResult run(Scanner reader) {
		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Publication ID: ");
		int publicationID = reader.nextInt();
		reader.nextLine();

		System.out.println("Chapter Number: ");
		int chapterNumber = reader.nextInt();
		reader.nextLine();

		System.out.println("Title: ");
		String title = reader.nextLine();

		System.out.println("Text: ");
		String text = reader.nextLine();

		return execute(publicationID, chapterNumber, title, text);	
	}

	public static ExecResult execute(int publicationID, int chapterNumber, String title, String text) {
		
		String sql = 
			"INSERT INTO Chapter VALUES "  + "\n" + "\t" +
				"(%s, %s, '%s', '%s')"  + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, publicationID, chapterNumber, title, text);
        
		return WolfPubDB.executeUpdate(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for AddBookChapter");
		System.out.println("===============================");
		execute(10, 2, "Last Mile", "Lorem Ipsum...");
	}

}