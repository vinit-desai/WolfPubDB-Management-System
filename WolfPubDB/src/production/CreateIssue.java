

    

import java.util.Scanner;
import java.sql.*;

public class CreateIssue {

    public static ExecResult execute(int publicationID, String publicationType, String publishDate, String publicationTopic, String issueType, String issueTitle, int issueNumber) {

        ExecResult result = null;

		String sql = 
			"INSERT INTO Publication VALUES "  + "\n" + "\t" +
				"(%d , '%s' , '%s' , '%s')"  + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, publicationID, publicationType, publishDate, publicationTopic);

		result = WolfPubDB.executeUpdate(sql);

        if(!result.success){
            return result;
        }

        sql = 
			"INSERT INTO Periodical VALUES "  + "\n" + "\t" +
				"(%d , '%s' , '%s' , %d , '%s')"  + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, publicationID, issueType, issueTitle, issueNumber, publishDate);
        
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

		String publicationType = "Periodical";

		System.out.println("Publish Date (YYYY-MM-DD): ");
		String publishDate = reader.nextLine();

		System.out.println("Publication Topic: ");
		String publicationTopic = reader.nextLine();

        System.out.println("Enter issue type: 1. Magazine   2. Journal");
        int option = reader.nextInt();
        reader.nextLine();

        String issueType = "";
        if(option == 1){
            issueType = "Magazine";
        } else if(option == 2){
            issueType = "Journal";
        }

        System.out.println("Issue Title: ");
		String issueTitle = reader.nextLine();

        System.out.println("Issue Number: ");
		int issueNumber = reader.nextInt();
        reader.nextLine();

		return execute(publicationID, publicationType, publishDate, publicationTopic, issueType, issueTitle, issueNumber);	
	}

}