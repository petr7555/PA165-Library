package cz.muni.fi.pa165.library;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.Role;
import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.repositories.BookRepository;
import cz.muni.fi.pa165.library.repositories.RoleRepository;
import cz.muni.fi.pa165.library.repositories.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 12.04.2020
 * <p>
 * Initializes in-memory database with initial data. The data is reset after each restart of the application.
 */
@Configuration
public class DataConfiguration {

    /**
     * Create some books to start with.
     *
     * @param bookRepository
     * @return
     */
    @Bean
    public ApplicationRunner bookInitializer(BookRepository bookRepository) {
        return args -> bookRepository.saveAll(List.of(
                new Book("Animal Farm", "George Orwell"),
                new Book("1984", "George Orwell"),
                new Book("Ostře sledované vlaky", "Bohumil Hrabal")
        ));
    }

    /**
     * Creates two users. One normal user and one admin.
     *
     * @param userRepository
     * @param roleRepository
     * @param encoder
     * @return
     */
    @Bean
    public ApplicationRunner userInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        Role roleUser = new Role(Role.RoleType.USER);
        Role roleAdmin = new Role(Role.RoleType.ADMIN);
        roleRepository.saveAll(List.of(
                roleUser,
                roleAdmin
        ));
        return args -> userRepository.saveAll(List.of(
                new User("John", "Smith", "john.smith@email.cz",
                        encoder.encode("john1234"), Collections.singletonList(roleUser)),
                new User("Peter", "Griffin", "peter.griffin@gmail.com", encoder.encode("password"), List.of(roleUser, roleAdmin)),
                new User("Ad", "Min", "admin",
                        encoder.encode("admin"), List.of(roleUser, roleAdmin)),
                new User("Us", "Er", "user",
                        encoder.encode("user"), Collections.singletonList(roleUser)))
        );
    }
}
