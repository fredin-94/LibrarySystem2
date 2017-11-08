package library;

import java.util.*;

public class Book {

	//private String id;
	private String title;
	private ArrayList<String>authors;
	// Or does the group want only a single way to construct a new book e.g with an arraylist of authors both
	// in the case of a single author and in the case of multiple?
	//private String author;
	private String genre;
	private String publisher;
	private String shelf;
	private int timesBorrowed = 0;
	// is this method too old? Google further to determine most elegant method of unique id.
	private UUID id = UUID.randomUUID();
	
	// serialized UID???
	
	// ===== Constructor(s) =====
	public Book (String title, ArrayList<String> authors, String genre, String publisher, String shelf) throws Exception{
		/* TODO
		 * Handle errors (e.g. not the same id as another book)
		 *  Handle other errors such as if the user attempts to enter empty strings for any of the 
		 * other parameters
		 * */
		//this.id = id;
		//Note by Fabian: Use generic error message (ex: "Input parameter cannot be empty"). And use || in a single if-statement.
		if(title.equals("")) {
			throw new Exception("A book title can't be empty");
		} else {
			this.title = title;
		}
		if(authors.isEmpty()) {
			throw new Exception("You must give a book at least one author.");
		} else {
			this.authors = authors;
		}
		if(genre.equals("")) {
			throw new Exception("A genre must be entered");
		} else {
			this.genre = genre;
		}
		if(publisher.equals("")) {
			throw new Exception("A book must have a publisher");
		} else {
			this.publisher = publisher;
		}
		if(shelf.equals("")) {
			throw new Exception("A book must be assigned a shelf.");
		} else {
			this.shelf = shelf;
		}
	}

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
		return genre;
	}

	public String getPublisher() {
		return publisher;
	}

	public int getBorrowed() {
		return timesBorrowed;
	}
	
//<<<<<<< HEAD
	// Note by Fabian: Sorts the author alphabetically then returns the author at index 0.
//=======
	
	// we would have to have a way of returning multiple authors if the book has multiple and a single
	// author if the book has a single author
//>>>>>>> 76ff6bce83f7f884e66f32bedc0ae808a5ddf8a2
	
	// Note by Fabian: Just make another getAuthors method that returns a List of authors...
	public List<String> getAuthors() {return this.authors;}
	
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

	// Look over this one. Am I satisfied here?
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
