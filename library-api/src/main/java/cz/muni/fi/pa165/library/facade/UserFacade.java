package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.UserDTO;

import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 06.04.2020
 * <p>
 * A facade for working with UserDTOs.
 */
public interface UserFacade {
    List<UserDTO> findAllUsers();

    UserDTO findUserByEmail(String email);
}
