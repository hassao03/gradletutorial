package book.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "name")
    String name;
    @Column(name = "isbn")
    int isbn;
    @ManyToOne
    @JoinColumn(name="book_category_id")

    @JsonBackReference
    BookCategory bookCategory;

    public Book(int id, String name, int isbn, BookCategory bookCategory) {
        this.id = id;
        this.name = name;
        this.isbn = isbn;
        this.bookCategory = bookCategory;

    }

    public Book(int id, String name, int isbn) {
        this.id = id;
        this.name = name;
        this.isbn = isbn;


    }
    public Book(){
        this.id = id;
        this.name = name;
        this.isbn = isbn;
    }




    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }



}