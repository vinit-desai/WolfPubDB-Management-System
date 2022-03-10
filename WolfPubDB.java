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
	static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/$mgyoung$";
	// Put your oracle ID and password here

	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet result = null;

    private static String[] tables = {
        "Address",
        "Article",
        "AuthorsArticle",
        "AuthorsBook",
        "Bills",
        // "BillsDistributor",
        "Book",
        // "BookDetails",
        "Chapter",
        "Contributor",
        "Distributor",
        "Edits",
        "Orders",
        "Periodical",
        "Periodicity",
        // "PlacesOrder",
        "Publication",
        // "ReceivesWage",
        "Transaction",
        "Wages",

    };

	public static void main(String[] args) {

		initialize();

		// try {
		// 	boolean canAfford = checkAbilityToStudy("Todd");
		// 	// ************************************************************************

		// 	// modifyALittleBit1();
		// 	modifyALittleBit2();

		// 	boolean canAfford1 = checkAbilityToStudy("Angela");

		// 	if (canAfford == canAfford1) {
		// 		System.out.println("Success");
		// 	} else {
		// 		System.out.println("Failure");
		// 	}

		// } catch (SQLException e) {
		// 	e.printStackTrace();
		// }
		// // ***********************************************************************
		close();
	}

	private static void initialize() {
		try {
			connectToDatabase();

            createTables();

            populateTables();

			// statement.executeUpdate("INSERT INTO Students VALUES ('Todd', 'NC State'," + " 18, 16000, 30000, 'M')");
			// statement.executeUpdate("INSERT INTO Students VALUES ('Max', 'Stanford'," + " 21, 20000, 70000, 'M')");
			// statement.executeUpdate("INSERT INTO Students VALUES ('Alex', 'UNC'," + " 19, 8000, 40000, 'M')");
			// statement.executeUpdate("INSERT INTO Students VALUES ('Natasha', 'Harvard'," + " 22, 15000, 75000, 'F')");
			// statement.executeUpdate("INSERT INTO Students VALUES ('Kelly', 'UCLA'," + " 23, 2000, 50000, 'F')");
			// statement.executeUpdate("INSERT INTO Students VALUES ('Angela', 'NYU'," + "18, 8000, 45000, 'F')");

			// statement.executeUpdate("CREATE TABLE Schools (Name VARCHAR(10), Location VARCHAR(30), "
			// 		+ "TuitionFees INTEGER, LivingExpenses INTEGER)");
			// statement.executeUpdate("INSERT INTO Schools VALUES ('NC State', 'North Carolina', 24000, 20000)");
			// statement.executeUpdate("INSERT INTO Schools VALUES ('Stanford', 'California', 44000, 35000)");
			// statement.executeUpdate("INSERT INTO Schools VALUES ('UNC', 'North Carolina', 34000, 20000)");
			// statement.executeUpdate("INSERT INTO Schools VALUES ('Harvard', 'Massachusetts', 50000, 38000)");
			// statement.executeUpdate("INSERT INTO Schools VALUES ('UCLA', 'California', 36000, 30000)");
			// statement.executeUpdate("INSERT INTO Schools VALUES ('NYU', 'New York', 22000, 41000)");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void connectToDatabase() throws ClassNotFoundException, SQLException {
		Class.forName("org.mariadb.jdbc.Driver");

		String user = "mgyoung";
		String password = "200009434";

		connection = DriverManager.getConnection(jdbcURL, user, password);
		statement = connection.createStatement();

		try {
			// statement.executeUpdate("DROP TABLE Students");
			// statement.executeUpdate("DROP TABLE Schools");
            for (String table : tables) 
            {
                statement.executeUpdate("DROP TABLE IF EXISTS " + table + ";");
            }
		} catch (SQLException e) {
		}
	}

    private static void createTables() throws SQLException {
        
        statement.executeUpdate(
            "CREATE TABLE Book (" +
                "Name VARCHAR(10)," +
                "Location VARCHAR(30)," +
			    "TuitionFees INTEGER, " +
                "LivingExpenses INTEGER," +
            ")"
        );

    }

    private static void populateTables() throws SQLException {
        statement.executeUpdate(
            "INSERT INTO Schools VALUES" +
                "('NC State', 'North Carolina', 24000, 20000) " +
                "('NC Sate', 'North Carolina', 23456, 20000) " +
                "('NC Stte', 'North Carolina', 24043, 20000) " +
                "('N State', 'North Carolina', 24000, 20240) " +
            ";"
        );
        
        statement.executeUpdate(
            "CREATE TABLE Book (" +
                "Name VARCHAR(10), Location VARCHAR(30)," +
			    "TuitionFees INTEGER, LivingExpenses INTEGER" +
            ");"
        );
        
    }

	// private static boolean checkAbilityToStudy(String studentName) {
	// 	try {
	// 		result = statement
	// 				.executeQuery("SELECT (FundingReceived+Income) AS TotalIncome, (TuitionFees+LivingExpenses) AS "
	// 						+ "TotalFees FROM Students, Schools WHERE Students.School = Schools.Name AND Students.Name "
	// 						+ "LIKE '" + studentName + "%'");

	// 		if (result.next()) {
	// 			return (result.getInt("TotalIncome") > result.getInt("TotalFees"));
	// 		}
	// 		throw new RuntimeException(studentName + " cannot be found.");
	// 	} catch (SQLException e) {
	// 		e.printStackTrace();
	// 	}
	// 	return false;
	// }

	// private static void modifyALittleBit1() throws SQLException {
	// 	statement.executeUpdate("UPDATE Students SET Income = 39000 WHERE Name LIKE 'Angela%'");
	// 	statement.executeUpdate("UPDATE Schools SET TuitionFees = 30000 WHERE Name = 'NYU'");
	// }

	// private static void modifyALittleBit2() throws SQLException {
	// 	statement.executeUpdate("UPDATE Students SET Income = 55000 WHERE Name LIKE 'Angela%'");
	// 	statement.executeUpdate("UPDATE Schools SET TuitionFees = 15000 WHERE Name = 'NYU'");
	// }

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