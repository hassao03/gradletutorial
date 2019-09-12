package book;
import java.util.*;

import book.dao.BookDAO;
import model.Book;


import org.springframework.stereotype.Service;
@Service
public class BookService implements BookDAO {

    private List<Book> bookList;
    Book book1;

    public BookService(){
        this.bookList = new ArrayList<Book>();
        initList();

    }

    public BookService(List<Book> bookList){

        this.bookList = bookList;
    }

    public void initList(){
        book1 = new Book();
        book1.setName("book1");
        book1.setId("1");
        bookList.add(book1);
    }


    public int listSize(){
        return bookList.size();
    }

    public List<Book> getAllBooks() {

        return this.bookList;
    }


    public Book getBook(String id){

        return getAllBooks().stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    public void addBook(Book book){
        getAllBooks().add(book);

    }

    public void updateBook(Book updateBook, String id){

        for(int i=0; i< getAllBooks().size();i++){
            Book book = getAllBooks().get(i);

            if(book.getId().equals(id)){
                getAllBooks().set(i, updateBook);
            }
        }

    }

    public void deleteBook(String id){

        getAllBooks().removeIf(t->t.getId().equals(id));
    }

}
