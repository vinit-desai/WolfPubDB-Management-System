public class AddBookChapter {

	public static void execute(int PublicationID, int ChapterNumber, String Title, String Text) {
		String sql = 
			"INSERT INTO Chapter VALUES "  + "\n" + "\t" +
				"(%s, %s, '%s', '%s')"  + "\n" +
			";" + "\n" + "\n"
		;
        sql = String.format(sql, PublicationID, ChapterNumber, Title, Text);
        WolfPubDB.executeUpdate(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for AddBookChapter");
		System.out.println("===============================");
		execute(10, 2, "Last Mile", "Lorem Ipsum...");
	}

}