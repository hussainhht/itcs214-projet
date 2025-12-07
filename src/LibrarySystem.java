import java.util.LinkedList;
import java.util.ListIterator;

public class LibrarySystem {

    private LinkedList<Book> booksList;
    private LinkedList<LibMember> membersList;
    private int booksListSize;
    private int membersListSize;

    // Constructor
    public LibrarySystem() {
        booksList = new LinkedList<Book>();
        membersList = new LinkedList<LibMember>();
        booksListSize = 0;
        membersListSize = 0;
    }

    // addBook
    public boolean addBook(Book book) {
        if (book == null) {
            return false;
        }
         
        // Check if book with same accession number already exists
        ListIterator<Book> it = booksList.listIterator();
        while (it.hasNext()) {
            Book b = it.next();
            if (b.getAccessionNum() == book.getAccessionNum()) {
                return false;
            }
        }

        // Add the book to the list
        booksList.addLast(book);
        booksListSize++;
        return true;
    }

    // deleteBook
    public boolean deleteBook(long accessionNum) {
        if (booksListSize == 0) {
            return false;
        }

        // Remove the book from the list
        ListIterator<Book> it = booksList.listIterator();
        while (it.hasNext()) {
            Book b = it.next();

            // Check if the book is issued
            if (b.getAccessionNum() == accessionNum) {
                if (b.getIssuedTo() != null) {
                    return false;
                }
                it.remove();
                booksListSize--;
                return true;
            }
        }
        return false;
    }

    // addMember
    public boolean addMember(LibMember member) {
        if (member == null) {
            return false;
        }

        // Check if member with same CPR number already exists
        ListIterator<LibMember> it = membersList.listIterator();
        while (it.hasNext()) {
            LibMember m = it.next();
            if (m.getCprNum() == member.getCprNum()) {
                return false;
            }
        }

        membersList.addLast(member);
        membersListSize++;
        return true;
    }

    // deleteMember
    public boolean deleteMember(long cprNum) {
        if (membersListSize == 0) {
            return false;
        }

        // Remove the member from the list
        ListIterator<LibMember> it = membersList.listIterator();
        while (it.hasNext()) {
            LibMember m = it.next();
            if (m.getCprNum() == cprNum) {

                // Check if member has any issued books
                Book[] issued = m.getBooksIssued();
                boolean hasIssued = false;
                if (issued != null) {
                    for (Book b : issued) {
                        if (b != null) {
                            hasIssued = true;
                            break;
                        }
                    }
                }
                if (hasIssued) {
                    return false;
                }

                // Check if any book is issued to the member
                ListIterator<Book> bit = booksList.listIterator();
                while (bit.hasNext()) {
                    Book b = bit.next();
                    // If a book is issued to the member, cannot delete
                    if (b.getIssuedTo() == m) {
                        return false;
                    }
                }

                it.remove();
                membersListSize--;
                return true;
            }
        }
        return false;
    }

    // searchBook
    public int searchBook(long accessionNum) {
        int index = 0;

        // Iterate through the books list to find the book
        ListIterator<Book> it = booksList.listIterator();
        while (it.hasNext()) {
            Book b = it.next();
            if (b.getAccessionNum() == accessionNum) {
                return index;
            }
            index++;
        }
        return -1;
    }

    // searchMember
    public int searchMember(long cprNum) {
        int index = 0;

        // Iterate through the members list to find the member
        ListIterator<LibMember> it = membersList.listIterator();
        while (it.hasNext()) {
            LibMember m = it.next();
            if (m.getCprNum() == cprNum) {
                return index;
            }
            index++;
        }
        return -1;
    }

    // isEmptyBooksList
    public boolean isEmptyBooksList() {
        return booksListSize == 0;
    }

    // isEmptyMembersList
    public boolean isEmptyMembersList() {
        return membersListSize == 0;
    }

    // issueBook
    public boolean issueBook(long accessionNum, long cprNum) {

        // Find the book and member indices
        int bookIndex = searchBook(accessionNum);
        if (bookIndex == -1) {
            return false;
        }

        // Find the member index
        int memberIndex = searchMember(cprNum);
        if (memberIndex == -1) {
            return false;
        }

        // Get the book and member objects
        Book book = booksList.get(bookIndex);
        LibMember member = membersList.get(memberIndex);

        // Check if book is already issued
        if (book.getIssuedTo() != null) {
            return false;
        }

        // Check if member has reached maximum books limit
        if (member.getNumBookIssued() >= 10) {
            return false;
        }

        // Issue the book
        if (member.addIssuedBook(book)) {
            book.setIssuedTo(member);
            return true;
        }

        return false;
    }

    // returnBook
    public boolean returnBook(long accessionNum) {
        int bookIndex = searchBook(accessionNum);
        if (bookIndex == -1) {
            return false;
        }

        Book book = booksList.get(bookIndex);
        LibMember member = book.getIssuedTo();

        // Check if book is actually issued
        if (member == null) {
            return false;
        }

        // Return the book
        if (member.removeIssuedBook(accessionNum)) {
            book.setIssuedTo(null);
            return true;
        }

        return false;
    }

    // printBooksIssued
    public void printBooksIssued(long cprNum) {
        int memberIndex = searchMember(cprNum);
        if (memberIndex == -1) {
            System.out.println("Error: Member not found!");
            return;
        }

        // Get the member object
        LibMember member = membersList.get(memberIndex);
        Book[] issued = member.getBooksIssued();

        if (issued == null || member.getNumBookIssued() == 0) {
            System.out.println("No books currently issued to this member.");
            return;
        }

        System.out.println("\n--- Books Issued to " + member.getFirstName() + " " +
                member.getLastName() + " ---");
        int count = 0;
        for (Book b : issued) {
            if (b != null) {
                count++;
                System.out.println("\nBook #" + count + ":");
                System.out.println(b);
                System.out.println("---");
            }
        }
    }

    // isBookIssued
    public boolean isBookIssued(long accessionNum) {
        int index = searchBook(accessionNum);
        if (index == -1) {
            return false;
        }
        Book book = booksList.get(index);
        return book.getIssuedTo() != null;
    }

    // sizeBooksList
    public int sizeBooksList() {
        return booksListSize;
    }

    // sizeMembersList
    public int sizeMembersList() {
        return membersListSize;
    }
}
