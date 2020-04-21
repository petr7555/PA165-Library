package cz.muni.fi.pa165.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main class. This class should be run when you want to run the application.
 * It automatically picks up all configurations and fills database with initial data.
 */
@SpringBootApplication
@EnableJpaRepositories
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }
}
