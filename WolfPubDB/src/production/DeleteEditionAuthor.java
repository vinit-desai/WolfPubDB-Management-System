import java.util.Scanner;
import java.sql.*;

public class DeleteEditionAuthor {

    public static ExecResult execute(int publicationID) {

		String sql = 
			"DELETE FROM AuthorsBook WHERE PublicationID = %d"  + "\n"
            + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, publicationID);
        
		return WolfPubDB.executeUpdate(sql);
	}

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

}