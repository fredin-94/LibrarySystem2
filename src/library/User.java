package library;

import java.util.ArrayList;

public class User {

	private String id;
	private String name;
	private String address;
	private int phoneNumber;
	private ArrayList<Book>currentBooks;
	private static int secureId = 1;
	
	public User(String name, String address, int phoneNumber) throws Exception{
		{
			this.id = String.valueOf(secureId);
			secureId++;
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
	
	@Override
	public String toString() {
		return "Id: "+id+", name: "+name+", address: "+address+", phone number: "+phoneNumber
				+", loaned books: "+currentBooks;
	}
}
