package cz.muni.fi.pa165.library;

import cz.muni.fi.pa165.library.entities.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

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
        return createTestBook("Animal farm", "George Orwell");
    }

    public static Book createTestBook1984() {
        return createTestBook("1984", "George Orwell");
    }

    public static Book createTestBookGatsby() {
        return createTestBook("The Great Gatsby", "Francis Scott Fitzgerald");
    }

    private static Book createTestBook(String title, String author) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        return book;
    }

    public static SingleLoan createSingleLoan(Book book, User user) {
        SingleLoan singleLoan = new SingleLoan();
        singleLoan.setBook(book);
        singleLoan.setUser(user);
        singleLoan.setBorrowedAt(LocalDateTime.of(2020, 1, 1, 12, 0));
        return singleLoan;
    }

    public static Loan createLoan(List<SingleLoan> singleLoans) {
        Loan loan = new Loan();
        loan.setSingleLoans(singleLoans);
        return loan;
    }
}
