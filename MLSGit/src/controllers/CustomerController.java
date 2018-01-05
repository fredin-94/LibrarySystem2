package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import library.Book;
import library.Customer;
import library.Library;

public class CustomerController implements EventHandler<ActionEvent>, Initializable {

	Library library = new Library();
	// Customer customer = new customer();
	// THIS IS THE CUSTOMER CONTROLLER GETTING UPDATED LIVE
	@FXML
	private Button returnToMainBtn, showCustomerBtn, addCustomerBtn, removeCustomerBtn, returnBooksBtn;
	@FXML
	private TableView<Customer> tableView;
	@FXML
	private TableView<Book> currentLoansTableView, loanHistoryTableView;
	@FXML
	private TableColumn<Customer, String> nameCol, addressCol, phoneCol, psnCol, debtCol;
	@FXML
	private TableColumn<Book, String> loanHistoryTitleCol, loanHistoryAuthorCol, loanHistoryGenreCol,
			loanHistoryPublisherCol, loanHistoryShelfCol, loanHistoryReturnDateCol, CurrentBTitleCol, CurrentBAuthorCol,
			CurrentBPublisherCol, CurrentBGenreCol, CurrentBShelfCol, CurrentBReturnDateCol;
	@FXML
	private TextField searchCustomerTxt, nameTxt, addressTxt, phoneTxt, psnTxt, psnTxt1;
	@FXML
	private Label addedCustomerLbl, returnBooksPrompt, paidDebtPrompt, totalDebtLbl;

	private ObservableList<Book> currentBooksTableData = FXCollections.observableArrayList();
	private ObservableList<Book> loanHistoryTableData = FXCollections.observableArrayList();

	// SWITCHING SCENES
	@Override
	public void handle(ActionEvent event) {

		if (event.getSource().equals(returnToMainBtn)) {
			System.out.println("returned");

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

//		if (event.getSource().equals(payDebtBtn) && !psnTxt.equals("")) {
//			try {
//				payDebt();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}

	// HANDLE WHAT TO SHOW AND WHEN
	public void handleShow(ActionEvent event) throws Exception {

		if (event.getSource().equals(showCustomerBtn)) {
//			nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
//			addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
//			phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
//			psnCol.setCellValueFactory(new PropertyValueFactory<>("psn"));
//			debtCol.setCellValueFactory(new PropertyValueFactory<>("debt"));

			tableView.setItems(library.customerDirectoryForGUI());

		}
	}

	// HANDLE ADDING AND REMOVING BOOKS TO LIST
	public void handleCustomers(ActionEvent event) throws Exception {
		if (event.getSource().equals(addCustomerBtn)) {
			if (!nameTxt.getText().equals("") && !addressTxt.getText().equals("") && !psnTxt1.getText().equals("")) {
				String name = nameTxt.getText();
				String address = addressTxt.getText();
				String psn = psnTxt1.getText();
				String phone = phoneTxt.getText();
				if (phone.equals("")) {
					phone = "   ";
				}

				// create the customer txt files
				createFile("customerCurrentLoans", psn + "CurrentLoans");
				createFile("customerLoanHistory", psn + "LoanHistory");

				// add the customer to the arraylist
				library.addCustomer(name, address, psn, phone, 0);
				System.out.println("in handlecustomers: added customer to arraylist?");

				// Try writing new book to txt file
				try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("res/customer.txt", true)))) {
					out.println(nameTxt.getText() + "/" + addressTxt.getText() + "/" + psnTxt1.getText() + "/"
							+ phoneTxt.getText() + "/0");

					System.out.println("In the try block, trying to write to txt file");
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
				addedCustomerLbl.setText("Added " + name);
				tableView.getItems().clear();
				tableView.setItems(library.customerDirectoryForGUI());
				nameTxt.clear();
				addressTxt.clear();
				psnTxt1.clear();
				phoneTxt.clear();

			} else if (nameTxt.getText().equals("") || addressTxt.getText().equals("") || psnTxt1.getText().equals("")
					|| psnTxt1.getText().length() < 9 || psnTxt1.getText().length() > 12) {
				addedCustomerLbl.setText("Please fill all fields with valid info");
			}
			System.out.println("add button is connected");
		}

		if (event.getSource().equals(removeCustomerBtn)) {

		}

	}

