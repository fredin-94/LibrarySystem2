package library;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

public class Book {
	
	private String title;
	//String author;
	private String authors;
	//private String author;
	private String genre;
	private String publisher;
	private String shelf;
	//String id;
	private UUID id;
	
	private int timesBorrowed;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
	
	//private DateTimeFormatter returnDate;
	private LocalDate returnDate;
	private String returnDateString;
	private int numOfCopies;
	private boolean delayed = false; //dont need??
	
	//will it work with authors as arraylist?
	public Book(String title, String authors, String publisher, String genre,  String shelf, int numOfCopies, int timesBorrowed,String returnDate) {
		super();
		this.title = title;
		this.authors = authors;
		this.genre = genre;
		this.publisher = publisher;
		this.shelf = shelf;
		this.timesBorrowed = timesBorrowed;
		//this.returnDate = LocalDate.parse(returnDate, formatter);
		this.returnDateString = returnDate;
		this.numOfCopies = numOfCopies;
		id = UUID.randomUUID();
	}
//	public Book(String title, String author, String publisher, String genre, String shelf) {
//		super();
//		this.title = title;
//		this.author = author;
//		this.genre = genre;
//		this.publisher = publisher;
//		this.shelf = shelf;
//		id = UUID.randomUUID();
//	}
	
	public String getTitle() {
		return title;
	}
	public int getTimesBorrowed() {
		return timesBorrowed;
	}

	public String getReturnDateString() {
		return returnDateString;
	}

	public int getNumOfCopies() {
		return numOfCopies;
	}

	public void setNumOfCopies(int numOfCopies) {
		this.numOfCopies = numOfCopies;
	}

	public String getGenre() {
		return genre;
	}
	public String getPublisher() {
		return publisher;
	}
	public String getShelf() {
		return shelf;
	}
	public UUID getId() {
		return id;
	}
	
	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public void setReturnDateString(String returnDateString) {
		this.returnDateString = returnDateString;
	}

	public String getAuthors() {
		return authors;
	}
	
	public String getAuthor() {
		String res = "";
		// In the case of multiple authors return this
//		if(authors.size() > 1) {
//			for(String a : authors) {
//				res += a + ", ";
//			}
//			return res;
//		} else {
			return authors;
	//	}
    }
	@Override
	public String toString() {
		return "Book title: " + title + ", authors:" + this.getAuthor() + ", genre: " + genre + ", publisher: " + publisher
				+ ", shelf: " + shelf + ", Copies: " + numOfCopies + "times borrowed:" + timesBorrowed + "return date:" + returnDateString;
	}
	
	//from libsys2
	
	public void incrementTimesBorrowed() {
		this.timesBorrowed++;
	}
	
	public void decrementAvailableCopies() {
		this.numOfCopies--;
	}
	
	public void incrementAvailableCopies() {
		this.numOfCopies++;
	}

}
