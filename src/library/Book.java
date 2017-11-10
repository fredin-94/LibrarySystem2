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
<<<<<<< HEAD
		this.id = UUID.randomUUID();

		if(title.equals("") || authors.isEmpty() || genre.isEmpty() || publisher.equals("") || shelf.equals("")) {
			throw new Exception("Input can't be empty");
=======
		//this.id = id;
		//Note by Fabian: Use generic error message (ex: "Input parameter cannot be empty"). And use || in a single if-statement.
		if(title.equals("")) {
			throw new Exception("A book title can't be empty");
>>>>>>> e2aedb0aa89ca5a4fc84c94d27230a5cbdb9467c
		} else {
			this.title = title;
			this.authors = authors;
			this.genre = genre;
			this.publisher = publisher;
			this.shelf = shelf;
		}
	}
<<<<<<< HEAD
	
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
	
=======

	// ===== Getters =====
	public UUID getId() {return this.id;}
	public String getTitle() {return this.title;}
	public String getGenre() {return this.genre;}
	public String getPublisher() {return this.publisher;}
	public int getBorrowed() {return this.timesBorrowed;}
	public String getShelf() {return this.shelf;}
	
//<<<<<<< HEAD
	// Note by Fabian: Sorts the author alphabetically then returns the author at index 0.
//=======
	
	// we would have to have a way of returning multiple authors if the book has multiple and a single
	// author if the book has a single author
//>>>>>>> 76ff6bce83f7f884e66f32bedc0ae808a5ddf8a2
	
	// Note by Fabian: Just make another getAuthors method that returns a List of authors...
	public List<String> getAuthors() {return this.authors;}
	
>>>>>>> e2aedb0aa89ca5a4fc84c94d27230a5cbdb9467c
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
	
	// ===== Setters =====
	public void setShelf(String shelf) {this.shelf = shelf;}

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
