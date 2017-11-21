package library;

import java.io.File;
import java.io.FileNotFoundException;

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
import static library.Library.bookKey.*;
//--------------------
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import static library.Library.bookKey.*; 
import static library.Library.customerKey.*;

public class Library {

	private ArrayList<Book> allBooks;
	private ArrayList<Book> books;
	private ArrayList<Book> loanedBooks;
	private ArrayList<Book> delayedBooks;
	private ArrayList<Book> topTen;
	private ArrayList<Customer> customers;
	private LocalDate date;

	// read txt files to the directories in constructor???
	public Library() {
		allBooks = new ArrayList<Book>();
		books = new ArrayList<Book>();
		loanedBooks = new ArrayList<Book>();
		delayedBooks = new ArrayList<Book>();
		topTen = new ArrayList<Book>();
		customers = new ArrayList<Customer>();
		date = LocalDate.now();
	}

	/* TODO ---------------------Basic------------------------------- */

	public ArrayList<Book> getAllBooks(){
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


	public Book findBookBy(bookKey key, String searchValue) throws InvalidKeyException {
		searchValue.toLowerCase();
        switch (key) {
            case TITLE: return findBookByString(searchValue, Book::getTitle); // No need for break since the return automatically breaks the switch.
            case GENRE: return findBookByString(searchValue, Book::getGenre);
            case PUBLISHER: return findBookByString(searchValue, Book::getPublisher);
            case ID: for (Book book : books) if (book.getId().toString().equals(searchValue)) return book;
            default: throw new InvalidKeyException("Invalid key in search function.");
        }
	}
	public ArrayList<Book> findBooksBy(bookKey key, String searchValue) throws InvalidKeyException {
		searchValue.toLowerCase();
		switch (key) {
	        case AUTHOR: return findBooksByString(searchValue);
	        case SHELF: return findBooksByString(searchValue);
	        default: throw new InvalidKeyException("Invalid key in search function.");
		}
	}
	private Book findBookByString(String s, Function<Book, ? extends Comparable> f) throws NullPointerException {
		s.toLowerCase();
		for (Book book : this.books) if (s.equals(((String)f.apply(book)).toLowerCase())) return book;
		return null;
	}
	private ArrayList<Book> findBooksByString(String s) throws NullPointerException {
		s.toLowerCase();
		ArrayList<Book> books = new ArrayList<Book>();
		for (Book book : this.books) {
			for (int i = 0; i < book.getAuthors().size(); i++) if (s.equals(book.getAuthors().get(i).toLowerCase())) books.add(book);
		}
		return books;
	}
	
	// ----- Search for customer ----- //
	public Customer findCustomerBy(customerKey key, String searchValue) throws InvalidKeyException {
		searchValue.toLowerCase();
		switch(key) {
			case NAME: return findCustomerByString(searchValue, Customer::getName);
			case ADRESS: return findCustomerByString(searchValue, Customer::getAdress);
			case NUMBER: return findCustomerByString(searchValue, Customer::getNumber);
			case ID: for (Customer customer : customers) if (customer.getID().toString().equals(searchValue)) return customer;
			case PERSONNUMMER: return findCustomerByString(searchValue, Customer::getPersonnummer);
			default: throw new InvalidKeyException("Invalid enum key in search customer function");
		}
	}
	private Customer findCustomerByString(String searchValue, Function<Customer, ? extends Comparable> f) throws NullPointerException {
		try {
		    // there wasnt an arraylist with objects until calling customerDirectory which
            // is the function that reads from txt and adds to the customers arraylist
		    customerDirectory();
            for (Customer customer : customers) {
                System.out.println("search value: " + searchValue);
                System.out.println(f.apply(customer));
                if (searchValue.equals(((String) f.apply(customer)).toLowerCase())) {
                    System.out.println("found customer");
                    return customer;
                }
            }
        }catch (Exception e){
		    e.getMessage();
        }
		throw new NullPointerException("Customer not found.");
	}

	/*---------------------SORTING------------------------------*/
	// Uses enums from search.
	// DON'T CHANGE FORMAT PLEASE.

	public void sortAllBooksBy(bookKey keyToSort) {
		try {
			for (Book book : this.allBooks) book.authors2UpperCase();
			Collections.sort(this.allBooks, Comparator.comparing(getBookFunction(keyToSort)));
		}
		catch (InvalidKeyException ike) {ike.printStackTrace();}
	}
	
	public void sortBooksBy(bookKey keyToSort) {
		try {
			for (Book book : this.books) book.authors2UpperCase();
			Collections.sort(this.books, Comparator.comparing(getBookFunction(keyToSort)));
		}
		catch (InvalidKeyException ike) {ike.printStackTrace();}
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
				case NAME: Collections.sort(customers, Comparator.comparing(Customer::getName)); break;
				case ADRESS: Collections.sort(customers, Comparator.comparing(Customer::getAdress)); break;
				case NUMBER: Collections.sort(customers, Comparator.comparing(Customer::getNumber)); break;
				case DEBT:
					// TODO: Needs testing. Not sure if this works for primitive types.
					Collections.sort(this.customers, Comparator.comparing(Customer::getDebt)); break;
				default: throw new InvalidKeyException("Invalid key in customer sort function");
			}
		} catch (InvalidKeyException ike) {ike.printStackTrace();}
	}
	
	// ----- Show 10 most popular books ----- //
	public String showTopBooks() {
		sortBooksBy(TIMESBORROWED);
		String s = "";
		for (int i = 0; i < 10; i++) s += (i) + "." + this.books.get(i).toString() + "\n";
		return s;
	}

	/* TODO -------------------REGISTRATION--------------------- */

	/* register books */
	public void addBook(Book book) {
		for(Book aBook: allBooks) {
			if(book == aBook) {
				break;
			}else {
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

	/*
	 * TODO register Customers
	 */
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public void removeCustomer(Customer customer) {
		customers.remove(customer);
	}

	/*TODO: ---------------------loan and return-----------------*/

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
		book.incrementTimesBorrowed();
		;
		loanedBooks.add(book);
		customer.addToCurrentLoan(book);
		customer.addToLoanHistory(book);
		books.remove(book);

	}

	public void borrowBookDay(String bookTitle, String personnummer, int loanPeriod) throws Exception {
		Customer customer = findCustomerBy(customerKey.PERSONNUMMER, personnummer);
		Book book = findBookBy(TITLE, bookTitle);
		
		// assumes default loanPeriod
		if (customer == null) {
			throw new Exception("Customer is not in System.");
		}
		if (book == null) {
			throw new Exception("Book is (currently) not in directory");
		} 
		
		if (loanPeriod <= 0) {
			throw new Exception("Loan Period needs to be larger than zero");
		}else {
			book.setReturnDate(this.date.plusDays(loanPeriod));
		}
		book.setStartDate(this.date);
		book.incrementTimesBorrowed();
		loanedBooks.add(book);
		customer.addToCurrentLoan(book);
		customer.addToLoanHistory(book);
		books.remove(book);

	}

	public void returnBook(String bookTitle, String personnummer) throws Exception{
		/*
		 * TODO: -check date //done -calculate debt //done -increment customer debt //
		 * done -restart book date //done -return book to library //done -remove book
		 * out of loanedBooks library -remove book from customer currentBooks //done
		 */

		Customer customer = findCustomerBy(customerKey.PERSONNUMMER, personnummer);
		if(customer == null) {
			throw new Exception ("Customer doesn't exist in directory");
		}
		
		Book book = customer.getFromCurrentLoan(bookTitle);
		if(book == null) {
			throw new Exception ("Book doesn't exist in directory");
		}
		
		int debt = this.checkDelay(book) * 2;
		customer.setDebt(debt);
		
		//statistics
		book.incrementTimesBorrowed();
		for(Book books: allBooks) {
			if(book.getTitle().equalsIgnoreCase(books.getTitle())) {
				books.incrementTimesBorrowed();
			}
		}
		
		
		/*
		 * TODO we need to "restart" the dates once the book is returned
		 * book.restartDates(); book.restartLoanPeriod(); book.notDelayed();
		 */
		LocalDate date = LocalDate.of(1998, 1, 1);
		book.setReturnDate(date);
		book.setStartDate(date);
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

	public ArrayList<Book> getTopTen() {
		ArrayList<Book> oneCopy = new ArrayList<Book>();
		this.sortAllBooksBy(TITLE);
		int numOfCopies = 0;
		
		{
			for(int i = 0; i < this.allBooks.size(); i++) {
				Book book = allBooks.get(i);
				oneCopy.add(book);
				
				for(int j = i; j < this.allBooks.size(); j++) {
					if(book.getTitle().equalsIgnoreCase(this.allBooks.get(j).getTitle()) ) {
						numOfCopies++;
					}
				}
				
				i+=numOfCopies;
				numOfCopies = 0;
			}
		}//end of block a: adds one copy of each book to the onCopy arrayList 
		
		{
			for(int i = 0; i < oneCopy.size(); i++) {
				if(i < 10) {
					 topTen.add(oneCopy.get(i));
					 
				}else {
					
				}
			}
		}//end of block b: adds 10 books to the topTen array, and the compares the remaining books to that 10 books already inside
		
		{
			/*I'm fucked*/
		}//end of block c
		
		return topTen;
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
		if (this.date.compareTo(book.getReturnDate()) > 0) {
			return (int) ChronoUnit.DAYS.between(book.getReturnDate(), this.date);
		} else {
			return 0;
		}
	}
	
	//Reading a text file into arraylist: (Books)// - change the exception handling for them(?)
	public void bookDirectory() throws FileNotFoundException {
		Scanner input = new Scanner(new File("res/bookDirectory.txt"));
	    input.useDelimiter("-|\n");
	    
		
		while(input.hasNext()) {
	       
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
	
	//Reading a txt file into arraylist (Customers)// 
	public void customerDirectory() throws Exception {
		Scanner input = new Scanner(new File("res/customer.txt"));
	    input.useDelimiter("/|\n");
		
		while(input.hasNext()) {
	       
	        String name = input.next();
			String address = input.next();
			String phoneNumber = input.next();
			String psn = input.next();
	        
			Customer customer = null;
			try {
				customer = new Customer(name, address, phoneNumber, psn);
			} catch (Exception e){
				e.printStackTrace();
			} finally {
				customers.add(customer);
			}
	    }
	}
	

	@Override
	public String toString(){
	    String res = "";
		for(Book book : books){
		    res += book.toString() + System.lineSeparator();
        }
        System.out.println(res);
        return res;
	}

}
