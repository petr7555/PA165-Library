package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.SingleLoanDTO;

import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 06.04.2020
 * <p>
 * A facade for working with SingleLoanDTOs.
 */
public interface SingleLoanFacade {
    List<SingleLoanDTO> findForBook(long bookId);
}
