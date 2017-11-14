package library;


import java.util.Scanner;

import library.Library.bookKey;
import static library.Library.bookKey.*; // Needed to take enum keys as parameters. //Fabian.
import static library.Library.customerKey.*;


public class Test {

	//Make attributes private?
	Menu menu = new Menu();
	Scanner scanner = new Scanner(System.in);
	Library library;

	public Test() {
		// System.out.println("hello t");
		library = new Library();
		
		try {
			library.bookDirectory();
			library.customerDirectory();
		} catch (Exception e) {
			System.out.println("Was not able to load text files");
		}
	}

	public static void main(String[] args) {
		// System.out.println("hello m");
		Test test = new Test();
		test.run();

	}

	public void run() {
		// System.out.println("run method");
		int userInput;
		String quit; // use this?

		do {
			// Make option to return to main menu!!
			// System.out.println("running");
			menu.getMenu();

			userInput = scanner.nextInt();
			quit = scanner.nextLine();

			switch (userInput) {
			case 1:
				menu.getSearch();
				userInput = scanner.nextInt();

				if (userInput == 1) {
					this.searchBook();
				} else if (userInput == 2) {
					this.sortBooks();
				} else if (userInput == 3) {
					this.searchCustomer();
				} else if (userInput == 4) {
					this.showCustomers();
				} else if (userInput == 0) { // DOES THIS GO BACK TO MAIN MENU?
					break;
				}

				break;
			case 2:
				menu.getBookOptions();
				userInput = scanner.nextInt();

				if (userInput == 1) {
					try {
						this.borrowBook();
					} catch (Exception e) {e.getMessage();}
				} else if (userInput == 2) {
					try {
						this.returnBook();
					} catch (Exception e) {e.getMessage();}
				} else if (userInput == 0) {

				}
				break;
			case 3:
				menu.getAdministration();
				userInput = scanner.nextInt();

				if (userInput == 1) {
					this.addBook();
				} else if (userInput == 2) {
					this.removeBook();
				} else if (userInput == 3) {
					this.addCustomer();
				} else if (userInput == 4) {
					this.removeCustomer();
				} else if (userInput == 0) {

				}

				break;
			case 4:
				menu.getSimulator();
				userInput = scanner.nextInt();

				if (userInput == 1) {
					this.incrementDays();
				} else if (userInput == 2) {
					this.incrementMonths();
				} else if (userInput == 3) {
					this.incrementYears();
				} else if (userInput == 0) {

				}

				break;

			default:
				break;
			}

		} while (userInput != 0);
	}

