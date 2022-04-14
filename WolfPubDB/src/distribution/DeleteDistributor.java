import java.util.Scanner;

public class DeleteDistributor {

	public static void execute(int ID) {
        String sql = String.format("DELETE FROM Distributor WHERE DistributorID = %d; ",ID);
        System.out.println("sql::" + sql);
		WolfPubDB.executeUpdate(sql);
	}

    public static void showDistributorDetails(){
        String sql = String.format("SELECT * FROM Distributor;");
        System.out.println("sql::" + sql);
		WolfPubDB.executeQuery(sql);
    }

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Delete a distributor");
        Scanner in = new Scanner(System.in);

        showDistributorDetails();

        System.out.println("Enter distributor's id you want to delete");
        int ID = in.nextInt();
        System.out.println("You entered ID " + ID);
		System.out.println("===============================");
		execute(ID);
	}

}