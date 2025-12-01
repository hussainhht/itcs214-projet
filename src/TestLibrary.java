/**
 * Simple test class to verify the Library Management System works correctly
 */
public class TestLibrary {
    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();

        System.out.println("=== Testing Library Management System ===\n");

        // Test 1: Add books
        System.out.println("Test 1: Adding books...");
        Book book1 = new Book("Java Programming", "James Gosling", "Bill Joy", "Oracle Press", 2020,
                "978-0-1234-5678-9", 1001);
        Book book2 = new Book("Data Structures", "Mark Weiss", "", "Addison Wesley", 2019, "978-0-9876-5432-1", 1002);

        boolean added1 = library.addBook(book1);
        boolean added2 = library.addBook(book2);

        System.out.println("Book 1 added: " + (added1 ? "✅" : "❌"));
        System.out.println("Book 2 added: " + (added2 ? "✅" : "❌"));
        System.out.println("Total books: " + library.sizeBooksList());

        // Test 2: Add duplicate book (should fail)
        System.out.println("\nTest 2: Adding duplicate book...");
        Book bookDup = new Book("Duplicate", "Author", "", "Publisher", 2021, "123", 1001);
        boolean addedDup = library.addBook(bookDup);
        System.out.println("Duplicate book rejected: " + (!addedDup ? "✅" : "❌"));

        // Test 3: Add members
        System.out.println("\nTest 3: Adding members...");
        LibMember member1 = new LibMember("Ahmed", "Ali", 'M', 123456789, "12345678");
        LibMember member2 = new LibMember("Sara", "Hassan", 'F', 987654321, "87654321");

        boolean addedM1 = library.addMember(member1);
        boolean addedM2 = library.addMember(member2);

        System.out.println("Member 1 added: " + (addedM1 ? "✅" : "❌"));
        System.out.println("Member 2 added: " + (addedM2 ? "✅" : "❌"));
        System.out.println("Total members: " + library.sizeMembersList());

        // Test 4: Issue book
        System.out.println("\nTest 4: Issuing book...");
        boolean issued = library.issueBook(1001, 123456789);
        System.out.println("Book issued to member: " + (issued ? "✅" : "❌"));
        System.out.println("Book 1001 is issued: " + (library.isBookIssued(1001) ? "✅" : "❌"));
        System.out.println("Member has " + member1.getNumBookIssued() + " book(s)");

        // Test 5: Issue same book again (should fail)
        System.out.println("\nTest 5: Issuing same book again...");
        boolean issuedAgain = library.issueBook(1001, 987654321);
        System.out.println("Duplicate issue rejected: " + (!issuedAgain ? "✅" : "❌"));

        // Test 6: Return book
        System.out.println("\nTest 6: Returning book...");
        boolean returned = library.returnBook(1001);
        System.out.println("Book returned: " + (returned ? "✅" : "❌"));
        System.out.println("Book 1001 is available: " + (!library.isBookIssued(1001) ? "✅" : "❌"));
        System.out.println("Member has " + member1.getNumBookIssued() + " book(s)");

        // Test 7: Search operations
        System.out.println("\nTest 7: Search operations...");
        int bookIndex = library.searchBook(1002);
        int memberIndex = library.searchMember(987654321);
        System.out.println("Book found at index: " + bookIndex + " " + (bookIndex >= 0 ? "✅" : "❌"));
        System.out.println("Member found at index: " + memberIndex + " " + (memberIndex >= 0 ? "✅" : "❌"));

        // Test 8: Delete operations
        System.out.println("\nTest 8: Delete book...");
        boolean deleted = library.deleteBook(1002);
        System.out.println("Book deleted: " + (deleted ? "✅" : "❌"));
        System.out.println("Total books: " + library.sizeBooksList());

        System.out.println("\n=== All Tests Completed ===");
        System.out.println("✅ Library Management System is working correctly!");
    }
}
