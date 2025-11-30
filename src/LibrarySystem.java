import java.util.LinkedList;
import java.util.ListIterator;

public class LibrarySystem {

    private LinkedList<Book> booksList;
    private LinkedList<LibMember> membersList;
    private int booksListSize;
    private int membersListSize;

    public LibrarySystem() {
        booksList = new LinkedList<Book>();
        membersList = new LinkedList<LibMember>();
        booksListSize = 0;
        membersListSize = 0;
    }

    //  addBook
    public boolean addBook(Book book) {
        if (book == null) {
            return false;
        }

        ListIterator<Book> it = booksList.listIterator();
        while (it.hasNext()) {
            Book b = it.next();
            if (b.getAccessionNum() == book.getAccessionNum()) {
                return false;
            }
        }

        booksList.addLast(book);
        booksListSize++;
        return true;
    }

    //  deleteBook
    public boolean deleteBook(long accessionNum) {
        if (booksListSize == 0) {
            return false;
        }

        ListIterator<Book> it = booksList.listIterator();
        while (it.hasNext()) {
            Book b = it.next();
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

    //  addMember
    public boolean addMember(LibMember member) {
        if (member == null) {
            return false;
        }

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

    //  deleteMember
    public boolean deleteMember(long cprNum) {
        if (membersListSize == 0) {
            return false;
        }

        ListIterator<LibMember> it = membersList.listIterator();
        while (it.hasNext()) {
            LibMember m = it.next();
            if (m.getCprNum() == cprNum) {

                if (m.getNumBooksIssued() > 0) {
                    return false;
                }

                ListIterator<Book> bit = booksList.listIterator();
                while (bit.hasNext()) {
                    Book b = bit.next();
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

    //  searchBook
    public int searchBook(long accessionNum) {
        int index = 0;
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

    //  searchMember
    public int searchMember(long cprNum) {
        int index = 0;
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

    //  isEmptyBooksList
    public boolean isEmptyBooksList() {
        return booksListSize == 0;
    }

    //  isEmptyMembersList
    public boolean isEmptyMembersList() {
        return membersListSize == 0;
    }

    //  sizeBooksList
    public int sizeBooksList() {
        return booksListSize;
    }

    //  sizeMembersList
    public int sizeMembersList() {
        return membersListSize;
    }

    //  issueBook
    public boolean issueBook(long accessionNum, long cprNum) {
        int bookIndex = searchBook(accessionNum);
        if (bookIndex == -1) return false;

        int memberIndex = searchMember(cprNum);
        if (memberIndex == -1) return false;

        Book book = booksList.get(bookIndex);
        LibMember member = membersList.get(memberIndex);

        if (book.getIssuedTo() != null) return false;

        if (member.getNumBooksIssued() >= 10) return false;

        Book[] issued = member.getBooksIssued();
        int num = member.getNumBooksIssued();

        issued[num] = book;
        member.setNumBooksIssued(num + 1);
        book.setIssuedTo(member);

        return true;
    }

    //  returnBook
    public boolean returnBook(long accessionNum) {
        int bookIndex = searchBook(accessionNum);
        if (bookIndex == -1) return false;

        Book book = booksList.get(bookIndex);
        LibMember member = book.getIssuedTo();

        if (member == null) return false;

        Book[] issued = member.getBooksIssued();
        int num = member.getNumBooksIssued();
        int pos = -1;

        for (int i = 0; i < num; i++) {
            if (issued[i].getAccessionNum() == accessionNum) {
                pos = i;
                break;
            }
        }

        if (pos != -1) {
            for (int i = pos; i < num - 1; i++) {
                issued[i] = issued[i + 1];
            }
            issued[num - 1] = null;
            member.setNumBooksIssued(num - 1);
        }

        book.setIssuedTo(null);
        return true;
    }

    //  printBooksIssued
    public void printBooksIssued(long cprNum) {
        int memberIndex = searchMember(cprNum);
        if (memberIndex == -1) {
            System.out.println("Member not found");
            return;
        }

        LibMember member = membersList.get(memberIndex);
        Book[] issued = member.getBooksIssued();
        int num = member.getNumBooksIssued();

        if (num == 0) {
            System.out.println("No books issued to this member");
            return;
        }

        for (int i = 0; i < num; i++) {
            System.out.println(issued[i]);
        }
    }

    //  isBookIssued
    public boolean isBookIssued(long accessionNum) {
        int index = searchBook(accessionNum);
        if (index == -1) {
            return false;
        }
        Book book = booksList.get(index);
        return book.getIssuedTo() != null;
    }
}
