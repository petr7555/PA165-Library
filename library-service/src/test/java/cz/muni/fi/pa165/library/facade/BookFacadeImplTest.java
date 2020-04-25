package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.BookDTO;
import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.SingleLoan;
import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.services.BookService;
import cz.muni.fi.pa165.library.services.MappingService;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static cz.muni.fi.pa165.library.Utils.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.when;

/**
 * @author Petr Janik 485122
 * @since 12.04.2020
 */
@SpringBootTest
class BookFacadeImplTest {

    @Autowired
    private MappingService mappingService;

    @Mock
    private BookService bookService;

    private BookFacadeImpl bookFacadeImpl;

    @BeforeEach
    public void setUp() {
        bookFacadeImpl = new BookFacadeImpl(mappingService, bookService);
    }

    @Test
    void createBook() {
        Book book = createTestBookAnimalFarm();
        when(bookService.createBook(book)).thenReturn(1L);

        BookDTO bookDTO = dtoCopyOfBook(book);

        assertEquals(1, bookFacadeImpl.createBook(bookDTO));
    }

    @Test
    void deleteBook() {
        when(bookService.deleteBook(1)).thenReturn(1L);
        assertEquals(1, bookFacadeImpl.deleteBook(1));
    }

    /**
     * Tests that availability is set properly.
     */
    @Test
    void findAllBooks() {
        Book book1 = createTestBookAnimalFarm();
        Book book2 = createTestBook1984();
        Book book3 = createTestBookGatsby();
        User user = createTestUser("John", "Doe");

        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);

        SingleLoan book1returnedYesterday = createSingleLoan(book1, user);
        book1returnedYesterday.setBorrowedAt(yesterday);
        book1returnedYesterday.setReturnedAt(yesterday.plusHours(1));

        SingleLoan book1notReturnedYet = createSingleLoan(book1, user);
        book1notReturnedYet.setBorrowedAt(yesterday.plusHours(3));
        book1notReturnedYet.setReturnedAt(null);

        book1.setSingleLoans(List.of(book1returnedYesterday, book1notReturnedYet));

        SingleLoan book2Returned= createSingleLoan(book2, user);
        book2Returned.setBorrowedAt(yesterday);
        book2Returned.setReturnedAt(yesterday.plusHours(1));

        book2.setSingleLoans(List.of(book2Returned));

        when(bookService.findAll()).thenReturn(List.of(book1, book2, book3));

        BookDTO bookDTO1 = dtoCopyOfBook(book1);
        bookDTO1.setAvailable(false);

        BookDTO bookDTO2 = dtoCopyOfBook(book2);
        bookDTO2.setAvailable(true);

        BookDTO bookDTO3 = dtoCopyOfBook(book3);
        bookDTO3.setAvailable(true);

        assertThat(bookFacadeImpl.findAllBooks(), containsInAnyOrder(bookDTO1, bookDTO2, bookDTO3));
    }

    @Test
    void findByTitle() {
        Book book1 = createTestBookAnimalFarm();
        Book book3 = createTestBookGatsby();

        when(bookService.findByTitle("a")).thenReturn(List.of(book1, book3));

        assertThat(bookFacadeImpl.findByTitle("a"), containsInAnyOrder(dtoCopyOfBook(book1), dtoCopyOfBook(book3)));
    }

    @Test
    void findByAuthor() {
        Book book1 = createTestBookAnimalFarm();
        Book book2 = createTestBook1984();

        when(bookService.findByAuthor("george")).thenReturn(List.of(book1, book2));

        assertThat(bookFacadeImpl.findByAuthor("george"), containsInAnyOrder(dtoCopyOfBook(book1), dtoCopyOfBook(book2)));
    }
}
