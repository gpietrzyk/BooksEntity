package pl.grzegorzpietrzyk.service;

import org.junit.Before;
import org.junit.Test;
import pl.grzegorzpietrzyk.model.BooksEntity;
import pl.grzegorzpietrzyk.model.BooksEntityBuilder;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


public class BooksServiceTest {
    private static final String UpdateData = "Hobbit;J.R.R. Tolkien;Iskry";
    private static final String CreateData = "Dziennik 1954;Leopold Tyrmand;MG";

    private BooksEntity book;
    private BooksEntity resultBook;
    private List<BooksEntity> books;
    private List<BooksEntity> resultBooks;
    private BooksEntity bookAfterUpdate;

    @Before
    public void setUp() throws Exception {
        books = BooksService.displayBooks();
    }

    @Test
    public void test_createBook() {

        book = new BooksEntityBuilder()
                .setBookID(books.get(books.size()-1).getBookID())
                .setTitle("Dziennik 1954")
                .setAuthor("Leopold Tyrmand")
                .setPublisher("MG")
                .build();

        BooksService.createBook(CreateData);
        resultBook = BooksService.displayBooks().get(books.size());

        assertEquals(book.getTitle(),resultBook.getTitle());
        assertEquals(book.getAuthor(),resultBook.getAuthor());
        assertEquals(book.getPublishername(),resultBook.getPublishername());
    }

    @Test
    public void test_deleteBookByID() {
        int id = books.get(books.size() - 1).getBookID();
        book = BooksService.findBookByID(id);

        BooksService.deleteBookByID(id);

        resultBooks = BooksService.displayBooks();
        resultBook = resultBooks.stream()
                .filter(booksEntity -> booksEntity.getBookID()==id)
                .collect(Collectors.toList())
                .stream()
                .findFirst()
                .orElse(null);

        assertTrue(resultBooks.size() < books.size());
        assertEquals(resultBook,null);

    }

    @Test
    public void test_updateBookByID(){
        int id = 3;
        String [] data = UpdateData.split(";");
        bookAfterUpdate = new BooksEntityBuilder()
                .setBookID(id)
                .setTitle(data[0])
                .setAuthor(data[1])
                .setPublisher(data[2])
                .build();

        book = BooksService.findBookByID(3);
        resultBook = BooksService.updateBook(3,UpdateData);

        assertEquals(bookAfterUpdate, resultBook);


    }
}