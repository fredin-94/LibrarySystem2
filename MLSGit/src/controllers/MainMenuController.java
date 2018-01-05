package controllers;

import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import gui.MainMenu;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import library.Book;
import library.Library;

public class MainMenuController implements EventHandler<ActionEvent>, Initializable {
	//Library library = new Library();
	//MainMenu mainm = new MainMenu();
	// connects fxml file to its controller
	private Library library = new Library();
	@FXML
	private Button booksBtn, customerBtn, libraryBtn;
	@FXML
	private Label currentTime;
	
//	public MainMenuController() {
//		displayTime();
//	}
	
	@Override
	public void handle(ActionEvent event) {

		try {
			if (event.getSource().equals(booksBtn)) {
				System.out.println("books");
				Parent mainMenuParent;
				Stage primaryStage;
				Scene mainMenuScene;
				mainMenuParent = FXMLLoader.load(getClass().getResource("/gui/sortShowAll.fxml"));
				mainMenuScene = new Scene(mainMenuParent);
				primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				primaryStage.setScene(mainMenuScene);
				primaryStage.show();
			}
			else if (event.getSource().equals(customerBtn)) {
				System.out.println("customer");
				Parent mainMenuParent;
				Stage primaryStage;
				Scene mainMenuScene;
				mainMenuParent = FXMLLoader.load(getClass().getResource("/gui/customerRegistry.fxml"));
				// HBox mainMenuParent = new HBox();
				mainMenuScene = new Scene(mainMenuParent);
				primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				primaryStage.setScene(mainMenuScene);
				primaryStage.show();
			}
			else if (event.getSource().equals(libraryBtn)) {
				System.out.println("library");
				Parent mainMenuParent;
				Stage primaryStage;
				Scene mainMenuScene;
				mainMenuParent = FXMLLoader.load(getClass().getResource("/gui/libraryFunctions.fxml"));
				// HBox mainMenuParent = new HBox();
				mainMenuScene = new Scene(mainMenuParent);
				primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				primaryStage.setScene(mainMenuScene);
				primaryStage.show();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void displayTime() { //fix this
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate now = LocalDate.now();
		currentTime.setText("Todays date is: " + now);
		//currentTime.setText("work!!");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		displayTime();
//		Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {            
//	        Calendar cal = Calendar.getInstance();
//	        int second = cal.get(Calendar.SECOND);
//	        int minute = cal.get(Calendar.MINUTE);
//	        int hour = cal.get(Calendar.HOUR);
//	        //System.out.println(hour + ":" + (minute) + ":" + second);
//	        currentTime.setText(hour + ":" + (minute) + ":" + second);
//	    }),
//	         new KeyFrame(Duration.seconds(1))
//	    );
//	    clock.setCycleCount(Animation.INDEFINITE);
//	    clock.play();
	}
}
