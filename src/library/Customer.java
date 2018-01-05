package library;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**This class is a representation of Customer objects in the library system.
 * @author Majd Hatoum
 * @editor Oliver Manzi
 * */
public class Customer {
	
	private String name;
	private String adress;
	private String number;
	private double debt;
	private ArrayList<Book> currentLoans;
	private ArrayList<Book> loanHistory;
	private String personnummer;
	final String END_OF_LINE = System.lineSeparator();// Skips A Line

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

	
	
	
	/**gets name of the customer object
	 * */
	public String getName() {
		return this.name;
	}

	/**gets address of the customer object
	 * */
	public String getAdress() {
		return this.adress;
	}

	/**gets customer object's social security number 
	 * */
	public String getPersonnummer() {
		return this.personnummer;
	}

	/**gets the phone number of the customer object (if it exists).
	 * */
	public String getNumber() {
		return this.number;
	}

	/**gets the debt of the customer object.
	 * */
	public double getDebt() {
		return this.debt;
	}

	/**gets the book objects that are loaned by the customer object.
	 * */
	public ArrayList<Book> getCurrentLoans() {
		return this.currentLoans;
	}

	/**gets the book objects from the loan history of the customer object.
	 * */
	public ArrayList<Book> getRealLoanHistory() {
		return this.loanHistory;
	}

	/**sets the name of the customer object.
	 * */
	public void setName(String newName) throws Exception {
		if (newName.equals("")) {
			throw new Exception("Name can not be empty");
		} else {
			this.name = newName;
		}
	}

	/**sets the adress of the customer object.
	 * */
	public void setAdress(String newAdress) throws Exception {
		if (newAdress.equals("")) {
			throw new Exception("Adress can not be empty");
		} else {
			this.adress = newAdress;
		}
	}

	/**sets the phone number of the customer object.
	 * */
	public void setNumber(String newNumber) throws Exception {
		if (newNumber.trim().matches("[0-9]+") && newNumber.trim().length() == 10) {
			this.number = newNumber;
		} else {
			throw new Exception("Phone number MUST consist of 10 digits");
		}
	}

	/**sets the debt of the customer object. 
	 * */
	public void setDebt(double newDebt) {
		this.debt = newDebt + this.debt;
	}

	/**adds a book to the current loans of a customer object.
	 * */
	public void addToCurrentLoan(Book book) {
		currentLoans.add(book);
	}

	/**adds a book to the loan history of the customer object.
	 * */
	public void addToLoanHistory(Book book) {
		loanHistory.add(book);
	}

	/**gets the book loan history of the customer object.
	 * */
	public ArrayList<Book> getLoanHistory() {
		return loanHistory;
	}

	/**removes a book from the current book loans of the customer object.
	 * */
	public void removeFromCurrentLoan(Book book) {
		currentLoans.remove(book);
	}

	/**gets a book object from the current book loans of the customer object.
	 * */
	public Book getFromCurrentLoan(String bookTitle) {
		for (Book book : currentLoans) {
			if (book.getTitle().equalsIgnoreCase(bookTitle)) {
				return book;
			}
		}
		return null;
	}

	/**reads the customer books (current loans and loan history) information from the text file when inserting the path of it.
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
	 * @version 1.0 Created by Majd: Displays customer information.
	 * @version 1.1 Modified by Oliver: Distinguishes "prints" from attributes"
	 * */
	/**displays the customer object with its properties as strings.
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
