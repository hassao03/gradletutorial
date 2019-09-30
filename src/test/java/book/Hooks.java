package book;

import book.controller.BookController;
import book.fetcher.BookFetcher;
import book.repository.BookRepository;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.springframework.http.HttpHeaders;
import com.github.tomakehurst.wiremock.WireMockServer;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class Hooks {
    public HttpHeaders httpHeaders;
    BookRepository bookRepository;
    BookController bookController;
    int wireMockPort = 9090;
    public WireMockServer wireMockServer;
    public BookFetcher bookFetcher;


    public Hooks(BookController bookController) {
        this.bookController = bookController;

    }

    @Before
    public void beforeScenario() {
        System.out.println("This will run before the Scenario");
        httpHeaders = new HttpHeaders();
        bookController.deleteAllBooks();

        wireMockServer = new WireMockServer(
                wireMockConfig().port(wireMockPort)
        );
        wireMockServer.start();

        this.bookFetcher = new BookFetcher("http://localhost:"+wireMockPort);
    }

    @After
    public void afterScenario() {

        System.out.println("This will run after the Scenario");
        wireMockServer.stop();
    }




}