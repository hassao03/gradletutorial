package book.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


//@Data
//@EqualsAndHashCode(exclude = "books")
@Entity
@Table(name = "book_category")
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    String name;
    @Access(AccessType.PROPERTY)
    @OneToMany(mappedBy = "bookCategory", cascade = CascadeType.MERGE)

    Set<Book> books;

    public BookCategory(){

    }

    public BookCategory(String name) {

        this.name = name;

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
