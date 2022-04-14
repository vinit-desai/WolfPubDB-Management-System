import java.util.Scanner;

public class ListEditorPublication {

	public static ExecResult run(Scanner reader) {
		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Contributor ID: ");
		int contributorID = reader.nextInt();
		reader.nextLine();

		return execute(contributorID);	
	}

	public static ExecResult execute(int contributorID) {
		
		String sql = 
			"SELECT  * FROM Publication" + "\n" +
			"WHERE PublicationID IN " + "\n" +
            "(SELECT PublicationID FROM Edits WHERE ContributorID = %s);"
		;
        
		sql = String.format(sql, contributorID);
		
		return WolfPubDB.executeQuery(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for ListEditorPublication");
		System.out.println("===============================");
		execute(7);
	}

}