package book.model;

public class BookUI {

    String name;
    int id;
    int isbn;




    public BookUI(int id, String name, int isbn) {
        this.id = id;
        this.name = name;
        this.isbn = isbn;

    }

    public BookUI(){
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


}