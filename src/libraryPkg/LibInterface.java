package libraryPkg;

import java.util.jar.Attributes.Name;

import bookPkg.Book;
import bookPkg.BookList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LibInterface extends Application implements EventHandler<ActionEvent> {
	// handle user clicking a button with actionevent

	//LibMain libMain = new LibMain();
	// BookDirectory bookDirectory = new BookDirectory();
	BookList bookList = new BookList();

	public Scene directory;
	public VBox directoryMenu;

	Button directoryBtn;
	Button borrowBookBtn;
	Button returnBookBtn;
	Button customerRegBtn;
	Button bookStatsBtn;

	Button showBooks;
	Button addNewBook;

	Button homeBtn;
	
	TextField name;
	TextField genre;
	TextField author;
	TextField publisher;
	TextField shelf;
	
	private String bookName = "hello!";
	private String bookAuthor = "";
	private String bookGenre = "";
	private String bookPublisher = "";
	private String bookShelf = "0";

	public String getBookName() {
		return bookName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public String getBookGenre() {
		return bookGenre;
	}
	public String getBookPublisher() {
		return bookPublisher;
	}
	public String getBookShelf() {
		return bookShelf;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("The library website");

		// GridPane pane = new GridPane();
		// pane.setAlignment(Pos.CENTER);
		// pane.setVgap(10);
		// pane.setHgap(10);
		//
		// //for some reason needed to crate another gridpane for new scene... o.o!
		// GridPane pane2 = new GridPane();
		// pane2.setAlignment(Pos.CENTER);
		// pane2.setVgap(10);
		// pane2.setHgap(10);

		directoryBtn = new Button();
		borrowBookBtn = new Button();
		returnBookBtn = new Button();
		customerRegBtn = new Button();
		bookStatsBtn = new Button();
		homeBtn = new Button();
		showBooks = new Button();
		addNewBook = new Button();

		VBox mainMenu = new VBox(20); // space all things out 20px
		Scene scene = new Scene(mainMenu, 900, 800);

		directoryMenu = new VBox(20);
		directory = new Scene(directoryMenu, 900, 800);
		
		mainMenu.setPadding(new Insets(20,40,20,40));
		
//		ScrollPane scrollPane = new ScrollPane();
//		scrollPane.setContent(directory);
//	    scrollPane.setFitToHeight(true);
//	    scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
	   // BorderPane root = new BorderPane(scrollPane);
	    //root.setPadding(new Insets(15));
	    
	   // directoryMenu.getChildren().add(scrollPane);

		Label greeting = new Label("Welcome to the library system");
		//greeting.setContentDisplay(ContentDisplay.CENTER); //doesnt work
		greeting.setTextFill(Color.BLUEVIOLET);
		greeting.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 35)); //doesnt work!
		greeting.setStyle("-fx-border-color: blue;");

		Label instructions = new Label("Please choose an option");
		instructions.setFont(Font.font("Arial", FontWeight.NORMAL, 20));

		directoryBtn.setText("Book directory");
		borrowBookBtn.setText("Borrow a book");
		returnBookBtn.setText("Return a book");
		customerRegBtn.setText("Customer registry");
		bookStatsBtn.setText("Book statistics");
		homeBtn.setText("Return to main menu");
		showBooks.setText("show all books");
		addNewBook.setText("Add a new book");

		directoryBtn.setOnAction(e -> primaryStage.setScene(directory)); // lambda expression
		borrowBookBtn.setOnAction(this);
		returnBookBtn.setOnAction(this);
		customerRegBtn.setOnAction(this);
		bookStatsBtn.setOnAction(this);
		homeBtn.setOnAction(e -> primaryStage.setScene(scene));
		showBooks.setOnAction(this);
		addNewBook.setOnAction(this);
		

		mainMenu.getChildren().add(greeting);
		mainMenu.getChildren().add(instructions);
		mainMenu.getChildren().add(directoryBtn);
		mainMenu.getChildren().add(borrowBookBtn);
		mainMenu.getChildren().add(returnBookBtn);
		mainMenu.getChildren().add(customerRegBtn);
		mainMenu.getChildren().add(bookStatsBtn);

		directoryMenu.getChildren().add(homeBtn);
		directoryMenu.getChildren().add(showBooks);
		
		
		//add name 
		directoryMenu.getChildren().add(new Label("Add new book to directory"));
		directoryMenu.getChildren().add(new Label("Enter name of book:"));	
		name = new TextField();
		name.setMinWidth(120);
		name.setMaxWidth(200);
		directoryMenu.getChildren().add(name);
		
		//add author
		directoryMenu.getChildren().add(new Label("Enter author of book:"));
		author = new TextField();
		author.setMinWidth(120);
		author.setMaxWidth(200);
		directoryMenu.getChildren().add(author);
		
		//add genre
		directoryMenu.getChildren().add(new Label("Enter genre of book:"));
		genre = new TextField();
		genre.setMinWidth(120);
		genre.setMaxWidth(200);
		directoryMenu.getChildren().add(genre);
		
		//add publisher
		directoryMenu.getChildren().add(new Label("Enter publisher of book:"));
		publisher = new TextField();
		publisher.setMinWidth(120);
		publisher.setMaxWidth(200);
		directoryMenu.getChildren().add(publisher);
		
		//add shelf
		directoryMenu.getChildren().add(new Label("Enter shelf of book:"));
		shelf = new TextField();
		shelf.setMinWidth(120);
		shelf.setMaxWidth(200);
		directoryMenu.getChildren().add(shelf);

		//DONT WORK! they are in the wrong place and become empty thanks to that
//		bookName = name.getText();
//		bookAuthor = author.getText();
//		bookGenre = genre.getText();
//		bookPublisher = publisher.getText();
//		bookShelf = shelf.getText();
		
		directoryMenu.getChildren().add(addNewBook);
		
		primaryStage.setScene(scene);
		primaryStage.show(); // launches application
	}

	@Override
	public void handle(ActionEvent event) { // method called whenever user clicks the button

		if (event.getSource() == showBooks) {
			
			for(int i = 0; i < bookList.bookListSize(); i++) {
				directoryMenu.getChildren().add(new Text(bookList.getBooks())); //ONLY GET THE LAST ONE NOW
			}	
			//bookList.preBooks();
			bookList.getBooks();
		}
		if(event.getSource() == addNewBook) {
			String nameString = name.getText();
			bookList.addBook(new Book(name.getText(), author.getText(), genre.getText(), publisher.getText(), shelf.getText()));
			name.clear();
			author.clear();
			genre.clear();
			publisher.clear();
			shelf.clear();
					
			Text text1 = new Text("Added " + nameString);
			directoryMenu.getChildren().add(text1);
			//System.out.println(bookName);
		}

	}

}
