package library;

public class Menu {

	public void getWelcomeScreen() {
		System.out.println();
		System.out.println(" =================================================== ");
		System.out.println(" ==               Welcome to                      == ");
		System.out.println(" ==         The Royal Oxford Library              == ");
		System.out.println(" ==                                               == ");
		System.out.println(" ==            Established 1817                   == ");
		System.out.println(" =================================================== ");
		System.out.println();
	}
	
	public void getMenu() {
		System.out.println();
		System.out.println(" =================================================== ");
		System.out.println(" ==             Library System                    == ");
		System.out.println(" =================================================== ");
		System.out.println(" ===                                             === ");
		System.out.println(" ===   Choose an option below:                   === ");
		System.out.println(" ===   1. Search library directory               === ");
		System.out.println(" ===   2. Borrow or return books                 === ");
		System.out.println(" ===   3. Administration                         === ");
		System.out.println(" ===   4. Extra                                  === ");
		System.out.println(" ===   5. Time Simulator FX40000                 === ");
		System.out.println(" ===   0. Quit program                           === ");
		System.out.println(" ===                                             === ");
		System.out.println(" =================================================== ");
		System.out.println();
	}
	
	public void getSearch() {
		System.out.println(
				"\n =================================================== "+ System.lineSeparator()
				+ " ==             Search System                     == "+ System.lineSeparator()
				+ " =================================================== "+ System.lineSeparator()
              	+ " ===                                             === "+ System.lineSeparator()
             	+ " ===   Choose an option below:                   === "+ System.lineSeparator()
              	+ " ===   1. Search for book                        === "+ System.lineSeparator()
              	+ " ===   2. Search for customer                    === "+ System.lineSeparator()
             	+ " ===   3. Print all available books              === "+ System.lineSeparator()
             	+ " ===   4. Print all customers                    === "+ System.lineSeparator()
				+ " ===   5. Show customer loan history             === "+ System.lineSeparator()
				+ " ===   0. Return to main menu                    === "+ System.lineSeparator()
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
		+ " ===   0. Return to main menu                    === "+ System.lineSeparator()
		+ " ===                                             === "+ System.lineSeparator()
		+ " =================================================== \n");
	}
	
	public void bookOption() {
		System.out.println();
		System.out.println(" =================================================== ");
		System.out.println(" ==             Book Search System                == ");
		System.out.println(" =================================================== ");
		System.out.println(" ===                                             === ");
		System.out.println(" ===   Search by:                                === ");
		System.out.println(" ===   1. Title                                  === ");
		System.out.println(" ===   2. Id                                     === ");
		System.out.println(" =================================================== ");
		System.out.println();
	}
	
	public void customerOption() {
		System.out.println();
		System.out.println(" =================================================== ");
		System.out.println(" ==             Customer Search System            == ");
		System.out.println(" =================================================== ");
		System.out.println(" ===                                             === ");
		System.out.println(" ===   Search by:                                === ");
		System.out.println(" ===   1. Name                                   === ");
		System.out.println(" ===   2. Id                                     === ");
		System.out.println(" =================================================== ");
		System.out.println();
	}
	
	public void searchBookByTitle() {
		System.out.println();
		System.out.println(" =================================================== ");
		System.out.println(" ==             Book Search System                == ");
		System.out.println(" =================================================== ");
		System.out.println(" ===                                             === ");
		System.out.println(" ===   Enter title below:                        === ");
		System.out.println(" ===                                             === ");
		System.out.println(" =================================================== ");
		System.out.println();
	}
	
	public void searchBookById() {
		System.out.println();
		System.out.println(" =================================================== ");
		System.out.println(" ==             Book Search System                == ");
		System.out.println(" =================================================== ");
		System.out.println(" ===                                             === ");
		System.out.println(" ===   Enter book id below:                      === ");
		System.out.println(" ===                                             === ");
		System.out.println(" =================================================== ");
		System.out.println();
	}
	
	public void searchCustomerByName() {
		System.out.println();
		System.out.println(" =================================================== ");
		System.out.println(" ==             Customer Search System            == ");
		System.out.println(" =================================================== ");
		System.out.println(" ===                                             === ");
		System.out.println(" ===   Enter customer name below:                === ");
		System.out.println(" ===                                             === ");
		System.out.println(" =================================================== ");
		System.out.println();
	}
	
