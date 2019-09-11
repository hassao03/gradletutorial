package book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import model.Book;
import book.dao.BookDAO;

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
    public void deleteBook(@PathVariable String id){
        bookService.deleteBook(id);
    }

    //update a book
    @RequestMapping(method = RequestMethod.PUT, value = "/books/{id}")
    public void updateBook(@RequestBody Book book, @PathVariable String id){
         bookService.updateBook(book, id);
    }

    //add a book
    @RequestMapping(method = RequestMethod.POST, value = "/books")
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }


    //return book by id
    @RequestMapping(method = RequestMethod.GET, value = "/books/{id}")
    public Book getBook(@PathVariable String id){
        return bookService.getBook(id);
    }

    //return all books
    @RequestMapping(method = RequestMethod.GET, value = "/books")
    public List<Book> getAll(){
        return bookService.getAll();
    }

}


//Use the following in normal terminal while the server is running in intellij
//curl -X POST localhost:8080/books -H 'Content-type:application/json' -d '{"name": "omar", "id": "4"}'
//curl -X PUT localhost:8080/books/1 -H 'Content-type:application/json' -d '{"name": "Omar", "id": "222"}'
//curl -X DELETE localhost:8080/books/1 -H 'Content-type:application/json' -d '{"name": "Book1", "id": "1"}'