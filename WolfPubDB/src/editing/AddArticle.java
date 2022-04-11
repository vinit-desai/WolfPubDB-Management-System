public class AddArticle {

	public static void execute(int PublicationID, int SequenceNumber, String Title, String CreationDate, String Text) {
		String sql = 
			"INSERT INTO Article VALUES "  + "\n" + "\t" +
				"(%s, %s, '%s', '%s', '%s')"  + "\n" +
			";" + "\n" + "\n"
		;
        sql = String.format(sql, PublicationID, SequenceNumber, Title, CreationDate, Text);
        WolfPubDB.executeUpdate(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for AddArticle");
		System.out.println("===============================");
		execute(9, 3, "Dream Come True", "2021-12-19", "Lorem Ipsum...");
	}

}