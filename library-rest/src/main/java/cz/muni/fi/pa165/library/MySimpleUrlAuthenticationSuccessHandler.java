package cz.muni.fi.pa165.library;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Petr Janik 485122
 * @since 28.04.2020
 * <p>
 * Sets session and cookies after successful authentication.
 */
public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MySimpleUrlAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        HttpSession session = httpServletRequest.getSession();
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("username", authUser.getUsername());
        session.setAttribute("authorities", authentication.getAuthorities());

        // for React frontend
        List<String> authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        Cookie username = new Cookie("username", authUser.getUsername());
        String authoritiesString = String.join("-", authorities);
        Cookie authority = new Cookie("authorities", authoritiesString);
        httpServletResponse.addCookie(username);
        httpServletResponse.addCookie(authority);

        //set the response to OK status
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        LOGGER.info("A user {} with role {} has logged in.", username.getValue(), authority.getValue());
        //since we have created our custom success handler, its up to us to where
        //we will redirect the user after successfully login
        httpServletResponse.sendRedirect("/");
    }
}
