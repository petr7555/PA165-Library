package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.services.MappingService;
import cz.muni.fi.pa165.library.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static cz.muni.fi.pa165.library.Utils.createTestUser;
import static cz.muni.fi.pa165.library.Utils.dtoCopyOfUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.when;

/**
 * @author Petr Janik 485122
 * @since 12.04.2020
 */
@SpringBootTest
class UserFacadeImplTest {

    @Autowired
    private MappingService mappingService;

    @Mock
    private UserService userService;

    private UserFacadeImpl userFacadeImpl;

    @BeforeEach
    public void setUp() {
        userFacadeImpl = new UserFacadeImpl(mappingService, userService);
    }

    @Test
    void findAllUsers() {
        User user1 = createTestUser("John", "Doe");
        User user2 = createTestUser("Anne", "Smith");

        when(userService.findAll()).thenReturn(List.of(user1, user2));

        assertThat(userFacadeImpl.findAllUsers(), containsInAnyOrder(dtoCopyOfUser(user1), dtoCopyOfUser(user2)));
    }
}