	public void handleBooks(ActionEvent event) throws Exception {
		if (event.getSource().equals(returnBooksBtn)) {
			returnBook();
		}

	}

	// ON-CLICK EVENTS
	public void clickOnItem(MouseEvent event) {
		if (event.getClickCount() == 2) // Checking double click
		{
			System.out.println(tableView.getSelectionModel().getSelectedItem().getName());
			// System.out.println(tableView.getSelectionModel().getSelectedItem().getBrewery());
			// System.out.println(tableView.getSelectionModel().getSelectedItem().getCountry());

			// bookToRemoveTxt.setText(tableView.getSelectionModel().getSelectedItem().getTitle());
			// borrowBookTitleTextField.setText(tableView.getSelectionModel().getSelectedItem().getTitle());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		psnCol.setCellValueFactory(new PropertyValueFactory<>("psn"));
		//debtCol.setCellValueFactory(new PropertyValueFactory<>("debt"));

		CurrentBTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		CurrentBAuthorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
		CurrentBGenreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
		CurrentBPublisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
		// CurrentBShelfCol.setCellValueFactory(new PropertyValueFactory<>("shelf"));
		CurrentBReturnDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDateString"));

		loanHistoryTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		loanHistoryAuthorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
		loanHistoryPublisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
		loanHistoryGenreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
		// loanHistoryShelfCol.setCellValueFactory(new PropertyValueFactory<>("shelf"));
		loanHistoryReturnDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDateString"));

		try {
			tableView.setItems(library.customerDirectoryForGUI());
			fillBookTables();

			// how to get this mess into a method??//
			FilteredList<Customer> filteredData = new FilteredList<>(library.customerDirectoryForGUI(), p -> true);
			searchCustomerTxt.textProperty().addListener((observable, oldValue, newValue) -> {
				filteredData.setPredicate(customer -> {

					if (newValue == null || newValue.isEmpty()) {
						return true;
					}

					String lowerCaseFilter = newValue.toLowerCase();

					if (customer.getName().toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches first name.
					} else if (customer.getAddress().toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches last name.
					} else if (customer.getPhoneNumber().toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches last name.
					} else if (customer.getPsn().toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches last name.
					}
					return false; // Does not match.
				});

				SortedList<Customer> sortedData = new SortedList<>(filteredData);
				sortedData.comparatorProperty().bind(tableView.comparatorProperty());

				tableView.setItems(sortedData);
			});
			/// end of searching mess//

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void createFile(String directory, String fileName) {
		// System.out.println("in createFile");
		try {
			File file = new File("res/" + directory + "/" + fileName + ".txt");
			if (file.createNewFile()) {
				// System.out.println("Text file is created!");
			} else {
				System.out.println("Text file for " + fileName + " already exists.");
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void returnBook() {
		TablePosition pos = tableView.getSelectionModel().getSelectedCells().get(0);
		int row = pos.getRow();
		TableColumn col = pos.getTableColumn();
		Customer customer = tableView.getItems().get(row);
		String psn = psnTxt.getText();
		System.out.println("Customer psn: " + psn);

		// TablePosition posCurrent =
		// currentLoansTableView.getSelectionModel().getSelectedCells().get(0);
		// int rowCurrent = pos.getRow();
		// TableColumn colCurrent = pos.getTableColumn();
		// Book book = currentLoansTableView.getItems().get(row);
		// String titleData = book.getTitle();

		if (!currentLoansTableView.equals(null) && !psn.equals("")) { // CHANGE the one for the tableview, it might be
																		// wrong
			// Book book = null;
			for (Customer customerInLibrary : library.getCustomers()) {// going into the customer arraylist
				if (customerInLibrary.getPsn().equals(psn)) {
					// book = customer.getFromCurrentLoan(titleData);

					for (int i = 0; i < customer.getCurrentBooks().size(); i++) {
						try {
							System.out.println(customer.getCurrentBooks().get(i).getTitle());
							library.removeLineFromFile("res/customerCurrentLoans/" + psn + "CurrentLoans.txt",
									library.parseBookToString(customer.getCurrentBooks().get(i)));
							library.changeCopiesAndTimesBorrowed(customer.getCurrentBooks().get(i).getTitle(), false); //dont know if it works
							//library.removeLineFromFile("res/LoanedBooks.txt",library.parseBookToString(customer.getCurrentBooks().get(i)));
							library.returnBook(customer.getCurrentBooks().get(i).getTitle(), psn);
							System.out.println("Book returned successfully");
							returnBooksPrompt.setText("Books returned successfully");
							totalDebtLbl.setText(Integer.toString(library.getCustomerDebt(psn)));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							// throw new Exception("Empty title or social security number");
						}
					}
					// customer2.getCurrentBooks().clear();
					currentBooksTableData.clear();
				}
			}
			// returns a book into library's available books directory
			// writeBookToFile("res/bookDirectory.txt", book);

			// System.out.println("In return book: removed book from loaned books arraylist,
			// added to books arraylist, removed from customer current loans

		} else {
			System.out.println("controller, return book: Either title or psn is empty");
		}
	}

	public void fillBookTables() {
		// WHEN PRESSING CUSTOMER 2 times to add all customers current books to
		// tableview, and loan history
		tableView.setRowFactory(tv -> {
			TableRow<Customer> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					currentLoansTableView.getItems().clear();
					loanHistoryTableView.getItems().clear();
					TablePosition pos = tableView.getSelectionModel().getSelectedCells().get(0);
					int row2 = pos.getRow();
					TableColumn col = pos.getTableColumn();
					Customer customer = tableView.getItems().get(row2);
					String psn = customer.getPsn();
					// System.out.println("Title: "+ titleData);
					psnTxt.setText(psn);

					// Customer rowData = row.getItem();
					for (int i = 0; i < library.getCustomers().size(); i++) {
						if (library.getCustomers().get(i).getPsn().equals(psn)) {
							// currentBooksTableData.addAll(library.getCustomers().get(i).getCurrentBooks());
							try {
								System.out.println("in the try now");
								library.getCustomers().get(i).addToArrayList(currentBooksTableData,
										psn + "currentLoans");
								library.getCustomers().get(i).addToArrayList(loanHistoryTableData, psn + "loanHistory");
								currentLoansTableView.setItems(currentBooksTableData);
								loanHistoryTableView.setItems(loanHistoryTableData);
								totalDebtLbl.setText(Integer.toString(library.getCustomerDebt(psn)));
								System.out.println("end of the try");
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
						}
					}
					// System.out.println(rowData);
					// currentLoansTableView.setItems(currentBooksTableData);
				}
			});
			return row;
		});
	}

	public void searchCustomer() {

	}

//	public void payDebt() throws Exception {
//		String psn = psnTxt.getText();
//		for (int i = 0; i < library.getCustomers().size(); i++) {
//			if (psn.equals(library.getCustomers().get(i).getPsn()) && !psn.equals("") && library.getCustomers().get(i).getDebt()>0) {
//				
//				// library.getCustomerObs().get(i).setDebt(0);
//				library.removeLineFromFile("res/customer.txt", library.parseCustomerToString(library.getCustomers().get(i)));
//				library.getCustomers().get(i).setDebt(0);
//				library.writeCustomerToFile("res/customer.txt", library.getCustomers().get(i));
//				totalDebtLbl.setText(Integer.toString(library.getCustomers().get(i).getDebt()));
//				paidDebtPrompt.setText("Customer paid their debt");
//				tableView.getItems().clear();
//				tableView.setItems(library.customerDirectoryForGUI());
//			}
//		}
//	}

	//this probably WONT WORK if 2 people have the SAME AMOUNT OF DEBT!
	//its no good, it replaces all numbers it finds, so tex all 2's will become 0 and mess up programp
	
}
