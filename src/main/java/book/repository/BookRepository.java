package book.repository;

import book.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository

public interface BookRepository extends CrudRepository<Book, Integer> {


    Optional<Book> findByName(String name);
    Optional<Book> findById(int id);
    List<Book> findAll();


}