package library;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import library.Library.bookKey;
import static library.Library.bookKey.*; // Needed to take enum keys as parameters. //Fabian.
import static library.Library.customerKey.*;


public class Test {

	//Make attributes private? --YES
    private Menu menu = new Menu();
	private Scanner scanner = new Scanner(System.in);
	private Library library;

	public Test() {
        library = new Library();

		//faulty!: for some reason it prints catch text too- is it with the
		//customer perhaps??
		/*try {
			System.out.println("Text files loaded successfully");
			library.bookDirectory();
			library.customerDirectory();
			
		} catch (Exception e) {
			System.out.println("Was not able to load text files");
		}*/
	}


	public void run() {
		int userInput;
		String quit; // use this?

		do {
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
                    //menu.getBookOptions();
                		//resolve this
                    userInput = scanner.nextInt();
                    handleBookMenu(userInput);
                    break;
                case 3:
                    menu.getAdministration();
                    userInput = scanner.nextInt();
                    handleAdmin(userInput);
                    break;
                case 4:
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

	public void handleSearchMenu(int option){
	    switch(option){
            case 1:
                this.searchBook();
                break;
            case 2:
                this.sortBooks();
                break;
            case 3:
                this.searchCustomer();
                break;
            case 4:
                this.showCustomers();
                break;
            case 5:
                showAvailableBooks();
                break;
            case 6:
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

    public void handleBookMenu(int option){
	    switch (option){
            case 1:
                try {
                    borrowBook();
                } catch(Exception e){e.getMessage();}
                break;
            case 2:
                try {
                    returnBook();
                } catch (Exception e) {e.getMessage();}
                break;
            case 0:
                run();
                break;
            default:
                System.out.println("Not a valid option");
                break;
        }
    }

    public void handleAdmin(int option){
        switch (option){
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

    public void handleTimeSimMenu(int option){
        switch (option){
            case 1:
                incrementDays();
                break;
            case 2:
                incrementMonths();
                break;
            case 3:
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

    public ArrayList<Book> retrieveBookDirectory(){
        try{
            library.bookDirectory();
        } catch(Exception e){
            e.getMessage();
        }
        return library.getBooks();
    }

    public ArrayList<Customer> retrieveCustomerDirectory(){
        try{
            library.customerDirectory();
        } catch(Exception e){
            e.getMessage();
        }
        return library.getCustomers();
    }

	// Methods - everything is void now - change that if needed
	// -- Book handling methods --//
	public void addBook() {

		//ADD FUNCTION TO WRITE TO TXT FILE! --DONE
		String hopString = scanner.nextLine();
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
			library.addBook(new Book(title, author, publisher, genre, shelf));
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeBookToFile(title, author, publisher, genre, shelf);
	}

	public void writeBookToFile(String title, String author, String publisher, String genre, String shelf){
        //adding user input to text file://
        //Make sure no necessary fields are empty//
        if (!title.equals("") && !author.equals("")
                && !publisher.equals("") && !genre.equals("")
                && !shelf.equals("")) {

            try (PrintWriter out = new PrintWriter(
                    new BufferedWriter(new FileWriter("res/bookDirectory.txt", true)))) {
                out.println(title + "-" + author + "-" + publisher
                        + "-" + genre + "-" + shelf);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            System.out.println("Added " + title + " to library");
        }else {
            System.out.println("No parameters allowed to be empty");
        }
    }

	public void removeBook() { //WILL THIS WORK OR NOT???
		//ADD FUNCTION TO REMOVE FROM TXT FILE
		//TODO: check if books arraylist contains ALL books in the library ever or only the ones currently in stock
		System.out.println("Enter title of book to remove: ");
		scanner.nextLine();
		String title = scanner.nextLine();

        Book book = retrieveBook(title);
        removeLineFromFile("res/bookDirectory.txt", parseBookToString(book));
        library.removeBook(book);
	}

	public Book retrieveBook(String title){
	    for(Book book : retrieveBookDirectory()){
	        if(book.getTitle().equalsIgnoreCase(title)){
	            return book;
            }
        }
        return null;
    }

    public Customer retrieveCustomer(String psn){
	    for(Customer customer : retrieveCustomerDirectory()){
	        if(customer.getPersonnummer().equals(psn)){
	            return customer;
            }
        }
        return null;
    }

    // changes objects into a format appropriate for the txt files
	public String parseBookToString(Book book){
        return book.getTitle() + "-" + book.getAuthor() +
                "-" + book.getPublisher() + "-" + book.getGenre() + "-" + book.getShelf();
    }

    public String parseCustomerToString(Customer customer){
	    return customer.getName() + "/" + customer.getAdress()
                + "/" + customer.getNumber() + "/" + customer.getPersonnummer();
    }

	public void removeLineFromFile(String path, String lineToRemove){
        try{
            File dirFile = new File(path);
            File tmpFile = new File(dirFile.getAbsolutePath() + ".tmp");
            BufferedReader br = new BufferedReader(new FileReader(path));
            PrintWriter pw = new PrintWriter(new FileWriter(tmpFile));
            String line;
            while((line = br.readLine()) != null){
                // I think it's saving a new line character at the end from when the user presses enter, therefore
                // a .trim() is required.
                if(!line.equals(lineToRemove.trim())){
                	// GONNA DELETE ALL BOOKS, FIX PLS
                    pw.println(line);
                    pw.flush();
                }
            }
            System.gc();
            pw.close();
            br.close();
            boolean success = dirFile.delete();
            boolean renameSuccess = tmpFile.renameTo(dirFile);

            if(success){
                System.out.println("Old file deleted");
            }
            if(renameSuccess) {
                System.out.println("file renamed");
            }
        } catch (Exception e){
            e.getMessage();
        }
    }

	public void borrowBook() throws Exception{
		//ADD FUNCTION TO REMOVE FROM TXT FILE AND ADD TO OTHER TXT FILE
		//must create txt file for customers borrowed books, create that when customer borrows their
		//first book, or create one for every customer when they are first created
        showAvailableBooks();
		System.out.println("Enter title of book to borrow:");
		scanner.nextLine();
		String title = scanner.nextLine();

		System.out.println("Enter personal security number:");
		String psn = scanner.nextLine();
		if(title.equals("") || psn.equals("")) {
			throw new Exception ("Empty title or social security number");
		}else {
		    System.out.println("about to borrow book yay");
            removeLineFromFile("res/bookDirectory.txt", parseBookToString(retrieveBook(title)));
		    // borrowBook in library checks whether the book is available in the library and moves it
            // to the appropriate arraylists.
			library.borrowBook(title, psn);

		}
	}

	public void returnBook() throws Exception{
		//ADD FUNCTION TO REMOVE FROM TXT FILE AND ADD TO OTHER TXT FILE -- NOT NEEDED???
		System.out.println("Enter title of book to return:");
		scanner.nextLine();
		String title = scanner.nextLine();
		System.out.println("Enter personal security number:");
		String psn = scanner.nextLine();

		if(title.equals("") || psn.equals("")) {
			throw new Exception ("Empty title or social security number");
		}else {
		    Book book = null;
            for(Customer c : retrieveCustomerDirectory()){
                if(c.getPersonnummer() == psn){
                    book = c.getFromCurrentLoan(title);
                }
            }
            writeBookToFile(book.getTitle(), book.getAuthor(), book.getPublisher(), book.getGenre(), book.getShelf());
			library.returnBook(title, psn);
		}
	}

	public void searchBook() {
		System.out.println("Choose what to search by");
		System.out.println("1. Search book using title");
		System.out.println("2. Search book using author");
		System.out.println("3. Search book using publisher");
		System.out.println("4. Search book using genre");
		System.out.println("5. Search book using shelf");

		int userInput = scanner.nextInt();
		scanner.nextLine();
		try {
            switch (userInput) {
                case 1:
                    System.out.println("Please enter the title");
                    String title = scanner.nextLine();
                    System.out.println(library.findBookBy(TITLE, title));
                    break;
                case 2:
                    System.out.println("Please enter the author");
                    String author = scanner.nextLine();
                    System.out.println(library.findBookBy(AUTHOR, author));
                    break;
                case 3:
                    System.out.println("Please enter the publisher");
                    String publisher = scanner.nextLine();
                    System.out.println(library.findBookBy(PUBLISHER, publisher));
                    break;
                case 4:
                    System.out.println("Please enter the genre");
                    String genre = scanner.nextLine();
                    System.out.println(library.findBookBy(GENRE, genre));
                    break;
                case 5:
                    System.out.println("Please enter the shelf");
                    String shelf = scanner.nextLine();
                    System.out.println(library.findBookBy(SHELF, shelf));
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } catch (Exception e){
		    e.getMessage();
        }
	}

	//Seems ok//
	public void sortBooks() { //doesnt work if the book starts with an lowercase letter,
		//also would be good to skip the "The" at the beginning of a book

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

	public void showAvailableBooks(){
        try {
            library.bookDirectory();
        }catch (Exception e) {
            e.getMessage();
        }
        System.out.println(library.toString());
    }

    public void writeCustomerToFile(String name, String address, String phoneNumber, String psn){
        if (!name.equals("") && !address.equals("")
                && !phoneNumber.equals("") && !psn.equals("")) {

            try (PrintWriter out = new PrintWriter(
                    new BufferedWriter(new FileWriter("res/customer.txt", true)))) {
                out.println(name + "/" + address + "/" + phoneNumber
                        + "/" + psn);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            System.out.println("Added " + name + " to customer database");
        }else {
            System.out.println("No parameters allowed to be empty");
        }
    }

	// -- Customer handling methods --//
	public void addCustomer() {
		//ADD FUNCTION TO ADD TO TXT FILE --done
        scanner.nextLine();
		System.out.println("Enter customer name: ");
		String name = scanner.nextLine();
		System.out.println("Enter customer adress: ");
		String address = scanner.nextLine();
		System.out.println("Enter customer phone number: ");
		String phoneNumber = scanner.nextLine();
		System.out.println("Enter customer personal security number: ");
		String psn = scanner.nextLine();

		try {
            library.addCustomer(new Customer(name, address,psn, phoneNumber));
            writeCustomerToFile(name, address, phoneNumber, psn);
		} catch (Exception e) {
			System.out.println("Please make sure name, address and personal security numbers are all filled out.");
			addCustomer();
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
        Customer customer = retrieveCustomer(psn);
        if(customer != null){
            removeLineFromFile("res/customer.txt", parseCustomerToString(customer));
            library.removeCustomer(customer);
        } else {
            System.out.println("There's no customer with that personnummer \n Please enter a valid one.");
            removeCustomer();
        }

	}

	public void searchCustomer() {
		System.out.println("Choose what to find customer by: ");
		System.out.println("1. Find customer by name");
		System.out.println("2. Find customer by address");
		System.out.println("3. Find customer by phone number");
		System.out.println("4. Find customer by personal security number");
		int userInput = scanner.nextInt();
		scanner.nextLine(); // skips a line

		try {
		    switch(userInput){
                case 1:
                    System.out.println("Enter the customer name: ");
                    String name = scanner.nextLine();
                    System.out.println(library.findCustomerBy(NAME, name.trim()));
                    break;
                case 2:
                    System.out.println("Enter the customer address: ");
                    String adress = scanner.next();
                    System.out.println(library.findCustomerBy(ADRESS, adress));
                    break;
                case 3:
                    System.out.println("Enter the customer phone number: ");
                    String phone = scanner.next();
                    System.out.println(library.findCustomerBy(NUMBER, phone));
                    break;
                case 4:
                    System.out.println("Enter the customer personal security number: ");
                    String psn = scanner.next();
                    System.out.println(library.findCustomerBy(PERSONNUMMER, psn));
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void showCustomers() {
        String res = "";
        System.out.println("In showCustomers");
        try {
            for(Customer c : retrieveCustomerDirectory()){
                if(c != null) {
                    res += c.toString();
                }
            }
        }catch (Exception e) {
            e.getMessage();
        }
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
	//MAKE TOSTRING METHODS FOR BOOKS!! --DONE
	public void showAllLoanedBooks() {
		library.getLoanedBooks();
		for(int i = 0; i < library.getLoanedBooks().size(); i++) {
			System.out.println(library.getLoanedBooks().get(i).toString());
		}
	}

	//MAKE TOSTRING METHODS FOR BOOKS!! --DONE, that is not the issue with these methods though.
    // most methods have the same issue, namely that bookDirectory() isnt called from the customer
    // so the books arraylist doesnt fill with the books from txt
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
	public void showCustomerLoanHistory() { //WILL THIS WORK?? ----- IT NOW WORKS
        scanner.nextLine();
		System.out.println("Enter the personal security number of the customer");
		String customerPsn = scanner.nextLine();
		System.out.println(customerPsn);
		Customer customer = retrieveCustomer(customerPsn.trim());

		try {
            for (Customer c : retrieveCustomerDirectory()) {
                if (c.equals(customer)) {
                        System.out.println("Here is " + c.getName() + "'s loan history: ");
                        library.getCustomerLoanHistory(c);
                    }
                }
        } catch(Exception e){
            System.out.println("No customer registered with that psn");
            showCustomerLoanHistory();
        }
		//here we also should be able to get loan history from only entering the PSN!! --That's what we are doing??
	}

    public static void main(String[] args) {
        // System.out.println("hello m");
        Test test = new Test();
        test.run();

    }
}

// TODO: things i wrote in my comments above, and add options for VG things !!,
// ADD error handling!!
//Add all the new options in the menu in this class and menu class (all methods must be used somewhere)
//make user able to exit by pressing "q" or "quit" --CAN DO THAT, need to change switch conditional to a char
//make sure everything is displayed - that they are in a sysout!
//write sysouts that confirm users actions tex if adding a book was successful or not
//see if i should use nextLine or if next is enough
//etc! - look through comments and go through the code, make sure all methods in library are used


/*      FOR G
 * For the grade (Pass), the following is expected:
 * 
 * There should be a directory of books. --DONE currently kept in list in Library and a txt in res/.txt
 * It should be possible to add new books to the directory (title, author, genre, publisher, shelf)--DONE for both list and txt
 * Sorting and searching of books, for example by Author. --DONE
 * To lend out a book to a customer and specify the date the book should be returned. --NOT SURE; NEEDS FURTHER TESTING
 * Register that the customer has returned the book. --RETURN OF BOOK REGISTERED, NOT SURE HOW IT INTERACTIS WITH CUSTOMER
 * Customers should be in a customer register. --DONE
 */
/*       FOR VG

 * For the grade “Väl Godkänd” (Pass with distinction) the criteria for pass
 * must be met as well as:
 * Should be able to show all borrowed books
 * Should be able to view all delayed books
 * Should be able to show if the borrower returned the book is delayed and what the total delay fee will be.
 * Statistics on what books have been lent out the most
 * To be able to view the borrower's loan history --DONE
 * For the grade (Pass with distinction) the criteria for pass
 * must be met as well as: Should be able to show all borrowed books Should be
 * able to view all delayed books Should be able to show if the borrower
 * returned the book is delayed and what the total delay fee will be. Statistics
 * on what books have been lent out the most To be able to view the borrower's
 * loan history
 */

