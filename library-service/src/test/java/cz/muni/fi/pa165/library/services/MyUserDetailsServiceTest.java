package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.Role;
import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

import static cz.muni.fi.pa165.library.Utils.createTestUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Petr Janik 485122
 * @since 25.04.2020
 */
@SpringBootTest
class MyUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MyUserDetailsService myUserDetailsService;

    @Test
    void userWithOneRole() {
        Role roleUser = new Role(Role.RoleType.USER);
        User user1 = createTestUser("John", "Doe", roleUser);

        when(userRepository.findByEmail(user1.getEmail())).thenReturn(user1);

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(user1.getEmail());
        assertEquals(user1.getEmail(), userDetails.getUsername());
        assertEquals(user1.getPassword(), userDetails.getPassword());
        List<String> authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        assertThat(authorities, containsInAnyOrder("ROLE_USER"));
    }

    @Test
    void userWithTwoRoles() {
        Role roleUser = new Role(Role.RoleType.USER);
        Role roleAdmin = new Role(Role.RoleType.ADMIN);
        User user = new User("John", "Doe", "john@doe.com", "Doe1234", List.of(roleUser, roleAdmin));

        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(user.getEmail());
        assertEquals(user.getEmail(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
        List<String> authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        assertThat(authorities, containsInAnyOrder("ROLE_USER", "ROLE_ADMIN"));
    }
}
