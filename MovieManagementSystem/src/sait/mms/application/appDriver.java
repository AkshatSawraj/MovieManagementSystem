package sait.mms.application;

import sait.mms.managers.MovieManagementSystem;

/**
 * appDriver - a class that has the main method to create an object from the
 * MovieManagementSystem class and display a menu to let the user interact with
 * the database.
 * 
 * @author Nagi Nabal, Prince Bansal, Akshat Sawraj.
 * @version 22 April, 2021.
 *
 */
public class appDriver {// class begin.

	/**
	 * main method to create an object and display the menu of teh aaplication.
	 * 
	 * @param args is an array of Strings passed as parameters when we are running
	 *             the application.
	 */
	public static void main(String[] args) {
		// create an object called managementSystem from the MovieManagementSystem
		// class.
		MovieManagementSystem managementSystem = new MovieManagementSystem();
		// display the menu to through the consol.
		managementSystem.displayMenu();
	}// end main method.
}// class end.
