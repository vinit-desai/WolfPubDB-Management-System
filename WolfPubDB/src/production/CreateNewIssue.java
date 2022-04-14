import java.util.Scanner;
import java.sql.*;

public class CreateNewIssue {

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        System.out.println("sql::" + sql);
		WolfPubDB.executeQuery(sql);
    }

    public static void executeNew(int publicationId, String issueType, String title, int issueNumber, String issueDate) {
        String sql = String.format("INSERT INTO Periodical VALUES (%d , '%s' , '%s' , %d , '%s');",publicationId,issueType,title,issueNumber,issueDate);
        System.out.println("sql::" + sql);
		WolfPubDB.executeUpdate(sql);
	}

    public static void execute(int publicationId, String type, String publishDate, String topic) {
        String sql = String.format("INSERT INTO Publication VALUES (%d , '%s' , '%s' , '%s');",publicationId,type,publishDate,topic);
        System.out.println("sql::" + sql);
		WolfPubDB.executeUpdate(sql);
	}


    public static void main(String[] args){
        System.out.println("\n");
		System.out.println("Create a new issue");

        Scanner in = new Scanner(System.in);

        System.out.println("Enter publication id");
        int publicationId = in.nextInt();
        in.nextLine();
        System.out.println("You entered id " + publicationId);

        String type = "Periodical";

        System.out.println("Enter publish date (YYYY-MM-DD)");
        String publishDate = in.nextLine();
        System.out.println("You entered publish date " + publishDate);

        System.out.println("Enter topic");
        String topic = in.nextLine();
        System.out.println("You entered topic " + topic);

        execute(publicationId, type, publishDate, topic);
        
        showDetails("Publication");

        System.out.println("Enter issue type: 1. Magazine   2. Journal");
        int option = in.nextInt();
        in.nextLine();
        String issueType = "";
        if(option == 1){
            issueType = "Magazine";
        } else if(option == 2){
            issueType = "Journal";
        }

        System.out.println("Enter title");
        String title = in.nextLine();
        System.out.println("You entered title " + title);

        System.out.println("Enter issue number");
        int issueNumber = in.nextInt();
        in.nextLine();
        System.out.println("You entered issue number " + issueNumber);


        executeNew(publicationId,issueType,title,issueNumber,publishDate);

        showDetails("Periodical");

    }

}