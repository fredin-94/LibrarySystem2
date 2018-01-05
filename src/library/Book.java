package library;
import java.time.LocalDate;

import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class Book {

	private UUID id;
	private String title;
	private String authors;
	private String genre;
	private String publisher;
	private String shelf;
	private int timesBorrowed = 0;
	final static int TWO_WEEKS = 2;
	private LocalDate returnDate;

	
	/**
	 * This class is a representation of Book objects in the library system.
	 *
	 * @author Hanien Kobus
	 * @Editor Oliver Manzi
	 * */
	public Book(String title, String authors, String publisher, String genre, String shelf) throws Exception {


		this.title = title;
		this.authors = authors;
		this.genre = genre;
		this.publisher = publisher;
		this.shelf = shelf;
		returnDate = LocalDate.of(2017, 10, 31);// homage to the first mini-project lecture... before we knew how fucked
												// this was
	}

	/**
	 * Returns the title of this book object
	 * */
	public String getTitle() {
		return this.title;
	}


	/**
	 * Returns the author(s) of this book object
	 * */
	public String getAuthors() {
		return authors;
	}

	/**
	 * Return the genre of this book object
	 * */
	public String getGenre() {
		return this.genre;
	}

	/**
	 * Returns the publisher of this book object
	 * */
	public String getPublisher() {
		return this.publisher;
	}

	/**
	 * Returns the shelf of this book object
	 * */
	public String getShelf() {
		return this.shelf;
	}

	/**
	 * Returns the number of times this book objects has been borrowed
	 * */
	public int getTimesBorrowed() {
		return this.timesBorrowed;
	}

	/**
	 * Returns the return date of this book object
	 * */
	public LocalDate getReturnDate() {
		return this.returnDate;
	}

	/**
	 * Sets the return date of this book object
	 *
	 * @throws Exception
	 * */
	public void setReturnDate(LocalDate returnDate) throws Exception {
		if (returnDate.equals(null)) {
			throw new Exception("Date can't be empty.");
		} else {
			this.returnDate = returnDate;
		}
	}

	/**
	 * Capitalizes the first letters of the attributes of this book object
	 * */
	public void firstLettersToUpperCase() {
		this.title = firstLetterInStringToUpperCase(this.title);
		this.authors = firstLetterInStringToUpperCase(this.authors);
		this.genre = firstLetterInStringToUpperCase(this.genre);
		this.publisher = firstLetterInStringToUpperCase(this.publisher);
		this.shelf = firstLetterInStringToUpperCase(this.shelf);
	}

	/**
	 * Capitalizes the first letter in a string.
	 * */
	private String firstLetterInStringToUpperCase(String whichString) {
		String s = (whichString.charAt(0) + "").toUpperCase();
		for (int i = 1; i < whichString.length(); i++)
			s += whichString.charAt(i);
		return s;
	}

	/**
	 * Increments the number of times borrowed for this book object
	 * */
	public void incrementTimesBorrowed() {
		this.timesBorrowed++;
	}

	/**
	 * Sets the number of times borrowed for this book object
	 *
	 * @throws Exception
	 * */
	public void setTimesBorrowed(int timesBorrowed) throws Exception{
		if(timesBorrowed < 0){
			throw new Exception("The number of times borrowed cannot be negative.");
		}
		this.timesBorrowed = timesBorrowed;
	}

	/**
	 * Displays this object's information in string format
	 * */
	@Override
	public String toString() {
		return "\n" + "||----------------------------------------------------------||\n" + "Book Title: " + this.title
				+ "\n • Authors: " + this.authors + "\n • Genre: " + this.genre + "\n • Publisher: " + this.publisher
				+ "\n • Shelf: " + this.shelf + "\n • Times Borrowed: " + this.timesBorrowed;
	}
	
	/**
	 * Displays this object's information including return date in string format
	 * */
	public String toStringCurrentLoans() {
		return "\n" + "||----------------------------------------------------------||\n" + "Book Title: " + this.title
				+ "\n • Authors: " + this.authors + "\n • Genre: " + this.genre + "\n • Publisher: " + this.publisher
				+ "\n • Shelf: " + this.shelf + "\n • Returndate: " + this.returnDate;
	}

}