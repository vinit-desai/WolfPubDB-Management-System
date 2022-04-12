public class UpdatePublication {

	public static void execute(int publicationID, String attribute, String newValue) {
		String sql = 
			"UPDATE Publication"  + "\n" +
				"SET %s = '%s'"  + "\n" +
                "WHERE PublicationID = %s" + "\n" +
			";" + "\n" + "\n"
		;
        sql = String.format(sql, attribute, newValue, publicationID);
        System.out.println(sql);
        WolfPubDB.executeUpdate(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for UpdatePublication");
		System.out.println("===============================");
		execute(1, "Topic", "Science");
	}

}