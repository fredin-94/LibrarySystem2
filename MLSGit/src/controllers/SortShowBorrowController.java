package controllers;

import javafx.scene.control.MenuItem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.MainMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import library.Book;
import library.Customer;
import library.Library;

public class SortShowBorrowController implements EventHandler<ActionEvent>, Initializable {
	private Library library = new Library();
	private MainMenu mainm = new MainMenu();
	//private Customer customer = new Customer();
	// Book book = new Book();

	// connects fxml file to its controller
	@FXML
	private ScrollPane mainArea;
	@FXML
	private Label topLabel, addedBookPrompt, borrowBookLbl;
	@FXML
	private MenuItem showAllBooks, showAllAvailableBooks, showMostLentBooks, showCurrLentBooks, showDelayedBooks;
	@FXML
	private Button returnToMainBtn, addBookBtn, searchBookBtn, removeBookBtn, borrowBookBtn;
	@FXML
	private TextField addTitleTxt, addAuthorTxt, addPublisherTxt, addGenreTxt, addShelfTxt, searchBookTextField,
			borrowBookTitleTextField, bookToRemoveTxt, psnTextField;
	@FXML
	private TableView<Book> tableView, borrowTableView, removeTableView;
	@FXML
	private TableColumn<Book, String> titleCol, authorCol, publisherCol, genreCol, shelfCol, idCol, borrowTblTitleCol,
			borrowTblAuthorCol, borrowTblPublisherCol, borrowTblGenreCol, borrowTblShelfCol, removeTblTitleCol,
			removeTblAuthorCol, removeTblPublisherCol, removeTblGenreCol, removeTblShelfCol, numOfCopiesCol, timesBorrowedCol;

	private ObservableList<Book> tableData = FXCollections.observableArrayList();
	private ObservableList<Book> delayedBooksTableData = FXCollections.observableArrayList();
	private ObservableList<Book> borrowRemoveBookTableData = FXCollections.observableArrayList();
	private FilteredList<Book> filteredData;
	
	private LocalDate today;

