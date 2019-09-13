package book.dao;

import java.util.*;
import model.Book;

public interface BookDAO{

    //public List<Book> getAllBooks();
    public Map<Integer, Book> getAllBooks();
}