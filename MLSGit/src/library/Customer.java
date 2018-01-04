package library;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import javafx.collections.ObservableList;

public class Customer {

	// private String id;
	private String name;
	private String address;
	private String phoneNumber;
	private String psn;
	private ArrayList<Book> currentLoans;
	private ObservableList<Book> currentLoansObs;
	private ArrayList<Book> loanHistory;
	private ObservableList<Book> loanHistoryObs;
	private UUID id;
	private int debt;

//	public Customer(String name, String address, String psn, int debt) throws Exception {
//
//		this.id = UUID.randomUUID();
//
//		this.currentLoans = new ArrayList<Book>();
//		this.loanHistory = new ArrayList<Book>();
//		
//		
//		//this.psn = psn;
//		
//		customerBooks("res/customerCurrentLoans/"+psn+"CurrentLoans.txt");
//		customerBooks("res/customerLoanHistory/"+psn+"LoanHistory.txt");
//		
//	}

	public Customer(String name, String address, String psn, String phoneNumber, int debt) throws Exception {
		//this.psn = psn; //this.name = name; this.address = address;
		this.id = UUID.randomUUID();
		this.currentLoans = new ArrayList<Book>();
		this.loanHistory = new ArrayList<Book>();
		
		this.debt = debt;
		
		if (psn.matches("[0-9]+") &&  psn.length() == 10 || psn.length() == 12) {// This																								// (number).
			this.psn = psn;
		} else {
			throw new Exception("PSN MUST consist of 10 or 12 digits");
		}
		if (name.equals("")) {
			throw new Exception("Name can not be empty");
		} else {
			this.name = name;
		}
		if (address.equals("")) {
			throw new Exception("Address can not be empty");
		} else {
			this.address = address;
		}
		
		if (phoneNumber.trim().matches("[0-9]+") && phoneNumber.trim().length() == 10) {// This checks the number of digits of (number).
			this.phoneNumber = phoneNumber;
		} else if (phoneNumber.equals("")) {
			this.phoneNumber= "   ";
		}else if(phoneNumber.equals("   ")) {
			this.phoneNumber=phoneNumber;
		}
		else {
			throw new Exception("Phone number MUST consist of 10 digits");
		}
		customerBooks("res/customerCurrentLoans/"+psn+"CurrentLoans.txt");
		customerBooks("res/customerLoanHistory/"+psn+"LoanHistory.txt");
		
	}
//	public Customer() {
//		
//	}

	public ArrayList<Book> getCurrentBooks() {
		return currentLoans;
	}

	public void addBook(Book book) {
		currentLoans.add(book);
	}

	public void removeBook(Book book) {
		currentLoans.remove(book);
	}
	
	public void removeFromCurrentLoan(Book book) {
		currentLoans.remove(book);
	}

	public int numberOfBooks() {
		return currentLoans.size();
	}

	public boolean getBookTitle(String randomTitle) {
		for (int i = 0; i < currentLoans.size(); i++) {
			if (currentLoans.get(i).getTitle().equals(randomTitle)) {
				return true;
			}
		}
		return false;
	}

	// GETTERS AND SETTERS//
	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPsn() {
		return psn;
	}

	public ArrayList<Book> getLoanHistory() {
		return loanHistory;
	}

	public int getDebt() {
		return debt;
	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public void setPhoneNumber(String phoneNumber) {
//		this.phoneNumber = phoneNumber;
//	}
	
	public Book getFromCurrentLoan(String bookTitle) {
		for(Book book: currentLoans) {
			if(book.getTitle().equalsIgnoreCase(bookTitle)) {
				return book;
			}
		}
		return null;
	}

	public void setDebt(int debt) {
		this.debt = debt;
	}

	public String getCurrentBooksList() {
		String currBooks = "";
		for (Book book : currentLoans) {
			System.out.println(book);
			currBooks = "" + book.getTitle() + " ";
		}
		return currBooks;
	}

	// TO STRING//
	@Override
	public String toString() {
		return "Name: " + name + ", address: " + address + ", phone number: " + phoneNumber
				+ ", personal security number: " + psn + ", Library card ID: " + id + ", loaned books: " + currentLoans;
	}

	// from libsys2 //

	public void addToCurrentLoan(Book book) {// This can be changed if we change the parameters( same for the next two
		// methods).
		currentLoans.add(book);
	}

	public void addToLoanHistory(Book book) {
		loanHistory.add(book);
	}
	
	public void customerBooks(String path) throws FileNotFoundException {
		File file = new File(path);
		Scanner input = new Scanner(file);
	    input.useDelimiter("%|\n");

		while(input.hasNext()) {
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
				book = new Book(title, author, publisher, genre, shelf,numOfCopies,timesBorrowed,returnDate);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(path.contains("CurrentLoans")) {
					currentLoans.add(book);
				}else if(path.contains("LoanHistory")) {
					loanHistory.add(book);
				}
			}
		}
	}
	
	public ObservableList<Book> bookDirectoryForGUI(String path) throws FileNotFoundException {		
		File file = null;
		if (path.contains("currentLoans")) {
			 file = new File("res/customerCurrentLoans/"+path+".txt");
		} else if(path.contains("laonHistory")){
			 file = new File("res/customerLoanHistory/"+path+".txt");
		}	
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
				book = new Book(title, author, publisher, genre, shelf, numOfCopies, timesBorrowed, returnDate);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (path.contains("currentLoans")) {
					currentLoansObs.add(book);
					return currentLoansObs;
				} else if (path.contains("loanHistory")) {
					loanHistoryObs.add(book);
					return loanHistoryObs;
				} 
			}
		}
		return null;
	}
	
	public void addToArrayList(ObservableList<Book> list, String txtFile) throws FileNotFoundException {
		Scanner input = null;	
		if (txtFile.contains("currentLoans")) {
			input = new Scanner(new File("res/customerCurrentLoans/"+txtFile+".txt"));
		} else if(txtFile.contains("loanHistory")){
			input = new Scanner(new File("res/customerLoanHistory/"+txtFile+".txt"));
		}
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
}