	// HANDLE SWITCHING SCENES
	@Override
	public void handle(ActionEvent event) {

		if (event.getSource().equals(returnToMainBtn)) {
			try {
				System.out.println("sortborrow-return");
				Parent mainMenuParent = FXMLLoader.load(getClass().getResource("/gui/mainMenu.fxml"));
				Scene mainMenuScene = new Scene(mainMenuParent);
				Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				primaryStage.setScene(mainMenuScene);
				primaryStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// HANDLE WHAT TO SHOW AND WHEN
	public void handleShow(ActionEvent event) throws FileNotFoundException {

		if (event.getSource().equals(showAllAvailableBooks)) { //CHANGE THIS TO NOT SHOW ONES WITH 0 COPIES
			showBooks("availableBooks");
			topLabel.setText("All available books");
			//this one doesnt work
		} else if (event.getSource().equals(showAllBooks)) {
			showBooks("availableBooks");
			topLabel.setText("All books (lent out and available)");
		}
		else if (event.getSource().equals(showCurrLentBooks)) {
			showBooks("loanedBooks");
			topLabel.setText("All loaned out books");
		}
		else if (event.getSource().equals(showDelayedBooks)) {
			showBooks("delayedBooks");
			topLabel.setText("All delayed books");
		}
	}

	// HANDLE ADDING AND REMOVING BOOKS TO LIST
	public void handleBooks(ActionEvent event) {
		if (event.getSource().equals(addBookBtn)) {
			if (!addTitleTxt.getText().equals("") && !addAuthorTxt.getText().equals("")
					&& !addPublisherTxt.getText().equals("") && !addGenreTxt.getText().equals("")
					&& !addShelfTxt.getText().equals("")) {
				String title = addTitleTxt.getText();
				String author = addAuthorTxt.getText();
				String publisher = addPublisherTxt.getText();
				String genre = addGenreTxt.getText();
				String shelf = addShelfTxt.getText();
				
				//System.out.println("handlebooks: inside ifstatement 1");

				library.addBook(title, author, publisher, genre, shelf, 1, 0, "2017-12-12");
				//System.out.println("handlebooks: added to arraylists?");
				// Try writing new book to txt file
				try (PrintWriter out = new PrintWriter(
						new BufferedWriter(new FileWriter("res/availableBooks.txt", true)))) {
					out.println(addTitleTxt.getText() + "%" + addAuthorTxt.getText() + "%" + addPublisherTxt.getText()
							+ "%" + addGenreTxt.getText() + "%" + addShelfTxt.getText() + "%1%0%2017-12-12");
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}

				addedBookPrompt.setText("Added " + title);
				tableView.getItems().clear();
				try {
					tableView.setItems(library.bookDirectoryForGUI("availableBooks"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				addTitleTxt.clear();
				addAuthorTxt.clear();
				addPublisherTxt.clear();
				addGenreTxt.clear();
				addShelfTxt.clear();
			} else if (addTitleTxt.getText().equals("") || addAuthorTxt.getText().equals("")
					|| addPublisherTxt.getText().equals("") || addGenreTxt.getText().equals("")
					|| addShelfTxt.getText().equals("")) {
				addedBookPrompt.setText("Please fill all fields");
			}
			System.out.println("add button is connected");
		}

		if (event.getSource().equals(removeBookBtn)) {

		}

		if (event.getSource().equals(borrowBookBtn)) {	//BORROW BOOK// DOESNT QUITE WORK- if we add mutlipe books to list, no all get added
			today = LocalDate.now();
			int amountOfBooks = borrowRemoveBookTableData.size();
			for(int i = 0; i < borrowRemoveBookTableData.size(); i++) {
				TablePosition pos = tableView.getSelectionModel().getSelectedCells().get(0);
				int row = pos.getRow();
				TableColumn col = pos.getTableColumn();		
				Book book = tableView.getItems().get(row);
				String titleData = book.getTitle();
				String authorData = book.getAuthors();
				int availableCopies = book.getNumOfCopies();
				System.out.println("Title: "+ titleData);
				String psn = psnTextField.getText();	
				book.setReturnDate(LocalDate.now().plusDays(14));
				book.setReturnDateString(book.getReturnDate().format(library.getFormatter()));
				if (!library.listContainsCurrentBooks(borrowRemoveBookTableData, psn) &&library.getBookTitle(titleData) && library.getCustomerPSN(psn) && availableCopies>0 && !library.inCustomerCurrentLoans(psn, titleData, authorData)) {
					try {		
						//removeLineFromFile("res/availableBooks.txt", parseBookToString(book));
						//writeListToFile("res/loanedBooks.txt", borrowRemoveBookTableData, psn);
						writeListToFile("res/customerCurrentLoans/" + psn + "CurrentLoans.txt", borrowRemoveBookTableData, psn);
						writeListToFile("res/customerLoanHistory/" + psn + "LoanHistory.txt", borrowRemoveBookTableData, psn);
						library.changeCopiesAndTimesBorrowed(titleData, true);
						System.out.println("Wrote to files");
//						book.setReturnDate(today.plusDays(14)); //need??? doesnt get saved
//						book.setReturnDateString(today.plusDays(14).toString()); //not sure this will work!!
					//	library.borrowBook(titleData, psn); //doesnt finish			
						System.out.println("Added to arraylists");
						
						//move these elsewhere??
						
						borrowRemoveBookTableData.clear();
						
						borrowBookLbl.setText("Borrowed " + amountOfBooks + " books. Please return on: " + today.plusDays(14));
					
					} catch (Exception e) {
						System.out.println("Not able to write to file or write to arraylist etc");
					}			
				} else if(availableCopies==0) {
					borrowBookLbl.setText("Borrowing didnt work. Unsufficient copies available.");
				}
				else {
					psnTextField.clear();
					borrowRemoveBookTableData.clear();
					System.out.println("borrowing " + titleData + " didnt work");
					borrowBookLbl.setText("borrowing didnt work, unsufficent copies or already borrowed");
				}
			}
			try {
				psnTextField.clear();
				tableView.getItems().clear();
				tableView.setItems(library.bookDirectoryForGUI("availableBooks"));
				filteredData = new FilteredList<>(library.bookDirectoryForGUI("availableBooks"), p -> true);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			//System.out.println("borrowed a book (not yet)");
		}
	}

	// ON-CLICK EVENTS
	public void clickOnItem(MouseEvent e) {
		// if (e.getClickCount() == 1) //Checking 1 click
		// {
		// bookToRemoveTxt.setText(titleCol.getText());
		// borrowBookTitleTextField.setText(tableView.getSelectionModel().getSelectedItem().getTitle());
		// }
	}

	//INITIALIZING
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
		publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
		genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
		shelfCol.setCellValueFactory(new PropertyValueFactory<>("shelf"));
		numOfCopiesCol.setCellValueFactory(new PropertyValueFactory<>("numOfCopies"));
		timesBorrowedCol.setCellValueFactory(new PropertyValueFactory<>("timesBorrowed"));

		borrowTblTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		borrowTblAuthorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
		borrowTblPublisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
		borrowTblGenreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
		borrowTblShelfCol.setCellValueFactory(new PropertyValueFactory<>("shelf"));
//		numOfCopiesCol.setCellValueFactory(new PropertyValueFactory<>("numOfCopies"));
//		timesBorrowedCol.setCellValueFactory(new PropertyValueFactory<>("timesBorrowedCol"));

		removeTblTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		removeTblAuthorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
		removeTblPublisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
		removeTblGenreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
		removeTblShelfCol.setCellValueFactory(new PropertyValueFactory<>("shelf"));
//		numOfCopiesCol.setCellValueFactory(new PropertyValueFactory<>("numOfCopies"));
//		timesBorrowedCol.setCellValueFactory(new PropertyValueFactory<>("timesBorrowedCol"));

		// MAKE searching POSSIBLE
		try {
			library.addToArrayList(tableData);
			//library.addToArrayList(delayedBooksTableData, "delayedBooks"); //need???

			tableView.setItems(library.bookDirectoryForGUI("availableBooks")); //work or no?
			filteredData = new FilteredList<>(library.bookDirectoryForGUI("availableBooks"), p -> true);

			searchBookTextField.textProperty().addListener((observable, oldValue, newValue) -> {
				filteredData.setPredicate(book -> {

					if (newValue == null || newValue.isEmpty()) {
						return true;
					}

					String lowerCaseFilter = newValue.toLowerCase();

					if (book.getTitle().toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches title.
					} else if (book.getAuthor().toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches author.
					} else if (book.getPublisher().toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches publisher.
					} else if (book.getGenre().toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches genre.
					} else if (book.getShelf().toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches shelf.
					}
					return false; // Does not match.
				});

				SortedList<Book> sortedData = new SortedList<>(filteredData);
				sortedData.comparatorProperty().bind(tableView.comparatorProperty());

				tableView.setItems(sortedData);
			});
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// TRANSFER (COPY) TABLEROWS TO OTHER TABLEVIEWS
		tableView.setRowFactory(tv -> {
			TableRow<Book> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					borrowBookLbl.setText("");
					psnTextField.setText("");
					Book rowData = row.getItem();
					borrowRemoveBookTableData.add(rowData);
					System.out.println(rowData);
					borrowTableView.setItems(borrowRemoveBookTableData);
					removeTableView.setItems(borrowRemoveBookTableData);
				}
			});
			return row;
		});

		borrowTableView.setRowFactory(tv -> {
			TableRow<Book> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					Book rowData = row.getItem();
					borrowRemoveBookTableData.remove(rowData);
					borrowTableView.setItems(borrowRemoveBookTableData);
					removeTableView.setItems(borrowRemoveBookTableData);
				}
			});
			return row;
		});

		removeTableView.setRowFactory(tv -> {
			TableRow<Book> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					Book rowData = row.getItem();
					borrowRemoveBookTableData.remove(rowData);
					borrowTableView.setItems(borrowRemoveBookTableData);
					removeTableView.setItems(borrowRemoveBookTableData);
				}
			});
			return row;
		});
	}

	// where to put these methods that i took from test??
