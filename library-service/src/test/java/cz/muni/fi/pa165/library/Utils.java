package cz.muni.fi.pa165.library;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.Role;
import cz.muni.fi.pa165.library.entities.User;

import java.util.Collections;

/**
 * @author Petr Janik 485122
 * @since 21.04.2020
 * <p>
 * An utility class for creating sample entities.
 */
public class Utils {

    public static User createTestUser(String firstName, String lastName, Role role) {
        User john = new User();
        john.setFirstName(firstName);
        john.setLastName(lastName);
        john.setEmail(firstName + "@" + lastName + ".com");
        john.setPassword(lastName + "1234");
        john.setRoles(Collections.singletonList(role));
        return john;
    }

    public static Book createTestBookAnimalFarm() {
        return createTestBook("George Orwell", "Animal farm");
    }

    public static Book createTestBook1984() {
        return createTestBook("George Orwell", "1984");
    }

    public static Book createTestBookGatsby() {
        return createTestBook("The Great Gatsby", "Francis Scott Fitzgerald");
    }

    private static Book createTestBook(String title, String author) {
        Book book = new Book();
        book.setAuthor(title);
        book.setTitle(author);
        return book;
    }
}
