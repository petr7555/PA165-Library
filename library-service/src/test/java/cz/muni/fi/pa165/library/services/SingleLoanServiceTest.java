package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.SingleLoan;
import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.repositories.SingleLoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static cz.muni.fi.pa165.library.Utils.createSingleLoan;
import static cz.muni.fi.pa165.library.Utils.createTestBook1984;
import static cz.muni.fi.pa165.library.Utils.createTestBookAnimalFarm;
import static cz.muni.fi.pa165.library.Utils.createTestUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

/**
 * @author Petr Janik 485122
 * @since 21.04.2020
 */
@SpringBootTest
public class SingleLoanServiceTest {

    @Mock
    private SingleLoanRepository singleLoanRepository;

    @InjectMocks
    private SingleLoanService singleLoanService;

    private SingleLoan singleLoan1;
    private SingleLoan singleLoan2;
    private SingleLoan singleLoan3;
    private User user1;
    private Book book2;

    @BeforeEach
    public void setupMock() {
        Book book1 = createTestBookAnimalFarm();
        book1.setId(1);
        book2 = createTestBook1984();
        book2.setId(2);

        user1 = createTestUser("John", "Doe");
        user1.setId(1);
        User user2 = createTestUser("Boris", "Smith");
        user2.setId(2);

        singleLoan1 = createSingleLoan(book1, user1);
        singleLoan2 = createSingleLoan(book2, user1);
        singleLoan3 = createSingleLoan(book2, user2);

        when(singleLoanRepository.findAll())
                .thenReturn(List.of(singleLoan1, singleLoan2, singleLoan3));
    }

    @Test
    public void findForUser() {
        assertThat(singleLoanService.findForUser(user1.getId()), containsInAnyOrder(singleLoan1, singleLoan2));
    }

    @Test
    public void findForBook() {
        assertThat(singleLoanService.findForBook(book2.getId()), containsInAnyOrder(singleLoan2, singleLoan3));
    }

    @Test
    public void findForNonexistentUser() {
        assertThat(singleLoanService.findForUser(123), is(empty()));
    }

    @Test
    public void findForNonexistentBook() {
        assertThat(singleLoanService.findForBook(123), is(empty()));
    }
}
