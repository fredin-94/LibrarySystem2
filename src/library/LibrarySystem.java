package library;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import library.Library.bookKey;

import static library.Library.bookKey.*; // Needed to take enum keys as parameters. //Fabian.

public class LibrarySystem {

    private Menu menu = new Menu();
    private Scanner scanner = new Scanner(System.in);
    private Library library;

    public LibrarySystem() {
        library = new Library();
    }

    public void searchBook() {
        System.out.println("===================================================\n" + "== Search for book:\n"
                + "=================================================== ");
        scanner.nextLine();
        String searchText = scanner.nextLine();
        ArrayList<Book> searchResults = library.searchForBook(searchText);
        try {
            String result = "\n==================================================="
                    + "\nSearch Result\nSize of result: " + searchResults.size()
                    + "\n===================================================\n";
            for (Book book : searchResults) {
                result += book.toString() + "\n";
            }
            System.out.println(result);
        } catch (NullPointerException npe) {
            System.out.println("\n~~~~~~~~ No matches with '" + searchText + "'. Try again later.\n");
        }
    }

    public int requestUserInput() {
        int userInput;
        while (!scanner.hasNextInt()) {
            System.out.println("Not a valid option. Please enter a number.");
            scanner.next();
        }
        userInput = scanner.nextInt();
        return userInput;
    }

    public String requestTitle() throws Exception {
        System.out.println("===================================================\n" + "Enter title *: "
                + "\n===================================================");
        String title = scanner.nextLine().trim();
        if (title.equals("")) {
            throw new Exception("~~~~~~~~ Title of a book cannot be empty. Please retry. ");
        }
        return title;
    }

    public String requestAuthor() throws Exception {
        System.out.println("===================================================\n" + "Enter author(s) *: "
                + "\n===================================================");
        String author = scanner.nextLine().trim();
        if (author.equals("")) {
            throw new Exception("~~~~~~~~ Author of a book cannot be empty. Please retry.");
        }
        return author;
    }

    public String requestPublisher() throws Exception {
        System.out.println("===================================================\n" + "Enter publisher *: "
                + "\n===================================================");
        String publisher = scanner.nextLine().trim();
        if (publisher.equals("")) {
            throw new Exception("~~~~~~~~ Publisher of a book cannot be empty. Please retry.");
        }
        return publisher;
    }

    public String requestGenre() throws Exception {
        System.out.println("===================================================\n" + "Enter genre *: "
                + "\n===================================================");
        String genre = scanner.nextLine().trim();
        if (genre.equals("")) {
            throw new Exception("~~~~~~~~ Genre of a book cannot be empty. Please retry.");
        }
        return genre;
    }

    public String requestShelf() throws Exception {
        System.out.println("===================================================\n" + "Enter shelf *: "
                + "\n===================================================");
        String shelf = scanner.nextLine();
        if (shelf.equals("")) {
            throw new Exception("~~~~~~~~ Shelf of a book cannot be empty. Please retry.");
        }
        return shelf;
    }

