/**
 * This file describes the WolfPubDB class which is used by the code in the
 * rest of the project to connect to the database and execute SQL statements
 * and transactions.
 *
 * - IMPORTANT NOTE - Relpace all $USER$ with your unity id and $PASSWORD$ 
 *	with your 9 digit student id or updated password (if changed).
 */

import java.sql.*;
// import ExecResult;

public class WolfPubDB {
	
	/* Static Data - change to your user credentials */
	private static final String user = "vdesai5";
	private static final String password = "200368285";
	private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/"+user;

	// /* Instance Fields */
	// private Connection connection;

	// public WolfPubDB() {
	// 	this.connection = null;
	// }

	private static Connection connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.mariadb.jdbc.Driver");
		return DriverManager.getConnection(jdbcURL, user, password);
	}

	public static ExecResult executeQuery(String sql) {

		try (Connection connection = connect()) {

			try (Statement statement = connection.createStatement()) {
				ResultSet resultSet = statement.executeQuery(sql);
				if (resultSet != null) {
					// do something
					System.out.println("Results were returned!!!");
				}
			} catch (SQLException error) {
				return new ExecResult(false, "Problem Executing SQL Query");
			}

		} catch (ClassNotFoundException | SQLException e) {

			String errorMsg = "Unable to Connect Using jdbcURL: " + jdbcURL;
			return new ExecResult(false, errorMsg);

		}

		return new ExecResult(true, "");
	}

	public static ExecResult executeUpdate(String sql) {

		try (Connection connection = connect()) {

			try (Statement statement = connection.createStatement()) {
				statement.executeUpdate(sql);
			} catch (SQLException error) {
				return new ExecResult(false, "Problem Executing SQL Update");
			}

		} catch (ClassNotFoundException | SQLException e) {

			String errorMsg = "Unable to Connect Using jdbcURL: " + jdbcURL;
			return new ExecResult(false, errorMsg);

		}

		return new ExecResult(true, "");
	}
}