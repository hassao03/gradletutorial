package book.Service;
import java.util.*;

import book.model.Book;
import book.repository.BookRepository;
import book.model.BookUI;

import org.springframework.stereotype.Service;
@Service
public class BookService implements BookRepository {

    private Map<Integer, BookUI> bookList;
    int idIndex = 1;
    BookUI book1;

    public BookService(){
        this.bookList = new HashMap<Integer, BookUI>();
        initList();

    }

    public BookService(Map<Integer, BookUI> bookList){

        this.bookList = bookList;
    }

    public void initList(){
        book1 = new BookUI();
        book1.setId(1);
        book1.setName("book1");
        book1.setIsbn(123123);
        bookList.put(1, book1);
    }


    public int listSize(){
        return bookList.size();
    }

    public Map<Integer, BookUI> getAllBooks() {

        return this.bookList;
    }


    public BookUI getBook(int id){
        if(bookList.containsKey(id))
            return bookList.get(id);

        return null;

    }

    public void addBook(BookUI book){

        idIndex += idIndex;
        book.setId(idIndex);
        getAllBooks().put(idIndex, book);

    }

    public void updateBook(int id, BookUI book){
        if(bookList.containsKey(id)) {
        getAllBooks().put(id,book);
        }else{
            System.out.println("id not found");
        }

    }

    public void deleteBook(int id){

        if(bookList.containsKey(id)) {
            getAllBooks().remove(id);
        }else{
            System.out.println("id not found");
        }
    }




}
