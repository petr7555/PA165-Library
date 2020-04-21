package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.Role;
import cz.muni.fi.pa165.library.entities.SingleLoan;
import cz.muni.fi.pa165.library.entities.User;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static cz.muni.fi.pa165.library.Utils.createTestBook1984;
import static cz.muni.fi.pa165.library.Utils.createTestBookAnimalFarm;
import static cz.muni.fi.pa165.library.Utils.createTestUser;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Petr Janik 485122
 * @since 29.03.2020
 */
@DataJpaTest
public class SingleLoanRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SingleLoanRepository singleLoanRepository;

    private static Role roleUser;

    @BeforeClass
    public static void createRoleUser() {
        roleUser = new Role(Role.RoleType.USER);
    }

    @Test
    public void createSingleLoan() {
        SingleLoan singleLoan = new SingleLoan();

        Book book = createTestBookAnimalFarm();
        entityManager.persist(book);

        User user = createTestUser("John", "Doe", roleUser);
        entityManager.persist(user);

        singleLoan.setBook(book);
        singleLoan.setUser(user);
        singleLoan.setBorrowedAt(LocalDateTime.of(2020, 1, 1, 12, 0));

        entityManager.persist(singleLoan);

        List<SingleLoan> singleLoans = singleLoanRepository.findAll();

        assertThat(singleLoans, CoreMatchers.hasItems(singleLoan));
    }

    @Test
    public void multipleSingleLoansForUser() {
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

        List<SingleLoan> singleLoans = singleLoanRepository.findAll();

        assertThat(singleLoans, CoreMatchers.hasItems(singleLoan, secondLoan));
    }
}
