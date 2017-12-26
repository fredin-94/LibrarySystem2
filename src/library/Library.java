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

	public Library() {
		allBooks = new ArrayList<Book>();
		books = new ArrayList<Book>();
		customers = new ArrayList<Customer>();
		date = LocalDate.now();

		try {
			customerDirectory();
			// bookDirectory("res/bookDirectory.txt");
			// bookDirectory("res/LoanedBookssd.txt");
			// bookDirectory("res/delayedBooks.txt");
			// bookDirectory("res/AllBooks.txt");
		} catch (Exception e) {
			System.out.println("Unable to initialize customer directory");
		}

		try {
			bookDirectory("res/bookDirectory.txt");
		} catch (Exception e) {
			System.out.println("Unable to initialize available books directory");
		}

		try {
			//bookDirectory("res/LoanedBooks.txt");
		} catch (Exception e) {
			System.out.println("Unable to initialize loaned books directory");
		}

		try {
			// bookDirectory("res/delayedBooks.txt");
		} catch (Exception e) {
			System.out.println("Unable to initialize delayed books directory");
		}

		try {
			bookDirectory("res/AllBooks.txt");
		} catch (Exception e) {
			System.out.println("Unable to initialize all books directory");
		}
	}

	/* TODO ---------------------Basic------------------------------- */

	public ArrayList<Book> getAllBooks() {
		return allBooks;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public ArrayList<Book> getLoanedBooks() {
		ArrayList<Book> allLoanedBooks = new ArrayList<Book>();
		for (Customer customer : customers) {
			for (Book book : customer.getCurrentLoans()) {
				allLoanedBooks.add(book);
			}
		}
		return allLoanedBooks;
	}

	public ArrayList<Book> getDelayedBooks() {
		ArrayList<Book> allDelayedBooks = new ArrayList<Book>();
		for (Book book : getLoanedBooks()) {
			if (checkDelay(book) > 0) {
				allDelayedBooks.add(book);
			}
		}
		return allDelayedBooks;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	/* TODO: ---------------- Simulate ------------------------ */

	public LocalDate getDate() {
		return date;
	}

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

	/* TODO---------------------SEARCH------------------------------ */
	// DON'T CHANGE FORMAT PLEASE.
	public enum bookKey {
		TITLE, AUTHOR, GENRE, PUBLISHER, SHELF, ID, TIMESBORROWED
	}

	public enum customerKey {
		NAME, ADRESS, NUMBER, DEBT, ID, PERSONNUMMER
	}

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

	// ----- Search for customer ----- //
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

	private Customer findCustomerByString(String searchValue, Function<Customer, ? extends Comparable> f)
			throws NullPointerException {
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

	/* TODO---------------------SORTING------------------------------ */

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

	/* TODO -------------------Administration--------------------- */

	/* register books */
	public void addBook(Book book) {
		if (allBooks.contains(book) == false) {
			allBooks.add(book);
		}
		books.add(book);
	}

	public void removeBook(Book book) {
		books.remove(book);
	}

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

	public void addCustomer(Customer customer) throws Exception {
		if (customer == null) {
			throw new Exception("Customer object is null");
		} else {
			customers.add(customer);
		}
	}

	public void removeCustomer(Customer customer) {
		customers.remove(customer);
	}

	/* TODO -------------------loan and return--------------------- */
	public void borrowBook(String bookTitle, String personnummer) throws Exception {

		Customer customer = this.findCustomerBy(customerKey.PERSONNUMMER, personnummer);
		Book book = searchForBook(bookTitle).get(0);
		// assumes default loanPeriod
		//sortBooksBy(TITLE);		*** this line of code was changing the title to have a first letter uppercase even
		// 								if it was put in by the user as lower case ***
		book.setReturnDate(this.date.plusWeeks(2)); // 2 weeks //Need to delete this??

		book.incrementTimesBorrowed();
		for (Book books : allBooks) {
			if (book.getTitle().trim().equalsIgnoreCase(books.getTitle().trim())) {
				//books.incrementTimesBorrowed();// not sure if this increments this book as well
			}
		}
		customer.addToCurrentLoan(book);
		customer.addToLoanHistory(book);
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
			if (theBook.getTitle().trim().equalsIgnoreCase(bookTitle.trim())) {
				book = theBook;
				break;
			}
		}

		if (book == null) {
			throw new Exception("Book is not in" + customer.getName() + "'s current loans");
		}

		if (this.checkDelay(book) > 0) {
			throw new Exception("Book cannot be extended because it is delayed");
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
		LocalDate restartDate = LocalDate.of(2017, 10, 31);
		;
		book.setReturnDate(restartDate);
		books.add(book);
		customer.removeFromCurrentLoan(book);

		if (debt > 0) {
			System.out.println(customer.getName() + " returned the book " + (debt / 2) + " days after the return date."
					+ "\nA fee of " + debt + " SEK has been placed on customers account.");
		} else {
			System.out.println("** Customer returned the book on time. **");
		}

	}

	/* TODO: ---------------- Extra ----------------------- */

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

	public void addBookToList(String title, ArrayList<Book> list) {

		for (int i = 0; i < allBooks.size(); i++) {
			if (title.equals(allBooks.get(i).getTitle())) {
				list.add(allBooks.get(i));
				break;
			}
		}
	}

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

	public String getCustomerLoanHistoryString(Customer customer) {
		String current = "";
		String END_OF_LINE = "\n";
		if (customer.getLoanHistory().isEmpty()) {
			current += "\n--------- Customer's has no current loan(s) ---------" + END_OF_LINE;
		} else {
			System.out.println("\n--------- Customer's loan history: ---------");
			for (int i = 0; i < customer.getLoanHistory().size(); i++) {
				current += customer.getLoanHistory().get(i).toString() + END_OF_LINE;
			}
		}
		return current;
	}

	public String getCustomerCurrentLoanString(Customer customer) {
		String currentLoan = "";
		String END_OF_LINE = "\n";
		if (customer.getCurrentLoans().isEmpty()) {
			currentLoan += "\n--------- Customer's loan is empty ---------" + END_OF_LINE;
		} else {
			System.out.println("\n--------- Customer's current loan(s): ---------");
			for (int i = 0; i < customer.getCurrentLoans().size(); i++) {
				currentLoan += customer.getCurrentLoans().get(i).toString() + END_OF_LINE;
			}
		}
		return currentLoan;
	}

	/* TODO ------ OTHER METHODS --------- */
	// returns delay surplus
	public int checkDelay(Book book) {
		if (this.date.compareTo(book.getReturnDate()) > 0) {
			return (int) ChronoUnit.DAYS.between(book.getReturnDate(), this.date);
		} else {
			return 0;
		}
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

	// TODO -------------Text Files-----------------------------
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
				} else if (path.contains("delayedBooks")) {
					// delayedBooks.add(book);
				} else if (path.contains("LoanedBooks")) {
					// loanedBooks.add(book);
				} else if (path.contains("AllBooks")) {
					allBooks.add(book);
				}
			}
		}
	}

	public void importBooksFrom(String fileName) throws Exception {
		try (FileInputStream fis = new FileInputStream(fileName);
				BufferedReader reader = new BufferedReader(new InputStreamReader(fis));) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] args = line.split("\\/"); // change back to \\- ???
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