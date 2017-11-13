package library;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Book {

	//*** ATTRIBUTES ***//

	// G stuff
	private UUID id;
	private String title;
	private ArrayList<String> authors;
	private ArrayList<String> genre;
	private String publisher;
	private String shelf;

	// VG stuff
	private int timesBorrowed = 0;
	final static int TWO_WEEKS = 14;
	// need to think about the logic here!!!
	// does loanPeriod refer to the duration of the loan? could be done more elegantly if so.
	// add a setReturnDate for extensions and to check whether the book is delayed, check inside Library whether the 
	// currentDate - returnDate is greater than zero or less than. If greater than, there will be delay fees. 
	// The LocalDate class implements the Comparable interface and a built in minus which allows you to calc difference
	// between current date and the return date the customer should've returned it.
	// returnDate can then be updated through setReturnDate in the case of a customer wanting to extend their loan.
	private int loanPeriod = 0;
	private LocalDate startDate = LocalDate.now();
	private LocalDate returnDate = startDate.plus(TWO_WEEKS, ChronoUnit.DAYS);

	//*** CONSTRUCTOR ***//
	public Book(String title, ArrayList<String> authors, String publisher, ArrayList<String> genre, String shelf) throws Exception{

		this.id = UUID.randomUUID();

		if(title.equals("") || authors.isEmpty() || publisher.equals("") || genre.isEmpty() || shelf.equals("")){
			throw new Exception("Input argument cannot be empty.");
		} else {
			this.title = title;
			this.authors = authors;
			this.genre = genre;
			this.publisher = publisher;
			this.shelf = shelf;
		}
	}

	//*** GETTERS AND SETTERS ***//
	public UUID getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public String getAuthor(){
		String res = "";
		if(authors.size() == 1){
			return authors.get(0);
		} else {
			for(int i = 0; i < authors.size(); i++){
				res += authors.get(i) + ", ";
			}
			return res;
		}
	}

	public String getGenre(){
		String res = "";
		if(genre.size() == 1){
			return genre.get(0);
		} else {
			for(int i = 0; i < genre.size(); i++){
				res += genre.get(i) + ", ";
			}
			return res;
		}	
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

	public LocalDate getStartDate(){
		return this.startDate;
	}

	public LocalDate getReturnDate(){
		return this.returnDate;
	}

	// needed for when a customer borrows a book and startdate needs to be set.
	public void setStartDate(LocalDate startDate) throws Exception{
		if(startDate.equals(null)) {
			throw new Exception("Date can't be empty.");
		} else {
			this.startDate = startDate;
		}
	}

	// needed for when you want to extend loans
	public void setReturnDate(LocalDate returnDate) throws Exception{
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
	
	public void incrementTimesBorrowed() {
		this.timesBorrowed++;
	}

}


	public int checkDelay() {
		if(LocalDate.now().compareTo(returnDate) > 0) {
			return (int)ChronoUnit.DAYS.between(returnDate, LocalDate.now());
		} else {
			return 0;
		}
	}
	