package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static cz.muni.fi.pa165.library.Utils.createTestBook1984;
import static cz.muni.fi.pa165.library.Utils.createTestBookAnimalFarm;
import static cz.muni.fi.pa165.library.Utils.createTestBookGatsby;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    private Book book3;

    @BeforeEach
    public void setupMock() {
        book1 = createTestBookAnimalFarm();
        book1.setAuthor("gEOrge OrweLL");

        book2 = createTestBook1984();

        book3 = createTestBookGatsby();
        book3.setTitle("The greAT GATsby");

        when(bookRepository.findAll())
                .thenReturn(List.of(book1, book2, book3));
    }

    private Book createCopyWithId(Book book, long id) {
        Book copy = new Book();
        copy.setId(id);
        copy.setTitle(book.getTitle());
        copy.setAuthor(book.getAuthor());
        return copy;
    }

    @Test
    public void createBooks() {
        Book bookResult1 = createCopyWithId(book1, 1);
        Book bookResult2 = createCopyWithId(book2, 2);

        when(bookRepository.save(book1)).thenReturn(bookResult1);
        when(bookRepository.save(book2)).thenReturn(bookResult2);

        assertEquals(1, bookService.createBook(book1));
        assertEquals(2, bookService.createBook(book2));
    }

    @Test
    public void deleteBook() {
        assertEquals(book1.getId(), bookService.deleteBook(book1.getId()));
    }

    @Test
    public void deleteNonexistentBook() {
        assertEquals(123, bookService.deleteBook(123));
    }

    @Test
    public void bookMustNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> bookService.createBook(null));
    }

    @Test
    public void findAll() {
        assertThat(bookService.findAll(), containsInAnyOrder(book1, book2, book3));
    }

    @Test
    public void findAllEmpty() {
        when(bookRepository.findAll())
                .thenReturn(Collections.emptyList());
        assertThat(bookService.findAll(), is(empty()));
    }

    @Test
    public void findBookByAuthorFull() {
        String author = "George Orwell";
        assertThat(bookService.findByAuthor(author), containsInAnyOrder(book1, book2));
    }

    @Test
    public void findBookByAuthorPartial() {
        String author = "ge Orw";
        assertThat(bookService.findByAuthor(author), containsInAnyOrder(book1, book2));
    }

    @Test
    public void findBookByAuthorCase() {
        String author = "geoRGe orWELL";
        assertThat(bookService.findByAuthor(author), containsInAnyOrder(book1, book2));
    }

    @Test
    public void findBookByAuthorNonexistent() {
        String author = "Does not exist";
        assertThat(bookService.findByAuthor(author), is(empty()));
    }

    @Test
    public void testFindBookByAuthorNull() {
        assertThat(bookService.findByAuthor(null), is(empty()));
    }

    @Test
    public void findBookByAuthorEmpty() {
        String author = "";
        assertThat(bookService.findByAuthor(author), containsInAnyOrder(book1, book2, book3));
    }

    @Test
    public void findBookByTitleFull() {
        String title = "Animal Farm";
        assertThat(bookService.findByTitle(title), containsInAnyOrder(book1));
    }

    @Test
    public void findBookByTitlePartial() {
        String title = "a";
        assertThat(bookService.findByTitle(title), containsInAnyOrder(book1, book3));
    }

    @Test
    public void findBookByTitleCase() {
        String title = "THE great GATSby";
        assertThat(bookService.findByTitle(title), containsInAnyOrder(book3));
    }

    @Test
    public void findBookByTitleNonexistent() {
        String title = "Does not exist";
        assertThat(bookService.findByTitle(title), is(empty()));
    }

    @Test
    public void findBookByTitleNull() {
        assertThat(bookService.findByTitle(null), is(empty()));
    }

    @Test
    public void findBookByTitleEmpty() {
        String title = "";
        assertThat(bookService.findByTitle(title), containsInAnyOrder(book1, book2, book3));
    }
}