    // TODO Retrieve Customer information
    public void searchCustomer() {
        System.out.println("===================================================\n" + "Search for customer: ");
        scanner.nextLine();
        String searchText = scanner.nextLine();
        ArrayList<Customer> searchResults = library.searchForCustomer(searchText);
        Customer theCustomer = null;
        String s = "";
        try {
            s += "\n===================================================\nSearch Result\nSize of result: "
                    + searchResults.size() + "\n===================================================\n";

            for (int i = 0; i < searchResults.size(); i++) {
                s += "\n===================================================\nCustomer number -> " + (i + 1) + ") "
                        + searchResults.get(i).toString();
                ;
            }
            System.out.println(s);
            System.out
                    .println("===================================================\n" + "Enter the customer number*: ");
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("~~~~~~~~ Please enter an integer\nfrom 1 to " + searchResults.size());
            }
            int userInput = scanner.nextInt();
            scanner.nextLine();
            theCustomer = searchResults.get(userInput - 1);
            System.out.println(theCustomer.toString());
            customerExtraFeatures(theCustomer);
        } catch (NullPointerException npe) {
            System.out.println("~~~~~~~~ No matches with '" + searchText + "'.");
        }

    }

    public String requestName() throws Exception {
        System.out.println("===================================================\n" + "Enter customer name *: "
                + "\n===================================================");
        String name = scanner.nextLine().trim();
        if (name.equals("")) {
            throw new Exception("~~~~~~~~ Customer name cannot be empty. Please retry.");
        } else {
            return name;
        }
    }

    public String requestAddress() throws Exception {
        System.out.println("===================================================\n" + "Enter customer address *: "
                + "\n===================================================");
        String address = scanner.nextLine().trim();
        if (address.equals("")) {
            throw new Exception("~~~~~~~~ Customer address cannot be empty. Please retry.");
        } else {
            return address;
        }
    }

    public String requestPhoneNumber() throws Exception {
        System.out.println("===================================================\n" + "Enter customer phonenumber: "
                + "\n===================================================");
        String phoneNum = scanner.nextLine().trim();
        if (phoneNum.equals("")) {
            return "Not Available";
        } else if ((phoneNum.trim().matches("[0-9]+") && phoneNum.trim().length() == 10)) {
            return phoneNum;
        } else {
            throw new Exception("~~~~~~~~ Phone number MUST either be empty or consist of 10 digits");
        }
    }

    public String requestPsn() throws Exception {
        System.out.println(
                "===================================================\n" + "Enter customer personal security number *: "
                        + "\n===================================================");
        String psn = scanner.nextLine().trim();
        if (psn.equals("")) {
            throw new Exception("~~~~~~~~ Customer social security number cannot be empty. Please retry.");
        } else if (!(psn.matches("[0-9]+") && psn.length() == 10 || psn.length() == 12)) {
            throw new Exception("~~~~~~~~ Incorrect social security number input. Please retry.");
        }

        boolean exists = false;
        for (Customer theCustomer : library.getCustomers()) {
            if (theCustomer.getPersonnummer().length() > psn.length() && theCustomer.getPersonnummer().contains(psn)) {
                exists = true;
            } else if (theCustomer.getPersonnummer().length() < psn.length()
                    && psn.contains(theCustomer.getPersonnummer())) {
                exists = true;
            } else if (theCustomer.getPersonnummer().trim().equals(psn.trim())) {
                exists = true;
            }
        }

        if (!exists) {
            return psn;
        } else {
            throw new Exception("~~~~~~~~ Customer with " + psn + " Social Security Number Already Exists");
        }
    }

    public String getPsn() throws Exception {
        System.out.println("Enter customer social security number: ");
        String psn = scanner.nextLine().trim();
        if (psn.equals("")) {
            throw new Exception("~~~~~~~~ Customer social security number cannot be empty. Please retry.");
        } else if (!(psn.matches("[0-9]+") && psn.length() == 10 || psn.length() == 12)) {
            throw new Exception("~~~~~~~~ Incorrect social security number input. Please retry.");
        }
        return psn;
    }

    public void addBook() {
        String title = "";
        String author = "";
        String publisher = "";
        String genre = "";
        String shelf = "";
        System.out.println("===================================================\n" + "==		Book Registration"
                + "\n==		Fields with (*) MUST be filled " + "\n===================================================");
        try {
            title = requestTitle();
            author = requestAuthor();
            publisher = requestPublisher();
            genre = requestGenre();
            shelf = requestShelf();
            Book b = new Book(title, author, publisher, genre, shelf);
            library.addBook(b);
            writeBookToFile("res/AllBooks.txt", b);
            writeBookToFile("res/bookDirectory.txt", b);
            System.out.println(b.getTitle() + " was successfully added\nto the library.");
        } catch (Exception e) {
            System.out.println("~~~~~~~~~~~~~~~~\n" + e.getMessage() + "\n~~~~~~~~~~~~~~~~\nTry again..");
        }
    }

    public void deleteBook() {
        String searchTextBook = "";

        try {
            searchTextBook = requestTitle();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ArrayList<Book> searchResults = library.searchForBook(searchTextBook);
        Book bookToDelete = null;
        try {
            String result = "\n===================================================\nSearch Result\nSize of result: "
                    + searchResults.size() + "\n===================================================\n";
            if (searchResults.isEmpty() || searchResults == null) {
                System.out.println("~~~~~~~~~~~~~~~~Nothing matched with " + searchTextBook);
            } else {
                for (int i = 0; i < searchResults.size(); i++) {
                    result += "===================================================\nBook number -> " + (i + 1) + ") "
                            + searchResults.get(i).toString() + "\n===================================================";
                }
            }
            System.out.println(result);
            System.out.println("\n===================================================\n" + "Enter the book number: "
                    + "\n===================================================\n");
            int userInp = scanner.nextInt();
            scanner.nextLine();
            bookToDelete = searchResults.get(userInp - 1);
            System.out.println("== " + bookToDelete.getTitle()
                    + " by: " + bookToDelete.getAuthors() + " has been chosen\n--------\n");
        } catch (NullPointerException npe) {
            System.out.println("\nNo matches with '" + searchTextBook + "'. Try again. \n");
            menu.getAdministration(library.getDate());
            int userInput = requestUserInput();
            handleAdmin(userInput);
        }

        try {
        	 if (isInList(library.getBooks(), bookToDelete)) { //gives nullpointer exception
                 removeLineFromFile("res/bookDirectory.txt", parseBookToString(bookToDelete));
                 removeLineFromFile("res/AllBooks.txt", parseBookToString(bookToDelete));
                 try {
                     library.deleteBook(bookToDelete);
                     System.out.println("Book deleted");
                 } 
                 catch (Exception e) {
                     System.out.println("~~~~~~~~~~~~~~~~\n" + e.getMessage() + "\n~~~~~~~~~~~~~~~~\n");
                 }
             }
		} catch (NullPointerException e) {
			//menu.getAdministration(library.getDate());
		}
        
    }

    public void addCustomer() {
        String name = "";
        String address = "";
        String psn = "";
        String phoneNumber = "";
        System.out.println("===================================================\n" + "Customer Registration"
                + "\n Fields with (*) MUST be filled " + "\n===================================================");
        try {
            name = requestName();
            address = requestAddress();
            psn = requestPsn();
            phoneNumber = requestPhoneNumber();
            createFile(psn + "LoanHistory");
            createFile(psn + "CurrentLoans");
            writeCustomerToFile(name, address, psn, phoneNumber);
            System.out.println("Added " + name + " to customer database");
            //System.out.println("bugChecker: No bugs found. Success!");
            library.addCustomer(new Customer(name, address, psn, phoneNumber));
        } catch (Exception e) {
            removeLineFromFile("res/customer.txt", name + "/" + address + "/" + psn + "/" + phoneNumber);
            deleteFile("res/" + psn + "CurrentLoans.txt");
            deleteFile("res/" + psn + "LoanHistory.txt");
            System.out.println("~~~~~~~~~~~~~~~~\n" + e.getMessage() + "\n~~~~~~~~~~~~~~~~\nTry again..");
            addCustomer();
        }
    }

    public void removeCustomer() {
        String ssn = "";
        Customer customer = null;
        try {
            ssn = getPsn();
            customer = retrieveCustomer(ssn);
        } catch (Exception e) {
            System.out.println("~~~~~~~~~~~~~~~~\n" + e.getMessage() + "\n~~~~~~~~~~~~~~~~\nTry again..");
            removeCustomer();
        }
        removeLineFromFile("res/customer.txt", parseCustomerToString(customer));
        deleteFile("res/" + ssn + "CurrentLoans.txt");
        deleteFile("res/" + ssn + "LoanHistory.txt");
        library.removeCustomer(customer);
        System.out.println("Removed customer");
    }

    public void borrowBook() throws Exception {
        scanner.nextLine();
        // Retrieving custowriteBookToFile("res/" + psn + "CurrentLoans.txt", book);mer
        System.out.println("===================================================\n" + "== Search for customer:");
        String searchTextCustomer = scanner.nextLine();
        ArrayList<Customer> searchResult = library.searchForCustomer(searchTextCustomer);
        Customer theCustomer = null;

        try {
            String res = "\n===================================================\nSearch Result\nSize of result: "
                    + searchResult.size() + "\n===================================================\n";

			/* TODO fix */
            for (int i = 0; i < searchResult.size(); i++) {
                res += "\n===================================================\nCustomer number -> " + (i + 1) + ") "
                        + searchResult.get(i).toString();
            }
            System.out.println(res);
        } catch (NullPointerException npe) {
            System.out.println("No matches with '" + searchTextCustomer + "'.");
        }

        System.out.println("\n===================================================\n" + "Enter the customer number: ");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("~~~~~~~~ Please enter an integer\nfrom 1 to " + searchResult.size());
        }
        int userInput = scanner.nextInt();
        scanner.nextLine();
        theCustomer = searchResult.get(userInput - 1);
        System.out.println("== " + theCustomer.getName() + " has been chosen\n");

        // retrieving book
        String searchTextBook = requestTitle();
        ArrayList<Book> searchResults = library.searchForBook(searchTextBook);
        Book bookToBorrow = null;
        try {
            String result = "\n===================================================\nSearch Result\nSize of result: "
                    + searchResults.size() + "\n===================================================\n";
            if (searchResults.isEmpty() || searchResults == null) {
                System.out.println("~~~~~~~~~~~~~~~~Nothing matched with " + searchTextBook);
            } else {
                for (int i = 0; i < searchResults.size(); i++) {
                    result += "===================================================\nBook number -> " + (i + 1) + ") "
                            + searchResults.get(i).toString() + "\n===================================================";
                }
            }
            System.out.println(result);
            System.out.println("\n===================================================\n" + "Enter the book number: ");
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("~~~~~~~~ Please enter an integer\nfrom 1 to " + searchResults.size());
            }
            int userInp = scanner.nextInt();
            scanner.nextLine();
            bookToBorrow = searchResults.get(userInp - 1);
            //bookToBorrow.setReturnDate(LocalDate.now().plusDays(14));
          //  System.out.println("borrowing: " + bookToBorrow.getReturnDate());
            System.out.println("== " + bookToBorrow.getTitle()
                    + " by: " + bookToBorrow.getAuthors() + " has been chosen\n--------\n");
        } catch (NullPointerException npe) {
            System.out.println("~~~~~~~~~~~~~~~~\n" + npe.getMessage() + "\n~~~~~~~~~~~~~~~~\nTry again..");
            System.out.println("\nNo matches with '" + searchTextBook + "'. Try again. \n");
            borrowBook();
        }

        // borrowing book
        System.out.println("Borrowing " + bookToBorrow.getTitle() + " ...");
        removeLineFromFile("res/bookDirectory.txt", parseBookToString(bookToBorrow));
        library.borrowBook(bookToBorrow.getTitle(), theCustomer.getPersonnummer());
        borrowBookTxtHandling(theCustomer.getPersonnummer(), bookToBorrow);

        System.out.println("||-----------------------------------------------||\n" + bookToBorrow.getTitle() + "\nby: "
                + bookToBorrow.getAuthors() + " was\nsuccessfully lent to " + theCustomer.getName() + "."
                + "\nTo be returned no later than: *" + bookToBorrow.getReturnDate() + "*"
                + "\n||-----------------------------------------------||\n");
    }

    public void borrowBookTxtHandling(String psn, Book book) {
        // ** text file handling for borrow book **//
        incrementTimesBorrowed("res/AllBooks.txt", book);
        incrementTimesBorrowed("res/bookDirectory.txt", book);

        //removeLineFromFile("res/bookDirectory.txt", parseBookToString(book)); //doesnt work here cuz you already changed the parameters of the book, so it will be seen as a different object
        try {
            book.setReturnDate(LocalDate.now().plusDays(14));
            writeBookToFile("res/" + psn + "CurrentLoans.txt", book);
            writeBookToFile("res/" + psn + "LoanHistory.txt", book);
           // System.out.println("borrowing: " + book.getReturnDate());
            //book.setReturnDate(LocalDate.of(2017, 10, 31));
            //writeBookToFile("res/AllBooks.txt", book); //will only add one book, but the remove line from file deles all books, so if we had several books we only get one back
            book.setReturnDate(LocalDate.now().plusDays(14));
           // System.out.println("borrowing: " + book.getReturnDate());
        } catch (Exception e) {
            e.printStackTrace();
        }

        checkCustomerFiles(book);
    }


    // This can definitely be prettied up especially because the code is so similar to what's going on in removeLineFromFile
    public void incrementTimesBorrowed(String path, Book book) {
        String currentTimes = Integer.toString(book.getTimesBorrowed() - 1);
        String incremented = Integer.toString(book.getTimesBorrowed());

        try {
            File dirFile = new File(path);
            File tmpFile = new File(dirFile.getAbsolutePath() + ".tmp");
            BufferedReader br = new BufferedReader(new FileReader(path));
            PrintWriter pw = new PrintWriter(new FileWriter(tmpFile));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(book.getTitle()) && line.contains(book.getAuthors())) {
                    pw.println(line.replaceFirst("/" + currentTimes + "/", "/" + incremented + "/"));
                    pw.flush();
                } else {
                    pw.println(line);
                    pw.flush();
                }
            }
            System.gc();
            pw.close();
            br.close();
            boolean success = dirFile.delete();
            boolean renameSuccess = tmpFile.renameTo(dirFile);

           // System.out.println("current file: " + path);
            if (success) {
                //System.out.println("Old file deleted " + path);
            }
            if (renameSuccess) {
                //System.out.println("file renamed " + path);
            }
        } catch (Exception e) {
            // e.getMessage();
            System.out.println("In increment times borrowed: Not able to complete method");
        }
    }

    public void checkCustomerFiles(Book book) {
        File dir = new File("res/");
        File[] dirList = dir.listFiles();

        for (File file : dirList) {
            try {
                incrementTimesBorrowed(file.getAbsolutePath(), book);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean titleHasDifferentAuthors(Book book) {
        for (Book b : library.getBooks()) {
            if (book.getTitle().equalsIgnoreCase(b.getTitle())) {
                return true;
            }
        }
        return false;
    }

    public void returnBook() throws Exception {
        scanner.nextLine();

        System.out.println("===================================================\n" + "== Search for customer:");
        String searchTextCustomer = scanner.nextLine();
        ArrayList<Customer> searchResult = library.searchForCustomer(searchTextCustomer);
        Customer theCustomer = null;

        try {
            String res = "\n===================================================\n== Search Result\n== Size of result: "
                    + searchResult.size() + "\n===================================================\n";
            for (int i = 0; i < searchResult.size(); i++) {
                res += "\n===================================================\nCustomer number ->" + (i + 1) + ") "
                        + searchResult.get(i).toString() + "\n" + library.getCustomerCurrentLoanString(searchResult.get(i));
            }
            System.out.println(res);
        } catch (NullPointerException npe) {
            System.out.println("~~~~~~~~ No matches with '" + searchTextCustomer + "'.");
        }

        System.out.println("\n===================================================\n" + "== Enter the customer number:");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("~~~~~~~~ Please enter an integer\nfrom 1 to " + searchResult.size());
        }
        int UserInput = scanner.nextInt();
        scanner.nextLine();
        theCustomer = searchResult.get(UserInput - 1);
        System.out.println("\n== " + theCustomer.getName()
                + "'s currently loaned books\n===================================================\n" + library.getCustomerCurrentLoanString(theCustomer));

        System.out.println("\n===================================================\n"
                + "== Enter title for book to return:\n(Hint: Review customer current loans)"
                + "\n===================================================\n");
        scanner.nextLine();
        Book bookToBorrow = null;
        ArrayList<Book> customerBooks = theCustomer.getCurrentLoans();
        try {
            String result = "\n===================================================\n== Result\n== Size of result: "
                    + customerBooks.size() + "\n===================================================\n";
            if (customerBooks.isEmpty() || customerBooks == null) {
                System.out.println("===================================================\n== " + theCustomer.getName()
                        + "'s current loans are empty." + "\n===================================================");
            } else {
                for (int i = 0; i < customerBooks.size(); i++) {
                    result += "===============\nBook number ->" + (i + 1) + ") " + customerBooks.get(i).toString()
                            + "\n";
                }
            }
            System.out.println(result);
            System.out.println("\n===================================================\n" + "Enter the book number: ");
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("~~~~~~~~ Please enter an integer\nfrom 1 to " + searchResult.size());
            }
            int UserInp = scanner.nextInt();
            scanner.nextLine();
            bookToBorrow = customerBooks.get(UserInp - 1);

        } catch (NullPointerException npe) {
            System.out.println("\n~~~~~~~~~~~~~~~~ Failed to return book. Try again. \n");
            returnBook();
        }

        // return a book
        Book book = theCustomer.getFromCurrentLoan(bookToBorrow.getTitle());
        // returns a book into library's available books directory
        removeLineFromFile("res/" + theCustomer.getPersonnummer() + "CurrentLoans.txt", parseBookToString(book));
        library.returnBook(bookToBorrow.getTitle(), theCustomer.getPersonnummer());
        book.setReturnDate(LocalDate.of(2017, 10, 31));
        writeBookToFile("res/bookDirectory.txt", book);
        // removeLineFromFile("res/LoanedBooks.txt", parseBookToString(book));
        System.out.println("*** Book returned successfully ***");
    }

    public void sortBooks() { // this is never called haha
        System.out.println("Show all books: Choose what to sort by");
        System.out.println("1. Sort after title");
        System.out.println("2. Sort after author");
        System.out.println("3. Sort after publisher");
        System.out.println("4. Sort after genre");
        System.out.println("5. Sort after shelf");
        System.out.println("6. Sort after times borrowed");

        int userInput = scanner.nextInt();
        if (userInput == 1) {
            library.sortBooksBy(bookKey.TITLE);
            for (int i = 0; i < library.getBooks().size(); i++) {
                System.out.println(library.getBooks().get(i));
            }
        } else if (userInput == 2) {
            library.sortBooksBy(bookKey.AUTHOR);
            for (int i = 0; i < library.getBooks().size(); i++) {
                System.out.println(library.getBooks().get(i));
            }
        } else if (userInput == 3) {
            library.sortBooksBy(bookKey.PUBLISHER);
            for (int i = 0; i < library.getBooks().size(); i++) {
                System.out.println(library.getBooks().get(i));
            }
        } else if (userInput == 4) {
            library.sortBooksBy(bookKey.GENRE);
            for (int i = 0; i < library.getBooks().size(); i++) {
                System.out.println(library.getBooks().get(i));
            }
        } else if (userInput == 5) {
            library.sortBooksBy(bookKey.SHELF);
            for (int i = 0; i < library.getBooks().size(); i++) {
                System.out.println(library.getBooks().get(i));
            }
        } else if (userInput == 6) {
            library.sortBooksBy(bookKey.TIMESBORROWED);
            for (int i = 0; i < library.getBooks().size(); i++) {
                System.out.println(library.getBooks().get(i));
            }
        }
    }

    public void showAvailableBooks() {
        System.out.println(library.toString());
    }

    public void showCustomers() {
        System.out.println("|==================== Customers =====================|" + "\n== Search results: "
                + library.getCustomers().size() + "\n===================================================\n");
        String res = "";
        try {
            for (Customer c : library.getCustomers()) {
                if (c != null) {
                    res += c.toString();
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }

        if (res.equals("")) {
            res = "~~~~~~~~ No customers are registered.";
        } else {
            System.out.println(res);
        }
    }

    public void showAllLoanedBooks() {
        if (library.getLoanedBooks().isEmpty()) {
            System.out.println("~~~~~~~~ No books have been lent out... yet");
        } else {
            for (int i = 0; i < library.getLoanedBooks().size(); i++) {
                System.out.println(library.getLoanedBooks().get(i).toString());
            }
        }
    }

    public void showAllDelayedBooks() {
        if (library.getDelayedBooks().isEmpty()) {
            System.out.println("~~~~~~~~ No delayed books... yet");
        } else {
            for (int i = 0; i < library.getDelayedBooks().size(); i++) {
                System.out.println(library.getDelayedBooks().get(i).toStringCurrentLoans());
            }
        }
    }

    public void showMostPopularBook() {
        String res = "";
        if (library.getTopTen().isEmpty() || library.getTopTen().equals(null)) {
            res += "No book has been loaned out yet.";
        } else {
            res += "Most popular books now are:\n";
            for (Book book : library.getTopTen()) {
                res += book.toString();
            }
        }
        System.out.println(res);
    }

    public void customerExtraFeatures(Customer customer) {
        menu.getCustomerFeatures();
        String option = scanner.nextLine();
        option = option.trim();
        String temp;
        switch (option) {
            case "1":
                temp = library.getCustomerCurrentLoanString(customer);
                System.out.println(temp);
                System.out.println();
                customerExtraFeatures(customer);
                break;
            case "2":
                temp = library.getCustomerLoanHistoryString(customer);
                System.out.println(temp);
                System.out.println();
                customerExtraFeatures(customer);
                break;
//            case "3": //CHANGE HERE TO DISPLAY DEBT BEFORE PAYING TO SEE WHAT U NEED TO PAY
//                System.out.print("===================================================\n" + "Enter payment: ");
//                double payement = scanner.nextDouble();
//                scanner.nextLine();
//                try {
//                    customer.payDebt(payement);
//                } catch (Exception e) {
//                    System.out.println("~~~~~~~~\n " + e.getMessage() + "\ntry again later...\n~~~~~~~~");
//                }
//                System.out.println();
//                System.out.println(customer.toString());
//                customerExtraFeatures(customer);
//                break;
            case "0":
                break;
            default:
                System.out.println("~~~~~~~~ Not a valid option");
                break;

        }
    }

    public void incrementDays() {
        System.out.println(
                "===================================================" 
        + "\n== Enter how many days to increment: ");
        int day = scanner.nextInt();
        library.addDays(day);
    }

    public void incrementWeeks() {
        System.out.println(
                "===================================================" 
        + "\n== Enter how many weeks to increment: ");
        int week = scanner.nextInt();
        library.addWeeks(week);
    }

    public void incrementMonths() {
        System.out.println(
                "===================================================" 
        + "\n== Enter how many months to increment: ");
        int month = scanner.nextInt();
        library.addMonths(month);
    }

    public void incrementYears() {
        System.out.println(
                "===================================================" 
        + "\n== Enter how many years to increment: ");
        int year = scanner.nextInt();
        library.addyears(year);
    }


    public Book retrieveBook(ArrayList<Book> listOfBooks, String title) throws Exception {
        for (Book book : listOfBooks) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        throw new Exception("~~~~~~~~ The book is not in that directory.");

    }

    public boolean isInList(ArrayList<Book> listOfBooks, Book book) {
        for (Book b : listOfBooks) {
            if (b.getTitle().equals(book.getTitle())) {
                return true;
            }
        }
        return false;
    }

    public Customer retrieveCustomer(String psn) throws Exception {
        Customer somePerson = null;
        for (Customer customer : library.getCustomers()) {
            if (customer.getPersonnummer().equals(psn)) {
                somePerson = customer;
                break;
            }
        }
        if (somePerson != null) {
            return somePerson;
        } else {
            throw new Exception("C~~~~~~~~ Customer does not exist in the library's database.");
        }
    }

    public void writeBookToFile(String path, Book book) {
        if (!book.getTitle().equals("") && !book.getAuthors().equals("") && !book.getPublisher().equals("")
                && !book.getGenre().equals("") && !book.getShelf().equals("")) {
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)))) {
                out.println(
                        book.getTitle() + "/" + book.getAuthors() + "/" + book.getPublisher() + "/" + book.getGenre()
                                + "/" + book.getShelf() + "/" + book.getTimesBorrowed() + "/" + book.getReturnDate());
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            // System.out.println("In Write book to file: Added " + book.getTitle() + " to
            // library");
        } else {
            //System.out.println("In write book to file: No parameters allowed to be empty");
        }
    }

    // changes objects into a format appropriate for the txt files
    public String parseBookToString(Book book) {
        return book.getTitle() + "/" + book.getAuthors() + "/" + book.getPublisher() + "/" + book.getGenre() + "/"
                + book.getShelf() + "/" + book.getTimesBorrowed() + "/" + book.getReturnDate();
    }

    public String parseCustomerToString(Customer customer) {
        return customer.getName() + "/" + customer.getAdress() + "/" + customer.getPersonnummer() + "/"
                + customer.getNumber();
    }

    public void removeLineFromFile(String path, String lineToRemove) {
        int count = 0;
        try {
            File dirFile = new File(path);
            File tmpFile = new File(dirFile.getAbsolutePath() + ".tmp");
            BufferedReader br = new BufferedReader(new FileReader(path));
            PrintWriter pw = new PrintWriter(new FileWriter(tmpFile));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.equals(lineToRemove.trim()) || count > 0) {
                    pw.println(line);
                    pw.flush();
                } else {
                    if (count == 0) {
                        count++;
                    }
                }
            }
            System.gc();
            pw.close();
            br.close();
            boolean success = dirFile.delete();
            boolean renameSuccess = tmpFile.renameTo(dirFile);

            if (success) {
                //System.out.println("Old file deleted");
            }
            if (renameSuccess) {
                //System.out.println("file renamed");
            }
        } catch (Exception e) {
            // e.getMessage();
            System.out.println("In remove line from file: Not able to complete method");
        }
    }

    public void writeCustomerToFile(String name, String address, String psn, String phoneNumber) {
        if (!name.equals("") && !address.equals("") && !psn.equals("")) {

            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("res/customer.txt", true)))) {
                out.println(name + "/" + address + "/" + psn + "/" + phoneNumber);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            System.out.println("Added " + name + " to customer database");
        } else {
            //System.out.println("No parameters allowed to be empty");
        }
    }

    // -- Customer handling methods --//
    public void createFile(String fileName) {
        //System.out.println("in createFIle");
        try {
            File file = new File("res/" + fileName + ".txt");
            if (file.createNewFile()) {
                //System.out.println("Text file is created!");
            } else {
                //System.out.println("Text file for " + fileName + " already exists.");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void deleteFile(String path) {
        File f = new File(path);
        if (f.delete()) {
            //System.out.println("Customer files deleted successfully");
        } else {
            //System.out.println("Unable to delete customer files");
        }
    }

    // TODO Menu Handling
    public void run() {
        int userInput;
        menu.getWelcomeScreen(library.getDate());
        menu.getMenu(library.getDate());

        userInput = requestUserInput();
        scanner.nextLine();
        do {
            switch (userInput) {
                case 1:
                    menu.getSearch(library.getDate());
                    handleSearchMenu(requestUserInput());
                    break;
                case 2:
                    menu.getBookOptions(library.getDate());
                    handleBookMenu(requestUserInput());
                    break;
                case 3:
                    menu.getAdministration(library.getDate());
                    handleAdmin(requestUserInput());
                    break;
                case 4:
                    menu.getExtra(library.getDate());
                    handleExtra(requestUserInput());
                    break;
                case 5:
                    menu.getSimulator(library.getDate());
                    handleTimeSimMenu(requestUserInput());
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("~~~~~~~~ Not a valid option");
                    run();
                    break;
            }
        } while (userInput != 0);
    }

    public void handleSearchMenu(int option) {
        switch (option) {
            case 1:
                searchBook();
                break;
            case 2:
                searchCustomer();
                break;
            case 3:
                System.out.println("Sort books after: ");
                System.out.println("1. Title");
                System.out.println("2. Author");
                System.out.println("3. Publisher");
                System.out.println("4. Genre");
                System.out.println("5. Shelf");
                System.out.println("6. Most borrowed");
                option = requestUserInput(); // need another one and not reuse "option"?
                
                if (option == 1) {
                    library.sortBooksBy(TITLE);
                } else if (option == 2) {
                    library.sortBooksBy(AUTHOR);
                } else if (option == 3) {
                    library.sortBooksBy(PUBLISHER);
                } else if (option == 4) {
                    library.sortBooksBy(GENRE);
                } else if (option == 5) {
                    library.sortBooksBy(SHELF);
                } else if (option == 6) {
                    library.sortAllBooksBy(TIMESBORROWED); //why is this showing the wrong list??
                } else {
                		
                    System.out.println("~~~~~~~~ Not a valid option, displaying randomly");// ??
                }
                showAvailableBooks();
                break;
            case 4:
                showCustomers();
                break;
            case 0:
                run();
                break;
            default:
                System.out.println("~~~~~~~~ Not a valid option.");
                break;
        }
    }

    public void handleBookMenu(int option) {
        switch (option) {
            case 1:
                try {
                    borrowBook();
                } catch (Exception e) {
                    e.getMessage();
                }
                break;
            case 2:
                try {
                    returnBook();
                } catch (Exception e) {
                    e.getMessage();
                }
                break;
            case 0:
                run();
                break;
            default:
                System.out.println("~~~~~~~~ Not a valid option");
                break;
        }
    }

    public void handleAdmin(int option) {
        scanner.nextLine();
        switch (option) {
            case 1:
                addBook();
                break;
            case 2:
                deleteBook();
                break;
            case 3:
                addCustomer();
                break;
            case 4:
                removeCustomer();
                break;
            case 0:
                run();
                break;
            default:
                System.out.println("~~~~~~~~ Not a valid option");
                break;
        }
    }

    public void handleExtra(int option) {
        switch (option) {
            case 1:
                showAllLoanedBooks();
                break;
            case 2:
                showAllDelayedBooks();
                break;
            case 3:
                showMostPopularBook();
                break;
            case 0:
                run();
                break;
            default:
                System.out.println("~~~~~~~~ Not a valid option");
                break;
        }
    }

    public void handleTimeSimMenu(int option) {
        switch (option) {
            case 1:
                incrementDays();
                break;
            case 2:
                incrementWeeks();
                break;
            case 3:
                incrementMonths();
                break;
            case 4:
                incrementYears();
                break;
            case 0:
                run();
                break;
            default:
                System.out.println("~~~~~~~~ Not a valid option");
                break;
        }
    }
}
