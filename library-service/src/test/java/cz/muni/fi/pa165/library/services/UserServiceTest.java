package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.Role;
import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

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
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void findAll() {
        Role roleUser = new Role(Role.RoleType.USER);
        Role roleAdmin = new Role(Role.RoleType.ADMIN);

        User user1 = createTestUser("John", "Doe", roleUser);
        User user2 = createTestUser("Boris", "Smith", roleUser);
        User user3 = createTestUser("Peter", "Griffin", roleAdmin);

        when(userRepository.findAll())
                .thenReturn(List.of(user1, user2, user3));

        assertThat(userService.findAll(), containsInAnyOrder(user1, user2, user3));
    }

    @Test
    public void findAllEmpty() {
        when(userRepository.findAll())
                .thenReturn(Collections.emptyList());

        assertThat(userService.findAll(), is(empty()));
    }
}
