import java.util.Scanner;
import java.sql.*; 

public class DeleteBookEdition {

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        System.out.println("sql::" + sql);
		WolfPubDB.executeQuery(sql);
    }

    public static void execute(int publicationId) {
        String sql = String.format("DELETE FROM Publication WHERE Publication = %d; ",publicationId);
        System.out.println("sql::" + sql);
		WolfPubDB.executeUpdate(sql);
	}

    public static void main(String[] args){
        System.out.println("\n");
		System.out.println("Delete book edition");

        Scanner in = new Scanner(System.in);

        showDetails("Book");

        showDetails("Publication");

        System.out.println("Enter publication id");
        int publicationId = in.nextInt();
        in.nextLine();
        System.out.println("You entered publication id " + publicationId);

        execute(publicationId);

    }
}