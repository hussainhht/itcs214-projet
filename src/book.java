public class Book  {
 private String title;
 private String author1;
 private String author2;
 private String publisher;
 private int yearPublication;
 private String isbn;
 private long accessionNum;
 private LibMember issuedTo;


    public Book(){
    title="";
    author1="";
    author2="";
    publisher="";
    yearPublication=0;
    isbn="";
    accessionNum=0;
    issuedTo=null;
    }

    public Book(String t, String a1, String a2, String p, int y, String i, long ac){
        title=t;
        author1=a1;
        author2=a2;
        publisher=p;
        yearPublication=y;
        isbn=i;
        accessionNum=ac;
        issuedTo=null;
    }

    public void setTitile(String t){title=t;}
    public void setAuthor1(String a1){author1 = a1;}
    public void setAuthor2(String a2){author2 = a2;}
    public void setPublisher(String p){publisher = p;}
    public void setYearPublication(int y){yearPublication = y;}
    public void setIsbn(String i){isbn = i;}
    public void setAccessionNum(long ac){accessionNum = ac;}
    public void setIssuedTo(LibMember m){issuedTo = m;}
    //all set methods

    public String getTitle(){ return title;}
    public String getAuthor1(){return author1;}
    public String getAuthor2(){return author2;}
    public String getPublisher(){return publisher;}
    public int getYearPublication(){return yearPublication;}
    public String getIsbn(){return isbn;}
    public long getAccessionNum(){return accessionNum;}
    public LibMember getIssuedTo(){return issuedTo;}
    //all get methods


    public boolean equals(Book b){
        boolean isequal=false;
        if(title.equals(b.title) && author1.equals(b.author1) && author2.equals(b.author2) && publisher.equals(b.publisher) && yearPublication == b.yearPublication && isbn.equals(b.isbn) && accessionNum == b.accessionNum && issuedTo == b.issuedTo)
        {isequal=true;}
        return isequal;
    }


    public String toString() {
    String con= "Title: " + title + "\nAuthor1: " + author1 + "\nAuthor2: " + author2 + "\nPublisher: " + publisher + "\nYear: " + yearPublication + "\nISBN: " + isbn + "\nAccession#: " + accessionNum + "\nIssued To: " + issuedTo;
    return con;
    }
    
}
