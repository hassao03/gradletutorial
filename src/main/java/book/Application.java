package book;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import book.repository.BookRepository;

@SpringBootApplication
public class Application {
    @Autowired
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}