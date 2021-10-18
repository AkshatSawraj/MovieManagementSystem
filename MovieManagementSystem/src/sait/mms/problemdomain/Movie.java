package sait.mms.problemdomain;

import java.sql.Date;

/**
 * Movie - a class that have a constructor with a specific attributes, also the
 * getters and the setters to get the information for each movie from the
 * database.
 * 
 * @author Nagi Nabal, Prince Bansal, Akshat Sawraj.
 * @version 22 April, 2021.
 * 
 */
public class Movie {

	//
	private int duration;
	private String title;
	private Date year;

	/**
	 * Movie - creat a Movie constructor with a specific attributes.
	 * 
	 * @param duration - holds the duration number of the movie.
	 * @param title    - holds the title name of the movie.
	 * @param year     - holds the year nuber of the movie.
	 * 
	 */
	public Movie(int duration, String title, Date year) {
		this.duration = duration;
		this.title = title;
		this.year = year;
	}// constructor end.

	/**
	 * getDuration - a method used to get the duration number of the number.
	 * 
	 * @return the duration of the movie.
	 * 
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * getTitle - a method used to get the title of the movie.
	 * 
	 * @return the title of the movie.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * getYear - a method used to get the year of the movie.
	 * 
	 * @return the year of the movie.
	 */
	public Date getYear() {
		return year;
	}

	/**
	 * setDuration - a method used to receive one parameters to set the durarion of
	 * the movie.
	 * 
	 * @param duration - holds the duration number of the movie.
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * setTitle - a method used to recieve one parameters to set the title of the
	 * movie.
	 * 
	 * @param title - holds the title of the movie.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * setYear - a method used to receive one parameters to set the year of the
	 * movie.
	 * 
	 * @param year - holds the year number of the movie.
	 */
	public void setYear(Date year) {
		this.year = year;
	}

	/**
	 * toString() - an Override the toString defined in the class.
	 */
	@Override
	public String toString() {
		return "duration=" + duration + ", title=" + title + ", year=" + year + "";
	}
}// end of the class.
