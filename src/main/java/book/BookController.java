package book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import model.Book;

@RestController
public class BookController {

    @RequestMapping("/")
    public String index() {

        return "Book Controller!";
    }

    @Autowired
    BookService bookService;


    //delete a book
    @RequestMapping(method = RequestMethod.DELETE, value = "/books/{id}")
    public void deleteBook(@PathVariable int id){

        bookService.deleteBook(id);
    }



    //update a book

    @RequestMapping(method = RequestMethod.PUT, value = "/books/{id}")
    public void updateBook(@PathVariable int id, @RequestBody Book book){
         bookService.updateBook(id, book);
    }




    //return book by id
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/books/{id}")
    public Book getBook(@PathVariable int id){

        return bookService.getBook(id);
    }



    //return all books
    //@ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.GET, value = "/books")
    public ResponseEntity<Map<Integer, Book>> getAllBooks() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "BookController");

        return ResponseEntity.accepted().headers(headers).body(bookService.getAllBooks());


    }

    //add a book
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, value = "/books")
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }





}


//Use the following in normal terminal while the server is running in intellij
//curl -X POST localhost:8080/books -H 'Content-type:application/json' -d '{"id": "2", "name": "book1", "isbn":"123123"}'
//curl -X PUT localhost:8080/books/2 -H 'Content-type:application/json' -d '{"id": "2", "name": "book1", "isbn":"123123"}'
//curl -X DELETE localhost:8080/books/2 -H 'Content-type:application/json' -d '{"id": "2", "name": "book1", "isbn":"123123"}'