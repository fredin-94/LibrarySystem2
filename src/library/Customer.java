package library;

import java.util.ArrayList;

public class Customer {

	private String id;
	private String name;
	private String address;
	private int phoneNumber;
	private ArrayList<Book>currentBooks;
	private static int generator = 1;
	
	public Customer(String name, String address, int phoneNumber) throws Exception{
		{
			this.id = String.valueOf(generator);
			generator++;
		}
		
		if(name.equals("")) {
			throw new Exception ("Empty name.");
		}else {
			this.name = name;
		}
		
		if(address.equals("")) {
			throw new Exception ("Empty address.");
		}else {
			this.address = address;
		}
		
		{
			String temp = String.valueOf(phoneNumber);
			int length = temp.length();
			if(phoneNumber < 0 ) {
				throw new Exception ("Number cannot be less than zero.");
			}else if(length < 10) {
				throw new Exception ("Phone number has less than 10 digits");
			}else {
				this.phoneNumber = phoneNumber;
			}	
		}
		
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

	public ArrayList<Book> getCurrentBooks() {
		return currentBooks;
	}
	
	public void addBook(Book book) {
		currentBooks.add(book);
	}
	
	public void removeBook(Book book) {
		currentBooks.remove(book);
	}
	
	public int numberOfBooks() {
		return currentBooks.size();
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
	
	@Override
	public String toString() {
		return "Id: "+id+", name: "+name+", address: "+address+", phone number: "+phoneNumber
				+", loaned books: "+currentBooks;
	}
}
