public class ViewPublication {

	public static void execute(int publicationID) {
		
		String sql = 
			"SELECT * FROM Publication" + "\n" +
			"WHERE PublicationID = %s;"
		;
        
		sql = String.format(sql, publicationID);
		
		WolfPubDB.executeQuery(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for ViewPublication");
		System.out.println("===============================");
		execute(1);
	}

}