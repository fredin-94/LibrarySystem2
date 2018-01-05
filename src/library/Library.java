package library;
import java.io.*;
import java.security.*;
import java.util.function.*;

import library.Library.bookKey;

import static library.Library.bookKey.*;
//--------------------
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Library {

	private ArrayList<Book> allBooks;
	private ArrayList<Book> books;
	private ArrayList<Customer> customers;
	private LocalDate date;

	/**This class stores all the functionalities for the library system.  
	 * 
	 * @author Oliver Manzi
	 * @author Fabian Fröding
	 * @editor Lucas Fredin
	 * @editor Hanien Kobus
	 * @editor Majd Hatoum
	 * */
	public Library() {
		allBooks = new ArrayList<Book>();
		books = new ArrayList<Book>();
		customers = new ArrayList<Customer>();
		date = LocalDate.now();

		try {
			customerDirectory();
		} catch (Exception e) {
			System.out.println("Unable to initialize customer directory");
		}

		try {
			bookDirectory("res/bookDirectory.txt");
		} catch (Exception e) {
			System.out.println("Unable to initialize available books directory");
		}

		try {
			bookDirectory("res/AllBooks.txt");
		} catch (Exception e) {
			System.out.println("Unable to initialize all books directory");
		}
		setDebt();
	}

	/**
	 * Method to set the debt of all customers in the customers arraylist
	 */
	public void setDebt() {
		for(int i = 0; i < customers.size(); i++) {
			customers.get(i).setDebt(0);
			int debt = 0;
			for(int j = 0; j < customers.get(i).getCurrentLoans().size(); j++) {
				debt += this.checkDelay(customers.get(i).getCurrentLoans().get(j)) * 2;
			}
			customers.get(i).setDebt(debt);
		}
	}

	/**returns an arrayList of allBooks, all the books in the library 
	 * whether they are borrowed or not.
	 * */
	public ArrayList<Book> getAllBooks() {
		return allBooks;
	}

	/**Returns an arrayList of book objects, all books that have not 
	 * been borrowed yet.
	 * */
	public ArrayList<Book> getBooks() {
		return books;
	}

	/**This method returns an arrayList of books objects, all books
	 * that have been loaned out.
	 * 
	 * @version 1.1 Modified: To avoid excess text-files, the "loanedBooks" 
	 * arrayList was deleted and replaced with a for-loop that goes throughout
	 * customer objects and acquires all their loaned out books  
	 * */
	public ArrayList<Book> getLoanedBooks() {
		ArrayList<Book> allLoanedBooks = new ArrayList<Book>();
		for (Customer customer : customers) {
			for (Book book : customer.getCurrentLoans()) {
				allLoanedBooks.add(book);
			}
		}
		return allLoanedBooks;
	}

	/**returns an arrayList of book objects, all books that have been delayed.
	 *
	 * */
	public ArrayList<Book> getDelayedBooks() {
		ArrayList<Book> allDelayedBooks = new ArrayList<Book>();
		for (Book book : getLoanedBooks()) {
			if (checkDelay(book) > 0) {
				allDelayedBooks.add(book);
			}
		}
		return allDelayedBooks;
	}

	/**returns an arrayList of customer objects, 
	 * all customers registered to the library system.
	 * */
	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	/**Returns the current date of the library.
	 * */
	public LocalDate getDate() {
		return date;
	}

	/**increments the days of the library date while checking if a book is delayed.
	 * 
	 * */
	public void addDays(int Days) {
		this.date = this.getDate().plusDays(Days);
		for (Book book : getLoanedBooks()) {
			for (Customer customer : customers) {
				for (Book sameBook : customer.getCurrentLoans()) {
					if (book.equals(sameBook)) {
						int debt = this.checkDelay(sameBook) * 2;
						customer.setDebt(debt);
					}
				}
			}
		}
	}

	/**increments the weeks of the library date while checking if a book is delayed.
	 * 
	 * */
	public void addWeeks(int weeks) {
		date = date.plusWeeks(weeks);
		for (Book book : getLoanedBooks()) {
			for (Customer customer : customers) {
				for (Book sameBook : customer.getCurrentLoans()) {
					if (book.equals(sameBook)) {
						int debt = this.checkDelay(sameBook) * 2;
						customer.setDebt(debt);
					}
				}
			}
		}
	}

	/**increments the months of the library date while checking if a book is delayed.
	 * 
	 * */
	public void addMonths(int months) {
		this.date = this.date.plusMonths(months);
		for (Book book : getLoanedBooks()) {
			for (Customer customer : customers) {
				for (Book sameBook : customer.getCurrentLoans()) {
					if (book.equals(sameBook)) {
						int debt = this.checkDelay(sameBook) * 2;
						customer.setDebt(debt);
					}
				}
			}
		}
	}

	/**increments the years of the library date while checking if a book is delayed.
	 * 
	 * */
	public void addyears(int years) {
		this.date = this.date.plusYears(years);
		for (Book book : getLoanedBooks()) {
			for (Customer customer : customers) {
				for (Book sameBook : customer.getCurrentLoans()) {
					if (book.equals(sameBook)) {
						int debt = this.checkDelay(sameBook) * 2;
						customer.setDebt(debt);
					}
				}
			}
		}
	}

	/**Enums for book object
	 * */
	public enum bookKey {
		TITLE, AUTHOR, GENRE, PUBLISHER, SHELF, ID, TIMESBORROWED
	}

	/**Enums for customer object
	 * */
	public enum customerKey {
		NAME, ADRESS, NUMBER, DEBT, ID, PERSONNUMMER
	}

	/**Searches for book objects in allBooks arrayList.
	 * 
	 * @version 1.0 Created by Fabian to read book toString and 
	 * compare it to search text from parameter.
	 * @version 1.1 Modified by Oliver to read book object 
	 * attributes instead of toString
	 * 
	 * @throws exception
	 * */
	public ArrayList<Book> searchForBook(String searchText) throws NullPointerException {
		searchText.trim().toLowerCase();
		ArrayList<Book> list = new ArrayList<>();
		for (Book book : this.books) {
			if (book.getTitle().trim().toLowerCase().contains(searchText.trim().toLowerCase())
					|| book.getAuthors().trim().toLowerCase().contains(searchText.trim().toLowerCase())
					|| book.getPublisher().trim().toLowerCase().contains(searchText.trim().toLowerCase())
					|| book.getGenre().trim().toLowerCase().contains(searchText)
					|| book.getShelf().trim().toLowerCase().contains(searchText.trim().toLowerCase())) {
				if (!list.contains(book)) {
					list.add(book);
				}
			}
		}
		if (list.size() >= 1) {
			return list;
		} else {
			return null;
		}
	}

	/**Searches for customers registered to the library system.
	 * 
	 * @version 1.0 Created by Fabian to read customer toString and
	 * compare it to searchText from parameters.
	 * @version 1.1 Modified by Oliver to read customer object 
	 * attributes instead of toString
	 * 
	 * @throws exception
	 * */
	public ArrayList<Customer> searchForCustomer(String searchText) throws NullPointerException {
		searchText.trim().toLowerCase();
		ArrayList<Customer> list = new ArrayList<>();
		for (Customer customer : this.customers) {
			if (customer.getName().trim().toLowerCase().contains(searchText.trim().toLowerCase())
					|| customer.getAdress().toLowerCase().trim().contains(searchText.trim().toLowerCase())
					|| customer.getPersonnummer().toLowerCase().trim().contains(searchText.trim().toLowerCase())
					|| customer.getNumber().trim().toLowerCase().contains(searchText.trim().toLowerCase())) {
				list.add(customer);
			}
		}
		if (list.size() >= 1)
			return list;
		return null;
	}

	/**Finds customer objects by a enum value.
	 * 
	 * @throws exception
	 * */
	public Customer findCustomerBy(customerKey key, String searchValue) throws InvalidKeyException {
		searchValue.toLowerCase();
		switch (key) {
		case NAME:
			return findCustomerByString(searchValue, Customer::getName);
		case ADRESS:
			return findCustomerByString(searchValue, Customer::getAdress);
		case NUMBER:
			return findCustomerByString(searchValue, Customer::getNumber);
		case PERSONNUMMER:
			return findCustomerByString(searchValue, Customer::getPersonnummer);
		default:
			throw new InvalidKeyException("Invalid enum key in search customer function");
		}
	}

	/**Finds customer objects by a string value.
	 * 
	 * @throws exception
	 * */
	private Customer findCustomerByString(String searchValue, Function<Customer, ? extends Comparable> f)
			throws NullPointerException {
		try {
			for (Customer customer : customers) {
			//	System.out.println("search value: " + searchValue);
			//	System.out.println(f.apply(customer));
				if (searchValue.equals(((String) f.apply(customer)).toLowerCase())) {
				//	System.out.println("found customer");
					return customer;
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
		throw new NullPointerException("Customer not found.");
	}

	/**Sorts book objects in allBooks arrayList by Title, Author, Genre, Publisher or Shelf.
	 * 
	 * @throws exception
	 * */
	public void sortAllBooksBy(bookKey keyToSort) {
		try {
			for (Book book : this.allBooks)
				book.firstLettersToUpperCase();
			Collections.sort(this.allBooks, Comparator.comparing(getBookFunction(keyToSort)));
			if(keyToSort.equals(TIMESBORROWED)) {
				Collections.reverse(this.allBooks);
			}
		} catch (InvalidKeyException ike) {
			ike.printStackTrace();
		}
	}


	/**Sorts book objects in books arrayList by Title, Author, Genre, Publisher or Shelf.
	 * */
	public void sortBooksBy(bookKey keyToSort) {
		try {
			for (Book book : this.books)
				book.firstLettersToUpperCase();
			Collections.sort(this.books, Comparator.comparing(getBookFunction(keyToSort)));
			if(keyToSort.equals(TIMESBORROWED)) {
				Collections.reverse(this.allBooks);
			}
		} catch (InvalidKeyException ike) {
			ike.printStackTrace();
		}
	}

	/**Compliments enums.
	 * 
	 * @throws exception
	 * */
	private Function<Book, ? extends Comparable> getBookFunction(bookKey key) throws InvalidKeyException {
		switch (key) {
		case TITLE:
			return Book::getTitle;
		case AUTHOR:
			return Book::getAuthors;
		case GENRE:
			return Book::getGenre;
		case PUBLISHER:
			return Book::getPublisher;
		case SHELF:
			return Book::getShelf;
		case TIMESBORROWED:
			return Book::getTimesBorrowed;
		default:
			throw new InvalidKeyException("Invalid key in sort book function");
		}
	}

	/**Sorts customer objects in customers arrayList by Name, Address, Number.
	 * */
	public void sortCustomersBy(customerKey keyToSort) {
		try {
			switch (keyToSort) {
			case NAME:
				Collections.sort(customers, Comparator.comparing(Customer::getName));
				break;
			case ADRESS:
				Collections.sort(customers, Comparator.comparing(Customer::getAdress));
				break;
			case NUMBER:
				Collections.sort(customers, Comparator.comparing(Customer::getNumber));
				break;
			case DEBT:
				Collections.sort(this.customers, Comparator.comparing(Customer::getDebt));
				break;
			default:
				throw new InvalidKeyException("Invalid key in customer sort function");
			}
		} catch (InvalidKeyException ike) {
			ike.printStackTrace();
		}
	}


	/**Adds a book to the books arrayList. Checks whether 
	 * book being added is already in allBooks arrayList. If it isn't,
	 * it is added. This method is used for two things, 1. returning a borrowed book
	 * 2. Add a book to the library system.
	 * */
	public void addBook(Book book) {
		if (allBooks.contains(book) == false) {
			allBooks.add(book);
		}
		books.add(book);
	}

	/**Removes book object from the books arrayList.
	 * This method is used to borrow a book. 
	 * 
	 * */
	public void removeBook(Book book) {
		books.remove(book);
	}

	/**Removes a book from the entire library system. 
	 * 
	 * @version 1.1 Modified: checks whether the book has been borrowed by a customer.
	 * If a customer has loaned the books, it deletes it from their current loans
	 * and increments any debt related to the book (if there is any).
	 * Useful when dealing with lost books. 
	 * 
	 * @throws exception
	 * */
	public void deleteBook(Book book) throws Exception {
		if (this.checkDelay(book) > 0) {
			for (Customer theCustomer : customers) {
				if (theCustomer.getCurrentLoans().contains(book)) {
					this.returnBook(book.getTitle(), theCustomer.getPersonnummer());
				}
			}
		}
		allBooks.remove(book);
		books.remove(book);
	}

	/**Adds a customer to customers arrayList.
	 * 
	 * @throws exception
	 * */
	public void addCustomer(Customer customer) throws Exception {
		if (customer == null) {
			throw new Exception("Customer object is null");
		} else {
			customers.add(customer);
		}
	}

	/**Removes a customer from library system
	 * 
	 * @version 1.1 Modification: if customer has any loaned books, 
	 * they cannot be deleted until they return them.
	 * 
	 * @throws exception
	 * */
	public void removeCustomer(Customer customer) throws Exception {
		if(!customer.getCurrentLoans().isEmpty()) {
			throw new Exception("Cannot delete customer until\ncurrent loans have been returned"); 
		}else {
			customers.remove(customer);
		}
	}

	/**Borrows a book to a customer. Sets return date of book (2 weeks from current date).
	 * 
	 * @throws exception
	 * */
	public void borrowBook(String bookTitle, String personnummer) throws Exception {

		Customer customer = this.findCustomerBy(customerKey.PERSONNUMMER, personnummer);
		Book book = searchForBook(bookTitle).get(0);
		book.setReturnDate(this.date.plusWeeks(2)); 
		book.incrementTimesBorrowed();
		for (Book books : allBooks) {
			if (book.getTitle().trim().equalsIgnoreCase(books.getTitle().trim())) {
				
			}
		}
		customer.addToCurrentLoan(book);
		customer.addToLoanHistory(book);
		books.remove(book);
	}

	/**Returns a book to the library. Calculates debt and adds sum total to customer profile.
	 * 
	 * @throws exception
	 * */
	public void returnBook(String bookTitle, String personnummer) throws Exception {

		Customer customer = findCustomerBy(customerKey.PERSONNUMMER, personnummer);
		if (customer == null) {
			throw new Exception("Customer doesn't exist in directory");
		}

		Book book = customer.getFromCurrentLoan(bookTitle);
		if (book == null) {
			throw new Exception("Book doesn't exist in directory");
		}

		int debt = this.checkDelay(book) * 2;
		//customer.setDebt(debt);
		LocalDate restartDate = LocalDate.of(2017, 10, 31);
		;
		book.setReturnDate(restartDate);
		books.add(book);
		customer.removeFromCurrentLoan(book);

		if (debt > 0) {
			System.out.println(customer.getName() + " returned the book " + (debt / 2) + " days after the return date");
			System.out.println("and was charged a delay fee of " + debt);
			customers.clear();
			customerDirectory();
			setDebt();
		} else {
			System.out.println("** Customer returned the book on time. **");
		}

	}

	/**Returns top ten most borrowed books.
	 * */
	public ArrayList<Book> getTopTen() {
		sortAllBooksBy(TIMESBORROWED);
		Set<String> hashSetTitle = new LinkedHashSet<String>();
		ArrayList<String> arrayTitle = new ArrayList<String>();

		for (Book book : allBooks) {
			arrayTitle.add(book.getTitle());
			hashSetTitle.add(book.getTitle());
		}
		for (int i = 0; i < arrayTitle.size(); i++) {
			hashSetTitle.add(arrayTitle.get(i));
			arrayTitle.clear();
			arrayTitle.addAll(hashSetTitle);
		}

		ArrayList<Book> topBooks = new ArrayList<Book>();
		if (arrayTitle.size() < 10) {
			for (int i = 0; i < arrayTitle.size(); i++) {
				addBookToList(arrayTitle.get(i), topBooks);
			}
		} else {
			if (arrayTitle.size() > 10) {
				for (int i = 0; i < 10; i++) {
					addBookToList(arrayTitle.get(i), topBooks);
				}
			}
		}
		return topBooks;
	}

	/**Compliments getTopTen method. Adds books to lists
	 * */
	public void addBookToList(String title, ArrayList<Book> list) {

		for (int i = 0; i < allBooks.size(); i++) {
			if (title.equals(allBooks.get(i).getTitle())) {
				list.add(allBooks.get(i));
				break;
			}
		}
	}

	/**Sorts books in alBooks arrayList by times borrowed.
	 * */
	public void sortTimesBorrowed() {
		try {
			for (Book book : this.allBooks)
				book.firstLettersToUpperCase();
			Collections.sort(this.allBooks, Comparator.comparing(getBookFunction(TIMESBORROWED)));
			Collections.reverse(this.allBooks);
		} catch (InvalidKeyException ike) {
			ike.printStackTrace();
		}
	}

	/**Returns customers loan history arrayList.
	 * */
	public String getCustomerLoanHistoryString(Customer customer) {
		String current = "";
		String END_OF_LINE = "\n";
		if (customer.getLoanHistory().isEmpty()) {
			current += "\n--------- Customer's has no current loan(s) ---------" + END_OF_LINE;
		} else {
			System.out.println("\n--------- Customer's loan history: ---------");
			for (int i = 0; i < customer.getLoanHistory().size(); i++) {
				current += customer.getLoanHistory().get(i).toStringCurrentLoans() + END_OF_LINE;
			}
		}
		return current;
	}

	/**Returns customers current loan arrayList.
	 * */
	public String getCustomerCurrentLoanString(Customer customer) {
		String currentLoan = "";
		String END_OF_LINE = "\n";
		if (customer.getCurrentLoans().isEmpty()) {
			currentLoan += "\n--------- Customer's loan is empty ---------" + END_OF_LINE;
		} else {
			System.out.println("\n--------- Customer's current loan(s): ---------");
			for (int i = 0; i < customer.getCurrentLoans().size(); i++) {
				currentLoan += customer.getCurrentLoans().get(i).toStringCurrentLoans() + END_OF_LINE;
			}
		}
		return currentLoan;
	}

	/**Compares the current date of library with the return date 
	 * of a book object return date.
	 * */
	public int checkDelay(Book book) {
		if (this.date.compareTo(book.getReturnDate()) > 0) {
			return (int) ChronoUnit.DAYS.between(book.getReturnDate(), this.date);
		} else {
			return 0;
		}
	}

	/**Returns number of copies of all books.
	 * */
	private int getCopiesOfTitle(String title) {
		int copies = 0;
		for (int i = 0; i < this.books.size(); i++) {
			if (title.trim().toLowerCase().equals(this.books.get(i).getTitle().trim().toLowerCase())) {
				copies++;
			}
		}
		return copies;
	}

	/**Parses lines in a text file to objects in an arrayList.
	 * 
	 * @throws exception
	 * */
	public void bookDirectory(String path) throws FileNotFoundException {
		File file = new File(path);
		Scanner input = new Scanner(file);
		input.useDelimiter("/|\n");

		while (input.hasNext()) {

			String title = input.next();
			String author = input.next();
			String publisher = input.next();
			String genre = input.next();
			String shelf = input.next();
			String timesBorrowed = input.next();
			String returnDate = input.next();
			Book book = null;
			try {
				book = new Book(title, author, publisher, genre, shelf);
				book.setTimesBorrowed(Integer.parseInt(timesBorrowed)); // changed this!!
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // need to change this?
				book.setReturnDate(LocalDate.parse(returnDate, formatter));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (path.contains("bookDirectory")) {
					books.add(book);
				}else if (path.contains("AllBooks")) {
					allBooks.add(book);
				}
			}
		}
	}

	/**Parses lines in a text file into customer objects in an arrayList.
	 * 
	 * @throws exception
	 * */
	public void customerDirectory() throws Exception {
		Scanner input = new Scanner(new File("res/customer.txt"));
		input.useDelimiter("/|\n");

		while (input.hasNext()) {

			String name = input.next().trim();
			String address = input.next().trim();
			String psn = input.next().trim();
			String phoneNumber = input.next().trim();

			Customer customer = null;
			try {
				customer = new Customer(name, address, psn, phoneNumber);
			} catch (Exception e) {
				//System.out.println(e.getMessage());
				e.printStackTrace();
			} finally {
				customers.add(customer);
			}
		}
	}

	/**Displays all available books in the library.
	 * */
	@Override
	public String toString() {
		String s = "|======== Books ==========|\n===========================\nBooks Available: " + this.books.size()
				+ "	===\n===========================\n";
		List<String> sList = new ArrayList<>();
		for (int i = 0; i < this.books.size(); i++) {
			Book book = this.books.get(i);
			if (!(sList.contains(book.toString()))) {
				sList.add(book.toString());
				s += book.toString() + "\n • Copies available: " + getCopiesOfTitle(book.getTitle())
						+ "\n----------------------------------------------------------\n";
			}
		}
		return s;
	}
}