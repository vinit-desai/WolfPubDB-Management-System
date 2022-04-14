import java.util.Scanner;

/**
 * Class used for executing the DeleteArticle API operation.
 */
public class DeleteArticle {

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

	public static ExecResult execute(int publicationID, int sequenceNumber) {

		String sql = 
			"DELETE FROM Article "  + "\n" +
            "WHERE PublicationID = %s AND SequenceNumber = %s"  + "\n" +
			";" + "\n" + "\n"
		;

        sql = String.format(sql, publicationID, sequenceNumber);

        return WolfPubDB.executeUpdate(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for DeleteArticle");
		System.out.println("===============================");
		execute(9, 3);
	}

}