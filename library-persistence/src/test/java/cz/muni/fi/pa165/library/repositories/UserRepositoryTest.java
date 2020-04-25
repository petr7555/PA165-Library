package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.Role;
import cz.muni.fi.pa165.library.entities.User;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import javax.validation.ConstraintViolationException;

import static cz.muni.fi.pa165.library.Utils.createTestUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.Matchers.is;


/**
 * @author Petr johnik 485122
 * @since 21.04.2020
 */
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    private Role roleUser;

    @BeforeEach
    public void createRoleUser() {
        roleUser = new Role(Role.RoleType.USER);
        roleRepository.save(roleUser);
    }

    @Test
    public void userMustNotBeNull() {
        assertThrows(InvalidDataAccessApiUsageException.class, ()->userRepository.save(null));
    }

    @Test
    public void findByEmail() {
        User john = createTestUser("John", "Doe", roleUser);
        userRepository.save(john);

        assertEquals(userRepository.findByEmail(john.getEmail()), john);
    }

    @Test
    public void createTwoUsers() {
        User john = createTestUser("John", "Doe", roleUser);
        userRepository.save(john);

        User boris = createTestUser("Boris", "Smith", roleUser);
        userRepository.save(boris);

        assertThat(userRepository.findAll(), containsInAnyOrder(john, boris));
    }

    @Test
    public void deleteById() {
        User john = createTestUser("John", "Doe", roleUser);
        userRepository.save(john);

        User boris = createTestUser("Boris", "Smith", roleUser);
        userRepository.save(boris);

        userRepository.deleteById(boris.getId());

        assertThat(userRepository.findAll(), containsInAnyOrder(john));
    }

    @Test
    public void emailMustBeUnique() {
        User john = createTestUser("John", "Doe", roleUser);
        userRepository.save(john);
        User boris = createTestUser("Boris", "Smith", roleUser);
        boris.setEmail(john.getEmail());
        assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(boris));
    }

    @Test
    public void firstNameMustNotBeNull() {
        User user = createTestUser(null, "Doe", roleUser);
        assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
    }

    @Test
    public void lastNameMustNotBeNull() {
        User user = createTestUser("John", null, roleUser);
        assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
    }

    @Test
    public void emailMustNotBeNull() {
        User user = createTestUser("John", null, roleUser);
        user.setEmail(null);
        assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
    }

    @Test
    public void roleMustNotBeNull() {
        User user = createTestUser("John", "Doe", roleUser);
        user.setEmail(null);
        assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
    }

    @Test
    public void deletingUserDoesNotDeleteRole() {
        User user = createTestUser("John", "Doe", roleUser);
        userRepository.save(user);
        assertThat(userRepository.findAll(), containsInAnyOrder(user));
        assertThat(roleRepository.findAll(), containsInAnyOrder(roleUser));

        userRepository.delete(user);
        assertThat(userRepository.findAll(), is(empty()));
        assertThat(roleRepository.findAll(), containsInAnyOrder(roleUser));
    }
}
