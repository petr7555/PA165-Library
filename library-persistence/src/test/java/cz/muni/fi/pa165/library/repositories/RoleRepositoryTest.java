package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import javax.validation.ConstraintViolationException;

import static cz.muni.fi.pa165.library.entities.Role.RoleType.ADMIN;
import static cz.muni.fi.pa165.library.entities.Role.RoleType.USER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Petr Janik 485122
 * @since 21.04.2020
 */
@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void addOneRole() {
        Role role = new Role(USER);
        roleRepository.save(role);
        assertThat(roleRepository.findAll(), containsInAnyOrder(role));
    }

    @Test
    public void roleMustNotBeNull() {
        assertThrows(InvalidDataAccessApiUsageException.class, ()->roleRepository.save(null));
    }

    @Test
    public void addTwoDifferentRoles() {
        Role user = new Role(USER);
        roleRepository.save(user);
        Role admin = new Role(ADMIN);
        roleRepository.save(admin);
        assertThat(roleRepository.findAll(), containsInAnyOrder(user, admin));
    }

    @Test
    public void cannotHaveTwoSameRoles() {
        Role user1 = new Role(USER);
        roleRepository.save(user1);
        Role user2 = new Role(USER);
        assertThrows(DataIntegrityViolationException.class, () -> roleRepository.save(user2));
    }

    @Test
    public void roleNameMustNotBeNull() {
        Role user = new Role();
        assertThrows(ConstraintViolationException.class, () -> roleRepository.save(user));
    }
}
