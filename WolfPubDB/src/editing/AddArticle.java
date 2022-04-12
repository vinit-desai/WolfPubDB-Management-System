public class AddArticle {

	public static void execute(int publicationID, int sequenceNumber, String title, String creationDate, String text) {

		String sql = 
			"INSERT INTO Article VALUES "  + "\n" + "\t" +
				"(%s, %s, '%s', '%s', '%s')"  + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, publicationID, sequenceNumber, title, creationDate, text);
        
		WolfPubDB.executeUpdate(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for AddArticle");
		System.out.println("===============================");
		execute(9, 3, "Dream Come True", "2021-12-19", "Lorem Ipsum...");
	}

}