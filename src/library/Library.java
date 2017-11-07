package library;

import java.util.*;

public class Library {
	
    private List<Book> books;
    
    private List<Customer> customers;

    // ===== Constructor(s) =====
    public Library() {
    	books = new ArrayList<Book>();
    	}

    // ===== Public Functions =====
    public void add(Book book) {books.add(book);}

    public String toString() {
        String s = "";
        for (Book book : books) s += book.toString();
        return s;
    }
    
    public void borrowBook(Book book, String id) {
//		customer = someCustomer
//		someCustomer.getLibraryId
//		someCustomer.addbooktolentoutarraylist
	}
	
	public void returnBook(Book book, String id) throws Exception{
		if(id != null) {
			
		}
	}
	
	public void createCustomer(Customer customer) { //change parameters? to the id
		
	}
	
	public void removeCustomer(Customer customer) {
		
	}

    // ----- Sorting Functions -----
    public void sortByTitle() {Collections.sort(books, Comparator.comparing(Book::getTitle));}
    public void sortByAuthor() {Collections.sort(books, Comparator.comparing(Book::getAuthor));}

    // ----- Search Functions -----
    public Book searchForTitle(String title) {
        title.toLowerCase();
        for (Book book : books) if (title.equals(book.getTitle().toLowerCase())) return book;
        return null;
    }
    public Book searchForAuthor(String author) {
        author.toLowerCase();
        for (Book book : books) if (author.equals(book.getAuthor().toLowerCase())) return book;
        return null;
    }

}