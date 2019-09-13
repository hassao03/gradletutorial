package book;

import book.model.BookUI;
import book.Service.BookService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.*;


public class BookUIServiceTest {

    private BookService bookService;
    int idIndex = 1;

    //This runs before any test method.
    @Before
    public void setUp() {
        this.bookService = new BookService();
    }


    @Test
    public void getBook(){

        Map<Integer, BookUI> results = bookService.getAllBooks();
        BookUI resultBook = results.get(1);
        assertEquals(1, resultBook.getId());


    }

    @Test
    public void addBook(){

        BookUI book1 = new BookUI();

        List<BookUI> listBook = new ArrayList<BookUI>();
        listBook.add(book1);
        assertEquals(bookService.listSize(), 1);

    }



    @Test
    public void updateBook(){
        BookUI book1 = new BookUI();
        book1.setName("book2");
        book1.setId(2);

        bookService.updateBook(1,book1);

        Map<Integer, BookUI> results = bookService.getAllBooks();
        BookUI resultBook = results.get(1);
        assertEquals(2, resultBook.getId());
        assertEquals("book2", resultBook.getName());

    }

    @Test
    public void deleteBook(){
        BookUI book1 = new BookUI();
        book1.setName("book2");
        book1.setId(2);
        bookService.addBook(book1);

        bookService.deleteBook(2);
        Map<Integer, BookUI> results = bookService.getAllBooks();
        BookUI resultBook = results.get(1);


        assertEquals(1, resultBook.getId());


    }

}