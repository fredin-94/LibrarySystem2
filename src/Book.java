

public class Book {
	
	private String title;
	private String author;
	private String[] authors;
	private String genre;
	private String publisher;
	private double shelf;
	
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

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String[] getAuthors() {
		return authors;
	}

	public String getGenre() {
		return genre;
	}

	public String getPublisher() {
		return publisher;
	}

	public double getShelf() {
		return shelf;
	}
	
	public void setShelf(double shelf) {
		this.shelf = shelf;
	}

	
}
