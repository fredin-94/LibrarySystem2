package library;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import library.Library.bookKey;
import static library.Library.bookKey.*; // Needed to take enum keys as parameters. //Fabian.
import static library.Library.customerKey.*;

public class Test {

	private Menu menu = new Menu();
	private Scanner scanner = new Scanner(System.in);
	private Library library;

	public Test() {
		library = new Library();
	}
	
	public void run() {
		int userInput;
		do {
			menu.getWelcomeScreen();
			menu.getMenu();
			userInput = scanner.nextInt();
			scanner.nextLine();

			switch (userInput) {
				case 1:
					menu.getSearch();
					userInput = scanner.nextInt();
					handleSearchMenu(userInput);
					break;
				case 2:
					menu.getBookOptions();
					userInput = scanner.nextInt();
					handleBookMenu(userInput);
					break;
				case 3:
					menu.getAdministration();
					userInput = scanner.nextInt();
					handleAdmin(userInput);
					break;
				case 4:
					menu.getExtra();
					userInput = scanner.nextInt();
					handleExtra(userInput);
					break;
				case 5:
					menu.getSimulator();
					userInput = scanner.nextInt();
					handleTimeSimMenu(userInput);
					break;
				case 0:
					System.exit(0);
				default:
					System.out.println("Not a valid option");
					break;
			}
		} while (userInput != 0);
	}

	public void handleSearchMenu(int option) {
		switch (option) {
			case 1:
				searchBook();
				break;
			case 2:
				searchCustomer();
				break;
			case 3:
				System.out.println("Sort books after: ");
				System.out.println("1. Title");
				System.out.println("2. Author");
				System.out.println("3. Publisher");
				System.out.println("4. Genre");
				System.out.println("5. Shelf");
				option = scanner.nextInt(); //need another one and not reuse "option"?
				if(option == 1) {
					library.sortBooksBy(TITLE);
				}else if (option == 2) {
					library.sortBooksBy(AUTHOR);
				}else if (option == 3) {
					library.sortBooksBy(PUBLISHER);
				}else if (option == 4) {
					library.sortBooksBy(GENRE);
				}else if (option == 5) {
					library.sortBooksBy(SHELF);
				}else {
					System.out.println("Not a valid option, displaying randomly");//??
				}
				showAvailableBooks();
				break;
			case 4:
				showCustomers();
				break;
			case 5:
				showCustomerLoanHistory();
				break;
			case 0:
				run();
				break;
			default:
				System.out.println("Not a valid option.");
				break;
		}
	}

	public void handleBookMenu(int option) {
		switch (option) {
			case 1:
				try {
					borrowBook();
				} catch (Exception e) {
					e.getMessage();
				}
				break;
			case 2:
				try {
					returnBook();
				} catch (Exception e) {
					e.getMessage();
				}
				break;
			case 0:
				run();
				break;
			default:
				System.out.println("Not a valid option");
				break;
		}
	}

	public void handleAdmin(int option) {
		scanner.nextLine();
		switch (option) {
			case 1:
				addBook();
				break;
			case 2:
				removeBook();
				break;
			case 3:
				addCustomer();
				break;
			case 4:
				removeCustomer();
				break;
			case 0:
				run();
				break;
			default:
				System.out.println("Not a valid option");
				break;
		}
	}

	public void handleExtra(int option) {
		switch (option) {
		case 1:
			for (int i = 0; i < library.getLoanedBooks().size(); i++) {
				System.out.println(library.getLoanedBooks().get(i).toString());
			}
			break;
		case 2:
			for (int i = 0; i < library.getDelayedBooks().size(); i++) {
				System.out.println(library.getDelayedBooks().get(i).toString());
			}
			break;
		case 3:
			showCustomerLoanHistory();
			break;
		case 4:
			showCustomerLCurrentLoans();
			break;
		case 5:
			System.out.println("Top 10 books: ");
			//library.showTopBooks();
			break;
		case 0:
			run();
			break;

		default:
			System.out.println("Not a valid option");
			break;
		}
	}

