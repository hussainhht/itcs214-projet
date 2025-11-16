public class LibMember {
    
    private String firstName;
    private String lastName;
    private char gender;
    private long cprNum;
    private String teleNum;
    private int numBookIssued;
    private Book [] booksIssued = new Book[10];

    public LibMember(){
        firstName="";
        lastName="";
        gender='-';
        cprNum=-1;
        teleNum="";
        booksIssued = new Book[10];
        numBookIssued=-1;
    }//defult constracture

    public LibMember(String f, String l, char g, long c, String t){
        firstName=f;
        lastName=l;
        gender=g;
        cprNum=c;
        teleNum=t;
        booksIssued = new Book[10];
        numBookIssued=0;
    }//second constractor

    public void setFirstName(String f) { firstName = f; }
    public void setLastName(String l) { lastName = l; }
    public void setGender(char g) { gender = g; }
    public void setCprNum(long cpr) { cprNum = cpr; }
    public void setTeleNum(String tel) { teleNum = tel; }
    public void setBooksIssued(Book[] books) { booksIssued = books; }
    public void setNumBooksIssued(int num) { numBookIssued = num; }
    //all set methods

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public char getGender() { return gender; }
    public long getCprNum() { return cprNum; }
    public String getTeleNum() { return teleNum; }
    public Book[] getBooksIssued() { return booksIssued; }
    public int getNumBookIssued() { return numBookIssued; }
    //all get methods

    public boolean equals(LibMember check){
        if(check == null){
            return false;
        }
        boolean same= false;
        if(firstName.equals(check.firstName) && lastName.equals(check.lastName) && gender == check.gender && cprNum == check.cprNum && teleNum.equals(check.teleNum) && numBookIssued == check.numBookIssued){
            same = true;
        }
        return same;
    }

    public String toString(){
        String converted= "Name: " + firstName + " " + lastName + "\ngender: " + gender + "\nCPR: " +cprNum + "\nTelephone: " +teleNum + "\nNumber of books issued: " + numBookIssued; 
        return converted;
    }
}
