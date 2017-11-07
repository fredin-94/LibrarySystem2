package library;

import java.util.*;

public class Book {

	private String id;
	private String title;
	private ArrayList<String>authors;
	private String genre;
	private String publisher;
	private String shelf;
	private int borrowed;
	
	// ===== Constructor(s) =====
	public Book (String id, String title, String author, String genre, String publisher, String shelf) {
		/* TODO
		 * Handle errors (e.g. not the same id as another book)
		 * */
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.publisher = publisher;
		this.shelf = shelf;
		this.borrowed = 0;
		authors = new ArrayList<String>();
		authors.add(author);
	}

	public String getId() {
		return id;
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
		return borrowed;
	}
	
	public String getAuthor() {
        Collections.sort(authors);
        return this.authors.get(0);
    }

	@Override
    public String toString() {
        String authors = "";
        for (int i = 0; i < this.authors.size(); i++) {
            if (i > 0 || i == this.authors.size() - 1) authors += ", ";
            if (this.authors.get(i) != null) authors += this.authors.get(i);
        }
        return "\n" + this.title
                + " by " + authors
                + ". Genre: " + this.genre
                + ". Publisher: " + this.publisher
                + ". Shelf: " + this.shelf;
    }
	
	
}
