package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.Loan;
import cz.muni.fi.pa165.library.repositories.LoanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Petr Janik 485122
 * @since 21.04.2020
 * <p>
 * A service providing functionality to LoanFacade.
 * It interacts directly with LoanRepository.
 */
@Service
public class LoanService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanService.class);

    private LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public long createLoan(Loan loan) {
        LOGGER.info("Creating loan {}.", loan);
        loan = loanRepository.save(loan);
        LOGGER.info("Created loan with id {}.", loan.getId());
        return loan.getId();
    }
}