	public void searchCustomerById() {
		System.out.println();
		System.out.println(" =================================================== ");
		System.out.println(" ==             Customer Search System            == ");
		System.out.println(" =================================================== ");
		System.out.println(" ===                                             === ");
		System.out.println(" ===   Enter customer id below:                  === ");
		System.out.println(" ===                                             === ");
		System.out.println(" =================================================== ");
		System.out.println();
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

	
	public void addAuthor() {
		System.out.println();
		System.out.println(" =================================================== ");
		System.out.println(" ==             Book Creation                     == ");
		System.out.println(" =================================================== ");
		System.out.println(" ===                                             === ");
		System.out.println(" ===   Do you want to add another author:        === ");
		System.out.println(" ===   1. Yes                                    === ");
		System.out.println(" ===   2. No                                     === ");
		System.out.println(" ===                                             === ");
		System.out.println(" =================================================== ");
		System.out.println();
	}
	
	public void removeBookOption() {
		System.out.println();
		System.out.println(" =================================================== ");
		System.out.println(" ==             Book Search System                == ");
		System.out.println(" =================================================== ");
		System.out.println(" ===                                             === ");
		System.out.println(" ===   Search by:                                === ");
		System.out.println(" ===   1. Title                                  === ");
		System.out.println(" ===   2. Id                                     === ");
		System.out.println(" =================================================== ");
		System.out.println();
	}
	
	public void removeCustomerOption() {
		System.out.println();
		System.out.println(" =================================================== ");
		System.out.println(" ==             Customer Search System            == ");
		System.out.println(" =================================================== ");
		System.out.println(" ===                                             === ");
		System.out.println(" ===   Search by:                                === ");
		System.out.println(" ===   1. Name                                   === ");
		System.out.println(" ===   2. Id                                     === ");
		System.out.println(" =================================================== ");
		System.out.println();
	}
	
	public void removeBookByTitle() {
		System.out.println();
		System.out.println(" =================================================== ");
		System.out.println(" ==             Book Removal System               == ");
		System.out.println(" =================================================== ");
		System.out.println(" ===                                             === ");
		System.out.println(" ===   Enter title below:                        === ");
		System.out.println(" ===                                             === ");
		System.out.println(" =================================================== ");
		System.out.println();
	}
	
	public void removeBookById() {
		System.out.println();
		System.out.println(" =================================================== ");
		System.out.println(" ==             Book Removal System               == ");
		System.out.println(" =================================================== ");
		System.out.println(" ===                                             === ");
		System.out.println(" ===   Enter book id below:                      === ");
		System.out.println(" ===                                             === ");
		System.out.println(" =================================================== ");
		System.out.println();
	}
	
	public void removeCustomerByName() {
		System.out.println();
		System.out.println(" =================================================== ");
		System.out.println(" ==             Customer Removal System           == ");
		System.out.println(" =================================================== ");
		System.out.println(" ===                                             === ");
		System.out.println(" ===   Enter customer name below:                === ");
		System.out.println(" ===                                             === ");
		System.out.println(" =================================================== ");
		System.out.println();
	}
	
	public void removeCustomerById() {
		System.out.println();
		System.out.println(" =================================================== ");
		System.out.println(" ==             Customer Removal System           == ");
		System.out.println(" =================================================== ");
		System.out.println(" ===                                             === ");
		System.out.println(" ===   Enter customer id below:                  === ");
		System.out.println(" ===                                             === ");
		System.out.println(" =================================================== ");
		System.out.println();
	}
	
	public void getSimulator() {
		System.out.println();
		System.out.println(" =================================================== ");
		System.out.println(" ==             Simulator System                  == ");
		System.out.println(" =================================================== ");
		System.out.println(" ===                                             === ");
		System.out.println(" ===   Choose an option below:                   === ");
		System.out.println(" ===   1. Increment Days                         === ");
		System.out.println(" ===   2. Increment Weeks                        === ");
		System.out.println(" ===   3. Increment Months                       === ");
		System.out.println(" ===   4. Increment Years                        === ");
		System.out.println(" ===   0. Return to main menu                    === ");
		System.out.println(" ===                                             === ");
		System.out.println(" =================================================== ");
		System.out.println();
	}
	
	public void getExtra() {
		System.out.println();
		System.out.println(" =================================================== ");
		System.out.println(" ==             Extra                             == ");
		System.out.println(" =================================================== ");
		System.out.println(" ===                                             === ");
		System.out.println(" ===   Choose an option below:                   === ");
		System.out.println(" ===   1. Show all lent out books                === ");
		System.out.println(" ===   2. Show all delayed books                 === ");
		System.out.println(" ===   3. Show users loan history                === ");
		System.out.println(" ===   4. Show top 10 most popular books         === ");
		System.out.println(" ===   0. Return to main menu                    === ");
		System.out.println(" ===                                             === ");
		System.out.println(" =================================================== ");
		System.out.println();
	}
}
