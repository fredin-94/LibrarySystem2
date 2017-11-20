package library;

public class Menu {

    public void getMainMenu() {
        System.out.println("\n =================================================== " + System.lineSeparator()
                + " ==             Library System                    == " + System.lineSeparator()
                + " =================================================== " + System.lineSeparator()
                + " ===                                             === " + System.lineSeparator()
                + " ===   Choose an option below:                   === " + System.lineSeparator()
                + " ===   1. Search library directory               === " + System.lineSeparator()
                + " ===   2. Borrow/Return books                    === " + System.lineSeparator()
                + " ===   3. Administartion                         === " + System.lineSeparator()
                + " ===   4. Time Simulator FX40000                 === " + System.lineSeparator()
                + " ===   Press Q to quit                           === " + System.lineSeparator()
                + " ===                                             === " + System.lineSeparator()
                + " =================================================== \n");
    }
	
	public void getSearch() {
		System.out.println("\n =================================================== "+ System.lineSeparator()
				+ " ==             Search System                     == "+ System.lineSeparator()
				+ " =================================================== "+ System.lineSeparator()
            	+ " ===                                             === "+ System.lineSeparator()
            	+ " ===   Choose an option below:                   === "+ System.lineSeparator()
            	+ " ===   1. Search for book                        === "+ System.lineSeparator()
            	+ " ===   2. Show and sort books                    === "+ System.lineSeparator()
            	+ " ===   3. Search for customers                   === "+ System.lineSeparator()
            	+ " ===   4. Show all customers                     === "+ System.lineSeparator()
				+ " ===   5. Show all books                         === "+ System.lineSeparator()
				+ " ===   6. Show customer search history           === "+ System.lineSeparator()
            	+ " ===   0. Quit search                            === "+ System.lineSeparator()
            	+ " ===                                             === "+ System.lineSeparator()
            	+ " =================================================== \n");
	}
	
	public void getBookOptions() {
		System.out.println(
		"\n =================================================== "+ System.lineSeparator()
		+ " ==             Borrow or return books            == "+ System.lineSeparator()
		+ " =================================================== "+ System.lineSeparator()
		+ " ===                                             === "+ System.lineSeparator()
		+ " ===   Choose an option below:                   === "+ System.lineSeparator()
		+ " ===   1. Borrow book                            === "+ System.lineSeparator()
		+ " ===   2. Return book                            === "+ System.lineSeparator()
		+ " ===   0. Quit search                            === "+ System.lineSeparator()
		+ " ===                                             === "+ System.lineSeparator()
		+ " =================================================== \n");
	}
	
	public void getAdministration() {
		System.out.println(
		"\n =================================================== "+ System.lineSeparator()
		+ " ==             Administration System             == "+ System.lineSeparator()
		+ " =================================================== "+ System.lineSeparator()
		+ " ===                                             === "+ System.lineSeparator()
		+ " ===   Choose an option below:                   === "+ System.lineSeparator()
		+ " ===   1. Add Book                               === "+ System.lineSeparator()
		+ " ===   2. Remove Book                            === "+ System.lineSeparator()
		+ " ===   3. Add Customer                           === "+ System.lineSeparator()
		+ " ===   4. Remove Customer                        === "+ System.lineSeparator()
		+ " ===   Press Q to quit                           === "+ System.lineSeparator()
		+ " ===                                             === "+ System.lineSeparator()
		+ " =================================================== \n");
	}
	
	public void getSimulator() {
		System.out.println(
		"\n =================================================== "+ System.lineSeparator()
		+ " ==             Simulator System                  == "+ System.lineSeparator()
		+ " =================================================== "+ System.lineSeparator()
		+ " ===                                             === "+ System.lineSeparator()
		+ " ===   Choose an option below:                   === "+ System.lineSeparator()
		+ " ===   1. Increment Days                         === "+ System.lineSeparator()
		+ " ===   2. Increment Months                       === "+ System.lineSeparator()
		+ " ===   3. Increment Years                        === "+ System.lineSeparator()
		+ " ===   Press Q to quit                           === "+ System.lineSeparator()
		+ " ===                                             === "+ System.lineSeparator()
		+ " =================================================== \n");
	}
}
