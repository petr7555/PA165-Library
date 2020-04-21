package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.BookDto;

import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 06.04.2020
 * <p>
 * A facade for working with BookDTOs.
 */
public interface BookFacade {
    long createBook(BookDto book);

    long deleteBook(long id);

    List<BookDto> findAllBooks();

    List<BookDto> findByTitle(String title);

    List<BookDto> findByAuthor(String author);
}
