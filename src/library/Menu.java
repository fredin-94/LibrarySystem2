package library;

public class Menu {

	public void getMenu() {
		System.out.println(
		  " =================================================== \n"
		+ " ==             Library System                    == \n"
		+ " =================================================== \n"
		+ " ===                                             === \n"
		+ " ===   Choose an option below:                   === \n"
		+ " ===   1. Search library directory               === \n"
		+ " ===   2. Borrow/Return books                    === \n"
		+ " ===   3. Administartion                         === \n"
		+ " ===   4. Time Simulator FX40000                 === \n"
		+ " ===   0. Quit program                           === \n"
		+ " ===                                             === \n"
		+ " =================================================== \n");
	}
	
	public void getSearch() {
		System.out.println(
		  " =================================================== \n"
		+ " ==             Search System                     == \n"
		+ " =================================================== \n"
		+ " ===                                             === \n"
		+ " ===   Choose an option below:                   === \n"
		+ " ===   1. Search for book                        === \n"
		+ " ===   2. Show and sort books                    === \n"
		+ " ===   3. Search for customers                   === \n"
		+ " ===   4. Show all customers                     === \n"
		+ " ===   0. Return to main menu                    === \n"
		+ " ===                                             === \n"
		+ " =================================================== \n");
	}
	
	public void getBookOptions() {
		System.out.println(
		  " =================================================== \n"
		+ " ==             Borrow or return books            == \n"
		+ " =================================================== \n"
		+ " ===                                             === \n"
		+ " ===   Choose an option below:                   === \n"
		+ " ===   1. Borrow book                            === \n"
		+ " ===   2. Return book                            === \n"
		+ " ===   0. Return to main menu                    === \n"
		+ " ===                                             === \n"
		+ " =================================================== \n");
	}
	
	public void getAdministration() {
		System.out.println(
		  " =================================================== \n"
		+ " ==             Administration System             == \n"
		+ " =================================================== \n"
		+ " ===                                             === \n"
		+ " ===   Choose an option below:                   === \n"
		+ " ===   1. Add Book                               === \n"
		+ " ===   2. Remove Book                            === \n"
		+ " ===   3. Add Customer                           === \n"
		+ " ===   4. Remove Customer                        === \n"
		+ " ===   0. Return to main menu                    === \n"
		+ " ===                                             === \n"
		+ " =================================================== \n");
	}
	
	public void getSimulator() {
		System.out.println(
		  " =================================================== \n"
		+ " ==             Simulator System                  == \n"
		+ " =================================================== \n"
		+ " ===                                             === \n"
		+ " ===   Choose an option below:                   === \n"
		+ " ===   1. Increment Days                         === \n"
		+ " ===   2. Increment Months                       === \n"
		+ " ===   3. Increment Years                        === \n"
		+ " ===   0. Return to main menu                    === \n"
		+ " ===                                             === \n"
		+ " =================================================== \n");
	}
}
