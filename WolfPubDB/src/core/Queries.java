public class Queries{

    public static void createPublication(int publicationID, String type, String publicationDate, String topic, String ISBNType, String title, int editionIssue, String creationDate) {
		
        String sql = 
			"INSERT INTO Publication VALUES "  + "\n" + "\t" +
				"(%s, '%s', '%s', '%s')"  + "\n" +
			";" + "\n" + "\n"
		;
        
        sql = String.format(sql, publicationID, type, publicationDate, topic);
        
        WolfPubDB.executeUpdate(sql);
        
        if(type.equals("Book")){
            sql = "INSERT INTO Book VALUES " + "\n" + "\t" +
				"(%s, '%s', '%s', %s, '%s')"  + "\n" +
			";" + "\n" + "\n"
        ;
        }
        else{
            sql = "INSERT INTO Periodical VALUES " + "\n" + "\t" +
				"(%s, '%s', '%s', %s, '%s')"  + "\n" +
			";" + "\n" + "\n"
		;
        }
        
        sql = String.format(sql, publicationID, ISBNType, title, editionIssue, creationDate);
		
        WolfPubDB.executeUpdate(sql);
	}

    public static void assignEditor(int publicationID, int contributorID) {
		
        String sql = 
			"INSERT INTO Edits VALUES "  + "\n" + "\t" +
				"(%s,%s)"  + "\n" +
			";" + "\n" + "\n"
		;
        
        sql = String.format(sql, publicationID, contributorID);
        
        WolfPubDB.executeUpdate(sql);
	}

    public static void viewPublication(int publicationID) {
		
		String sql = 
			"SELECT * FROM Publication" + "\n" +
			"WHERE PublicationID = %s;"
		;
        
		sql = String.format(sql, publicationID);
		
		WolfPubDB.executeQuery(sql);
	}

    public static void addBookChapter(int publicationID, int chapterNumber, String title, String text) {
		
		String sql = 
			"INSERT INTO Chapter VALUES "  + "\n" + "\t" +
				"(%s, %s, '%s', '%s')"  + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, publicationID, chapterNumber, title, text);
        
		WolfPubDB.executeUpdate(sql);
	}

    public static void deleteBookChapter(int publicationID, int chapterNumber) {

		String sql = 
			"DELETE FROM Chapter"  + "\n" +
            "WHERE PublicationID = %s AND ChapterNumber = %s"  + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, publicationID, chapterNumber);
        
		WolfPubDB.executeUpdate(sql);
	}

    public static void addArticle(int publicationID, int sequenceNumber, String title, String creationDate, String text) {

		String sql = 
			"INSERT INTO Article VALUES "  + "\n" + "\t" +
				"(%s, %s, '%s', '%s', '%s')"  + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, publicationID, sequenceNumber, title, creationDate, text);
        
		WolfPubDB.executeUpdate(sql);
	}

    public static void deleteArticle(int publicationID, int sequenceNumber) {

		String sql = 
			"DELETE FROM Article "  + "\n" +
            "WHERE PublicationID = %s AND SequenceNumber = %s"  + "\n" +
			";" + "\n" + "\n"
		;

        sql = String.format(sql, publicationID, sequenceNumber);

        WolfPubDB.executeUpdate(sql);
	}

}