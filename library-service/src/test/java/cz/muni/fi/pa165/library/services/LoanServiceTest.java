package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.Loan;
import cz.muni.fi.pa165.library.entities.SingleLoan;
import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.repositories.LoanRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static cz.muni.fi.pa165.library.Utils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Petr Janik 485122
 * @since 21.04.2020
 */
@SpringBootTest
public class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    private Loan createCopyWithId(Loan loan, long id) {
        Loan copy = new Loan();
        copy.setId(id);
        copy.setSingleLoans(loan.getSingleLoans());
        return copy;
    }

    @Test
    public void createLoan() {
        User user = createTestUser("John", "Doe");
        SingleLoan singleLoan1 = createSingleLoan(createTestBookAnimalFarm(), user);
        SingleLoan singleLoan2 = createSingleLoan(createTestBook1984(), user);
        Loan loan = createLoanOfSingleLoans(List.of(singleLoan1, singleLoan2));

        Loan loanResult = createCopyWithId(loan, 1);

        when(loanRepository.save(loan)).thenReturn(loanResult);

        assertEquals(1, loanService.createLoan(loan));
    }
}
