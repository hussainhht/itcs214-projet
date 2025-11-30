import java.util.Scanner;

public class LibraryMain {

    private static LibrarySystem library = new LibrarySystem();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean running = true;

        while (running) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. Add Member");
            System.out.println("4. Delete Member");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Search Book");
            System.out.println("8. Search Member");
            System.out.println("9. Print Books Issued to Member");
            System.out.println("10. Check if Book is Issued");
            System.out.println("11. Size of Books List");
            System.out.println("12. Size of Members List");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

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
                    System.out.println("Books list size = " + library.sizeBooksList());
                    break;

                case 12:
                    System.out.println("Members list size = " + library.sizeMembersList());
                    break;

                case 0:
                    System.out.println("Exiting...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter accession number: ");
        long ac = scanner.nextLong();

        System.out.print("Enter title: ");
        scanner.nextLine();
        String t = scanner.nextLine();

        System.out.print("Enter author1: ");
        String a1 = scanner.nextLine();

        System.out.print("Enter author2: ");
        String a2 = scanner.nextLine();

        System.out.print("Enter publisher: ");
        String p = scanner.nextLine();

        System.out.print("Enter year: ");
        int y = scanner.nextInt();

        System.out.print("Enter ISBN: ");
        scanner.nextLine();
        String isbn = scanner.nextLine();

        Book b = new Book(t, a1, a2, p, y, isbn, ac);

        if (library.addBook(b))
            System.out.println("Book added.");
        else
            System.out.println("Book NOT added (duplicate).");
    }

    private static void deleteBook() {
        System.out.print("Enter accession number: ");
        long ac = scanner.nextLong();

        if (library.deleteBook(ac))
            System.out.println("Book deleted.");
        else
            System.out.println("Cannot delete book.");
    }

    private static void addMember() {
        System.out.print("Enter CPR: ");
        long cpr = scanner.nextLong();

        System.out.print("Enter first name: ");
        scanner.nextLine();
        String f = scanner.nextLine();

        System.out.print("Enter last name: ");
        String l = scanner.nextLine();

        System.out.print("Enter gender: ");
        char g = scanner.nextLine().charAt(0);

        System.out.print("Enter telephone: ");
        String tel = scanner.nextLine();

        LibMember m = new LibMember(f, l, g, cpr, tel);

        if (library.addMember(m))
            System.out.println("Member added.");
        else
            System.out.println("Cannot add member (duplicated).");
    }

    private static void deleteMember() {
        System.out.print("Enter CPR: ");
        long cpr = scanner.nextLong();

        if (library.deleteMember(cpr))
            System.out.println("Member deleted.");
        else
            System.out.println("Cannot delete member.");
    }

    private static void issueBook() {
        System.out.print("Enter accession number: ");
        long ac = scanner.nextLong();

        System.out.print("Enter CPR: ");
        long cpr = scanner.nextLong();

        if (library.issueBook(ac, cpr))
            System.out.println("Book issued.");
        else
            System.out.println("Cannot issue book.");
    }

    private static void returnBook() {
        System.out.print("Enter accession number: ");
        long ac = scanner.nextLong();

        if (library.returnBook(ac))
            System.out.println("Book returned.");
        else
            System.out.println("Cannot return book.");
    }

    private static void searchBook() {
        System.out.print("Enter accession number: ");
        long ac = scanner.nextLong();

        int pos = library.searchBook(ac);

        if (pos != -1)
            System.out.println("Book found at index: " + pos);
        else
            System.out.println("Book not found.");
    }

    private static void searchMember() {
        System.out.print("Enter CPR: ");
        long cpr = scanner.nextLong();

        int pos = library.searchMember(cpr);

        if (pos != -1)
            System.out.println("Member found at index: " + pos);
        else
            System.out.println("Member not found.");
    }

    private static void printBooksIssued() {
        System.out.print("Enter CPR: ");
        long cpr = scanner.nextLong();

        library.printBooksIssued(cpr);
    }

    private static void checkIssued() {
        System.out.print("Enter accession number: ");
        long ac = scanner.nextLong();

        if (library.isBookIssued(ac))
            System.out.println("Book is issued.");
        else
            System.out.println("Book is NOT issued.");
    }
}
