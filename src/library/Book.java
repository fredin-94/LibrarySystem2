package library;

import java.time.LocalDate;
import java.util.*;
import static java.time.temporal.ChronoUnit.DAYS;

/**<I, the author, am a jackass!>
 * 
 * @author Hanien Kobus
 * @author Oliver Manzi
 * */
public class Book {

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
		returnDate = LocalDate.of(2017, 10, 31);
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Hanien Kobus
	 * */
	public String getTitle() {
		return this.title;
	}


	/**<I, the author, am a jackass!>
	 * 
	 * @author Hanien Kobus
	 * */
	public String getAuthors() {
		return authors;
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Hanien Kobus
	 * */
	public String getGenre() {
		return this.genre;
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Hanien Kobus
	 * */
	public String getPublisher() {
		return this.publisher;
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Hanien Kobus
	 * */
	public String getShelf() {
		return this.shelf;
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Hanien Kobus
	 * */
	public void setShelf(String shelf) throws Exception {
		if (shelf.equals("")) {
			throw new Exception("A shelf must be chosen in order to move the book.");
		} else {
			this.shelf = shelf;
		}
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Hanien Kobus
	 * */
	public int getTimesBorrowed() {
		return this.timesBorrowed;
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Hanien Kobus
	 * */
	public LocalDate getReturnDate() {
		return this.returnDate;
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Hanien Kobus
	 * */
	public void setReturnDate(LocalDate returnDate) throws Exception {
		if (returnDate.equals(null)) {
			throw new Exception("Date can't be empty.");
		} else {
			this.returnDate = returnDate;
		}
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Fabian Fröding
	 * */
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

	/**<I, the author, am a jackass!>
	 * 
	 * @author Hanien Kobus
	 * */
	public void incrementTimesBorrowed() {
		this.timesBorrowed++;
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Hanien Kobus
	 * */
	public void setTimesBorrowed(int timesBorrowed) {
		this.timesBorrowed = timesBorrowed;
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Hanien Kobus
	 * @author Oliver Manzi
	 * 
	 * @version 1.0 Created by Hanien: Displays customer information.
	 * @version 1.1 Modified by Oliver: Distinguishes "prints" from attributes"
	 * */
	@Override
	public String toString() {
		return "\n" + "||----------------------------------------------------------||\n" + "Book Title: " + this.title
				+ "\n • Authors: " + this.authors + "\n • Genre: " + this.genre + "\n • Publisher: " + this.publisher
				+ "\n • Shelf: " + this.shelf + "\n • Times Borrowed: " + this.timesBorrowed;
	}
	
	/**<I, the author, am a jackass!>
	 * 
	 * @author Lucas Fredin
	 * */
	public String toStringCurrentLoans() {
		return "\n" + "||----------------------------------------------------------||\n" + "Book Title: " + this.title
				+ "\n • Authors: " + this.authors + "\n • Genre: " + this.genre + "\n • Publisher: " + this.publisher
				+ "\n • Shelf: " + this.shelf + "\n • Returndate: " + this.returnDate;
	}

}