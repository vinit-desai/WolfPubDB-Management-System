public class CreatePublication {

	public static void execute(int PublicationID, String Type, String PublishDate, String Topic, String ISBNType, String Title, int EditionIssue, String Date) {
		String sql = 
			"INSERT INTO Publication VALUES "  + "\n" + "\t" +
				"(%s, '%s', '%s', '%s')"  + "\n" +
			";" + "\n" + "\n"
		;
        sql = String.format(sql, PublicationID, Type, PublishDate, Topic);
        WolfPubDB.executeUpdate(sql);
        if(Type.equals("Book")){
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
        sql = String.format(sql, PublicationID, ISBNType, Title, EditionIssue, Date);
		WolfPubDB.executeUpdate(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for CreatePublication");
		System.out.println("===============================");
		execute(11, "Book", "2020-04-04", "Fantasy", "0000000005", "God of War", 1, "2019-05-01");
	}

}