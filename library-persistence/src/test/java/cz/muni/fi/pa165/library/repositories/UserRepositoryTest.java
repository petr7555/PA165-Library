package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.Role;
import cz.muni.fi.pa165.library.entities.User;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.persistence.PersistenceException;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAndIs;
import static cz.muni.fi.pa165.library.Utils.createTestUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Petr johnik 485122
 * @since 21.04.2020
 */
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private static Role roleUser;

    @BeforeClass
    public static void createRoleUser() {
        roleUser = new Role(Role.RoleType.USER);
    }

    @Test
    public void findById() {
        // given
        User john = createTestUser("John", "Doe", roleUser);
        entityManager.persist(john);

        // then
        assertThat(userRepository.findById(john.getId()), isPresentAndIs(john));
    }

    @Test
    public void findAll() {
        // given
        User john = createTestUser("John", "Doe", roleUser);
        entityManager.persist(john);

        User boris = createTestUser("Boris", "Smith", roleUser);
        entityManager.persist(boris);

        assertThat(userRepository.findAll(), containsInAnyOrder(john, boris));
    }

    @Test
    public void deleteById() {
        // given
        User john = createTestUser("John", "Doe", roleUser);
        entityManager.persist(john);

        User boris = createTestUser("Boris", "Smith", roleUser);
        entityManager.persist(boris);

        userRepository.deleteById(boris.getId());

        assertThat(userRepository.findAll(), containsInAnyOrder(john));
    }

    @Test
    public void emailMustBeUnique() {
        User john = createTestUser("John", "Doe", roleUser);
        entityManager.persist(john);
        User boris = createTestUser("Boris", "Smith", roleUser);
        boris.setEmail(john.getEmail());
        assertThrows(PersistenceException.class, () -> entityManager.persist(boris));
    }
}
