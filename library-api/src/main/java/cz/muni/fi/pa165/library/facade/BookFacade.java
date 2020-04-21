package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.BookDTO;

import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 06.04.2020
 * <p>
 * A facade for working with BookDTOs.
 */
public interface BookFacade {
    //TODO not used so far
    long createBook(BookDTO book);

    //TODO not used so far
    long deleteBook(long id);

    List<BookDTO> findAllBooks();

    List<BookDTO> findByTitle(String title);

    List<BookDTO> findByAuthor(String author);
}
