package library;

import java.io.*;
import java.security.*;
import java.util.function.*;

import static library.Library.bookKey.*;
//--------------------
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static library.Library.bookKey.*;
import static library.Library.customerKey.*;

public class Library {

	private ArrayList<Book> allBooks;
	private ArrayList<Book> books;
	private ArrayList<Book> loanedBooks;
	private ArrayList<Book> delayedBooks;
	private ArrayList<Customer> customers;
	private static LocalDateTime date;
	private Timer timer;
	private TimerTask hourlyTask;

	public Library() {
		allBooks = new ArrayList<>();
		books = new ArrayList<>();
		loanedBooks = new ArrayList<>();
		delayedBooks = new ArrayList<>();
		customers = new ArrayList<>();
		date = LocalDateTime.now();

		try {
			customerDirectory();
			bookDirectory("res/bookDirectory.txt");
			bookDirectory("res/LoanedBooks.txt");
			bookDirectory("res/delayedBooks.txt");
			bookDirectory("res/AllBooks.txt");
//			importBooksFrom("res/bookDirectory.txt");
//			importBooksFrom("res/LoanedBooks.txt");
//			importBooksFrom("res/delayedBooks.txt");
//			importBooksFrom("res/AllBooks.txt");
		} catch (Exception e) {
			System.out.println("Unable to initialize all books directory");
			e.printStackTrace();
		}

	}

	/* TODO ---------------------Basic------------------------------- */

