import java.util.Scanner;

/**
 * Class used for executing the UpdatePublication API operation.
 */
public class UpdatePublication {

	public static ExecResult run(Scanner reader) {
		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Publication ID: ");
		int publicationID = reader.nextInt();
		reader.nextLine();

		System.out.println("Select attribute to update:" + "\n" +
			"1. Publication Date (YYYY-MM-YY)" + "\n" + 
			"2. Topic"
		);
		int choice = reader.nextInt();
		reader.nextLine();

		String attribute;
		if (choice == 1){
			attribute = "PublishDate";
		} else if (choice == 2){
			attribute = "Topic";
		} else {
			return new ExecResult(false, "Invalid input");
		}

		System.out.println("New Value: ");
		String newValue = reader.nextLine();
		
		return execute(publicationID, attribute, newValue);
	}

	public static ExecResult execute(int publicationID, String attribute, String newValue) {

		String sql = 
			"UPDATE Publication"  + "\n" +
				"SET %s = '%s'"  + "\n" +
                "WHERE PublicationID = %s" + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, attribute, newValue, publicationID);
        
        return WolfPubDB.executeUpdate(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for UpdatePublication");
		System.out.println("===============================");
		execute(1, "Topic", "Science");
	}

}