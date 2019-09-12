package book;

import model.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.*;


public class BookServiceTest {

    private BookService bookService;

    @Before
    public void setUp() {
        this.bookService = new BookService();
    }

    @Test
    public void addBook(){

        Book book1 = new Book();

        List<Book> listBook = new ArrayList<Book>();
        listBook.add(book1);

        bookService = new BookService(listBook);
        assertEquals(bookService.listSize(), 1);

    }

    @Test
    public void getBook(){

        List<Book> results = bookService.getAllBooks();
        Book resultBook = results.get(1);
        assertEquals("1", resultBook.getId());


    }

    @Test
    public void updateBook(){
        Book book1 = new Book();
        book1.setName("book2");
        book1.setId("2");
        bookService.updateBook(book1, "1");

        List<Book> results = bookService.getAllBooks();
        Book resultBook = results.get(0);
        assertEquals("2", resultBook.getId());
        assertEquals("book2", resultBook.getName());

    }

    @Test
    public void deleteBook(){
        Book book1 = new Book();
        book1.setName("book2");
        book1.setId("2");
        bookService.addBook(book1);

        bookService.deleteBook("1");
        List<Book> results = bookService.getAllBooks();
        Book resultBook = results.get(0);


        assertEquals("2", resultBook.getId());


    }

}