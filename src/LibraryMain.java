import java.util.Scanner;

public class LibraryMain {

    private static LibrarySystem library = new LibrarySystem();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   Welcome to Library Management System ║");
        System.out.println("╚════════════════════════════════════════╝");

        while (running) {
            System.out.println("prss enter to return to System : ...");
             String enter = scanner.nextLine();
             System.out.println(enter);
            
            System.out.println("\n╔════════════ LIBRARY MENU ═════════════╗");
            System.out.println("║  1.  Add Book                          ║");
            System.out.println("║  2.  Delete Book                       ║");
            System.out.println("║  3.  Add Member                        ║");
            System.out.println("║  4.  Delete Member                     ║");
            System.out.println("║  5.  Issue Book                        ║");
            System.out.println("║  6.  Return Book                       ║");
            System.out.println("║  7.  Search Book                       ║");
            System.out.println("║  8.  Search Member                     ║");
            System.out.println("║  9.  Print Books Issued to Member      ║");
            System.out.println("║  10. Check if Book is Issued           ║");
            System.out.println("║  11. Size of Books List                ║");
            System.out.println("║  12. Size of Members List              ║");
            System.out.println("║  0.  Exit                              ║");
            System.out.println("╚════════════════════════════════════════╝");

            System.out.print("Enter your choice: ");
            
            // Input validation
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Error: Invalid input! Please enter a number.");
                scanner.nextLine(); // Clear invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    deleteBook();
                    break;
                case 3:
                    addMember();
                    break;
                case 4:
                    deleteMember();
                    break;
                case 5:
                    issueBook();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    searchBook();
                    break;
                case 8:
                    searchMember();
                    break;
                case 9:
                    printBooksIssued();
                    break;
                case 10:
                    checkIssued();
                    break;
                case 11:
                    System.out.println("📚 Books list size = " + library.sizeBooksList());
                    break;
                case 12:
                    System.out.println("👥 Members list size = " + library.sizeMembersList());
                    break;
                case 0:
                    System.out.println("\n╔════════════════════════════════════════╗");
                    System.out.println("║   Thank you for using our system!      ║");
                    System.out.println("╚════════════════════════════════════════╝");
                    running = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice! Please select a valid option (0-12).");
            }
        }
        scanner.close();
    }

    private static void addBook() {
        System.out.println("\n--- Add New Book ---");
        
        try {
            System.out.print("Enter accession number: ");
            long ac = scanner.nextLong();

            System.out.print("Enter title: ");
            scanner.nextLine();
            String t = scanner.nextLine();

            System.out.print("Enter author1: ");
            String a1 = scanner.nextLine();

            System.out.print("Enter author2 (or press Enter to skip): ");
            String a2 = scanner.nextLine();

            System.out.print("Enter publisher: ");
            String p = scanner.nextLine();

            System.out.print("Enter year: ");
            int y = scanner.nextInt();

            System.out.print("Enter ISBN: ");
            scanner.nextLine();
            String isbn = scanner.nextLine();

            Book b = new Book(t, a1, a2, p, y, isbn);

            if (library.addBook(b)) {
                System.out.println("✅ Book added successfully!");
            } else {
                System.out.println("❌ Book NOT added (duplicate accession number or invalid data).");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: Invalid input format!");
            scanner.nextLine(); // Clear invalid input
        }
    }

    private static void deleteBook() {
        System.out.println("\n--- Delete Book ---");
        
        try {
            System.out.print("Enter accession number: ");
            long ac = scanner.nextLong();

            if (library.deleteBook(ac)) {
                System.out.println("✅ Book deleted successfully!");
            } else {
                System.out.println("❌ Cannot delete book (not found or currently issued).");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: Invalid input format!");
            scanner.nextLine();
        }
    }

    private static void addMember() {
        System.out.println("\n--- Add New Member ---");
        
        try {
            System.out.print("Enter CPR: ");
            long cpr = scanner.nextLong();

            System.out.print("Enter first name: ");
            scanner.nextLine();
            String f = scanner.nextLine();

            System.out.print("Enter last name: ");
            String l = scanner.nextLine();

            System.out.print("Enter gender (M/F): ");
            char g = scanner.nextLine().toUpperCase().charAt(0);

            System.out.print("Enter telephone: ");
            String tel = scanner.nextLine();

            LibMember m = new LibMember(f, l, g, cpr, tel);

            if (library.addMember(m)) {
                System.out.println("✅ Member added successfully!");
            } else {
                System.out.println("❌ Cannot add member (duplicate CPR number).");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: Invalid input format!");
            scanner.nextLine();
        }
    }

    private static void deleteMember() {
        System.out.println("\n--- Delete Member ---");
        
        try {
            System.out.print("Enter CPR: ");
            long cpr = scanner.nextLong();

            if (library.deleteMember(cpr)) {
                System.out.println("✅ Member deleted successfully!");
            } else {
                System.out.println("❌ Cannot delete member (not found or has issued books).");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: Invalid input format!");
            scanner.nextLine();
        }
    }

    private static void issueBook() {
        System.out.println("\n--- Issue Book ---");
        
        try {
            System.out.print("Enter accession number: ");
            long ac = scanner.nextLong();

            System.out.print("Enter member CPR: ");
            long cpr = scanner.nextLong();

            if (library.issueBook(ac, cpr)) {
                System.out.println("✅ Book issued successfully!");
            } else {
                System.out.println("❌ Cannot issue book (book/member not found, book already issued, or member limit reached).");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: Invalid input format!");
            scanner.nextLine();
        }
    }

    private static void returnBook() {
        System.out.println("\n--- Return Book ---");
        
        try {
            System.out.print("Enter accession number: ");
            long ac = scanner.nextLong();

            if (library.returnBook(ac)) {
                System.out.println("✅ Book returned successfully!");
            } else {
                System.out.println("❌ Cannot return book (book not found or not issued).");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: Invalid input format!");
            scanner.nextLine();
        }
    }

    private static void searchBook() {
        System.out.println("\n--- Search Book ---");
        
        try {
            System.out.print("Enter accession number: ");
            long ac = scanner.nextLong();

            int pos = library.searchBook(ac);

            if (pos != -1) {
                System.out.println("✅ Book found at index: " + pos);
            } else {
                System.out.println("❌ Book not found.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: Invalid input format!");
            scanner.nextLine();
        }
    }

    private static void searchMember() {
        System.out.println("\n--- Search Member ---");
        
        try {
            System.out.print("Enter CPR: ");
            long cpr = scanner.nextLong();

            int pos = library.searchMember(cpr);

            if (pos != -1) {
                System.out.println("✅ Member found at index: " + pos);
            } else {
                System.out.println("❌ Member not found.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: Invalid input format!");
            scanner.nextLine();
        }
    }

    private static void printBooksIssued() {
        System.out.println("\n--- Books Issued to Member ---");
        
        try {
            System.out.print("Enter member CPR: ");
            long cpr = scanner.nextLong();

            library.printBooksIssued(cpr);
        } catch (Exception e) {
            System.out.println("❌ Error: Invalid input format!");
            scanner.nextLine();
        }
    }

    private static void checkIssued() {
        System.out.println("\n--- Check if Book is Issued ---");
        
        try {
            System.out.print("Enter accession number: ");
            long ac = scanner.nextLong();

            if (library.isBookIssued(ac)) {
                System.out.println("📖 Book is currently issued.");
            } else {
                System.out.println("📚 Book is available.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: Invalid input format!");
            scanner.nextLine();
        }
    }
}
