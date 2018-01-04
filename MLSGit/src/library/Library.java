package library;

import static library.Library.bookKey.TITLE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import library.Library.bookKey;
import library.Library.customerKey;

public class Library {

	public enum bookKey {
		TITLE, AUTHOR, GENRE, PUBLISHER, SHELF, ID, TIMESBORROWED
	}

	public enum customerKey {
		NAME, ADRESS, NUMBER, DEBT, ID, PSN
	}

	private List<Customer> customers;
	private ObservableList<Customer> customerObs;
	private List<Book> availableBooks;
	private ObservableList<Book> availableBooksObs;
	// private ArrayList<Book> allBooks;
	// private ObservableList<Book> allBooksObs;
	// private ArrayList<Book> loanedBooks;
	private ObservableList<Book> loanedBooksObs;
	// private ArrayList<Book> delayedBooks;
	private ObservableList<Book> delayedBooksObs;

	private LocalDate today = LocalDate.now(); // dont need these here??
	private LocalDate date;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private String todayString = today.format(formatter);

	public Library() {
		availableBooks = new ArrayList<Book>();
		customers = new ArrayList<Customer>();
		// allBooks = new ArrayList<Book>();
		// loanedBooks = new ArrayList<Book>();
		// delayedBooks = new ArrayList<Book>();

		// need to do this for observable lists as well??

		try {
			customerDirectory();
			bookDirectory("res/availableBooks.txt");
			//bookDirectory("res/loanedBooks.txt");
			//bookDirectory("res/delayedBooks.txt");
			// bookDirectory("res/allBooks.txt");
		} catch (FileNotFoundException e) {
			System.out.println("In library: was not able to load all text files");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("In library: was not able to load all text files");
			e.printStackTrace();
		}

		// date = LocalDate.now().minusDays(14);
		// long daysBetween = ChronoUnit.DAYS.between(date,today);
		// System.out.println(daysBetween);
		getLoanedBooks();
	}

	public void addBook(String title, String authors, String genre, String publisher, String shelf, int numOfCopies,
			int timesBorrowed, String returnDate) {
		Book book = new Book(title, authors, genre, publisher, shelf, numOfCopies, timesBorrowed, returnDate);
		availableBooks.add(book);
		availableBooksObs.add(book);

	}

	public void removeBook(String id) {
		for (int i = 0; i < availableBooksObs.size(); i++) {
			if (availableBooksObs.get(i).getId().equals(id)) {
				availableBooks.remove(i);
				availableBooksObs.remove(i);
			}
		}
	}
	//
	// public void borrowBook(String title, String id) {
	// // someCustomer.getLibraryId
	// // someCustomer.addbooktolentoutarraylist
	// for (int i = 0; i < availableBooks.size(); i++) {
	// if (customers.get(i).getId().equals(id)) {
	// if (availableBooks.get(i).getTitle().equals(title)) { // MAKE A BOOK
	// CONSTRUCTOR THAT TAKES ID AS PARAMETER I
	// // NEED IT COUGH, WE NEED IT
	// Book book = new Book(title, availableBooks.get(i).getAuthors(),
	// availableBooks.get(i).getGenre(),
	// availableBooks.get(i).getPublisher(), availableBooks.get(i).getShelf());
	// customers.get(i).addBook(book);
	// }
	// }
	// }
	// }

	public void returnBook(String bookTitle, String psn) throws Exception {
		Customer customer = this.findCustomerBy(customerKey.PSN, psn); // works??
		if (customer == null) {
			throw new Exception("Customer doesn't exist in directory");
		}
		// Book book = customer.getFromCurrentLoan(bookTitle);
		// if (book == null) {
		// throw new Exception("Book doesn't exist in directory");
		// }

		// int debt = this.checkDelay(book) * 2;
		// customer.setDebt(debt);
		// LocalDateTime date = LocalDateTime.now();
		// book.setReturnDate(date);
		// book.setStartDate(date);

		/* TODO: adapt text files */
		// books.add(book);
		// loanedBooks.remove(book); //MAKE SURE IT ONLY REMOVES ONE BOOK, SINCE SEVERAL
		// CUSTOMERS CAN BORROW BOOKS BY THE SAME TITLE
		customer.getCurrentBooks().clear();
		for (int i = 0; i < availableBooks.size(); i++) {
			if (bookTitle.equals(availableBooks.get(i).getTitle())) {
				availableBooks.get(i).incrementAvailableCopies();
			}

		}
	}

