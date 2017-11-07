package bookPkg;

import java.util.ArrayList;
import java.util.List;

public class BookList {
	
	List<Book> books = new ArrayList<Book>();
	
	public void preBooks() {
//		books.add(new Book("catcher in da rye", "guy", "old", "rabensjögren", "10"));
//		books.add(new Book("dev", "guy2", "big", "hm", "65"));
//		books.add(new Book("book3", "guy3", "medium", "rabensjögren", "2"));
//		books.add(new Book("my story book", "guy4", "new", "big boy", "11"));
	}
	
	public void addBook(Book book) {
		books.add(book);
	}
	
	public String getBooks() {
		String returnString = "";
		for(int i = 0; i < books.size(); i++) {
			//System.out.println(books.get(i).toString());
			returnString = books.get(i).toString();
		}
		return returnString;
	}
	
	public int bookListSize() {
		int counter = 0;
		
		for (int i = 0; i < books.size(); i++) {
			counter++;
		}
		
		return counter;
	}

}
