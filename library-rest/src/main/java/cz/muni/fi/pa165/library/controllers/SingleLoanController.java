package cz.muni.fi.pa165.library.controllers;

import cz.muni.fi.pa165.library.dto.SingleLoanDTO;
import cz.muni.fi.pa165.library.facade.SingleLoanFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author Petr Janik 485122
 * @since 21.04.2020
 * <p>
 * A controller for endpoints related to SingleLoanDTO.
 */
@RestController
@Transactional
public class SingleLoanController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SingleLoanController.class);

    private final SingleLoanFacade singleLoanFacade;

    public SingleLoanController(SingleLoanFacade singleLoanFacade) {
        this.singleLoanFacade = singleLoanFacade;
    }

    @GetMapping(value = "/singleLoans", params = "userId", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SingleLoanDTO> findForUSer(@RequestParam long userId) {
        LOGGER.info("Finding single loans for user with id {}.", userId);
        return singleLoanFacade.findForUser(userId);
    }

    @GetMapping(value = "/singleLoans", params = "bookId", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SingleLoanDTO> findForBook(@RequestParam long bookId) {
        LOGGER.info("Finding single loans for book with id {}.", bookId);
        return singleLoanFacade.findForBook(bookId);
    }
}
