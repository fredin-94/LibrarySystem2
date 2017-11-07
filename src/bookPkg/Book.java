package bookPkg;

public class Book {
	
	private String name;
	private String author;
	private String genre;
	private String publisher;
	private String shelf;
	
	public Book(String name, String author, String genre, String publisher, String shelf) {
		super();
		this.name = name;
		this.author = author;
		this.genre = genre;
		this.publisher = publisher;
		this.shelf = shelf;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
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

	@Override
	public String toString() {
		return "Book [name=" + getName() + ", author=" + author + ", genre=" + genre + ", publisher=" + publisher
				+ ", shelf=" + shelf + "]";
	}
	
	

}
