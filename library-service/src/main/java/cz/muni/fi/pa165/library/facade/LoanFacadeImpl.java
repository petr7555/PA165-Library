package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.LoanDTO;
import cz.muni.fi.pa165.library.entities.Loan;
import cz.muni.fi.pa165.library.services.LoanService;
import cz.muni.fi.pa165.library.services.MappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Petr Janik 485122
 * @since 21.04.2020
 * <p>
 * Implements methods defined in LoanFacade.
 */
@Service
@Transactional
public class LoanFacadeImpl implements LoanFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanFacadeImpl.class);

    private final MappingService mappingService;
    private final LoanService loanService;

    public LoanFacadeImpl(MappingService mappingService, LoanService loanService) {
        this.mappingService = mappingService;
        this.loanService = loanService;
    }

    @Override
    public long createLoan(LoanDTO loan) {
        LOGGER.info("Creating loan {}.", loan);
        Loan loan1 = mappingService.mapTo(loan, Loan.class);
        return loanService.createLoan(loan1);
    }
}
