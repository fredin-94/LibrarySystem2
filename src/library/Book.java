package library;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import static library.Library.bookKey.*;

public class Book {

	//*** ATTRIBUTES ***//
	// G stuff
	private UUID id;
	private String title;
	private ArrayList<String> authors;
	private String genre;
	private String publisher;
	private String shelf;

	// VG stuff
	private int timesBorrowed = 0;
	final static int TWO_WEEKS = 2;
	private LocalDateTime startDate;
	private LocalDateTime returnDate;

	//*** CONSTRUCTOR ***//
	public Book(String title, String authors, String publisher, String genre, String shelf) throws Exception{

		if(title.equals("") || authors.equals("") || publisher.equals("") || genre.equals("") || shelf.equals("")){
			throw new Exception("Input argument cannot be empty.");
		} else {
			this.title = title;
			this.authors = new ArrayList<String>(Arrays.asList(authors.split(",")));
			this.genre = genre;
			this.publisher = publisher;
			this.shelf = shelf;
		}

		startDate = LocalDateTime.now();
		returnDate = LocalDateTime.now();
	}

	//*** GETTERS AND SETTERS ***//
	public UUID getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public String getAuthor() {
		if (this.authors.size() > 1) {
			return this.authors.get(0) + "," + this.authors.get(1);
		} else {
			return this.authors.get(0);
		}
	}

	public ArrayList<String> getAuthors(){
		/*String res = ": ";
		if(authors.size() == 1){
			return authors.get(0);
		} else {
			for(int i = 0; i < authors.size(); i++){
				if(i == authors.size() - 1) {
					res += authors.get(i);
				} else {
					res += authors.get(i) + ", ";
				}
			}
			return res;
		}*/
		return this.authors;
	}

	public String getGenre() {
		return this.genre;
	}

	public String getPublisher(){
		return this.publisher;
	}

	public String getShelf(){
		return this.shelf;
	}

	public int getTimesBorrowed(){
		return this.timesBorrowed;
	}

	public LocalDateTime getStartDate(){
		return this.startDate;
	}

	public LocalDateTime getReturnDate(){
		return this.returnDate;
	}

	// needed for when a customer borrows a book and startdate needs to be set.
	public void setStartDate(LocalDateTime startDate) throws Exception{
		if(startDate.equals(null)) {
			throw new Exception("Date can't be empty.");
		} else {
			this.startDate = startDate;
		}
	}

	// needed for when you want to extend loans
	public void setReturnDate(LocalDateTime returnDate) throws Exception{
		if(returnDate.equals(null)) {
			throw new Exception("Date can't be empty.");
		} else {
			this.returnDate = returnDate;
		}
	}

	public void setShelf(String shelf) throws Exception{
		if(shelf.equals("")){
			throw new Exception("A shelf must be chosen in order to move the book.");
		} else {
			this.shelf = shelf;
		}
	}

	public void firstLettersToUpperCase() {
		this.title = firstLetterInStringToUpperCase(this.title);
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < this.authors.size(); i++) list.add(firstLetterInStringToUpperCase(this.authors.get(i)));
		this.authors = list;
		this.genre = firstLetterInStringToUpperCase(this.genre);
		this.publisher = firstLetterInStringToUpperCase(this.publisher);
		this.shelf = firstLetterInStringToUpperCase(this.shelf);
	}
	private String firstLetterInStringToUpperCase(String whichString) {
		String s = (whichString.charAt(0) + "").toUpperCase();
		for (int i = 1; i < whichString.length(); i++) s += whichString.charAt(i);
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
		return "\n" + "----------------------------------------------------------\n" +
				"Book Title: " + this.title +
				"\n • Authors: " + this.authors +
				"\n • Genre: " + this.genre +
				"\n • Publisher: " + this.publisher +
				"\n • Shelf: " + this.shelf +
				"\n • Times Borrowed: " + this.timesBorrowed;
	}

}