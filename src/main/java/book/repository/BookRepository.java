package book.repository;

import book.model.Book;
import book.model.BookCategory;
import book.model.BookUI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository

public interface BookRepository extends CrudRepository<Book, Integer> {


    List<Book> findByName(String name);
    List<Book> findById(int id);
    List<Book> findAll();
    //BookCategory categoryA = new BookCategory("Category A");
    //List<Book> book = new ArrayList<>(Arrays.asList(new Book(1,"Book1",123,categoryA)));

    //int listSize();
}