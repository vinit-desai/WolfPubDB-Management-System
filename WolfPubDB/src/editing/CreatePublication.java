public class CreatePublication {

	public static void execute(int publicationID, String type, String publicationDate, String topic, String ISBNType, String title, int editionIssue, String creationDate) {
		
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

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for CreatePublication");
		System.out.println("===============================");
		execute(11, "Book", "2020-04-04", "Fantasy", "0000000005", "God of War", 1, "2019-05-01");
	}

}