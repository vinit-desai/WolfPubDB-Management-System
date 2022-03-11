/**
 * This script builds constructs the WolfPubDB for our project. Note that it
 * drops the existing database and its tables should it exist already. I.e.,
 * this script will remove the existing DB and rebuild it from scratch.
 *
 * - IMPORTANT NOTE - Relpace all $USER$ with your unity id and $PASSWORD$ 
 *	with your 9 digit student id or updated password (if changed).
 */

import java.sql.*;

public class WolfPubDB {
	static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/mgyoung";
	// Put your oracle ID and password here

	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet result = null;

	private static String[] tables = {
		"Wages",
		"Bills",
		"Transaction",
		"Orders",
		"Distributor",
		"Address",
		"AuthorsArticle",
		"AuthorsBook",
		"Edits",
		"Contributor",
		"Article",
		"Chapter",
		"Periodical",
		"Periodicity",
		"Book",
		"Publication",
	};

	public static void main(String[] args) {
		initialize();
		close();
	}

	private static void initialize() {
		try {
			connectToDatabase();
			createTables();
			// populateTables();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	
/* ################################################################################################################################################################# */
	/* ********************************************************************** */
	/* *********************** Connect & Drop Tables ************************ */
	/* ********************************************************************** */
/* ################################################################################################################################################################# */



	private static void connectToDatabase() throws ClassNotFoundException, SQLException {
		Class.forName("org.mariadb.jdbc.Driver");

		String user = "mgyoung";
		String password = "200009434";

		connection = DriverManager.getConnection(jdbcURL, user, password);
		statement = connection.createStatement();

		try {
			for (String table : tables) 
			{   
				System.out.println("DROP TABLE IF EXISTS " + table + ";");
				statement.executeUpdate("DROP TABLE IF EXISTS " + table + ";");
			}
		} catch (SQLException e) {
		}
	}


	
/* ################################################################################################################################################################# */
	/* ********************************************************************** */
	/* *************************** Table Creation *************************** */
	/* ********************************************************************** */
/* ################################################################################################################################################################# */

	

	private static void createTables() throws SQLException {
		/* Publication */
		/* ------------------------------------------------------------------ */
		String SQL =
			"CREATE TABLE Publication ("  + "\n" + 
			"PublicationID 	INT 		NOT NULL,"  + "\n" + 
			"	Type 		VARCHAR(16) 	NOT NULL,"  + "\n" + 
			"	PublishDate 	DATE 		NOT NULL,"  + "\n" + 
			"	Topic 		VARCHAR(128) 	NOT NULL,"  + "\n" + 
			"	PRIMARY KEY (PublicationID)"  + "\n" +
			");" + "\n"  + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */
		
		/* Book */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Book(" + "\n" + "\t" +
			"	PublicationID 	INT 		NOT NULL," + "\n" + 
			"	ISBN 		CHAR(10) 	NOT NULL," + "\n" + 
			"	Title 		VARCHAR(128) 	NOT NULL," + "\n" + 
			"	Edition 	INT 		NOT NULL," + "\n" + 
			"	CreationDate 	DATE 		NOT NULL," + "\n" + 
			"	UNIQUE(ISBN)," 	+ "\n" + 
			"	PRIMARY KEY (PublicationID)," + "\n" + 
			"	FOREIGN KEY (PublicationID)" + "\n" + 
			"		REFERENCES Publication (PublicationID)" + "\n" + 
			"		ON DELETE CASCADE" + "\n" + 
			"		ON UPDATE CASCADE" + "\n" +
			");" + "\n" + "\n" 
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */


		/* Periodicity */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Periodicity (" + "\n" + "\t" +
			"	Type 		VARCHAR(16)	NOT NULL," + "\n" +
			"	Periodicity 	VARCHAR(16) 	NOT NULL," + "\n" +
			"	PRIMARY KEY (Type)" + "\n" +
			");" + "\n" + "\n" 
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */


		/* Periodical */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Periodical (" + "\n" + "\t" +
			"	PublicationID 	INT 		NOT NULL," + "\n" +
			"	Type 		VARCHAR(16) 	NOT NULL," + "\n" + 
			"	Title 		VARCHAR(128) 	NOT NULL," + "\n" + 
			"	Issue 		INT 		NOT NULL," + "\n" + 
			"	IssueDate 	DATE		NOT NULL," + "\n" + 
			"	PRIMARY KEY (PublicationID)," + "\n" + 
			"	FOREIGN KEY (PublicationID)" + "\n" + 
			"		REFERENCES Publication (PublicationID)" + "\n" + 
			"		ON DELETE CASCADE" + "\n" + 
			"		ON UPDATE CASCADE," + "\n" + 
			"	FOREIGN KEY (Type)" + "\n" + 
			"		REFERENCES Periodicity (Type)" + "\n" + 
			"		ON UPDATE CASCADE" + "\n" +
			");" + "\n" + "\n" 
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */


		/* Chapter */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Chapter (" + "\n" + "\t" +
			"	PublicationID	INT 		NOT NULL," + "\n" + 
			"	ChapterNumber 	INT 		NOT NULL," + "\n" + 
			"	Title 		VARCHAR(128) 	NOT NULL," + "\n" + 
			"	Text 		LONGTEXT 	NOT NULL," + "\n" + 
			"	PRIMARY KEY (PublicationID, ChapterNumber)," + "\n" +
			"	FOREIGN KEY (PublicationID)" + "\n" +
			"		REFERENCES Publication (PublicationID)" + "\n" + 
			"		ON DELETE CASCADE" + "\n" +
			"		ON UPDATE CASCADE" + "\n" +
			");" + "\n" + "\n" 
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */


		/* Article */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Article (" + "\n" + "\t" +
			"	PublicationID 	INT 		NOT NULL," + "\n" + 
			"	SequenceNumber INT 		NOT NULL," + "\n" + 
			"	Title 		VARCHAR(128) 	NOT NULL," + "\n" +
			"	CreationDate 	DATE 		NOT NULL," + "\n" + 
			"	Text 		MEDIUMTEXT 	NOT NULL," + "\n" +
			"	PRIMARY KEY (PublicationID, SequenceNumber)," + "\n" + 
			"	FOREIGN KEY (PublicationID)" + "\n" + 
			"		REFERENCES Publication (PublicationID)" + "\n" + 
			"		ON DELETE CASCADE"+ "\n" +
			"		ON UPDATE CASCADE" + "\n" +
			");" + "\n" + "\n" 
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */


		/* Contributor */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Contributor (" + "\n" + 
			"	ContributorID	INT 		NOT NULL," + "\n" + 
			"	Name 		VARCHAR(128) 	NOT NULL," + "\n" +
			"	Position 	VARCHAR(16) 	NOT NULL," + "\n" + 
			"	Type 		VARCHAR(16) 	NOT NULL," + "\n" + 
			"	PRIMARY KEY (ContributorID)" + "\n" +
			");" + "\n" + "\n" 
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */


		/* Edits */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Edits (" + "\n" + "\t" +
			"	PublicationID	INT	NOT NULL," + "\n" +
			"	ContributorID 	INT 	NOT NULL," + "\n" + 
			"	PRIMARY KEY (PublicationID, ContributorID)," + "\n" +
			"	FOREIGN KEY (PublicationID)" + "\n" + 
			"		REFERENCES Publication (PublicationID)" + "\n" + 
			"		ON DELETE CASCADE" + "\n" + 
			"		ON UPDATE CASCADE," + "\n" + 
			"	FOREIGN KEY (ContributorID)" + "\n" + 
			"		REFERENCES Contributor (ContributorID)" + "\n" + 
			"		ON DELETE CASCADE" + "\n" + 
			"		ON UPDATE CASCADE" + "\n" +
			");" + "\n" + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */

		/* AuthorsArticle */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE AuthorsArticle (" + "\n" + 
			"	PublicationID		INT 	NOT NULL," + "\n" + 
			"	SequenceNumber		INT	NOT NULL," + "\n" + 
			"	ContributorID 		INT 	NOT NULL," + "\n" + 
			"	PRIMARY KEY (PublicationID, SequenceNumber, ContributorID)," + "\n" +
			"	FOREIGN KEY (PublicationID, SequenceNumber)"+ "\n" +
			"		REFERENCES Article (PublicationID, SequenceNumber)"+ "\n" +
			"		ON DELETE CASCADE" + "\n" + 
			"		ON UPDATE CASCADE," + "\n" + 
			"	FOREIGN KEY (ContributorID)"+ "\n" + 
			"		REFERENCES Contributor (ContributorID)"+ "\n" + 
			"		ON DELETE CASCADE" + "\n" + 
			"		ON UPDATE CASCADE" + "\n" +
			");" + "\n" + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */

		/* AuthorsBook */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE AuthorsBook (" + "\n" +
			"	PublicationID 	INT 	NOT NULL," + "\n" + 
			"	ContributorID 	INT 	NOT NULL," + "\n" + 
			"	PRIMARY KEY (PublicationID, ContributorID)," + "\n" + 
			"	FOREIGN KEY (PublicationID)"+ "\n" +
			"		REFERENCES Article (PublicationID)"+ "\n" +
			"		ON DELETE CASCADE" + "\n" + 
			"		ON UPDATE CASCADE," + "\n" + 
			"	FOREIGN KEY (ContributorID)"+ "\n" + 
			"		REFERENCES Contributor (ContributorID)"+ "\n" + 
			"		ON DELETE CASCADE" + "\n" + 
			"		ON UPDATE CASCADE" + "\n" +
			");" + "\n"  + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */

		/* Address */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Address (" + "\n" + 
			"	StreetAddress	VARCHAR(128) NOT NULL," + "\n" +
			"	City 		VARCHAR(128) NOT NULL," + "\n" + 
			"	PRIMARY KEY (StreetAddress)" + "\n" +
			");" + "\n"  + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */

		/* Distributor */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Distributor (" + "\n" + 
			"	DistributorID	INT 		NOT NULL," + "\n" + 
			"	Name 		VARCHAR(128) 	NOT NULL," + "\n" +
			"	Balance 	INT 		NOT NULL," + "\n" +
			"	PhoneNumber 	CHAR(10) 	NOT NULL," + "\n" + 
			"	StreetAddress 	VARCHAR(128) 	NOT NULL," + "\n" + 
			"	ContactPerson 	VARCHAR(128) 	NOT NULL," + "\n" + 
			"	Type 		VARCHAR(32) 	NOT NULL," + "\n" + 
			"	PRIMARY KEY (DistributorID)," + "\n" + 
			"	FOREIGN KEY (StreetAddress)" + "\n" + 
			"		REFERENCES Address (StreetAddress)" + "\n" + 
			"		ON UPDATE CASCADE" + "\n" +
			");" + "\n"  + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */

		/* Orders */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Orders (" + "\n" + "\t" +
			"	OrderID 	INT 		NOT NULL," + "\n" + 
			"	DistributorID 	INT 		NOT NULL," + "\n" + 
			"	PublicationID 	INT 		NOT NULL," + "\n" +
			"	Units 		INT 		NOT NULL," + "\n" +
			"	PricePerUnit 	DECIMAL(7,2)	NOT NULL," + "\n" + 
			"	OrderDate 	DATE 		NOT NULL," + "\n" +
			"	ShippingCost 	DECIMAL(9,2)	NOT NULL," + "\n" + 
			"	Status 		VARCHAR(16) 	NOT NULL," + "\n" + 
			"	PRIMARY KEY (OrderID)," + "\n" +
			"	FOREIGN KEY (DistributorID)"+ "\n" +
			"		REFERENCES Distributor (DistributorID)"+ "\n" +
			"		ON UPDATE CASCADE," + "\n" + 
			"	FOREIGN KEY (PublicationID)"+ "\n" +
			"		REFERENCES Publication (PublicationID)"+ "\n" +
			"		ON UPDATE CASCADE" + "\n" +
			");" + "\n"  + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */


		/* Transaction */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Transaction (" + "\n" +  
			"	TransactionID	INT 	NOT NULL," + "\n" + 
			"	Amount 		INT 	NOT NULL," + "\n" +
			"	IssueDate 	DATE 	NOT NULL," + "\n" + 
			"	PRIMARY KEY (TransactionID)" + "\n" +
			");" + "\n" 
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */

		/* Bills */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Bills (" + "\n" + "\t" +
			"	TransactionID	INT		NOT NULL," + "\n" + 
			"	DistributorID 	INT 		NOT NULL," + "\n" + 
			"	Paid 		BOOL 		NOT NULL," + "\n" +
			"	PaymentDate 	DATE," + "\n" +
			"	PRIMARY KEY (TransactionID)," + "\n" +
			"	FOREIGN KEY (TransactionID)" + "\n" +
			"		REFERENCES Transaction (TransactionID)" + "\n" +
			"		ON DELETE CASCADE" + "\n" +
			"		ON UPDATE CASCADE," + "\n" + 
			"	FOREIGN KEY (DistributorID )" + "\n" + 
			"		REFERENCES Distributor (DistributorID )" + "\n" + 
			"		ON UPDATE CASCADE" + "\n" +
			");" + "\n" + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */

		/* Wages */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Wages (" + "\n" +
			"	TransactionID	INT 		NOT NULL," + "\n" +
			"	ContributorID 	INT 		NOT NULL," + "\n" + 
			"	Type 		VARCHAR(32) 	NOT NULL," + "\n" + 
			"	ClaimDate 	DATE," + "\n" + 
			"	PRIMARY KEY (TransactionID)," + "\n" + 
			"	FOREIGN KEY (TransactionID)" + "\n" +
			"		REFERENCES Transaction (TransactionID)" + "\n" +
			"		ON DELETE CASCADE" + "\n" +
			"		ON UPDATE CASCADE," + "\n" + 
			"	FOREIGN KEY (ContributorID )" + "\n" + 
			"		REFERENCES Contributor (ContributorID )" + "\n" + 
			"		ON UPDATE CASCADE" + "\n" +
			");" + "\n" + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */

	}
	

/* ################################################################################################################################################################# */
	/* ********************************************************************** */
	/* ************************** Table Population ************************** */
	/* ********************************************************************** */
/* ################################################################################################################################################################# */


	private static void populateTables() throws SQLException {
		
		/* Publication */
		/* ------------------------------------------------------------------ */
		String SQL =
			"INSERT INTO Publication VALUES "  + "\n" + "\t" +
				"(1, 'Book', '2020-05-23', 'Sports'),"  + "\n" + "\t" +
				"(2, 'Periodical', '2021-01-01', 'Science'),"  + "\n" + "\t" +
				"(3, 'Periodical', '2018-11-01', 'Entertainment'),"  + "\n" + "\t" +
				"(4, 'Book', '2020-08-28', 'Science Fiction'),"  + "\n" + "\t" +
				"(5, 'Book', '2016-09-09', 'Biography'),"  + "\n" + "\t" +
				"(6, 'Periodical', '2022-01-08', 'Sports'),"  + "\n" + "\t" +
				"(7, 'Periodical', '2021-10-01', 'Music'),"  + "\n" + "\t" +
				"(8, 'Periodical', '2021-11-01', 'Sports'),"  + "\n" + "\t" +
				"(9, 'Periodical', '2021-12-15', 'History'),"  + "\n" + "\t" +
				"(10, 'Book', '2021-04-02', 'Self Help')"  + "\n" +
			";" + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */
		
		/* Book */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Book VALUES " + "\n" + "\t" +
				"(4, '0000000001', 'Harry Potter', 1, '1998-03-02')," + "\n" + "\t" +
				"(1, '0000000002', 'What Made Maddy Run', 1, '2000-12-10'),"  + "\n" + "\t" +
				"(5, '0000000003', 'Churchill: A life', 2, '2012-08-05'),"  + "\n" + "\t" +
				"(10, '0000000004', 'Atomic Habits', 1, '2006-06-09')"  + "\n" +
			";" + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
// INSERT INTO Book VALUES (4, '0000000001', 'Harry Potter', 1, '1998-03-02');
// INSERT INTO Book VALUES (1, '0000000002', 'What Made Maddy Run', 1, '2000-12-10');
// INSERT INTO Book VALUES (5, '0000000003', 'Churchill: A life', 2, '2012-08-05');
// INSERT INTO Book VALUES (10, '0000000004', 'Atomic Habits', 1, '2006-06-09');
		/* ------------------------------------------------------------------ */


		/* Periodicity */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Periodicity VALUES "  + "\n" + "\t" +
				"('Journal', 'Weekly')," + "\n" + "\t" +
				"('Magazine', 'Monthly')"  + "\n" +
			";" + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
// INSERT INTO Periodicity VALUES ('Journal', 'Weekly');
// INSERT INTO Periodicity VALUES ('Magazine', 'Monthly');
		/* ------------------------------------------------------------------ */

		/* Periodical */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Periodical VALUES "  + "\n" + "\t" +
				"(2,  'Magazine', 'Discover', 1, '2020-12-01')," + "\n" + "\t" +
				"(3,  'Journal', 'Variety', 1, '2018-11-07')," + "\n" + "\t" +
				"(6,  'Journal', 'ESPN', 1, '2022-01-14')," + "\n" + "\t" +
				"(7,  'Magazine', 'Revolver', 2, '2021-09-01')," + "\n" + "\t" +
				"(8,  'Magazine', 'Hoop', 1, '2021-10-01')," + "\n" + "\t" +
				"(9,  'Journal', 'Ancient', 3, '2021-12-21')"  + "\n" +
			";" + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
// INSERT INTO Periodical VALUES (2,  'Magazine', 'Discover', 1, '2020-12-01');
// INSERT INTO Periodical VALUES (3,  'Journal', 'Variety', 1, '2018-11-07');
// INSERT INTO Periodical VALUES (6,  'Journal', 'ESPN', 1, '2022-01-14');
// INSERT INTO Periodical VALUES (7,  'Magazine', 'Revolver', 2, '2021-09-01');
// INSERT INTO Periodical VALUES (8,  'Magazine', 'Hoop', 1, '2021-10-01');
// INSERT INTO Periodical VALUES (9,  'Journal', 'Ancient', 3, '2021-12-21'); 
		/* ------------------------------------------------------------------ */


		/* Chapter */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Chapter VALUES "  + "\n" + "\t" +
				"(4,  1, 'The Boy Who Lived')," + "\n" + "\t" +
				"(4,  2, 'The Vanishing Glass')," + "\n" + "\t" +
				"(4,  3, 'The Letters From No One')," + "\n" + "\t" +
				"(4,  4, 'The Keeper Of Keys')," + "\n" + "\t" +
				"(1,  1, 'Vacuum')," + "\n" + "\t" +
				"(1,  2, 'Run Maddy Run')," + "\n" + "\t" +
				"(5,  1, 'England')," + "\n" + "\t" +
				"(10,  1, 'Focus')"  + "\n" +
			";" + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
// INSERT INTO Chapter VALUES (4,  1, 'The Boy Who Lived');
// INSERT INTO Chapter VALUES (4,  2, 'The Vanishing Glass');
// INSERT INTO Chapter VALUES (4,  3, 'The Letters From No One');
// INSERT INTO Chapter VALUES (4,  4, 'The Keeper Of Keys');
// INSERT INTO Chapter VALUES (1,  1, 'Vacuum');
// INSERT INTO Chapter VALUES (1,  2, 'Run Maddy Run');
// INSERT INTO Chapter VALUES (5,  1, 'England');
// INSERT INTO Chapter VALUES (10,  1, 'Focus');
		/* ------------------------------------------------------------------ */


		/* Article */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Article VALUES "  + "\n" + "\t" +
				"(2,  1, 'Nuclear Bomb', '2020-11-11')," + "\n" + "\t" +
				"(2,  2, 'Magnetism', '2020-11-15')," + "\n" + "\t" +
				"(3,  1, 'Gossip Column', '2018-10-17')," + "\n" + "\t" +
				"(6,  1, 'Manchester Derby', '2021-12-28')," + "\n" + "\t" +
				"(7,  1, 'Top 10 Country Songs', '2021-08-24')," + "\n" + "\t" +
				"(8,  1, 'The Last Dance', '2021-09-25')," + "\n" + "\t" +
				"(9,  1, 'Pyramids Of Giza', '2021-12-12')," + "\n" + "\t" +
				"(9,  2, 'The 8th Wonder', '2021-12-19')"  + "\n" +
			";" + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
// INSERT INTO Article VALUES (2,  1, 'Nuclear Bomb', '2020-11-11');
// INSERT INTO Article VALUES (2,  2, 'Magnetism', '2020-11-15');
// INSERT INTO Article VALUES (3,  1, 'Gossip Column', '2018-10-17');
// INSERT INTO Article VALUES (6,  1, 'Manchester Derby', '2021-12-28');
// INSERT INTO Article VALUES (7,  1, 'Top 10 Country Songs', '2021-08-24');
// INSERT INTO Article VALUES (8,  1, 'The Last Dance', '2021-09-25');
// INSERT INTO Article VALUES (9,  1, 'Pyramids Of Giza', '2021-12-12');
// INSERT INTO Article VALUES (9,  2, 'The 8th Wonder', '2021-12-19');
		/* ------------------------------------------------------------------ */


		/* Contributor */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Contributor VALUES "  + "\n" + "\t" +
				"(1,  'J.K. Rowling', 'Author', 'Staff')," + "\n" + "\t" +
				"(2,  'Kate Fagan', 'Author', 'Staff')," + "\n" + "\t" +
				"(3,  'Martin Gilbert', 'Author', 'Invited')," + "\n" + "\t" +
				"(4,  'James Clear', 'Author', 'Invited')," + "\n" + "\t" +
				"(5,  'Bob Woodward', 'Author', 'Staff')," + "\n" + "\t" +
				"(6,  'Barbara Walter', 'Author', 'Staff')," + "\n" + "\t" +
				"(7,  'Anna Wintour', 'Editor', 'Staff')," + "\n" + "\t" +
				"(8,  'Dasha Gold', 'Editor', 'Invited')," + "\n" + "\t" +
				"(9,  'Emmanuelle', 'Editor', 'Staff')," + "\n" + "\t" +
				"(10,  'Carine Roitfeld', 'Editor', 'Invited')"  + "\n" +
			";" + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
// INSERT INTO Contributor VALUES (1,  'J.K. Rowling', 'Author', 'Staff');
// INSERT INTO Contributor VALUES (2,  'Kate Fagan', 'Author', 'Staff');
// INSERT INTO Contributor VALUES (3,  'Martin Gilbert', 'Author', 'Invited');
// INSERT INTO Contributor VALUES (4,  'James Clear', 'Author', 'Invited');
// INSERT INTO Contributor VALUES (5,  'Bob Woodward', 'Author', 'Staff');
// INSERT INTO Contributor VALUES (6,  'Barbara Walter', 'Author', 'Staff');
// INSERT INTO Contributor VALUES (7,  'Anna Wintour', 'Editor', 'Staff');
// INSERT INTO Contributor VALUES (8,  'Dasha Gold', 'Editor', 'Invited');
// INSERT INTO Contributor VALUES (9,  'Emmanuelle', 'Editor', 'Staff');
// INSERT INTO Contributor VALUES (10,  'Carine Roitfeld', 'Editor', 'Invited');
		/* ------------------------------------------------------------------ */


		/* Edits */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Edits VALUES "  + "\n" + "\t" +
				"(1, 7)," + "\n" + "\t" +
				"(2, 8)," + "\n" + "\t" +
				"(3, 9)," + "\n" + "\t" +
				"(4, 10)"  + "\n" +
			";" + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
// INSERT INTO Edits VALUES (1, 7);
// INSERT INTO Edits VALUES (2, 8);
// INSERT INTO Edits VALUES (3, 9);
// INSERT INTO Edits VALUES (4, 10);

		/* ------------------------------------------------------------------ */

		/* AuthorsArticle */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO AuthorsArticle VALUES "  + "\n" + "\t" +
				"(2, 1, 4)," + "\n" + "\t" +
				"(2, 2, 4)," + "\n" + "\t" +
				"(3, 1, 2)," + "\n" + "\t" +
				"(6, 1, 3)," + "\n" + "\t" +
				"(7, 1, 1)," + "\n" + "\t" +
				"(8, 1, 5)," + "\n" + "\t" +
				"(9, 1, 6)," + "\n" + "\t" +
				"(9, 2, 6)"  + "\n" +
			";" + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
// INSERT INTO AuthorsArticle VALUES (2, 1, 4);
// INSERT INTO AuthorsArticle VALUES (2, 2, 4);
// INSERT INTO AuthorsArticle VALUES (3, 1, 2);
// INSERT INTO AuthorsArticle VALUES (6, 1, 3);
// INSERT INTO AuthorsArticle VALUES (7, 1, 1);
// INSERT INTO AuthorsArticle VALUES (8, 1, 5);
// INSERT INTO AuthorsArticle VALUES (9, 1, 6);
// INSERT INTO AuthorsArticle VALUES (9, 2, 6);

		/* ------------------------------------------------------------------ */
		
		/* AuthorsBook */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO AuthorsBook VALUES "  + "\n" + "\t" +
				"(1, 2)," + "\n" + "\t" +
				"(4, 1)," + "\n" + "\t" +
				"(5, 3)," + "\n" + "\t" +
				"(10, 4)"  + "\n" +
			";" + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
// INSERT INTO AuthorsBook VALUES (1, 2);
// INSERT INTO AuthorsBook VALUES (4, 1);
// INSERT INTO AuthorsBook VALUES (5, 3);
// INSERT INTO AuthorsBook VALUES (10, 4);

		/* ------------------------------------------------------------------ */

		/* Address */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Address VALUES "  + "\n" + "\t" +
				"('1615 S Wilmington St', 'Raleigh')," + "\n" + "\t" +
				"('230 East Cameron Ave', 'Chapel Hill')"  + "\n" +
			";" + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
// INSERT INTO Address VALUES ('1615 S Wilmington St', 'Raleigh');
// INSERT INTO Address VALUES ('230 East Cameron Ave', 'Chapel Hill');
		/* ------------------------------------------------------------------ */

		/* Distributor */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Distributor VALUES "  + "\n" + "\t" +
				"(1, 'Whole Order', 0, '5647891452', '1615 S Wilmington St', 'Mark Austin', 'Wholesale Distributor')," + "\n" + "\t" +
				"(2, 'Davis Library', 0, '7645801443', '230 East Cameron Ave', 'Christina Higgins', 'Library')"  + "\n" +
			";" + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
//         INSERT INTO Distributor VALUES (1, 'Whole Order', 0, '5647891452', '1615 S Wilmington St', 'Mark Austin', 'Wholesale Distributor');
// INSERT INTO Distributor VALUES (2, 'Davis Library', 0, '7645801443', '230 East Cameron Ave', 'Christina Higgins', 'Library');
		/* ------------------------------------------------------------------ */

		/* Orders */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Orders VALUES "  + "\n" + "\t" +
				"(1, 1, 1, 30, 15, '2022-03-21', 3, 'Order Received')," + "\n" + "\t" +
				"(2, 2, 3, 20, 10, '2022-03-09', 2, 'Delivered')," + "\n" + "\t" +
				"(3, 2, 5, 15, 20, '2022-03-15', 1, 'Shipped')," + "\n" + "\t" +
				"(4, 1, 7, 25, 30, '2022-03-29', 2, 'Order Received')"  + "\n" +
			";" + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
// INSERT INTO Orders VALUES (1, 1, 1, 30, 15, '2022-03-21', 3, 'Order Received');
// INSERT INTO Orders VALUES (2, 2, 3, 20, 10, '2022-03-09', 2, 'Delivered');
// INSERT INTO Orders VALUES (3, 2, 5, 15, 20, '2022-03-15', 1, 'Shipped');
// INSERT INTO Orders VALUES (4, 1, 7, 25, 30, '2022-03-29', 2, 'Order Received');
		/* ------------------------------------------------------------------ */


		/* Transaction */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Transaction VALUES "  + "\n" + "\t" +
				"(1, 450, '2022-03-21')," + "\n" + "\t" +
				"(2, 200, '2022-03-09')," + "\n" + "\t" +
				"(3, 2500, '2020-01-01')," + "\n" + "\t" +
				"(4, 3000, '2020-01-01')"  + "\n" +
			";" + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
// INSERT INTO Transaction VALUES (1, 450, '2022-03-21');
// INSERT INTO Transaction VALUES (2, 200, '2022-03-09');
// INSERT INTO Transaction VALUES (3, 2500, '2020-01-01');
// INSERT INTO Transaction VALUES (4, 3000, '2020-01-01');

		/* ------------------------------------------------------------------ */

		/* Bills */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Bills VALUES "  + "\n" + "\t" +
				"(1, 1, 'False', NULL)," + "\n" + "\t" +
				"(2, 1, 'False', NULL)"  + "\n" +
			";" + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
// INSERT INTO Bills VALUES (1, 1, 'False', NULL);
// INSERT INTO Bills VALUES (2, 1, 'False', NULL);


		/* ------------------------------------------------------------------ */

		/* Wages */ 
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Wages VALUES "  + "\n" + "\t" +
				"(3, 1, 'Salary', NULL)," + "\n" + "\t" +
				"(4, 2, 'Salary', NULL)"  + "\n" +
			";" + "\n"
		;
		System.out.println(SQL);
		statement.executeUpdate(SQL);
// INSERT INTO Wages VALUES (3, 1, 'Salary', NULL);
// INSERT INTO Wages VALUES (4, 2, 'Salary', NULL);

		/* ------------------------------------------------------------------ */

	}
	

/* ################################################################################################################################################################# */
	/* ********************************************************************** */
	/* **************************** Print Tables **************************** */
	/* ********************************************************************** */
/* ################################################################################################################################################################# */



	private static void printTables() {

	}

	private static void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (result != null) {
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}