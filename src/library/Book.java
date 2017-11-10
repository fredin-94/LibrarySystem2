package library;

import java.util.*;

public class Book {

	/*
	 * Attributes
	 */
	//G STUFF
	private String title;
	private ArrayList<String>authors;
	private ArrayList<String> genre;
	private String publisher;
	private String shelf;
	private UUID id;
	
	// VG STUFF
	private int timesBorrowed = 0;
	final static int TWO_WEEKS = 14;
	private int loanPeriod = 0;
	
	// ===== Constructor(s) =====
	public Book (String title, ArrayList<String> authors, ArrayList<String> genre, String publisher, String shelf) throws Exception{
		/* TODO
		 * Handle errors (e.g. not the same id as another book)
		 *  Handle other errors such as if the user attempts to enter empty strings for any of the 
		 * other parameters
		 * */
		this.id = UUID.randomUUID();

		if(title.equals("") || authors.isEmpty() || genre.isEmpty() || publisher.equals("") || shelf.equals("")) {
			throw new Exception("Input can't be empty");
		} else {
			this.title = title;
			this.authors = authors;
			this.genre = genre;
			this.publisher = publisher;
			this.shelf = shelf;
		}
	}
	
	// GETTERS AND SETTERS
	public UUID getId() {
		return id;
	}
	
	public void setShelf(String shelf) {
		this.shelf = shelf;
	}

	public String getTitle() {
		return title;
	}

	public String getGenre() {
		String res = "";
		// In the case of multiple authors return this
		if(this.genre.size() > 1) {
			for(String g : this.genre) {
				res += g + ", ";
			}
			return res;
		} else {
			return this.genre.get(0);
		}
	}

	public String getPublisher() {
		return publisher;
	}

	public int getBorrowed() {
		return timesBorrowed;
	}
	
	public String getAuthor() {
		String res = "";
		// In the case of multiple authors return this
		if(authors.size() > 1) {
			for(String a : authors) {
				res += a + ", ";
			}
			return res;
		} else {
			return authors.get(0);
		}
    }

	public String getShelf() {
		return shelf;
	}

	// toString METHOD
	@Override
    public String toString() {
		String res = "";
		if(authors.size() > 1) {
			for(int i = 0; i < authors.size(); i++) {
				if(i != authors.size() - 1) {
					res += authors.get(i) + ", ";
				} else {
					res += authors.get(i);
				}
			}
		} else {
			res += authors.get(0);
		}
		
		return "Title: " + this.title + "\n" + 
				"Author: " + res + "\n" + 
				"Genre: " + this.genre + "\n" + 
				"Publisher: " + this.publisher + "\n" + 
				"Shelf: " + this.shelf + "\n";

    }
	
	// ===== Sort Authors ===== (Fabian)
	public void sortAuthors() {Collections.sort(authors);}
	
	
}
