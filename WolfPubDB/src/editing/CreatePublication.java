import java.util.Scanner;

/**
 * Class used for executing the CreatePublication API operation.
 */
public class CreatePublication {
	
	public static ExecResult run(Scanner reader){
		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Publication ID: ");
		int publicationID = reader.nextInt();
		reader.nextLine();

		System.out.println("Type: ");
		String type = reader.nextLine();

		System.out.println("Publication Date (YYYY-MM-DD): ");
		String publicationDate = reader.nextLine(); 

		System.out.println("Topic: ");
		String topic = reader.nextLine();

		String ISBNType;
		String title;
		int editionIssue;
		String creationDate;
		if(type.equals("Book")){
			System.out.println("ISBN: ");
			ISBNType = reader.nextLine();

			System.out.println("Title: ");
			title = reader.nextLine();

			System.out.println("Edition: ");
			editionIssue = reader.nextInt();
			reader.nextLine();

			System.out.println("Creation Date (YYYY-MM-DD): ");
			creationDate = reader.nextLine();
		}else {
			System.out.println("Periodical Type: ");
			ISBNType = reader.nextLine();

			System.out.println("Title: ");
			title = reader.nextLine();

			System.out.println("Issue: ");
			editionIssue = reader.nextInt();
			reader.nextLine();

			System.out.println("Issue Date (YYYY-MM-DD): ");
			creationDate = reader.nextLine();
		}

		return execute(publicationID, type, publicationDate, topic, ISBNType, title, editionIssue, creationDate);
	}

	public static ExecResult execute(int publicationID, String type, String publicationDate, String topic, String ISBNType, String title, int editionIssue, String creationDate) {
		
		ExecResult result = null;
		Transaction transaction = new Transaction();

		/* Statement 1 in Transaction (adds record to Publication Table) */
		/* ------------------------------------------------------------------ */
		String sql = 
			"INSERT INTO Publication VALUES "  + "\n" + "\t" +
				"(%s, '%s', '%s', '%s')"  + "\n" +
			";" + "\n" + "\n"
		;
		sql = String.format(sql, publicationID, type, publicationDate, topic);
		transaction.addStatement(sql, Transaction.StatementType.UPDATE);
		/* ------------------------------------------------------------------ */
        

		// result = WolfPubDB.executeUpdate(sql);

		// if(!result.success) {
		// 	return result;
		// }
        
		
		/* Statement 2 in Transaction (adds record to Book or Periodical Table) */
		/* ------------------------------------------------------------------ */
		if(type.equals("Book")){
            sql = "INSERT INTO Book VALUES " + "\n" + "\t" +
				"(%s, '%s', '%s', %s, '%s')"  + "\n" +
				";" + "\n" + "\n"
        	;
        }
        else{
            sql = "INSERT INTO Periodical VALUES " + "\n" + "\t" +
				"(%s, '%s', '%s', %s, '%s')"  + "\n" +
				";" + "\n" + "\n"
			;
        }
        sql = String.format(sql, publicationID, ISBNType, title, editionIssue, creationDate);
		transaction.addStatement(sql, Transaction.StatementType.UPDATE);
		/* ------------------------------------------------------------------ */

		/* Execute Transaction via WolfPubDB and return result */
		return WolfPubDB.executeTransaction(transaction);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for CreatePublication");
		System.out.println("===============================");
		execute(11, "Book", "2020-04-04", "Fantasy", "0000000005", "God of War", 1, "2019-05-01");
	}

}