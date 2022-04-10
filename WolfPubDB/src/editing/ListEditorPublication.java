public class ListEditorPublication {

	public static void execute(int ContributorID) {
		String sql = 
			"SELECT  * FROM Publication" + "\n" +
			"WHERE PublicationID IN " + "\n" +
            "(SELECT PublicationID FROM Edits WHERE ContributorID = %s);"
		;
        sql = String.format(sql, ContributorID);
		WolfPubDB.executeQuery(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for ListEditorPublication");
		System.out.println("===============================");
		execute(7);
	}

}