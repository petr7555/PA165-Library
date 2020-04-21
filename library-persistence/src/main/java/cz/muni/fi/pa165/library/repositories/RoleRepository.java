package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Petr Janik 485122
 * @since 21.04.2020
 * <p>
 * A repository for Role entity.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
}
