package gui;

import controllers.MainMenuController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MainMenu extends Application {

	// ATTRIBUTES//
	Scene mainMenuScene;
	
	MainMenuController mainController = new MainMenuController();

	/// METHODS///
	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Library System");

		// layouts
		Parent mainMenu = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));

		//primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("libIcon2.png")));
		primaryStage.getIcons().add(new Image("libIcon.png"));
		// scenes
		mainMenuScene = new Scene(mainMenu);
		primaryStage.setScene(mainMenuScene);
		primaryStage.centerOnScreen();
		primaryStage.show();
		
		

		// this is for getting style sheet for use later
		// scene.getstylesheets().add(getclass().getrescourse("sheet.css").toexternalform())
	}
}
