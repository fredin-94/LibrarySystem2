package library;

import java.util.*;

public class Library {

	private List<Book> books;

	private List<Customer> customers;

	// ===== Constructor(s) =====
	public Library() {
		books = new ArrayList<Book>();
		customers = new ArrayList<Customer>();
	}

	// ===== Public Functions =====
	public void add(Book book) {
		books.add(book);
	}

	public String toString() {
		String s = "";
		for (Book book : books)
			s += book.toString();
		return s;
	}

	public void borrowBook(String title, String id) {
		// someCustomer.getLibraryId
		// someCustomer.addbooktolentoutarraylist
		for (int i = 0; i < books.size(); i++) {
			if (customers.get(i).getId().equals(id)) {
				if (books.get(i).getTitle().equals(title)) { // MAKE A BOOK CONSTRUCTOR THAT TAKES ID AS PARAMETER I NEED IT COUGH, WE NEED IT
					Book book = new Book(title, books.get(i).getAuthor(), books.get(i).getGenre(), books.get(i).getPublisher(), books.get(i).getShelf());
					customers.get(i).addBook(book);
				}
			}
		}
	}

	public void returnBook(String title, String id) throws Exception {
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getId().equals(id)) { // CHANGE STUFF HERE
				if(customers.get(i).getBookTitle(title)) {
//					customers.get(i).
				}
				
				//				if (books.get(i).getTitle().equals(customers.get(i).getLoanedBooks().get(i).getTitle().equals(book))) {
//					customers.get(i).removeBook(book);
//				}
			}
		}
	}

	public void createCustomer(String name, String address, int phoneNumber) throws Exception {	// the id
		if(!name.equals("") || !address.equals("") || Integer.toString(phoneNumber).length() < 9) {
			throw new Exception("Don't enter empty strings or an incomplete phone number.");
		} else {
			Customer customer = new Customer(name, address, phoneNumber);
			customers.add(customer);
		}
	}

	public void removeCustomer(String id) {
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getId().equals(id)) {
				customers.remove(i);
			}
		}
	}

	// ----- Sorting Functions -----
	public void sortByTitle() {
		Collections.sort(books, Comparator.comparing(Book::getTitle));
	}

	public void sortByAuthor() {
		Collections.sort(books, Comparator.comparing(Book::getAuthor));
	}

	// ----- Search Functions -----
	public Book searchForTitle(String title) {
		title.toLowerCase();
		for (Book book : books)
			if (title.equals(book.getTitle().toLowerCase()))
				return book;
		return null;
	}

	public Book searchForAuthor(String author) {
		author.toLowerCase();
		for (Book book : books)
			if (author.equals(book.getAuthor().toLowerCase()))
				return book;
		return null;
	}

}