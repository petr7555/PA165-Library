package cz.muni.fi.pa165.library.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import cz.muni.fi.pa165.library.dto.UserDTO;
import cz.muni.fi.pa165.library.dto.View;
import cz.muni.fi.pa165.library.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 21.04.2020
 * <p>
 * A controller for endpoints related to UserDTO.
 */
@RestController
public class UserController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @JsonView(View.Users.class)
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> findAllUsers() {
        LOGGER.info("Finding all users.");
        return userFacade.findAllUsers();
    }

    @JsonView(View.Users.class)
    @GetMapping(value = "/users", params = "email", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO findUserByEmail(@RequestParam String email) {
        LOGGER.info("Finding user with email {}.", email);
        return userFacade.findUserByEmail(email);
    }
}
