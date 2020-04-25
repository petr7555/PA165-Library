package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.SingleLoan;
import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.services.MappingService;
import cz.muni.fi.pa165.library.services.SingleLoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static cz.muni.fi.pa165.library.Utils.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Petr Janik 485122
 * @since 12.04.2020
 */
@SpringBootTest
class SingleLoanFacadeImplTest {

    @Autowired
    private MappingService mappingService;

    @Mock
    private SingleLoanService singleLoanService;

    private SingleLoanFacadeImpl singleLoanFacadeImpl;

    @BeforeEach
    public void setUp() {
        singleLoanFacadeImpl = new SingleLoanFacadeImpl(mappingService, singleLoanService);
    }

    @Test
    public void findForUser() {
        User user1 = createTestUser("John", "Doe");
        user1.setId(1);
        SingleLoan singleLoan1 = createSingleLoan(createTestBookAnimalFarm(), user1);
        SingleLoan singleLoan2 = createSingleLoan(createTestBook1984(), user1);

        when(singleLoanService.findForUser(1)).thenReturn(List.of(singleLoan1, singleLoan2));

        assertThat(singleLoanFacadeImpl.findForUser(user1.getId()),
                containsInAnyOrder(dtoCopyOfSingleLoan(singleLoan1), dtoCopyOfSingleLoan(singleLoan2)));
    }

    @Test
    public void findForBook() {
        Book book = createTestBookAnimalFarm();
        book.setId(1);

        User user1 = createTestUser("John", "Doe");
        User user2 = createTestUser("Anne", "Smith");

        SingleLoan singleLoan1 = createSingleLoan(book, user1);
        SingleLoan singleLoan2 = createSingleLoan(book, user2);

        when(singleLoanService.findForBook(1)).thenReturn(List.of(singleLoan1, singleLoan2));

        assertThat(singleLoanFacadeImpl.findForBook(book.getId()),
                containsInAnyOrder(dtoCopyOfSingleLoan(singleLoan1), dtoCopyOfSingleLoan(singleLoan2)));
    }
}
