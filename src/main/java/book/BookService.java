package book;
import java.util.*;

import book.dao.BookDAO;
import model.Book;


import org.springframework.stereotype.Service;
@Service
public class BookService implements BookDAO {

    public List<Book> getAllBooks() {

        List<Book> bookList = new ArrayList<Book>();

        Book book1 = new Book();
        book1.setName("book1");
        book1.setId("1");
        bookList.add(book1);

        Book book2 = new Book();
        book2.setName("book2");
        book2.setId("2");
        bookList.add(book2);

        return bookList;
    }

    public List<Book> getAll(){

        return getAllBooks();
    }

    public Book getBook(String id){

        //orElse(null) ??
        return getAllBooks().stream().filter(t -> t.getId().equals(id)).findFirst().get();
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
