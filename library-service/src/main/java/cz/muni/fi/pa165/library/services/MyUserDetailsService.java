package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.Role;
import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Petr Janik 485122
 * @since 28.04.2020
 * <p>
 * A service for basic Spring Security authentication.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyUserDetailsService.class);

    final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) {
        try {
            LOGGER.info("Searching for user with email {}.", email);
            final User user = userRepository.findByEmail(email);
            if (user == null) {
                LOGGER.error("Unable to find user with email {}.", email);
                throw new UsernameNotFoundException("No user found with username: " + email);
            }
            LOGGER.info("A user with email {} has been found.", email);
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true, true, true, getAuthorities(user.getRoles()));
        } catch (final Exception e) {
            LOGGER.error("An exception occurred when logging in user with email {}", email, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts a collection of Roles to a collection of GrantedAuthorities
     *
     * @param roles
     * @return
     */
    private Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleType().getRoleName()))
                .collect(Collectors.toList());
    }
}
