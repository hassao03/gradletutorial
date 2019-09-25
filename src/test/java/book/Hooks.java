package book;

import book.controller.BookController;
import book.repository.BookRepository;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.springframework.http.HttpHeaders;

public class Hooks {
    public HttpHeaders httpHeaders;
    BookRepository bookRepository;
    BookController bookController;

    public Hooks(BookController bookController) {
        this.bookController = bookController;
    }

    @Before
    public void beforeScenario() {
        System.out.println("This will run before the Scenario");
        httpHeaders = new HttpHeaders();


        bookController.deleteAllBooks();
    }

    @After
    public void afterScenario() {
        System.out.println("This will run after the Scenario");
    }
}