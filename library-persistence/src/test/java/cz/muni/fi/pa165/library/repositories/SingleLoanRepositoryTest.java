package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.Role;
import cz.muni.fi.pa165.library.entities.SingleLoan;
import cz.muni.fi.pa165.library.entities.User;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
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
import static org.hamcrest.Matchers.containsInAnyOrder;

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

    @Test
    public void createSingleLoan() {
        SingleLoan singleLoan = new SingleLoan();
        singleLoan.setBook(animalFarm);
        singleLoan.setUser(user);
        singleLoan.setBorrowedAt(LocalDateTime.of(2020, 1, 1, 12, 0));
        singleLoanRepository.save(singleLoan);

        assertThat(singleLoanRepository.findAll(), containsInAnyOrder(singleLoan));
    }

    @Test
    public void multipleSingleLoansForUser() {
        SingleLoan singleLoan1 = new SingleLoan();
        singleLoan1.setBook(animalFarm);
        singleLoan1.setUser(user);
        singleLoan1.setBorrowedAt(LocalDateTime.of(2020, 1, 1, 12, 0));
        singleLoanRepository.save(singleLoan1);

        SingleLoan singleLoan2 = new SingleLoan();
        singleLoan2.setBook(book1984);
        singleLoan2.setUser(user);
        singleLoan2.setBorrowedAt(LocalDateTime.of(2020, 1, 1, 12, 0));
        singleLoanRepository.save(singleLoan2);

        assertThat(singleLoanRepository.findAll(), containsInAnyOrder(singleLoan1, singleLoan2));
    }
}
