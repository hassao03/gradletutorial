package book.controller;
import book.model.Book;
import book.model.BookCategory;
import book.repository.BookCategoryRepository;
import book.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.*;

@RestController
public class BookController {

    public static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @RequestMapping("/")
    public String index() {
        return "Book Controller!";
    }

    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookCategoryRepository bookCategoryRepository;

    public BookController(@Autowired BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Bean
    CommandLineRunner init (BookRepository bookRepository){
        return args -> {
            bookRepository.save(new Book(2,"Book2",123));
        };
    }

    @DeleteMapping("/deleteall")
    public void deleteAllBooks() {

        if (bookRepository.findAll()!= null) {
            bookRepository.deleteAll();
        }
    }

    @GetMapping("/add")
    public ResponseEntity<String> add(){
        BookCategory categoryA = new BookCategory("Category A");
        Book book1 = new Book(1, "Book1", 123, categoryA);

        Set<Book> bookAs = new HashSet<>();
        bookAs.add(new Book(1,"Book1", 123, categoryA));
        categoryA.setBooks(bookAs);
        bookCategoryRepository.saveAll(Arrays.asList(categoryA));


        book1.setBookCategory(categoryA);
        bookRepository.saveAll(Arrays.asList(book1));

        return new ResponseEntity<String>("Books are created", HttpStatus.CREATED);
    }

    @GetMapping("/showbooks")
    public ResponseEntity<List<Book>> findAll(){
        List<Book> books = bookRepository.findAll();

        if (books.isEmpty()) {
            return new ResponseEntity<List<Book>>(books, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    @RequestMapping("/search/{id}")
    public ResponseEntity<Book> search(@PathVariable int id){
        //Optional<Book> books = bookRepository.findById(id);

        return bookRepository.findById(id).map(cat -> ResponseEntity.ok().body(cat))
                .orElse(ResponseEntity.noContent().build());
    }

    @RequestMapping("/searchbyname/{name}")
    public ResponseEntity<Book> fetchDataByName(@PathVariable String name){

        return bookRepository.findByName(name).map(cat -> ResponseEntity.ok().body(cat))
                .orElse(ResponseEntity.noContent().build());



    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {

        Optional<Book> books = bookRepository.findById(id);
        if(!books.isPresent())
            return new ResponseEntity<String>("Book doesn't exist", HttpStatus.CREATED);
        bookRepository.deleteById(id);
        return new ResponseEntity<String>("Book deleted", HttpStatus.CREATED);

    }


    @RequestMapping(method = RequestMethod.POST, value = "/updatebook/{id}")
    public ResponseEntity<String> updateBook(@RequestBody Book book, @PathVariable int id) {

        Optional<Book> books = bookRepository.findById(id);
        if(!books.isPresent())
            return new ResponseEntity<String>("Book doesn't exist. Unable to update.", HttpStatus.CREATED);

        bookRepository.save(book);

        return new ResponseEntity<String>("Book updated", HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addbook")
    public ResponseEntity<String> addBook(@RequestBody Book book) {

        List<Book> books = bookRepository.findAll();

        if(!books.contains(book)) {
            bookRepository.save(book);
            return new ResponseEntity<String>("Book added", HttpStatus.CREATED);
        }

        return new ResponseEntity<String>("Book already exists", HttpStatus.BAD_REQUEST);
    }

}


//Use the following in normal terminal while the server is running in intellij
//curl -X POST localhost:8080/books -H 'Content-type:application/json' -d '{"id": "2", "name": "book1", "isbn":"123123"}'
//curl -X PUT localhost:8080/books/2 -H 'Content-type:application/json' -d '{"id": "2", "name": "book1", "isbn":"123123"}'
//curl -X DELETE localhost:8080/books/2 -H 'Content-type:application/json' -d '{"id": "2", "name": "book1", "isbn":"123123"}'

//curl -X POST localhost:9090/addbook -H 'Content-type:application/json' -d '{"id": "2", "name": "book1", "isbn":"123123"}'

//curl -X DELETE localhost:9090/deletebyid/1 -H 'Content-type:application/json' -d '{"id": "1", "name": "book1", "isbn":"123123"}'
//curl -X PUT localhost:9090/updatebook/1 -H 'Content-type:application/json' -d '{"id": "1", "name": "book1", "isbn":"123123"}'
/*


CREATE TABLE BOOK(
   ID INT PRIMARY KEY     NOT NULL,
   NAME           TEXT    NOT NULL,
   ISBN           INT     NOT NULL
);

 */