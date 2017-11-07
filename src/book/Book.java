package book;

public class Book {
	
	String title;
	String author;
	String[] authors;
	String genre;
	String publisher;
	double shelf;
	
	public Book(String title, String author, String genre, String publisher, double shelf){
		
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.publisher = publisher;
		this.shelf = shelf;
		
	}
	
	public Book(String title, String[] authors, String genre, String publisher, double shelf){
		
		this.title = title;
		this.authors = authors;
		this.genre = genre;
		this.publisher = publisher;
		this.shelf = shelf;
		
	}
	
}
