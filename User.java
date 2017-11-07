package library;
import java.util.*;

public class User {

	private String id;
	private String name;
	private String address;
	private int phoneNumber;
	private ArrayList<Book>currentBooks;
	
	public User(String id, String name, String address, int phoneNumber) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		currentBooks = new ArrayList<Book>();
	}

	public String getId() {
		return id;
	}
	
	public String getname() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public int getPhoneNumber() {
		return phoneNumber;
	}

	public ArrayList<Book> getLoanedBooks() {
		return currentBooks;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
