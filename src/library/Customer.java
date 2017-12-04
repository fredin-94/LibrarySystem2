package library;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
public class Customer implements Serializable {

	private String name;
	private String adress;
	private String number = "";
	private double debt;// Need to know how is it going to be used or changed.
	private ArrayList<Book> currentLoans;
	private ArrayList<Book> loanHistory;
	private UUID ID;
	private String personnummer;
	final String END_OF_LINE = System.lineSeparator();// Skips A Line

	public Customer(String name, String adress, String personnummer) throws Exception {
		if (name.equals("")) {

			throw new Exception("Name can not be empty");
		} else {
			this.name = name;
		}
		if (adress.equals("")) {
			throw new Exception("Adress can not be empty");
		} else {
			this.adress = adress;
		}
		this.ID = UUID.randomUUID();
		this.currentLoans = new ArrayList<Book>();
		this.loanHistory = new ArrayList<Book>();
		this.debt = 0;
		if (personnummer.matches("[0-9]+") && personnummer.length() == 10 || personnummer.length() == 12) {// This																								// (number).
			this.personnummer = personnummer;
		} else {
			throw new Exception("Personnummer MUST consist of 10 or 12 digits");
		}
		customerBooks("res/"+personnummer+"CurrentLoans.txt");
		customerBooks("res/"+personnummer+"LoanHistory.txt");
	}

	public Customer(String name, String adress, String personnummer, String number) throws Exception {
		this(name, adress, personnummer);// calling the first constructor.

		if (number.trim().matches("[0-9]+") && number.trim().length() == 10) {// This checks the number of digits of (number).
			this.number = number;
		} else {
			throw new Exception("Phone number MUST consist of 10 digits");
		}
		customerBooks("res/"+personnummer+"CurrentLoans.txt");
		customerBooks("res/"+personnummer+"LoanHistory.txt");
	}

	public UUID getID() {
		return this.ID;
	}

	public String getName() {
		return this.name;
	}

	public String getAdress() {
		return this.adress;
	}

	public String getPersonnummer() {
		return this.personnummer;
	}

	public String getNumber() {
		return this.number;
	}

	public double getDebt() {
		return this.debt;
	}

	public ArrayList<Book> getCurrentLoans() {
		return this.currentLoans;
	}

	public ArrayList<Book> getloanHistory() {
		return this.loanHistory;
	}

	public void setName(String newName) throws Exception {
		if (newName.equals("")) {
			throw new Exception("Name can not be empty");
		} else {
			this.name = newName;
		}
	}

	public void setAdress(String newAdress) throws Exception {
		if (newAdress.equals("")) {
			throw new Exception("Adress can not be empty");
		} else {
			this.adress = newAdress;
		}
	}

	public void setPersonnummer(String newPersonnummer) throws Exception {
		if (newPersonnummer.matches("[0-9]+") && (newPersonnummer.length() == 10 || newPersonnummer.length() == 12)) {
			this.personnummer = newPersonnummer;
		} else {
			throw new Exception("Personnummer MUST consist of 10 or 12 digits");

		}
	}

	public void setNumber(String newNumber) throws Exception {
		if (newNumber.trim().matches("[0-9]+") && newNumber.trim().length() == 10) {
			this.number = newNumber;
		} else {
			throw new Exception("Phone number MUST consist of 10 digits");
		}
	}

	public void setDebt(double newDebt) {
		this.debt = newDebt;
	}

	public void addToCurrentLoan(Book book) {// This can be changed if we change the parameters( same for the next two
												// methods).
		currentLoans.add(book);
	}

	public void addToLoanHistory(Book book) {
		loanHistory.add(book);
	}

	public void removeFromCurrentLoan(Book book) {
		currentLoans.remove(book);
	}

	public Book getFromCurrentLoan(String bookTitle) {
		for(Book book: currentLoans) {
			if(book.getTitle().equalsIgnoreCase(bookTitle)) {
				return book;
			}
		}
		return null;
	}
	
	public void addBookToCurrentLoan(Book book) {
		currentLoans.add(book);
	}

	public void addBookToLoanHistory(Book book) {
		loanHistory.add(book);
	}
	
	
	
	public void customerBooks(String path) throws FileNotFoundException {
		File file = new File(path);
		Scanner input = new Scanner(file);
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
				if(path.contains("CurrentLoans")) {
					currentLoans.add(book);
				}else if(path.contains("LoanHistory")) {
					loanHistory.add(book);
				}
			}
		}
	}
	

	public ArrayList<Book> getLoanHistory() {
		return loanHistory;
	}

	public String toString() {// Need to check the form then it will be edited.
		String printC = " " + END_OF_LINE; // PrintC is going to include everything to be printed.
		printC += "Customer Name: " + this.name + END_OF_LINE;
		printC += "Customer ID: " + this.ID + END_OF_LINE;
		printC += "Customer Personnummer: " + this.personnummer + END_OF_LINE;
		printC += "Customer Adress: " + this.adress + END_OF_LINE;
		if (this.number.equals("")) {// Checking if the user chose the second constructor (the one with no number in
			// it).
			printC += "Customer Phone Number: Not Available" + END_OF_LINE;
		} else {
			printC += "Customer Phone Number: " + this.number + END_OF_LINE;
		}

		printC += "Customer Debt: " + this.debt + END_OF_LINE;
		if (this.loanHistory.isEmpty()) {// Checking if the customer has no loan history.
			printC += "Customer's loan history is empty" + END_OF_LINE;
		} else {
			String historyB = "";

			for (int i = 0; i < loanHistory.size(); i++) {
				historyB += loanHistory.get(i).toString() + END_OF_LINE;
			}
			printC += "Customer's loan History: " + END_OF_LINE + historyB;
		}
		if (this.currentLoans.isEmpty()) {// Checking if the customer has no current loans.
			printC += "The customer has no current loans" + END_OF_LINE;
		} else {
			String currentB = "";

			for (int i = 0; i < currentLoans.size(); i++) {
				currentB += currentLoans.get(i).toString() + END_OF_LINE;
			}
			printC += "Customer's current loan(s): " + END_OF_LINE + currentB;
		}

		return printC;
	}

}