//	public void writeBookToFile(String path, Book book) {
//		if (!book.getTitle().equals("") && !book.getAuthor().equals("") && !book.getPublisher().equals("")
//				&& !book.getGenre().equals("") && !book.getShelf().equals("")) {
//			try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)))) {
//				out.println(book.getTitle() + "%" + book.getAuthor() + "%" + book.getPublisher() + "%" + book.getGenre()
//						+ "%" + book.getShelf() + "%" + book.getNumOfCopies() + "%" + book.getTimesBorrowed() + "%" + book.getReturnDateString());
//			} catch (IOException ioe) {
//				ioe.printStackTrace();
//			}
//			// System.out.println("In Write book to file: Added " + book.getTitle() + " to
//			// library");
//		} else {
//			System.out.println("In write book to file: No parameters allowed to be empty");
//		}
//	}
	
	public void writeListToFile(String path, ObservableList<Book> list, String psn) {
		today = LocalDate.now();
		for (int j = 0; j < list.size(); j++) {
			if (!list.get(j).getTitle().equals("") && !list.get(j).getAuthor().equals("") && !list.get(j).getPublisher().equals("")
					&& !list.get(j).getGenre().equals("") && !list.get(j).getShelf().equals("")) {
				
				try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)))) {
					out.println(list.get(j).getTitle() + "%" + list.get(j).getAuthor() + "%" + list.get(j).getPublisher() + "%" + list.get(j).getGenre()
							+ "%" + list.get(j).getShelf() + "%" + list.get(j).getNumOfCopies() + "%" + list.get(j).getTimesBorrowed() + "%" + list.get(j).getReturnDateString());
				
					try {
						library.borrowBook(list.get(j).getTitle(), psn);
						list.get(j).setReturnDate(today.plusDays(14)); //need??? doesnt get saved
						list.get(j).setReturnDateString(today.plusDays(14).toString()); //not sure this will work!!
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
				// System.out.println("In Write book to file: Added " + book.getTitle() + " to
				// library");
			} else {
				System.out.println("In write book to file: No parameters allowed to be empty");
			}
		}
		
	}
	
	public void showBooks(String bookList) throws FileNotFoundException {
//		titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
//		authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
//		publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
//		genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
//		shelfCol.setCellValueFactory(new PropertyValueFactory<>("shelf"));
//		numOfCopiesCol.setCellValueFactory(new PropertyValueFactory<>("numOfCopies"));
//		timesBorrowedCol.setCellValueFactory(new PropertyValueFactory<>("timesBorrowed"));
		tableView.getItems().clear();
		tableView.setItems(library.bookDirectoryForGUI(bookList));
	}

}
