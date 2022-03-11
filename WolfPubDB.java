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
		close();
	}

	private static void initialize() {
		try {
			connectToDatabase();
            createTables();
            populateTables();
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
            for (String table : tables) 
            {   
                System.out.println("DROP TABLE IF EXISTS " + table + ";");
                statement.executeUpdate("DROP TABLE IF EXISTS " + table + ";");
            }
		} catch (SQLException e) {
		}
	}

    private static void createTables() throws SQLException {
        String SQL =    "CREATE TABLE Book (" +
                        "Name VARCHAR(10)," +
                        "Location VARCHAR(30)," +
			            "TuitionFees INTEGER, " +
                        "LivingExpenses INTEGER" +
                        ");"
            ;
        System.out.println(SQL);
        statement.executeUpdate(SQL);

    }

    private static void populateTables() throws SQLException {
        String SQL =    "INSERT INTO Schools VALUES" +
                        "('NC State', 'North Carolina', 24000, 20000), " +
                        "('NC Sate', 'North Carolina', 23456, 20000), " +
                        "('NC Stte', 'North Carolina', 24043, 20000), " +
                        "('N State', 'North Carolina', 24000, 20240) " +
                        ";"
        ;
        System.out.println(SQL);
        statement.executeUpdate(SQL);     
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