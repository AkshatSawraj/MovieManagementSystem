package sait.mms.drivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sait.mms.contracts.DatabaseDriver;

/**
 * MariaDBDriver - a driver class for connecting to and accessing a MySQL or
 * MariaDB database.
 *
 * @author Nagi Nabal, Prince Bansal, Akshat Sawraj.
 * @version 22 April, 2021.
 * 
 */
public class MariaDBDriver implements DatabaseDriver {

	// value needed to get a connection to the database.
	private static final String SERVER = "localhost";
	private static final int PORT = 3306;
	private static final String DATABASE = "cprg251";
	private static final String USERNAME = "cprg251";
	private static final String PASSWORD = "password";
	private Connection connection = null;

	/**
	 * Initializes the newly created JdbcDriver.
	 */
	public MariaDBDriver() {
	}

	/**
	 * Connect - a method that implement the connetion with the database.
	 * 
	 * @throws an SQLException if the connection did not connect successfuly.
	 */
	@Override
	public void connect() throws SQLException {
		String dsn = this.getDsn();
		connection = DriverManager.getConnection(dsn);
	}// end connect method.

	/**
	 * getDsn - a method that gets the data source name to connect to the database.
	 * 
	 * @return Data source Name (DSN).
	 */
	private String getDsn() {
		// Data source name is formatted as follows (for MariaDB):
		// jdbc:mariadb://localhost:3306/DB?user=root&password=myPassword
		String dataSourceName = String.format("jdbc:mariadb://%s:%d/%s?user=%s&password=%s", SERVER, PORT, DATABASE,
				USERNAME, PASSWORD);
		return dataSourceName;
	}// end getDsn mehtod.

	/**
	 * get - a method that receive String parameter and create a statment and excute
	 * the query.
	 * 
	 * @return results of the query executed.
	 * @throws SQLException.
	 */
	@Override
	public ResultSet get(String query) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet results = statement.executeQuery(query);
		return results;
	}// end get method.

	/**
	 * update - a method that receive String parameter and create a statment and
	 * excute the query to update.
	 * 
	 * @return ret of the query excuted.
	 * @throws SQLException.
	 */
	@Override
	public int update(String query) throws SQLException {
		Statement statement = connection.createStatement();
		int ret = statement.executeUpdate(query);
		return ret;
	}// end update method.

	/**
	 * disconnect - a method that close the connection with the database.
	 * 
	 */
	@Override
	public void disconnect() throws SQLException {
		if (connection != null && !connection.isClosed())
			connection.close();
	}// end disconnect method.
}// end MariaDBDriver class.
