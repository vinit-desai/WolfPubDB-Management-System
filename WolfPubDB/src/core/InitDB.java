/**
 * This script builds constructs the WolfPubDB for our project. Note that it
 * drops the existing database and its tables should it exist already. I.e.,
 * this script will remove the existing DB and rebuild it from scratch.
 *
 * - IMPORTANT NOTE - Relpace all $USER$ with your unity id and $PASSWORD$ 
 *	with your 9 digit student id or updated password (if changed).
 */

import java.sql.*;

// import WolfPubDB;

public class InitDB {

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
		dropTables();
		createTables();
		populateTables();
	}


	
/* ################################################################################################################################################################# */
	/* ********************************************************************** */
	/* *********************** Connect & Drop Tables ************************ */
	/* ********************************************************************** */
/* ################################################################################################################################################################# */



	private static void dropTables() {
		for (String table : tables) 
		{   
			String sql = "DROP TABLE IF EXISTS " + table + ";";
			System.out.println(sql);
			WolfPubDB.executeUpdate(sql);
		}
	}


	
/* ################################################################################################################################################################# */
	/* ********************************************************************** */
	/* *************************** Table Creation *************************** */
	/* ********************************************************************** */
/* ################################################################################################################################################################# */

	

	private static void createTables() {

		String SQL;

		/* Publication */
		/* ------------------------------------------------------------------ */
		SQL =
			"CREATE TABLE Publication ("  + "\n" + 
			"	PublicationID 	INT 		NOT NULL,"  + "\n" + 
			"	Type 		VARCHAR(16) 	NOT NULL,"  + "\n" + 
			"	PublishDate 	DATE 		NOT NULL,"  + "\n" + 
			"	Topic 		VARCHAR(128) 	NOT NULL,"  + "\n" + 
			"	PRIMARY KEY (PublicationID)"  + "\n" +
			");" + "\n"  + "\n"
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */
		
		/* Book */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Book(" + "\n" +
			"	PublicationID 	INT 			NOT NULL," + "\n" + 
			"	ISBN 			CHAR(10) 		NOT NULL," + "\n" + 
			"	Title 			VARCHAR(128) 	NOT NULL," + "\n" + 
			"	Edition 		INT 			NOT NULL," + "\n" + 
			"	CreationDate 	DATE 			NOT NULL," + "\n" + 
			"	UNIQUE(ISBN)," 	+ "\n" + 
			"	PRIMARY KEY (PublicationID)," + "\n" + 
			"	FOREIGN KEY (PublicationID)" + "\n" + 
			"		REFERENCES Publication (PublicationID)" + "\n" + 
			"		ON DELETE CASCADE" + "\n" + 
			"		ON UPDATE CASCADE" + "\n" +
			");" + "\n" + "\n" 
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */


		/* Periodicity */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Periodicity (" + "\n" + 
			"	Type 			VARCHAR(16)		NOT NULL," + "\n" +
			"	Periodicity 	VARCHAR(16) 	NOT NULL," + "\n" +
			"	PRIMARY KEY (Type)" + "\n" +
			");" + "\n" + "\n" 
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */


		/* Periodical */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Periodical (" + "\n" + 
			"	PublicationID 	INT 			NOT NULL," + "\n" +
			"	Type 			VARCHAR(16) 	NOT NULL," + "\n" + 
			"	Title 			VARCHAR(128) 	NOT NULL," + "\n" + 
			"	Issue 			INT 			NOT NULL," + "\n" + 
			"	IssueDate 		DATE			NOT NULL," + "\n" + 
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
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */


		/* Chapter */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Chapter (" + "\n" + 
			"	PublicationID	INT 			NOT NULL," + "\n" + 
			"	ChapterNumber 	INT 			NOT NULL," + "\n" + 
			"	Title 			VARCHAR(128) 	NOT NULL," + "\n" + 
			"	Text 			LONGTEXT 		NOT NULL," + "\n" + 
			"	PRIMARY KEY (PublicationID, ChapterNumber)," + "\n" +
			"	FOREIGN KEY (PublicationID)" + "\n" +
			"		REFERENCES Publication (PublicationID)" + "\n" + 
			"		ON DELETE CASCADE" + "\n" +
			"		ON UPDATE CASCADE" + "\n" +
			");" + "\n" + "\n" 
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */


		/* Article */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Article (" + "\n" +
			"	PublicationID 		INT 			NOT NULL," + "\n" + 
			"	SequenceNumber 		INT 			NOT NULL," + "\n" + 
			"	Title 				VARCHAR(128) 	NOT NULL," + "\n" +
			"	CreationDate 		DATE 			NOT NULL," + "\n" + 
			"	Text 				MEDIUMTEXT 		NOT NULL," + "\n" +
			"	PRIMARY KEY (PublicationID, SequenceNumber)," + "\n" + 
			"	FOREIGN KEY (PublicationID)" + "\n" + 
			"		REFERENCES Publication (PublicationID)" + "\n" + 
			"		ON DELETE CASCADE"+ "\n" +
			"		ON UPDATE CASCADE" + "\n" +
			");" + "\n" + "\n" 
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */


		/* Contributor */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Contributor (" + "\n" + 
			"	ContributorID	INT 			NOT NULL," + "\n" + 
			"	Name 			VARCHAR(128) 	NOT NULL," + "\n" +
			"	Position 		VARCHAR(16) 	NOT NULL," + "\n" + 
			"	Type 			VARCHAR(16) 	NOT NULL," + "\n" + 
			"	Age 			INT 			NOT NULL," + "\n" + 
			"	Gender 			CHAR(1) 		NOT NULL," + "\n" + 
			"	Phone 			CHAR(10) 		NOT NULL," + "\n" + 
			"	Email 			VARCHAR(128) 	NOT NULL," + "\n" + 
			"	Address			VARCHAR(128) 	NOT NULL," + "\n" + 
			"	PRIMARY KEY (ContributorID)" + "\n" +
			");" + "\n" + "\n" 
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */


		/* Edits */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Edits (" + "\n" + 
			"	PublicationID	INT		NOT NULL," + "\n" +
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
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */

		/* AuthorsArticle */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE AuthorsArticle (" + "\n" + 
			"	PublicationID		INT 	NOT NULL," + "\n" + 
			"	SequenceNumber		INT		NOT NULL," + "\n" + 
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
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */

		/* AuthorsBook */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE AuthorsBook (" + "\n" +
			"	PublicationID 	INT 	NOT NULL," + "\n" + 
			"	ContributorID 	INT 	NOT NULL," + "\n" + 
			"	PRIMARY KEY (PublicationID, ContributorID)," + "\n" + 
			"	FOREIGN KEY (PublicationID)"+ "\n" +
			"		REFERENCES Book (PublicationID)"+ "\n" +
			"		ON DELETE CASCADE" + "\n" + 
			"		ON UPDATE CASCADE," + "\n" + 
			"	FOREIGN KEY (ContributorID)"+ "\n" + 
			"		REFERENCES Contributor (ContributorID)"+ "\n" + 
			"		ON DELETE CASCADE" + "\n" + 
			"		ON UPDATE CASCADE" + "\n" +
			");" + "\n"  + "\n"
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */

		/* Address */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Address (" + "\n" + 
			"	StreetAddress	VARCHAR(128) NOT NULL," + "\n" +
			"	City 			VARCHAR(128) NOT NULL," + "\n" + 
			"	PRIMARY KEY (StreetAddress)" + "\n" +
			");" + "\n"  + "\n"
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */

		/* Distributor */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Distributor (" + "\n" + 
			"	DistributorID	INT 			NOT NULL," + "\n" + 
			"	Name 			VARCHAR(128) 	NOT NULL," + "\n" +
			"	Balance 		DECIMAL(14,2)	NOT NULL," + "\n" +
			"	PhoneNumber 	CHAR(10) 		NOT NULL," + "\n" + 
			"	StreetAddress 	VARCHAR(128) 	NOT NULL," + "\n" + 
			"	ContactPerson 	VARCHAR(128) 	NOT NULL," + "\n" + 
			"	Type 			VARCHAR(32) 	NOT NULL," + "\n" + 
			"	PRIMARY KEY (DistributorID)," + "\n" + 
			"	FOREIGN KEY (StreetAddress)" + "\n" + 
			"		REFERENCES Address (StreetAddress)" + "\n" + 
			"		ON UPDATE CASCADE" + "\n" +
			");" + "\n"  + "\n"
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */

		/* Orders */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Orders (" + "\n" + 
			"	OrderID 		INT 			NOT NULL," + "\n" + 
			"	DistributorID 	INT 			NOT NULL," + "\n" + 
			"	PublicationID 	INT 			NOT NULL," + "\n" +
			"	Units 			INT 			NOT NULL," + "\n" +
			"	PricePerUnit 	DECIMAL(7,2)	NOT NULL," + "\n" + 
			"	OrderDate 		DATE 			NOT NULL," + "\n" +
			"	ShippingCost 	DECIMAL(9,2)	NOT NULL," + "\n" + 
			"	Status 			VARCHAR(16) 	NOT NULL," + "\n" + 
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
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */


		/* Transaction */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Transaction (" + "\n" +  
			"	TransactionID	INT 			NOT NULL," + "\n" + 
			"	Amount 			DECIMAL(14,2) 	NOT NULL," + "\n" +
			"	IssueDate 		DATE 			NOT NULL," + "\n" + 
			"	PRIMARY KEY (TransactionID)" + "\n" +
			");" + "\n" 
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */

		/* Bills */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Bills (" + "\n" + "\t" +
			"	TransactionID	INT			NOT NULL," + "\n" + 
			"	DistributorID 	INT 		NOT NULL," + "\n" + 
			"	Paid 			BOOL 		NOT NULL," + "\n" +
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
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */

		/* Wages */
		/* ------------------------------------------------------------------ */
		SQL = 
			"CREATE TABLE Wages (" + "\n" +
			"	TransactionID	INT 			NOT NULL," + "\n" +
			"	ContributorID 	INT 			NOT NULL," + "\n" + 
			"	Type 			VARCHAR(32) 	NOT NULL," + "\n" + 
			"	ClaimDate 		DATE," + "\n" + 
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
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */

	}
	

/* ################################################################################################################################################################# */
	/* ********************************************************************** */
	/* ************************** Table Population ************************** */
	/* ********************************************************************** */
/* ################################################################################################################################################################# */


	private static void populateTables() {

		String SQL;
		
		/* Publication */
		/* ------------------------------------------------------------------ */
		SQL =
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
				"(10, 'Book', '2021-04-02', 'Self Help'),"  + "\n" + "\t" +
				"(1001, 'Book', '2018-10-10', 'Technology'),"  + "\n" + "\t" +
				"(1002, 'Periodical', '2020-02-24', 'Health'),"  + "\n" + "\t" +
				"(1003, 'Periodical', '2020-03-01', 'Science')"  + "\n" +
			";" + "\n" + "\n"
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */
		
		/* Book */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Book VALUES " + "\n" + "\t" +
				"(4, '0000000001', 'Harry Potter', 1, '1998-03-02')," + "\n" + "\t" +
				"(1, '0000000002', 'What Made Maddy Run', 1, '2000-12-10'),"  + "\n" + "\t" +
				"(5, '0000000003', 'Churchill: A life', 2, '2012-08-05'),"  + "\n" + "\t" +
				"(10, '0000000004', 'Atomic Habits', 1, '2006-06-09'),"  + "\n" + "\t" +
				"(1001, '0000012345', 'Introduction to Database', 2, '2017-03-17')"  + "\n" +
			";" + "\n" + "\n"
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */


		/* Periodicity */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Periodicity VALUES "  + "\n" + "\t" +
				"('Journal', 'Monthly')," + "\n" + "\t" +
				"('Magazine', 'Weekly')"  + "\n" +
			";" + "\n" + "\n"
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);
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
				"(9,  'Journal', 'Ancient', 3, '2021-12-21'),"  + "\n" + "\t" +
				"(1002,  'Magazine', 'Healthy Diet', 1, '2020-02-24'),"  + "\n" + "\t" +
				"(1003,  'Journal', 'Animal Science', 5, '2020-03-01')"  + "\n" +
			";" + "\n" + "\n"
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */


		/* Chapter */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Chapter VALUES "  + "\n" + "\t" +
				"(4,  1, 'The Boy Who Lived','Lorem Ipsum is simply dummy text...')," + "\n" + "\t" +
				"(4,  2, 'The Vanishing Glass','It was popularised in the 1960s...')," + "\n" + "\t" +
				"(4,  3, 'The Letters From No One','Lorem Ipsum is simply dummy text of the printing...')," + "\n" + "\t" +
				"(4,  4, 'The Keeper Of Keys', 'It was popularised in the 1960s with the release of Letraset...')," + "\n" + "\t" +
				"(1,  1, 'Vacuum', 'Lorem Ipsum is simply dummy text of the...')," + "\n" + "\t" +
				"(1,  2, 'Run Maddy Run', 'It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum...')," + "\n" + "\t" +
				"(5,  1, 'England', 'Lorem Ipsum is simply dummy text of...')," + "\n" + "\t" +
				"(10,  1, 'Focus', 'It was popularised in the 1960s with the release of...'),"  + "\n" + "\t" +
				"(1001,  1, 'Why Data?', 'Since the dawn of time, man has strived to store his data, whether it be on cave walls, papyrus sheets, or bits on a magnetic strip...'),"  + "\n" + "\t" +
				"(1001,  2, 'Relational Schemas', 'Tables, tables, tables.... Did I mention tables? That's how we store data in a relational DB...'),"  + "\n" + "\t" +
				"(1001,  3, 'SQL Basics', 'SQL is the language of databases. Though limited in its functionality, these limitations allow for efficiency during execution...'),"  + "\n" + "\t" +
				"(1001,  4, 'Transactions', 'Transactions allows mutliple queries and updates to run simultaneously on a DB while ensuring that the data always stays in a consistent state...')"  + "\n" +
			";" + "\n" + "\n"
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */


		/* Article */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Article VALUES "  + "\n" + "\t" +
				"(2,  1, 'Nuclear Bomb', '2020-11-11', 'Lorem Ipsum is simply dummy text...')," + "\n" + "\t" +
				"(2,  2, 'Magnetism', '2020-11-15', 'It was popularised in the 1960s with the release of Letraset...')," + "\n" + "\t" +
				"(3,  1, 'Gossip Column', '2018-10-17', 'Lorem Ipsum is simply dummy text of the printing...')," + "\n" + "\t" +
				"(6,  1, 'Manchester Derby', '2021-12-28', 'It was popularised in the 1960s with the release of Letraset sheets...')," + "\n" + "\t" +
				"(7,  1, 'Top 10 Country Songs', '2021-08-24', 'Lorem Ipsum is simply dummy tex...')," + "\n" + "\t" +
				"(8,  1, 'The Last Dance', '2021-09-25', 'It was popularised in the 1960s...')," + "\n" + "\t" +
				"(9,  1, 'Pyramids Of Giza', '2021-12-12', 'Lorem Ipsum is simply dummy text of the printing...')," + "\n" + "\t" +
				"(9,  2, 'The 8th Wonder', '2021-12-19', 'It was popularised in the 1960s with the release of...'),"  + "\n" + "\t" +
				"(1002,  1, 'Eat More Chicken', '2020-02-24', 'ABC'),"  + "\n" + "\t" +
				"(1003,  1, 'Why Cats are Evil', '2020-03-01', 'AAA')"  + "\n" +
			";" + "\n" + "\n"
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */


		/* Contributor */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Contributor VALUES "  + "\n" + "\t" +
				"(1,  'J.K. Rowling', 'Author', 'Staff', 56, 'F', '9477488475', 'imawizard@potter.com', '1200 Hogwarts Street, ENG')," + "\n" + "\t" +
				"(2,  'Kate Fagan', 'Author', 'Staff', 32, 'F', '8563156942', 'katefagan@gmail.com', '101 Main Street, NC 27513')," + "\n" + "\t" +
				"(3,  'Martin Gilbert', 'Author', 'Invited', 45, 'M', '3685149657', 'martingilbert@gmail.com', '345 Silver Church Road, NC 27845')," + "\n" + "\t" +
				"(4,  'James Clear', 'Author', 'Invited', 74, 'M', '4865123975', 'jameclear@gmail.com', '2306 Raleigh Road, NC 27530')," + "\n" + "\t" +
				"(5,  'Bob Woodward', 'Author', 'Staff', 21, 'M', '9863254178', 'bossboby@gmail.com', '310 Carytown Road, NC 27513')," + "\n" + "\t" +
				"(6,  'Barbara Walter', 'Author', 'Staff', 26, 'F', '6668453248', 'barbarawalter@yahoo.com', '598 Old Apex Road, NC 27516')," + "\n" + "\t" +
				"(7,  'Anna Wintour', 'Editor', 'Staff', 28, 'F', '3877749865', 'annawintour@yahoo.com', '234 Wake Avenue, NC 28645')," + "\n" + "\t" +
				"(8,  'Dasha Gold', 'Editor', 'Invited', 30, 'F', '5488675309', '24kgold@hotmail.com', '1602 Broadway, NY 15036')," + "\n" + "\t" +
				"(9,  'Emmanuelle', 'Editor', 'Staff', 54, 'M', '55555555555', 'emmanuelle@hotmail.com', '9849 Higher Living Circle, Apt. 235, NC 27348')," + "\n" + "\t" +
				"(10,  'Carine Roitfeld', 'Editor', 'Invited', 38, 'F', '9865321148', 'carineroitfeld@ncsu.edu', '666 Big Cow Road, TX 69420'),"  + "\n" + "\t" +
				"(3001,  'John', 'Editor', 'Staff', 36, 'M', '9391234567', '3001@gmail.com', '21 ABC St, NC 27'),"  + "\n" + "\t" +
				"(3002,  'Ethen', 'Editor', 'Staff', 30, 'M', '9491234567', '3002@gmail.com', '21 ABC St, NC 27606'),"  + "\n" + "\t" +
				"(3003,  'Cathy', 'Author', 'Invited', 28, 'F', '9591234567', '3003@gmail.com', '3300 AAA St, NC 27606')"  + "\n" +
			";" + "\n" + "\n"
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */


		/* Edits */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Edits VALUES "  + "\n" + "\t" +
				"(1, 7)," + "\n" + "\t" +
				"(2, 8)," + "\n" + "\t" +
				"(3, 9)," + "\n" + "\t" +
				"(4, 10),"  + "\n" + "\t" +
				"(1001, 3001),"  + "\n" + "\t" +
				"(1002, 3002)"  + "\n" +
			";" + "\n" + "\n"
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);

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
				"(9, 2, 6),"  + "\n" + "\t" +
				"(1002, 1, 3003),"  + "\n" + "\t" +
				"(1003, 1, 3003)"  + "\n" +
			";" + "\n" + "\n"
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);

		/* ------------------------------------------------------------------ */
		
		/* AuthorsBook */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO AuthorsBook VALUES "  + "\n" + "\t" +
				"(1, 2)," + "\n" + "\t" +
				"(4, 1)," + "\n" + "\t" +
				"(5, 3)," + "\n" + "\t" +
				"(10, 4),"  + "\n" + "\t" +
				"(1001, 3003)"  + "\n" +
			";" + "\n" + "\n"
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);

		/* ------------------------------------------------------------------ */

		/* Address */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Address VALUES "  + "\n" + "\t" +
				"('1615 S Wilmington St', 'Raleigh')," + "\n" + "\t" +
				"('230 East Cameron Ave', 'Chapel Hill'),"  + "\n" + "\t" +
				"( '3000 Cowboy Street', 'Greensboro')," + "\n" + "\t" +
				"('250 West Main Street', 'Apex')," + "\n" + "\t" +
				"('2200, A Street, NC', 'Charlotte')," + "\n" + "\t" +
				"('2200, B Street, NC', 'Raleigh')" + "\n" + "\t" +
			";" + "\n" + "\n"
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */

		/* Distributor */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Distributor VALUES "  + "\n" + "\t" +
				"(1, 'Whole Order', 243.56, '5647891452', '1615 S Wilmington St', 'Mark Austin', 'Wholesale Distributor')," + "\n" + "\t" +
				"(2, 'Davis Library', 2542.43, '7645801443', '230 East Cameron Ave', 'Christina Higgins', 'Library'),"  + "\n" + "\t" +
				"(3, 'Bobs Used Books', 126.98, '2537891452', '3000 Cowboy Street', 'Bob Bobertson', 'Book Store')," + "\n" + "\t" +
				"(4, 'Barnes & Noble of Apex', 48987.05, '7645864443', '250 West Main Street', 'Jerry Seinfeld', 'Book Store')," + "\n" + "\t" +
				"(2001, 'BookSell', 215.00, '9191234567', '2200, A Street, NC', 'Jason', 'Book Store')," + "\n" + "\t" +
				"(2002, 'BookDist', 0.00, '9291234568', '2200, B Street, NC', 'Alex', 'Wholesale Distributor')" + "\n" +
			";" + "\n" + "\n"
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */

		/* Orders */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Orders VALUES "  + "\n" + "\t" +
				"(1, 1, 1, 30, 15.95, '2022-03-21', 20.35, 'Order Received')," + "\n" + "\t" +
				"(2, 2, 3, 20, 10.99, '2022-03-09', 15.99, 'Delivered')," + "\n" + "\t" +
				"(3, 2, 5, 15, 20.98, '2022-03-15', 12.47, 'Shipped')," + "\n" + "\t" +
				"(4, 1, 7, 25, 30.49, '2022-03-29', 18.89, 'Order Received'),"  + "\n" + "\t" +
				"(5, 3, 1, 15, 15.95, '2022-03-15', 12.47, 'Delivered')," + "\n" + "\t" +
				"(6, 4, 5, 10, 20.98, '2022-03-29', 11.99, 'Delivered')," + "\n" + "\t" +
				"(7, 3, 3, 15, 21.99, '2022-03-15', 8.47, 'Order Received')," + "\n" + "\t" +
				"(8, 4, 5, 5, 22.98, '2022-03-29', 5.95,'Shipped')," + "\n" + "\t" +
				"(4001, 2001, 1001, 30, 20.00, '2020-01-02', 30.00, 'Delivered')," + "\n" + "\t" +
				"(4002, 2001, 1001, 10, 20.00, '2020-02-05', 15.00, 'Delivered')," + "\n" + "\t" +
				"(4003, 2002, 1003, 10, 10.00, '2020-02-10', 15.00, 'Delivered')" + "\n" +
			";" + "\n" + "\n"
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */


		/* Transaction */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Transaction VALUES "  + "\n" + "\t" +
				"(1, 450.00, '2020-03-21')," + "\n" + "\t" +
				"(2, 200.00, '2020-03-09')," + "\n" + "\t" +
				"(3, 2500.00, '2022-01-01')," + "\n" + "\t" +
				"(4, 3000.00, '2022-01-01'),"  + "\n" +"\t" +
				"(5, 600.00, '2022-03-09'),"  + "\n" + "\t" +
				"(6, 3200.00, '2022-03-03'),"  + "\n" +"\t" +
				"(7, 300.00, '2022-03-05'),"  + "\n" +"\t" +
				"(8, 1300.00, '2022-03-06'),"  + "\n" +"\t" +
				"(9, 300.00, '2022-03-10'),"  + "\n" + "\t" +
				"(10, 630.00, '2020-01-02'),"  + "\n" + "\t" +
				"(11, 215.00, '2020-02-05'),"  + "\n" + "\t" +
				"(12, 115.00, '2020-02-10'),"  + "\n" + "\t" +
				"(13, 1000.00, '2020-04-01'),"  + "\n" + "\t" +
				"(14, 1000.00, '2020-04-01'),"  + "\n" + "\t" +
				"(15, 1200.00, '2020-04-01')"  + "\n" + 
			";" + "\n" + "\n"
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);

		/* ------------------------------------------------------------------ */

		/* Bills */
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Bills VALUES "  + "\n" + "\t" +
				"(1, 1, True, '2020-03-28')," + "\n" + "\t" +
				"(2, 1, True,  '2020-03-16'),"  + "\n" +"\t" +
				"(6, 4, True,  '2022-03-05')," + "\n" + "\t" +
				"(7, 3, False, NULL)," + "\n" + "\t" +
				"(8, 4, True,  '2022-03-10')," + "\n" + "\t" +
				"(10, 2001, True,  '2020-01-15')," + "\n" + "\t" +
				"(11, 2001, False,  NULL)," + "\n" + "\t" +
				"(12, 2002, True,  '2020-02-25')" + "\n" +
			";" + "\n" + "\n"
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */

		/* Wages */ 
		/* ------------------------------------------------------------------ */
		SQL = 
			"INSERT INTO Wages VALUES "  + "\n" + "\t" +
				"(3, 1,  'Book Authorship', '2022-01-03')," + "\n" + "\t" +
				"(4, 7, 'Editorial Work', '2022-01-05'),"  + "\n" + "\t" +
				"(5, 1, 'Article Authorship', '2022-01-05'),"  + "\n" + "\t" +
				"(9, 1, 'Article Authorship', NULL),"  + "\n" + "\t" +
				"(13, 3001, 'Editorial Work', '2020-04-02'),"  + "\n" + "\t" +
				"(14, 3002, 'Editorial Work', '2020-04-02'),"  + "\n" + "\t" +
				"(15, 3003, 'Editorial Work', '2020-04-02')"  + "\n" +
			";" + "\n" + "\n"
		;
		System.out.println(SQL);
		WolfPubDB.executeUpdate(SQL);
		/* ------------------------------------------------------------------ */

	}
}