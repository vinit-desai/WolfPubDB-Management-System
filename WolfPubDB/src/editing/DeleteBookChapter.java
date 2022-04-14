import java.util.Scanner;

/**
 * Class used for executing the DeleteBookChapter API operation.
 */
public class DeleteBookChapter {

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

		return execute(publicationID, chapterNumber);	
	}

	public static ExecResult execute(int publicationID, int chapterNumber) {

		String sql = 
			"DELETE FROM Chapter"  + "\n" +
            "WHERE PublicationID = %s AND ChapterNumber = %s"  + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, publicationID, chapterNumber);
        
		return WolfPubDB.executeUpdate(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for DeleteBookChapter");
		System.out.println("===============================");
		execute(10, 2);
	}

}