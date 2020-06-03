package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.entities.Loan;
import cz.muni.fi.pa165.library.entities.SingleLoan;
import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.services.LoanService;
import cz.muni.fi.pa165.library.services.MappingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static cz.muni.fi.pa165.library.Utils.createLoanOfSingleLoans;
import static cz.muni.fi.pa165.library.Utils.createSingleLoan;
import static cz.muni.fi.pa165.library.Utils.createTestBook1984;
import static cz.muni.fi.pa165.library.Utils.createTestBookAnimalFarm;
import static cz.muni.fi.pa165.library.Utils.dtoCopyOfLoan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Petr Janik 485122
 * @since 12.04.2020
 */
@SpringBootTest
class LoanFacadeImplTest {

    @Autowired
    private MappingService mappingService;

    @Mock
    private LoanService loanService;

    private LoanFacadeImpl loanFacadeImpl;

    @BeforeEach
    public void setUp() {
        loanFacadeImpl = new LoanFacadeImpl(mappingService, loanService);
    }

    @Test
    void createLoan() {
        User user = new User("John", "Doe", "John@Doe.com", null, null);
        SingleLoan singleLoan1 = createSingleLoan(createTestBookAnimalFarm(), user);
        SingleLoan singleLoan2 = createSingleLoan(createTestBook1984(), user);
        Loan loan = createLoanOfSingleLoans(List.of(singleLoan1, singleLoan2));

        when(loanService.createLoan(loan)).thenReturn(1L);

        assertEquals(1, loanFacadeImpl.createLoan(dtoCopyOfLoan(loan)));
    }
}
