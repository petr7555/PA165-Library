package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.BookDTO;
import cz.muni.fi.pa165.library.dto.UserDTO;

import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 06.04.2020
 * <p>
 * A facade for working with BookDTOs.
 */
public interface SingleLoanFacade {
    List<BookDTO> findSingleLoansForUser(UserDTO user);
}
