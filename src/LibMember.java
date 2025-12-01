public class LibMember {
    private String firstName;
    private String lastName;
    private char gender;
    private long cprNum;
    private String teleNum;
    private int numBooksIssued;
    private Book[] booksIssued = new Book[10];

    public LibMember() {
        firstName = "";
        lastName = "";
        gender = '-';
        cprNum = -1;
        teleNum = "";
        booksIssued = new Book[10];
        numBooksIssued = 0;
    }

    public LibMember(String f, String l, char g, long c, String t) {
        firstName = f;
        lastName = l;
        gender = g;
        cprNum = c;
        teleNum = t;
        booksIssued = new Book[10];
        numBooksIssued = 0;
    }

    public void setFirstName(String f) {
        firstName = f;
    }

    public void setLastName(String l) {
        lastName = l;
    }

    public void setGender(char g) {
        gender = g;
    }

    public void setCprNum(long cpr) {
        cprNum = cpr;
    }

    public void setTeleNum(String tel) {
        teleNum = tel;
    }

    public void setBooksIssued(Book[] books) {
        booksIssued = books;
    }

    public void setNumBooksIssued(int num) {
        numBooksIssued = num;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public char getGender() {
        return gender;
    }

    public long getCprNum() {
        return cprNum;
    }

    public String getTeleNum() {
        return teleNum;
    }

    public Book[] getBooksIssued() {
        return booksIssued;
    }

    public int getNumBookIssued() {
        return numBooksIssued;
    }

    // Add a book to member's issued books and update counter
    public boolean addIssuedBook(Book book) {
        if (numBooksIssued >= booksIssued.length) {
            return false;
        }
        for (int i = 0; i < booksIssued.length; i++) {
            if (booksIssued[i] == null) {
                booksIssued[i] = book;
                numBooksIssued++;
                return true;
            }
        }
        return false;
    }

    // Remove a book from member's issued books and update counter
    public boolean removeIssuedBook(long accessionNum) {
        for (int i = 0; i < booksIssued.length; i++) {
            if (booksIssued[i] != null && booksIssued[i].getAccessionNum() == accessionNum) {
                booksIssued[i] = null;
                numBooksIssued--;
                return true;
            }
        }
        return false;
    }

    public boolean equals(LibMember check) {
        if (check == null) {
            return false;
        }
        return firstName.equals(check.firstName) &&
                lastName.equals(check.lastName) &&
                gender == check.gender &&
                cprNum == check.cprNum &&
                teleNum.equals(check.teleNum) &&
                numBooksIssued == check.numBooksIssued;
    }

    public String toString() {
        return "Name: " + firstName + " " + lastName +
                "\nGender: " + gender +
                "\nCPR: " + cprNum +
                "\nTelephone: " + teleNum +
                "\nNumber of books issued: " + numBooksIssued;
    }
}
