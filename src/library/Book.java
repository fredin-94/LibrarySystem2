package library;

import java.util.ArrayList;

public class Book {

	private String id;
	private String title;
	private ArrayList<String>authors;
	private String genre;
	private String publisher;
	private int borrowed;
	
	public Book (String id, String title, String author, String genre, String publisher) {
		/* TODO
		 * Handle errors (e.g. not the same id as another book)
		 * */
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.publisher = publisher;
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

	@Override
	public String toString() {
		return "id: " + id + ", title: " + title + ", genre: " + genre + ", publisher: " + publisher + ", borrowed: "
				+ borrowed;
	}
	
	
}
