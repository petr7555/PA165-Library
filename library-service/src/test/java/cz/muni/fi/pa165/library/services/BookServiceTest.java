package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author Petr Janik 485122
 * @since 21.04.2020
 */
@SpringBootTest
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book book1;
    private Book book2;

    @Test
    public void testFindBookByAuthorNull() {
        assertTrue(bookService.findByAuthor(null).isEmpty());
    }

    @Test
    public void testFindBookByTitleNull() {
        assertTrue(bookService.findByTitle(null).isEmpty());
    }

    @Test
    public void testFindBookByAuthor() {
        setBook1();
        String author = book1.getAuthor();

        when(bookRepository.findAll())
                .thenReturn(Arrays.asList(book1));

        assertEquals(Arrays.asList(book1), bookService.findByAuthor(author));
    }

    @Test
    public void testFindMultipleBooksByAuthor() {
        setBook1();
        book2 = new Book();
        book2.setTitle("Another Title");
        book2.setAuthor("George Orwell");
        String author = book1.getAuthor();

        when(bookRepository.findAll())
                .thenReturn(Arrays.asList(book1, book2));

        assertEquals(Arrays.asList(book1, book2), bookService.findByAuthor(author));
    }

    @Test
    public void testFindBookByTitle() {
        setBook1();
        String title = book1.getTitle();

        when(bookRepository.findAll())
                .thenReturn(Arrays.asList(book1));

        assertEquals(Arrays.asList(book1), bookService.findByTitle(title));
    }

    @Test
    public void testFindMultipleBooksByTitle() {
        setBook1();
        book2 = new Book();
        book2.setTitle("Animal Farm");
        book2.setAuthor("Another Author");
        String title = book1.getTitle();

        when(bookRepository.findAll())
                .thenReturn(Arrays.asList(book1, book2));

        assertEquals(Arrays.asList(book1, book2), bookService.findByTitle(title));
    }

    private void setBook1() {
        book1 = new Book();
        book1.setTitle("Animal Farm");
        book1.setAuthor("George Orwell");
    }
}