	// Methods - everything is void now - change that if needed
	// -- Book handling methods --//
	public void addBook() {
		
		//ADD FUNCTION TO WRITE TO TXT FILE!

		System.out.println("Creating new book:");
		System.out.println("Enter title: ");
		String title = scanner.next();
		System.out.println("Enter author: ");
		String author = scanner.next();
		System.out.println("Enter publisher: ");
		String publisher = scanner.next();
		System.out.println("Enter genre: ");
		String genre = scanner.next();
		System.out.println("Enter shelf: ");
		String shelf = scanner.next();

		try {
			library.addBook(new Book(title, author, publisher, genre, shelf));
			System.out.println("Added " + title + " to library");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeBook() { //WILL THIS WORK OR NOT???
		//ADD FUNCTION TO REMOVE FROM TXT FILE
		//TODO: check if books arraylist contains ALL books in the library ever or only the ones currently in stock
		System.out.println("Enter title of book to remove: ");
		String title = scanner.nextLine();
		String authors;
		String publisher;
		String genre;
		String shelf;
		for(int i = 0; i < library.getBooks().size(); i++) {
			if(title.equals(library.getBooks().get(i).getTitle())) {
				authors = library.getBooks().get(i).getAuthor();
				publisher = library.getBooks().get(i).getPublisher();
				genre = library.getBooks().get(i).getGenre();
				shelf = library.getBooks().get(i).getShelf();
				try {
					library.removeBook(new Book(title, authors, publisher, genre, shelf));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				System.out.println("No book by such title available for removal");
			}
		}
		// Need library method that goes through arraylist, finds the first book with
		// this name
		// and removes it ?? a method that only takes title string as a parameter!
		
	}

	public void borrowBook() throws Exception{
		//ADD FUNCTION TO REMOVE FROM TXT FILE AND ADD TO OTHER TXT FILE
		//must create txt file for customers borrowed books, create that when customer borrows their
		//first book, or create one for every customer when they are first created
		System.out.println("Enter title of book to borrow:");
		String title = scanner.nextLine();
		
		System.out.println("Enter personal security number:");
		String psn = scanner.nextLine();

		// customer should borrow with psn instead??
		if(title.equals("") || psn.equals("")) {
			throw new Exception ("Empty title or social security number");
		}else {
			library.borrowBook(title, psn);
		}
	}

	public void returnBook() throws Exception{
		//ADD FUNCTION TO REMOVE FROM TXT FILE AND ADD TO OTHER TXT FILE
		System.out.println("Enter title of book to return:");
		String title = scanner.nextLine();
		System.out.println("Enter personal security number:");
		String psn = scanner.nextLine();

		if(title.equals("") || psn.equals("")) {
			throw new Exception ("Empty title or social security number");
		}else {
			library.returnBook(title, psn);
		}
	}

	public void searchBook() {
		System.out.println("Choose what to sort by");
		System.out.println("1. Search book using title");
		System.out.println("2. Search book using author");
		System.out.println("3. Search book using publisher");
		System.out.println("4. Search book using genre");
		System.out.println("5. Search book using shelf");
		
		int userInput = scanner.nextInt();
		
		try {
			if(userInput == 1) { //did i use the right enums or not? cuz there were 2 options
				System.out.println("Please enter the title");
				String title = scanner.nextLine();
				
				System.out.println(library.findBookBy(TITLE, title));
			}else if (userInput == 2) {
				System.out.println("Please enter the author");
				String author = scanner.nextLine();
				
				System.out.println(library.findBookBy(AUTHOR, author));
			}else if (userInput == 3) {
				System.out.println("Please enter the publisher");
				String publisher = scanner.nextLine();
				
				System.out.println(library.findBookBy(PUBLISHER, publisher));
			}else if (userInput == 4) {
				System.out.println("Please enter the genre");
				String genre = scanner.nextLine();
				
				System.out.println(library.findBookBy(GENRE, genre));
			}else if (userInput == 5) {
				System.out.println("Please enter the shelf");
				String shelf = scanner.nextLine();
				
				System.out.println(library.findBookBy(SHELF, shelf));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}
	
	//Seems ok//
	public void sortBooks() {

		System.out.println("Show all books: Choose what to sort by");
		System.out.println("1. Sort after title");
		System.out.println("2. Sort after author");
		System.out.println("3. Sort after publisher");
		System.out.println("4. Sort after genre");
		System.out.println("5. Sort after shelf");

		int userInput = scanner.nextInt();
		if (userInput == 1) {
			library.sortBooksBy(bookKey.TITLE);
			for(int i = 0; i < library.getBooks().size(); i++) {
				System.out.println(library.getBooks().get(i));
			}
		} else if (userInput == 2) {
			library.sortBooksBy(bookKey.AUTHOR);
			for(int i = 0; i < library.getBooks().size(); i++) {
				System.out.println(library.getBooks().get(i));
			}
		} else if (userInput == 3) {
			library.sortBooksBy(bookKey.PUBLISHER);
			for(int i = 0; i < library.getBooks().size(); i++) {
				System.out.println(library.getBooks().get(i));
			}
		} else if (userInput == 4) {
			library.sortBooksBy(bookKey.GENRE);
			for(int i = 0; i < library.getBooks().size(); i++) {
				System.out.println(library.getBooks().get(i));
			}
		} else if (userInput == 5) {
			library.sortBooksBy(bookKey.SHELF);
			for(int i = 0; i < library.getBooks().size(); i++) {
				System.out.println(library.getBooks().get(i));
			}
		}

	}

	// -- Customer handling methods --//
	public void addCustomer() {
		//ADD FUNCTION TO ADD TO TXT FILE
		System.out.println("Enter customer name: ");
		String name = scanner.nextLine();
		System.out.println("Enter customer adress: ");
		String address = scanner.nextLine();
		System.out.println("Enter customer phone number: ");
		String phoneNumber = scanner.nextLine();
		System.out.println("Enter customer personal security number: ");
		String psn = scanner.nextLine();
		
		try {
			if(!name.equals("") && !address.equals("") && !psn.equals("")) {
				library.addCustomer(new Customer(name, address,psn, phoneNumber));
				System.out.println("Added " + name + " to customer database");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}

	public void removeCustomer() { // is there an easier way to do this so we dont have to rember the whole
									// number??
		//ADD FUNCTION TO REMOVE FROM TXT FILE
		System.out.println("Enter personal security number of customer to remove: ");
		String psn = scanner.next();
		String name;
		String address;
		String phoneNumber;
		//WILL THIS WORK??
		for(int i = 0; i < library.getCustomers().size(); i++) {
			if(psn.equals(library.getCustomers().get(i).getPersonnummer())) {
				name = library.getCustomers().get(i).getName();
				address = library.getCustomers().get(i).getAdress();
				phoneNumber = library.getCustomers().get(i).getNumber();
				try {
					library.removeCustomer(new Customer(name, address, psn, phoneNumber));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// have a method to remove customers by psn??
		// pretty hard to remove customer by entering all parameters correctly..
		
	}

	public void searchCustomer() {
		System.out.println("Choose what to find customer by: ");
		System.out.println("1. Find customer by name");
		System.out.println("2. Find customer by address");
		System.out.println("3. Find customer by phone number");
		System.out.println("4. Find customer by personal security number");
		int userInput = scanner.nextInt();
		
		try {
			if(userInput == 1) {
				System.out.println("Enter the customer name: ");
				String name = scanner.next();
				System.out.println(library.findCustomerBy(NAME, name));
			}else if (userInput == 2) {
				System.out.println("Enter the customer address: ");
				String adress = scanner.next();
				System.out.println(library.findCustomerBy(ADRESS, adress));
			}else if (userInput == 3) {
				System.out.println("Enter the customer phone number: ");
				String phone = scanner.next();
				System.out.println(library.findCustomerBy(NUMBER, phone));
			}else if (userInput == 4) {
				System.out.println("Enter the customer personal security number: ");
				String psn = scanner.next();
				System.out.println(library.findCustomerBy(PERSONNUMMER, psn));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}

	public void showCustomers() {
		
	}

	public void extendLoan() {
		// wait for them to implement - tell them to implement?
	}

	// -- Time handling methods - do we need?? --//
	public void incrementDays() {
		System.out.println("Enter how many days to increment: ");
		int day = scanner.nextInt();
		library.addDays(day);
	}

	public void incrementMonths() {
		System.out.println("Enter how many months to increment: ");
		int month = scanner.nextInt();
		library.addDays(month);
	}

	public void incrementYears() {
		System.out.println("Enter how many years to increment: ");
		int year = scanner.nextInt();
		library.addDays(year);
	}

	// VG implementations //
	//MAKE TOSTRING METHODS FOR BOOKS!!
	public void showAllLoanedBooks() {
		library.getLoanedBooks();
		for(int i = 0; i < library.getLoanedBooks().size(); i++) {
			System.out.println(library.getLoanedBooks().get(i).toString());
		}
	}
	
	//MAKE TOSTRING METHODS FOR BOOKS!!
	public void showAllDelayedBooks() {
		library.getDelayedBooks();
		for(int i = 0; i < library.getDelayedBooks().size(); i++) {
			System.out.println(library.getDelayedBooks().get(i).toString());
		}
	}
	public void showMostPopularBook() {
		//this is the like method below but only for 1 single book.. need?
		System.out.println("Most popular book right now is:");
		System.out.println(library.getMostPopularBook()); //does this display anything??
		
	}
	public void showMostLentOutBooks() {
		//wait for them to implement
		//need a counter that goes up every time a book is lent out
		//need another arraylist that fills up with the maybe top 5 books that have highest counter
		//search through the "all books" arraylist and add to that arraylist and display here
	}
	public void showCustomerLoanHistory() { //WILL THIS WORK??
		System.out.println("Enter the personal secuiry number of the customer");
		String customerPsn = scanner.nextLine();
		String customerName = "";
		String customerAddress = "";
		String customerPhone = ""; 
		Customer customer;
		
		for(int i = 0; i <library.getCustomers().size(); i++) {
			if(customerPsn.equals(library.getCustomers().get(i).getPersonnummer())) {
				customerName = library.getCustomers().get(i).getName();
				customerAddress = library.getCustomers().get(i).getAdress();
				customerPhone = library.getCustomers().get(i).getNumber();
				System.out.println("Here is " + customerName + "'s loan history: ");
				try {
					library.getCustomerLoanHistory(new Customer(customerName, customerAddress, customerPsn, customerPhone));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				System.out.println("No customer registered with that psn");
			}
		}
		//here we also should be able to get loan history from only entering the PSN!!
	}
}

// TODO: things i wrote in my comments above, and add options for VG things !!,
// ADD error handling!!
//Add all the new options in the menu in this class and menu class (all methods must be used somewhere)
//make user able to exit by pressing "q" or "quit"
//make sure everything is displayed - that they are in a sysout!
//write sysouts that confirm users actions tex if adding a book was successful or not
//see if i should use nextLine or if next is enough
//etc! - look through comments and go through the code, make sure all methods in library are used


/*      FOR G
 * For the grade “Godkänd” (Pass), the following is expected:
 * 
 * There should be a directory of books. It should be possible to add new books
 * to the directory (title, author, genre, publisher, shelf) Sorting and
 * searching of books, for example by Author. To lend out a book to a customer
 * and specify the date the book should be returned. Register that the customer
 * has returned the book. Customers should be in a customer register.
 */
/*       FOR VG
 * For the grade “Väl Godkänd” (Pass with distinction) the criteria for pass
 * must be met as well as: Should be able to show all borrowed books Should be
 * able to view all delayed books Should be able to show if the borrower
 * returned the book is delayed and what the total delay fee will be. Statistics
 * on what books have been lent out the most To be able to view the borrower's
 * loan history
 */

