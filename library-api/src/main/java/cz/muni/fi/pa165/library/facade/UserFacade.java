package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.BookDTO;

import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 06.04.2020
 * <p>
 * A facade for working with UserDTOs.
 */
public interface UserFacade {
    List<BookDTO> findAllUsers();
}
