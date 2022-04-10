public class AssignEditor {

	public static void execute(int PublicationID, int ContributorID) {
		String sql = 
			"INSERT INTO Edits VALUES "  + "\n" + "\t" +
				"(%s,%s)"  + "\n" +
			";" + "\n" + "\n"
		;
        sql = String.format(sql, PublicationID, ContributorID);
        WolfPubDB.executeUpdate(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for AssignEditor");
		System.out.println("===============================");
		execute(4, 8);
	}

}