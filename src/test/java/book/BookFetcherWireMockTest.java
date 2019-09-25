package book;

import book.controller.BookController;
import book.model.Book;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BookFetcherWireMockTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8090);

    @Mock
    BookFetcherWiremock bookFetcherWiremock;

    @Test
    public void testGet(){

        //verify
        verify(get(urlEqualTo("/books/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/plain")
                        .withBodyFile("response.json")));


        verify(exactly(1), getRequestedFor(urlEqualTo("/books/1")));
    }

    @Test
    public void testGetNotPresent(){

        //verify
        verify(get(urlEqualTo("/books/2"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader("Content-Type", "text/plain")
                        .withBody("{ Not found }")));

    }


}
