import java.util.Scanner;

public class DeleteDistributor {

    public static ExecResult execute(int distributorID) {

		String sql = 
			"DELETE FROM Distributor WHERE DistributorID = "  + "\n" + "\t" +
				"(%d)"  + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, distributorID);
        
		return WolfPubDB.executeUpdate(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for DeleteDistributor");
		System.out.println("===============================");
		execute(1);
	}



    public static ExecResult run(Scanner reader) {
		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Distributor ID: ");
		int distributorID = reader.nextInt();
		reader.nextLine();

		return execute(distributorID);	
	}

}