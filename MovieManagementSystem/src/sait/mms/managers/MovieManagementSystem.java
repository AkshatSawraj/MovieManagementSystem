package sait.mms.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import sait.mms.drivers.MariaDBDriver;
import sait.mms.problemdomain.Movie;

/**
 * MovieManagementSystem - a class that have the display menu method and
 * different method that work with the MariaDB database system to performe
 * queries.
 * 
 * @author Nagi Nabal, Prince Bansal, Akshat Sawraj.
 * @version 22 April, 2021.
 * 
 */
public class MovieManagementSystem {

	MariaDBDriver dbDriver;// create an object from MariaDBDriver.
	static Scanner in = new Scanner(System.in);// create a scanner object to read from the input user.
	ArrayList<Movie> moviesList = new ArrayList<Movie>();// create an ArrayList to hold the movie list.

	/**
	 * displayMenu - a mehtod that display the menu to the user user consol to
	 * interact easily with the database.
	 * 
	 */
	public void displayMenu() {

		dbDriver = new MariaDBDriver(); // assign the new MariaDBDriver to the an object called dbDriver.
		int selection = 0;
		selection = printMenu(in);
		while (selection != 5) {
			switch (selection) {
			case 1:
				addMovie();
				break;
			case 2:
				printMoviesInYear();
				break;
			case 3:
				printRandomMovies();
				break;
			case 4:
				deleteMovie();
				break;
			case 5:
				break;
			default:
				System.out.printf("Invalid option:%d\nTry Again!\n\n", selection);
				selection = printMenu(in);
			}
			// print the menu again.
			selection = printMenu(in);
			// will excute the program if you input number 5.
			if (selection == 5)
				System.out.println("Thank You!\nHave a good Day.");
		} // end while loop.
	}// end displayMenu method.

