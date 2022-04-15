import java.util.Scanner;
import java.sql.*;

public class UpdateChapter {

    public static ExecResult execute(String sql) {
        
		return WolfPubDB.executeUpdate(sql);
	}

   public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        System.out.println("sql::" + sql);
		WolfPubDB.executeQuery(sql);
    }


    public static ExecResult run(Scanner reader) {

        System.out.println("+------------------------------------+");
		System.out.println("|         Chapter Details         |");
		System.out.println("+------------------------------------+");
		System.out.println("");

        showDetails("Chapter");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Publication ID: ");
		int publicationID = reader.nextInt();
		reader.nextLine();

        System.out.println("Attribute you want to update: ");
		String attributeName = reader.nextLine();

        System.out.println("New attribute value: ");

        String sql = "";

        if(attributeName.equals("Text") || attributeName.equals("Title")){
            
		    String updatedAttributeValue = reader.nextLine();

            sql = 
			"UPDATE Chapter SET %s='%s' WHERE PublicationID = "  + "\n" + "\t" +
				"(%d)"  + "\n" +
			";" + "\n" + "\n"
		    ;
        
		    sql = String.format(sql, attributeName,updatedAttributeValue,publicationID);

        } else if(attributeName.equals("ChapterNumber")){

            int updatedAttributeValue = reader.nextInt();
            reader.nextLine();

            sql = 
			"UPDATE Chapter SET %s=%d WHERE PublicationID = "  + "\n" + "\t" +
				"(%d)"  + "\n" +
			";" + "\n" + "\n"
		    ;
        
		    sql = String.format(sql, attributeName,updatedAttributeValue,publicationID);

        }	

        return execute(sql);
	}

}