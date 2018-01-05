package library;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Menu {

	/**
	 * This class stores all the menu prints and user prompts in order to use the system.
	 * 
	 * @author Oliver Manzi
	 * 
	 * @version 1.0 Created: Simple prints that displayed the different menu sections.
	 * @version 1.1 Modification: added a localDate parameter in the method signature so 
	 * that the current date is visible at each menu print.
	 * Additionally, added a date formatter that takes given date and prints it out in 
	 * "Weekday-Month-calendar day-year" to facilitate user (less numbers, easier to read)
	 * @version 1.2 Modification: Added a "customer extra features" menu
	 * */
	public void getWelcomeScreen(LocalDate date) {
		SimpleDateFormat theDate = new SimpleDateFormat("EEE MMM dd-yyyy");
		Date fooBarBat = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		String fooBarDate = theDate.format(fooBarBat);
		System.out.println();
		System.out.println(" ===================================================\n"
		+ " ==               Welcome to                      ==\n"
		+ " ==            The TEAM 9 Library                 ==\n"
		+ " ==            Established 1817                   ==\n"
		+ " ==                                               ==\n"
		+ " ==           "  + fooBarDate +"                     ==\n"
		+ " ===================================================\n");
		System.out.println();
	}

	public void getMenu(LocalDate date) {
		SimpleDateFormat theDate = new SimpleDateFormat("EEE MMM dd-yyyy");
		Date fooBarBat = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		String fooBarDate = theDate.format(fooBarBat);
		System.out.println();
		System.out.println(" ===================================================\n"
		+ " ==             Library System                    ==\n"
		+ " ==                                               ==\n"
		+ " ==           "  + fooBarDate +"                     ==\n"
		+ " ===================================================\n"
		+ " ===                                             ===\n"
		+ " ===   Choose an option below:                   ===\n"
		+ " ===   1. Search library directory               ===\n"
		+ " ===   2. Borrow or return books                 ===\n"
		+ " ===   3. Administration                         ===\n"
		+ " ===   4. Extra                                  ===\n"
		+ " ===   5. Time Simulator FX40000                 ===\n"
		+ " ===   0. Quit program                           ===\n"
		+ " ===                                             ===\n"
		+ " ===================================================\n");
		System.out.println();
	}

	public void getSearch(LocalDate date) {
		SimpleDateFormat theDate = new SimpleDateFormat("EEE MMM dd-yyyy");
		Date fooBarBat = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		String fooBarDate = theDate.format(fooBarBat);
		System.out.println();
		System.out.println(" ===================================================\n"
		+ " ==             Search System                     ==\n"
		+ " ==                                               ==\n"
		+ " ==           "  + fooBarDate +"                     ==\n"
		+ " ===================================================\n"
		+ " ===                                             ===\n"
		+ " ===   Choose an option below:                   ===\n"
		+ " ===   1. Search for Book                        ===\n"
		+ " ===   2. Search for Customer                    ===\n"
		+ " ===   3. Print all Books                        ===\n"
		+ " ===   4. Print all Customers                    ===\n"
		+ " ===   0. Return to main menu                    ===\n"
		+ " ===================================================\n");
		System.out.println();
	}

	public void getBookOptions(LocalDate date) {
		SimpleDateFormat theDate = new SimpleDateFormat("EEE MMM dd-yyyy");
		Date fooBarBat = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		String fooBarDate = theDate.format(fooBarBat);
		System.out.println();
		System.out.println(" ===================================================\n"
		+ " ==             Borrow or Return books            ==\n"
		+ " ==                                               ==\n"
		+ " ==           "  + fooBarDate +"                     ==\n"
		+ " ===================================================\n"
		+ " ===                                             ===\n"
		+ " ===   Choose an option below:                   ===\n"
		+ " ===   1. Borrow Book                            ===\n"
		+ " ===   2. Return Book                            ===\n"
		+ " ===   0. Return to main menu                    ===\n"
		+ " ===================================================\n");
		System.out.println();
	}

	public void getAdministration(LocalDate date) {
		SimpleDateFormat theDate = new SimpleDateFormat("EEE MMM dd-yyyy");
		Date fooBarBat = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		String fooBarDate = theDate.format(fooBarBat);
		System.out.println();
		System.out.println(" ===================================================\n"
		+ " ==             Administration System             ==\n"
		+ " ==                                               ==\n"
		+ " ==           "  + fooBarDate +"                     ==\n"
		+ " ===================================================\n"
		+ " ===                                             ===\n"
		+ " ===   Choose an option below:                   ===\n"
		+ " ===   1. Add Book                               ===\n"
		+ " ===   2. Delete Book from directory             ===\n"
		+ " ===   3. Add Customer                           ===\n"
		+ " ===   4. Remove Customer                        ===\n"
		+ " ===   0. Return to main menu                    ===\n"
		+ " ===================================================\n");
		System.out.println();
	}

	public void getSimulator(LocalDate date) {
		SimpleDateFormat theDate = new SimpleDateFormat("EEE MMM dd-yyyy");
		Date fooBarBat = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		String fooBarDate = theDate.format(fooBarBat);
		System.out.println();
		System.out.println(" ===================================================\n"
		+ " ==             Simulator System                  ==\n"
		+ " ==                                               ==\n"
		+ " ==           "  + fooBarDate +"                     ==\n"
		+ " ===================================================\n"
		+ " ===                                             ===\n"
		+ " ===   Choose an option below:                   ===\n"
		+ " ===   1. Increment Days                         ===\n"
		+ " ===   2. Increment Weeks                        ===\n"
		+ " ===   3. Increment Months                       ===\n"
		+ " ===   4. Increment Years                        ===\n"
		+ " ===   0. Return to main menu                    ===\n"
		+ " ===                                             ===\n"
		+ " ===================================================\n");
		System.out.println();
	}

	public void getExtra(LocalDate date) {
		SimpleDateFormat theDate = new SimpleDateFormat("EEE MMM dd-yyyy");
		Date fooBarBat = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		String fooBarDate = theDate.format(fooBarBat);
		System.out.println();
		System.out.println(" ===================================================\n"
		+ " ==             Extra                             ==\n"
		+ " ==                                               ==\n"
		+ " ==           "  + fooBarDate +"                     ==\n"
		+ " ===================================================\n"
		+ " ===                                             ===\n"
		+ " ===   Choose an option below:                   ===\n"
		+ " ===   1. Show all lent out books                ===\n"
		+ " ===   2. Show all delayed books                 ===\n"
		+ " ===   3. Show most popular books                ===\n"
		+ " ===   0. Return to main menu                    ===\n"
		+ " ===                                             ===\n"
		+ " ===================================================\n");
		System.out.println();
	}
	
	public void getCustomerFeatures() {
		System.out.println("||-----------------------------------------------||\n"
		+ "||-   Choose an option below:                   -||\n"
		+ "||-----------------------------------------------||\n"
		+ "---   1. Show current loans                     ---\n"
		+ "---   2. Show loan history                      ---\n"
		+ "---   0. Return to Search Menu                    ---\n");
	}
}
