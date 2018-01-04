package library;

import java.io.FileNotFoundException;
import java.util.Scanner;

import controllers.MainMenuController;
import gui.MainMenu;

public class MainLib {
	
	Scanner scanner = new Scanner(System.in);
	int quit = scanner.nextInt();

	public static void main(String[] args) throws Exception {
		Library library = new Library();
		//MainMenuController mainMenuController = new MainMenuController();
		//library.bookDirectory();
		//library.customerDirectory();
		
//		 MainLib main = new MainLib();
//         main.text();
		
		new Thread() {   
            
            @Override
            public void run() {   		
                javafx.application.Application.launch(MainMenu.class);
               
            }
        }.start();

	}
	
//	public void text() {
//		System.out.println("running");
//	}
	


}
