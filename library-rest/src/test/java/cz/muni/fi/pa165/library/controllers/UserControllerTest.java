package cz.muni.fi.pa165.library.controllers;

import cz.muni.fi.pa165.library.facade.UserFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Petr Janik 485122
 * @since 16.05.2020
 */
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @MockBean
    private UserFacade userFacade;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "USER")
    public void findAllUsersAsUser() throws Exception {
        mockMvc.perform(get("/pa165/rest/users"))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void FindUserByEmailAsUser() throws Exception {
        mockMvc.perform(get("/pa165/rest/users")
                .queryParam("email", "mail@gmail.com"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
