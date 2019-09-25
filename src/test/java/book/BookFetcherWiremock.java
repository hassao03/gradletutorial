package book;

import book.model.Book;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class BookFetcherWiremock extends WireMockConfig{

    //private String endpointURL;
    private ResponseEntity<List<Book>> response;

    public int get(String id) {

        response = testRestTemplate.exchange(getEndpoint("books/1"), HttpMethod.GET,null,
                new ParameterizedTypeReference<List<Book>>(){}
        );

        return response.getStatusCode().value();

    }

    public List<Book>  getAll() {

        response = testRestTemplate.exchange(getEndpoint("books/all"), HttpMethod.GET,null,
                new ParameterizedTypeReference<List<Book>>(){}
        );

        return response.getBody();
    }


    public String getEndpoint(String URI) {
        return endpointURL + port + URI;
    }


}