	public void addCustomer(String name, String address, String psn, String phoneNumber, int debt) throws Exception { // the
																														// id
		if (name.equals("") || address.equals("") || phoneNumber.length() > 11 || psn.length() > 12
				|| psn.length() < 9) { // add one for PSN too!
			throw new Exception("In library: Don't enter empty strings or an incomplete phone number/psn.");
		} else {
			Customer customer = new Customer(name, address, psn, phoneNumber, debt);
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

	public void bookDirectory(String path) throws FileNotFoundException {
		File file = new File(path);
		Scanner input = new Scanner(file);
		input.useDelimiter("%|\n");

		while (input.hasNext()) {

			String title = input.next();
			String author = input.next();
			String publisher = input.next();
			String genre = input.next();
			String shelf = input.next();
			int numOfCopies = Integer.parseInt(input.next());
			int timesBorrowed = Integer.parseInt(input.next());
			String returnDate = input.next();
			Book book = null;
			try {
				// if(input.next()==null) {
				// numOfCopies = 1;
				// timesBorrowed = 0;
				// }else {
				// numOfCopies = Integer.parseInt(input.next());
				// timesBorrowed = Integer.parseInt(input.next());
				// }
				book = new Book(title, author, publisher, genre, shelf, numOfCopies, timesBorrowed, returnDate);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (path.contains("availableBooks")) {
					availableBooks.add(book);
					// availableBooksObs.add(book);
				}
				// else if (path.contains("delayedBooks")) {
				// delayedBooks.add(book);
				// // delayedBooksObs.add(book);
				// }
				// else if (path.contains("loanedBooks")) {
				// loanedBooks.add(book);
				// // loanedBooksObs.add(book);
				// }
				// else if (path.contains("allBooks")) {
				// allBooks.add(book);
				// // allBooksObs.add(book);
				// }
			}
		}
	}

	public DateTimeFormatter getFormatter() {
		return formatter;
	}

	public LocalDate getToday() {
		return today;
	}

	public String getTodayString() {
		return todayString;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public ObservableList<Customer> getCustomerObs() {
		return customerObs;
	}

	public ObservableList<Book> getAvailableBooksObs() {
		return availableBooksObs;
	}

	// public ArrayList<Book> getAllBooks() {
	// return allBooks;
	// }

	// public ObservableList<Book> getAllBooksObs() {
	// return allBooksObs;
	// }

	public ArrayList<Book> getLoanedBooks() {
		ArrayList<Book> allLoanedBooks = new ArrayList<Book>();
		for (Customer customer : customers) {
			for (Book book : customer.getCurrentBooks()) {
				// System.out.println(book.getReturnDate());
				// System.out.println(book.getReturnDateString());
				if (book.getReturnDate() == null) {
					book.setReturnDate(LocalDate.parse(book.getReturnDateString(), formatter));
				}
				// System.out.println(book.getReturnDate());
				allLoanedBooks.add(book);
			}
		}
		return allLoanedBooks;
	}

	// public ObservableList<Book> getLoanedBooksObs() {
	// return loanedBooksObs;
	// }

	public ArrayList<Book> getDelayedBooks() {
		ArrayList<Book> allDelayedBooks = new ArrayList<Book>();
		for (Book book : getLoanedBooks()) {
			// System.out.println(book.toString());
			if (checkDelay(book) > 0) {
				allDelayedBooks.add(book);
			}
		}
		return allDelayedBooks;
	}

	public long checkDelay(Book book) { // need to initilize date? or can i just use today??
		long daysBetween = ChronoUnit.DAYS.between(book.getReturnDate(), today);
		System.out.println(daysBetween);
		if (daysBetween > 0) {

			// return (int) ChronoUnit.DAYS.between(book.getReturnDate(), this.today);
			return daysBetween;
		} else {
			return 0;
		}
	}

	// public ObservableList<Book> getDelayedBooksObs() {
	// return delayedBooksObs;
	// }

	// public ObservableList<Book> bookDirectoryForGUI() throws
	// FileNotFoundException {
	// Scanner input = new Scanner(new File("res/availableBooks.txt"));
	// input.useDelimiter("%|\n");
	// availableBooksObs = FXCollections.observableArrayList();
	//
	// while (input.hasNext()) {
	//
	// String title = input.next();
	// String author = input.next();
	// String publisher = input.next();
	// String genre = input.next();
	// String shelf = input.next();
	// int numOfCopies = input.nextInt();
	// int timesBorrowed = input.nextInt();
	// String returnDate = input.next();
	//
	// Book book = new Book(title, author, publisher, genre, shelf, numOfCopies,
	// timesBorrowed, returnDate);
	// availableBooksObs.add(book);
	// }
	// return availableBooksObs;
	//
	// }

	@SuppressWarnings("finally")
	public ObservableList<Book> bookDirectoryForGUI(String path) throws FileNotFoundException {
		File file = new File("res/" + path + ".txt");
		Scanner input;

		
		if (path.contains("availableBooks")) {
			input = new Scanner(file);
			input.useDelimiter("%|\n");
			availableBooksObs = FXCollections.observableArrayList();
			while (input.hasNext()) {
				String title = input.next();
				String author = input.next();
				String publisher = input.next();
				String genre = input.next();
				String shelf = input.next();
				int numOfCopies = Integer.parseInt(input.next());
				int timesBorrowed = Integer.parseInt(input.next());
				String returnDate = input.next();
				Book book = null; // change this!!
				try {
					book = new Book(title, author, publisher, genre, shelf, numOfCopies, timesBorrowed, returnDate);
					book.setReturnDate(LocalDate.parse(returnDate, formatter));
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					availableBooksObs.add(book);
				}
			}
			
			return availableBooksObs;
		} else if (path.contains("delayedBooks")) {
			delayedBooksObs = FXCollections.observableArrayList();
			delayedBooksObs.addAll(getDelayedBooks());
		//	break;
			return delayedBooksObs;
		} else if (path.contains("loanedBooks")) {
			loanedBooksObs = FXCollections.observableArrayList();
			loanedBooksObs.addAll(getLoanedBooks());
		//	break;
			return loanedBooksObs;
		}
//		while (input.hasNext()) {
//			
//
//			try {
//				
//			} catch (Exception e) {
//				
//			} finally {
//				if (path.contains("availableBooks")) {
//					
//				} else if (path.contains("delayedBooks")) {
//					// delayedBooksObs.add(book);
//
//				} else if (path.contains("loanedBooks")) {
//					
//				}
//			}
//		}
//		if (path.contains("availableBooks")) {
//			
//		} else if (path.contains("delayedBooks")) {
//			
//		} else if (path.contains("loanedBooks")) {
//			
//		}
		return null;
	}

	public void customerDirectory() throws Exception {
		Scanner input = new Scanner(new File("res/customer.txt"));
		input.useDelimiter("/|\n");

		while (input.hasNext()) {

			String name = input.next();
			String address = input.next();
			String phoneNumber = input.next();
			// String phoneNumberInt = input2.next();
			String psn = input.next();
			int debt = input.nextInt();

			Customer customer = new Customer(name, address, phoneNumber, psn, debt);
			customers.add(customer);
		}
	}

	public ObservableList<Customer> customerDirectoryForGUI() throws Exception { // not gonna work with displayeing cuz
																					// they all need to be string
		Scanner input = new Scanner(new File("res/customer.txt"));
		input.useDelimiter("/|\n");
		customerObs = FXCollections.observableArrayList();

		while (input.hasNext()) {

			String name = input.next();
			String address = input.next();
			String phoneNumber = input.next();
			String psn = input.next();
			int debt = input.nextInt();

			Customer customer = new Customer(name, address, phoneNumber, psn, debt);
			customerObs.add(customer);
		}
		// for (Book book : booksObs) {
		// System.out.println(book);
		// }
		return customerObs;

	}

	public void addToArrayList(ObservableList<Book> list) throws FileNotFoundException {
		Scanner input = new Scanner(new File("res/availableBooks.txt"));
		input.useDelimiter("%|\n");

		while (input.hasNext()) {

			String title = input.next();
			String author = input.next();
			String publisher = input.next();
			String genre = input.next();
			String shelf = input.next();
			int numOfCopies = Integer.parseInt(input.next());
			int timesBorrowed = Integer.parseInt(input.next());
			String returnDate = input.next();

			Book book = new Book(title, author, publisher, genre, shelf, numOfCopies, timesBorrowed, returnDate);
			list.add(book);
		}
	}

	public void addToArrayList(ObservableList<Book> list, String txtFile) throws FileNotFoundException {
		Scanner input = new Scanner(new File("res/" + txtFile + ".txt"));
		input.useDelimiter("%|\n");

		while (input.hasNext()) {

			String title = input.next();
			String author = input.next();
			String publisher = input.next();
			String genre = input.next();
			String shelf = input.next();
			int numOfCopies = Integer.parseInt(input.next());
			int timesBorrowed = Integer.parseInt(input.next());
			String returnDate = input.next();

			Book book = new Book(title, author, publisher, genre, shelf, numOfCopies, timesBorrowed, returnDate);
			list.add(book);
		}
	}

	public List<Book> getAvailableBooks() {
		return availableBooks;
	}

	public boolean getBookTitle(String title) {

		for (int i = 0; i < availableBooks.size(); i++) {
			if (title.equals(availableBooks.get(i).getTitle())) {
				title = availableBooks.get(i).getTitle();
				return true;
			}
		}
		System.out.println("library: Book not in database");
		return false;
	}

	public boolean getCustomerPSN(String psn) {

		for (int i = 0; i < customers.size(); i++) {
			if (psn.equals(customers.get(i).getPsn())) {
				psn = customers.get(i).getPsn();
				return true;
			}
		}
		System.out.println("library: customer not in database");
		return false;
	}

	// public ObservableList<Book> getBooksObs() {
	// return booksObs;
	// }

	public String toString() {
		String s = "";
		for (Book book : availableBooks)
			s += book.toString();
		return s;
	}

	// methods added from library system 2- move them later!!

	public void borrowBook(String bookTitle, String psn) throws Exception {

		Customer customer = this.findCustomerBy(customerKey.PSN, psn);
		Book book = findBook(bookTitle);
		// System.out.println("In library: customer and book not yet checked!");
		if (customer == null) {
			// System.out.println("Library: Customer not in system");
			throw new Exception("Customer is not in System.");
		} else if (book == null) {
			// System.out.println("Library: Book not in system");
			throw new Exception("Book is (currently) not in directory");
		}
		// System.out.println("In library: customer and book ok!");
		// book.setStartDate(this.date);
		// book.setReturnDate(this.date.plusWeeks(2)); // 2 weeks
		//
		// for (Book books : availableBooks) {
		// //System.out.println("In library: in for loop");
		// if (book.getTitle().trim().equalsIgnoreCase(books.getTitle().trim())) {
		// //System.out.println("In library: in if statement");
		// books.incrementTimesBorrowed();
		// book.decrementAvailableCopies();
		// //System.out.println("In library: end of if statement");
		// }
		// // not sure if this increments this book as well
		// }
		// incrementDecrement(bookTitle);
		customer.addToCurrentLoan(book);
		customer.addToLoanHistory(book);
		// loanedBooks.add(book);
		System.out.println("In library: added book to arraylists");
		// availableBooks.remove(book);
	}

	// i dont know much about these. would like to change methods so that i dont
	// have to use these
	public Customer findCustomerBy(customerKey key, String searchValue) throws InvalidKeyException {
		searchValue.toLowerCase();
		switch (key) {
		case NAME:
			return findCustomerByString(searchValue, Customer::getName);
		case ADRESS:
			return findCustomerByString(searchValue, Customer::getAddress);
		case NUMBER:
			return findCustomerByString(searchValue, Customer::getPhoneNumber);
		case ID:
			for (Customer customer : customers)
				if (customer.getId().toString().equals(searchValue))
					return customer;
		case PSN:
			return findCustomerByString(searchValue, Customer::getPsn);
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

	// public Book findBookBy(bookKey key, String searchValue) throws
	// InvalidKeyException {
	// searchValue.toLowerCase();
	// switch (key) {
	// case TITLE: return findBookByString(searchValue, Book::getTitle);
	// case GENRE: return findBookByString(searchValue, Book::getGenre);
	// case PUBLISHER: return findBookByString(searchValue, Book::getPublisher);
	// case ID:
	// for (Book book : availableBooks)
	// if (book.getId().toString().equals(searchValue)) return book;
	// default: throw new InvalidKeyException("Invalid key in search function.");
	// }
	// }

	// private Book findBookByString(String s, Function<Book, ? extends Comparable>
	// f) throws NullPointerException {
	// s.toLowerCase();
	// for (Book book : this.availableBooks)
	// if (s.equals(((String) f.apply(book)).toLowerCase())) return book;
	// return null;
	// }

	public Book findBook(String title) {
		for (int i = 0; i < availableBooks.size(); i++) {
			if (availableBooks.get(i).getTitle().equals(title)) {
				return availableBooks.get(i);
			}
		}
		return null;
	}

	public void incrementDecrement(String title) {
		for (int i = 0; i < availableBooks.size(); i++) {
			if (availableBooks.get(i).getTitle().equals(title)) {
				availableBooks.get(i).incrementTimesBorrowed();
				availableBooks.get(i).decrementAvailableCopies();
			}
		}
	}

	public String parseBookToString(Book book) {
		return book.getTitle() + "%" + book.getAuthor() + "%" + book.getPublisher() + "%" + book.getGenre() + "%"
				+ book.getShelf() + "%" + book.getNumOfCopies() + "%" + book.getTimesBorrowed() + "%"
				+ book.getReturnDateString();
	}

	public String parseCustomerToString(Customer customer) {
		return customer.getName() + "/" + customer.getAddress() + "/" + customer.getPsn() + "/"
				+ customer.getPhoneNumber() + "/" + customer.getDebt();
	}

	public void writeCustomerToFile(String path, Customer customer) {
		if (!customer.getName().equals("") && !customer.getAddress().equals("") && !customer.getPsn().equals("")
				&& !customer.getPhoneNumber().equals("") && customer.getDebt() >= 0) {
			try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)))) {
				out.println(customer.getName() + "/" + customer.getAddress() + "/" + customer.getPsn() + "/"
						+ customer.getPhoneNumber() + "/" + customer.getDebt());
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			// System.out.println("In Write book to file: Added " + book.getTitle() + " to
			// library");
		} else {
			System.out.println("In write book to file: No parameters allowed to be empty");
		}
	}

	public int getCustomerDebt(String psn) {
		int debt = 0;
		for (Customer customer : customers) {
			if (customer.getPsn().equals(psn)) {
				for (Book book : customer.getCurrentBooks()) {
					debt += (int) this.checkDelay(book) * 2; // should it be += or = ??
				}
			}
		}
		return debt;
	}

	public void changeCopiesAndTimesBorrowed(String title, boolean borrowing) {
		for (int i = 0; i < availableBooks.size(); i++) {
			if (borrowing) {
				//ArrayList<Book> books = new ArrayList<Book>();
				if (availableBooks.get(i).getTitle().equals(title)) {
					Book book = availableBooks.get(i); //Gets all books with no stop lol
					removeLineFromFile("res/availableBooks.txt", parseBookToString(book));
					book.decrementAvailableCopies();
					book.incrementTimesBorrowed();
//					books.add(book);
//					for(Book book2 : books) {
//						System.out.println(book2.getTitle());
//					}
					writeBookToFile("res/availableBooks.txt", book);
					
				}
			//	writeBookToFile("res/availableBooks.txt", otherBooks);
			} else if (!borrowing) { // can be used when returning a book!
				if (availableBooks.get(i).getTitle().equals(title)) {
					Book book = availableBooks.get(i);
					// wrong order??
					removeLineFromFile("res/availableBooks.txt", parseBookToString(getAvailableBooks().get(i)));
					book.incrementAvailableCopies();
					writeBookToFile("res/availableBooks.txt", book);
				}
			}
		}
	}
	
    public void removeLineFromFile(String path, String lineToRemove) {
        int count = 0;
        try {
            File dirFile = new File(path);
            File tmpFile = new File(dirFile.getAbsolutePath() + ".tmp");
            BufferedReader br = new BufferedReader(new FileReader(path));
            PrintWriter pw = new PrintWriter(new FileWriter(tmpFile));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.equals(lineToRemove.trim()) || count > 0) {
                    pw.println(line);
                    pw.flush();
                } else {
                    if (count == 0) {
                        count++;
                    }
                }
            }
            System.gc();
            pw.close();
            br.close();
            boolean success = dirFile.delete();
            boolean renameSuccess = tmpFile.renameTo(dirFile);

            if (success) {
                //System.out.println("Old file deleted");
            }
            if (renameSuccess) {
                //System.out.println("file renamed");
            }
        } catch (Exception e) {
            // e.getMessage();
            System.out.println("In remove line from file: Not able to complete method");
        }
    }

//	public void removeLineFromFile(String path, String lineToRemove) {
//		try {
//			File dirFile = new File(path);
//			File tmpFile = new File(dirFile.getAbsolutePath() + ".tmp");
//			BufferedReader br = new BufferedReader(new FileReader(path));
//			PrintWriter pw = new PrintWriter(new FileWriter(tmpFile));
//			String line;
//			while ((line = br.readLine()) != null) {
//				if (!line.equals(lineToRemove.trim())) {
//					// GONNA DELETE ALL BOOKS WITH THAT TITLE, FIX PLS
//					pw.println(line);
//					pw.flush();
//					break;
//				}
//			}
//			System.gc();
//			pw.close();
//			br.close();
//			boolean success = dirFile.delete();
//			boolean renameSuccess = tmpFile.renameTo(dirFile);
//			
//			if (success) {
//				// System.out.println("Old file deleted");
//			}
//			if (renameSuccess) {
//				// System.out.println("file renamed");
//			}
//		} catch (Exception e) {
//			// e.getMessage();
//			System.out.println("In remove line from file: Not able to complete method");
//		}
//	}

