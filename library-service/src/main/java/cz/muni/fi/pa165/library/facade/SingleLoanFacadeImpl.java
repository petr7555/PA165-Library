package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.BookDTO;
import cz.muni.fi.pa165.library.dto.SingleLoanDTO;
import cz.muni.fi.pa165.library.dto.UserDTO;
import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.services.MappingService;
import cz.muni.fi.pa165.library.services.SingleLoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 21.04.2020
 * <p>
 * Implements methods defined in SingleLoanFacade.
 */
@Service
@Transactional
public class SingleLoanFacadeImpl implements SingleLoanFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(SingleLoanFacadeImpl.class);

    private final MappingService mappingService;
    private final SingleLoanService singleLoanService;

    public SingleLoanFacadeImpl(MappingService mappingService, SingleLoanService singleLoanService) {
        this.mappingService = mappingService;
        this.singleLoanService = singleLoanService;
    }

    @Override
    public List<SingleLoanDTO> findForUser(long userId) {
        LOGGER.info("Finding single loans for user with id {}.", userId);
        return mappingService.mapTo(singleLoanService.findForUser(userId), SingleLoanDTO.class);
    }

    @Override
    public List<SingleLoanDTO> findForBook(long bookId) {
        LOGGER.info("Finding single loans for book with id {}.", bookId);
        return mappingService.mapTo(singleLoanService.findForBook(bookId), SingleLoanDTO.class);

    }
}
