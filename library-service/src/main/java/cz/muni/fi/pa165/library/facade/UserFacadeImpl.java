package cz.muni.fi.pa165.library.facade;

import cz.muni.fi.pa165.library.dto.UserDTO;
import cz.muni.fi.pa165.library.services.MappingService;
import cz.muni.fi.pa165.library.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 06.04.2020
 * <p>
 * Implements methods defined in UserFacade.
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserFacadeImpl.class);

    private final MappingService mappingService;
    private final UserService userService;

    public UserFacadeImpl(MappingService mappingService, UserService userService) {
        this.mappingService = mappingService;
        this.userService = userService;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        LOGGER.info("Finding all users.");
        return mappingService.mapTo(userService.findAll(), UserDTO.class);
    }
}
