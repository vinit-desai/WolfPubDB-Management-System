import java.util.Scanner;

public class DeleteIssue {

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
		WolfPubDB.executeQuery(sql);
    }

    public static ExecResult execute(int publicationID) {

        ExecResult result = null;

        String sql = 
			"DELETE FROM Periodical WHERE PublicationID = %d"  + "\n" + "\t" +
				 "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, publicationID);
        
		return WolfPubDB.executeUpdate(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for DeleteIssue");
		System.out.println("===============================");
		execute(1);
	}



    public static ExecResult run(Scanner reader) {

		System.out.println("+------------------------------------+");
		System.out.println("|         Periodical Details         |");
		System.out.println("+------------------------------------+");
		System.out.println("");

        showDetails("Periodical");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Publication ID: ");
		int publicationID = reader.nextInt();
		reader.nextLine();

		return execute(publicationID);	
	}

}