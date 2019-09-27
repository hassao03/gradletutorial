package book;

import book.fetcher.BookFetcher;
import book.model.Book;
import book.repository.BookRepository;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import cucumber.api.java.*;

import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberConfig {

    @Autowired
    public TestRestTemplate testRestTemplate;
    @Autowired

    @LocalServerPort
    public int port;

    public String staticURL = "http://localhost:";
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8090);

}