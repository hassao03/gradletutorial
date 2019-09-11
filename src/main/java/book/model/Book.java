package model;
import java.util.*;
public class Book{

    String name;
    String id;

    public Book(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public Book(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}