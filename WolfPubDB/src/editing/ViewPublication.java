import java.util.Scanner;

/**
 * Class used for executing the ViewPublication API operation.
 */
public class ViewPublication {

	public static ExecResult run(Scanner reader) {
		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Publication ID: ");
		int publicationID = reader.nextInt();
		reader.nextLine();

		return execute(publicationID);	
	}

	public static ExecResult execute(int publicationID) {
		
		String sql = 
			"SELECT * FROM Publication" + "\n" +
			"WHERE PublicationID = %s;"
		;
        
		sql = String.format(sql, publicationID);
		
		return WolfPubDB.executeQuery(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for ViewPublication");
		System.out.println("===============================");
		execute(1);
	}

}