package library;

public class Menu {

	public void getWelcomeScreen() {
		System.out.println();
		System.out.println(" =================================================== ");
		System.out.println(" ==               Welcome to                      == ");
		System.out.println(" ==             FooBar Library                    == ");
		System.out.println(" ==                                               == ");
		System.out.println(" ==      established [2017]                       == ");
		System.out.println(" =================================================== ");
		System.out.println();
	}

	public void getMenu() {
		System.out.println(" =================================================== \n"
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
		System.out.println(" =================================================== \n"
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
		System.out.println();
		System.out.println(" =================================================== ");
		System.out.println(" ==             Administration System             == ");
		System.out.println(" =================================================== ");
		System.out.println(" ===                                             === ");
		System.out.println(" ===   Choose an option below:                   === ");
		System.out.println(" ===   1. Add Book                               === ");
		System.out.println(" ===   2. Remove Book                            === ");
		System.out.println(" ===   3. Add User                               === ");
		System.out.println(" ===   4. Remove User                            === ");
		System.out.println(" ===   0. Return to main manu                    === ");
		System.out.println(" ===                                             === ");
		System.out.println(" =================================================== ");
		System.out.println();
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
		System.out.println(" ===   5. Quit program                           === ");
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
		System.out.println(" ===   1. Show all borrowed books                === ");
		System.out.println(" ===   2. Show all delayed books                 === ");
		System.out.println(" ===   3. Show users loan history                === ");
		System.out.println(" ===   4. Quit program                           === ");
		System.out.println(" ===                                             === ");
		System.out.println(" =================================================== ");
		System.out.println();
	}
}
