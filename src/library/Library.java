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
 * 	- days		x
 * 	- months		x
 * 	- years		x
 * 
 * - Show:
 * 	- all borrowed books		x
 * 	- all delayed books		x
 * 	- most borrowed book		x
 * 	- customer loan history		x
 * 
 * */

/* note: [lendBook();] consider the date applications
 * - how to use boolean in books to control delayed feature
 * */

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

	/*TODO ---------------------SEARCH------------------------------*/
	
	/*Search for books in the library directory*/
	
	public Book findBookByTitle(String title) {
		for(Book book: books) {
			if(book.getTitle().equals(title)) {
				return book;
			}
		}
		return null;
	}
	
	/*Search for Customers in the library directory*/
	
	// there could be more than one person with the same name
	public ArrayList<Customer> findCustomersByName(String name) {
		ArrayList<Customer> foundCustomers = new ArrayList<Customer>();
		for(Customer customer: customers) {
			if(customer.getName().equals(name)) {
				foundCustomers.add(customer);
			}
		}
		
		if(foundCustomers.isEmpty()) {
			return null;
		}else {
			return foundCustomers;
		}
	}
	
	public Customer findCustomerByName(String name) {
		for(Customer customer: customers) {
			if(customer.getName().equals(name)) {
				return customer;
			}
		}
		return null;
	}
	
	public Customer findCustomerById(UUID id) {
		for(Customer customer: customers) {
			if(customer.getID().equals(id)) {
				return customer;
			}
		}
		return null;
	}
	
	
	/*TODO -------------------REGISTRATION---------------------*/
	
	/*register books*/
	public void addBook(Book book) {
		books.add(book);
	}
	
	public void removeBook(Book book) {
		books.remove(book);
	}
	
	/*TODO
	 * register Customers*/
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}
	
	public void removeCustomer(Customer customer) {
		customers.remove(customer);
	}
	
	
	/*---------------------loan and return-----------------*/
	
	/*TODO loan book
	 * */
	public void borrowBook(String bookTitle, UUID customerId) throws Exception{
		Customer customer = findCustomerById(customerId);
		Book book = findBookByTitle(bookTitle);
		//assumes default loanPeriod
		
		if(customer == null) {
			throw new Exception ("Customer is not in System.");
		}else if(book == null) {
			throw new Exception ("Book is (currently) not in directory");
		}
		
		//TODO book.setLoanPeriod(book.getLoanPeriod());
		book.setStartDate(this.date);
		book.setReturnDate(this.date);
		book.incrementTimesBorrowed();;
		loanedBooks.add(book);
		customer.addToCurrentLoan(book);
		customer.addToLoanHistory(book);
		this.removeBook(book);
		
	}
	
	public void borrowBookDay(String bookTitle, UUID customerId, int loanPeriod) throws Exception{
		Customer customer = findCustomerById(customerId);
		Book book = findBookByTitle(bookTitle);
		//assumes default loanPeriod
		
		if(customer == null) {
			throw new Exception ("Customer is not in System.");
		}else if(book == null) {
			throw new Exception ("Book is (currently) not in directory");
		}else if(loanPeriod <= 0) {
			throw new Exception ("Loan Period needs to be larger than zero");
		}else {
			//TODO we should discuss this [book.setLoanPeriod(loanPeriod);]
		}
		
		book.setStartDate(this.date);
		book.setReturnDate(this.date);
		book.incrementTimesBorrowed();
		loanedBooks.add(book);
		customer.addToCurrentLoan(book);
		customer.addToLoanHistory(book);
		this.removeBook(book);
		
	}
	
	
	public void returnBook(String bookTitle, UUID customerId) {
		/*TODO:
		 * -check date 		//done
		 * -calculate debt  //done
		 * -increment customer debt  // done
		 * -restart book date 		//done
		 * -return book loan period to TWO_WEEKS 		//done
		 * -return book to library 		//done
		 * -remove book out of loanedBooks library
		 * -remove book from customer currentBooks 		//done
		 * */
		
		Customer customer = findCustomerById(customerId);
		Book book = findBookByTitle(bookTitle);
		//final int TWO_WEEKS = 14;
		//final int LOAN_TIME = book.getLoanPeriod();
		//assumes default loanPeriod
		
		
		/*TODO fix calculation 
		int period = this.daysBetween(book);
		int debt = 0;
		
		if(period <= LOAN_TIME) {
		}else {
			int surplus = period - TWO_WEEKS;
			debt = surplus * 2;
		}
		customer.setDebt(debt);*/
		
		/*TODO we need to "restart" the dates once the book is returned
		book.restartDates();
		book.restartLoanPeriod();
		book.notDelayed();*/
		//we should discuss whether the books should be set to a 
		//certain day when they are not being loaned out
		
		this.addBook(book);
		this.loanedBooks.remove(book);
		customer.removeFromCurrentLoan(book);
		
	}
	
	/*TODO: ---------------- SHOW -----------------------*/
	/*- show all currently loaned books
	 *- show all delayed books
	 *
	 * */
	//public void 
	
	
	/*TODO: ---------------- Simulate -----------------------*/
	
	/*
	 *this simulation alters the library date and then alters the return date of all books in 
	 *the loaned arrayList.
	 *
	 *IMPORTANT
	 *check whether each book has passed it's loan period and switch delayed boolean
	 * */
	public LocalDate getDate() {
		return this.date;
	}
	
	public void addDays(int Days) {
		this.date = this.date.plusDays(Days);
		
	}
	
	public void addWeeks(int weeks) {
		this.date = this.date.plusWeeks(weeks);
		
	}
	
	public void addMonths(int months) {
		this.date = this.date.plusMonths(months);
		
	}
	
	public void addyears(int years) {
		this.date = this.date.plusYears(years);
		
	}
	
	
	/*------ OTHER METHODS ---------*/
	
	//CALCULATING DAYS BETWEEN
	
	public long daysBetween(Book book) {
		long days = book.getStartDate().until(book.getReturnDate(), ChronoUnit.DAYS);
		return days;
	}
	
	//TODO mark a book as delayed or not delayed
	//public boolean isDelayed(Book book) {
	//	
	//}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public ArrayList<Book> getDelayedBooks() {
		return delayedBooks;
	}

	public ArrayList<Book> getLoanedBooks() {
		return loanedBooks;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}
	
	
}



