import book.CucumberConfig;
import book.Hooks;
import book.controller.BookController;
import book.fetcher.BookFetcher;
import book.model.Book;
import book.repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import cucumber.api.java.en.*;
import gherkin.deps.com.google.gson.JsonArray;
import org.junit.Assert;
import org.junit.Rule;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.*;
import static org.springframework.http.HttpStatus.CREATED;


public class BookStepDefinitions extends CucumberConfig {

    private ResponseEntity<String> responseEntity;
    private BookController bookController;
    Hooks hooks;

    public BookStepDefinitions(BookController bookController, Hooks hooks) {
        this.bookController = bookController;
        this.hooks = hooks;

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
        assertEquals(jsonParsed, rsRmQuotes);


    }

    @Given("The db is empty")
    public void theDbIsEmpty() {
        String URI = "/showbooks";
        responseEntity = testRestTemplate.getForEntity(getCompleteEndPoint(URI), String.class);
        assertEquals("[]", responseEntity.getBody());
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
        assertEquals(CREATED, responseEntity.getStatusCode());
        //assertEquals(true, responseEntity.getBody().contains("Book added"));

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

            String jsonPathsRmSpace = jsonPaths.toString().
                    replaceAll("\\s", "").
                    replace("=", ":").
                    replace("[", "").
                    replace("]", "");
            String rs = responseEntity.getBody();
            String rsRmQuotes = rs.replaceAll("\"", "");
            assertEquals(jsonPathsRmSpace, rsRmQuotes);
        }

    }

    @Given("I add a new book")
    public void IAddANewBook() {

        String URI = "/addbook";
        Book book = new Book(2, "Book2", 555);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<Book> request = new HttpEntity<>(book, headers);

        responseEntity = testRestTemplate.postForEntity(getCompleteEndPoint(URI), request, String.class);


    }

    @Given("I update a book with a given id")
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


    @Then("A book with the given id should exist")
    public void aBookWithIdIdShouldHaveANameName(final List<Map<String, String>> jsonPaths) throws IOException {
        String URI = "/search/2";
        responseEntity = this.testRestTemplate.getForEntity(getCompleteEndPoint(URI), String.class);

        for (int i = 0; i < jsonPaths.size(); i++) {

            String jsonPathsRmSpace = jsonPaths.toString().replaceAll("\\s", "");
            String jsonParsed = jsonPathsRmSpace.toString().replace("=", ":");
            String str = jsonParsed.replaceAll("\\[|\\]", "");
            String rs = responseEntity.getBody();
            String rsRmQuotes = rs.replaceAll("\"", "");
            assertEquals(str, rsRmQuotes);


        }


    }
    @Given("I add a book with id {int} in the db")
    public void iAddABookWithIdInTheDb(int id) {
        String URI = "/addbook";
        Book book = new Book(2, "Book2", 555);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<Book> request = new HttpEntity<>(book, headers);

        responseEntity = testRestTemplate.postForEntity(getCompleteEndPoint(URI), request, String.class);

    }


    @When("I delete a book with id {int}")
    public void iDeleteABookWithId(int id) {

        String URI = "/deletebyid/2";

        Map<String, Integer> params = new HashMap<>();
        params.put("id", id);
        testRestTemplate.delete(getCompleteEndPoint(URI), params);


    }


    @And("I call show books i should receive {int}")
    public void iCallShowBooksIShouldReceive(int statusCode) {
        String URI = "/search/2";
        responseEntity = this.testRestTemplate.getForEntity(getCompleteEndPoint(URI), String.class);
        assertEquals(statusCode, responseEntity.getStatusCode().value());


    }

    @Given("The database is empty")
    public void theDatabaseIsEmpty() {
        String URI = "/showbooks";
        responseEntity = testRestTemplate.getForEntity(getCompleteEndPoint(URI), String.class);
        assertEquals("[]", responseEntity.getBody());
    }

    @When("I search for a book with id {int}")
    public void iSearchForABookWithId(int id) {
        String URI = "/search/" + id;
        responseEntity = this.testRestTemplate.getForEntity(getCompleteEndPoint(URI), String.class);
        assertEquals("Book with such id does not exist", responseEntity.getBody());
    }

    @Then("I should receive a response with status code of {int}")
    public void iShouldReceiveAResponseWithStatusCodeOf(int statusCode) {
        assertEquals(statusCode, responseEntity.getStatusCode().value());
    }

    @Then("I create a stub with wiremock")
    public void iCreateAStubWithWiremock() throws IOException {

        hooks.wireMockServer.stubFor(get(urlEqualTo("/books/3"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/plain")
                        .withBody("{ \"id\": \"3\", \"name\": \"Book3\",\"isbn\": \"333\" }")));

        hooks.bookFetcher.getFromEndpoint("3");

        Assert.assertEquals("{ \"id\": \"3\", \"name\": \"Book3\",\"isbn\": \"333\" }", hooks.bookFetcher.getFromEndpoint("3"));

    }


    @And("I should be able to find the book with id {int} in the system")
    public void iShouldBeAbleToFindTheBookWithIdInTheSystem(int id) throws IOException {
        String URI = "/search/" + id;
        responseEntity = this.testRestTemplate.getForEntity(getCompleteEndPoint(URI), String.class);

        //assertEquals("{ \"id\": 3, \"name\": Book3,\"isbn\": 333 }", responseEntity.getBody());
        assertEquals("Book found from book fetcher", responseEntity.getBody());

    }

    @And("return status code {int}")
    public void returnStatusCode(int statusCode) {

        assertEquals(statusCode, responseEntity.getStatusCode().value());
    }



}
