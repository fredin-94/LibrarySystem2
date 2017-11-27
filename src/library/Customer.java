package library;

import java.util.ArrayList;
import java.util.UUID;

public class Customer {

	private String name;
	private String adress;
	private int number;
	private double debt;// Need to know how is it going to be used or changed.
	private ArrayList<Book> currentLoans;
	private ArrayList<Book> loanHistory;
	private UUID ID;
	public Exception nameE = new Exception("Name Can not Be Empty");// Exceptions to be thrown:
	public Exception adressE = new Exception("Adress Can not Be Empty");
	public Exception numberE = new Exception("number must be 10 digits");
	final String END_OF_LINE = System.lineSeparator();// Skips A Line

	public Customer(String name, String adress) throws Exception {
		if (name.equals("")) {

			throw nameE;
		} else {
			this.name = name;
		}
		if (adress.equals("")) {
			throw adressE;
		} else {
			this.adress = adress;
		}
		this.ID = UUID.randomUUID();
		this.currentLoans = new ArrayList<Book>();
		this.loanHistory = new ArrayList<Book>();
		this.debt = 0;

	}

	public Customer(String name, String adress, int number) throws Exception {
		this(name, adress);// calling the first constructor.

		if ((int) Math.log10(number) + 1 != 10) {// This checks the number of digits of (number).
			throw numberE;
		} else {
			this.number = number;
		}
	}

	//kolopl
	public UUID getID() {
		return this.ID;
	}

	public String getName() {
		return this.name;
	}

	public String getAdress() {
		return this.adress;
	}

	public int getNumber() {
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
			throw nameE;
		} else {
			this.name = newName;
		}
	}

	public void setAdress(String newAdress) throws Exception {
		if (newAdress.equals("")) {
			throw adressE;
		} else {
			this.adress = newAdress;
		}
	}

	public void setNumber(int newNumber) throws Exception {
		if ((int) Math.log10(newNumber) + 1 != 10) {
			throw numberE;
		} else {
			this.number = newNumber;
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

	public void addBookToCurrentLoann(Book book) {
		currentLoans.add(book);
	}

	public void addBookToLoanHistory(Book book) {
		loanHistory.add(book);
	}

	public void removeBookFromLoanHistory(Book book) {
		currentLoans.remove(book);
	}

	public String toString() {// Need to check the form then it will be edited.
		String printC = " " + END_OF_LINE; // PrintC is going to include everything to be printed.
		printC += "Customer Name: " + this.name + END_OF_LINE;
		printC += "Customer ID: " + this.ID + END_OF_LINE;
		printC += "Customer Adress: " + this.adress + END_OF_LINE;
		if (this.number == 0) {// Checking if the user chose the second constructor (the one with no number in
								// it).
			printC += "Customer Number: Not Available" + END_OF_LINE;
		} else {
			printC += "Customer Number: " + this.number + END_OF_LINE;
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