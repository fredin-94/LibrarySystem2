package library;

public class Menu {

	public void getMenu() {
		System.out.println(
		"\n =================================================== "
		+ " ==             Library System                    == "
		+ " =================================================== "
		+ " ===                                             === "
		+ " ===   Choose an option below:                   === "
		+ " ===   1. Search library directory               === "
		+ " ===   2. Borrow/Return books                    === "
		+ " ===   3. Administartion                         === "
		+ " ===   4. Time Simulator FX40000                 === "
		+ " ===   Press Q to quit                           === "
		+ " ===                                             === "
		+ " =================================================== \n");
	}
	
	public void getSearch() {
		System.out.println(
		"\n =================================================== "
		+ " ==             Search System                     == "
		+ " =================================================== "
		+ " ===                                             === "
		+ " ===   Choose an option below:                   === "
		+ " ===   1. Search for book                        === "
		+ " ===   2. Show and sort books                    === "
		+ " ===   3. Search for customers                   === "
		+ " ===   4. Show all customers                     === "
		+ " ===   0. Quit search                            === "
		+ " ===                                             === "
		+ " =================================================== \n");
	}
	
	public void getBookOptions() {
		System.out.println(
		"\n =================================================== "
		+ " ==             Borrow or return books            == "
		+ " =================================================== "
		+ " ===                                             === "
		+ " ===   Choose an option below:                   === "
		+ " ===   1. Borrow book                            === "
		+ " ===   2. Return book                            === "
		+ " ===   0. Quit search                            === "
		+ " ===                                             === "
		+ " =================================================== \n");
	}
	
	public void getAdministration() {
		System.out.println(
		"\n =================================================== "
		+ " ==             Administration System             == "
		+ " =================================================== "
		+ " ===                                             === "
		+ " ===   Choose an option below:                   === "
		+ " ===   1. Add Book                               === "
		+ " ===   2. Remove Book                            === "
		+ " ===   3. Add Customer                           === "
		+ " ===   4. Remove Customer                        === "
		+ " ===   Press Q to quit                           === "
		+ " ===                                             === "
		+ " =================================================== \n");
	}
	
	public void getSimulator() {
		System.out.println(
		"\n =================================================== "
		+ " ==             Simulator System                  == "
		+ " =================================================== "
		+ " ===                                             === "
		+ " ===   Choose an option below:                   === "
		+ " ===   1. Increment Days                         === "
		+ " ===   2. Increment Months                       === "
		+ " ===   3. Increment Years                        === "
		+ " ===   Press Q to quit                           === "
		+ " ===                                             === "
		+ " =================================================== \n");
	}
}
