package book.controller;
import book.model.Book;
import book.model.BookCategory;
import book.model.BookUI;
import book.repository.BookCategoryRepository;
import book.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

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

    /*@GetMapping("/add")
    public ResponseEntity<String> add(){
        List<Book> book = new ArrayList<>(Arrays.asList(new Book(1,"Book1",999)));
        bookRepository.saveAll(book);

        return new ResponseEntity<String>("Books are created", HttpStatus.OK);

    }*/

    @GetMapping("/add")
    public String add(){
        BookCategory categoryA = new BookCategory("Category A");
        Book book1 = new Book(1, "Book1", 123, categoryA);

        Set<Book> bookAs = new HashSet<>();
        bookAs.add(new Book(1,"Book1", 123, categoryA));
        categoryA.setBooks(bookAs);
        bookCategoryRepository.saveAll(Arrays.asList(categoryA));


        book1.setBookCategory(categoryA);
        bookRepository.saveAll(Arrays.asList(book1));



        return "Books are created";
    }

    @GetMapping("/showbooks")
    public ResponseEntity<List<BookUI>> findAll(){

        List<Book> books = bookRepository.findAll();
        List<BookUI> bookUI = new ArrayList<>();

        for (Book book : books) {
            bookUI.add(new BookUI(book.getId(),book.getName(),book.getIsbn()));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        if(books != null) return new ResponseEntity<List<BookUI>>(bookUI, headers, HttpStatus.OK);
        else return new ResponseEntity<>(headers, HttpStatus.OK);

    }



    @RequestMapping("/search/{id}")
    public ResponseEntity<List<BookUI>> search(@PathVariable int id){
        List<Book> books = bookRepository.findById(id);
        List<BookUI> bookUI = new ArrayList<>();

        for (Book book : books) {
            bookUI.add(new BookUI(book.getId(),book.getName(),book.getIsbn()));
        }

        return new ResponseEntity<List<BookUI>>(bookUI, HttpStatus.OK);
    }

    @RequestMapping("/searchbyname/{name}")
    public ResponseEntity<List<BookUI>> fetchDataByName(@PathVariable String name){

        List<Book> books = bookRepository.findByName(name);
        List<BookUI> bookUI = new ArrayList<>();

        for (Book book : books) {
            bookUI.add(new BookUI(book.getId(),book.getName(),book.getIsbn()));
        }

        return new ResponseEntity<List<BookUI>>(bookUI, HttpStatus.OK);
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {

        bookRepository.deleteById(id);
        return new ResponseEntity<String>("Book deleted", HttpStatus.CREATED);

    }

    //@PutMapping("/updatebook/{id}")
    @RequestMapping(method = RequestMethod.POST, value = "/updatebook/{id}")
    public ResponseEntity<String> updateBook(@RequestBody Book book, @PathVariable int id) {

        List<Book> books = bookRepository.findById(id);

        bookRepository.save(book);

        return new ResponseEntity<String>("Book updated", HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addbook")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
    //public String addBook(@RequestBody Book book) {
        bookRepository.save(book);
        //return "Book added";
        return new ResponseEntity<String>("Book added", HttpStatus.CREATED);



    }

    /*@RequestMapping(value = "/addbook", method = RequestMethod.POST)
    public ResponseEntity<?> addBook(@RequestBody Book book, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Book : {}", book);

        if (bookRepository.equals(book.getName())) {
            logger.error("Unable to create. A Book with name {} already exist", book.getName());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Book with name " +
                    book.getName() + " already exist."),HttpStatus.CONFLICT);
        }
        bookRepository.save(book);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/addbook").buildAndExpand(book.getId()).toUri());
        return new ResponseEntity<String>("book created", HttpStatus.CREATED);
    }*/




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