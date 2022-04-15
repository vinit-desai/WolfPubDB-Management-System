import java.util.Scanner;
import java.sql.*;

public class UpdateDistributor {

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
		System.out.println("|         Distributor Details         |");
		System.out.println("+------------------------------------+");
		System.out.println("");

        showDetails("Distributor");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Distributor ID: ");
		int distributorID = reader.nextInt();
		reader.nextLine();

        System.out.println("Attribute you want to update: ");
		String attributeName = reader.nextLine();

        System.out.println("New attribute value: ");

        String sql = "";

        if(attributeName.equals("Name") || attributeName.equals("StreetAddress") || attributeName.equals("ContactPerson") || attributeName.equals("Type")){
            
		    String updatedAttributeValue = reader.nextLine();

            sql = 
			"UPDATE Distributor SET %s='%s' WHERE DistributorID = "  + "\n" + "\t" +
				"(%d)"  + "\n" +
			";" + "\n" + "\n"
		    ;
        
		    sql = String.format(sql, attributeName,updatedAttributeValue,distributorID);

        } else if(attributeName.equals("PhoneNumber")){

            int updatedAttributeValue = reader.nextInt();
            reader.nextLine();

            sql = 
			"UPDATE Distributor SET %s=%d WHERE DistributorID = "  + "\n" + "\t" +
				"(%d)"  + "\n" +
			";" + "\n" + "\n"
		    ;
        
		    sql = String.format(sql, attributeName,updatedAttributeValue,distributorID);

        } else if(attributeName.equals("Balance")){

            float updatedAttributeValue = reader.nextFloat();
            reader.nextLine();

            sql = 
			"UPDATE Distributor SET %s=%.2f WHERE DistributorID = "  + "\n" + "\t" +
				"(%d)"  + "\n" +
			";" + "\n" + "\n"
		    ;
        
		    sql = String.format(sql, attributeName,updatedAttributeValue,distributorID);

        }	

        return execute(sql);
	}

}