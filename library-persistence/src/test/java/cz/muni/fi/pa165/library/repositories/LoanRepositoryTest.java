package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static cz.muni.fi.pa165.library.Utils.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Petr Janik 485122
 * @since 29.03.2020
 */
@DataJpaTest
public class LoanRepositoryTest {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

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
        roleRepository.save(roleUser);
        animalFarm = createTestBookAnimalFarm();
        book1984 = createTestBook1984();
        bookRepository.save(animalFarm);
        bookRepository.save(book1984);
        user = createTestUser("John", "Doe", roleUser);
        userRepository.save(user);
    }

    /**
     * Tests also that creating Loan transitively creates SingleLoans.
     */
    @Test
    public void createLoanWithTwoSingleLoans() {
        SingleLoan singleLoan1 = createSingleLoan(animalFarm, user, LocalDateTime.of(2020, 1, 1, 12, 0));
        SingleLoan singleLoan2 = createSingleLoan(book1984, user, LocalDateTime.of(2020, 1, 1, 12, 0));
        Loan loan = createLoan(List.of(singleLoan1, singleLoan2));
        loanRepository.save(loan);

        assertThat(loanRepository.findAll(), containsInAnyOrder(loan));
        assertThat(singleLoanRepository.findAll(), containsInAnyOrder(singleLoan1, singleLoan2));
    }

    @Test
    public void deletingLoanTransitivelyDeletesSingleLoansButNotUserOrBook() {
        SingleLoan singleLoan1 = createSingleLoan(animalFarm, user, LocalDateTime.of(2020, 1, 1, 12, 0));
        SingleLoan singleLoan2 = createSingleLoan(book1984, user, LocalDateTime.of(2020, 1, 1, 12, 0));
        Loan loan = createLoan(List.of(singleLoan1, singleLoan2));
        loanRepository.save(loan);

        assertThat(loanRepository.findAll(), containsInAnyOrder(loan));
        assertThat(singleLoanRepository.findAll(), containsInAnyOrder(singleLoan1, singleLoan2));

        loanRepository.delete(loan);

        assertThat(loanRepository.findAll(), is(empty()));
        assertThat(singleLoanRepository.findAll(), is(empty()));
        assertThat(bookRepository.findAll(), containsInAnyOrder(animalFarm, book1984));
        assertThat(userRepository.findAll(), containsInAnyOrder(user));
    }

    @Test
    public void loanMustNotBeNull() {
        assertThrows(InvalidDataAccessApiUsageException.class, ()->loanRepository.save(null));
    }
}
