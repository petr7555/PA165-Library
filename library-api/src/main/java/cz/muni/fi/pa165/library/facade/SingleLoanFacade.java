package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.BookDTO;
import cz.muni.fi.pa165.library.dto.SingleLoanDTO;
import cz.muni.fi.pa165.library.dto.UserDTO;

import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 06.04.2020
 * <p>
 * A facade for working with SingleLoanDTOs.
 */
public interface SingleLoanFacade {
    List<SingleLoanDTO> findSingleLoansForUser(UserDTO user);
    List<SingleLoanDTO> findSingleLoansForBook(BookDTO book);
}
