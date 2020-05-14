package cz.muni.fi.pa165.library.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import cz.muni.fi.pa165.library.dto.BookDTO;
import cz.muni.fi.pa165.library.dto.View;
import cz.muni.fi.pa165.library.facade.BookFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 21.04.2020
 * <p>
 * A controller for endpoints related to BookDTO.
 */
@RestController
public class BookController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    private final BookFacade bookFacade;

    public BookController(BookFacade bookFacade) {
        this.bookFacade = bookFacade;
    }

    @PostMapping(value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public long createBook(@RequestBody BookDTO book) {
        LOGGER.info("Creating book {}.", book);
        return bookFacade.createBook(book);
    }

    @DeleteMapping(value = "/books", params = "id")
    public long deleteBook(@RequestParam long id) {
        LOGGER.info("Deleting book with id {}.", id);
        return bookFacade.deleteBook(id);
    }

    @JsonView(View.Books.class)
    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDTO> findAllBooks() {
        LOGGER.info("Finding all books.");
        return bookFacade.findAllBooks();
    }

    @JsonView(View.Books.class)
    @GetMapping(value = "/books", params = "title", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDTO> findByTitle(@RequestParam String title) {
        LOGGER.info("Finding all books containing {} in title.", title);
        return bookFacade.findByTitle(title);
    }

    @JsonView(View.Books.class)
    @GetMapping(value = "/books", params = "author", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDTO> findByAuthor(@RequestParam String author) {
        LOGGER.info("Finding all books containing {} as an author.", author);
        return bookFacade.findByAuthor(author);
    }
}
