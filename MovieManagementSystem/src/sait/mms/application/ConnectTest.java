package sait.mms.application;

import java.sql.ResultSet;
import java.sql.SQLException;

import sait.mms.contracts.DatabaseDriver;
import sait.mms.drivers.MariaDBDriver;

/**
 * ConnectTest - a class that simply make a connect and disconnect with the
 * database.
 *
 * @author Nagi Nabal, Prince Bansal, Akshat Sawraj.
 * @version 22 April, 2021.
 * 
 */
public class ConnectTest {// class begin
	/**
	 * Tests connecting to the database.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {// class begin
											// create an object from MariaDBDriver.
		DatabaseDriver driver = new MariaDBDriver();
		try {// try block.
				// make a connection with the database.
			driver.connect();
			// if successfuly connected will display connected.
			System.out.println("Connected!");
			ResultSet rs = driver.get("SELECT 'Hello from the other side!'");
			boolean hasRow = rs.next();
			// check if the database has a rows or not.
			if (hasRow) {
				String column1 = rs.getString(1);
				System.out.println(column1);
			} else {
				System.out.println("Ooops! No rows were found.");
			}
			// disconnect with the database.
			driver.disconnect();
		} catch (SQLException e) {// catch block.
			e.printStackTrace();
		} // end catch block.
	}// end main method.
}// end class ConnectTest.
