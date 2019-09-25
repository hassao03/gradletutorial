package book;

import book.controller.BookController;
import book.model.Book;
import book.repository.BookRepository;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class BookControllerMockSpringContextTest {

    @MockBean
    BookRepository bookRepository;
    @Autowired
    BookController bookController;

    private ResponseEntity<String> responseEntity;


    @Mock
    private TestRestTemplate restTemplate;


    @LocalServerPort
    private int port;

    HttpHeaders headers;
    HttpEntity<Book> entity;
    ResponseEntity<String> response;

    @Test
    public void testSearchByID(){

        when(bookRepository.findAll()).thenReturn(Collections.emptyList());
        assertEquals(HttpStatus.NO_CONTENT,bookController.search(2));
    }

    @Test
    public void whenBookExistsReturnName() throws JSONException {
        List<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(1, "Book1", 123));
        when(bookRepository.findAll()).thenReturn(bookList);
        response =
                restTemplate.exchange(
                        createURLWithPort("/showbooks"),
                        HttpMethod.GET, entity, String.class);

        assertEquals("Book2", response.getBody().contains("Book2"));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
