public class DistributorCountReport {

	public static ExecResult execute() {
		
		String sql = 
			"SELECT COUNT(*) AS DistributorCount FROM Distributor;" + "\n"
		;
		
		return WolfPubDB.executeQuery(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for DistributorCountReport");
		System.out.println("====================================");
		execute();
	}

}