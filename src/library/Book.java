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

	public Book(String title, String authors, String publisher, String genre, String shelf) throws Exception {


		this.title = title;
		this.authors = authors;
		this.genre = genre;
		this.publisher = publisher;
		this.shelf = shelf;
		//returnDate = LocalDate.of(2017, 10, 31);// homage to the first mini-project lecture... before we knew how fucked
												// this was
	}

	public UUID getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}


	public String getAuthors() {
		return authors;
	}

	public String getGenre() {
		return this.genre;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public String getShelf() {
		return this.shelf;
	}

	public void setShelf(String shelf) throws Exception {
		if (shelf.equals("")) {
			throw new Exception("A shelf must be chosen in order to move the book.");
		} else {
			this.shelf = shelf;
		}
	}

	public int getTimesBorrowed() {
		return this.timesBorrowed;
	}

	public LocalDate getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(LocalDate returnDate) throws Exception {
		if (returnDate.equals(null)) {
			throw new Exception("Date can't be empty.");
		} else {
			this.returnDate = returnDate;
		}
	}

	public void firstLettersToUpperCase() {
		this.title = firstLetterInStringToUpperCase(this.title);
		this.authors = firstLetterInStringToUpperCase(this.authors);
		this.genre = firstLetterInStringToUpperCase(this.genre);
		this.publisher = firstLetterInStringToUpperCase(this.publisher);
		this.shelf = firstLetterInStringToUpperCase(this.shelf);
	}

	private String firstLetterInStringToUpperCase(String whichString) {
		String s = (whichString.charAt(0) + "").toUpperCase();
		for (int i = 1; i < whichString.length(); i++)
			s += whichString.charAt(i);
		return s;
	}

	public void incrementTimesBorrowed() {
		this.timesBorrowed++;
	}

	public void setTimesBorrowed(int timesBorrowed) {
		this.timesBorrowed = timesBorrowed;
	}

	@Override
	public String toString() {
		return "\n" + "||----------------------------------------------------------||\n" + "Book Title: " + this.title
				+ "\n • Authors: " + this.authors + "\n • Genre: " + this.genre + "\n • Publisher: " + this.publisher
				+ "\n • Shelf: " + this.shelf + "\n • Times Borrowed: " + this.timesBorrowed;
	}

}