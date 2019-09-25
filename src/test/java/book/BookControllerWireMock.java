package book;

import book.controller.BookController;
import book.model.Book;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles(value = "integration")
public class BookControllerWireMock {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8090);

    @Autowired
    private TestRestTemplate restTemplate;
    private WireMockServer wireMockServer;

    @Autowired
    private BookController bookController;
    private ResponseEntity response;

    @Test
    public void setupStub() {

        stubFor(get(urlEqualTo("/showbooks"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withStatus(200)
                        .withBody("You've reached a valid WireMock endpoint")));
    }
    @Test
    public void findAllTest() throws Exception {

        //This is the same as the localhost:8080
        stubFor(get(urlEqualTo("/showbooks")).withHeader("Accept", equalTo("application/json")).willReturn(aResponse().withStatus(200)
                .withBody("{ \"id\": \"1\", \"name\": \"Book1\",\"isbn\": \"123\" }")));

        String url = "http://localhost:8090/showbooks";
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Accept", "application/json");
        HttpResponse response = client.execute(request);

        verify(getRequestedFor(urlPathEqualTo("/showbooks"))
                .withHeader("Content-Type", equalTo("application/json")));



    }

    @Test
    public void testAddBook() {
        final String bookAdditionPath = "/addbook";
        WireMock.stubFor(post(bookAdditionPath).willReturn(ok()));

        this.bookController.addBook(new Book(1,"Book1", 123));

        verify(exactly(1), getRequestedFor(urlEqualTo(bookAdditionPath)));
    }


    @Test
    public void findAll() {
        stubFor(get(urlEqualTo("/api/resource/"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", TEXT_PLAIN_VALUE)
                        .withBody("test")));

        response = restTemplate.getForEntity("http://localhost:8089/showbooks/", String.class);

        Assert.assertEquals("Verify Response Body", response.getBody());
        Assert.assertEquals("Verify Status Code", response.getStatusCode().equals(HttpStatus.OK));

        verify(getRequestedFor(urlMatching("/api/resource/.*")));
    }



}