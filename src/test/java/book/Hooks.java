package book;

import book.controller.BookController;
import book.fetcher.BookFetcher;
import book.repository.BookRepository;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.Rule;
import org.springframework.http.HttpHeaders;

public class Hooks {
    public HttpHeaders httpHeaders;
    BookRepository bookRepository;
    BookController bookController;
    //public BookFetcher bookFetcher;



    public Hooks(BookController bookController) {
        this.bookController = bookController;

    }

    @Before
    public void beforeScenario() {
        System.out.println("This will run before the Scenario");
        httpHeaders = new HttpHeaders();
        bookController.deleteAllBooks();
        //this.bookFetcher = new BookFetcher("http://localhost:8090");
    }

    @After
    public void afterScenario() {

        System.out.println("This will run after the Scenario");
    }


}