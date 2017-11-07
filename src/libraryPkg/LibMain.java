package libraryPkg;

import java.awt.Label;

import bookPkg.Book;
import bookPkg.BookList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

public class LibMain implements EventHandler<ActionEvent>{
	
	BookList bookList = new BookList();
	LibInterface libInterface = new LibInterface();

	public static void main(String[] args) {
		
		BookDirectory bookDirectory = new BookDirectory();
		BookList bookList = new BookList();
		
		bookList.preBooks();
		bookList.getBooks();
		
		LibInterface libInterface = new LibInterface();
		
		new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(LibInterface.class);
            }
        }.start();
       

	}

	public void addBook() {	
		Book book = new Book(libInterface.getBookName(), libInterface.getBookAuthor(), libInterface.getBookGenre(), libInterface.getBookPublisher(), libInterface.getBookShelf());	
		bookList.addBook(book);
		//libInterface.directoryMenu.getChildren().add(new Label("Added book"));
	}
	
	@Override
	public void handle(ActionEvent event) { // method called whenever user clicks the button

		if (event.getSource() == libInterface.showBooks) {

		//	bookList.preBooks();
		//	bookList.getBooks();
		}
		if(event.getSource() == libInterface.addNewBook) {
			//addBook();
		}

	}

}
