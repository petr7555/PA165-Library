package cz.muni.fi.pa165.library.controllers;

import cz.muni.fi.pa165.library.dto.LoanDTO;
import cz.muni.fi.pa165.library.facade.LoanFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Petr Janik 485122
 * @since 21.04.2020
 * <p>
 * A controller for endpoints related to LoanDTO.
 */
@RestController
public class LoanController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoanController.class);

    private final LoanFacade loanFacade;

    public LoanController(LoanFacade loanFacade) {
        this.loanFacade = loanFacade;
    }

    @PostMapping(value = "/loans", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public long createLoan(@RequestBody LoanDTO loan) {
        LOGGER.info("Creating loan {}.", loan);
        return loanFacade.createLoan(loan);
    }
}
