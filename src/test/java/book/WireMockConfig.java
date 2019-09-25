package book;

import book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WireMockConfig {

    @Autowired
    public TestRestTemplate testRestTemplate;

    @LocalServerPort
    public int port;

    public String endpointURL = "http://localhost:";


}