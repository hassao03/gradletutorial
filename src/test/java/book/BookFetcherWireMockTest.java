package book;

import book.fetcher.BookFetcher;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import javax.naming.Reference;
import javax.validation.constraints.AssertTrue;
import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

public class BookFetcherWireMockTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8090);

    private BookFetcher underTest;

    @Before
    public void setUp() {
        this.underTest = new BookFetcher("http://localhost:8090");
    }

    @Test
    public void testGetFromEndpoint() throws IOException {
        // Given
        // Tell wiremock what to return
        wireMockRule.givenThat(get(urlEqualTo("/books/1"))
                        .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/plain")
                        .withBody("{ \"id\": \"1\", \"name\": \"Book1\",\"isbn\": \"123\" }")));

        // When
        // hitting the /books/1 endpoint
        underTest.getFromEndpoint("1");

        // Then
        // verify that atleast 1 request has been made
        verify(exactly(1), getRequestedFor(urlEqualTo("/books/1")));

        // check status code
        Assert.assertEquals("{ \"id\": \"1\", \"name\": \"Book1\",\"isbn\": \"123\" }", underTest.getFromEndpoint("1"));

    }

    @Test
    public void testGetAllFromEndpoint() throws IOException {

        // Given
        // Tell wiremock what to return
        givenThat(get(urlEqualTo("/books/all"))
                        .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader("Content-Type", "text/plain")
                        .withBody("{\"status\":\"Error\",\"message\":\"Endpoint not found\"}")));

        // When
        // hit the /books/1 endpoint
        underTest.getAllFromEndpoint();

        // Then
        // verify that atleast 1 request has been made
        verify(exactly(1), getRequestedFor(urlEqualTo("/books/all")));

        // check content
        Assert.assertEquals("{\"status\":\"Error\",\"message\":\"Endpoint not found\"}", underTest.getAllFromEndpoint());
    }



}
