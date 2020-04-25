package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import javax.validation.ConstraintViolationException;

import static cz.muni.fi.pa165.library.Utils.createTestBook1984;
import static cz.muni.fi.pa165.library.Utils.createTestBookAnimalFarm;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Petr Janik 485122
 * @since 21.04.2020
 */
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void addOneBook() {
        Book book = createTestBookAnimalFarm();
        bookRepository.save(book);
        assertThat(bookRepository.findAll(), containsInAnyOrder(book));
    }

    @Test
    public void bookMustNotBeNull() {
        assertThrows(InvalidDataAccessApiUsageException.class, ()->bookRepository.save(null));
    }

    @Test
    public void addTwoBooks() {
        Book book1 = createTestBookAnimalFarm();
        Book book2 = createTestBook1984();
        bookRepository.save(book1);
        bookRepository.save(book2);
        assertThat(bookRepository.findAll(), containsInAnyOrder(book1, book2));
    }

    @Test
    public void addingTwoSameBooksIsPossible() {
        Book book1 = createTestBookAnimalFarm();
        Book book2 = createTestBookAnimalFarm();
        bookRepository.save(book1);
        bookRepository.save(book2);
        assertThat(bookRepository.findAll(), containsInAnyOrder(book1, book2));
    }

    @Test
    public void titleMustNotBeNull() {
        Book book = createTestBookAnimalFarm();
        book.setTitle(null);
        assertThrows(ConstraintViolationException.class, ()->bookRepository.save(book));
    }

    @Test
    public void authorMustNotBeNull() {
        Book book = createTestBookAnimalFarm();
        book.setAuthor(null);
        assertThrows(ConstraintViolationException.class, ()->bookRepository.save(book));
    }
}
