package book;
import java.util.*;
import java.util.stream.Collectors;

import book.dao.BookDAO;
import model.Book;


import org.springframework.stereotype.Service;
@Service
public class BookService implements BookDAO {

    private Map<Integer, Book> bookList;
    int idIndex = 1;
    Book book1;

    public BookService(){
        this.bookList = new HashMap<Integer, Book>();
        initList();

    }

    public BookService(Map<Integer, Book> bookList){

        this.bookList = bookList;
    }

    public void initList(){
        book1 = new Book();
        book1.setId(1);
        book1.setName("book1");
        book1.setIsbn(123123);
        bookList.put(1, book1);
    }


    public int listSize(){
        return bookList.size();
    }

    public Map<Integer, Book> getAllBooks() {

        return this.bookList;
    }


    public Book getBook(int id){
        return bookList.get(id);
    }

    public void addBook(Book book){

        idIndex += idIndex;
        book.setId(idIndex);
        getAllBooks().put(idIndex, book);

    }

    public void updateBook(int id, Book book){

        getAllBooks().put(id,book);

    }

    public void deleteBook(int id){

        getAllBooks().remove(id);
    }

}
