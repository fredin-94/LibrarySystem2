package library;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**<I, the author, am a jackass!>
 * 
 * @author Majd Hatoum
 * @author Oliver Manzi
 * */
public class Customer {


	private String name;
	private String adress;
	private String number;
	private double debt;
	private ArrayList<Book> currentLoans;
	private ArrayList<Book> loanHistory;
	private String personnummer;
	final String END_OF_LINE = System.lineSeparator();

	public Customer(String name, String adress, String personnummer, String number) throws Exception {

		this.name = name;
		this.adress = adress;
		this.personnummer = personnummer;
		this.number = number;
		this.currentLoans = new ArrayList<Book>();
		this.loanHistory = new ArrayList<Book>();

		customerBooks("res/" + personnummer + "CurrentLoans.txt");
		customerBooks("res/" + personnummer + "LoanHistory.txt");
	}
	
	/**<I, the author, am a jackass!>
	 * 
	 * @author Majd Hatoum
	 * */
	public String getName() {
		return this.name;
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Majd Hatoum
	 * */
	public String getAdress() {
		return this.adress;
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Majd Hatoum
	 * */
	public String getPersonnummer() {
		return this.personnummer;
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Majd Hatoum
	 * */
	public String getNumber() {
		return this.number;
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Majd Hatoum
	 * */
	public double getDebt() {
		return this.debt;
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Majd Hatoum
	 * */
	public ArrayList<Book> getCurrentLoans() {
		return this.currentLoans;
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Majd Hatoum
	 * */
	public ArrayList<Book> getRealLoanHistory() {
		return this.loanHistory;
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Majd Hatoum
	 * */
	public void setName(String newName) throws Exception {
		if (newName.equals("")) {
			throw new Exception("Name can not be empty");
		} else {
			this.name = newName;
		}
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Majd Hatoum
	 * */
	public void setAdress(String newAdress) throws Exception {
		if (newAdress.equals("")) {
			throw new Exception("Adress can not be empty");
		} else {
			this.adress = newAdress;
		}
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Majd Hatoum
	 * */
	public void setNumber(String newNumber) throws Exception {
		if (newNumber.trim().matches("[0-9]+") && newNumber.trim().length() == 10) {
			this.number = newNumber;
		} else {
			throw new Exception("Phone number MUST consist of 10 digits");
		}
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Majd Hatoum
	 * */
	public void setDebt(double newDebt) {
		this.debt = newDebt + this.debt;
	}
	
	/**<I, the author, am a jackass!>
	 * 
	 * @author Majd Hatoum
	 * */
	public void payDebt(double payement) throws Exception {
		if((this.debt - payement) >= 0) {
			this.debt = this.debt - payement;
		} else {
			throw new Exception ( payement + " SEK exceeds the required\n amount of " + (this.debt - 0) + " SEK");
		}
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Majd Hatoum
	 * */
	public void addToCurrentLoan(Book book) {
		currentLoans.add(book);
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Majd Hatoum
	 * */
	public void addToLoanHistory(Book book) {
		loanHistory.add(book);
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Majd Hatoum
	 * */
	public ArrayList<Book> getLoanHistory() {
		return loanHistory;
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Majd Hatoum
	 * */
	public void removeFromCurrentLoan(Book book) {
		currentLoans.remove(book);
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Majd Hatoum
	 * */
	public Book getFromCurrentLoan(String bookTitle) {
		for (Book book : currentLoans) {
			if (book.getTitle().equalsIgnoreCase(bookTitle)) {
				return book;
			}
		}
		return null;
	}

	/**<I, the author, am a jackass!>
	 * 
	 * @author Majd Hatoum
	 * */
	public void customerBooks(String path) throws FileNotFoundException {
		File file = new File(path);
		Scanner input = new Scanner(file);
		input.useDelimiter("/|\n");
		//System.out.println("current customer: " + this.name);
		while (input.hasNext()) {
			String title = input.next().trim();
			String authors = input.next().trim();
			String publisher = input.next().trim();
			String genre = input.next().trim();
			String shelf = input.next().trim();
			String timesBorrowed = input.next().trim();
			String returnDate = input.next();
			Book book = null;
			try {
				book = new Book(title, authors, publisher, genre, shelf);
				book.setTimesBorrowed(Integer.parseInt(timesBorrowed));
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // need to change this?
				book.setReturnDate(LocalDate.parse(returnDate, formatter));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (path.contains("CurrentLoans")) {
					currentLoans.add(book);
				} else if (path.contains("LoanHistory")) {
					loanHistory.add(book);
				}
			}
		}
	}

	/**Returns a string of customer attribute information.
	 * 
	 * @author Majd Hatoum
	 * @author Oliver Manzi
	 * 
	 * @version 1.0 Created by Majd: Displays customer information.
	 * @version 1.1 Modified by Oliver: Distinguishes "prints" from attributes"
	 * */
	@Override
	public String toString() {
		String customerToString = " " + END_OF_LINE;
		customerToString += "||----------------------------------------------||" + END_OF_LINE;
		customerToString += "Customer Name: " + this.name + END_OF_LINE;
		customerToString += "Customer Personnummer: " + this.personnummer + END_OF_LINE;
		customerToString += "Customer Adress: " + this.adress + END_OF_LINE;
		customerToString += "Customer Phone Number: " + this.number + END_OF_LINE;
		customerToString += "Customer Debt: " + this.debt + END_OF_LINE;
		customerToString += "Size of curently loaned books: " + this.currentLoans.size() + " book(s)" + END_OF_LINE;
		customerToString += "Size of loan history: " + this.loanHistory.size() + " book(s)" + END_OF_LINE;
		return customerToString;
	}
}
