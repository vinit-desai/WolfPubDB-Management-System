import java.util.Scanner;
import java.sql.*;

public class CreateNewDistributor {

	public static void execute(int ID, String name, float balance, String phoneNumber, String streetAddress,String contactPerson,String type) {
        String sql = String.format("INSERT INTO Distributor VALUES (%d , '%s' , %.2f , '%s' , '%s' , '%s' , '%s');",ID,name,balance,phoneNumber,streetAddress,contactPerson,type);
        System.out.println("sql::" + sql);
		WolfPubDB.executeUpdate(sql);
	}

    public static void showAddressDetails(){
        String sql = String.format("SELECT * FROM Address;");
        System.out.println("sql::" + sql);
		WolfPubDB.executeQuery(sql);
    }

    public static void showDistributorDetails(){
        String sql = String.format("SELECT * FROM Distributor;");
        System.out.println("sql::" + sql);
		WolfPubDB.executeQuery(sql);
    }

     public static void addToAddressTable(String city, String streetAddress){
        String sql = String.format("INSERT INTO Address VALUES ('%s' , '%s');",streetAddress,city);
        System.out.println("sql::" + sql);
		WolfPubDB.executeUpdate(sql);
    }

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Create a new distributor");
        Scanner in = new Scanner(System.in);

        System.out.println("Enter distributor's name");
        String name = in.nextLine();
        System.out.println("You entered name " + name);
 
        System.out.println("Enter distributor's phone number");
        String phoneNumber = in.nextLine();
        System.out.println("You entered phone number " + phoneNumber);

        System.out.println("Choose from the options below:");
        System.out.println("1. Use an existing address   2. Enter a new address");

        int option = in.nextInt();
        System.out.println("option" + option);

        String streetAddress = "";

        if(option == 1){
            Scanner in1 = new Scanner(System.in);
            showAddressDetails();
            System.out.println("Enter distributor's address");
            streetAddress = in1.nextLine();
            System.out.println("You entered address " + streetAddress);

        } else if(option == 2){
            Scanner in2 = new Scanner(System.in);
            System.out.println("Enter distributor's city address");
            String cityAddress = in2.nextLine();
            System.out.println("Enter distributor's street address");
            streetAddress = in2.nextLine();

            addToAddressTable(cityAddress,streetAddress);

            showAddressDetails();
        }
        Scanner in3 = new Scanner(System.in);
        System.out.println("Enter distributor's contact person");
        String contactPerson = in3.nextLine();
        System.out.println("You entered contact person " + contactPerson);
 
        System.out.println("Enter distributor's type");
        String type = in3.nextLine();
        System.out.println("You entered type " + type);

        System.out.println("Enter distributor's id");
        int ID = in3.nextInt();
        System.out.println("You entered id " + ID);

        Scanner in4 = new Scanner(System.in);
        System.out.println("Enter distributor's balance");
        float balance = in4.nextFloat();
        System.out.println("You entered balance " + balance);
       
		System.out.println("===============================");
		execute(ID, name,balance,phoneNumber,streetAddress,contactPerson,type);

        showDistributorDetails();
	}

}