	public void writeBookToFile(String path, Book book) {
		if (!book.getTitle().equals("") && !book.getAuthor().equals("") && !book.getPublisher().equals("")
				&& !book.getGenre().equals("") && !book.getShelf().equals("")) {
			try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)))) {
				out.println(book.getTitle() + "%" + book.getAuthor() + "%" + book.getPublisher() + "%" + book.getGenre()
						+ "%" + book.getShelf() + "%" + book.getNumOfCopies() + "%" + book.getTimesBorrowed() + "%"
						+ book.getReturnDateString());
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			// System.out.println("In Write book to file: Added " + book.getTitle() + " to
			// library");
		} else {
			System.out.println("In write book to file: No parameters allowed to be empty");
		}
	}
	
	public boolean inCustomerCurrentLoans(String psn, String title, String author) {
		for(Customer customer : customers) {
			for(int i = 0; i<customer.getCurrentBooks().size();i++) {
				if(customer.getPsn().equals(psn) && customer.getCurrentBooks().get(i).getTitle().equals(title) && customer.getCurrentBooks().get(i).getAuthors().equals(author)) {
					return true;
				}
			}
			
		}	
		return false;
	}
	
	public Customer findCustomer (String psn) {
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getPsn().equals(psn)) {
				return customers.get(i);
			}
		}
		return null;
	}
	
	public boolean listContainsCurrentBooks(ObservableList<Book> list, String psn) {
		Customer customer = findCustomer(psn);
		int counter = 0;
		if(customer.getCurrentBooks().size()==0 || customer.getCurrentBooks()==null) {
			return false;
		}
		for(int i = 0; i<customer.getCurrentBooks().size();i++) { //not sure this will work...
		//	if(customer.getCurrentBooks().get(i).getTitle().equals(list.get(i).getTitle())) {
			
			 if(list.get(counter).getTitle().contains(customer.getCurrentBooks().get(i).getTitle()) && list.get(counter).getAuthors().contains(customer.getCurrentBooks().get(i).getAuthors())) { 
				if(counter<list.size()) { //should this be outside??
					counter++;
				}else {
					counter = 0;
				}
				return true;
			}
		}
		return false;
	}
}