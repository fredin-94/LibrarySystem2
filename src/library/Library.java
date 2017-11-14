package library;

/*TODO:
 * - Search
 * 	- books		done
 * 	- customers		done
 * 
 * -Register
 * 	- register books		done
 * 	- register customer		done
 * 
 * -Loan and return
 * 	- loan default	x
 * 	- loan with period	x	
 * 	- return		x
 * 
 * - Simulate	
 * 	- days		done
 * 	- months		done
 * 	- years		done
 * 
 * - Show:
 * 	- all borrowed books		done
 * 	- all delayed books		done
 * 	- most borrowed book		done
 * 	- customer loan history		done
 * 
 * */

import java.security.*;
import java.util.function.*;
//--------------------
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Library {

	private ArrayList<Book> books;
	private ArrayList<Book> loanedBooks;
	private ArrayList<Book> delayedBooks;
	private ArrayList<Customer> customers;
	private LocalDate date;

	public Library() {
		books = new ArrayList<Book>();
		loanedBooks = new ArrayList<Book>();
		delayedBooks = new ArrayList<Book>();
		customers = new ArrayList<Customer>();
		date = LocalDate.now();
	}

	/* TODO ---------------------Basic------------------------------- */

	public ArrayList<Book> getBooks() {
		return books;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	/* TODO ---------------------SEARCH------------------------------ */

	/* Search for books in the library directory */

	/* Search for Customers in the library directory */

	// there could be more than one person with the same name
	public ArrayList<Customer> findCustomersByName(String name) {
		ArrayList<Customer> foundCustomers = new ArrayList<Customer>();
		for (Customer customer : customers) {
			if (customer.getName().equals(name)) {
				foundCustomers.add(customer);
			}
		}

		if (foundCustomers.isEmpty()) {
			return null;
		} else {
			return foundCustomers;
		}
	}

	public Customer findCustomerByName(String name) {
		for (Customer customer : customers) {
			if (customer.getName().equals(name)) {
				return customer;
			}
		}
		return null;
	}

	public Customer findCustomerById(UUID id) {
		for (Customer customer : customers) {
			if (customer.getID().equals(id)) {
				return customer;
			}
		}
		return null;
	}

	public Book findBookByTitle(String whichTitle) {
		whichTitle.toLowerCase();
		for (Book book : books)
			if (whichTitle.equals(book.getTitle().toLowerCase()))
				return book;
		return null;
	}

	/*---------------------SORTING------------------------------*/

	public enum bookKey {
		TITLE, AUTHOR, GENRE, PUBLISHER, SHELF
	}

	public void sortBooksBy(bookKey keyToSort) {
		try {
			Collections.sort(this.books, Comparator.comparing(getBookFunction(keyToSort)));
		} catch (InvalidKeyException ike) {
			ike.printStackTrace();
		}
	}

	private Function<Book, ? extends Comparable> getBookFunction(bookKey key) throws InvalidKeyException {
		switch (key) {
		case TITLE:
			return Book::getTitle; // No need for break since return automatically breaks the switch.
		case AUTHOR:
			return Book::getAuthor;
		case GENRE:
			return Book::getGenre;
		case PUBLISHER:
			return Book::getPublisher;
		case SHELF:
			return Book::getShelf;
		default:
			throw new InvalidKeyException("Invalid key in sort function");
		}
	}

	public enum customerKey {
		NAME, ADRESS, NUMBER, DEBT
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
				// TODO: NEEDS testing. Not sure if this works for primitive types.
				Collections.sort(customers, Comparator.comparing(Customer::getNumber));
				break;
			case DEBT:
				// TODO: Same as above.
				Collections.sort(customers, Comparator.comparing(Customer::getDebt));
				break;
			default:
				throw new InvalidKeyException("Invalid key in sort function");
			}
		} catch (InvalidKeyException ike) {
			ike.printStackTrace();
		}
	}

	/* TODO -------------------REGISTRATION--------------------- */

	/* register books */
	public void addBook(Book book) {
		books.add(book);
	}

	public void removeBook(Book book) {
		books.remove(book);
	}

	/*
	 * TODO register Customers
	 */
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public void removeCustomer(Customer customer) {
		customers.remove(customer);
	}

	/*---------------------loan and return-----------------*/

	/*
	 * TODO loan book
	 */
	public void borrowBook(String bookTitle, UUID customerId) throws Exception {
		Customer customer = findCustomerById(customerId);
		Book book = findBookByTitle(bookTitle);
		// assumes default loanPeriod

		if (customer == null) {
			throw new Exception("Customer is not in System.");
		} else if (book == null) {
			throw new Exception("Book is (currently) not in directory");
		}

		book.setStartDate(this.date);
		book.setReturnDate(this.date.plusWeeks(2)); // 2 weeks
		book.incrementTimesBorrowed();
		;
		loanedBooks.add(book);
		customer.addToCurrentLoan(book);
		customer.addToLoanHistory(book);
		books.remove(book);

	}

	public void borrowBookDay(String bookTitle, UUID customerId, int loanPeriod) throws Exception {
		Customer customer = findCustomerById(customerId);
		Book book = findBookByTitle(bookTitle);
		// assumes default loanPeriod

		if (customer == null) {
			throw new Exception("Customer is not in System.");
		} else if (book == null) {
			throw new Exception("Book is (currently) not in directory");
		} else if (loanPeriod <= 0) {
			throw new Exception("Loan Period needs to be larger than zero");
		}

		book.setStartDate(this.date);
		book.setReturnDate(this.date.plusDays(loanPeriod));
		book.incrementTimesBorrowed();
		loanedBooks.add(book);
		customer.addToCurrentLoan(book);
		customer.addToLoanHistory(book);
		books.remove(book);

	}

	public void returnBook(String bookTitle, UUID customerId) {
		/*
		 * TODO: -check date //done -calculate debt //done -increment customer debt //
		 * done -restart book date //done -return book to library //done -remove book
		 * out of loanedBooks library -remove book from customer currentBooks //done
		 */

		Customer customer = findCustomerById(customerId);
		Book book = findBookByTitle(bookTitle);

		int debt = this.checkDelay(book) * 2;
		customer.setDebt(debt);

		/*
		 * TODO we need to "restart" the dates once the book is returned
		 * book.restartDates(); book.restartLoanPeriod(); book.notDelayed();
		 */
		// we should discuss whether the books should be set to a
		// certain day when they are not being loaned out

		books.add(book);
		loanedBooks.remove(book);
		customer.removeFromCurrentLoan(book);

	}

	/* TODO: ---------------- SHOW ----------------------- */
	/*- show all currently loaned books
	 *- show all delayed books
	 *- most popular book
	 *- customer loan history
	 */
	public ArrayList<Book> getDelayedBooks() {
		return delayedBooks;
	}

	public ArrayList<Book> getLoanedBooks() {
		return loanedBooks;
	}

	public Book getMostPopularBook() {
		Book mostPopular = books.get(0);
		for (Book book : books) {
			if (book != books.get(0) && book.getTimesBorrowed() > mostPopular.getTimesBorrowed()) {
				mostPopular = book;
			}
		}
		return mostPopular;
	}

	public ArrayList<Book> getCustomerLoanHistory(Customer customer) {
		return customer.getloanHistory();
	}

	/* TODO: ---------------- Simulate ------------------------*/

	/*
	 * this simulation alters the library date and then alters the return date of
	 * all books in the loaned arrayList.
	 *
	 * IMPORTANT check whether each book has passed it's loan period and switch
	 * delayed boolean
	 */
	public LocalDate getDate() {
		return this.date;
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
		}
	}

	//returns delay surplus
	public int checkDelay(Book book) {
		if (LocalDate.now().compareTo(book.getReturnDate()) > 0) {
			return (int) ChronoUnit.DAYS.between(book.getReturnDate(), LocalDate.now());
		} else {
			return 0;
		}
	}

}
