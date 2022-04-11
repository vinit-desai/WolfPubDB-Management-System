public class DeleteBookChapter {

	public static void execute(int PublicationID, int ChapterNumber) {
		String sql = 
			"DELETE FROM Chapter "  + "\n" +
            "WHERE PublicationID = %s AND ChapterNumber = %s"  + "\n" +
			";" + "\n" + "\n"
		;
        sql = String.format(sql, PublicationID, ChapterNumber);
        WolfPubDB.executeUpdate(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for DeleteBookChapter");
		System.out.println("===============================");
		execute(10, 2);
	}

}