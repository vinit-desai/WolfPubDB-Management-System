import java.util.Scanner;

/**
 * Class used for executing the AssignEditor API operation.
 */
public class AssignEditor {

	public static ExecResult run(Scanner reader) {
		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Publication ID: ");
		int publicationID = reader.nextInt();
		reader.nextLine();

		System.out.println("Contributor ID: ");
		int contributorID = reader.nextInt();
		reader.nextLine();
		
		return execute(publicationID, contributorID);	
	}

	public static ExecResult execute(int publicationID, int contributorID) {
		
		String sql = 
			"INSERT INTO Edits VALUES "  + "\n" + "\t" +
				"(%s,%s)"  + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, publicationID, contributorID);
        
		return WolfPubDB.executeUpdate(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for AssignEditor");
		System.out.println("===============================");
		execute(4, 8);
	}

}