package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LibraryController  implements EventHandler<ActionEvent>{
	
	@FXML
	private Button returnToMainBtn;
	
	@Override
	public void handle(ActionEvent event) {
			
		if(event.getSource().equals(returnToMainBtn)) {
			System.out.println("DANK");
			
			try {
				System.out.println("sortborrow-return");
				Parent mainMenuParent = FXMLLoader.load(getClass().getResource("/gui/mainMenu.fxml"));
				Scene mainMenuScene = new Scene(mainMenuParent);
				Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
				primaryStage.setScene(mainMenuScene);
				primaryStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
}
