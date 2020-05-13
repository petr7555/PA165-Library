package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.LoanDTO;
import cz.muni.fi.pa165.library.dto.SingleLoanDTO;
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

import java.util.Collection;
import java.util.List;

import static cz.muni.fi.pa165.library.Utils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
        LoanDTO loanDTO = dtoCopyOfLoan(loan);
        Loan loan1 = mappingService.mapTo(loanDTO, Loan.class);
        boolean eq = loan1.equals(loan);
        List<SingleLoan> singleLoans = (List<SingleLoan>) loan.getSingleLoans();
        List<SingleLoan> singleLoans1 = (List<SingleLoan>) loan1.getSingleLoans();
        boolean eq2 = singleLoans.get(0).equals(singleLoans1.get(0));
        boolean eq3 = singleLoans.get(1).equals(singleLoans1.get(1));

        assertEquals(1, loanFacadeImpl.createLoan(dtoCopyOfLoan(loan)));
    }
}
