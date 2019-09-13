package book.repository;

import book.model.Book;
import book.model.BookUI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository

public interface BookRepository extends JpaRepository<Book, Integer> {


    public Map<Integer, BookUI> getAllBooks();

}