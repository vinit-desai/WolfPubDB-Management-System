/**
 * This file describes the WolfPubDB class which is used by the code in the
 * rest of the project to connect to the database and execute SQL statements
 * and transactions.
 *
 * - IMPORTANT NOTE - Relpace all $USER$ with your unity id and $PASSWORD$ 
 *	with your 9 digit student id or updated password (if changed).
 */

import java.sql.*;
import java.lang.StringBuilder;
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
					// just print results to console
					printResultSet(resultSet);
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

	// public static void printResultSet(ResultSet rs) throws SQLException {
	// 	ResultSetMetaData rsmd = rs.getMetaData();
	// 	int columnsNumber = rsmd.getColumnCount();

	// 	while (rs.next()) {
	// 		for (int i = 1; i <= columnsNumber; i++) {
	// 			if (i > 1) System.out.print(",  ");
	// 			String columnValue = rs.getString(i);
	// 			System.out.print(columnValue + " " + rsmd.getColumnName(i));
	// 		}
	// 		System.out.println("");
	// 	}
	// }

	private static void getDividerString(int[] columnSizes, StringBuilder builder) {
		for (int i=0; i<columnSizes.length; i++) {
			builder.append("|-");
			for (int j=0; j<columnSizes[i]; j++) {
				builder.append('-');
			}
			builder.append('-');
		}
		builder.append("|\n");
	}

	private static void getHeaderString(int[] columnSizes, ResultSetMetaData rsmd, StringBuilder builder) throws SQLException {
		for (int i=0; i<columnSizes.length; i++) {
			String formatter = String.format("| %%%ds ", columnSizes[i]);
			builder.append(String.format(formatter, rsmd.getColumnName(i+1)));
		}
		builder.append("|\n");
	}

	private static void getRowString(int[] columnSizes, ResultSet rs, StringBuilder builder) throws SQLException {
		for (int i=0; i<columnSizes.length; i++) {
			String formatter = String.format("| %%%ds ", columnSizes[i]);
			builder.append(String.format(formatter, rs.getString(i+1)));
		}
		builder.append("|\n");
	}

	public static void printResultSet(ResultSet rs) throws SQLException {
		StringBuilder builder = new StringBuilder();

		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();

		/* get max length of each column */
		int[] columnSizes = new int[columnsNumber]; 
		for (int i = 1; i <= columnsNumber; i++) {
			columnSizes[i-1] = rsmd.getColumnName(i).length();
		}
		while (rs.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				int valueLength = rs.getString(i).length();
				if (valueLength > columnSizes[i-1]) {
					columnSizes[i-1] = valueLength;
				}
			}
		}
		
		/* move back to front of result set */
 		rs.beforeFirst(); 

		/* build string that will be printed */
		builder.append('\n');
		getDividerString(columnSizes, builder);
		getHeaderString(columnSizes, rsmd, builder);
		getDividerString(columnSizes, builder);
		while (rs.next()) {
			getRowString(columnSizes, rs, builder);
		}
		getDividerString(columnSizes, builder);
		builder.append('\n');

		/* print the string */
		System.out.print(builder.toString());
	}
}