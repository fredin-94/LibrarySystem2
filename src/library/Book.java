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
	private String genre;
	private String publisher;
	private String shelf;

	// VG stuff
	private int timesBorrowed = 0;
	final static int TWO_WEEKS = 14;
	private LocalDate startDate = LocalDate.now();
	private LocalDate returnDate = startDate.plus(TWO_WEEKS, ChronoUnit.DAYS);

	//*** CONSTRUCTOR ***//
	public Book(String title, String authors, String publisher, String genre, String shelf) throws Exception{

		this.id = UUID.randomUUID();

		if(title.equals("") || authors.equals("") || publisher.equals("") || genre.equals("") || shelf.equals("")){
			throw new Exception("Input argument cannot be empty.");
		} else {
			this.title = title;
			this.authors = new ArrayList<String>(Arrays.asList(authors.split(",")));
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

	public String getAuthor() {return this.authors.get(0);}
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

	@Override
	public String toString() {
		return "----------------------------------------------------------" + System.lineSeparator() +
				"Book Title: " + this.title + System.lineSeparator() + 
				", Authors: " + this.authors + System.lineSeparator() + 
				", Genre: " + this.genre + System.lineSeparator() +  
				", Publisher: " + this.publisher + System.lineSeparator() +
				", Shelf: " + this.shelf + System.lineSeparator() + 
				", Times Borrowed: " + this.timesBorrowed + System.lineSeparator() +
				"----------------------------------------------------------";
	}
	
	
	
}



	
	