	/**
	 * addMovie - a method that make a connection with the database and inset a new
	 * movie to the database.
	 * 
	 */
	public void addMovie() {
		System.out.print("\nEnter the duration:");
		int duration = 0;
		try {
			duration = in.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid Input!");
		}
		System.out.print("Enter the title: ");
		String title1 = in.next();
		System.out.print("Enter the Year: ");
		String year = in.next();
		// try block.
		try {
			dbDriver.connect();// make a connection with the databse.
			// create a stetment to insert the new value.
			String sqlStatement = "INSERT INTO movies" + "(duration,title, year) VALUES ('" + duration + "', '" + title1
					+ "','" + year + "')";
			// update the database with the new row.
			int rows = dbDriver.update(sqlStatement); // Display the results.
			System.out.println(rows + " row(s) added to the table.\n\n");
			dbDriver.disconnect();// disconnect with the database.
		} // end try block.
			// catch block.
		catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage() + "\n\n");
		} // end catch block.
	}// end addMovie method.

	/**
	 * printMoviesInYear - a method that print a specific movies from the database
	 * based on a specific year that the use inter.
	 *
	 */
	public void printMoviesInYear() {
		System.out.print("\n");
		System.out.print("Enter the Year:");
		int year = 0;
		try {
			year = in.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid Input!");
		}
		moviesList.clear();// claer the ArrayList.
		// try block.
		try {
			dbDriver.connect();// make the connection with the database.
			// retrieve the data from the database based on the year input.
			ResultSet retrivedData = dbDriver.get("SELECT * FROM movies WHERE year='" + year + "';");
			while (retrivedData.next()) {
				moviesList.add(new Movie(retrivedData.getInt("Duration"), retrivedData.getString("title"),
						retrivedData.getDate("year")));
			} // end while loop.
			dbDriver.disconnect();// disconnect with the database.
		} // end try block.
			// catch block.
		catch (SQLException e) {
			e.printStackTrace();
		} // end catch block.
		System.out.print("\n");
		// print the movie information.
		System.out.printf("Duration: Year: Movie Title:");
		for (int i = 0; i < moviesList.size(); i++) {
			System.out.printf("\n%-11d%-15s%-11s", moviesList.get(i).getDuration(), moviesList.get(i).getYear(),
					moviesList.get(i).getTitle());
		} // end for loop.
		int result = findSumOfDuration(year);
		if (result != -1)
			System.out.println("\nTotal Duration: " + result + " Minutes.");
		else
			System.out.println("\nTotal Minutes count Failed");
		System.out.println("\n\n\n");
	}// end printMoviesInYear method.

	/**
	 * findSumOfDuration - a method that will be calculating total duration of the
	 * movies.
	 * 
	 * @param year - year will contain the year value for the data requested by
	 *             specific years.
	 * @return return integer
	 *
	 */
	public int findSumOfDuration(int year) {
		try {
			dbDriver.connect();// make the connection with the database.
			String sqlStatement = "SELECT SUM(duration) FROM movies " + "WHERE year = '" + String.valueOf(year) + "'";
			ResultSet result = dbDriver.get(sqlStatement);
			dbDriver.disconnect();// disconnect with the database.
			while (result.next())
				return (result.getInt("SUM(duration)"));
		} // end try block.
		catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
		} // end catch block.
		return -1;
	}

	/**
	 * printRandomMovies - a method that print a random movies from the database
	 * based on how many user want.
	 * 
	 */
	public void printRandomMovies() {
		// claer the ArrayList.
		moviesList.clear();
		System.out.print("\n");
		System.out.print("Enter Number of movies:");
		int limitBy = 0;
		try {
			limitBy = in.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid Input!");
		}
		// try block.
		try {
			dbDriver.connect();// make the connection with the database.
			// retrieve the data from the database based on the year input.
			ResultSet retrivedData = dbDriver.get("SELECT * FROM movies order by RAND() LIMIT " + limitBy + ";");
			// Display the contents of the result set.
			// The result set will have two columns.
			while (retrivedData.next()) {
				moviesList.add(new Movie(retrivedData.getInt("Duration"), retrivedData.getString("title"),
						retrivedData.getDate("year")));
			} // end while loop.
			dbDriver.disconnect();// disconnect with the database.
		} // end try block.
			// catch block.
		catch (SQLException e) {
			e.printStackTrace();
		} // end catch block.
		System.out.print("\n");
		// print the movie information.
		System.out.printf("Duration:  Year:          Movie Title:");
		for (int i = 0; i < moviesList.size(); i++) {
			System.out.printf("\n%-11d%-15s%-11s", moviesList.get(i).getDuration(), moviesList.get(i).getYear(),
					moviesList.get(i).getTitle());
		} // end for loop.
		System.out.println("\n\n\n");
	}// end printRandomMovies.

	/**
	 * deleteMovie - a method that delete a movie from the database based on the
	 * movie ID.
	 * 
	 */
	public void deleteMovie() {
		System.out.print("\n");
		System.out.print("Enter the moive ID: ");
		int id = in.nextInt();
		// try block.
		try {
			dbDriver.connect();// make the connection with the database.
			// if statement to call another method and return the id if they found or not.
			if (findAndDisplayProduct(id)) {
				System.out.print("Are you sure you want to delete " + "this item? (y/n): ");
				String sure = in.next();
				if (Character.toUpperCase(sure.charAt(0)) == 'Y') {
					String sqlStatement = "DELETE FROM movies " + "WHERE id = '" + id + "'";
					int rows = dbDriver.update(sqlStatement);
					System.out.println(rows + " row(s) deleted.");
				} else {
					System.out.println("The item was not deleted.");
				}
			} else {
				System.out.println("That movie was not found.");
				dbDriver.disconnect();// disconnect with the database.
			} // end try block.
		} // catch block.
		catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
		} // end catch block.
	}// end deleteMovie.

	/**
	 * findAndDisplayProduct - a method that receive one parameter called id to
	 * search through the database if it found it or not.
	 * 
	 * @param id - holds the id number that the user need to search for a movie
	 *           through the database.
	 * @return true or false.
	 */
	private boolean findAndDisplayProduct(int id) {
		// try block/.
		try {
			// create a statment to hold the sql statement.
			String sqlStatement = "SELECT * FROM movies WHERE id = '" + id + "';";
			// get the data from the database.
			ResultSet reterivedData = dbDriver.get(sqlStatement);
			while (reterivedData.next()) {
				System.out.println("Movie Found: " + reterivedData.getString("title"));
			} // end while loop.
			return true;
		} // end try block.
			// catch block.
		catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
		} // end catch block.
		return false;
	}// end findAndDisplayProduct method.

	/**
	 * printMenu - a method to display the menu to the user console.
	 * 
	 * @param in - to read the user input and select with option from the wit case.
	 * @return
	 */
	public static int printMenu(Scanner in) {
		System.out.printf("\nMovie Manager:-\n");
		System.out.printf("1. Add New Movie.\n");
		System.out.printf("2. Print Movie released in specific Year.\n");
		System.out.printf("3. Print Random list of movies.\n");
		System.out.printf("4. Delete a movie.\n");
		System.out.printf("5. Exit\n\n");
		System.out.printf("Enter your selection: ");
		int select = in.nextInt();
		return select;
	}// end printMenu method.
}// end MovieManagementSyatem mehtod.