	public void handleTimeSimMenu(int option) {
		switch (option) {
			case 1:
				incrementDays();
				break;
			case 2:
				incrementWeeks();
				break;
			case 3:
				incrementMonths();
				break;
			case 4:
				incrementYears();
				break;
			case 0:
				menu.getMenu();
				break;
			default:
				System.out.println("Not a valid option");
				break;
		}
	}

	public ArrayList<Customer> retrieveCustomerDirectory() {
		return library.getCustomers();
	}

	// Methods - everything is void now - change that if needed
	// -- Book handling methods --//
	public void addBook() {

		/* TODO: add to all books directory */
		// ADD FUNCTION TO WRITE TO TXT FILE! --DONE
		System.out.println("Creating new book:");
		System.out.println("Enter title: ");
		String title = scanner.nextLine();
		System.out.println("Enter author: ");
		String author = scanner.nextLine();
		System.out.println("Enter publisher: ");
		String publisher = scanner.nextLine();
		System.out.println("Enter genre: ");
		String genre = scanner.nextLine();
		System.out.println("Enter shelf: ");
		String shelf = scanner.nextLine();

		try {
			Book b = new Book(title, author, publisher, genre, shelf);
			library.addBook(b);
			writeBookToFile("res/AllBooks.txt", b);
			writeBookToFile("res/bookDirectory.txt", b);
			System.out.println(b.getTitle() + " was successfully added to the library.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeBookToFile(String path, Book book) {
		// can probably change this to if(!book.equals(null))
		if (!book.getTitle().equals("") && !book.getAuthor().equals("") && !book.getPublisher().equals("")
				&& !book.getGenre().equals("") && !book.getShelf().equals("")) {
			try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)))) {
				out.println(book.getTitle() + "-" + book.getAuthor() + "-" + book.getPublisher() + "-"
						+ book.getGenre() + "-" + book.getShelf() + "-" + book.getTimesBorrowed());
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			//System.out.println("In Write book to file: Added " + book.getTitle() + " to library");
		} else {
			System.out.println("In write book to file: No parameters allowed to be empty");
		}
	}

	public void removeBook() {
		System.out.println("Enter title of book to remove: ");
		String title = scanner.nextLine().trim();
		Book book = retrieveBook(library.getAllBooks(), title);
		// ---- Most of this will prob be deleted if Oliver implements the functionality in Library.java.-----
		if(!book.equals(null)) {
		    if(isInList(library.getBooks(), book)) {
                removeLineFromFile("res/bookDirectory.txt", parseBookToString(book));
				library.removeBook(book);
            }else if(isInList(library.getLoanedBooks(), book)){
                removeLineFromFile("res/LoanedBooks.txt", parseBookToString(book));
                //library.removeBookFromLoanedBooks
            } else if(isInList(library.getDelayedBooks(), book)){
                removeLineFromFile("res/delayedBooks.txt", parseBookToString(book));
                //library.removeBookFromDelayed
            }

            removeLineFromFile("res/AllBooks.txt", parseBookToString(book));
		} else {
			System.out.println("There's no book with that title");
		}
	}

	public Book retrieveBook(ArrayList<Book> listOfBooks, String title) {
		for (Book book : listOfBooks) {
			if (book.getTitle().equalsIgnoreCase(title)) {
				return book;
			}
		}
		return null;
	}

	public boolean isInList(ArrayList<Book> listOfBooks, Book book){
        for(Book b : listOfBooks){
            if(b.equals(book)){
                return true;
            }
        }
        return false;
    }

	public Customer retrieveCustomer(String psn) {
		for (Customer customer : library.getCustomers()) {
			if (customer.getPersonnummer().equals(psn)) {
				return customer;
			}
		}
		return null;
	}

	// changes objects into a format appropriate for the txt files
	public String parseBookToString(Book book) {
		return book.getTitle() + "-" + book.getAuthor() + "-" + book.getPublisher() + "-" + book.getGenre() + "-"
				+ book.getShelf() + "-" + book.getTimesBorrowed();
	}

	public String parseCustomerToString(Customer customer) {
		return customer.getName() + "/" + customer.getAdress() + "/" + customer.getPersonnummer() + "/"
				+ customer.getNumber();
	}

	public void removeLineFromFile(String path, String lineToRemove) {
		try {
			File dirFile = new File(path);
			File tmpFile = new File(dirFile.getAbsolutePath() + ".tmp");
			BufferedReader br = new BufferedReader(new FileReader(path));
			PrintWriter pw = new PrintWriter(new FileWriter(tmpFile));
			String line;
			while ((line = br.readLine()) != null) {
				// I think it's saving a new line character at the end from when the user
				// presses enter, therefore
				// a .trim() is required.
				if (!line.equals(lineToRemove.trim())) {
					// GONNA DELETE ALL BOOKS WITH THAT TITLE, FIX PLS
					pw.println(line);
					pw.flush();
				}
			}
			System.gc();
			pw.close();
			br.close();
			boolean success = dirFile.delete();
			boolean renameSuccess = tmpFile.renameTo(dirFile);

			if (success) {
				System.out.println("Old file deleted");
			}
			if (renameSuccess) {
				System.out.println("file renamed");
			}
		} catch (Exception e) {
			//e.getMessage();
			System.out.println("In remove line from file: Not able to complete method");
		}
	}

	public void borrowBook() throws Exception {
		showAvailableBooks();
		scanner.nextLine();

		System.out.println("Enter title of book to borrow:");
		String title = scanner.nextLine().trim();
		Book book = retrieveBook(library.getAllBooks(), title);
		System.out.println("Enter personal security number:");
		String psn = scanner.nextLine().trim();
		Customer customer = retrieveCustomer(psn);
		if(customer != null && book != null) {
			
		
		/* 
	 	ArrayList <Book> options = new ArrayList <Book>();
		ArrayList <Book> list = new ArrayList <Book>();
		for(Book somebook: library.getAllBooks()) {
			if(somebook.getTitle().equalsIgnoreCase(title)) {
				options.add(somebook);
			}
		}*/
		
		if (title.equals("") || psn.equals("")) {
			throw new Exception("Empty title or social security number");
		} else {
			if (!book.equals(null)) {
				System.out.println("about to borrow book yay");
				removeLineFromFile("res/bookDirectory.txt", parseBookToString(book));
				writeBookToFile("res/LoanedBooks.txt", book);
				writeBookToFile("res/" + psn + "CurrentLoans.txt", book);
				writeBookToFile("res/" + psn + "LoanHistory.txt", book);
				System.out.println("--In test, borrow book: Success! Borrowed " + title + "--");
				library.borrowBook(title, psn);
				ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
				ses.scheduleAtFixedRate(new Runnable() {
					@Override
					public void run() {
						library.isDelayed(retrieveBook(library.getLoanedBooks(), title));
					}
				}, 0, 1, TimeUnit.HOURS);
			}
		}
		}else {
			throw new Exception("Customer Not Registered Or Book Not Found");
		}
	}

	public void returnBook() throws Exception {
		scanner.nextLine();
		System.out.println("Enter personal security number:");
		String psn = scanner.nextLine();
		Customer customer1 = retrieveCustomer(psn);
		if( customer1 != null) {
		
		for (Customer customer : retrieveCustomerDirectory()) {//going into the customer arraylist
			if (customer.getPersonnummer().equals(psn)) {
				System.out.println("Your current books are:");
				for(int i = 0; i<customer.getCurrentLoans().size(); i++) {
					System.out.println(customer.getCurrentLoans().get(i).toString());
				}	
			}
		}
		System.out.println("Enter title of book to return:");
		String title = scanner.nextLine();
		
		if (title.equals("") || psn.equals("")) {
			throw new Exception("Empty title or social security number");
		} else {
			Book book = null;
			for (Customer customer : retrieveCustomerDirectory()) {//going into the customer arraylist
				if (customer.getPersonnummer().equals(psn)) {
					book = customer.getFromCurrentLoan(title);
				}
			}
			// returns a book into library's available books directory
			writeBookToFile("res/bookDirectory.txt", book);
			removeLineFromFile("res/"+psn+"CurrentLoans.txt", parseBookToString(book));		
			removeLineFromFile("res/LoanedBooks.txt", parseBookToString(book));	
			library.returnBook(title, psn);
			//System.out.println("In return book: removed book from loaned books arraylist, added to books arraylist, removed from customer current loans arraylist");
			
			System.out.println("Book returned successfully");
		}
		}else {
			throw new Exception("Customer Not Registered");
		}
	}

	public void searchBook() {
		System.out.println("Search for book: ");
		scanner.nextLine();
		String searchText = scanner.nextLine();
		try {
			for (Book book : library.searchForBook(searchText)) {
				System.out.println(book.toString());
			}
		} catch (NullPointerException npe) {
			System.out.println("No matches with '" + searchText + "'.");
		}
	}

	// Seems ok//
	public void sortBooks() { // doesnt work if the book starts with an lowercase letter,
		// also would be good to skip the "The" at the beginning of a book

		System.out.println("Show all books: Choose what to sort by");
		System.out.println("1. Sort after title");
		System.out.println("2. Sort after author");
		System.out.println("3. Sort after publisher");
		System.out.println("4. Sort after genre");
		System.out.println("5. Sort after shelf");

		int userInput = scanner.nextInt();
		if (userInput == 1) {
			library.sortBooksBy(bookKey.TITLE);
			for (int i = 0; i < library.getBooks().size(); i++) {
				System.out.println(library.getBooks().get(i));
			}
		} else if (userInput == 2) {
			library.sortBooksBy(bookKey.AUTHOR);
			for (int i = 0; i < library.getBooks().size(); i++) {
				System.out.println(library.getBooks().get(i));
			}
		} else if (userInput == 3) {
			library.sortBooksBy(bookKey.PUBLISHER);
			for (int i = 0; i < library.getBooks().size(); i++) {
				System.out.println(library.getBooks().get(i));
			}
		} else if (userInput == 4) {
			library.sortBooksBy(bookKey.GENRE);
			for (int i = 0; i < library.getBooks().size(); i++) {
				System.out.println(library.getBooks().get(i));
			}
		} else if (userInput == 5) {
			library.sortBooksBy(bookKey.SHELF);
			for (int i = 0; i < library.getBooks().size(); i++) {
				System.out.println(library.getBooks().get(i));
			}
		}

	}

	public void showAvailableBooks() {
		System.out.println(library.toString());
	}

	public void writeCustomerToFile(String name, String address, String phoneNumber, String psn) {
		// should allow a customer to be added to txt without entering phonenumber
		if (!name.equals("") && !address.equals("") && !phoneNumber.equals("") && !psn.equals("")) {

			try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("res/customer.txt", true)))) {
				out.println(name + "/" + address + "/" + phoneNumber + "/" + psn);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			System.out.println("Added " + name + " to customer database");
		} else {
			System.out.println("No parameters allowed to be empty");
		}
	}

	// -- Customer handling methods --//
	public void addCustomer() {
		// ADD FUNCTION TO ADD TO TXT FILE --done
		System.out.println("Enter customer name: ");
		String name = scanner.nextLine();
		System.out.println("Enter customer adress: ");
		String address = scanner.nextLine();
		System.out.println("Enter customer psn: ");
		String psn = scanner.nextLine();
		System.out.println("Enter customer phone number: ");
		String phoneNumber = scanner.nextLine().trim();

		try {
			if (!name.equals("") && !address.equals("") && !psn.equals("")) {
				if(phoneNumber.equals("")) {
					createFile(psn + "LoanHistory");
					createFile(psn+"CurrentLoans");
					library.addCustomer(new Customer(name, address, psn));
				} else {
					createFile( psn + "LoanHistory");
					createFile(psn+"CurrentLoans");
					library.addCustomer(new Customer(name, address, psn, phoneNumber));
				}
				System.out.println("Added " + name + " to customer database");
				writeCustomerToFile(name, address, psn, phoneNumber);
			}

		} catch (Exception e) {
			System.out.println("Please make sure name, address and personal security numbers are all filled out.");
			addCustomer();
		}
	}

	public void createFile(String fileName){
		System.out.println("in createFIle");
		try {
			File file = new File("res/"+fileName+".txt");
			if (file.createNewFile()){
				System.out.println("Text file is created!");
			}else{
				System.out.println("Text file for " + fileName + " already exists.");
			}
		}catch(Exception e){
			e.getMessage();
		}
	}

	public void removeCustomer() {
		System.out.println("Enter personal security number of customer to remove: ");
		String psn = scanner.nextLine();
		Customer customer = retrieveCustomer(psn);
		if (customer != null) {
			removeLineFromFile("res/customer.txt", parseCustomerToString(customer));
			deleteFile("res/"+psn+"CurrentLoans.txt");
			deleteFile("res/"+psn+"LoanHistory.txt"); // should we keep that customer's loan history in library archives??
			library.removeCustomer(customer);
		} else {
			System.out.println("There's no customer with that personnummer \nPlease enter a valid one.");
			removeCustomer();
		}
	}

	public void deleteFile(String path){
		File f = new File(path);
		if(f.delete()){
			System.out.println("Customer files deleted successfully");
		} else {
			System.out.println("Unable to delete customer files");
		}
	}

	public void searchCustomer() {
		System.out.println("Search for customer: ");
		scanner.nextLine();
		String searchText = scanner.nextLine();
		try {
			for (Customer customer : library.searchForCustomer(searchText)) {
				System.out.println(customer.toString());
			}
		} catch (NullPointerException npe) {
			System.out.println("No matches with '" + searchText + "'.");
		}
	}

	public void showCustomers() {
		String res = "";
		try {
			for (Customer c : library.getCustomers()) {
				if (c != null) {
					res += c.toString();
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
		if (res.equals("")) res = "No customers are registered.";
		System.out.println(res);
	}

	public void extendLoan() {
		// wait for them to implement - tell them to implement?
		// Loan extension should be in Library class, we should only call it from here.
	}

	// -- Time handling methods - do we need?? --//
	public void incrementDays() {
		System.out.println("Enter how many days to increment: ");
		int day = scanner.nextInt();
		library.addDays(day);
	}
	public void incrementWeeks() {
		System.out.println("Enter how many weeks to increment: ");
		int week = scanner.nextInt();
		library.addWeeks(week);
	}

	public void incrementMonths() {
		System.out.println("Enter how many months to increment: ");
		int month = scanner.nextInt();
		library.addMonths(month);
	}

	public void incrementYears() {
		System.out.println("Enter how many years to increment: ");
		int year = scanner.nextInt();
		library.addyears(year);
	}

	// VG implementations //
	// MAKE TOSTRING METHODS FOR BOOKS!! --DONE
	public void showAllLoanedBooks() {
		library.getLoanedBooks();
		for (int i = 0; i < library.getLoanedBooks().size(); i++) {
			System.out.println(library.getLoanedBooks().get(i).toString());
		}
	}

	// MAKE TOSTRING METHODS FOR BOOKS!! --DONE, that is not the issue with these
	// methods though.
	// most methods have the same issue, namely that bookDirectory() isnt called
	// from the customer
	// so the books arraylist doesnt fill with the books from txt
	public void showAllDelayedBooks() {
		library.getDelayedBooks();
		for (int i = 0; i < library.getDelayedBooks().size(); i++) {
			System.out.println(library.getDelayedBooks().get(i).toString());
		}
	}

	public void showMostPopularBook() {
		// this is the like method below but only for 1 single book.. need?
		System.out.println("Most popular book right now is:");
		String res = "";
		for(Book b : library.getTopTen()){
		    res += b.toString();
        }
		System.out.println(res);
	}

	public void showCustomerLoanHistory() { // WILL THIS WORK?? ----- IT NOW WORKS
		System.out.println("Enter the personal security number of the customer:");
		String skipString = scanner.nextLine();
		String customerPsn = scanner.nextLine();
		System.out.println(customerPsn);
		Customer customer = retrieveCustomer(customerPsn.trim());

		try {
			for (Customer c : retrieveCustomerDirectory()) {
				if (c.equals(customer)) {
					System.out.println("Here is " + c.getName() + "'s loan history: ");
					library.getCustomerLoanHistory(c);
					
					for (int i = 0; i < customer.getLoanHistory().size(); i++) {
						System.out.println(customer.getLoanHistory().get(i).toString());
					}
				}
			}
		} catch (Exception e) {
			System.out.println("No customer registered with that psn");
			showCustomerLoanHistory();
		}
	}
	public void showCustomerLCurrentLoans() { // WILL THIS WORK?? ----- IT NOW WORKS
		System.out.println("Enter the personal security number of the customer:");
		String skipString = scanner.nextLine();
		String customerPsn = scanner.nextLine();
		Customer customer = retrieveCustomer(customerPsn.trim());

		try {
			for (Customer c : retrieveCustomerDirectory()) {
				if (c.equals(customer)) {
					System.out.println("Here is " + c.getName() + "'s current loans: ");
					customer.getCurrentLoans();
					
					for (int i = 0; i < customer.getCurrentLoans().size(); i++) {
						System.out.println(customer.getCurrentLoans().get(i).toString());
					}
				}
			}
		} catch (Exception e) {
			System.out.println("No customer registered with that psn");
			showCustomerLoanHistory();
		}
	}

	public static void main(String[] args) {
		// System.out.println("hello m");
		Test test = new Test();
		test.run();

	}
}

// TODO: things i wrote in my comments above, and add options for VG things !!,
// ADD error handling!!
// Add all the new options in the menu in this class and menu class (all methods
// must be used somewhere)
// make user able to exit by pressing "q" or "quit" --CAN DO THAT, need to
// change switch conditional to a char
// make sure everything is displayed - that they are in a sysout!
// write sysouts that confirm users actions tex if adding a book was successful
// or not
// see if i should use nextLine or if next is enough
// etc! - look through comments and go through the code, make sure all methods
// in library are used

/*
 * For the grade (Pass), the following is expected:
 *
 * There should be a directory of books. --DONE currently kept in list in
 * Library and a txt in res/.txt It should be possible to add new books to the
 * directory (title, author, genre, publisher, shelf)--DONE for both list and
 * txt Sorting and searching of books, for example by Author. --DONE To lend out
 * a book to a customer and specify the date the book should be returned. --NOT
 * SURE; NEEDS FURTHER TESTING Register that the customer has returned the book.
 * --RETURN OF BOOK REGISTERED, NOT SURE HOW IT INTERACTIS WITH CUSTOMER
 * Customers should be in a customer register. --DONE
 */
/*
 * FOR VG For the grade (Pass with distinction) the criteria for pass must be
 * met as well as: Should be able to show all borrowed books Should be able to
 * view all delayed books Should be able to show if the borrower returned the
 * book is delayed and what the total delay fee will be. Statistics on what
 * books have been lent out the most To be able to view the borrower's loan
 * history --DONE
 */
