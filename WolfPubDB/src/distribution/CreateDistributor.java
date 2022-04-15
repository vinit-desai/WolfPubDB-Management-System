import java.util.Scanner;
import java.sql.*;

public class CreateDistributor {


public static ExecResult execute(String name, String phoneNumber, String streetAddress, String cityAddress, String contactPerson, String type, int ID, float balance) {
		
		ExecResult result = null;

        if(cityAddress != null){
            String sql = 
			"INSERT INTO Address VALUES "  + "\n" + "\t" +
				"(%s, '%s')"  + "\n" +
			";" + "\n" + "\n";
        
		    sql = String.format(sql, streetAddress,cityAddress);
        
		    result = WolfPubDB.executeUpdate(sql);

		    if(!result.success) {
			    return result;
		    }
        }

		
        String sql = "INSERT INTO Distributor VALUES "  + "\n" + "\t" +
				"(%d , '%s' , %.2f , '%s' , '%s' , '%s' , '%s')"  + "\n" +
			";" + "\n" + "\n";
		
        sql = String.format(sql, ID,name,balance,phoneNumber,streetAddress,contactPerson,type);
		return WolfPubDB.executeUpdate(sql);
	}


    public static void showAddressDetails(){
        String sql = String.format("SELECT * FROM Address;");
        System.out.println("sql::" + sql);
		WolfPubDB.executeQuery(sql);
    }


    public static ExecResult run(Scanner reader) {
		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Distributor Name: ");
        String name = reader.nextLine();

		System.out.println("Distributor Phone Number: ");
        String phoneNumber = reader.nextLine();

        System.out.println("Choose from the options below:");
        System.out.println("1. Use an existing address   2. Enter a new address");

        int option = reader.nextInt();
        reader.nextLine();

        String streetAddress = "";
        String cityAddress = null;

        if(option == 1){

            System.out.println("+------------------------------------+");
		    System.out.println("|         Address Details         |");
		    System.out.println("+------------------------------------+");
		    System.out.println("");

            showAddressDetails();

            System.out.println("Distributor Street Address: ");
            streetAddress = reader.nextLine();


        } else if(option == 2){
   
            System.out.println("Distributor City Address: ");
            cityAddress = reader.nextLine();

            System.out.println("Distributor Street Address: ");
            streetAddress = reader.nextLine();
            
        }

        System.out.println("Distributor Contact Person: ");
        String contactPerson = reader.nextLine();

 
        System.out.println("Distributor Type: ");
        String type = reader.nextLine();


        System.out.println("Distributor ID: ");
        int ID = reader.nextInt();
        reader.nextLine();
  
        System.out.println("Distributor Balance: ");
        float balance = reader.nextFloat();
        reader.nextLine();

		return execute(name,phoneNumber,streetAddress,cityAddress,contactPerson,type,ID,balance);	
	}

    public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for CreateDistributor");
		System.out.println("===============================");
		execute("Mark Austin","5647891452","1615 S Wilmington St","Austin","Test user","Wholesale Distributor",1000,240.45f);
	}

}