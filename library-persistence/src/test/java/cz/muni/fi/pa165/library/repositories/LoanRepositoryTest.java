package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.Arrays;

import static cz.muni.fi.pa165.library.Utils.createTestBook1984;
import static cz.muni.fi.pa165.library.Utils.createTestBookAnimalFarm;
import static cz.muni.fi.pa165.library.Utils.createTestUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

/**
 * @author Petr Janik 485122
 * @since 29.03.2020
 */
@DataJpaTest
public class LoanRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private SingleLoanRepository singleLoanRepository;

    private Book animalFarm;
    private Book book1984;
    private User user;

    @BeforeEach
    public void setupTest() {
        Role roleUser = new Role(Role.RoleType.USER);
        animalFarm = createTestBookAnimalFarm();
        book1984 = createTestBook1984();
        entityManager.persist(animalFarm);
        entityManager.persist(book1984);
        user = createTestUser("John", "Doe", roleUser);
        entityManager.persist(user);
    }

    /**
     * Tests also that creating Loan transitively creates SingleLoans.
     */
    @Test
    public void createLoanWithTwoSingleLoans() {
        SingleLoan singleLoan1 = new SingleLoan();
        singleLoan1.setBook(animalFarm);
        singleLoan1.setUser(user);
        singleLoan1.setBorrowedAt(LocalDateTime.of(2020, 1, 1, 12, 0));

        SingleLoan singleLoan2 = new SingleLoan();
        singleLoan2.setBook(book1984);
        singleLoan2.setUser(user);
        singleLoan2.setBorrowedAt(LocalDateTime.of(2020, 1, 1, 12, 0));

        Loan loan = new Loan();
        loan.setSingleLoans(Arrays.asList(singleLoan1, singleLoan2));
        loanRepository.save(loan);

        assertThat(loanRepository.findAll(), containsInAnyOrder(loan));
        assertThat(singleLoanRepository.findAll(), containsInAnyOrder(singleLoan1, singleLoan2));
    }
}
