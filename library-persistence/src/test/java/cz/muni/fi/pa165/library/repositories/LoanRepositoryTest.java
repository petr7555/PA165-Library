package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.*;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.Arrays;

import static cz.muni.fi.pa165.library.Utils.createTestUser;

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

    private static Role roleUser;

    @BeforeAll
    public static void createRoleUser() {
        roleUser = new Role(Role.RoleType.USER);
    }

    @Test
    public void createLoan() {
        Book animalFarm = createTestBookAnimalFarm();
        Book book1984 = createTestBook1984();
        entityManager.persist(animalFarm);
        entityManager.persist(book1984);

        User user = createTestUser("John", "Doe", roleUser);
        entityManager.persist(user);

        SingleLoan singleLoan = new SingleLoan();
        singleLoan.setBook(animalFarm);
        singleLoan.setUser(user);
        singleLoan.setBorrowedAt(LocalDateTime.of(2020, 1, 1, 12, 0));
        entityManager.persist(singleLoan);

        SingleLoan secondLoan = new SingleLoan();
        secondLoan.setBook(book1984);
        secondLoan.setUser(user);
        secondLoan.setBorrowedAt(LocalDateTime.of(2020, 1, 1, 12, 0));
        entityManager.persist(secondLoan);

        Loan loan = new Loan();
        loan.setLoans(Arrays.asList(singleLoan, secondLoan));
        entityManager.persist(loan);


        MatcherAssert.assertThat(loanRepository.findAll(), CoreMatchers.hasItems(loan));
        MatcherAssert.assertThat(singleLoanRepository.findAll(), CoreMatchers.hasItems(singleLoan, secondLoan));
    }


    private Book createTestBookAnimalFarm() {
        Book book = new Book();
        book.setAuthor("George Orwell");
        book.setTitle("Animal farm");
        return book;
    }

    private Book createTestBook1984() {
        Book book = new Book();
        book.setAuthor("George Orwell");
        book.setTitle("1984");
        return book;
    }
}
