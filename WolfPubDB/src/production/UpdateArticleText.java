import java.util.Scanner;
import java.sql.*;

public class UpdateArticleText {

    public static ExecResult execute(int publicationID, String updatedAttributeValue) {

        String sql = 
		"UPDATE Article SET Text='%s' WHERE PublicationID = "  + "\n" + "\t" +
			"(%d)"  + "\n" +
		";" + "\n" + "\n"
		;
        
		sql = String.format(sql, updatedAttributeValue,publicationID);
        
		return WolfPubDB.executeUpdate(sql);
	}

   public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        System.out.println("sql::" + sql);
		WolfPubDB.executeQuery(sql);
    }


    public static ExecResult run(Scanner reader) {

        System.out.println("+------------------------------------+");
		System.out.println("|         Article Details         |");
		System.out.println("+------------------------------------+");
		System.out.println("");

        showDetails("Article");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Publication ID: ");
		int publicationID = reader.nextInt();
		reader.nextLine();

        System.out.println("New text value: ");
            
		String updatedAttributeValue = reader.nextLine();

        return execute(publicationID,updatedAttributeValue);
	}

}