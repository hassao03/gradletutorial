import book.CucumberConfig;
import book.controller.BookController;
import book.model.Book;
import book.model.BookCategory;
import book.model.BookUI;
import book.repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.*;
import gherkin.deps.com.google.gson.JsonArray;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.mockito.internal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


public class BookStepDefinitions extends CucumberConfig {

    private ResponseEntity<String> responseEntity;
    private BookController bookController;
    BookRepository bookRepository;
    private TestRestTemplate restTemplate;
    List<Book> bookList = new ArrayList<>();


    public BookStepDefinitions(BookController bookController) {
        this.bookController = bookController;
    }

    public String getCompleteEndPoint(String URI) {
        return staticURL + port + URI;
    }

    @When("I call the list all books endpoint")
    public void iCallTheListAllBooksEndpoint() {
        String URI = "/add";
        responseEntity = testRestTemplate.getForEntity(getCompleteEndPoint(URI), String.class);
    }

    @Then("I receive a response with status code {int}")
    public void iReceiveAResponseWithStatusCode(int statusCode) {
        assertEquals(statusCode, responseEntity.getStatusCode().value());

    }

    @And("I should get a list of books created")
    public void iShouldGetAListOfBooks() {

        assertEquals(true, responseEntity.getBody().contains("Books are created"));
    }

    @And("the JSON paths below are satisfied:")
    public void theJSONPathsBelowAreSatisfied(final List<Map<String, String>> jsonPaths) throws IOException {
        String URI = "/showbooks";
        responseEntity = testRestTemplate.getForEntity(getCompleteEndPoint(URI), String.class);

        String rs = responseEntity.getBody();
        //assertEquals(rs,jsonPaths);
        String key = "";
        String value = "";
        String concat = "";

        for (int i = 0; i < jsonPaths.size(); i++) {
            Map<String, String> myMap = jsonPaths.get(i);
            //System.out.println("Data For Map" + i);
            for (Map.Entry<String, String> entrySet : myMap.entrySet()) {
                System.out.println("Key: " + entrySet.getKey() + "\nValue: " + entrySet.getValue());
                key = entrySet.getKey();
                value = entrySet.getValue();
                JsonArray js = new JsonArray();

            }
        }

        String jsonPathsRmSpace = jsonPaths.toString().replaceAll("\\s", "");
        String jsonParsed = jsonPathsRmSpace.toString().replace("=", ":");
        String rsRmQuotes = rs.replaceAll("\"", "");
        assertEquals(rsRmQuotes, jsonParsed);
    }

    @When("I call the add books endpoint")
    public void iCallTheAddBooksEndpoint() {
        String URI = "/addbook";
        responseEntity = testRestTemplate.getForEntity(getCompleteEndPoint(URI), String.class);

    }

    @Then("I should receive a response with status code {int}")
    public void iShouldReceiveAResponseWithStatusCode(int statusCode) {
        assertEquals(statusCode, responseEntity.getStatusCode().value());
    }

    @Then("I should be able to add a book")
    public void iShouldBeAbleToAddABook() throws URISyntaxException, JsonProcessingException {

        String URI = "/addbook";
        Book book = new Book(2, "Book2", 555);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<Book> request = new HttpEntity<>(book, headers);

        responseEntity = testRestTemplate.postForEntity(getCompleteEndPoint(URI), request, String.class);
        //String getBody = responseEntity.getBody();
        //assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.getBody().contains("Book added"));

    }



    @And("I should get a list of books added")
    public void iShouldGetAListOfBooksAdded() {
        assertNotNull(responseEntity.getBody());
    }

    @Then("I should be able to find this book in the system by name")
    public void iShouldBeAbleToFindThisBookInTheSystemByName(final List<Map<String, String>> jsonPaths) throws IOException {
        String URI = "/searchbyname/Book2";
        responseEntity = this.testRestTemplate.getForEntity(getCompleteEndPoint(URI), String.class);

        for (int i = 0; i < jsonPaths.size(); i++) {

            String jsonPathsRmSpace = jsonPaths.toString().replaceAll("\\s", "");
            String jsonParsed = jsonPathsRmSpace.toString().replace("=", ":");
            String rs = responseEntity.getBody();
            String rsRmQuotes = rs.replaceAll("\"", "");
            assertEquals(jsonParsed,rsRmQuotes);
        }

    }


    @Given("I update a book with id <id> with a new name <name>")
    public void iUpdateABookWithIdIdWithANewNameName() {

        String URI = "/updatebook/2";
        Book book = new Book(2, "Book4", 444);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<Book> request = new HttpEntity<>(book, headers);

        responseEntity = testRestTemplate.postForEntity(getCompleteEndPoint(URI), request, String.class);
        String getBody = responseEntity.getBody();
        //assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.getBody().contains("Book updated"));

    }


    @Then("A book with id <id> should have a name <name>")
    public void aBookWithIdIdShouldHaveANameName(final List<Map<String, String>> jsonPaths) throws IOException {
        String URI = "/search/2";
        responseEntity = this.testRestTemplate.getForEntity(getCompleteEndPoint(URI), String.class);

        for (int i = 0; i < jsonPaths.size(); i++) {

            String jsonPathsRmSpace = jsonPaths.toString().replaceAll("\\s", "");
            String jsonParsed = jsonPathsRmSpace.toString().replace("=", ":");
            String rs = responseEntity.getBody();
            String rsRmQuotes = rs.replaceAll("\"", "");
            assertEquals(jsonParsed,rsRmQuotes);
        }

    }
}


//curl -X POST localhost:9090/addbook -H 'Content-type:application/json' -d '{"id": "2", "name": "book1", "isbn":"123123"}'
