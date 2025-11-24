import java.util.Scanner;

public class LibraryMain {
    private static LibrarySystem library = new LibrarySystem();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Add some sample data
        initializeSampleData();

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    addMember();
                    break;
                case 3:
                    issueBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    library.displayAllBooks();
                    break;
                case 6:
                    library.displayAllMembers();
                    break;
                case 7:
                    library.displayAvailableBooks();
                    break;
                case 8:
                    library.displayIssuedBooks();
                    break;
                case 9:
                    searchBooks();
                    break;
                case 10:
                    displayStatistics();
                    break;
                case 0:
                    System.out.println("Thank you for using the Library System. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("       LIBRARY MANAGEMENT SYSTEM");
        System.out.println("=".repeat(50));
        System.out.println("1.  Add New Book");
        System.out.println("2.  Add New Member");
        System.out.println("3.  Issue Book");
        System.out.println("4.  Return Book");
        System.out.println("5.  Display All Books");
        System.out.println("6.  Display All Members");
        System.out.println("7.  Display Available Books");
        System.out.println("8.  Display Issued Books");
        System.out.println("9.  Search Books");
        System.out.println("10. Display Statistics");
        System.out.println("0.  Exit");
        System.out.println("=".repeat(50));
    }

    private static void addBook() {
        System.out.println("\n--- Add New Book ---");
        scanner.nextLine(); // consume newline

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author 1: ");
        String author1 = scanner.nextLine();

        System.out.print("Enter Author 2 (or press Enter to skip): ");
        String author2 = scanner.nextLine();

        System.out.print("Enter Publisher: ");
        String publisher = scanner.nextLine();

        int year = getIntInput("Enter Year of Publication: ");

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        long accessionNum = getLongInput("Enter Accession Number: ");

        Book book = new Book(title, author1, author2, publisher, year, isbn, accessionNum);
        library.addBook(book);
    }

    private static void addMember() {
        System.out.println("\n--- Add New Member ---");
        scanner.nextLine(); // consume newline

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Gender (M/F): ");
        char gender = scanner.nextLine().toUpperCase().charAt(0);

        long cprNum = getLongInput("Enter CPR Number: ");

        System.out.print("Enter Telephone Number: ");
        String teleNum = scanner.nextLine();

        LibMember member = new LibMember(firstName, lastName, gender, cprNum, teleNum);
        library.addMember(member);
    }

    private static void issueBook() {
        System.out.println("\n--- Issue Book ---");
        long accessionNum = getLongInput("Enter Book Accession Number: ");
        long cprNum = getLongInput("Enter Member CPR Number: ");

        library.issueBook(accessionNum, cprNum);
    }

    private static void returnBook() {
        System.out.println("\n--- Return Book ---");
        long accessionNum = getLongInput("Enter Book Accession Number: ");

        library.returnBook(accessionNum);
    }

    private static void searchBooks() {
        System.out.println("\n--- Search Books ---");
        System.out.println("1. Search by Title");
        System.out.println("2. Search by Author");
        int choice = getIntInput("Enter your choice: ");

        scanner.nextLine(); // consume newline

        if (choice == 1) {
            System.out.print("Enter Title (or part of it): ");
            String title = scanner.nextLine();
            library.searchBooksByTitle(title);
        } else if (choice == 2) {
            System.out.print("Enter Author Name: ");
            String author = scanner.nextLine();
            library.searchBooksByAuthor(author);
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private static void displayStatistics() {
        System.out.println("\n=== Library Statistics ===");
        System.out.println("Total Books: " + library.getTotalBooks());
        System.out.println("Available Books: " + library.getAvailableBooks());
        System.out.println("Issued Books: " + library.getIssuedBooksCount());
        System.out.println("Total Members: " + library.getTotalMembers());
        System.out.println("=".repeat(50));
    }

    private static void initializeSampleData() {
        // Add sample books
        library.addBook(
                new Book("The Great Gatsby", "F. Scott Fitzgerald", "", "Scribner", 1925, "978-0-7432-7356-5", 1001));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "", "J.B. Lippincott & Co.", 1960,
                "978-0-06-112008-4", 1002));
        library.addBook(new Book("1984", "George Orwell", "", "Secker & Warburg", 1949, "978-0-452-28423-4", 1003));
        library.addBook(
                new Book("Pride and Prejudice", "Jane Austen", "", "T. Egerton", 1813, "978-0-14-143951-8", 1004));
        library.addBook(new Book("The Catcher in the Rye", "J.D. Salinger", "", "Little, Brown and Company", 1951,
                "978-0-316-76948-0", 1005));

        // Add sample members
        library.addMember(new LibMember("John", "Doe", 'M', 123456789L, "12345678"));
        library.addMember(new LibMember("Jane", "Smith", 'F', 987654321L, "87654321"));
        library.addMember(new LibMember("Ahmed", "Ali", 'M', 555555555L, "55555555"));

        System.out.println("Sample data initialized!");
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                return value;
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine(); // clear buffer
            }
        }
    }

    private static long getLongInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                long value = scanner.nextLong();
                return value;
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine(); // clear buffer
            }
        }
    }
}