	public ArrayList<Book> getAllBooks() {
		return allBooks;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	/*---------------------SEARCH------------------------------*/
	// DON'T CHANGE FORMAT PLEASE.
	public enum bookKey {TITLE, AUTHOR, GENRE, PUBLISHER, SHELF, ID, TIMESBORROWED}
	public enum customerKey {NAME, ADRESS, NUMBER, DEBT, ID, PERSONNUMMER}

	// ----- Search for book ----- //
	// Use findBookBy for title, genre, publisher and ID. Returns Book.
	// Use findBooksBy for author and shelf. Returns ArrayList<Book>.
	public List<Book> searchForBook(String searchText) throws NullPointerException {
		searchText.trim().toLowerCase();
		List<Book> list = new ArrayList<>();
		for (Book book : this.books) {
			if (book.toString().trim().toLowerCase().contains(searchText)) list.add(book);
		}
		if (list.size() >= 1) return list;
		return null;
	}

	public List<Customer> searchForCustomer(String searchText) throws NullPointerException {
		searchText.trim().toLowerCase();
		List<Customer> list = new ArrayList<>();
		for (Customer customer : this.customers) {
			if (customer.toString().trim().toLowerCase().contains(searchText)) list.add(customer);
		}
		if (list.size() >= 1) return list;
		return null;
	}

	public Book findBookBy(bookKey key, String searchValue) throws InvalidKeyException {
		searchValue.toLowerCase();
		switch (key) {
		case TITLE: return findBookByString(searchValue, Book::getTitle);
		case GENRE: return findBookByString(searchValue, Book::getGenre);
		case PUBLISHER: return findBookByString(searchValue, Book::getPublisher);
		case ID:
			for (Book book : books)
				if (book.getId().toString().equals(searchValue)) return book;
		default: throw new InvalidKeyException("Invalid key in search function.");
		}
	}

	private Book findBookByString(String s, Function<Book, ? extends Comparable> f) throws NullPointerException {
		s.toLowerCase();
		for (Book book : this.books)
			if (s.equals(((String) f.apply(book)).toLowerCase())) return book;
		return null;
	}

	// ----- Search for customer ----- //
	public Customer findCustomerBy(customerKey key, String searchValue) throws InvalidKeyException {
		searchValue.toLowerCase();
		switch (key) {
		case NAME: return findCustomerByString(searchValue, Customer::getName);
		case ADRESS: return findCustomerByString(searchValue, Customer::getAdress);
		case NUMBER: return findCustomerByString(searchValue, Customer::getNumber);
		case ID:
			for (Customer customer : customers)
				if (customer.getID().toString().equals(searchValue)) return customer;
		case PERSONNUMMER: return findCustomerByString(searchValue, Customer::getPersonnummer);
		default: throw new InvalidKeyException("Invalid enum key in search customer function");
		}
	}

	private Customer findCustomerByString(String searchValue, Function<Customer, ? extends Comparable> f) throws NullPointerException {
		try {
			for (Customer customer : customers) {
				System.out.println("search value: " + searchValue);
				System.out.println(f.apply(customer));
				if (searchValue.equals(((String) f.apply(customer)).toLowerCase())) {
					System.out.println("found customer");
					return customer;
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
		throw new NullPointerException("Customer not found.");
	}

	/*---------------------SORTING------------------------------*/
	// Uses enums from search.
	// DON'T CHANGE FORMAT PLEASE.

	public void sortAllBooksBy(bookKey keyToSort) {
		try {
			for (Book book : this.allBooks) book.firstLettersToUpperCase();
			Collections.sort(this.allBooks, Comparator.comparing(getBookFunction(keyToSort)));
		} catch (InvalidKeyException ike) {
			ike.printStackTrace();
		}
	}

	public void sortBooksBy(bookKey keyToSort) {
		try {
			for (Book book : this.books) book.firstLettersToUpperCase();
			Collections.sort(this.books, Comparator.comparing(getBookFunction(keyToSort)));
		} catch (InvalidKeyException ike) {
			ike.printStackTrace();
		}
	}

	private Function<Book, ? extends Comparable> getBookFunction(bookKey key) throws InvalidKeyException {
		switch (key) {
		case TITLE: return Book::getTitle;
		case AUTHOR: return Book::getAuthor;
		case GENRE: return Book::getGenre;
		case PUBLISHER: return Book::getPublisher;
		case SHELF: return Book::getShelf;
		case TIMESBORROWED:
			// TODO: Needs testing. Not sure if this works for primitive types.
			return Book::getTimesBorrowed;
		default: throw new InvalidKeyException("Invalid key in sort book function");
		}
	}

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
				// TODO: Needs testing. Not sure if this works for primitive types.
				Collections.sort(this.customers, Comparator.comparing(Customer::getDebt));
				break;
			default: throw new InvalidKeyException("Invalid key in customer sort function");
			}
		} catch (InvalidKeyException ike) {
			ike.printStackTrace();
		}
	}

	/* TODO -------------------REGISTRATION--------------------- */

	/* register books */
	public void addBook(Book book) {
		for (Book aBook : allBooks) {
			if (book == aBook) {
				break;
			} else {
				allBooks.add(book);
			}
		}
		books.add(book);
	}

	public void removeBook(Book book) {
		books.remove(book);
	}

	public void deleteBook(Book book) {
		allBooks.remove(book);
	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public void removeCustomer(Customer customer) {
		customers.remove(customer);
	}

	public void borrowBook(String bookTitle, String personnummer) throws Exception {

		Customer customer = this.findCustomerBy(customerKey.PERSONNUMMER, personnummer);
		Book book = findBookBy(TITLE, bookTitle);
		// assumes default loanPeriod
		sortBooksBy(TITLE);

		if (customer == null) {
			throw new Exception("Customer is not in System.");
		} else if (book == null) {
			throw new Exception("Book is (currently) not in directory");
		}

		book.setStartDate(this.date);
		book.setReturnDate(this.date.plusWeeks(2)); // 2 weeks

		for (Book books : allBooks) {
			if (book.getTitle().trim().equalsIgnoreCase(books.getTitle().trim())) {
				books.incrementTimesBorrowed();
			}
			// not sure if this increments this book as well
		}

		customer.addToCurrentLoan(book);
		customer.addToLoanHistory(book);
		loanedBooks.add(book);
		books.remove(book);
	}

	public void extendLoanPeriod(String personnumer, String bookTitle) throws Exception {
		Customer customer = this.findCustomerBy(customerKey.PERSONNUMMER, personnumer);
		if (customer == null) {
			throw new Exception("Customer is not in System.");
		}

		ArrayList<Book> temp = customer.getCurrentLoans();
		Book book = null;
		for (Book theBook : temp) {
			if (theBook.getTitle().equals(bookTitle)) {
				book = theBook;
				break;
			}
		}

		if (book == null) {
			throw new Exception("Book is (currently) not in directory");
		}

		long loanPeriod = ChronoUnit.DAYS.between(book.getReturnDate(), book.getStartDate());

		if (loanPeriod >= 14 || this.checkDelay(book) > 0) {
			// return a error message
		} else {
			book.setReturnDate(book.getReturnDate().plusWeeks(2));
		}

	}

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
		customer.setDebt(debt);
		LocalDateTime date = LocalDateTime.now();
		book.setReturnDate(date);
		book.setStartDate(date);

		/* TODO: adapt text files */
		books.add(book);
		loanedBooks.remove(book);
		customer.removeFromCurrentLoan(book);

	}

	/* TODO: ---------------- SHOW ----------------------- */

	public ArrayList<Book> getDelayedBooks() {
		return delayedBooks;
	}

	public ArrayList<Book> getLoanedBooks() {
		return loanedBooks;
	}

	public ArrayList<Book> getTopTen() {
		ArrayList<Book> topTen = new ArrayList<Book>();
		ArrayList<Book> oneCopy = new ArrayList<Book>();
		this.sortAllBooksBy(TITLE);

		try {
			topTen.add(new Book("new top10", "aa", "a", "a", "a"));
			oneCopy.add(new Book("new copy", "aa", "a", "a", "a"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < this.allBooks.size(); i++) {
			//System.out.println("gettopten 1st forloop");
			int numOfCopies = 0;
			Book book = allBooks.get(i);
			oneCopy.add(book);
			for (int j = i; j < this.allBooks.size(); j++) {
			//	System.out.println("Gettopten 2nd forloop");
				if (book.getTitle().equalsIgnoreCase(this.allBooks.get(j).getTitle().trim())) {// trims
					numOfCopies++;
				}
			}
			i += numOfCopies;
			numOfCopies = 0;
		}
		
		for(int i = 0; i < 10; i++) {
			System.out.println("gettopten forloop after tryblock");
			Book theBook = oneCopy.get(i); 
			topTen.add(theBook);
		}

		try {
			System.out.println("Gettopten tryblock");
			for (Book book : oneCopy)
			Collections.sort(oneCopy, Comparator.comparing(getBookFunction(TIMESBORROWED)));
		} catch (InvalidKeyException ike) {
			System.out.println("In gettopten: was not able to finish try block");
		}
		


		return topTen;
	}

	public ArrayList<Book> getCustomerLoanHistory(Customer customer) {
		return customer.getloanHistory();
	}

	private int getCopiesOfTitle(String title) {
		int copies = 0;
		for (int i = 0; i < this.books.size(); i++) {
			if (title.trim().toLowerCase().equals(this.books.get(i).getTitle().trim().toLowerCase())) {
				copies++;
			}
		}
		return copies;
	}

	/* TODO: ---------------- Simulate ------------------------ */

	/*
	 * this simulation alters the library date and then alters the return date of
	 * all books in the loaned arrayList.
	 *
	 * IMPORTANT check whether each book has passed it's loan period and switch
	 * delayed boolean
	 */
	public static LocalDateTime getDate() {
		return date;
	}

	public void addDays(int Days) {
		this.date = this.date.plusDays(Days);
		for (Book book : loanedBooks) {
			this.isDelayed(book);
		}

	}

	public void addWeeks(int weeks) {
		this.date = this.date.plusWeeks(weeks);
		for (Book book : loanedBooks) {
			this.isDelayed(book);
		}
	}

	public void addMonths(int months) {
		this.date = this.date.plusMonths(months);
		for (Book book : loanedBooks) {
			this.isDelayed(book);
		}
	}

	public void addyears(int years) {
		this.date = this.date.plusYears(years);
		for (Book book : loanedBooks) {
			this.isDelayed(book);
		}
	}

	/*------ OTHER METHODS ---------*/
	// moves a book that is delayed to delayed arrayList. (is only used in
	// simulation)
	public void isDelayed(Book book) {
		if (this.checkDelay(book) > 0) {
			delayedBooks.add(book);
			/* TODO: move this to java */
			try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("res/delayedBooks.txt", true)))) {
				out.println(book.getTitle() + "-" + book.getAuthor() + "-" + book.getPublisher() + "-" + book.getGenre()
						+ "-" + book.getShelf());
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			System.out.println("Added " + book.getTitle() + " to delayed  books.");
		}
	}

	// returns delay surplus
	public int checkDelay(Book book) {
		if (this.date.compareTo(book.getReturnDate()) > 0) {
			return (int) ChronoUnit.DAYS.between(book.getReturnDate(), this.date);
		} else {
			return 0;
		}
	}

	// Reading a text file into arraylist: (Books)// - change the exception handling
	// for them(?)
	public void bookDirectory() throws FileNotFoundException {
		Scanner input = new Scanner(new File("res/bookDirectory.txt"));
		input.useDelimiter("-|\n");

		while (input.hasNext()) {

			String title = input.next();
			String author = input.next();
			String publisher = input.next();
			String genre = input.next();
			String shelf = input.next();

			Book book = null;
			try {
				book = new Book(title, author, publisher, genre, shelf);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				books.add(book);
			}
		}
	}

	// Reading a text file into arraylist: (Books)// - change the exception handling
	// for them(?)
	public void bookDirectory(String path) throws FileNotFoundException {
		File file = new File(path);
		Scanner input = new Scanner(file);
		input.useDelimiter("-|\n");

		while (input.hasNext()) {

			String title = input.next();
			String author = input.next();
			String publisher = input.next();
			String genre = input.next();
			String shelf = input.next();
			Book book = null;
			try {
				book = new Book(title, author, publisher, genre, shelf);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (path.contains("bookDirectory")) {
					books.add(book);
				} else if (path.contains("delayedBooks")) {
					delayedBooks.add(book);
				} else if (path.contains("LoanedBooks")) {
					loanedBooks.add(book);
				} else if (path.contains("AllBooks")) {
					allBooks.add(book);
				}
				// else if(path.contains("CurrentLoans")) {
				// customer.getLoanHistory().add(book);
				// }else if(path.contains("LoanHistory")) {
				// customer.getCurrentLoans().add(book);
				// }
			}
		}
	}

	public void importBooksFrom(String fileName) throws Exception {
		try (
				FileInputStream fis = new FileInputStream(fileName);
				BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
		) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] args = line.split("\\-");
				addBook(new Book(args[0], args[1], args[2], args[3], args[4]));
			}
		} catch (IOException ioe) {
			System.out.printf("Problems loading " + fileName + ".\n");
			ioe.printStackTrace();
		}
	}

	// Reading a txt file into arraylist (Customers)//
	public void customerDirectory() throws Exception {
		Scanner input = new Scanner(new File("res/customer.txt"));
		input.useDelimiter("/|\n");

		while (input.hasNext()) {

			String name = input.next();
			String address = input.next();
			String psn = input.next();
			String phoneNumber = input.next().trim();

			Customer customer = null;
			try {
				if (phoneNumber.equals("")) {
					customer = new Customer(name, address, psn);
				} else {
					customer = new Customer(name, address, psn, phoneNumber);
				}
				// System.out.println("Sup, " + psn);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				customers.add(customer);
			}
		}
	}

	
	public String toStringForDisplayAll() {
		String s = "\n// ========== Books ========== //\n";
		List<String> sList = new ArrayList<>();
		for (int i = 0; i < this.books.size(); i++) {
			Book book = this.books.get(i);
			if (!(sList.contains(book.toString()))) {
				sList.add(book.toString());
				s += book.toString()
						+ "\n • Copies available: " + getCopiesOfTitle(book.getTitle())
						+ "\n----------------------------------------------------------\n";
			}
		}
		return s;
	}
	
	
	public String toString() {
		String res = "";
		for (Book book : books) {
			res += book.toString() + System.lineSeparator();
		}
		System.out.println(res);
		return res;
	}
}