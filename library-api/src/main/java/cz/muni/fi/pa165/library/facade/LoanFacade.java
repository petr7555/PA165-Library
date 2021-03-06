package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.LoanDTO;

/**
 * @author Petr Janik 485122
 * @since 06.04.2020
 * <p>
 * A facade for working with LoanDTOs.
 */
public interface LoanFacade {
    long createLoan(LoanDTO loan);
}
