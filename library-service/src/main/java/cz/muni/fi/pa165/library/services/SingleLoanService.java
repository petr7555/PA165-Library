package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.SingleLoan;
import cz.muni.fi.pa165.library.repositories.SingleLoanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Petr Janik 485122
 * @since 21.04.2020
 * <p>
 * A service providing functionality to SingleLoanFacade.
 * It interacts directly with SingleLoanRepository.
 */
@Service
public class SingleLoanService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SingleLoanService.class);

    private SingleLoanRepository singleLoanRepository;

    public SingleLoanService(SingleLoanRepository singleLoanRepository) {
        this.singleLoanRepository = singleLoanRepository;
    }

    public List<SingleLoan> findForUser(long userId) {
        LOGGER.info("Finding single loans for user with id {}.", userId);
        return singleLoanRepository.findAll().stream().filter(singleLoan -> singleLoan.getBook().getId() == userId).collect(Collectors.toList());
    }

    public List<SingleLoan> findForBook(long bookId) {
        LOGGER.info("Finding single loans for book with id {}.", bookId);
        return singleLoanRepository.findAll().stream().filter(singleLoan -> singleLoan.getBook().getId() == bookId).collect(Collectors.toList());
    }
}
