import java.util.ArrayList;

public class LibrarySystem {
    private ArrayList<Book> books;
    private ArrayList<LibMember> members;

    public LibrarySystem() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    // Add a new book to the library
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!");
    }

    // Add a new member to the library
    public void addMember(LibMember member) {
        members.add(member);
        System.out.println("Member added successfully!");
    }

    // Issue a book to a member
    public boolean issueBook(long accessionNum, long cprNum) {
        Book book = findBookByAccession(accessionNum);
        LibMember member = findMemberByCpr(cprNum);

        if (book == null) {
            System.out.println("Book not found!");
            return false;
        }

        if (member == null) {
            System.out.println("Member not found!");
            return false;
        }

        if (book.getIssuedTo() != null) {
            System.out.println("Book is already issued!");
            return false;
        }

        if (member.getNumBookIssued() >= 10) {
            System.out.println("Member has reached maximum book limit!");
            return false;
        }

        // Issue the book
        book.setIssuedTo(member);
        Book[] currentBooks = member.getBooksIssued();
        currentBooks[member.getNumBookIssued()] = book;
        member.setNumBooksIssued(member.getNumBookIssued() + 1);

        System.out.println("Book issued successfully!");
        return true;
    }

    // Return a book
    public boolean returnBook(long accessionNum) {
        Book book = findBookByAccession(accessionNum);

        if (book == null) {
            System.out.println("Book not found!");
            return false;
        }

        if (book.getIssuedTo() == null) {
            System.out.println("Book is not currently issued!");
            return false;
        }

        LibMember member = book.getIssuedTo();

        // Remove book from member's issued books
        Book[] currentBooks = member.getBooksIssued();
        int bookIndex = -1;
        for (int i = 0; i < member.getNumBookIssued(); i++) {
            if (currentBooks[i].getAccessionNum() == accessionNum) {
                bookIndex = i;
                break;
            }
        }

        if (bookIndex != -1) {
            // Shift books array
            for (int i = bookIndex; i < member.getNumBookIssued() - 1; i++) {
                currentBooks[i] = currentBooks[i + 1];
            }
            currentBooks[member.getNumBookIssued() - 1] = null;
            member.setNumBooksIssued(member.getNumBookIssued() - 1);
        }

        book.setIssuedTo(null);
        System.out.println("Book returned successfully!");
        return true;
    }

    // Find book by accession number
    public Book findBookByAccession(long accessionNum) {
        for (Book book : books) {
            if (book.getAccessionNum() == accessionNum) {
                return book;
            }
        }
        return null;
    }

    // Find member by CPR number
    public LibMember findMemberByCpr(long cprNum) {
        for (LibMember member : members) {
            if (member.getCprNum() == cprNum) {
                return member;
            }
        }
        return null;
    }

    // Display all books
    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library!");
            return;
        }

        System.out.println("\n=== All Books ===");
        for (int i = 0; i < books.size(); i++) {
            System.out.println("\nBook #" + (i + 1) + ":");
            System.out.println(books.get(i));
            System.out.println("Status: " + (books.get(i).getIssuedTo() == null ? "Available" : "Issued"));
            System.out.println("-------------------");
        }
    }

    // Display all members
    public void displayAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members in the library!");
            return;
        }

        System.out.println("\n=== All Members ===");
        for (int i = 0; i < members.size(); i++) {
            System.out.println("\nMember #" + (i + 1) + ":");
            System.out.println(members.get(i));
            System.out.println("-------------------");
        }
    }

    // Display available books
    public void displayAvailableBooks() {
        System.out.println("\n=== Available Books ===");
        boolean found = false;
        for (Book book : books) {
            if (book.getIssuedTo() == null) {
                System.out.println(book);
                System.out.println("-------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available books!");
        }
    }

    // Display issued books
    public void displayIssuedBooks() {
        System.out.println("\n=== Issued Books ===");
        boolean found = false;
        for (Book book : books) {
            if (book.getIssuedTo() != null) {
                System.out.println(book);
                System.out.println("-------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No issued books!");
        }
    }

    // Search books by title
    public void searchBooksByTitle(String title) {
        System.out.println("\n=== Search Results ===");
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println(book);
                System.out.println("Status: " + (book.getIssuedTo() == null ? "Available" : "Issued"));
                System.out.println("-------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found with that title!");
        }
    }

    // Search books by author
    public void searchBooksByAuthor(String author) {
        System.out.println("\n=== Search Results ===");
        boolean found = false;
        for (Book book : books) {
            if (book.getAuthor1().toLowerCase().contains(author.toLowerCase()) ||
                    book.getAuthor2().toLowerCase().contains(author.toLowerCase())) {
                System.out.println(book);
                System.out.println("Status: " + (book.getIssuedTo() == null ? "Available" : "Issued"));
                System.out.println("-------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found by that author!");
        }
    }

    // Get total number of books
    public int getTotalBooks() {
        return books.size();
    }

    // Get total number of members
    public int getTotalMembers() {
        return members.size();
    }

    // Get number of available books
    public int getAvailableBooks() {
        int count = 0;
        for (Book book : books) {
            if (book.getIssuedTo() == null) {
                count++;
            }
        }
        return count;
    }

    // Get number of issued books
    public int getIssuedBooksCount() {
        int count = 0;
        for (Book book : books) {
            if (book.getIssuedTo() != null) {
                count++;
            }
        }
        return count;
    }
}
