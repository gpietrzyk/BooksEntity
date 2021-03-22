package pl.grzegorzpietrzyk.model;

public class BooksEntityBuilder {
    private int bookID;
    private String title;
    private String author;
    private String publisher;

    public BooksEntityBuilder setBookID(int bookID){
        this.bookID = bookID;
        return this;
    }

    public BooksEntityBuilder setTitle(String title){
        this.title = title;
        return this;
    }

    public BooksEntityBuilder setAuthor(String author){
        this.author = author;
        return this;
    }

    public BooksEntityBuilder setPublisher(String publisher){
        this.publisher = publisher;
        return this;
    }

    public BooksEntity build(){
        return new BooksEntity(bookID,title,author,publisher);
    }
}
