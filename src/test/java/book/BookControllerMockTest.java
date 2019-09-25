package book;

import book.controller.BookController;
import book.model.Book;
import book.repository.BookRepository;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerMockTest {

    @Mock
    BookRepository bookRepository;
    @InjectMocks
    BookController bookController;

    @Test
    public void testFindAll() {
        // Given
        List<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(1, "Book1", 123));
        given(bookRepository.findAll()).willReturn(bookList);

        // When
        ResponseEntity<List<Book>> book = bookController.findAll();

        // Then
        assertEquals(bookList, book.getBody());
        verify(bookRepository,times(1)).findAll();

    }


    @Test
    public void testAddBook() {
        //Given
        given(bookRepository.findAll()).willReturn(Collections.EMPTY_LIST);

        //When
        ResponseEntity<String> book = bookController.addBook(new Book(1, "Book1", 123));

        //Then
        assertEquals(CREATED, book.getStatusCode());

    }

    @Test
    public void testSearchById() {
        // Given
        given(bookRepository.findById(1)).willReturn(Optional.of(new Book(1, "Book1", 123)));

        // When
        ResponseEntity<Book> book = bookController.search(1);

        // Then
        assertEquals(OK, book.getStatusCode());
        Mockito.verify(bookRepository, times(1)).findById(anyInt());


    }

    @Test
    public void deleteBookById() {
        // Given
        List<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(1, "Book1", 123));
        given(bookRepository.findById(1)).willReturn(Optional.of(new Book(1, "Book1", 123)));

        // When
        ResponseEntity<String> book = bookController.deleteById(1);

        // Then
        assertEquals("Book deleted", book.getBody());
        verify(bookRepository, times(1)).deleteById(anyInt());

    }

    @Test
    public void testUpdateBook() {
        // Given
        given(bookRepository.findById(1)).willReturn(Optional.of(new Book(1, "Book1", 123)));

        //When
        ResponseEntity<String> book = bookController.updateBook(new Book(1, "Book1", 234), 1);

        //Then
        assertEquals("Book updated", book.getBody());

    }

}
